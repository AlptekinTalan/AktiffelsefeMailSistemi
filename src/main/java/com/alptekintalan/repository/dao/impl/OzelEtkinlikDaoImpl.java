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

import com.alptekintalan.model.pojo.entity.OzelEtkinlik;
import com.alptekintalan.repository.dao.OzelEtkinlikDao;

@Repository
public class OzelEtkinlikDaoImpl implements OzelEtkinlikDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	public OzelEtkinlikDaoImpl() {
		System.out.println("OzelEtkinlikDaoImpl");
	}

	@Override
	public long createEtkinlik(OzelEtkinlik etkinlik) {
		return (long) sessionFactory.getCurrentSession().save(etkinlik);
	}

	@Override
	public void deleteEtkinlik(long etkinlikId) {
		OzelEtkinlik ozelEtkinlik = new OzelEtkinlik();
		ozelEtkinlik.setEtkinlikId(etkinlikId);
		sessionFactory.getCurrentSession().delete(ozelEtkinlik);
	}

	@Override
	public OzelEtkinlik updateEtkinlik(OzelEtkinlik etkinlik) {
		sessionFactory.getCurrentSession().update(etkinlik);
		return etkinlik;
	}

	@Override
	public List<OzelEtkinlik> findAllEtkinlik() {

		String hql = "from OzelEtkinlik p order by p.etkinlikTarihi desc";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<OzelEtkinlik> results = query.list();
		return results;
	}

	@Override
	public OzelEtkinlik findEtkinlik(long etkinlikId) {
		return sessionFactory.getCurrentSession().get(OzelEtkinlik.class, etkinlikId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OzelEtkinlik> findEtkinlikler(Object arananEtkinlikAdi, String arananTur, Date tarih1, Date tarih2) {

		String sorguNeyeGore = "etkinlikAdi";
		if (arananTur.equals("id")) {
			sorguNeyeGore = "etkinlikId";
		} else if (arananTur.equals("ad")) {
			sorguNeyeGore = "etkinlikAdi";
		} else if (arananTur.equals("soyad")) {
			sorguNeyeGore = "etkinlikSoyadi";

		}
		List<OzelEtkinlik> etkinlikler;

		if (sorguNeyeGore.equals("etkinlikId")) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OzelEtkinlik.class);
			etkinlikler = criteria.add(Restrictions.idEq(arananEtkinlikAdi)).list();
		} else {
			etkinlikler = sessionFactory.getCurrentSession().createCriteria(OzelEtkinlik.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
					.add(Restrictions.like(sorguNeyeGore, arananEtkinlikAdi + "%"))
					.add(Restrictions.ge("etkinlikTarihi", tarih1)).add(Restrictions.le("etkinlikTarihi", tarih2))
					.addOrder(Order.desc("etkinlikTarihi")).list();
		}

		return etkinlikler;

	}

	@Override
	public List<OzelEtkinlik> findEtkinliklerForSendMail() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();
		String now = dateFormat.format(date);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -7);
		Date sevenDaysBefore = cal.getTime();
		String sevenDaysAgo = dateFormat.format(sevenDaysBefore);

		System.out.println("Now :: " + now + " sevenDaysAgo: " + sevenDaysAgo);

		Date minDate = new Date();
		Date nowDate = new Date();

		try {

			minDate = new SimpleDateFormat("yyyy-MM-dd").parse(sevenDaysAgo);

			nowDate = new SimpleDateFormat("yyyy-MM-dd").parse(now);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<OzelEtkinlik> etkinlikler;

		// Þimdiki Tarihden Büyük Olacak ve etkinlik tarihinin bir hafta
		// öncesinden büyük olacak.
		etkinlikler = sessionFactory.getCurrentSession().createCriteria(OzelEtkinlik.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("etkinlikBildir", true))
				.add(Restrictions.ge("etkinlikTarihi", nowDate)).addOrder(Order.asc("etkinlikTarihi")).list();
		return etkinlikler;
	}

	@Override
	public List<OzelEtkinlik> findEtkinliklerBySubeId(long subeId) {
		List<OzelEtkinlik> etkinlikler;

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(OzelEtkinlik.class);

		etkinlikler = criteria.createAlias("sube", "s").add(Restrictions.like("s.subeId", subeId)).list();

		return etkinlikler;
	}
}
