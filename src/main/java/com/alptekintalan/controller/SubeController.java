package com.alptekintalan.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.model.pojo.entity.Sube;
import com.alptekintalan.service.SubeService;

@Controller
public class SubeController {

	@Inject // @Autowired
	private SubeService subeService;

	private static final Logger logger = Logger.getLogger(SubeController.class);

	public SubeController() {
	}

	@RequestMapping("newSube")
	public ModelAndView newSube(@ModelAttribute Sube sube) {
		return new ModelAndView("subeForm");
	}

	@RequestMapping("editSube")
	public ModelAndView updateSube(@RequestParam long id, @ModelAttribute Sube sube, HttpServletResponse response) {

		sube = subeService.findSube(id);

		return new ModelAndView("subeForm", "subeObject", sube);
	}

	@RequestMapping("saveSube")
	public ModelAndView saveSube(@ModelAttribute Sube sube) {
		if (sube.getSubeId() == 0) {

			subeService.createSube(sube);

		} else {

			subeService.updateSube(sube);
		}
		return new ModelAndView("redirect:listAllSube");
	}

	@RequestMapping("removeSube")
	public ModelAndView deleteSube(@RequestParam long id) {
		try {
			subeService.deleteSube(id);

		} catch (Exception ex) {
			return new ModelAndView("subeHata");

		}

		return new ModelAndView("redirect:listAllSube");
	}

	@RequestMapping("listAllSube")
	public ModelAndView findAllSube() {
		List<Sube> subeSube = subeService.findSubeler("");
		return new ModelAndView("subeListe", "subeListe", subeSube);
	}

	@RequestMapping("searchSube")
	public ModelAndView searchSube(@RequestParam String requestParams) {
		Object deger = requestParams;

		List<Sube> subeListe = subeService.findSubeler(deger);
		return new ModelAndView("subeListe", "subeListe", subeListe);
	}

}
