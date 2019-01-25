package com.alptekintalan.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.jboss.logging.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.MailException;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alptekintalan.model.pojo.entity.Abone;
import com.alptekintalan.model.pojo.entity.AboneTemp;
import com.alptekintalan.model.pojo.entity.Egitmen;
import com.alptekintalan.model.pojo.entity.Etkinlik;
import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.service.AboneService;
import com.alptekintalan.service.AboneTempService;
import com.alptekintalan.service.MailService;
import com.alptekintalan.service.SubeService;

@Controller
public class AboneController {

	@Inject // @Autowired
	private AboneService aboneService;
	@Inject // @Autowired
	private SubeService subeService;
	@Inject // @Autowired
	private MailService mailService;
	@Inject // @Autowired
	private AboneTempService aboneTempService;
	@Inject
	private JavaMailSender mailSender;

	private static final Logger logger = Logger.getLogger(AboneController.class);

	public AboneController() {
	}

	// for 403 access denied page
	@RequestMapping("aboneEkleHata")
	public void aboneEkleHata() {
	}

	@RequestMapping("deneme")
	public String deneme() {
		return "deneme";
	}

	@RequestMapping("aboneOlForm")
	public String aboneOlForm(Model model) {

		String subeAdi = findSubeAdiByLoggedUserName("");

		List<Sube> subeSube = subeService.findSubeler(subeAdi);

		model.addAttribute("subeListe", subeSube);

		return "aboneOlForm";
	}

	@RequestMapping("aboneOlFormSonuc")
	public String EgitmenListele(@RequestParam String email) {

		return "aboneOlFormSonuc";
	}

