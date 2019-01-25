package com.alptekintalan.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import org.jboss.logging.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alptekintalan.model.pojo.entity.Abone;
import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.model.pojo.entity.Mail;
import com.alptekintalan.service.AboneService;
import com.alptekintalan.service.EtkinlikService;
import com.alptekintalan.service.MailService;
import com.alptekintalan.service.SubeService;

@Controller
public class AppController {

	@Inject
	private JavaMailSender mailSender;
	@Inject
	private JavaMailSender mailSenderBakirkoy;
	@Inject
	private JavaMailSender mailSenderAdana;
	@Inject
	private JavaMailSender mailSenderAnkara;
	@Inject
	private JavaMailSender mailSenderBursa;
	@Inject
	private JavaMailSender mailSenderAlsancak;
	@Inject
	private JavaMailSender mailSenderGuzelyali;
	@Inject
	private JavaMailSender mailSenderKarsiyaka;
	@Inject
	private JavaMailSender mailSenderBornova;
	@Inject
	private JavaMailSender mailSenderMersin;
	@Inject
	private JavaMailSender mailSenderUskudar;
	@Inject
	private JavaMailSender mailSenderKadikoy;
	@Inject
	private JavaMailSender mailSenderIzmit;
	@Inject
	private JavaMailSender mailSenderAntalya;
	@Inject
	private JavaMailSender mailSenderEskisehir;
	@Inject
	private JavaMailSender mailSenderSisli;

	@Inject // @Autowired
	private EtkinlikService etkinlikService;
	@Inject // @Autowired
	private MailService mailService;
	@Inject // @Autowired
	private AboneService aboneService;
	@Inject // @Autowired
	private SubeService subeService;

	private static final Logger logger = Logger.getLogger(UyeController.class);

	public AppController() {
	}

	@RequestMapping("sablonEtkinlik")
	public void hakkimizda() {
	}

	@RequestMapping("deneme2")
	public void deneme2() {
		System.out.println("111");
	}

	@RequestMapping("deneme3")
	public void deneme3() {
	}

	@RequestMapping("deneme4")
	public void deneme4() {
	}

	@RequestMapping("deneme5")
	public void deneme5() {
		Etkinlik e1 = new Etkinlik();
		e1 = etkinlikService.findEtkinlik(54);

		System.out.println("555 " + e1.getEtkinlikMailIcerik().toString());
	}

	static int mailGondermeDeger = 0;

