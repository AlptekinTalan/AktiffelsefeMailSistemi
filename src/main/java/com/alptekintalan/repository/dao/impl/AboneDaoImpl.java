package com.alptekintalan.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.model.pojo.entity.Abone;
import com.alptekintalan.repository.dao.AboneDao;

@Repository
public class AboneDaoImpl implements AboneDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	// ---------------------------------------------
	public AboneDaoImpl() {
		System.out.println("AboneDaoImpl");
	}

	// ---------------------------------------------
	@Override
	public long createAbone(Abone abone) {
		return (long) sessionFactory.getCurrentSession().save(abone);
	}

	@Override
	public Abone updateAbone(Abone abone) {
		sessionFactory.getCurrentSession().update(abone);
		return abone;
	}

	@Override
	public void deleteAbone(long aboneId) {
		Abone abone = new Abone();
		abone.setAboneId(aboneId);
		sessionFactory.getCurrentSession().delete(abone);
		// sessionFactory.getCurrentSession().createQuery("DELETE FROM Abone
		// WHERE
		// aboneId = "+aboneId).executeUpdate();
	}

	@Override
	public List<Abone> findAllAbone() {
		return sessionFactory.getCurrentSession().createQuery("FROM " + Abone.class.getName()).list();

		/*
		 * Session session = this.sessionFactory.getCurrentSession();
		 * List<Abone> muusteriListe = session.createQuery("FROM Abone").list();
		 * for(Abone m : aboneListe){ // logger.info("Abone List:"+m); } return
		 * aboneListe;
		 */

	}

	@Override
	public Abone findAbone(long aboneId) {
		return sessionFactory.getCurrentSession().get(Abone.class, aboneId);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Abone> findAboneler(Object arananAboneAdi, String arananTur, Long subeId) {

		String sorguNeyeGore = "aboneEposta";
		if (arananTur.equals("ad")) {
			sorguNeyeGore = "aboneAdi";
		} else if (arananTur.equals("soyad")) {
			sorguNeyeGore = "aboneSoyadi";
		} else if (arananTur.equals("eposta")) {
			sorguNeyeGore = "aboneEposta";
		}

		List<Abone> aboneler;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Abone.class);
		if (sorguNeyeGore.equals("aboneEposta")) {
			if (subeId == null) {

				aboneler = criteria.add(Restrictions.like(sorguNeyeGore, arananAboneAdi)).list();
			} else if (arananAboneAdi.equals("")) {
				aboneler = criteria.createAlias("sube", "s").add(Restrictions.like("s.subeId", subeId)).list();
			} else {
				aboneler = criteria.add(Restrictions.like(sorguNeyeGore, arananAboneAdi)).createAlias("sube", "s")
						.add(Restrictions.like("s.subeId", subeId)).list();
			}
		} else {
			if (subeId == null) {
				aboneler = criteria.add(Restrictions.like(sorguNeyeGore, arananAboneAdi + "%")).list();
			} else {
				aboneler = criteria.add(Restrictions.like(sorguNeyeGore, arananAboneAdi + "%")).createAlias("sube", "s")
						.add(Restrictions.like("s.subeId", subeId)).list();
			}

		}

		return aboneler;

	}

	@Override
	public List<Abone> findAbonelerBySubeId(Sube subeId) {

		List<Abone> aboneler;

		aboneler = sessionFactory.getCurrentSession().createCriteria(Abone.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("sube", "s")
				.add(Restrictions.like("s.subeId", subeId.getSubeId())).add(Restrictions.eq("aktif", true))
				.add(Restrictions.eq("hatali", false)).list();

		//System.out.println("BULUNAN ABONELER :: " + aboneler.size());

		return aboneler;
	}

	private static final int limitResultsPerPage = 50;

	@Override
	public List<Abone> findAbonelerPageByPage(int page) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Abone");
		q.setFirstResult(page * limitResultsPerPage);
		q.setMaxResults(limitResultsPerPage);
		return (List<Abone>) q.list();
	}
}