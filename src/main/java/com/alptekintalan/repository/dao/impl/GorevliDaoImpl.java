package com.alptekintalan.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.model.pojo.entity.Gorevli;
import com.alptekintalan.repository.dao.GorevliDao;

@Repository
public class GorevliDaoImpl implements GorevliDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	public GorevliDaoImpl() {
		System.out.println("GorevliDaoImpl");
	}

	@Override
	public long createGorevli(Gorevli gorevli) {
		return (long) sessionFactory.getCurrentSession().save(gorevli);
	}

	@Override
	public void deleteGorevli(long gorevliId) {
		Gorevli gorevli = new Gorevli();
		gorevli.setGorevliId(gorevliId);
		sessionFactory.getCurrentSession().delete(gorevli);
	}

	@Override
	public Gorevli updateGorevli(Gorevli gorevli) {
		sessionFactory.getCurrentSession().update(gorevli);
		return gorevli;
	}

	@Override
	public List<Gorevli> findAllGorevli() {
		return sessionFactory.getCurrentSession().createQuery("FROM " + Gorevli.class.getName()).list();

	}

	@Override
	public Gorevli findGorevli(long gorevliId) {
		return sessionFactory.getCurrentSession().get(Gorevli.class, gorevliId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Gorevli> findGorevlilar(Object arananGorevliDeger, String arananTur) {

		List<Gorevli> gorevlilar = null;

		String alias = "etkinlik";

		if (arananTur.equals("etkinlik")) {
			alias = "etkinlik";
		} else if (arananTur.equals("ozeletkinlik")) {
			alias = "ozeletkinlik";
		}

		gorevlilar = sessionFactory.getCurrentSession().createCriteria(Gorevli.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias(alias, "e")
				.add(Restrictions.like("e.etkinlikId", arananGorevliDeger)).list();

		return gorevlilar;

	}

}
