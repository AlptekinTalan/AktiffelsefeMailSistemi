package com.alptekintalan.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Gorevli;
import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.repository.dao.UyeDao;

@Repository
public class UyeDaoImpl implements UyeDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	// ---------------------------------------------
	public UyeDaoImpl() {
		System.out.println("UyeDaoImpl");
	}

	// ---------------------------------------------
	@Override
	public long createUye(Uye uye) {
		return (long) sessionFactory.getCurrentSession().save(uye);
	}

	@Override
	public Uye updateUye(Uye uye) {
		sessionFactory.getCurrentSession().update(uye);
		return uye;
	}

	@Override
	public void deleteUye(long uyeId) {
		Uye uye = new Uye();
		uye.setUyeId(uyeId);
		sessionFactory.getCurrentSession().delete(uye);
		// sessionFactory.getCurrentSession().createQuery("DELETE FROM Uye WHERE
		// uyeId = "+uyeId).executeUpdate();
	}

	@Override
	public List<Uye> findAllUye() {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM " + Uye.class.getName() + " d ORDER BY d.uyeAdi ASC").list();

		/*
		 * Session session = this.sessionFactory.getCurrentSession(); List<Uye>
		 * muusteriListe = session.createQuery("FROM Uye").list(); for(Uye m :
		 * uyeListe){ // logger.info("Uye List:"+m); } return uyeListe;
		 */

	}

	@Override
	public Uye findUye(long uyeId) {
		return sessionFactory.getCurrentSession().get(Uye.class, uyeId);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Uye> findUyeler(Object arananUyeAdi, String arananTur, String statu) {

		String sorguNeyeGore = "uyeAdi";
		if (arananTur.equals("id")) {
			sorguNeyeGore = "uyeId";
		} else if (arananTur.equals("ad")) {
			sorguNeyeGore = "uyeAdi";
		} else if (arananTur.equals("soyad")) {
			sorguNeyeGore = "uyeSoyadi";
		} else if (arananTur.equals("eposta")) {
			sorguNeyeGore = "ePosta";
		}

		List<Uye> uyeler;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uye.class);
		if (sorguNeyeGore.equals("uyeId")) {
			uyeler = criteria.add(Restrictions.idEq(arananUyeAdi)).list();
		} else if (sorguNeyeGore.equals("ePosta")) {
			uyeler = criteria.add(Restrictions.like(sorguNeyeGore, arananUyeAdi)).list();
		} else {
			if (statu.equals("")) {
				uyeler = criteria.add(Restrictions.like(sorguNeyeGore, arananUyeAdi + "%")).list();
			} else {
				uyeler = criteria.add(Restrictions.like(sorguNeyeGore, arananUyeAdi + "%"))
						.add(Restrictions.like("uyeStatu", statu)).list();
			}
		}

		return uyeler;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Uye> findIcUyeler() {

		List<Uye> uyeler;

		// Criteria criteria =
		// sessionFactory.getCurrentSession().createCriteria(Uye.class);

		// uyeler = criteria.add(Restrictions.not(Restrictions.like("uyeStatu",
		// "Ziyaretçi"))).list();

		Criteria criteria2 = sessionFactory.getCurrentSession().createCriteria(Uye.class);
		Criterion rest1 = Restrictions.not(Restrictions.like("uyeStatu", "Ziyaretçi"));
		Criterion rest2 = Restrictions.isNull("uyeStatu");
		uyeler = criteria2.add(Restrictions.or(rest1, rest2)).list();

		return uyeler;

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Uye> findUyelerByNameAndLastName(String uyeAdi, String uyeSoyadi) {

		List<Uye> uyeler;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Uye.class);

		uyeler = criteria.add(Restrictions.like("uyeAdi", uyeAdi)).add(Restrictions.like("uyeSoyadi", uyeSoyadi))
				.list();

		return uyeler;

	}

	@Override
	public List<Uye> findUyelerBySubeId(Sube subeId) {

		List<Uye> uyeler;

		uyeler = sessionFactory.getCurrentSession().createCriteria(Uye.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("sube", "s")
				.add(Restrictions.like("s.subeId", subeId.getSubeId())).list();

		return uyeler;
	}
}