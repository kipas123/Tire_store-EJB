package tire_store.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Storeorder;
import tire_store.entities.User;

@Stateless
public class StoreorderDAO {
	@Inject
	FacesContext ctx;
	@EJB
	OrderStatusDAO orderStatusDAO;
	// Dependency injection (no setter method is needed)
	@PersistenceContext
	protected EntityManager em;

	public void insert(Storeorder order) {
		em.persist(order);
	}

	public Storeorder update(Storeorder order) {
		return em.merge(order);
	}

	public void delete(Storeorder order) {
		em.remove(em.merge(order));
	}

	public Storeorder get(Storeorder id) {
		return em.find(Storeorder.class, id);
	}

	public Storeorder get(int id) {
		return em.find(Storeorder.class, id);
	}

//		public void insertOrder(Storeorder order){
//			Query query = em.createNativeQuery("insert into storedb.order (orderdata, orderstatus_idorderstatus, totalprice, user_iduser) values (?, ?, ?, ?)");
//			query.setParameter(1, order.getOrderdata());
//			query.setParameter(2, order.getOrderstatus());
//			query.setParameter(3, order.getTotalprice());
//			query.setParameter(4, order.getUser().getIduser());
//			query.executeUpdate();
//		}
//		
	public Storeorder getLastOrderId() {
		try {
			Query query = em.createQuery(("from Storeorder s ORDER BY s.idorder DESC")).setMaxResults(1);
			return (Storeorder) query.getSingleResult();

		} catch (javax.persistence.NoResultException e) {
			return null;
		}
	}

