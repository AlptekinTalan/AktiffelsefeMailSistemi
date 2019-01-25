package com.alptekintalan.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.model.pojo.entity.Katilimci;
import com.alptekintalan.repository.dao.KatilimciDao;

@Repository
public class KatilimciDaoImpl implements KatilimciDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	public KatilimciDaoImpl() {
		System.out.println("KatilimciDaoImpl");
	}

	@Override
	public long createKatilimci(Katilimci katilimci) {
		return (long) sessionFactory.getCurrentSession().save(katilimci);
	}

	@Override
	public void deleteKatilimci(long katilimciId) {
		Katilimci katilimci = new Katilimci();
		katilimci.setKatilimciId(katilimciId);
		sessionFactory.getCurrentSession().delete(katilimci);
	}

	@Override
	public Katilimci updateKatilimci(Katilimci katilimci) {
		sessionFactory.getCurrentSession().update(katilimci);
		return katilimci;
	}

	@Override
	public List<Katilimci> findAllKatilimci() {
		return sessionFactory.getCurrentSession().createQuery("FROM " + Katilimci.class.getName()).list();

	}

	@Override
	public Katilimci findKatilimci(long katilimciId) {
		return sessionFactory.getCurrentSession().get(Katilimci.class, katilimciId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Katilimci> findKatilimcilar(Object arananKatilimciDeger, String arananTur) {

		List<Katilimci> katilimcilar = null;

		String alias = "etkinlik";

		if (arananTur.equals("etkinlik")) {
			alias = "etkinlik";
		} else if (arananTur.equals("ozeletkinlik")) {
			alias = "ozeletkinlik";
		}

		katilimcilar = sessionFactory.getCurrentSession().createCriteria(Katilimci.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias(alias, "e")
				.add(Restrictions.like("e.etkinlikId", arananKatilimciDeger)).list();

		return katilimcilar;

	}

}
