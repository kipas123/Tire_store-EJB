package tire_store.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Orderstatus;
import tire_store.entities.Storeorder;
import tire_store.entities.Tireproduct;

@Stateless
public class TireproductDAO {

	// Dependency injection (no setter method is needed)
		@PersistenceContext
		protected EntityManager em;

		public void insert(Tireproduct tireproduct) {
			em.persist(tireproduct);
		}

		public Tireproduct update(Tireproduct tireproduct) {
			return em.merge(tireproduct);
		}

		public void delete(Tireproduct tireproduct) {
			em.remove(em.merge(tireproduct));
		}

		public Tireproduct get(Tireproduct id) {
			return em.find(Tireproduct.class, id);
		}
		public Tireproduct get(int id) {
			return em.find(Tireproduct.class, id);
		}
		public List<Tireproduct> getAllProducts() {
			List<Tireproduct> list = null;

			Query query = em.createQuery("from Tireproduct s ");

			try {
				list = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return list;
		}
	
}
