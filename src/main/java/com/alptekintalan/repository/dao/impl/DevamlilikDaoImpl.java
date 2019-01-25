package com.alptekintalan.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Devamlilik;
import com.alptekintalan.model.pojo.entity.Katilimci;
import com.alptekintalan.model.pojo.entity.OzelEtkinlik;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.model.pojo.entity.Yoklama;
import com.alptekintalan.repository.dao.DevamlilikDao;

@Repository
public class DevamlilikDaoImpl implements DevamlilikDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	public DevamlilikDaoImpl() {
	}

	@Override
	public long createDevamlilik(Devamlilik devamlilik) {
		return (long) sessionFactory.getCurrentSession().save(devamlilik);

	}

	@Override
	public void deleteDevamlilik(long devamlilikId) {
		Devamlilik devamlilik = new Devamlilik();
		devamlilik.setDevamlilikId(devamlilikId);
		sessionFactory.getCurrentSession().delete(devamlilik);

	}

	@Override
	public Devamlilik updateDevamlilik(Devamlilik devamlilik) {
		sessionFactory.getCurrentSession().update(devamlilik);
		return devamlilik;
	}

	@Override
	public List<Devamlilik> findAllDevamlilik() {
		return sessionFactory.getCurrentSession().createQuery("FROM " + Devamlilik.class.getName()).list();

	}

	@Override
	public Devamlilik findDevamlilik(long devamlilikId) {
		return sessionFactory.getCurrentSession().get(Devamlilik.class, devamlilikId);

	}

	@Override
	public List<Devamlilik> findDevamlilik(Yoklama yoklamaId) {
		List<Devamlilik> devamliliklar = null;

		devamliliklar = sessionFactory.getCurrentSession().createCriteria(Devamlilik.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("yoklama", "y")
				.add(Restrictions.like("y.yoklamaId", yoklamaId.getYoklamaId())).list();

		return devamliliklar;
	}

	@Override
	public List<Devamlilik> findDevamlilik(OzelEtkinlik ozelEtkinlikId) {
		List<Devamlilik> devamliliklar = null;

		devamliliklar = sessionFactory.getCurrentSession().createCriteria(Devamlilik.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("ozeletkinlik", "e")
				.add(Restrictions.like("e.etkinlikId", ozelEtkinlikId.getEtkinlikId()))
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("yoklama", "y")
				.addOrder(Order.asc("y.yoklamaTarihi")).list();

		return devamliliklar;
	}

	@Override
	public List<Devamlilik> findDevamlilik(Yoklama yoklama, Uye uye) {
		List<Devamlilik> devamliliklar = null;

		devamliliklar = sessionFactory.getCurrentSession().createCriteria(Devamlilik.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("yoklama", "y")
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("uye", "u")
				.add(Restrictions.like("y.yoklamaId", yoklama.getYoklamaId()))
				.add(Restrictions.like("u.uyeId", uye.getUyeId())).list();

		return devamliliklar;
	}

}
