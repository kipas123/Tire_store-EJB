package tire_store.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Offer;
import tire_store.entities.Tireproduct;
import tire_store.entities.User;

@Stateless
public class OfferDAO {

	// Dependency injection (no setter method is needed)
		@PersistenceContext
		protected EntityManager em;

		public void insert(Offer offer) {
			em.persist(offer);
		}

		public Offer update(Offer offer) {
			return em.merge(offer);
		}

		public void delete(Offer offer) {
			em.remove(em.merge(offer));
		}

		public Offer get(Offer id) {
			return em.find(Offer.class, id);
		}
		
		public Offer get(int id) {
			try {
				Query query = em.createQuery("from Offer u where u.idoffer=:id");
				query.setParameter("id", id);
				return (Offer)query.getSingleResult();

			}catch(javax.persistence.NoResultException e) {
				return null;
			}
		}
		public Offer get(Tireproduct tireproduct) {
			try {
				Query query = em.createQuery("from Offer u where u.tireproduct=:tireproduct");
				query.setParameter("tireproduct", tireproduct);
				return (Offer)query.getSingleResult();

			}catch(javax.persistence.NoResultException e) {
				return null;
			}
		}
		
		
		public List<Offer> getOffer(byte active) {
			List<Offer> list = null;
			Query query = em.createQuery("from Offer p where p.active=:active");
			query.setParameter("active", active);
			try {
				list = query.getResultList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return list;
		}
}
