package com.alptekintalan.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.repository.dao.SubeDao;

@Repository
public class SubeDaoImpl implements SubeDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	public SubeDaoImpl() {
		System.out.println("SubeDaoImpl");
	}

	@Override
	public long createSube(Sube sube) {
		return (long) sessionFactory.getCurrentSession().save(sube);
	}

	@Override
	public void deleteSube(long subeId) {
		Sube sube = new Sube();
		sube.setSubeId(subeId);
		sessionFactory.getCurrentSession().delete(sube);
	}

	@Override
	public Sube updateSube(Sube sube) {
		sessionFactory.getCurrentSession().update(sube);
		return sube;
	}

	@Override
	public List<Sube> findAllSube() {
		return sessionFactory.getCurrentSession().createQuery("FROM " + Sube.class.getName()).list();

	}

	@Override
	public Sube findSube(long subeId) {
		return sessionFactory.getCurrentSession().get(Sube.class, subeId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Sube> findSubeler(Object arananSubeAdi) {

		String sorguNeyeGore = "subeAdi";

		List<Sube> subeler;

		subeler = sessionFactory.getCurrentSession().createCriteria(Sube.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.add(Restrictions.like(sorguNeyeGore, arananSubeAdi + "%")).addOrder(Order.asc("subeAdi")).list();

		return subeler;

	}

	@SuppressWarnings("unchecked")
	@Override
	public Sube findSubeByName(Object arananSubeAdi) {

		String sorguNeyeGore = "subeAdi";

		List<Sube> subeler;

		subeler = sessionFactory.getCurrentSession().createCriteria(Sube.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
				.add(Restrictions.like(sorguNeyeGore, arananSubeAdi + "%")).addOrder(Order.asc("subeAdi")).list();

		return subeler.get(0);

	}
}
