package com.alptekintalan.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
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
import org.springframework.web.servlet.ModelAndView;

import com.alptekintalan.model.pojo.entity.Gorevli;
import com.alptekintalan.model.pojo.entity.Abone;
import com.alptekintalan.model.pojo.entity.Egitmen;
import com.alptekintalan.model.pojo.entity.OzelEtkinlik;
import com.alptekintalan.model.pojo.entity.Katilimci;
import com.alptekintalan.model.pojo.entity.Mail;
import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.model.pojo.entity.Yoklama;
import com.alptekintalan.model.pojo.entity.Devamlilik;
import com.alptekintalan.model.pojo.entity.YoklamaForm;
import com.alptekintalan.service.AboneService;
import com.alptekintalan.service.DevamlilikService;
import com.alptekintalan.service.EgitmenService;
import com.alptekintalan.service.OzelEtkinlikService;
import com.alptekintalan.service.GorevliService;
import com.alptekintalan.service.KatilimciService;
import com.alptekintalan.service.MailService;
import com.alptekintalan.service.SubeService;
import com.alptekintalan.service.UyeService;
import com.alptekintalan.service.YoklamaService;
import com.alptekintalan.service.impl.YoklamaServiceImpl;

@Controller
public class OzelEtkinlikController {

	@Inject // @Autowired
	private UyeService uyeService;
	@Inject // @Autowired
	private OzelEtkinlikService ozelEtkinlikService;
	@Inject // @Autowired
	private KatilimciService katilimciService;
	@Inject // @Autowired
	private EgitmenService egitmenService;
	@Inject // @Autowired
	private GorevliService gorevliService;
	@Inject // @Autowired
	private SubeService subeService;
	@Inject // @Autowired
	private YoklamaService yoklamaService;
	@Inject // @Autowired
	private DevamlilikService devamlilikService;

	private static final Logger logger = Logger.getLogger(OzelEtkinlikController.class);

	public OzelEtkinlikController() {
	}

	@RequestMapping("newOzelEtkinlik")
	public String newOzelEtkinlik(@ModelAttribute OzelEtkinlik etkinlik, Model ozeletkinlik) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		List<Sube> subeSube = subeService.findSubeler(subeAdi);

		ozeletkinlik.addAttribute("subeListe", subeSube);

		etkinlik.setEtkinlikTarihi(new Date());

		///////////////////////
		// String content = "";
		// try {
		// BufferedReader in = new BufferedReader(new InputStreamReader(
		// this.getClass().getClassLoader().getResourceAsStream("sablonEtkinlik2.jsp"),
		// "UTF8"));
		// String str;
		// while ((str = in.readLine()) != null) {
		// content += str;
		// }
		// in.close();
		// } catch (IOException e) {
		// }

		///////////////////////

		String adres = "Hoþdere Cad. Fuar Sk. No:11/13";
		String ePosta = "info@aktiffelsefe.org";
		if (subeSube.size() == 1) {
			etkinlik.setEtkinlikAdresi(subeSube.get(0).getSubeAdresi());
			adres = subeSube.get(0).getSubeAdresi();
			ePosta = subeSube.get(0).getSubeEposta();
		}

		// etkinlik.setEtkinlikMailIcerik(content.replace("#Adres#",
		// adres).replace("#ePosta#", ePosta));

		ozeletkinlik.addAttribute("ozelEtkinlikObject", etkinlik);

		return "ozelEtkinlikForm";
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

	@RequestMapping("editOzelEtkinlik")
	public String updateEtkinlik(@RequestParam long id, @ModelAttribute OzelEtkinlik ozeletkinlik, Model model,
			HttpServletResponse response) {

		List<Sube> subeSube = subeService.findSubeler("");

		model.addAttribute("subeListe", subeSube);

		ozeletkinlik = ozelEtkinlikService.findEtkinlik(id);

		Sube seciliSube = subeService.findSube(ozeletkinlik.getSube().getSubeId());

		model.addAttribute("seciliSube", seciliSube);

		model.addAttribute("ozelEtkinlikObject", ozeletkinlik);

		return "ozelEtkinlikForm";
	}

	// LIST YOKLAMA
	@RequestMapping("listAddYoklamaToOzelEtkinlik")
	public String listAddYoklamaToOzelEtkinlik(@RequestParam long etkinlikId, Model model,
			@ModelAttribute OzelEtkinlik etkinlik) {

		etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);

