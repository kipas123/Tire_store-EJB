package tire_store.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Storeorder;
import tire_store.entities.Tireproduct;
import tire_store.entities.TireproductHasOrder;
import tire_store.entities.User;

@Stateless
public class TireproductHasOrderDAO {

	// Dependency injection (no setter method is needed)
	@PersistenceContext
	protected EntityManager em;

	public Tireproduct update(Tireproduct tireproduct) {
		return em.merge(tireproduct);
	}

	public void delete(Tireproduct tireproduct) {
		em.remove(em.merge(tireproduct));
	}

	public Tireproduct get(Tireproduct id) {
		return em.find(Tireproduct.class, id);
	}

	public void insert(int idtire, int idorder, int quantity) {
		Query query = em.createNativeQuery(
				"insert into storedb.tireproduct_has_order (tireproduct_idtire, order_idorder, quantity) values (?, ?, ?)");
		query.setParameter(1, idtire);
		query.setParameter(2, idorder);
		query.setParameter(3, quantity);
		query.executeUpdate();
	}

	public List<TireproductHasOrder> getUserOrdersProducts(Storeorder order) {
		List<TireproductHasOrder> list = null;

		Query query = em.createQuery("from TireproductHasOrder s " + "where s.storeorder =:order");
		query.setParameter("order", order);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
