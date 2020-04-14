package com.indoorpositioning.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.indoorpositioning.persistence.Beacon;
import com.indoorpositioning.persistence.Component;
import com.indoorpositioning.services.BeaconService;
import com.indoorpositioning.services.ComponentService;

import java.util.List;

@RestController
@RequestMapping("/")
public class AddComponentsController {
	@Autowired
	private ComponentService componentService;
	@Autowired
	private BeaconService beaconService;

	@GetMapping("/addComponents")
	public ModelAndView renderPageGet_AC() {
		ModelAndView mav= new ModelAndView("classpath:/templates/addComponents.html");
		mav.addObject("component", new Component());
		List<Beacon> allUnusedBeacons=beaconService.getAllUnusedBeacons();
		if (!allUnusedBeacons.isEmpty()) {
			mav.addObject("allUnusedBeacons", allUnusedBeacons);
		}
		return mav;
	}

	@PostMapping("/addComponents")
	public ModelAndView renderPagePost_AC(@ModelAttribute("component") Component component) {
		ModelAndView mav= new ModelAndView("classpath:/templates/addComponents.html");
		String feedback = "El componente '"+component.getName()+"' se añadio correctamente";
		if (component.getName()==null||component.getName()=="") {
			feedback="Por favor asigna un nombre para el componente";
		}
		try {
			componentService.saveComponent(component);
		} catch (InvalidDataAccessApiUsageException e) {
		}
		catch (Exception e) {
			//When hibernate throws the exception because of the violation of some constraint
			feedback= "El componente no fue guardado con exito, el componente debe tener un nombre unico y beacon";
			
		}
		List<Beacon> allUnusedBeacons=beaconService.getAllUnusedBeacons();
		mav.addObject("feedback", feedback);
		mav.addObject("allUnusedBeacons", allUnusedBeacons);
		mav.addObject("component", new Component());
		return mav;

	}	

}
