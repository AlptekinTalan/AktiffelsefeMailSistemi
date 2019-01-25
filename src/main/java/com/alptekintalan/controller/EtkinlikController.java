package com.alptekintalan.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.json.Json;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alptekintalan.model.pojo.entity.Gorevli;
import com.alptekintalan.model.pojo.entity.Abone;
import com.alptekintalan.model.pojo.entity.Egitmen;
import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.model.pojo.entity.Katilimci;
import com.alptekintalan.model.pojo.entity.Mail;
import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.service.AboneService;
import com.alptekintalan.service.EgitmenService;
import com.alptekintalan.service.EtkinlikService;
import com.alptekintalan.service.GorevliService;
import com.alptekintalan.service.KatilimciService;
import com.alptekintalan.service.MailService;
import com.alptekintalan.service.SubeService;
import com.alptekintalan.service.UyeService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Controller
public class EtkinlikController {

	@Inject // @Autowired
	private UyeService uyeService;
	@Inject // @Autowired
	private EtkinlikService etkinlikService;
	@Inject // @Autowired
	private KatilimciService katilimciService;
	@Inject // @Autowired
	private EgitmenService egitmenService;
	@Inject // @Autowired
	private GorevliService gorevliService;
	@Inject // @Autowired
	private SubeService subeService;
	@Inject // @Autowired
	private MailService mailService;
	@Inject // @Autowired
	private AboneService aboneService;
	@Inject
	private JavaMailSender mailSenderBakirkoy;

	private static final Logger logger = Logger.getLogger(EtkinlikController.class);

	public EtkinlikController() {
	}

