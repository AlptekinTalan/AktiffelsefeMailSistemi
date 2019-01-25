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

				// Bildirilecek etkinlikleri Bul eðer bu etkinlik tarihler
				// þimdiki
				// tarihden 1 hafta kadar önce durumundaysa
				// ve etkinlik bildirilecek iþareti varsa bildirilecek etkinlik
				// olarak
				// arraya al. MAX RESULTS dao imp içerisinde ayarlandý
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

						// System.out.println("etkinlikTar 3 Hafta Önce :: " +
						// etkinlikTar + " Þimdiki Tarih: " + nowDate);

						if (nowDate.getTime() >= etkinlikTar.getTime()) {
							etkinlikler.add(etkinliklerOrj.get(i));
						}
					}
				}

				// Bulunan her etkinlik için mail listesinden eleman varsa o
				// kiþilere
				// mail gidecek ve data base
				// güncellenecek.

				if (etkinlikler != null) {
					for (int i = 0; i < etkinlikler.size(); i++) {
						StringBuffer etkinlikIcerikForMail = new StringBuffer();

						System.out.println("Zaman :: " + new Date() + " Duyurulacak Etkinlik :: "
								+ etkinlikler.get(i).getEtkinlikAdi() + " Etkinlik Sube ::"
								+ etkinlikler.get(i).getSube().getSubeAdi());

						etkinlikIcerikForMail.delete(0, etkinlikIcerikForMail.length());
						// KAÇAR KAÇAR MAÝL GÖNDERÝLECEK
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

							// MAÝL GÖNER
							if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýstanbul Bakýrköy Þubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.bakirkoy@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Bakýrköy";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ankara Batýkent Þubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.ankara@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Batýkent";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Antalya Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Adana Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Kuzey Adana Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Aydýn Þubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "aktiffelsefe.ims@gmail.com";
									String sentFromName = "Aktiffelsefe Aydýn";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Bursa Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Denizli Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Eskiþehir Þubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.eskisehir@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Eskiþehir";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýstanbul Fatih Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýstanbul Kadýköy Þubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.kadikoy@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Kadýköy";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýstanbul Levent Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýstanbul Maltepe Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýstanbul Þiþli Þubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.sisli@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Þiþli";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýstanbul Üsküdar Þubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.uskudar@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Üsküdar";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýzmir Alsancak Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýzmir Bornova Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýzmir Buca Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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
									.equals("Ýzmir Güzelyalý Temsilciliði")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.guzelyali@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Güzelyalý";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýzmir Karþýyaka Þubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.karsiyaka@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Karþýyaka";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Ýzmit Þubesi")) {
								try {
									String konu = "En Son Etkinliklerimiz";
									String from = "ebulten.izmit@aktiffelsefe.org";
									String sentFromName = "Aktiffelsefe Ýzmit";
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Manisa Temsilciliði")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Mersin Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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

							} else if (etkinlikler.get(i).getSube().getSubeAdi().equals("Van Þubesi")) {
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

										// DATABASE GÜNCELLE
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
									// // DATABASE GÜNCELLE
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
		if (subeAdi.equals("Ýstanbul Bakýrköy Þubesi")) {
			mailler = "mailSenderBakirkoy";
		} else if (subeAdi.equals("Ankara Genel Merkez")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ankara Batýkent Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Antalya Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Adana Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Kuzey Adana Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Aydýn Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Bursa Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Denizli Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Eskiþehir Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýstanbul Fatih Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýstanbul Kadýköy Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýstanbul Levent Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýstanbul Maltepe Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýstanbul Þiþli Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýstanbul Üsküdar Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýzmir Alsancak Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýzmir Bornova Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýzmir Buca Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýzmir Güzelyalý Temsilciliði")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýzmir Karþýyaka Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Ýzmit Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Manisa Temsilciliði")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Mersin Þubesi")) {
			mailler = "mailSender";
		} else if (subeAdi.equals("Van Þubesi")) {
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
				System.out.println("Email: " + data[1] + " Þube: " + data[4]);
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
