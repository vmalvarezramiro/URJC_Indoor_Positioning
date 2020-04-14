package com.indoorpositioning.controllers;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/pairComponents")
public class PairComponentsController {
	@Autowired
	private ComponentService componentService;
	@Autowired
	private BeaconService beaconService;



	@GetMapping("")
	public ModelAndView renderPageGet_PC() {
		ModelAndView mav= new ModelAndView("classpath:/templates/pairComponents.html");
		mav.addObject("component", new Component());
		List<Component> allInactiveComponents=componentService.getAllInactiveComponents();
		List<Beacon> allUnusedBeacons=beaconService.getAllUnusedBeacons();
		if (!allInactiveComponents.isEmpty()) {
			mav.addObject("allInactiveComponents", allInactiveComponents);
		}
		if (!allUnusedBeacons.isEmpty()) {
			mav.addObject("allUnusedBeacons", allUnusedBeacons);
		}
		return mav;

	}
	@PostMapping("")
	public ModelAndView renderPagePost_PC(@ModelAttribute("component") Component component) {
		ModelAndView mav= new ModelAndView("classpath:/templates/pairComponents.html");
		mav.addObject("component", new Component());
		String feedback = "The component was succesfully paired";
		Integer id_cmp=component.getId_cmp();
		Integer id_bk=component.getBeacon().getId_bk();
		if (id_cmp==null || id_bk==null) {
			feedback="Please select a component and a beacon to pair";
		}
		try {
			componentService.pairComponent(id_bk,id_cmp);
		} catch (Exception e) {
			e.printStackTrace();
			feedback="The component could not be paired succesfully";
		}
		mav.addObject("component", new Component());
		List<Component> allInactiveComponents=componentService.getAllInactiveComponents();
		List<Beacon> allUnusedBeacons=beaconService.getAllUnusedBeacons();
		if (!allInactiveComponents.isEmpty()) {
			mav.addObject("allInactiveComponents", allInactiveComponents);
		}
		if (!allUnusedBeacons.isEmpty()) {
			mav.addObject("allUnusedBeacons", allUnusedBeacons);
		}

		mav.addObject("feedback", feedback);

		return mav;
	}


}
