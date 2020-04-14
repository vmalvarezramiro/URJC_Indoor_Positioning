package com.indoorpositioning.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.indoorpositioning.persistence.Beacon;
import com.indoorpositioning.services.BeaconList;
import com.indoorpositioning.services.BeaconService;

import java.util.List;

@RestController
@RequestMapping("/nameBeacons")
public class NameBeaconsController {
	@Autowired
	private BeaconService beaconService;

	@GetMapping("")
	public ModelAndView renderPageGet_NB() {
		ModelAndView mav= new ModelAndView("classpath:/templates/nameBeacons.html");
		List<Beacon> allBeacons=beaconService.getBeacons();
		BeaconList bc=new BeaconList();
		bc.setBeacons(allBeacons);
		mav.addObject("allBeacons",bc);
		return mav;
	}
	@PostMapping("")
	public ModelAndView renderPagePost_NB(@ModelAttribute("allBeacons") BeaconList updatedBeacons) {
		ModelAndView mav= new ModelAndView("classpath:/templates/nameBeacons.html");
		String feedback = "Los cambios se realizaron con exito";
		if (updatedBeacons==null) {
			feedback="No hay ningun beacon";
		}
		try {
			beaconService.updateAllBeacons(updatedBeacons.getBeacons());
		} catch (Exception e) {
			feedback="No se han guardado los cambios con exito";
		}
		List<Beacon> allBeacons=beaconService.getBeacons();
		BeaconList bc=new BeaconList();
		bc.setBeacons(allBeacons);
		mav.addObject("allBeacons", bc);
		//mav.addObject("beaconsTableData",allBeacons);
		mav.addObject("feedback",feedback);
		return mav;
	}
}