		model.addAttribute("ozelEtkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Yoklama> yoklamalar = yoklamaService.findYoklamalar(etkinlikId);

		model.addAttribute("yoklamaListe", yoklamalar);

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(etkinlikId, "ozeletkinlik");

		model.addAttribute("katilimcilarObject", katilimcilar);

		List<Devamlilik> devamliliklar = devamlilikService.findDevamlilik(etkinlik);

		// for (int i = 0; i < devamliliklar.size(); i++) {
		// System.out.println("Devamliliklar ::--> " +
		// devamliliklar.get(i).getDevamlilikId() + " - "
		// + devamliliklar.get(i).getYoklama().getYoklamaId());
		// }

		model.addAttribute("devamlilikListe", devamliliklar);

		return "ozelEtkinlikFormListAddYoklama";
	}

	// NEW YOKLAMA
	@RequestMapping("newSablonYoklama")
	public String newSablonYoklama(@RequestParam long etkinlikId, Model model, @ModelAttribute Yoklama yoklama,
			@ModelAttribute OzelEtkinlik etkinlik) {

		// Eski tarihli yoklama girilmesi engellenecek eðer sýra ile girilmez
		// ise arraydaki kayýtlar farklý gideceðinden
		// hatalý sonuçlar gösterebilir.

		etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);

		yoklama.setYoklamaTarihi(new Date());

		model.addAttribute("yoklamaObject", yoklama);

		model.addAttribute("ozelEtkinlikObject", etkinlik);

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(etkinlikId, "ozeletkinlik");

		model.addAttribute("katilimcilarObject", katilimcilar);

		if (katilimcilar.size() <= 0) {
			return "yoklamaFormError2";
		}

