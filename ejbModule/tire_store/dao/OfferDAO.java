package tire_store.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Offer;
import tire_store.entities.Order;
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
