package tire_store.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Storeorder;
import tire_store.entities.User;

@Stateless
public class StoreorderDAO {
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

			}catch(javax.persistence.NoResultException e) {
				return null;
			}
		}
		public List<Storeorder> getUserOrders(User user) {
			List<Storeorder> list = null;

			Query query = em.createQuery("from Storeorder s " + "where s.user =:iduser");
			query.setParameter("iduser", user);

			try {
				list = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return list;
		}
		public List<Storeorder> getOrdersToHandle() {
			List<Storeorder> list = null;

			Query query = em.createQuery("from Storeorder s " + "where s.orderstatus =:orderstatus OR s.orderstatus =:orderstatus2");
			query.setParameter("orderstatus", orderStatusDAO.get(3));
			query.setParameter("orderstatus2", orderStatusDAO.get(4));

			try {
				list = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return list;
		}
		public List<Storeorder> getOrders() {
			List<Storeorder> list = null;

			Query query = em.createQuery("from Storeorder s ");

			try {
				list = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}

			return list;
		}

	
}