		return "yoklamaSablonForm";
	}

	// SAVE YOKLAMA
	@RequestMapping("saveSablonYoklama")
	public ModelAndView saveSablonYoklama(@ModelAttribute Yoklama yoklama, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Yoklamalar içinden tarihi en büyük olandan küçük tarihli yoklama
		// girilemez.
		// Yani yoklamalar tarih sýrasýna göre girilmelidir.

		String yoklamaId = request.getParameter("yoklamaId");

		String etkinlikId = request.getParameter("etkinlikId");

		// Þablon Yoklamalar...

		///////////////////
		Date baslangicTarihi = yoklama.getYoklamaTarihi();

		ArrayList dersIsimleri = new ArrayList();
		dersIsimleri.add("Tanýtým Semineri");
		dersIsimleri.add("Etik ve Ahlak");
		dersIsimleri.add("Bhagavad Gita ve Ýnsanýn Ýç Mücadalesi");
		dersIsimleri.add("Aristoteles ve Mutluluk");
		dersIsimleri.add("Buda ve Acýdan Kurtuluþ");
		dersIsimleri.add("Plotinus ve Aþk");
		dersIsimleri.add("Tibet Felsefesi ve Yaþamýn Üç Aþamasý");
		dersIsimleri.add("Platon ve Akademia");
		dersIsimleri.add("Stoacýlar ve Özgür Ýrade");
		dersIsimleri.add("Birey, Toplum ve Devlet");
		dersIsimleri.add("Birlikte Yaþam ve Toplumda Roller");
		dersIsimleri.add("Tarih ve Mitoloji");
		dersIsimleri.add("Ýlkel Toplumlardan Günümüze Sosyopolitik Formlar");
		dersIsimleri.add("Tarih Felsefesi");

		for (int j = 0; j < 14; j++) {

			if (j != 0) {
				LocalDate etkinliktar1haftaSonra = LocalDate
						.parse(new SimpleDateFormat("yyyy-MM-dd").format(baslangicTarihi));
				// add 2 week to the current date
				etkinliktar1haftaSonra = etkinliktar1haftaSonra.plus(1, ChronoUnit.WEEKS);

				baslangicTarihi = java.sql.Date.valueOf(etkinliktar1haftaSonra);
			}

			yoklama.setYoklamaAdi("" + dersIsimleri.get(j));
			yoklama.setYoklamaTarihi(baslangicTarihi);
			yoklamaService.createYoklama(yoklama);

			List<Katilimci> katilimcilar2 = katilimciService.findKatilimcilar(yoklama.getOzeletkinlik().getEtkinlikId(),
					"ozeletkinlik");
			for (int i = 0; i < katilimcilar2.size(); i++) {

				Devamlilik devamlilik = new Devamlilik();
				devamlilik.setOzeletkinlik(yoklama.getOzeletkinlik());
				devamlilik.setUye(katilimcilar2.get(i).getUye());
				devamlilik.setVar(false);
				devamlilik.setYoklama(yoklama);
				devamlilikService.createDevamlilik(devamlilik);
			}

		}

		return new ModelAndView(
				"redirect:listAddYoklamaToOzelEtkinlik?etkinlikId=" + yoklama.getOzeletkinlik().getEtkinlikId());

	}

	// NEW YOKLAMA
	@RequestMapping("newYoklama")
	public String newYoklama(@RequestParam long etkinlikId, Model model, @ModelAttribute Yoklama yoklama,
			@ModelAttribute OzelEtkinlik etkinlik) {

		// Eski tarihli yoklama girilmesi engellenecek eðer sýra ile girilmez
		// ise arraydaki kayýtlar farklý gideceðinden
		// hatalý sonuçlar gösterebilir.

		etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);

		yoklama.setYoklamaTarihi(new Date());

		model.addAttribute("yoklamaObject", yoklama);

		model.addAttribute("ozelEtkinlikObject", etkinlik);

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(etkinlikId, "ozeletkinlik");

		model.addAttribute("katilimcilarObject", katilimcilar);

		if (katilimcilar.size() <= 0) {
			return "yoklamaFormError2";
		}

		return "yoklamaForm";
	}

	// EDIT YOKLAMA
	@RequestMapping("editYoklama")
	public String editYoklama(@RequestParam long yoklamaId, @RequestParam long etkinlikId, Model model,
			@ModelAttribute Yoklama yoklama, @ModelAttribute OzelEtkinlik ozelEtkinlik) {

		yoklama = yoklamaService.findYoklama(yoklamaId);

		ozelEtkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);

		model.addAttribute("yoklamaObject", yoklama);

		model.addAttribute("ozelEtkinlikObject", ozelEtkinlik);

		List<Devamlilik> devKatilimcilar = devamlilikService.findDevamlilik(yoklama);
		// for (int i = 0; i < devKatilimcilar.size(); i++) {
		//
		// System.out.println("ÖZEL ETKÝNLÝK devKatilimcilar " +
		// devKatilimcilar.get(i).getUye().getUyeAdi()
		// + " Üye Id: " + devKatilimcilar.get(i).getUye().getUyeId());
		// }

		model.addAttribute("devKatilimcilarObject", devKatilimcilar);

		Boolean readonlyDate = true;

		model.addAttribute("readonlyDate", readonlyDate);

		return "yoklamaForm";
	}

	// SAVE YOKLAMA
	@RequestMapping("saveYoklama")
	public ModelAndView saveYoklama(@ModelAttribute Yoklama yoklama, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Yoklamalar içinden tarihi en büyük olandan küçük tarihli yoklama
		// girilemez.
		// Yani yoklamalar tarih sýrasýna göre girilmelidir.

		Date enBuyukTarih;

		ArrayList<Date> tarihler = new ArrayList<Date>();

		enBuyukTarih = new SimpleDateFormat("yyyy-MM-dd").parse("1000-01-01");

		List<Yoklama> yoklamalar = yoklamaService.findYoklamalar(yoklama.getOzeletkinlik().getEtkinlikId());

		for (int i = 0; i < yoklamalar.size(); i++) {

			tarihler.add(yoklamalar.get(i).getYoklamaTarihi());

			if (i == 0) {
				enBuyukTarih = yoklamalar.get(i).getYoklamaTarihi();
			}
			if (yoklamalar.get(i).getYoklamaTarihi().getTime() > enBuyukTarih.getTime()) {
				enBuyukTarih = yoklamalar.get(i).getYoklamaTarihi();
			}
		}

		System.out.println(
				"enBuyukTarih: " + enBuyukTarih + " yoklama.getYoklamaTarihi(): " + yoklama.getYoklamaTarihi());

		if (yoklama.getYoklamaId() == 0) {

			// if (yoklama.getYoklamaTarihi().getTime() ==
			// enBuyukTarih.getTime()) {
			// System.out.println("AYNI GÜN ÝÇÝN ÝKÝ YOKLAMA GÝRÝLEMEZ...");
			// return new ModelAndView("yoklamaFormError");
			// }

			if (tarihler.indexOf(yoklama.getYoklamaTarihi()) != -1) {
				System.out.println("AYNI GÜN ÝÇÝN ÝKÝ YOKLAMA GÝRÝLEMEZ...");
				return new ModelAndView("yoklamaFormError");
			}

			Long yoklamaId = Long.parseLong(request.getParameter("yoklamaId"));

			String etkinlikId = request.getParameter("yoklamaAdi");

			yoklamaService.createYoklama(yoklama);

			List<Katilimci> katilimcilar2 = katilimciService.findKatilimcilar(yoklama.getOzeletkinlik().getEtkinlikId(),
					"ozeletkinlik");
			for (int i = 0; i < katilimcilar2.size(); i++) {
				// System.out.println("ÖZEL ETKÝNLÝK KATILIMCILARI " +
				// katilimcilar2.get(i).getUye().getUyeAdi()
				// + " Üye Id: " +
				// katilimcilar2.get(i).getUye().getUyeId());

				Devamlilik devamlilik = new Devamlilik();
				devamlilik.setOzeletkinlik(yoklama.getOzeletkinlik());
				devamlilik.setUye(katilimcilar2.get(i).getUye());
				if (request.getParameter("tik" + i) != null) {
					devamlilik.setVar(true);
				} else {
					devamlilik.setVar(false);
				}
				devamlilik.setYoklama(yoklama);
				devamlilikService.createDevamlilik(devamlilik);
			}

		} else {

			yoklamaService.updateYoklama(yoklama);

			List<Katilimci> katilimcilar2 = katilimciService.findKatilimcilar(yoklama.getOzeletkinlik().getEtkinlikId(),
					"ozeletkinlik");
			for (int i = 0; i < katilimcilar2.size(); i++) {
				// System.out.println("ÖZEL ETKÝNLÝK KATILIMCILARI DEÐÝÞTÝR
				// " +
				// katilimcilar2.get(i).getUye().getUyeAdi()
				// + " Üye Id: " +
				// katilimcilar2.get(i).getUye().getUyeId());

				List<Devamlilik> devamlilik = devamlilikService.findDevamlilik(yoklama, katilimcilar2.get(i).getUye());
				Devamlilik dev = devamlilik.get(0);
				if (request.getParameter("tik" + i) != null) {
					dev.setVar(true);
				} else {
					dev.setVar(false);
				}
				devamlilikService.updateDevamlilik(dev);
			}

		}
		return new ModelAndView(
				"redirect:listAddYoklamaToOzelEtkinlik?etkinlikId=" + yoklama.getOzeletkinlik().getEtkinlikId());

	}

	@RequestMapping("listKatilimciToOzelEtkinlik")
	public String KatilimciListele(@RequestParam long etkinlikId, Model model, @ModelAttribute OzelEtkinlik etkinlik) {

		etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("ozelEtkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(deger, "ozeletkinlik");
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

		return "ozelEtkinlikFormListKatilimci";
	}

	@RequestMapping("listEgitmenToOzelEtkinlik")
	public String EgitmenListele(@RequestParam long etkinlikId, Model model, @ModelAttribute OzelEtkinlik etkinlik) {

		etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("ozelEtkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Egitmen> egitmenler = egitmenService.findEgitmenlar(deger, "ozeletkinlik");
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

		return "ozelEtkinlikFormListEgitmen";
	}

	@RequestMapping("listGorevliToOzelEtkinlik")
	public String GorevliListele(@RequestParam long etkinlikId, Model model, @ModelAttribute OzelEtkinlik etkinlik) {

		etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("ozelEtkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Gorevli> gorevliler = gorevliService.findGorevlilar(deger, "ozeletkinlik");
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

		return "ozelEtkinlikFormListGorevli";
	}

	@RequestMapping("searchUyeInOzelEtkinlik")
	public String searchUyeInEtkinlik(@RequestParam long etkinlikId, @RequestParam String searchAdi,
			@RequestParam String searchTur, Model model, @ModelAttribute OzelEtkinlik etkinlik) {

		etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("ozelEtkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(deger, "ozeletkinlik");
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

		return "ozelEtkinlikFormListKatilimci";
	}

	@RequestMapping("listAllUyeForOzelEtkinlikKatilimci")
	public ModelAndView findAllUye1(@RequestParam long etkinlikId) {

		OzelEtkinlik etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);

		List<Uye> uyeListe = new ArrayList();

		// Ziyaretçile sadece Halkla Ýliþkiler Faaliyetine Eklenebilir.
		if (etkinlik.getEtkinlikTuru().equals("Halkla Ýliþkiler Faaliyeti")) {
			uyeListe = uyeService.findAllUye();
		} else {
			uyeListe = uyeService.findIcUyeler();
		}

		List<Uye> uygunUyeListe = new ArrayList<Uye>();

		// KATILIMCILARINI LÝSTEDEN ÇIKAR
		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(etkinlikId, "ozeletkinlik");
		System.out.println("LÝSTELE katilimcilar.size(): " + katilimcilar.size());
		List etkinlikKatilimcilarId = new ArrayList();
		for (int i = 0; i < katilimcilar.size(); i++) {

			etkinlikKatilimcilarId.add(katilimcilar.get(i).getUye().getUyeId());

		}

		for (int i = 0; i < uyeListe.size(); i++) {
			if (uyeListe.get(i).getUyeSeviye() >= etkinlik.getEtkinlikSeviye()) {

				if (!etkinlikKatilimcilarId.contains(uyeListe.get(i).getUyeId())) {

					uygunUyeListe.add(uyeListe.get(i));

				}

			}
		}

		return new ModelAndView("ozelEtkinlikFormAddKatilimci", "uyeListe", uygunUyeListe);
	}

	@RequestMapping("listAllUyeForOzelEtkinlikGorevli")
	public ModelAndView findAllUye2(@RequestParam long etkinlikId) {
		List<Uye> uyeListe = uyeService.findAllUye();
		return new ModelAndView("ozelEtkinlikFormAddGorevli", "uyeListe", uyeListe);
	}

	@RequestMapping("listAllUyeForOzelEtkinlikEgitmen")
	public ModelAndView findAllUye3(@RequestParam long etkinlikId) {
		List<Uye> uyeListe = uyeService.findAllUye();
		return new ModelAndView("ozelEtkinlikFormAddEgitmen", "uyeListe", uyeListe);
	}

	@RequestMapping("addKatilimciToOzelEtkinlik")
	public String addKatilimciToEtkinlik(@RequestParam long uyeId, @RequestParam long etkinlikId, Model model) {

		// Burada kalýndý özel etkinliðe ayný katýlýmcý 2 kere eklenmesi
		// önlenecek.
		// Daha önce yoklamasý oluþturulmuþ ise Devamlýlýk tablolarýna yeni
		// eklenmiþ olan kiþi
		// teker teker yoklama id leri ile eklenir en sona eklenmiþ olan kiþi
		// tablonun en sonunda görüntülenir.
		ArrayList uyarilar = new ArrayList<>();

		OzelEtkinlik etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);

		// Daha Önce Eklenmiþ Bir Katýlýmcý Üye Etkinliðe Katýlýmcý Olarak
		// Eklenemesin.
		Boolean dahaOnceEklenmis = false;
		Object deger = null;
		deger = etkinlikId;

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(deger, "ozeletkinlik");
		System.out.println("katilimcilar.size(): " + katilimcilar.size());

		for (int i = 0; i < katilimcilar.size(); i++) {

			System.out.println("KATILIMCILAR: " + katilimcilar.get(i).getUye().getUyeId() + " - " + uyeId);

			if (katilimcilar.get(i).getUye().getUyeId() == uyeId) {

				dahaOnceEklenmis = true;
				uyarilar.add("ELEMAN");
			}

		}

		System.out.println("dahaOnceEklenmis: " + dahaOnceEklenmis);

		if (!dahaOnceEklenmis) {
			Katilimci katilimci = new Katilimci();
			katilimci.setKatilimciAdi("KATILIMCI EKLE");

			Uye uye = uyeService.findUye(uyeId);
			katilimci.setUye(uye);

			etkinlik.getKatilimci().add(katilimci);

			katilimci.setOzeletkinlik(etkinlik);

			ozelEtkinlikService.updateEtkinlik(etkinlik);

			// Eðer Katýlýmcý Etkinliðe herhangi bir yoklama alýndýktan sonra
			// eklendiyse
			// alýnmýþ olan her yoklama için devamlýlýk kayýtlarý oluþtur.
			List<Yoklama> yoklamalar = yoklamaService.findYoklamalar(etkinlikId);
			if (yoklamalar.size() > 0) {
				for (int j = 0; j < yoklamalar.size(); j++) {
					Devamlilik devamlilik = new Devamlilik();
					devamlilik.setVar(false);
					devamlilik.setOzeletkinlik(etkinlik);
					devamlilik.setUye(uye);
					devamlilik.setYoklama(yoklamalar.get(j));
					devamlilikService.createDevamlilik(devamlilik);
				}

			}
		}

		//////////////////////////
		etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("ozelEtkinlikObject", etkinlik);

		Object deger2 = null;
		deger2 = etkinlikId;

		List<Katilimci> katilimcilar2 = katilimciService.findKatilimcilar(deger2, "ozeletkinlik");

		ArrayList<Uye> uyeListe = new ArrayList<Uye>();
		for (int i = 0; i < katilimcilar2.size(); i++) {
			uyeListe.add(uyeService.findUye(katilimcilar2.get(i).getUye().getUyeId()));
		}

		model.addAttribute("uyariEklenemezListe", uyarilar);

		model.addAttribute("uyeListe", uyeListe);

		model.addAttribute("katilimciListe", katilimcilar2);

		return "redirect:listAllUyeForOzelEtkinlikKatilimci?etkinlikId=" + etkinlikId;

		// return new
		// ModelAndView("redirect:listKatilimciToOzelEtkinlik?etkinlikId=" +
		// etkinlikId);
	}

	@RequestMapping("addKatilimciToOzelEtkinlikDon")
	public String addKatilimciToEtkinlikDon(@RequestParam long uyeId, @RequestParam long etkinlikId, Model model) {

		// Burada kalýndý özel etkinliðe ayný katýlýmcý 2 kere eklenmesi
		// önlenecek.
		// Daha önce yoklamasý oluþturulmuþ ise Devamlýlýk tablolarýna yeni
		// eklenmiþ olan kiþi
		// teker teker yoklama id leri ile eklenir en sona eklenmiþ olan kiþi
		// tablonun en sonunda görüntülenir.
		ArrayList uyarilar = new ArrayList<>();

		OzelEtkinlik etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);

		// Daha Önce Eklenmiþ Bir Katýlýmcý Üye Etkinliðe Katýlýmcý Olarak
		// Eklenemesin.
		Boolean dahaOnceEklenmis = false;
		Object deger = null;
		deger = etkinlikId;

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(deger, "ozeletkinlik");
		System.out.println("katilimcilar.size(): " + katilimcilar.size());

		for (int i = 0; i < katilimcilar.size(); i++) {

			System.out.println("KATILIMCILAR: " + katilimcilar.get(i).getUye().getUyeId() + " - " + uyeId);

			if (katilimcilar.get(i).getUye().getUyeId() == uyeId) {

				dahaOnceEklenmis = true;
				uyarilar.add("ELEMAN");
			}

		}

		System.out.println("dahaOnceEklenmis: " + dahaOnceEklenmis);

		if (!dahaOnceEklenmis) {
			Katilimci katilimci = new Katilimci();
			katilimci.setKatilimciAdi("KATILIMCI EKLE");

			Uye uye = uyeService.findUye(uyeId);
			katilimci.setUye(uye);

			etkinlik.getKatilimci().add(katilimci);

			katilimci.setOzeletkinlik(etkinlik);

			ozelEtkinlikService.updateEtkinlik(etkinlik);

			// Eðer Katýlýmcý Etkinliðe herhangi bir yoklama alýndýktan sonra
			// eklendiyse
			// alýnmýþ olan her yoklama için devamlýlýk kayýtlarý oluþtur.
			List<Yoklama> yoklamalar = yoklamaService.findYoklamalar(etkinlikId);
			if (yoklamalar.size() > 0) {
				for (int j = 0; j < yoklamalar.size(); j++) {
					Devamlilik devamlilik = new Devamlilik();
					devamlilik.setVar(false);
					devamlilik.setOzeletkinlik(etkinlik);
					devamlilik.setUye(uye);
					devamlilik.setYoklama(yoklamalar.get(j));
					devamlilikService.createDevamlilik(devamlilik);
				}

			}
		}

		//////////////////////////
		etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("ozelEtkinlikObject", etkinlik);

		Object deger2 = null;
		deger2 = etkinlikId;

		List<Katilimci> katilimcilar2 = katilimciService.findKatilimcilar(deger2, "ozeletkinlik");

		ArrayList<Uye> uyeListe = new ArrayList<Uye>();
		for (int i = 0; i < katilimcilar2.size(); i++) {
			uyeListe.add(uyeService.findUye(katilimcilar2.get(i).getUye().getUyeId()));
		}

		model.addAttribute("uyariEklenemezListe", uyarilar);

		model.addAttribute("uyeListe", uyeListe);

		model.addAttribute("katilimciListe", katilimcilar2);

		return "ozelEtkinlikFormListKatilimci";

		// return new
		// ModelAndView("redirect:listKatilimciToOzelEtkinlik?etkinlikId=" +
		// etkinlikId);
	}

	@RequestMapping("addEgitmenToOzelEtkinlik")
	public ModelAndView addEgitmenToEtkinlik(@RequestParam long uyeId, @RequestParam long etkinlikId) {

		OzelEtkinlik etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);

		Egitmen egitmen = new Egitmen();

		Uye uye = uyeService.findUye(uyeId);
		egitmen.setUye(uye);

		etkinlik.getEgitmen().add(egitmen);

		egitmen.setOzelEtkinlik(etkinlik);

		ozelEtkinlikService.updateEtkinlik(etkinlik);

		return new ModelAndView("redirect:listEgitmenToOzelEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("addGorevliToOzelEtkinlik")
	public ModelAndView addGorevliToEtkinlik(@RequestParam long uyeId, @RequestParam long etkinlikId) {

		OzelEtkinlik etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);

		Gorevli gorevli = new Gorevli();

		Uye uye = uyeService.findUye(uyeId);
		gorevli.setUye(uye);

		etkinlik.getGorevli().add(gorevli);

		gorevli.setOzelEtkinlik(etkinlik);

		ozelEtkinlikService.updateEtkinlik(etkinlik);

		return new ModelAndView("redirect:listGorevliToOzelEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("saveOzelEtkinlik")
	public ModelAndView saveEtkinlik(@ModelAttribute OzelEtkinlik etkinlik, @RequestParam Long subeAdi) {
		if (etkinlik.getEtkinlikId() == 0) {

			Long deger = subeAdi;

			Sube sube = subeService.findSube(deger);

			etkinlik.setSube(sube);

			ozelEtkinlikService.createEtkinlik(etkinlik);

			Date nowDate = new Date();

			Date etkinlikTar = etkinlik.getEtkinlikTarihi();

			if (etkinlikTar.getTime() > nowDate.getTime()) {

			}

		} else {

			Long deger = subeAdi;

			Sube sube = subeService.findSube(deger);

			etkinlik.setSube(sube);

			String yeniIcerik = etkinlik.getEtkinlikMailIcerik().replace("'", "\\'");

			etkinlik.setEtkinlikMailIcerik(yeniIcerik);

			ozelEtkinlikService.updateEtkinlik(etkinlik);

			Date nowDate = new Date();

			Date etkinlikTar = etkinlik.getEtkinlikTarihi();

			if (etkinlikTar.getTime() > nowDate.getTime()) {

			}
		}
		return new ModelAndView("redirect:listOzelEtkinlik?ozelEtkinlikId=" + etkinlik.getEtkinlikId());
	}

	@RequestMapping("removeOzelEtkinlik")
	public ModelAndView deleteEtkinlik(@RequestParam long id) {
		try {
			Object deger = null;
			deger = id;
			OzelEtkinlik ozelEtkinlik = new OzelEtkinlik();
			ozelEtkinlik = ozelEtkinlikService.findEtkinlik(id);

			List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(deger, "ozeletkinlik");
			for (int i = 0; i < katilimcilar.size(); i++) {

				katilimciService.deleteKatilimci(katilimcilar.get(i).getKatilimciId());

			}
			List<Gorevli> gorevliler = gorevliService.findGorevlilar(deger, "ozeletkinlik");
			for (int i = 0; i < gorevliler.size(); i++) {

				gorevliService.deleteGorevli(gorevliler.get(i).getGorevliId());

			}
			List<Egitmen> egitmenler = egitmenService.findEgitmenlar(deger, "ozeletkinlik");
			for (int i = 0; i < egitmenler.size(); i++) {

				egitmenService.deleteEgitmen(egitmenler.get(i).getEgitmenId());

			}
			List<Devamlilik> Devamliliklar = devamlilikService.findDevamlilik(ozelEtkinlik);
			for (int i = 0; i < Devamliliklar.size(); i++) {

				devamlilikService.deleteDevamlilik(Devamliliklar.get(i).getDevamlilikId());

			}
			List<Yoklama> Yoklamalar = yoklamaService.findYoklamalar(ozelEtkinlik.getEtkinlikId());
			for (int i = 0; i < Yoklamalar.size(); i++) {

				yoklamaService.deleteYoklama(Yoklamalar.get(i).getYoklamaId());

			}
			ozelEtkinlikService.deleteEtkinlik(id);

		} catch (Exception ex) {
			return new ModelAndView("ozelEtkinlikHata");

		}

		return new ModelAndView("redirect:listAllOzelEtkinlik");
	}

	@RequestMapping("removeKatilimciFromOzelEtkinlik")
	public String removeKatilimciFromEtkinlik(@RequestParam long katilimciId, @RequestParam long etkinlikId,
			Model model) {
		// Eðer Katýlýmcý için daha önce yoklama alýndýysa özel etkinlikten
		// katýlýmcý çýkartýlamasýn...

		ArrayList uyarilar = new ArrayList<>();

		List<Yoklama> yoklamalar = yoklamaService.findYoklamalar(etkinlikId);

		if (yoklamalar.size() > 0) {
			// Katýlýmcý Silinemez...
			uyarilar.add("Bir Uyarý Eklendi.");

		} else {
			katilimciService.deleteKatilimci(katilimciId);
			uyarilar.clear();
		}

		OzelEtkinlik etkinlik = ozelEtkinlikService.findEtkinlik(etkinlikId);
		model.addAttribute("ozelEtkinlikObject", etkinlik);

		Object deger = null;
		deger = etkinlikId;

		List<Katilimci> katilimcilar = katilimciService.findKatilimcilar(deger, "ozeletkinlik");
		for (int i = 0; i < katilimcilar.size(); i++) {
			// System.out.println("ETKÝNLÝK KATILIMCILARI " +
			// katilimcilar.get(i).getKatilimciAdi() + " Üye Id: "
			// + katilimcilar.get(i).getUye().getUyeId());
		}

		ArrayList<Uye> uyeListe = new ArrayList<Uye>();
		for (int i = 0; i < katilimcilar.size(); i++) {
			uyeListe.add(uyeService.findUye(katilimcilar.get(i).getUye().getUyeId()));
		}

		model.addAttribute("uyariListe", uyarilar);

		model.addAttribute("uyeListe", uyeListe);

		model.addAttribute("katilimciListe", katilimcilar);

		return "ozelEtkinlikFormListKatilimci";

		// return new
		// ModelAndView("redirect:listKatilimciToOzelEtkinlik?etkinlikId=" +
		// etkinlikId);
	}

	@RequestMapping("removeEgitmenFromOzelEtkinlik")
	public ModelAndView removeEgitmenFromEtkinlik(@RequestParam long egitmenId, @RequestParam long etkinlikId) {
		egitmenService.deleteEgitmen(egitmenId);
		return new ModelAndView("redirect:listEgitmenToOzelEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("removeGorevliFromOzelEtkinlik")
	public ModelAndView removeGorevliFromEtkinlik(@RequestParam long gorevliId, @RequestParam long etkinlikId) {
		gorevliService.deleteGorevli(gorevliId);
		return new ModelAndView("redirect:listGorevliToOzelEtkinlik?etkinlikId=" + etkinlikId);
	}

	@RequestMapping("listAllOzelEtkinlik")
	public ModelAndView findAllEtkinlik() {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		Sube subeId = subeService.findSubeByName(subeAdi);

		List<OzelEtkinlik> etkinlikler = null;
		if (subeAdi.equals("")) {
			etkinlikler = ozelEtkinlikService.findAllEtkinlik();

		} else {

			etkinlikler = ozelEtkinlikService.findAllEtkinlik();

			// etkinlikler =
			// ozelEtkinlikService.findEtkinliklerBySubeId(subeId.getSubeId());

		}

		return new ModelAndView("ozelEtkinlikListe", "etkinlikListe", etkinlikler);
	}

	@RequestMapping("listOzelEtkinlik")
	public ModelAndView listOzelEtkinlik(@RequestParam long ozelEtkinlikId) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		OzelEtkinlik etkinlik = ozelEtkinlikService.findEtkinlik(ozelEtkinlikId);

		List<OzelEtkinlik> etkinlikler = new ArrayList<OzelEtkinlik>();

		etkinlikler.add(etkinlik);

		return new ModelAndView("ozelEtkinlikListe", "etkinlikListe", etkinlikler);
	}

	@RequestMapping("searchOzelEtkinlik")
	public ModelAndView searchEtkinlik(@RequestParam Map<Object, String> requestParams,
			@RequestParam String searchTarih1, @RequestParam String searchTarih2) {
		Date minDate = null;
		Date maxDate = null;
		try {
			minDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchTarih1);
			maxDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchTarih2);

		} catch (ParseException e) {
			e.printStackTrace();
		}
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
			}
			if (searchTarih2.equals("")) {
				maxDate = new SimpleDateFormat("yyyy-MM-dd").parse("9999-12-12");
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		List<OzelEtkinlik> etkinlikListe = ozelEtkinlikService.findEtkinlikler(deger, requestParams.get("searchTur"),
				minDate, maxDate);
		// ÞUBE BAZLI SORGU
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedUser = auth.getName(); // get logged in username

		String subeAdi = findSubeAdiByLoggedUserName(loggedUser);

		Sube subeId = subeService.findSubeByName(subeAdi);

		List<OzelEtkinlik> etkinlikListeSube = new ArrayList<OzelEtkinlik>();
		if (!loggedUser.equals("admin")) {
			for (int i = 0; i < etkinlikListe.size(); i++) {
				if (etkinlikListe.get(i).getSube().getSubeId() == subeId.getSubeId()) {
					etkinlikListeSube.add(etkinlikListe.get(i));
				}

			}
			return new ModelAndView("ozelEtkinlikListe", "etkinlikListe", etkinlikListeSube);

		} else {
			return new ModelAndView("ozelEtkinlikListe", "etkinlikListe", etkinlikListe);

		}
		// ÞUBE BAZLI SORGU

	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	@RequestMapping("searchUyeForOzelEtkinlik")
	public ModelAndView searchUye(@RequestParam Map<Object, String> requestParams, @RequestParam long etkinlikId,
			@RequestParam String statu) {
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
		List<Uye> uyeListe = uyeService.findUyeler(deger, requestParams.get("searchTur"), statu);
		return new ModelAndView("ozelEtkinlikFormAddKatilimci", "uyeListe", uyeListe);
	}

	@RequestMapping("ozelSirala")
	public void ozelSirala() {

		List<OzelEtkinlik> etkinlikListe = ozelEtkinlikService.findAllEtkinlik();
		for (int i = 0; i < etkinlikListe.size(); i++) {

		}

	}

}
