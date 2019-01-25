package com.alptekintalan.repository.dao.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.repository.dao.EtkinlikDao;

@Repository
public class EtkinlikDaoImpl implements EtkinlikDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	public EtkinlikDaoImpl() {
		System.out.println("EtkinlikDaoImpl");
	}

	@Override
	public long createEtkinlik(Etkinlik etkinlik) {
		return (long) sessionFactory.getCurrentSession().save(etkinlik);
	}

	@Override
	public void deleteEtkinlik(long etkinlikId) {
		Etkinlik etkinlik = new Etkinlik();
		etkinlik.setEtkinlikId(etkinlikId);
		sessionFactory.getCurrentSession().delete(etkinlik);
	}

	@Override
	public Etkinlik updateEtkinlik(Etkinlik etkinlik) {
		sessionFactory.getCurrentSession().update(etkinlik);
		return etkinlik;
	}

	@Override
	public List<Etkinlik> findAllEtkinlik() {
		// return sessionFactory.getCurrentSession().createQuery("FROM " +
		// Etkinlik.class.getName()).list();

		// Criteria criteria =
		// sessionFactory.getCurrentSession().createCriteria(Etkinlik.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// criteria.addOrder(Order.desc("etkinlikTarihi"));
		//
		// return criteria.list();

		String hql = "from Etkinlik p order by p.etkinlikTarihi desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Etkinlik> results = query.list();
		return results;
	}

	@Override
	public Etkinlik findEtkinlik(long etkinlikId) {
		return sessionFactory.getCurrentSession().get(Etkinlik.class, etkinlikId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Etkinlik> findEtkinlikler(Object arananEtkinlikAdi, String arananTur, Date tarih1, Date tarih2) {

		String sorguNeyeGore = "etkinlikAdi";
		if (arananTur.equals("id")) {
			sorguNeyeGore = "etkinlikId";
		} else if (arananTur.equals("ad")) {
			sorguNeyeGore = "etkinlikAdi";
		} else if (arananTur.equals("soyad")) {
			sorguNeyeGore = "etkinlikSoyadi";

		}
		List<Etkinlik> etkinlikler;

		if (sorguNeyeGore.equals("etkinlikId")) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Etkinlik.class);
			etkinlikler = criteria.add(Restrictions.idEq(arananEtkinlikAdi)).list();
		} else {
			etkinlikler = sessionFactory.getCurrentSession().createCriteria(Etkinlik.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.like(sorguNeyeGore, arananEtkinlikAdi + "%"))
					.add(Restrictions.ge("etkinlikTarihi", tarih1)).add(Restrictions.le("etkinlikTarihi", tarih2))
					.addOrder(Order.desc("etkinlikTarihi")).list();
		}

		return etkinlikler;

	}

	@Override
	public List<Etkinlik> findEtkinliklerForSendMail() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();
		String now = dateFormat.format(date);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -21);
		Date sevenDaysBefore = cal.getTime();
		String sevenDaysAgo = dateFormat.format(sevenDaysBefore);

		//System.out.println("Etkinlik Service Þimdi :: " + now + " 21 Gün Önce: " + sevenDaysAgo);

		Date nowDate = new Date();

		try {

			nowDate = new SimpleDateFormat("yyyy-MM-dd").parse(now);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Etkinlik> etkinlikler;

		// Þimdiki Tarihden Büyük Olacak ve etkinlik tarihinin bir hafta
		// öncesinden büyük olacak.
		etkinlikler = sessionFactory.getCurrentSession().createCriteria(Etkinlik.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("etkinlikBildir", true))
				.add(Restrictions.ge("etkinlikTarihi", nowDate)).addOrder(Order.desc("etkinlikTarihi")).list();
		return etkinlikler;
	}

	@Override
	public List<Etkinlik> findEtkinliklerBySubeId(long subeId) {
		List<Etkinlik> etkinlikler;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Etkinlik.class);

		etkinlikler = criteria.createAlias("sube", "s").add(Restrictions.like("s.subeId", subeId)).addOrder(Order.desc("etkinlikTarihi")).list();

		return etkinlikler;
	}
}
