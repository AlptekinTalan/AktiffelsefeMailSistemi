package com.alptekintalan.repository.dao;

import java.util.List;

import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.model.pojo.entity.Mail;

public interface MailDao {

	public long createMail(Mail mail);

	public void deleteMail(long mailId);

	public void deleteMailsByEtkinlikId(long etkinlikId);

	public void deleteMailsByAboneId(long aboneId);

	public Mail updateMail(Mail mail);

	public List<Mail> findAllMail();

	public Mail findMail(long mailId);

	public List<Mail> findMailByEtkinlik(long etkinlikId);

	public List<Mail> findMailByEtkinlikandSubeId(long etkinlikId, long subeId);

	public List<Mail> findMailsForSendMail(long etkinlikId, int mailSayisi);

	public List<Mail> findOldMails();

	public Mail findMailKeyByAboneId(long etkinlikId, long aboneId);

}
