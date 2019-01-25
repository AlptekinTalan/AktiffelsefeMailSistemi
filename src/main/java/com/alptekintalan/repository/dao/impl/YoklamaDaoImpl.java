package com.alptekintalan.repository.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Yoklama;
import com.alptekintalan.repository.dao.YoklamaDao;

@Repository
public class YoklamaDaoImpl implements YoklamaDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	public YoklamaDaoImpl() {
	}

	@Override
	public long createYoklama(Yoklama yoklama) {
		return (long) sessionFactory.getCurrentSession().save(yoklama);
	}

	@Override
	public void deleteYoklama(long yoklamaId) {
		Yoklama yoklama = new Yoklama();
		yoklama.setYoklamaId(yoklamaId);
		sessionFactory.getCurrentSession().delete(yoklama);
	}

	@Override
	public Yoklama updateYoklama(Yoklama yoklama) {
		sessionFactory.getCurrentSession().update(yoklama);
		return yoklama;
	}

	@Override
	public List<Yoklama> findAllYoklama() {
		return sessionFactory.getCurrentSession().createQuery("FROM " + Yoklama.class.getName()).list();

	}

	@Override
	public Yoklama findYoklama(long yoklamaId) {
		return sessionFactory.getCurrentSession().get(Yoklama.class, yoklamaId);

	}

	@Override
	public List<Yoklama> findYoklamalar(long ozelEtkinlikId) {
		List<Yoklama> yoklamalar;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Yoklama.class);

		yoklamalar = criteria.createAlias("ozeletkinlik", "o").add(Restrictions.like("o.etkinlikId", ozelEtkinlikId))
				.addOrder(Order.asc("yoklamaTarihi")).list();

		return yoklamalar;
	}

}
