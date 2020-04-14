package com.indoorpositioning.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.indoorpositioning.persistence.Component;
import com.indoorpositioning.services.ComponentService;

import java.util.List;

@RestController
@RequestMapping("/unpairComponents")
public class UnpairComponentsController {
	@Autowired
	private ComponentService componentService;


	@GetMapping("")
	public ModelAndView renderPageGet_UC() {
		ModelAndView mav= new ModelAndView("classpath:/templates/unpairComponents.html");
		mav.addObject("component", new Component());
		List<Component> allActiveComponents=componentService.getAllActiveComponents();
		if (!allActiveComponents.isEmpty()) {
			mav.addObject("allActiveComponents", allActiveComponents);
		}
		return mav;
	}
	@PostMapping("")
	public ModelAndView renderPagePost_UC(@ModelAttribute("component") Component component) {
		ModelAndView mav= new ModelAndView("classpath:/templates/unpairComponents.html");
		mav.addObject("component", new Component());
		String feedback = "El componente ha sido desemparejado correctamente";
		Integer id_cmp=component.getId_cmp();
		try {
			componentService.unpairComponent(id_cmp);
		} catch (Exception e) {
			e.printStackTrace();
			feedback="El componente no ha podido ser desemparejado";
		}
		mav.addObject("component", new Component());
		List<Component> allActiveComponents=componentService.getAllActiveComponents();
		if (!allActiveComponents.isEmpty()) {
			mav.addObject("allActiveComponents", allActiveComponents);
		}
		mav.addObject("feedback", feedback);
		return mav;
	}
}
