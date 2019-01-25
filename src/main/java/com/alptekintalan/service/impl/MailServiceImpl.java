package com.alptekintalan.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.model.pojo.entity.Mail;
import com.alptekintalan.repository.dao.MailDao;
import com.alptekintalan.service.MailService;

@Service
@Transactional
public class MailServiceImpl implements MailService {

	@Inject // @Autowired
	private MailDao mailDao;

	public MailServiceImpl() {
		System.out.println("MailServiceImpl");
	}

	@Override
	public long createMail(Mail mail) {
		return mailDao.createMail(mail);
	}

	@Override
	public void deleteMail(long mailId) {
		mailDao.deleteMail(mailId);
	}

	@Override
	public void deleteMailsByEtkinlikId(long etkinlikId) {
		mailDao.deleteMailsByEtkinlikId(etkinlikId);
	}

	@Override
	public void deleteMailsByAboneId(long aboneId) {
		mailDao.deleteMailsByAboneId(aboneId);
	}

	@Override
	public Mail updateMail(Mail mail) {
		return mailDao.updateMail(mail);
	}

	@Override
	public List<Mail> findAllMail() {
		return mailDao.findAllMail();
	}

	@Override
	public Mail findMail(long mailId) {
		return mailDao.findMail(mailId);
	}

	@Override
	public List<Mail> findMailByEtkinlik(long etkinlikId) {
		return mailDao.findMailByEtkinlik(etkinlikId);
	}

	@Override
	public List<Mail> findMailByEtkinlikandSubeId(long etkinlikId, long subeId) {
		return mailDao.findMailByEtkinlikandSubeId(etkinlikId, subeId);
	}

	@Override
	public List<Mail> findMailsForSendMail(long etkinlikId, int mailSayisi) {
		return mailDao.findMailsForSendMail(etkinlikId, mailSayisi);
	}

	@Override
	public List<Mail> findOldMails() {
		return mailDao.findOldMails();
	}

	@Override
	public Mail findMailKeyByAboneId(long etkinlikId, long aboneId) {
		return mailDao.findMailKeyByAboneId(etkinlikId, aboneId);
	}
}