	@RequestMapping("saveAboneWeb")
	public String saveAboneWeb(@RequestParam Map<Object, String> requestParams, @RequestParam String ad,
			@RequestParam String soyad, @RequestParam String email, @RequestParam Long subeAdi, Model model) {
		// String gRecaptchaResponse =
		// request.getParameter("g-recaptcha-response");
		String gRecaptchaResponse = requestParams.get("g-recaptcha-response");

		boolean verify = false;
		try {
			verify = VerifyRecaptcha.verify(gRecaptchaResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("verify: " + verify + " Ad: " + ad + " Soyad: " + soyad + " Email: " + email + " Þube: "
				+ subeAdi + " gRecaptchaResponse:" + gRecaptchaResponse);

		if (!verify) {
			String sube = findSubeAdiByLoggedUserName("");

			List<Sube> subeSube = subeService.findSubeler(sube);

			model.addAttribute("subeListe", subeSube);

			model.addAttribute("recaptchaFalse", true);

			return "aboneOlForm";
			// return new ModelAndView("redirect:aboneOlForm");

		} else {
			// EÐER eposta adresi ve þube ile daha önce kayýt yapýldýysa uyarý
			// versin.
			Long deger = subeAdi;

			Sube sube = subeService.findSube(deger);

			System.out.println("1 sube: " + sube.getSubeAdi());

			List<Abone> aboneler = aboneService.findAboneler(email, "eposta", sube.getSubeId());
			if (aboneler.size() > 0) {
				return "aboneOlFormHata";
			} else {

				System.out.println("AboneTmp oluþtur ve kodunu mail at.");

				int min = 1000;
				int max = 9999;
				Random r = new Random();
				int rasgeleDeger = r.nextInt((max - min) + 1) + min;

				// EMAÝL ÝÇERÝK
				String emailSifre = "";
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(
							this.getClass().getClassLoader().getResourceAsStream("guvenlikKodu.jsp"), "UTF8"));
					String str;
					while ((str = in.readLine()) != null) {
						emailSifre += str;
					}
					in.close();
				} catch (IOException e) {
				}
				final String emailSifre2 = emailSifre.replace("#KOD#", "" + rasgeleDeger);
				// EMAÝL ÝÇERÝK

				// Eðer geçici databasede varsa güncelle yoksa ekle.
				List<AboneTemp> abonelerTmp = aboneTempService.findAboneTempler(email, "eposta", sube.getSubeId());
				if (abonelerTmp.size() > 0) {

					AboneTemp aboneTemp = abonelerTmp.get(0);
					aboneTemp.setAboneTempAdi(ad);
					aboneTemp.setAboneTempSoyadi(soyad);
					aboneTemp.setAboneTempKey("" + rasgeleDeger);
					aboneTemp.setKayitTarihi(new Date());
					aboneTemp.setSube(sube);
					aboneTempService.updateAboneTemp(aboneTemp);

					try {
						String konu = "Abonelik Kayýt Þifresi";
						String from = "aktiffelsefe.ims@gmail.com";
						String sentFromName = "Aktiffelsefe Etkinlik Habercisi";
						//
						mailSender.send(new MimeMessagePreparator() {
							public void prepare(MimeMessage mimeMessage) throws MessagingException {
								MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
								try {
									message.setFrom(new InternetAddress(from, sentFromName));
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								message.setTo(email);
								message.setSubject(konu);
								message.setText(emailSifre2, true);

							}
						});

					} catch (MailException ex) {

						System.out.println("HATA :: MAIL GONDERILEMEDI");

						System.err.println(ex.getMessage());
					}

					return "redirect:aboneOlFormSonuc?email=" + email;
				} else {

					// AboneTemp database'ine geçici üye oluþturulmasý
					AboneTemp aboneTemp = new AboneTemp();
					aboneTemp.setAboneTempAdi(ad);
					aboneTemp.setAboneTempSoyadi(soyad);
					aboneTemp.setAboneTempEposta(email);
					aboneTemp.setAboneTempKey("" + rasgeleDeger);
					aboneTemp.setKayitTarihi(new Date());
					aboneTemp.setSube(sube);
					aboneTempService.createAboneTemp(aboneTemp);
					// KODU MAÝL GÖNDER
					try {
						String konu = "Abonelik Kayýt Þifresi";
						String from = "aktiffelsefe.ims@gmail.com";
						String sentFromName = "Aktiffelsefe Etkinlik Habercisi";
						//
						mailSender.send(new MimeMessagePreparator() {
							public void prepare(MimeMessage mimeMessage) throws MessagingException {
								MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
								try {
									message.setFrom(new InternetAddress(from, sentFromName));
								} catch (UnsupportedEncodingException e) {
									// TODO Auto-generated catch
									// block
									e.printStackTrace();
								}
								message.setTo(email);
								message.setSubject(konu);
								message.setText("" + emailSifre2, true);

							}
						});

					} catch (MailException ex) {

						System.out.println("HATA :: MAIL GONDERILEMEDI");

						System.err.println(ex.getMessage());
					}
					//
					return "redirect:aboneOlFormSonuc?email=" + email;
				}
			}

		}

	}

	@RequestMapping("saveAboneWebComplete")
	public String saveAboneWebComplete(@RequestParam String email, @RequestParam String sifre) {

		System.out.print("EMAÝL: " + email + "\nÞÝFRE:" + sifre);

		List<AboneTemp> aboneler = aboneTempService.findAboneTempByKeyAndEposta(sifre, email);
		if (aboneler.size() > 0) {
			AboneTemp aboneTemp = aboneler.get(0);
			// GERÇEK KAYIT OLUÞTUR
			Abone abone = new Abone();
			abone.setAboneAdi(aboneTemp.getAboneTempAdi());
			abone.setAboneSoyadi(aboneTemp.getAboneTempSoyadi());
			abone.setAboneEposta(aboneTemp.getAboneTempEposta());
			abone.setAktif(true);
			abone.setHatali(false);
			abone.setKayitTarihi(new Date());
			abone.setSube(aboneTemp.getSube());
			aboneService.createAbone(abone);

			aboneTempService.deleteAboneTemp(aboneTemp.getAboneTempId());
			return "aboneOlFormSuccessful";

		} else {
			// HATA GÖNDER
			return "aboneOlFormError";
		}

	}

	@RequestMapping("sendCode")
	public String sendCode(Model model) {

		System.out.print("Kodu Mail Gönder...");

		return "";
	}

	@RequestMapping("unsubscribe")
	public void unsubscribe() {

	}

	@RequestMapping("unsubscribeAbone")
	public String unsubscribeAbone(@RequestParam String eposta, @RequestParam String u, @RequestParam String e,
			Model model) {

		long etkinlikId = Long.parseLong(e);

		boolean pasiflendi = false;
		// Bu mail adresi ile bulunan aboneler
		List<Abone> aboneler = aboneService.findAboneler(eposta, "eposta", null);
		for (int i = 0; i < aboneler.size(); i++) {
			/// BULUNAN ABONE ÝÇÝN MAÝL TABLOSUNDA VAR MI ?
			if (mailService.findMailKeyByAboneId(etkinlikId, aboneler.get(i).getAboneId()) != null) {
				String mailKey = mailService.findMailKeyByAboneId(etkinlikId, aboneler.get(i).getAboneId())
						.getMailKey();

				if (u.indexOf(mailKey) != -1) {
					aboneler.get(i).setAktif(false);
					aboneService.updateAbone(aboneler.get(i));
					pasiflendi = true;
				}
			}

		}
		model.addAttribute("durum", pasiflendi);
		return "unsubscribeAbone";

	}

	@RequestMapping("newAbone")
	public String newAbone(@ModelAttribute Abone abone, Model model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		List<Sube> subeSube = subeService.findSubeler(subeAdi);

		model.addAttribute("subeListe", subeSube);

		abone.setAktif(true);

		model.addAttribute("aboneObject", abone);

		return "aboneForm";
	}

	@RequestMapping("newTopluAbone")
	public String newTopluAbone() {

		return "topluAboneForm";
	}

	/**
	 * email / Ýsim Soyisim / Þube
	 */
	@RequestMapping(value = "/saveTopluAbone", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public @ResponseBody String uploadFileHandler(@RequestParam("file") MultipartFile file) {

		if (!file.isEmpty()) {
			try {
				InputStream inputStream = file.getInputStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "ISO-8859-9"));

				String line;
				String data[];
				String uyariMetin = "<head><meta charset=" + (char) 34 + "UTF-8" + (char) 34 + "></head>";
				while ((line = bufferedReader.readLine()) != null) {
					data = line.split(";");
					System.out.println("Email: " + data[0] + " Ýsim Soyisim: " + data[1] + " Þube: " + data[2]);
					if (!data[0].equals("") && isValidEmailAddress(data[0].toString().trim())) {
						Abone abone = new Abone();
						abone.setAboneEposta(data[0].toString().trim());
						if (!data[1].equals("")) {
							if (data[1].toString().trim().lastIndexOf(" ") != -1) {
								// System.out.println("1
								// ----------------------------------------");

								// System.out.println("setAboneAdi: " +
								// data[0].toString().trim().split("
								// ")[0].trim());
								// System.out.println("setAboneSoyadi: " +
								// data[0].toString().trim().split("
								// ")[1].trim());

								abone.setAboneAdi(data[1].toString().trim().split(" ")[0].trim());
								abone.setAboneSoyadi(data[1].toString().trim().split(" ")[1].trim());
							} else {
								System.out.println("2 ----------------------------------------");
								System.out.println("setAboneAdi: " + data[1].toString().trim());

								abone.setAboneAdi(data[1].toString().trim());
							}
						}
						abone.setAktif(true);
						if (!data[2].equals("")) {
							abone.setSube(subeService.findSubeByName(data[2].toString().trim()));
							// eðer içeride ayný þube ve email ile baþka abone
							// yoksa.
							List<Abone> aboneler = aboneService.findAboneler(abone.getAboneEposta(), "eposta",
									abone.getSube().getSubeId());
							if (aboneler.size() > 0) {
								// Abone EKLEME
								uyariMetin = uyariMetin + "<font color=\"gold\">" + "Email: " + data[0] + " Þube: " + data[2]
										+ " daha önce eklendiði için eklenmedi.</font><br>";
							} else {
								aboneService.createAbone(abone);
								uyariMetin = uyariMetin + "<font color=\"blue\">" + "Email: " + data[0] + " Þube: " + data[2] + " Eklendi.</font><br>";

							}
						}
					} else {

						uyariMetin = uyariMetin + "<font color=\"red\">" + "Gecersiz Email: " + data[0].toString()+"</font> <br>";

						System.out.println("Geçersiz Email :: " + data[0].toString());
					}

				}
				return uyariMetin;

			} catch (Exception e) {
				return "Yükleme Hatasý => " + e.getMessage();
			}
		} else {
			return "Dosya Seçmediniz...";
		}
	}

