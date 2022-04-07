package tire_store.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Role;
import tire_store.entities.User;

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

	public List<Role> getRoles() {
		List<Role> list = null;
		RoleDAO roleDAO = new RoleDAO();
		Query query = em.createQuery("from Role r");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
