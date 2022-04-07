package tire_store.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Orderstatus;
import tire_store.entities.Storeorder;
import tire_store.entities.User;

@Stateless
public class OrderStatusDAO {

	// Dependency injection (no setter method is needed)
	@PersistenceContext
	protected EntityManager em;

	public void insert(Orderstatus orderstatus) {
		em.persist(orderstatus);
	}

	public Orderstatus update(Orderstatus orderstatus) {
		return em.merge(orderstatus);
	}

	public void delete(Orderstatus orderstatus) {
		em.remove(em.merge(orderstatus));
	}

	public Orderstatus get(int id) {
		return em.find(Orderstatus.class, id);
	}

	public List<Orderstatus> getOrderStatuses() {
		List<Orderstatus> list = null;

		Query query = em.createQuery("from Orderstatus s ");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
