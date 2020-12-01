package tire_store.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tire_store.entities.Order;

@Stateless
public class OrderDAO {

	// Dependency injection (no setter method is needed)
		@PersistenceContext
		protected EntityManager em;

		public void insert(Order order) {
			em.persist(order);
		}

		public Order update(Order order) {
			return em.merge(order);
		}

		public void delete(Order order) {
			em.remove(em.merge(order));
		}

		public Order get(Order id) {
			return em.find(Order.class, id);
		}
	
}
