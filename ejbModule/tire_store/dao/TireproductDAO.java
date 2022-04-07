package tire_store.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tire_store.entities.Orderstatus;
import tire_store.entities.Storeorder;
import tire_store.entities.Tireproduct;
import tire_store.entities.Tiretype;

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

	public List<Tireproduct> getProducts(int offset, int pageSize) {
		List<Tireproduct> list = null;

		Query query = em.createQuery("from Tireproduct s where s.archived=:archived").setFirstResult(offset)
				.setMaxResults(pageSize);
		query.setParameter("archived", (byte) 0);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Tireproduct> getProducts(String search, int offset, int pageSize) {
		List<Tireproduct> list = null;
		Query query;

		int idorder = 0;
		try {
			idorder = Integer.parseInt(search);
			query = em.createQuery("from Tireproduct t " + "where t.archived=:archived and t.idtire=:search")
					.setFirstResult(offset).setMaxResults(pageSize);
			query.setParameter("archived", (byte) 0);
			query.setParameter("search", idorder);
			list = query.getResultList();
			return list;
		} catch (NumberFormatException e) {
			query = em
					.createQuery("from Tireproduct t "
							+ "where t.archived=:archived and (t.brand=:search or t.model=:search or t.size=:search)")
					.setFirstResult(offset).setMaxResults(pageSize);
			query.setParameter("search", search);
			query.setParameter("archived", (byte) 0);
			list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Tireproduct> getProductsArchived(int offset, int pageSize) {
		List<Tireproduct> list = null;

		Query query = em.createQuery("from Tireproduct t where t.archived=:archived").setFirstResult(offset)
				.setMaxResults(pageSize);
		query.setParameter("archived", (byte) 1);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Tireproduct> getProductsArchived(String search, int offset, int pageSize) {
		List<Tireproduct> list = null;
		Query query;

		int idorder = 0;
		try {
			idorder = Integer.parseInt(search);
			query = em.createQuery("from Tireproduct t " + "where t.archived=:archived and t.idtire=:search ")
					.setFirstResult(offset).setMaxResults(pageSize);
			query.setParameter("search", idorder);
			query.setParameter("archived", (byte) 1);
			list = query.getResultList();
			return list;
		} catch (NumberFormatException e) {
			query = em
					.createQuery("from Tireproduct t "
							+ "where t.archived=:archived and (t.brand=:search or t.model=:search or t.size=:search)")
					.setFirstResult(offset).setMaxResults(pageSize);
			query.setParameter("search", search);
			query.setParameter("archived", (byte) 1);
			list = query.getResultList();
			return list;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Tireproduct> getActiveProducts(String tiretype) {
		List<Tireproduct> list = null;

		Query query = em.createQuery(
				"from Tireproduct s where s.archived=:archived and s.active=:active and s.tiretype.name=:tiretype");
		query.setParameter("archived", (byte) 0);
		query.setParameter("active", (byte) 1);
		query.setParameter("tiretype", tiretype);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public List<Tireproduct> getActiveProducts(Tiretype tiretype, int offset, int pageSize) {
		List<Tireproduct> list = null;

		Query query = em.createQuery("from Tireproduct s where s.archived=:archived and s.active=:active and tiretype=:tiretype").setFirstResult(offset).setMaxResults(pageSize);
		query.setParameter("archived", (byte) 0);
		query.setParameter("active", (byte) 1);
		query.setParameter("tiretype", tiretype);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	public List<Tireproduct> getActiveProducts(Tiretype tiretype, int offset, int pageSize, String search) {
		List<Tireproduct> list = null;

		Query query = em.createQuery("from Tireproduct s where s.archived=:archived and s.active=:active and tiretype=:tiretype and (s.brand=:search or s.model=:search or s.size=:search)").setFirstResult(offset).setMaxResults(pageSize);
		query.setParameter("archived", (byte) 0);
		query.setParameter("active", (byte) 1);
		query.setParameter("tiretype", tiretype);
		query.setParameter("search", search);

		try {
			list = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	public int countProducts() {
		try {
			Query query = em.createNativeQuery(
					"SELECT COUNT(*)" + "FROM storedb.tireproduct t " + "WHERE t.archived=:archived");
			query.setParameter("archived", (byte) 0);
			int a = ((Number) query.getSingleResult()).intValue();
			return a;
		} catch (javax.persistence.NoResultException e) {
			return 0;
		}
	}

	public int countProducts(String search) {
		if (search == null)
			return 0;
		try {
			int idorder = Integer.parseInt(search);
			Query query = em.createNativeQuery("SELECT COUNT(*) " + "FROM storedb.tireproduct t "
					+ "WHERE t.archived=:archived and t.idtire=:search");
			query.setParameter("archived", (byte) 0);
			query.setParameter("search", idorder);
			return ((Number) query.getSingleResult()).intValue();
		} catch (javax.persistence.NoResultException e) {
			return 0;
		} catch (NumberFormatException e) {
			Query query = em.createNativeQuery("SELECT COUNT(*) " + "FROM storedb.tireproduct t "
					+ "where t.archived=:archived and (t.brand=:search or t.model=:search or t.size=:search)");
			query.setParameter("archived", (byte) 0);
			query.setParameter("search", search);
			return ((Number) query.getSingleResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}

	public int countProductsArchived() {
		try {
			Query query = em.createNativeQuery(
					"SELECT COUNT(*)" + "FROM storedb.tireproduct t " + "WHERE t.archived=:archived");
			query.setParameter("archived", (byte) 1);
			int a = ((Number) query.getSingleResult()).intValue();
			return a;
		} catch (javax.persistence.NoResultException e) {
			return 0;
		}
	}

	public int countProductsArchived(String search) {
		if (search == null)
			return 0;
		try {
			int idorder = Integer.parseInt(search);
			Query query = em.createNativeQuery("SELECT COUNT(*) " + "FROM storedb.tireproduct t "
					+ "WHERE t.archived=:archived and t.idtire=:search");
			query.setParameter("search", idorder);
			query.setParameter("archived", (byte) 1);
			return ((Number) query.getSingleResult()).intValue();
		} catch (javax.persistence.NoResultException e) {
			return 0;
		} catch (NumberFormatException e) {
			Query query = em.createNativeQuery("SELECT COUNT(*) " + "FROM storedb.tireproduct t "
					+ "where t.archived=:archived and (t.brand=:search or t.model=:search or t.size=:search)");
			query.setParameter("search", search);
			query.setParameter("archived", (byte) 1);
			return ((Number) query.getSingleResult()).intValue();
		} catch (Exception e) {
			return 0;
		}
	}
	public int countActiveProducts(String tiretype) {
		try {
			Query query = em.createNativeQuery(
					"SELECT COUNT(*)" + "FROM storedb.tireproduct t "
			+"INNER JOIN storedb.tiretype s " + "ON tiretype_idtiretype = idtiretype "
			+"WHERE t.archived=:archived and t.active=:active and s.name=:tiretype");
			query.setParameter("archived", (byte) 0);
			query.setParameter("active", (byte) 1);
			query.setParameter("tiretype", tiretype);
			int a = ((Number) query.getSingleResult()).intValue();
			return a;
		} catch (javax.persistence.NoResultException e) {
			return 0;
		}
	}
	public int countActiveProducts(String tiretype, String search) {
		try {
			Query query = em.createNativeQuery(
					"SELECT COUNT(*)" + "FROM storedb.tireproduct t "
			+"INNER JOIN storedb.tiretype s " + "ON tiretype_idtiretype = idtiretype "
			+"WHERE t.archived=:archived and t.active=:active and s.name=:tiretype and (brand=:search or model=:search or size=:search)");
			query.setParameter("archived", (byte) 0);
			query.setParameter("active", (byte) 1);
			query.setParameter("tiretype", tiretype);
			query.setParameter("search", search);
			int a = ((Number) query.getSingleResult()).intValue();
			return a;
		} catch (javax.persistence.NoResultException e) {
			return 0;
		}
	}

}