	public List<Storeorder> getUserOrders(User user, int offset, int pageSize) {
		List<Storeorder> list = null;

		Query query = em.createQuery("from Storeorder s " + "where s.user =:iduser " + "ORDER BY s.orderdata desc")
				.setFirstResult(offset).setMaxResults(pageSize);
		query.setParameter("iduser", user);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

//	public List<Storeorder> getOrdersToHandle() {
//		List<Storeorder> list = null;
//
//		Query query = em.createQuery(
//				"from Storeorder s " + "where s.orderstatus =:orderstatus OR s.orderstatus =:orderstatus2");
//		query.setParameter("orderstatus", orderStatusDAO.get(3));
//		query.setParameter("orderstatus2", orderStatusDAO.get(4));
//
//		try {
//			list = query.getResultList();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return list;
//	}

	public List<Storeorder> getOrdersToHandle(int offset, int pageSize) {
		List<Storeorder> list = null;

		Query query = em.createQuery("from Storeorder s "
				+ "where s.orderstatus =:orderstatus OR s.orderstatus =:orderstatus2 " + "ORDER BY s.orderdata desc")
				.setFirstResult(offset).setMaxResults(pageSize);
		;
		query.setParameter("orderstatus", orderStatusDAO.get(3));
		query.setParameter("orderstatus2", orderStatusDAO.get(4));

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Storeorder> getOrdersToHandle(String search, int offset, int pageSize) {
		List<Storeorder> list = null;
		Query query;

		int idorder = 0;
		try {
			idorder = Integer.parseInt(search);
			query = em.createQuery("from Storeorder s "
					+ "where (s.orderstatus =:orderstatus OR s.orderstatus =:orderstatus2) AND s.idorder=:search "
					+ "ORDER BY s.orderdata desc").setFirstResult(offset).setMaxResults(pageSize);
			query.setParameter("orderstatus", orderStatusDAO.get(3));
			query.setParameter("orderstatus2", orderStatusDAO.get(4));
			query.setParameter("search", idorder);
			list = query.getResultList();
			return list;
		} catch (NumberFormatException e) {
			query = em.createQuery("from Storeorder s "
					+ "where (s.orderstatus =:orderstatus OR s.orderstatus =:orderstatus2) AND s.user.login=:search "
					+ "ORDER BY s.orderdata desc").setFirstResult(offset).setMaxResults(pageSize);
			query.setParameter("orderstatus", orderStatusDAO.get(3));
			query.setParameter("orderstatus2", orderStatusDAO.get(4));
			query.setParameter("search", search);
			list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Storeorder> getOrders(int offset, int pageSize) {
		List<Storeorder> list = null;

		Query query = em.createQuery("from Storeorder s ").setFirstResult(offset).setMaxResults(pageSize);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Storeorder> getOrders(String search, int offset, int pageSize) {
		List<Storeorder> list = null;
		Query query;

		int idorder = 0;
		try {
			idorder = Integer.parseInt(search);
			query = em.createQuery("from Storeorder s " + "where s.idorder=:search").setFirstResult(offset)
					.setMaxResults(pageSize);
			query.setParameter("search", idorder);
			list = query.getResultList();
			return list;
		} catch (NumberFormatException e) {
			query = em.createQuery("from Storeorder s " + "where s.user.login=:search").setFirstResult(offset)
					.setMaxResults(pageSize);
			query.setParameter("search", search);
			list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public int countOrdersToHandle(String search) {
		if (search == null)
			return 0;
		try {
			int idorder = Integer.parseInt(search);
			Query query = em.createNativeQuery("SELECT COUNT(*) " + "FROM storedb.storeorder s "
					+ "WHERE (s.orderstatus_idorderstatus =:orderstatus OR s.orderstatus_idorderstatus =:orderstatus2) AND s.idorder=:search");
			query.setParameter("orderstatus", 3);
			query.setParameter("orderstatus2", 4);
			query.setParameter("search", idorder);
			return ((Number) query.getSingleResult()).intValue();
		} catch (javax.persistence.NoResultException e) {
			return 0;
		} catch (NumberFormatException e) {
			Query query = em.createNativeQuery("SELECT COUNT(*) " + "FROM storedb.storeorder s "
					+ "INNER JOIN storedb.user " + "ON user_iduser = iduser "
					+ "WHERE (s.orderstatus_idorderstatus =:orderstatus OR s.orderstatus_idorderstatus =:orderstatus2) and login=:search");
			query.setParameter("orderstatus", 3);
			query.setParameter("orderstatus2", 4);
			query.setParameter("search", search);
			return ((Number) query.getSingleResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public int countOrdersToHandle() {
		try {
			Query query = em.createNativeQuery("SELECT COUNT(*)" + "FROM storedb.storeorder s "
					+ "WHERE (s.orderstatus_idorderstatus =:orderstatus OR s.orderstatus_idorderstatus =:orderstatus2);");
			query.setParameter("orderstatus", 3);
			query.setParameter("orderstatus2", 4);
			int a = ((Number) query.getSingleResult()).intValue();
			return a;
		} catch (javax.persistence.NoResultException e) {
			return 0;
		}
	}

	public int countOrders(String search) {
		if (search == null)
			return 0;
		try {
			int idorder = Integer.parseInt(search);
			Query query = em
					.createNativeQuery("SELECT COUNT(*) " + "FROM storedb.storeorder s " + "WHERE s.idorder=:search");
			query.setParameter("search", idorder);
			return ((Number) query.getSingleResult()).intValue();
		} catch (javax.persistence.NoResultException e) {
			return 0;
		} catch (NumberFormatException e) {
			Query query = em.createNativeQuery("SELECT COUNT(*) " + "FROM storedb.storeorder s "
					+ "INNER JOIN storedb.user " + "ON user_iduser = iduser " + "WHERE login=:search");
			query.setParameter("search", search);
			return ((Number) query.getSingleResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public int countOrders() {
		try {
			Query query = em.createNativeQuery("SELECT COUNT(*)" + "FROM storedb.storeorder;");
			int a = ((Number) query.getSingleResult()).intValue();
			return a;
		} catch (javax.persistence.NoResultException e) {
			return 0;
		}
	}

	public int countUserOrders(User user) {
		try {
			Query query = em
					.createNativeQuery("SELECT COUNT(*)" + "FROM storedb.storeorder s " + "where s.user_iduser=:user");
			query.setParameter("user", (int) user.getIduser());
			int a = ((Number) query.getSingleResult()).intValue();
			return a;
		} catch (javax.persistence.NoResultException e) {
			return 0;
		}
	}
}
