package com.alptekintalan.controller;

import java.io.IOException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Uye;
import com.alptekintalan.service.SubeService;
import com.alptekintalan.service.UyeService;

@Controller
public class UyeController {

	@Inject // @Autowired
	private UyeService uyeService;
	@Inject // @Autowired
	private SubeService subeService;

	private static final Logger logger = Logger.getLogger(UyeController.class);

	public UyeController() {
	}

	// @RequestMapping(value = "/login", method = RequestMethod.GET)
	// public ModelAndView login(@RequestParam(value = "error", required =
	// false) String error,
	// @RequestParam(value = "logout", required = false) String logout) {
	// ModelAndView model = new ModelAndView();
	// if (error != null) {
	// model.addObject("error", "Invalid username and password!");
	// }
	// if (logout != null) {
	// model.addObject("msg", "You've been logged out successfully.");
	// }
	// model.setViewName("login");
	// return model;
	// }

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Merhaba " + user.getName() + ", bu sayfayý görmek için yetkin yok :(((");
		} else {
			model.addObject("msg", "Bu sayfayý görmek için yetkin yok :(((");
		}

		model.setViewName("403");
		return model;

	}

	// for 403 access denied page
	@RequestMapping("uyeEkleHata")
	public void uyeEkleHata() {
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping("newUye")
	public String newUye(@ModelAttribute Uye uye, Model model) {

		List<Sube> subeSube = subeService.findSubeler("Ýstanbul Bakýrköy Þubesi");

		model.addAttribute("subeListe", subeSube);

		return "uyeForm";

	}

	@RequestMapping("editUye")
	public String updateUye(@RequestParam long id, @ModelAttribute Uye uye, Model model, HttpServletResponse response) {

		List<Sube> subeSube = subeService.findSubeler("");

		model.addAttribute("subeListe", subeSube);

		// Seçili Þube
		uye = uyeService.findUye(id);

		Sube seciliSube = subeService.findSube(uye.getSube().getSubeId());

		model.addAttribute("seciliSube", seciliSube);

		model.addAttribute("uyeObject", uye);

		return "uyeForm";

	}

	@RequestMapping("activityUye")
	public String activityUye(@RequestParam long id, @ModelAttribute Uye uye, Model model,
			HttpServletResponse response) {

		List<Sube> subeSube = subeService.findSubeler("");

		model.addAttribute("subeListe", subeSube);

		// Seçili Þube
		uye = uyeService.findUye(id);

		Sube seciliSube = subeService.findSube(uye.getSube().getSubeId());

		model.addAttribute("seciliSube", seciliSube);

		model.addAttribute("uyeObject", uye);
		
		ArrayList deger1 = new ArrayList();
		ArrayList deger2 = new ArrayList();
		
		deger1.add("A");
		deger1.add("B");
		
		double degera=1;
		double degerb=2;
		
		deger2.add(degera);
		deger2.add(degerb);
		
		model.addAttribute("deger1", deger1.toString());

		model.addAttribute("deger2", deger2.toString());

		return "activityForm";

	}

	@RequestMapping("hakkimizda")
	public void hakkimizda() {
	}

	@RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	public void showImage(@RequestParam long id, @ModelAttribute Uye uye, HttpServletResponse response,
			HttpServletRequest request) throws ServletException, IOException {

		uye = uyeService.findUye(id);

		response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
		response.getOutputStream().write(uye.getFoto());
		response.getOutputStream().close();

	}

	@RequestMapping("saveUye")
	public ModelAndView saveUye(@RequestParam("file") MultipartFile file, @ModelAttribute Uye uye,
			@RequestParam Long subeAdi, @RequestParam String ePosta) {
		// Ayný e posta ile içeride kayýt var ise üye kaydetmeyi engelle.
		List<Uye> uyeEposta = uyeService.findUyeler(ePosta, "eposta", "");

		if (uye.getUyeId() == 0) {
			/*
			 * if (uyeEposta.size() > 0) { return new
			 * ModelAndView("redirect:uyeEkleHata"); }
			 */
			String fileName = null;
			if (!file.isEmpty()) {
				try {
					fileName = file.getOriginalFilename();
					byte[] bytes = file.getBytes();
					uye.setFoto(bytes);

				} catch (Exception e) {
					logger.info("FOTO EKLENEMEDÝ 1 " + fileName + ": " + e.getMessage());
				}
			} else {
			}

			Long deger = subeAdi;

			Sube sube = subeService.findSube(deger);

			uye.setSube(sube);

			uyeService.createUye(uye);

		} else {
			if (uyeEposta.size() > 0) {
				/*
				 * if (uyeEposta.get(0).getUyeId() != uye.getUyeId()) { return
				 * new ModelAndView("redirect:uyeEkleHata"); }
				 */
			}
			String fileName = null;
			if (!file.isEmpty()) {
				try {
					fileName = file.getOriginalFilename();
					byte[] bytes = file.getBytes();
					uye.setFoto(bytes);

				} catch (Exception e) {
					logger.info("FOTO EKLENEMEDÝ 2" + fileName + ": " + e.getMessage());
				}
			} else {
				Uye uye2 = uyeService.findUye(uye.getUyeId());
				uye.setFoto(uye2.getFoto());
			}
			Long deger = subeAdi;

			Sube sube = subeService.findSube(deger);

			uye.setSube(sube);

			uyeService.updateUye(uye);

		}
		// return new ModelAndView("redirect:listAllUye");
		return new ModelAndView("redirect:listAllUye?uyeId=" + uye.getUyeId());

	}

	@RequestMapping("removeUye")
	public ModelAndView deleteUye(@RequestParam long id) {
		try {
			uyeService.deleteUye(id);
		} catch (Exception ex) {
			return new ModelAndView("uyeSilHata");
		}
		return new ModelAndView("redirect:listAllUye?uyeId=0");
	}

	@RequestMapping(value = { "getListAllUye", "/listAllUye" })
	public ModelAndView findAllUye(@RequestParam long uyeId) {
		List<Uye> uyeListe = new ArrayList<Uye>();
		if (uyeId == 0) {
			uyeListe = uyeService.findAllUye();

		} else {
			uyeListe.add(uyeService.findUye(uyeId));

		}
		return new ModelAndView("uyeListe", "uyeListe", uyeListe);
	}

	@RequestMapping("searchUye")
	public ModelAndView searchUye(@RequestParam Map<Object, String> requestParams) {
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
		return new ModelAndView("uyeListe", "uyeListe", uyeListe);
	}

	@RequestMapping(value = "/searchUyeAdiSoyadi", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody String getTime(@RequestParam(value = "uyeAdi") String uyeAdi,
			@RequestParam(value = "uyeSoyadi") String uyeSoyadi) {

		String result = "";
		if (!uyeAdi.equals("") && !uyeSoyadi.equals("")) {
			List<Uye> uyeListe = uyeService.findUyelerByNameAndLastName(uyeAdi, uyeSoyadi);

			if (uyeListe.size() > 0) {
				result = "<strong>" + uyeAdi + " " + uyeSoyadi
						+ "</strong> isimli üye daha önce oluþturulmuþ, kayýtlarý kontrol etmek isteyebilirsin.";
			}
		}

		return result;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		sdf.setLenient(true);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

}