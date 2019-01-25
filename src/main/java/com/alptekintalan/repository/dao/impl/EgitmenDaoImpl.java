package com.alptekintalan.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.model.pojo.entity.Egitmen;
import com.alptekintalan.repository.dao.EgitmenDao;

@Repository
public class EgitmenDaoImpl implements EgitmenDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	public EgitmenDaoImpl() {
		System.out.println("EgitmenDaoImpl");
	}

	@Override
	public long createEgitmen(Egitmen egitmen) {
		return (long) sessionFactory.getCurrentSession().save(egitmen);
	}

	@Override
	public void deleteEgitmen(long egitmenId) {
		Egitmen egitmen = new Egitmen();
		egitmen.setEgitmenId(egitmenId);
		sessionFactory.getCurrentSession().delete(egitmen);
	}

	@Override
	public Egitmen updateEgitmen(Egitmen egitmen) {
		sessionFactory.getCurrentSession().update(egitmen);
		return egitmen;
	}

	@Override
	public List<Egitmen> findAllEgitmen() {
		return sessionFactory.getCurrentSession().createQuery("FROM " + Egitmen.class.getName()).list();

	}

	@Override
	public Egitmen findEgitmen(long egitmenId) {
		return sessionFactory.getCurrentSession().get(Egitmen.class, egitmenId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Egitmen> findEgitmenlar(Object arananEgitmenDeger, String arananTur) {

		List<Egitmen> egitmenlar = null;

		String alias = "etkinlik";

		if (arananTur.equals("etkinlik")) {
			alias = "etkinlik";
		} else if (arananTur.equals("ozeletkinlik")) {
			alias = "ozeletkinlik";

		}

		egitmenlar = sessionFactory.getCurrentSession().createCriteria(Egitmen.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias(alias, "e")
				.add(Restrictions.like("e.etkinlikId", arananEgitmenDeger)).list();

		return egitmenlar;

	}

}
