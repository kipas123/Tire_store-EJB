package tire_store.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Role;
import tire_store.entities.Tireproduct;
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

	public User getbyUsername(String login) {
		try {
			Query query = em.createQuery("from User p where p.login=:login");
			query.setParameter("login", login);

			return (User) query.getSingleResult();
		}catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	public User getloginAccount(String login, String password) {
		try {
			Query query = em.createQuery("from User u where u.login=:login AND u.password=:password");
			query.setParameter("login", login);
			query.setParameter("password", password);
			return (User) query.getSingleResult();

		} catch (javax.persistence.NoResultException e) {
			return null;
		}

	}

	public List<User> getAdmins() {
		List<User> list = null;
		RoleDAO roleDAO = new RoleDAO();
		Query query = em.createQuery("from User u where u.role=1 or u.role=3");

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<User> getAdmins(String login) {
		List<User> list = null;
		RoleDAO roleDAO = new RoleDAO();
		Query query = em.createQuery("from User u where u.login=:login");
		query.setParameter("login", login);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
}