	@RequestMapping("newEtkinlik")
	public String newEtkinlik(@ModelAttribute Etkinlik etkinlik, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		List<Sube> subeSube = subeService.findSubeler(subeAdi);

		model.addAttribute("subeListe", subeSube);

		etkinlik.setEtkinlikTarihi(new Date());

		///////////////////////
		String content = "";
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(
					this.getClass().getClassLoader().getResourceAsStream("sablonEtkinlik2.jsp"), "UTF8"));
			String str;
			while ((str = in.readLine()) != null) {
				content += str;
			}
			in.close();
		} catch (IOException e) {
		}
		///////////////////////

		String adres = "Hoþdere Cad. Fuar Sk. No:11/13";
		String ePosta = "info@aktiffelsefe.org";
		if (subeSube.size() == 1) {
			etkinlik.setEtkinlikAdresi(subeSube.get(0).getSubeAdresi());
			adres = subeSube.get(0).getSubeAdresi();
			ePosta = subeSube.get(0).getSubeEposta();
		}

		etkinlik.setEtkinlikMailIcerik(content.replace("#Adres#", adres).replace("#ePosta#", ePosta));

		model.addAttribute("etkinlikObject", etkinlik);

		return "etkinlikForm";
	}

	private String findSubeAdiByLoggedUserName(String loggedUser) {
		String subeAdi = "";
		if (loggedUser.equals("admin")) {
			subeAdi = "";
		} else if (loggedUser.equals("istanbulbakýrköy")) {
			subeAdi = "Ýstanbul Bakýrköy Þubesi";
		} else if (loggedUser.equals("ankaramerkez")) {
			subeAdi = "Ankara Genel Merkez";
		} else if (loggedUser.equals("ankarabatýkent")) {
			subeAdi = "Ankara Batýkent Þubesi";
		} else if (loggedUser.equals("antalya")) {
			subeAdi = "Antalya Þubesi";
		} else if (loggedUser.equals("adana")) {
			subeAdi = "Adana Þubesi";
		} else if (loggedUser.equals("kuzeyadana")) {
			subeAdi = "Kuzey Adana Þubesi";
		} else if (loggedUser.equals("aydýn")) {
			subeAdi = "Aydýn Þubesi";
		} else if (loggedUser.equals("bursa")) {
			subeAdi = "Bursa Þubesi";
		} else if (loggedUser.equals("denizli")) {
			subeAdi = "Denizli Þubesi";
		} else if (loggedUser.equals("eskiþehir")) {
			subeAdi = "Eskiþehir Þubesi";
		} else if (loggedUser.equals("istanbulfatih")) {
			subeAdi = "Ýstanbul Fatih Þubesi";
		} else if (loggedUser.equals("istanbulkadýköy")) {
			subeAdi = "Ýstanbul Kadýköy Þubesi";
		} else if (loggedUser.equals("istanbullevent")) {
			subeAdi = "Ýstanbul Levent Þubesi";
		} else if (loggedUser.equals("istanbulmaltepe")) {
			subeAdi = "Ýstanbul Maltepe Þubesi";
		} else if (loggedUser.equals("istanbulþiþli")) {
			subeAdi = "Ýstanbul Þiþli Þubesi";
		} else if (loggedUser.equals("istanbulüsküdar")) {
			subeAdi = "Ýstanbul Üsküdar Þubesi";
		} else if (loggedUser.equals("izmiralsancak")) {
			subeAdi = "Ýzmir Alsancak Þubesi";
		} else if (loggedUser.equals("izmirbornova")) {
			subeAdi = "Ýzmir Bornova Þubesi";
		} else if (loggedUser.equals("izmirbuca")) {
			subeAdi = "Ýzmir Buca Þubesi";
		} else if (loggedUser.equals("izmirgüzelyalý")) {
			subeAdi = "Ýzmir Güzelyalý Temsilciliði";
		} else if (loggedUser.equals("izmirkarþýyaka")) {
			subeAdi = "Ýzmir Karþýyaka Þubesi";
		} else if (loggedUser.equals("izmit")) {
			subeAdi = "Ýzmit Þubesi";
		} else if (loggedUser.equals("manisa")) {
			subeAdi = "Manisa Temsilciliði";
		} else if (loggedUser.equals("mersin")) {
			subeAdi = "Mersin Þubesi";
		} else if (loggedUser.equals("van")) {
			subeAdi = "Van Þubesi";
		}
		return subeAdi;
	}

	@RequestMapping("editEtkinlik")
	public String updateEtkinlik(@RequestParam long id, @ModelAttribute Etkinlik etkinlik, Model model,
			HttpServletResponse response) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		List<Sube> subeSube = subeService.findSubeler(subeAdi);

		model.addAttribute("subeListe", subeSube);

		etkinlik = etkinlikService.findEtkinlik(id);

		Sube seciliSube = subeService.findSube(etkinlik.getSube().getSubeId());

		model.addAttribute("seciliSube", seciliSube);

		List<Abone> aboneler = aboneService.findAbonelerBySubeId(seciliSube);

		int yuzdeDegerGonderilme = 0;

		if (aboneler.size() == 0) {
			yuzdeDegerGonderilme = 0;
		} else {
			yuzdeDegerGonderilme = 100 * etkinlik.getEtkinlikGonderilmeSayisi() / aboneler.size();
		}

		int yuzdeDegerGonderilememe = 100 - yuzdeDegerGonderilme;
		//
		// if (aboneler.size() == 0) {
		// yuzdeDegerGonderilememe = 0;
		// } else {
		// yuzdeDegerGonderilememe = 100 *
		// etkinlik.getEtkinlikGonderilememeSayisi() / aboneler.size();
		// }

		model.addAttribute("gonderilmeOrani", "" + yuzdeDegerGonderilme);

		model.addAttribute("gonderilememeOrani", "" + yuzdeDegerGonderilememe);

		model.addAttribute("etkinlikObject", etkinlik);

		return "etkinlikForm";
	}

	@RequestMapping("listKatilimciToEtkinlik")
	public String KatilimciListele(@RequestParam long etkinlikId, Model model, @ModelAttribute Etkinlik etkinlik) {

		etkinlik = etkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("etkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(deger, "etkinlik");
		for (int i = 0; i < katilimcilar.size(); i++) {
			// System.out.println("ETKÝNLÝK KATILIMCILARI " +
			// katilimcilar.get(i).getKatilimciAdi() + " Üye Id: "
			// + katilimcilar.get(i).getUye().getUyeId());
		}

		ArrayList<Uye> uyeListe = new ArrayList<Uye>();
		for (int i = 0; i < katilimcilar.size(); i++) {
			uyeListe.add(uyeService.findUye(katilimcilar.get(i).getUye().getUyeId()));
		}

		model.addAttribute("uyeListe", uyeListe);

		model.addAttribute("katilimciListe", katilimcilar);

		return "etkinlikFormListKatilimci";
	}

	@RequestMapping("listEgitmenToEtkinlik")
	public String EgitmenListele(@RequestParam long etkinlikId, Model model, @ModelAttribute Etkinlik etkinlik) {

		etkinlik = etkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("etkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Egitmen> egitmenler = egitmenService.findEgitmenlar(deger, "etkinlik");
		for (int i = 0; i < egitmenler.size(); i++) {
			// System.out.println("ETKÝNLÝK KATILIMCILARI " + " Üye Id: " +
			// egitmenler.get(i).getUye().getUyeId());
		}

		ArrayList<Uye> uyeListe = new ArrayList<Uye>();
		for (int i = 0; i < egitmenler.size(); i++) {
			uyeListe.add(uyeService.findUye(egitmenler.get(i).getUye().getUyeId()));
		}

		model.addAttribute("uyeListe", uyeListe);

		model.addAttribute("egitmenListe", egitmenler);

		return "etkinlikFormListEgitmen";
	}

	@RequestMapping("listGorevliToEtkinlik")
	public String GorevliListele(@RequestParam long etkinlikId, Model model, @ModelAttribute Etkinlik etkinlik) {

		etkinlik = etkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("etkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Gorevli> gorevliler = gorevliService.findGorevlilar(deger, "etkinlik");
		for (int i = 0; i < gorevliler.size(); i++) {
			// System.out.println("ETKÝNLÝK KATILIMCILARI " + " Üye Id: " +
			// gorevliler.get(i).getUye().getUyeId());
		}

		ArrayList<Uye> uyeListe = new ArrayList<Uye>();
		for (int i = 0; i < gorevliler.size(); i++) {
			uyeListe.add(uyeService.findUye(gorevliler.get(i).getUye().getUyeId()));
		}

		model.addAttribute("uyeListe", uyeListe);

		model.addAttribute("gorevliListe", gorevliler);

		return "etkinlikFormListGorevli";
	}

	@RequestMapping("searchUyeInEtkinlik")
	public String searchUyeInEtkinlik(@RequestParam long etkinlikId, @RequestParam String searchAdi,
			@RequestParam String searchTur, Model model, @ModelAttribute Etkinlik etkinlik) {

		etkinlik = etkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("etkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(deger, searchTur);
		for (int i = 0; i < katilimcilar.size(); i++) {
			// System.out.println("ETKÝNLÝK KATILIMCILARI " +
			// katilimcilar.get(i).getKatilimciAdi() + " Üye Id: "
			// + katilimcilar.get(i).getUye().getUyeId());
		}
		ArrayList<Uye> uyeListe = new ArrayList<Uye>();
		if (searchTur.equals("id")) {
			try {
				Long searchAdiId = Long.parseLong(searchAdi);

				for (int i = 0; i < katilimcilar.size(); i++) {
					if (katilimcilar.get(i).getUye().getUyeId() == searchAdiId) {
						uyeListe.add(uyeService.findUye(katilimcilar.get(i).getUye().getUyeId()));
					}
				}
			} catch (Exception ex) {

			}

		} else if (searchTur.equals("ad")) {
			for (int i = 0; i < katilimcilar.size(); i++) {
				if (katilimcilar.get(i).getUye().getUyeAdi().toUpperCase().indexOf(searchAdi.toUpperCase()) != -1) {
					uyeListe.add(uyeService.findUye(katilimcilar.get(i).getUye().getUyeId()));
				}
			}
		} else if (searchTur.equals("soyad")) {
			for (int i = 0; i < katilimcilar.size(); i++) {
				if (katilimcilar.get(i).getUye().getUyeSoyadi().toUpperCase().indexOf(searchAdi.toUpperCase()) != -1) {
					uyeListe.add(uyeService.findUye(katilimcilar.get(i).getUye().getUyeId()));
				}
			}
		}

		model.addAttribute("uyeListe", uyeListe);

		model.addAttribute("katilimciListe", katilimcilar);

		return "etkinlikFormListKatilimci";
	}

	@RequestMapping("listAllUyeForEtkinlikKatilimci")
	public ModelAndView findAllUye1(@RequestParam long etkinlikId) {

		Etkinlik etkinlik = etkinlikService.findEtkinlik(etkinlikId);

		List<Uye> uyeListe = uyeService.findAllUye();

		List<Uye> uygunUyeListe = new ArrayList<Uye>();

		for (int i = 0; i < uyeListe.size(); i++) {
			if (uyeListe.get(i).getUyeSeviye() >= etkinlik.getEtkinlikSeviye()) {
				uygunUyeListe.add(uyeListe.get(i));
			}
		}

		return new ModelAndView("etkinlikFormAddKatilimci", "uyeListe", uygunUyeListe);
	}

	@RequestMapping("listAllUyeForEtkinlikGorevli")
	public ModelAndView findAllUye2(@RequestParam long etkinlikId) {
		List<Uye> uyeListe = uyeService.findAllUye();
		return new ModelAndView("etkinlikFormAddGorevli", "uyeListe", uyeListe);
	}

	@RequestMapping("listAllUyeForEtkinlikEgitmen")
	public ModelAndView findAllUye3(@RequestParam long etkinlikId) {
		List<Uye> uyeListe = uyeService.findAllUye();
		return new ModelAndView("etkinlikFormAddEgitmen", "uyeListe", uyeListe);
	}

	@RequestMapping("addKatilimciToEtkinlik")
	public ModelAndView addKatilimciToEtkinlik(@RequestParam long uyeId, @RequestParam long etkinlikId) {

		Etkinlik etkinlik = etkinlikService.findEtkinlik(etkinlikId);

		Katilimci katilimci = new Katilimci();
		katilimci.setKatilimciAdi("KATILIMCI EKLE");

		Uye uye = uyeService.findUye(uyeId);
		katilimci.setUye(uye);

		etkinlik.getKatilimci().add(katilimci);

		katilimci.setEtkinlik(etkinlik);

		etkinlikService.updateEtkinlik(etkinlik);

		return new ModelAndView("redirect:listKatilimciToEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("addEgitmenToEtkinlik")
	public ModelAndView addEgitmenToEtkinlik(@RequestParam long uyeId, @RequestParam long etkinlikId) {

		Etkinlik etkinlik = etkinlikService.findEtkinlik(etkinlikId);

		Egitmen egitmen = new Egitmen();

		Uye uye = uyeService.findUye(uyeId);
		egitmen.setUye(uye);

		etkinlik.getEgitmen().add(egitmen);

		egitmen.setEtkinlik(etkinlik);

		etkinlikService.updateEtkinlik(etkinlik);

		return new ModelAndView("redirect:listEgitmenToEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("addGorevliToEtkinlik")
	public ModelAndView addGorevliToEtkinlik(@RequestParam long uyeId, @RequestParam long etkinlikId) {

		Etkinlik etkinlik = etkinlikService.findEtkinlik(etkinlikId);

		Gorevli gorevli = new Gorevli();

		Uye uye = uyeService.findUye(uyeId);
		gorevli.setUye(uye);

		etkinlik.getGorevli().add(gorevli);

		gorevli.setEtkinlik(etkinlik);

		etkinlikService.updateEtkinlik(etkinlik);

		return new ModelAndView("redirect:listGorevliToEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("saveEtkinlik")
	public ModelAndView saveEtkinlik(@ModelAttribute Etkinlik etkinlik, @RequestParam Long subeAdi) {

		if (etkinlik.getEtkinlikId() == 0) {

			Long deger = subeAdi;

			Sube sube = subeService.findSube(deger);

			etkinlik.setSube(sube);

			String yeniIcerik = etkinlik.getEtkinlikMailIcerik().replace("'", "\\'");

			// Yeni içerik metnin oku içerisindeki resim dosyalarýný parset base
			// 64 sonra onlarý servera yaz
			// oluþturulan isimleriyle base 64 leri yer deðiþtir.
			String icerik = yeniIcerik;
			String fileName, str = icerik;
			String Style = "";
			ArrayList<String> fileData = new ArrayList<String>();
			while (str.contains("<img src=\"data:image/")) {
				str = str.substring(str.indexOf("<img src=\"data:image/"));
				fileData.add(str.substring(0, str.indexOf(">") + 1));
				str = str.substring(str.indexOf(">") + 1);
			}

			for (int i = 0; i < fileData.size(); i++) {
				str = fileData.get(i);

				fileName = str.substring(str.indexOf("data-filename=") + 15);
				fileName = fileName.substring(0, fileName.indexOf("\""));

				Style = str.substring(str.indexOf("style=") + 7);
				Style = "\"" + Style.substring(0, Style.indexOf("\"")) + "\"";
				//
				try {

					str = str.substring(str.indexOf(",") + 1);
					str = str.substring(0, str.indexOf("\""));

					// DATA str
					try {

						String OzelUUID = UUID.randomUUID().toString();

						System.out.println("--> " + OzelUUID);

						// icerik = icerik.replace(fileData.get(i), "<img
						// src=http://localhost:8080/images/" + OzelUUID
						// + "_" + fileName + " style=" + Style + " />");

						icerik = icerik.replace(fileData.get(i), "<img src=http://35.157.242.234/images/" + OzelUUID
								+ "_" + fileName + " style=" + Style + " />");

						// This will decode the String which is encoded by using
						// Base64 class
						byte[] scanBytes = Base64.getMimeDecoder().decode(str);

						String SAVE_DIR = "/home/ubuntu/images/";

						File fileSaveDir = new File(SAVE_DIR);

						// Creates the save directory if it does not exists
						if (!fileSaveDir.exists()) {
							fileSaveDir.mkdirs();
						}

						File scanFile = new File(SAVE_DIR + File.separator + OzelUUID + "_" + fileName);

						BufferedOutputStream scanStream = new BufferedOutputStream(new FileOutputStream(scanFile));
						scanStream.write(scanBytes);
						scanStream.close();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

				} catch (Exception rt) {
					// my.ozelfonk.exception_box(rt);
				}
			}

			// *************************************************************************************

			etkinlik.setEtkinlikMailIcerik(icerik);

			List<Abone> aboneler = aboneService.findAbonelerBySubeId(sube);

			etkinlik.setEtkinlikGonderilememeSayisi(aboneler.size());

			etkinlikService.createEtkinlik(etkinlik);

			Date nowDate = new Date();

			Date etkinlikTar = etkinlik.getEtkinlikTarihi();

			if (etkinlikTar.getTime() > nowDate.getTime()) {

				if (etkinlik.isEtkinlikBildir()) // Mail Atýlmasý Ýsteniyorsa
				{
					// eðer içeride o id den yok sa o id nin þubesine göre
					// elemanlarý ekle
					// varsa email gönderildi ve gönderilme zamaný varsa onlarý
					// email gönder tiklerini boþaltma
					// ama timestamplarý olmayanlarý yani iptal edilmiþleri
					// tekrar gönderildi tiklerini false yap

					List<Mail> mailler = mailService.findMailByEtkinlikandSubeId(etkinlik.getEtkinlikId(),
							sube.getSubeId());
					// Etkinlik ÝDSÝ ile içeride kayýt
					// varsa Herhangi bir þey yapma
					// Yoksa diðerlerini sil ve tekrar
					// oluþtur
					if (mailler.size() <= 0) {
						mailService.deleteMailsByEtkinlikId(etkinlik.getEtkinlikId());

						// List<Abone> aboneler =
						// aboneService.findAbonelerBySubeId(sube);

						for (int i = 0; i < aboneler.size(); i++) {
							if (aboneler.get(i).isAktif() && !aboneler.get(i).isHatali()) {

								if (!aboneler.get(i).getAboneEposta().equals("")) {
									Mail mail = new Mail();
									mail.setEtkinlik(etkinlik);
									mail.setAbone(aboneler.get(i));
									mail.setSube(sube);
									mail.setMailKey(keyOlstur());
									mailService.createMail(mail);
								}
							}
						}
					} else {
						// Gönderilme zamaný olmayan satýrlarýn gönderildi
						// tikini
						// kaldýr

						for (int i = 0; i < mailler.size(); i++) {
							if (mailler.get(i).getMailGonderilmeTarihi() == null) {
								mailler.get(i).setMailGonderildi(false);
								mailService.updateMail(mailler.get(i));
							}

						}
					}

				} else {
					// etkinlik id si ile girilmiþ kayýtlarýn gönderildi tikini
					// at
					// ama timestamplarýna
					// dokunma böylece iptalimi yoksa gerçekten mi bitti
					// anlaþýlsýn.

					List<Mail> mailler = mailService.findMailByEtkinlikandSubeId(etkinlik.getEtkinlikId(),
							sube.getSubeId());

					for (int i = 0; i < mailler.size(); i++) {
						mailler.get(i).setMailGonderildi(true);
						mailService.updateMail(mailler.get(i));
					}
				}
			}

		} else {

			Long deger = subeAdi;

			Sube sube = subeService.findSube(deger);

			etkinlik.setSube(sube);

			String yeniIcerik = etkinlik.getEtkinlikMailIcerik().replace("'", "\\'");

			// Yeni içerik metnin oku içerisindeki resim dosyalarýný parset base
			// 64 sonra onlarý servera yaz
			// oluþturulan isimleriyle base 64 leri yer deðiþtir.
			String icerik = yeniIcerik;
			String fileName, str = icerik;
			String Style = "";
			ArrayList<String> fileData = new ArrayList<String>();
			while (str.contains("<img src=\"data:image/")) {
				str = str.substring(str.indexOf("<img src=\"data:image/"));
				fileData.add(str.substring(0, str.indexOf(">") + 1));
				str = str.substring(str.indexOf(">") + 1);
			}

			for (int i = 0; i < fileData.size(); i++) {
				str = fileData.get(i);

				fileName = str.substring(str.indexOf("data-filename=") + 15);
				fileName = fileName.substring(0, fileName.indexOf("\""));

				Style = str.substring(str.indexOf("style=") + 7);
				Style = "\"" + Style.substring(0, Style.indexOf("\"")) + "\"";
				//
				try {

					str = str.substring(str.indexOf(",") + 1);
					str = str.substring(0, str.indexOf("\""));

					// DATA str
					try {

						String OzelUUID = UUID.randomUUID().toString();

						System.out.println("--> " + OzelUUID);

						// icerik = icerik.replace(fileData.get(i), "<img
						// src=http://localhost:8080/images/" + OzelUUID
						// + "_" + fileName + " style=" + Style + " />");

						icerik = icerik.replace(fileData.get(i), "<img src=http://35.157.242.234/images/" + OzelUUID
								+ "_" + fileName + " style=" + Style + " />");

						// This will decode the String which is encoded by using
						// Base64 class
						byte[] scanBytes = Base64.getMimeDecoder().decode(str);

						String SAVE_DIR = "/home/ubuntu/images/";

						File fileSaveDir = new File(SAVE_DIR);

						// Creates the save directory if it does not exists
						if (!fileSaveDir.exists()) {
							fileSaveDir.mkdirs();
						}

						File scanFile = new File(SAVE_DIR + File.separator + OzelUUID + "_" + fileName);

						BufferedOutputStream scanStream = new BufferedOutputStream(new FileOutputStream(scanFile));
						scanStream.write(scanBytes);
						scanStream.close();
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}

				} catch (Exception rt) {
					// my.ozelfonk.exception_box(rt);
				}
			}

			// *************************************************************************************

			etkinlik.setEtkinlikMailIcerik(icerik);

			etkinlikService.updateEtkinlik(etkinlik);

			Date nowDate = new Date();

			Date etkinlikTar = etkinlik.getEtkinlikTarihi();

			if (etkinlikTar.getTime() > nowDate.getTime()) {

				if (etkinlik.isEtkinlikBildir()) // Mail Atýlmasý Ýsteniyorsa
				{
					// eðer içeride o id den yok sa o id nin þubesine göre
					// elemanlarý ekle
					// varsa email gönderildi ve gönderilme zamaný varsa onlarý
					// email gönder tiklerini boþaltma
					// ama timestamplarý olmayanlarý yani iptal edilmiþleri
					// tekrar gönderildi tiklerini false yap

					List<Mail> mailler = mailService.findMailByEtkinlikandSubeId(etkinlik.getEtkinlikId(),
							sube.getSubeId());
					// Etkinlik ÝDSÝ ile içeride kayýt
					// varsa Herhangi bir þey yapma
					// Yoksa diðerlerini sil ve tekrar
					// oluþtur
					if (mailler.size() <= 0) {
						mailService.deleteMailsByEtkinlikId(etkinlik.getEtkinlikId());

						List<Abone> aboneler = aboneService.findAbonelerBySubeId(sube);

						for (int i = 0; i < aboneler.size(); i++) {
							if (aboneler.get(i).isAktif() && !aboneler.get(i).isHatali()) {

								if (!aboneler.get(i).getAboneEposta().equals("")) {
									Mail mail = new Mail();
									mail.setEtkinlik(etkinlik);
									mail.setAbone(aboneler.get(i));
									mail.setSube(sube);
									mail.setMailKey(keyOlstur());
									mailService.createMail(mail);
								}
							}
						}
					} else {
						// Gönderilme zamaný olmayan satýrlarýn gönderildi
						// tikini
						// kaldýr

						for (int i = 0; i < mailler.size(); i++) {
							if (mailler.get(i).getMailGonderilmeTarihi() == null) {
								mailler.get(i).setMailGonderildi(false);
								mailService.updateMail(mailler.get(i));
							}

						}
					}

				} else {
					// etkinlik id si ile girilmiþ kayýtlarýn gönderildi tikini
					// at
					// ama timestamplarýna
					// dokunma böylece iptalimi yoksa gerçekten mi bitti
					// anlaþýlsýn.

					List<Mail> mailler = mailService.findMailByEtkinlikandSubeId(etkinlik.getEtkinlikId(),
							sube.getSubeId());

					for (int i = 0; i < mailler.size(); i++) {
						mailler.get(i).setMailGonderildi(true);
						mailService.updateMail(mailler.get(i));
					}
				}
			}
		}
		return new ModelAndView("redirect:searchEtkinlik?searchAdi=" + etkinlik.getEtkinlikId()
				+ "&searchTur=id&searchTarih1=&searchTarih2=");
	}

	private String keyOlstur() {
		SecureRandom random = new SecureRandom();
		return new BigInteger(130, random).toString(32);
	}

	@RequestMapping("removeEtkinlik")
	public ModelAndView deleteEtkinlik(@RequestParam long id) {
		try {
			List<Mail> mailler = mailService.findMailByEtkinlik(id);
			for (int i = 0; i < mailler.size(); i++) {
				mailService.deleteMail(mailler.get(i).getMailId());
			}
			etkinlikService.deleteEtkinlik(id);

		} catch (Exception ex) {
			return new ModelAndView("etkinlikHata");

		}

		return new ModelAndView("redirect:listAllEtkinlik");
	}

	@RequestMapping("removeKatilimciFromEtkinlik")
	public ModelAndView removeKatilimciFromEtkinlik(@RequestParam long katilimciId, @RequestParam long etkinlikId) {
		katilimciService.deleteKatilimci(katilimciId);
		return new ModelAndView("redirect:listKatilimciToEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("removeEgitmenFromEtkinlik")
	public ModelAndView removeEgitmenFromEtkinlik(@RequestParam long egitmenId, @RequestParam long etkinlikId) {
		egitmenService.deleteEgitmen(egitmenId);
		return new ModelAndView("redirect:listEgitmenToEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("removeGorevliFromEtkinlik")
	public ModelAndView removeGorevliFromEtkinlik(@RequestParam long gorevliId, @RequestParam long etkinlikId) {
		gorevliService.deleteGorevli(gorevliId);
		return new ModelAndView("redirect:listGorevliToEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("listAllEtkinlik")
	public String findAllEtkinlik(Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		Sube subeId = subeService.findSubeByName(subeAdi);

		List<Etkinlik> etkinlikler = null;
		if (subeAdi.equals("")) {
			etkinlikler = etkinlikService.findAllEtkinlik();

		} else {
			etkinlikler = etkinlikService.findEtkinliklerBySubeId(subeId.getSubeId());

		}

		List<Sube> subeler = subeService.findAllSube();

		double toplamEtkinlikSayisi = etkinlikler.size();

		double etkinlikSayisi = 0;

		double subeBazliEtkinlikSayisi = 0;

		List<Etkinlik> etkinlikler2 = null;

		ArrayList chart_subeler = new ArrayList();
		ArrayList chart_subeBazliEtkinlikSayisi = new ArrayList();

		for (int i = 0; i < subeler.size(); i++) {
			subeBazliEtkinlikSayisi = 0;

			etkinlikler2 = etkinlikService.findEtkinliklerBySubeId(subeler.get(i).getSubeId());
			etkinlikSayisi = etkinlikler2.size();
			if (etkinlikSayisi > 0) {
				chart_subeler.add(
						"\"" + subeler.get(i).getSubeAdi() + " [ " + Math.round(etkinlikSayisi) + " Etkinlik ]" + "\"");
				for (int j = 0; j < etkinlikler2.size(); j++) {
					subeBazliEtkinlikSayisi = subeBazliEtkinlikSayisi
							+ etkinlikler2.get(j).getEtkinlikGonderilmeSayisi();
				}
				chart_subeBazliEtkinlikSayisi.add(subeBazliEtkinlikSayisi);
				// map = new HashMap<Object, Object>();
				// map.put("x", subeler.get(i).getSubeAdi() + " [ " +
				// Math.round(etkinlikSayisi) + " Etkinlik ]");
				// map.put("y", ((etkinlikSayisi / toplamEtkinlikSayisi) *
				// 100));
				// list.add(map);
			}
		}

		///////////////////////
		model.addAttribute("listAllEtkinlik", true);

		model.addAttribute("deger1", chart_subeler.toString());

		model.addAttribute("deger2", chart_subeBazliEtkinlikSayisi.toString());

		model.addAttribute("etkinlikListe", etkinlikler);

		return "etkinlikListe";
	}

	@RequestMapping("raporAllEtkinlik")
	public String raporAllEtkinlik(Model model, @RequestParam String yil) {

		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);

		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);

		System.out.println("year: " + year);
		System.out.println("month: " + month);
		System.out.println("day: " + day);

		if (yil.equals("")) 
		{
			if(month==1||month==2)
			{
				yil = "" + (year+1);
			}
			else
			{
				yil = "" + year;
			}
		}
		
		String nextYil =""+(Integer.parseInt(yil) + 1);

		//Calendar nextYil = new GregorianCalendar((Integer.parseInt(yil) + 1), Calendar.JANUARY, 1);

		System.out.println("Yil: " + yil+" nextYil: "+nextYil );

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		Sube subeClass = subeService.findSubeByName(subeAdi);

		List<Etkinlik> etkinlikler = null;
		List<Sube> subeler = new ArrayList();

		if (subeAdi.equals("")) {
			etkinlikler = etkinlikService.findAllEtkinlik();
			subeler = subeService.findAllSube();

		} else {
			etkinlikler = etkinlikService.findEtkinliklerBySubeId(subeClass.getSubeId());

			subeler.add(subeClass);

		}

		double etkinlikSayisi = 0;

		double subeBazliEtkinlikSayisi = 0;

		List<Etkinlik> etkinlikler2 = null;

		ArrayList chart_subeler = new ArrayList();
		ArrayList chart_subeBazliEtkinlikSayisi = new ArrayList();

		for (int i = 0; i < subeler.size(); i++) {
			subeBazliEtkinlikSayisi = 0;

			etkinlikler2 = etkinlikService.findEtkinliklerBySubeId(subeler.get(i).getSubeId());
			etkinlikSayisi = etkinlikler2.size();
			if (etkinlikSayisi > 0) {
				chart_subeler.add(
						"\"" + subeler.get(i).getSubeAdi() + " [ " + Math.round(etkinlikSayisi) + " Etkinlik ]" + "\"");
				for (int j = 0; j < etkinlikler2.size(); j++) {
					subeBazliEtkinlikSayisi = subeBazliEtkinlikSayisi
							+ etkinlikler2.get(j).getEtkinlikGonderilmeSayisi();
				}
				chart_subeBazliEtkinlikSayisi.add(subeBazliEtkinlikSayisi);
			}
		}

		double martToplamEtkinlik = 0;
		double martGonderilenMailSayisi = 0;
		double martGonderilemeyenMailSayisi = 0;

		double nisanToplamEtkinlik = 0;
		double nisanGonderilenMailSayisi = 0;
		double nisanGonderilemeyenMailSayisi = 0;

		double mayisToplamEtkinlik = 0;
		double mayisGonderilenMailSayisi = 0;
		double mayisGonderilemeyenMailSayisi = 0;

		double haziranToplamEtkinlik = 0;
		double haziranGonderilenMailSayisi = 0;
		double haziranGonderilemeyenMailSayisi = 0;

		double temmuzToplamEtkinlik = 0;
		double temmuzGonderilenMailSayisi = 0;
		double temmuzGonderilemeyenMailSayisi = 0;

		double agustosToplamEtkinlik = 0;
		double agustosGonderilenMailSayisi = 0;
		double agustosGonderilemeyenMailSayisi = 0;

		double eylulToplamEtkinlik = 0;
		double eylulGonderilenMailSayisi = 0;
		double eylulGonderilemeyenMailSayisi = 0;

		double ekimToplamEtkinlik = 0;
		double ekimGonderilenMailSayisi = 0;
		double ekimGonderilemeyenMailSayisi = 0;

		double kasimToplamEtkinlik = 0;
		double kasimGonderilenMailSayisi = 0;
		double kasimGonderilemeyenMailSayisi = 0;

		double aralikToplamEtkinlik = 0;
		double aralikGonderilenMailSayisi = 0;
		double aralikGonderilemeyenMailSayisi = 0;

		double ocakToplamEtkinlik = 0;
		double ocakGonderilenMailSayisi = 0;
		double ocakGonderilemeyenMailSayisi = 0;

		double subatToplamEtkinlik = 0;
		double subatGonderilenMailSayisi = 0;
		double subatGonderilemeyenMailSayisi = 0;

		for (int i = 0; i < etkinlikler.size(); i++) {

			Calendar martAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.MARCH, 1);
			Calendar nisanAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.APRIL, 1);
			Calendar mayisAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.MAY, 1);
			Calendar haziranAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.JUNE, 1);
			Calendar temmuzAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.JULY, 1);
			Calendar agustosAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.AUGUST, 1);
			Calendar eylulAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.SEPTEMBER, 1);
			Calendar ekimAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.OCTOBER, 1);
			Calendar kasimAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.NOVEMBER, 1);
			Calendar aralikAyi = new GregorianCalendar(Integer.parseInt(yil), Calendar.DECEMBER, 1);
			Calendar ocakAyi = new GregorianCalendar(Integer.parseInt(nextYil), Calendar.JANUARY, 1);
			Calendar subatAyi = new GregorianCalendar(Integer.parseInt(nextYil), Calendar.FEBRUARY, 1);
			Calendar nextMartAyi = new GregorianCalendar(Integer.parseInt(nextYil), Calendar.MARCH, 1);

			// MART
			if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= martAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < nisanAyi.getTimeInMillis()) {
				martToplamEtkinlik++;
				martGonderilenMailSayisi = martGonderilenMailSayisi + etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				martGonderilemeyenMailSayisi = martGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();

				// NÝSAN
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= nisanAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < mayisAyi.getTimeInMillis()) {
				nisanToplamEtkinlik++;
				nisanGonderilenMailSayisi = nisanGonderilenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				nisanGonderilemeyenMailSayisi = nisanGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();
				// MAYIS
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= mayisAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < haziranAyi.getTimeInMillis()) {
				mayisToplamEtkinlik++;
				mayisGonderilenMailSayisi = mayisGonderilenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				mayisGonderilemeyenMailSayisi = mayisGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();
				// HAZÝRAN
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= haziranAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < temmuzAyi.getTimeInMillis()) {
				haziranToplamEtkinlik++;
				haziranGonderilenMailSayisi = haziranGonderilenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				haziranGonderilemeyenMailSayisi = haziranGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();
				// TEMMUZ
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= temmuzAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < agustosAyi.getTimeInMillis()) {
				temmuzToplamEtkinlik++;
				temmuzGonderilenMailSayisi = temmuzGonderilenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				temmuzGonderilemeyenMailSayisi = temmuzGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();
				// AGUSTOS
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= agustosAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < eylulAyi.getTimeInMillis()) {
				agustosToplamEtkinlik++;
				agustosGonderilenMailSayisi = agustosGonderilenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				agustosGonderilemeyenMailSayisi = agustosGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();
				// EYLUL
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= eylulAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < ekimAyi.getTimeInMillis()) {
				eylulToplamEtkinlik++;
				eylulGonderilenMailSayisi = eylulGonderilenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				eylulGonderilemeyenMailSayisi = eylulGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();

				// EKÝM
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= ekimAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < kasimAyi.getTimeInMillis()) {
				ekimToplamEtkinlik++;
				ekimGonderilenMailSayisi = ekimGonderilenMailSayisi + etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				ekimGonderilemeyenMailSayisi = ekimGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();

				// KASIM
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= kasimAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < aralikAyi.getTimeInMillis()) {
				kasimToplamEtkinlik++;
				kasimGonderilenMailSayisi = kasimGonderilenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				kasimGonderilemeyenMailSayisi = kasimGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();

				// ARALIK
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= aralikAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < ocakAyi.getTimeInMillis()) {
				aralikToplamEtkinlik++;
				aralikGonderilenMailSayisi = aralikGonderilenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				aralikGonderilemeyenMailSayisi = aralikGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();

				// OCAK
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= ocakAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < subatAyi.getTimeInMillis()) {
				ocakToplamEtkinlik++;
				ocakGonderilenMailSayisi = ocakGonderilenMailSayisi + etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				ocakGonderilemeyenMailSayisi = ocakGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();

				// SUBAT
			} else if (etkinlikler.get(i).getEtkinlikTarihi().getTime() >= subatAyi.getTimeInMillis()
					&& etkinlikler.get(i).getEtkinlikTarihi().getTime() < nextMartAyi.getTimeInMillis()) {
				subatToplamEtkinlik++;
				subatGonderilenMailSayisi = subatGonderilenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilmeSayisi();
				subatGonderilemeyenMailSayisi = subatGonderilemeyenMailSayisi
						+ etkinlikler.get(i).getEtkinlikGonderilememeSayisi();

			}

		}

		/////////////////////
		ArrayList aylar = new ArrayList();
		aylar.add("\"Mart [ " + Math.round(martToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Nisan [ " + Math.round(nisanToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Mayýs [ " + Math.round(mayisToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Haziran [ " + Math.round(haziranToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Temmuz [ " + Math.round(temmuzToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Aðustos [ " + Math.round(agustosToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Eylül [ " + Math.round(eylulToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Ekim [ " + Math.round(ekimToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Kasým [ " + Math.round(kasimToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Aralýk [ " + Math.round(aralikToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Ocak [ " + Math.round(ocakToplamEtkinlik) + " Etkinlik ]\"");
		aylar.add("\"Þubat [ " + Math.round(subatToplamEtkinlik) + " Etkinlik ]\"");

		ArrayList aybasliGonderilenMailSayisi = new ArrayList();
		aybasliGonderilenMailSayisi.add(martGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(nisanGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(mayisGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(haziranGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(temmuzGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(agustosGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(eylulGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(ekimGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(kasimGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(aralikGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(ocakGonderilenMailSayisi);
		aybasliGonderilenMailSayisi.add(subatGonderilenMailSayisi);

		ArrayList aybasliGonderilemeyenMailSayisi = new ArrayList();
		aybasliGonderilemeyenMailSayisi.add(martGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(nisanGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(mayisGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(haziranGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(temmuzGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(agustosGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(eylulGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(ekimGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(kasimGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(aralikGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(ocakGonderilemeyenMailSayisi);
		aybasliGonderilemeyenMailSayisi.add(subatGonderilemeyenMailSayisi);

		System.out.println("deneme: " + aylar.toString());

		System.out.println("chart_subeler: " + chart_subeler.toString());

		System.out.println("chart_subeBazliEtkinlikSayisi: " + chart_subeBazliEtkinlikSayisi.toString());

		System.out.println("aylar: " + aylar.toString());

		System.out.println("aybasliGonderilenMailSayisi: " + aybasliGonderilenMailSayisi.toString());

		System.out.println("aybasliGonderilemeyenMailSayisi: " + aybasliGonderilemeyenMailSayisi.toString());

		model.addAttribute("aylar", aylar.toString());

		model.addAttribute("aybasliGonderilenMailSayisi", aybasliGonderilenMailSayisi.toString());

		model.addAttribute("aybasliGonderilemeyenMailSayisi", aybasliGonderilemeyenMailSayisi.toString());

		///////////////////////
		model.addAttribute("listAllEtkinlik", true);

		model.addAttribute("deger1", chart_subeler.toString());

		model.addAttribute("deger2", chart_subeBazliEtkinlikSayisi.toString());

		model.addAttribute("etkinlikListe", etkinlikler);

		return "raporListe";
	}

	@RequestMapping("searchEtkinlik")
	public ModelAndView searchEtkinlik(@RequestParam Map<Object, String> requestParams,
			@RequestParam String searchTarih1, @RequestParam String searchTarih2) {
		Date minDate = null;
		Date maxDate = null;
		Object deger = null;
		if (requestParams.get("searchTur").equals("id")) {

			try {
				deger = Long.parseLong(requestParams.get("searchAdi"));

			} catch (Exception e) {
				deger = (long) -1d;
			}

		} else {
			deger = requestParams.get("searchAdi");
		}
		// logger.info("aaa minDate : " + minDate.toString() + " bbb maxDate : "
		// + maxDate.toString());
		try {
			if (searchTarih1.equals("")) {
				minDate = new SimpleDateFormat("yyyy-MM-dd").parse("1000-01-01");
			} else {
				minDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchTarih1);
			}
			if (searchTarih2.equals("")) {
				maxDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-12");
			} else {
				maxDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchTarih2);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<Etkinlik> etkinlikListe = etkinlikService.findEtkinlikler(deger, requestParams.get("searchTur"), minDate,
				maxDate);
		// ÞUBE BAZLI SORGU
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		Sube subeId = subeService.findSubeByName(subeAdi);

		List<Etkinlik> etkinlikListeSube = new ArrayList<Etkinlik>();
		if (!loggedUser.equals("admin")) {
			for (int i = 0; i < etkinlikListe.size(); i++) {
				if (etkinlikListe.get(i).getSube().getSubeId() == subeId.getSubeId()) {
					etkinlikListeSube.add(etkinlikListe.get(i));
				}

			}
			return new ModelAndView("etkinlikListe", "etkinlikListe", etkinlikListeSube);

		} else {
			return new ModelAndView("etkinlikListe", "etkinlikListe", etkinlikListe);

		}
		// ÞUBE BAZLI SORGU

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping("searchUyeForEtkinlik")
	public ModelAndView searchUye(@RequestParam Map<Object, String> requestParams, @RequestParam long etkinlikId) {
		Object deger = null;
		if (requestParams.get("searchTur").equals("id")) {

			try {
				deger = Long.parseLong(requestParams.get("searchAdi"));

			} catch (Exception e) {
				deger = (long) -1d;
			}

		} else {
			deger = requestParams.get("searchAdi");
		}
		List<Uye> uyeListe = uyeService.findUyeler(deger, requestParams.get("searchTur"), "");
		return new ModelAndView("etkinlikFormAddKatilimci", "uyeListe", uyeListe);
	}

	@RequestMapping("sirala")
	public void sirala() {

		List<Etkinlik> etkinlikListe = etkinlikService.findAllEtkinlik();
		for (int i = 0; i < etkinlikListe.size(); i++) {
		}

	}

	@RequestMapping("sendTestMail")
	public ModelAndView sendTestMail(@RequestParam long etkinlikId, @RequestParam String testMailAdresi) {

		try {
			String konu = "En Son Etkinliklerimiz";
			String from = "ebulten.bakirkoy@aktiffelsefe.org";
			String sentFromName = "Aktiffelsefe (Þubeniz)";

			Etkinlik etkinlik = etkinlikService.findEtkinlik(etkinlikId);

			mailSenderBakirkoy.send(new MimeMessagePreparator() {
				public void prepare(MimeMessage mimeMessage) throws MessagingException {
					MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
					try {
						message.setFrom(new InternetAddress(from, sentFromName));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch
						// block
						e.printStackTrace();
					}
					message.setBcc(testMailAdresi);
					message.setSubject(konu);
					message.setText(etkinlik.getEtkinlikMailIcerik().toString(), true);

				}
			});
			return new ModelAndView("testMailSuccessful");

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new ModelAndView("testMailError");

		}

	}

}
