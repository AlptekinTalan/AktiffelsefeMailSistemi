package com.alptekintalan.repository.dao.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.alptekintalan.model.pojo.entity.Egitmen;
import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.model.pojo.entity.Gorevli;
import com.alptekintalan.model.pojo.entity.Mail;
import com.alptekintalan.repository.dao.MailDao;
import com.alptekintalan.service.MailService;

@Repository
public class MailDaoImpl implements MailDao {

	@Inject // @Autowired
	private SessionFactory sessionFactory;

	public MailDaoImpl() {
		System.out.println("MailDaoImpl");
	}

	@Override
	public long createMail(Mail mail) {
		return (long) sessionFactory.getCurrentSession().save(mail);
	}

	@Override
	public void deleteMail(long mailId) {
		Mail mail = new Mail();
		mail.setMailId(mailId);
		sessionFactory.getCurrentSession().delete(mail);
	}

	@Override
	public void deleteMailsByEtkinlikId(long etkinlikId) {

		List<Mail> mailler;

		mailler = sessionFactory.getCurrentSession().createCriteria(Mail.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("etkinlik", "e")
				.add(Restrictions.like("e.etkinlikId", etkinlikId)).list();

		for (int i = 0; i < mailler.size(); i++) {
			System.out.println("mailler: " + mailler.get(i).getMailId());

			sessionFactory.getCurrentSession().delete(mailler.get(i));

		}
	}

	@Override
	public void deleteMailsByAboneId(long aboneId) {
		List<Mail> mailler;

		mailler = sessionFactory.getCurrentSession().createCriteria(Mail.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("abone", "a")
				.add(Restrictions.like("a.aboneId", aboneId)).list();

		for (int i = 0; i < mailler.size(); i++) {
			System.out.println("mailler: " + mailler.get(i).getMailId());

			sessionFactory.getCurrentSession().delete(mailler.get(i));

		}
	}

	@Override
	public Mail updateMail(Mail mail) {
		sessionFactory.getCurrentSession().update(mail);
		return mail;
	}

	@Override
	public List<Mail> findAllMail() {

		return sessionFactory.getCurrentSession().createQuery("FROM " + Mail.class.getName()).list();

	}

	@Override
	public Mail findMail(long mailId) {
		return sessionFactory.getCurrentSession().get(Mail.class, mailId);
	}

	@Override
	public List<Mail> findMailByEtkinlik(long etkinlikId) {
		List<Mail> mailler;

		mailler = sessionFactory.getCurrentSession().createCriteria(Mail.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("etkinlik", "e")
				.add(Restrictions.like("e.etkinlikId", etkinlikId)).list();

		return mailler;
	}

	@Override
	public List<Mail> findMailByEtkinlikandSubeId(long etkinlikId, long subeId) {
		List<Mail> mailler;

		mailler = sessionFactory.getCurrentSession().createCriteria(Mail.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("etkinlik", "e")
				.createAlias("sube", "s").add(Restrictions.like("e.etkinlikId", etkinlikId))
				.add(Restrictions.like("s.subeId", subeId)).list();

		return mailler;
	}

	@Override
	public List<Mail> findMailsForSendMail(long etkinlikId, int mailSayisi) {
		List<Mail> mailler;

		mailler = sessionFactory.getCurrentSession().createCriteria(Mail.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).add(Restrictions.eq("mailGonderildi", false))
				.add(Restrictions.eq("mailGonderilemedi", false)).setMaxResults(mailSayisi).createAlias("etkinlik", "e")
				.add(Restrictions.like("e.etkinlikId", etkinlikId)).list();

		return mailler;
	}

	public static Date gunegit(Date date1, int gunsay) {
		return new Date(((long) date1.getTime() + gunsay * (1000 * 60 * 60 * 24)));
	}

	@Override
	public List<Mail> findOldMails() {

		////
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		Date date = new Date();
		String now = dateFormat.format(date);

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		Date sevenDaysBefore = cal.getTime();
		String sevenDaysAgo = dateFormat.format(sevenDaysBefore);

		// System.out.println("Mail Service Simdi :: " + now + " 1 Ay Once: " +
		// sevenDaysAgo);
		////

		List<Mail> mailler;

		mailler = sessionFactory.getCurrentSession().createCriteria(Mail.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).setMaxResults(100).createAlias("etkinlik", "e")
				.add(Restrictions.le("e.etkinlikTarihi", sevenDaysBefore)).list();

		return mailler;
	}

	@Override
	public Mail findMailKeyByAboneId(long etkinlikId, long aboneId) {
		List<Mail> mailler;

		mailler = sessionFactory.getCurrentSession().createCriteria(Mail.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).createAlias("etkinlik", "e")
				.createAlias("abone", "a").add(Restrictions.like("e.etkinlikId", etkinlikId))
				.add(Restrictions.like("a.aboneId", aboneId)).list();

		if (mailler.size() > 0) {
			return mailler.get(0);

		} else {
			return null;

		}
	}
}