	// @Scheduled(cron = "0 0/1 * * * *")
	@Scheduled(fixedDelay = 120000)
	public void mail() {

		if (true) {
			mailGondermeDeger++;
			int min = 1;
			int max = 10;
			Random r = new Random();
			int rasgeleDeger = r.nextInt((max - min) + 1) + min;
			// System.out.println("Dakikada Bir :: " + new Date() +
			// "mailGondermeDeger: " + mailGondermeDeger
			// + " rasgeleDeger: " + rasgeleDeger);
			if (rasgeleDeger == 1 || mailGondermeDeger >= 8) {

				// Bildirilecek etkinlikleri Bul e�er bu etkinlik tarihler
				// �imdiki
				// tarihden 1 hafta kadar �nce durumundaysa
				// ve etkinlik bildirilecek i�areti varsa bildirilecek etkinlik
				// olarak
				// arraya al. MAX RESULTS dao imp i�erisinde ayarland�
				mailGondermeDeger = 0;
				min = 8;
				max = 12;
				int kacKisiyeGonderilecek = r.nextInt((max - min) + 1) + min;

				DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

				Date date = new Date();
				String now = dateFormat2.format(date);

				Date nowDate = new Date();

				try {
					nowDate = new SimpleDateFormat("yyyy-MM-dd").parse(now);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				List<Etkinlik> etkinliklerOrj = etkinlikService.findEtkinliklerForSendMail();
				List<Etkinlik> etkinlikler = new ArrayList<Etkinlik>();

				for (int i = 0; i < etkinliklerOrj.size(); i++) {
					{
						// System.out.println("Mail Gonderilecek Etkinlikler ::
						// " + etkinliklerOrj.get(i).getEtkinlikAdi()
						// + " etkinlikTar :: " +
						// etkinliklerOrj.get(i).getEtkinlikTarihi());

						Date etkinlikTar = etkinliklerOrj.get(i).getEtkinlikTarihi();

						///////////////////
						LocalDate etkinliktar3haftaonce = LocalDate
								.parse(new SimpleDateFormat("yyyy-MM-dd").format(etkinlikTar));
						// add 2 week to the current date
						etkinliktar3haftaonce = etkinliktar3haftaonce.minus(3, ChronoUnit.WEEKS);

						etkinlikTar = java.sql.Date.valueOf(etkinliktar3haftaonce);

						//////////////////////////

						// System.out.println("etkinlikTar 3 Hafta �nce :: " +
						// etkinlikTar + " �imdiki Tarih: " + nowDate);

						if (nowDate.getTime() >= etkinlikTar.getTime()) {
							etkinlikler.add(etkinliklerOrj.get(i));
						}
					}
				}

				// Bulunan her etkinlik i�in mail listesinden eleman varsa o
				// ki�ilere
				// mail gidecek ve data base
				// g�ncellenecek.

				if (etkinlikler != null) {
					for (int i = 0; i < etkinlikler.size(); i++) {
						StringBuffer etkinlikIcerikForMail = new StringBuffer();

						System.out.println("Zaman :: " + new Date() + " Duyurulacak Etkinlik :: "
								+ etkinlikler.get(i).getEtkinlikAdi() + " Etkinlik Sube ::"
								+ etkinlikler.get(i).getSube().getSubeAdi());

						etkinlikIcerikForMail.delete(0, etkinlikIcerikForMail.length());
						// KA�AR KA�AR MA�L G�NDER�LECEK
						List<Mail> mailler = mailService.findMailsForSendMail(etkinlikler.get(i).getEtkinlikId(),
								kacKisiyeGonderilecek);
						String keys = "";
						ArrayList<String> emailler = new ArrayList<String>();

						if (mailler.size() > 0) {

							for (int y = 0; y < mailler.size(); y++) {
								emailler.add("" + mailler.get(y).getAbone().getAboneEposta());
								keys = keys + "-" + mailler.get(y).getMailKey();
							}
							keys = keys.substring(1);

							etkinlikIcerikForMail
									.append(etkinlikler.get(i).getEtkinlikMailIcerik().replace("unsubscribe",
											"unsubscribe?u=" + keys + "&e=" + etkinlikler.get(i).getEtkinlikId()));

							String[] toArray = emailler.toArray(new String[emailler.size()]);

							// MA�L G�NER
							if (etkinlikler.get(i).getSube().getSubeAdi().equals("�stanbul Bak�rk�y �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.bakirkoy@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Bak�rk�y";
									//
									mailSenderBakirkoy.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// - mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));

									// System.out.println("HATA :: " +
									// etkinlikler.get(i).getSube().getSubeAdi()
									// + " ICIN DB GUNCELLENDI");

									System.err.println(ex.getMessage());
								}
							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ankara Genel Merkez")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.ankara@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Ankara";
									//
									mailSenderAnkara.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ankara Bat�kent �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.ankara@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Bat�kent";
									//
									mailSenderAnkara.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Antalya �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.antalya@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Antalya";
									//
									mailSenderAntalya.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Adana �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.adana@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Adana";
									//
									mailSenderAdana.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Kuzey Adana �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.adana@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Adana";
									//
									mailSenderAdana.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ayd�n �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "aktiffelsefe.ims@gmail.com";
									String sentFromName = "Aktiffelsefe Ayd�n";
									//
									mailSender.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Bursa �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.bursa@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Bursa";
									//
									mailSenderBursa.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Denizli �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "aktiffelsefe.ims@gmail.com";
									String sentFromName = "Aktiffelsefe Denizli";
									//
									mailSender.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Eski�ehir �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.eskisehir@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Eski�ehir";
									//
									mailSenderEskisehir.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�stanbul Fatih �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "aktiffelsefe.ims@gmail.com";
									String sentFromName = "Aktiffelsefe Fatih";
									//
									mailSender.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�stanbul Kad�k�y �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.kadikoy@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Kad�k�y";
									//
									mailSenderKadikoy.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�stanbul Levent �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "aktiffelsefe.ims@gmail.com";
									String sentFromName = "Aktiffelsefe Levent";
									//
									mailSender.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�stanbul Maltepe �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "aktiffelsefe.ims@gmail.com";
									String sentFromName = "Aktiffelsefe Maltepe";
									//
									mailSender.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�stanbul �i�li �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.sisli@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe �i�li";
									//
									mailSenderSisli.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�stanbul �sk�dar �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.uskudar@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe �sk�dar";
									//
									mailSenderUskudar.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�zmir Alsancak �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.alsancak@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Alsancak";
									//
									mailSenderAlsancak.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�zmir Bornova �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.bornova@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Bornova";
									//
									mailSenderBornova.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�zmir Buca �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "aktiffelsefe.ims@gmail.com";
									String sentFromName = "Aktiffelsefe Buca";
									//
									mailSender.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi()
									.equals("�zmir G�zelyal� Temsilcili�i")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.guzelyali@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe G�zelyal�";
									//
									mailSenderGuzelyali.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�zmir Kar��yaka �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.karsiyaka@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Kar��yaka";
									//
									mailSenderKarsiyaka.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("�zmit �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.izmit@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe �zmit";
									//
									mailSenderIzmit.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Manisa Temsilcili�i")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "aktiffelsefe.ims@gmail.com";
									String sentFromName = "Aktiffelsefe Manisa";
									//
									mailSender.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Mersin �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.mersin@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Mersin";
									//
									mailSenderMersin.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Van �ubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "aktiffelsefe.ims@gmail.com";
									String sentFromName = "Aktiffelsefe Van";
									//
									mailSender.send(new MimeMessagePreparator() {
										public void prepare(MimeMessage mimeMessage) throws MessagingException {
											MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,
													"UTF-8");
											try {
												message.setFrom(new InternetAddress(from, sentFromName));
											} catch (UnsupportedEncodingException e) {
												// TODO Auto-generated catch
												// block
												e.printStackTrace();
											}
											message.setBcc(toArray);
											message.setSubject(konu);
											message.setText(etkinlikIcerikForMail.toString(), true);

										}
									});

									for (int y = 0; y < mailler.size(); y++) {

										// DATABASE G�NCELLE
										mailler.get(y).setMailGonderildi(true);
										mailler.get(y).setMailGonderilmeTarihi(nowDate);
										mailService.updateMail(mailler.get(y));

									}

									etkinlikler.get(i).setEtkinlikGonderilmeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilmeSayisi() + mailler.size());
									etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
											etkinlikler.get(i).getEtkinlikGonderilememeSayisi() - mailler.size());
									etkinlikService.updateEtkinlik(etkinlikler.get(i));

									System.out.println("BILGI ::" + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILDI DB GUNCELLENDI");

								} catch (MailException ex) {

									System.out.println("HATA :: " + etkinlikler.get(i).getSube().getSubeAdi()
											+ " ICIN MAIL GONDERILEMEDI");

									// for (int y = 0; y < mailler.size(); y++)
									// {
									//
									// // DATABASE G�NCELLE
									// mailler.get(y).setMailGonderilemedi(true);
									// mailler.get(y).setMailGonderilememeTarihi(nowDate);
									// mailService.updateMail(mailler.get(y));
									//
									// }
									// etkinlikler.get(i).setEtkinlikGonderilememeSayisi(
									// etkinlikler.get(i).getEtkinlikGonderilememeSayisi()
									// + mailler.size());
									// etkinlikService.updateEtkinlik(etkinlikler.get(i));
									//
									// System.out.println("HATA :: DB
									// GUNCELLENDI");

									System.err.println(ex.getMessage());
								}

							}

						}

					}
				}
			}
			List<Mail> mailler = mailService.findOldMails();
			for (int i = 0; i < mailler.size(); i++) {
				mailService.deleteMail(mailler.get(i).getMailId());
			}
			System.gc();
		}

	}

	private String findMaillerBySubeAdi(String subeAdi) {
		String mailler = "";
		if (subeAdi.equals("�stanbul Bak�rk�y �ubesi")) {
			mailler = "mailSenderBakirkoy";
		} else if (subeAdi.equals("Ankara Genel Merkez")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ankara Bat�kent �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Antalya �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Adana �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Kuzey Adana �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ayd�n �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Bursa �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Denizli �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Eski�ehir �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�stanbul Fatih �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�stanbul Kad�k�y �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�stanbul Levent �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�stanbul Maltepe �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�stanbul �i�li �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�stanbul �sk�dar �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�zmir Alsancak �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�zmir Bornova �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�zmir Buca �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�zmir G�zelyal� Temsilcili�i")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�zmir Kar��yaka �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("�zmit �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Manisa Temsilcili�i")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Mersin �ubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Van �ubesi")) {
			mailler = "mailSender";
		}
		return mailler;
	}

	@RequestMapping("readFromFile")
	public void readFromFile() {

		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(
					this.getClass().getClassLoader().getResourceAsStream("liste.csv"), "ISO-8859-9"));
			String readStr = "";
			String data[];
			while ((readStr = in.readLine()) != null) {
				// System.out.println("" + readStr);
				data = readStr.split(";");
				System.out.println("Email: " + data[1] + " �ube: " + data[4]);
				Abone abone = new Abone();
				abone.setAboneEposta(data[1].toString().trim());
				if (!data[2].equals("")) {
					if (data[2].toString().trim().lastIndexOf(" ") != -1) {
						System.out.println("1 ----------------------------------------");

						System.out.println("setAboneAdi: " + data[2].toString().trim().split(" ")[0].trim());
						System.out.println("setAboneSoyadi: " + data[2].toString().trim().split(" ")[1].trim());

						abone.setAboneAdi(data[2].toString().trim().split(" ")[0].trim());
						abone.setAboneSoyadi(data[2].toString().trim().split(" ")[1].trim());
					} else {
						System.out.println("2 ----------------------------------------");
						System.out.println("setAboneAdi: " + data[2].toString().trim());

						abone.setAboneAdi(data[2].toString().trim());
					}
				}
				abone.setAktif(true);
				if (!data[4].equals("")) {
					abone.setSube(subeService.findSubeByName(data[4].toString().trim()));
				}
				aboneService.createAbone(abone);

			}
			in.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	// @RequestMapping("deleteAllAbone")
	// public void deleteAllAbone() {
	// logger.info("deleteAllAbone.");
	//
	// List<Abone> aboneler = new ArrayList<Abone>();
	// aboneler = aboneService.findAllAbone();
	// for (int i = 0; i < aboneler.size(); i++) {
	// aboneService.deleteAbone(aboneler.get(i).getAboneId());
	// }
	//
	// }

}
