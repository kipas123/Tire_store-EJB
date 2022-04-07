package tire_store.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Orderstatus;
import tire_store.entities.Tireproduct;
import tire_store.entities.Tiretype;

@Stateless
public class TiretypeDAO {

	// Dependency injection (no setter method is needed)
	@PersistenceContext
	protected EntityManager em;

	public void insert(Tiretype tiretype) {
		em.persist(tiretype);
	}

	public Tiretype update(Tiretype tiretype) {
		return em.merge(tiretype);
	}

	public void delete(Tiretype tiretype) {
		em.remove(em.merge(tiretype));
	}

	public Tiretype get(Tiretype id) {
		return em.find(Tiretype.class, id);
	}

	public Tiretype get(int id) {
		return em.find(Tiretype.class, id);
	}

	public List<Tiretype> getTiretypes() {
		List<Tiretype> list = null;

		Query query = em.createQuery("from Tiretype s ");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