	public static boolean isValidEmailAddress(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
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

	@RequestMapping("editAbone")
	public String updateAbone(@RequestParam long id, @ModelAttribute Abone abone, Model model,
			HttpServletResponse response) {

		//////////////////////
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		List<Sube> subeSube = subeService.findSubeler(subeAdi);

		model.addAttribute("subeListe", subeSube);
		//////////////////////

		abone = aboneService.findAbone(id);

		Sube seciliSube = subeService.findSube(abone.getSube().getSubeId());

		model.addAttribute("seciliSube", seciliSube);

		model.addAttribute("aboneObject", abone);

		return "aboneForm";
	}

	@RequestMapping("saveAbone")
	public ModelAndView saveAbone(@ModelAttribute Abone abone, @RequestParam Long subeAdi) {

		if (abone.getAboneId() == 0) {

			Long deger = subeAdi;

			Sube sube = subeService.findSube(deger);

			List<Abone> aboneler = aboneService.findAboneler(abone.getAboneEposta(), "eposta", sube.getSubeId());
			if (aboneler.size() > 0) {
				return new ModelAndView("redirect:aboneEkleHata");
			}

			abone.setSube(sube);

			aboneService.createAbone(abone);

		} else {

			Long deger = subeAdi;

			Sube sube = subeService.findSube(deger);

			List<Abone> aboneler = aboneService.findAboneler(abone.getAboneEposta(), "eposta", sube.getSubeId());
			if (aboneler.size() > 0 && aboneler.get(0).getAboneId() != abone.getAboneId()) {
				return new ModelAndView("redirect:aboneEkleHata");
			}

			abone.setSube(sube);

			aboneService.updateAbone(abone);
		}
		return new ModelAndView(
				"redirect:searchAbone?searchAdi=" + abone.getAboneEposta() + "&searchTur=eposta&subeAdi=");
	}

	@RequestMapping("removeAbone")
	public ModelAndView deleteAbone(@RequestParam long id) {
		try {
			mailService.deleteMailsByAboneId(id);
			aboneService.deleteAbone(id);

		} catch (Exception ex) {
			return new ModelAndView("aboneHata");

		}

		return new ModelAndView("redirect:listAllAbone");
	}

	@RequestMapping("listAllAbone")
	public String findAllAbone(ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		Sube subeId = subeService.findSubeByName(subeAdi);

		List<Sube> subeSube = subeService.findSubeler(subeAdi);

		model.addAttribute("subeListe", subeSube);

		List<Abone> aboneAbone = new ArrayList<Abone>();
		List<Abone> aboneAboneSube = new ArrayList<Abone>();

		if (subeAdi.equals("")) {
			aboneAbone = aboneService.findAllAbone();
			model.addAttribute("aboneListe", aboneAbone);

		} else {
			aboneAbone = aboneService.findAllAbone();
			for (int i = 0; i < aboneAbone.size(); i++) {
				if (subeId.getSubeId() == aboneAbone.get(i).getSube().getSubeId()) {
					aboneAboneSube.add(aboneAbone.get(i));
				}
			}
			model.addAttribute("aboneListe", aboneAboneSube);

		}

		return "aboneListe";

	}

	@RequestMapping(value = "/listAboneByPage", method = RequestMethod.GET)
	public String getEmployees(@RequestParam(value = "page", required = false) Long page, ModelMap model) {
		// now page is available.

		List<Sube> subeSube = subeService.findSubeler("");

		model.addAttribute("subeListe", subeSube);

		int startpage = (int) (page - 5 > 0 ? page - 5 : 0);
		int endpage = startpage + 10;

		model.addAttribute("aboneListe", aboneService.findAbonelerPageByPage(Integer.parseInt("" + page)));

		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);

		return "aboneListe";
	}

	@RequestMapping("searchAbone")
	public String searchAbone(@RequestParam Map<Object, String> requestParams, @RequestParam Long subeAdi,
			ModelMap model) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdiKullanici = findSubeAdiByLoggedUserName(loggedUser);

		List<Sube> subeSube = subeService.findSubeler(subeAdiKullanici);

		Sube subeId = subeService.findSubeByName(subeAdiKullanici);

		model.addAttribute("subeListe", subeSube);

		Object deger = null;

		deger = requestParams.get("searchAdi");

		List<Abone> aboneListe = aboneService.findAboneler(deger, requestParams.get("searchTur"), subeAdi);
		List<Abone> aboneListeSube = new ArrayList<Abone>();
		if (subeAdiKullanici.equals("")) {

			model.addAttribute("aboneListe", aboneListe);

		} else {
			for (int i = 0; i < aboneListe.size(); i++) {
				if (subeId.getSubeId() == aboneListe.get(i).getSube().getSubeId()) {
					aboneListeSube.add(aboneListe.get(i));
				}

			}

			model.addAttribute("aboneListe", aboneListeSube);

		}

		return "aboneListe";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}
