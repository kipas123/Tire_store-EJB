package tire_store.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tire_store.entities.Role;

@Stateless
public class RoleDAO {

	// Dependency injection (no setter method is needed)
		@PersistenceContext
		protected EntityManager em;

		public void insert(Role role) {
			em.persist(role);
		}

		public Role update(Role role) {
			return em.merge(role);
		}

		public void delete(Role role) {
			em.remove(em.merge(role));
		}

		public Role get(int id) {
			return em.find(Role.class, id);
		}
	
}
