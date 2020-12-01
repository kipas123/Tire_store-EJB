package tire_store.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tire_store.entities.User;

@Stateless
public class UserDAO {

	// Dependency injection (no setter method is needed)
		@PersistenceContext
		protected EntityManager em;

		public void insert(User user) {
			em.persist(user);
		}

		public User update(User user) {
			return em.merge(user);
		}

		public void delete(User user) {
			em.remove(em.merge(user));
		}

		public User get(User id) {
			return em.find(User.class, id);
		}
	
}
