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
import com.indoorpositioning.services.HistoryService;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping("")
public class ShowPositionController {
	@Autowired
	private ComponentService componentService;
	@Autowired
	private BeaconService beaconService;
	@Autowired
	private HistoryService historyService;
	
	@GetMapping("")
    public ModelAndView renderHomePageGet() {
		return renderPageGet_SP2();
    }
	@GetMapping("/showPosition2")
    public ModelAndView renderPageGet_SP2() {
		ModelAndView mav= new ModelAndView("classpath:/templates/showPosition2.html");
		List<Component> allActiveComponents=componentService.getAllActiveComponents();
		mav.addObject("component", new Component());
		if (!allActiveComponents.isEmpty()) {
			mav.addObject("allActiveComponents", allActiveComponents);
		}
        return mav;
    }
	@PostMapping("/showPosition2")
	public ModelAndView renderPagePost_SP2(@ModelAttribute("component") Component component) {
		ModelAndView mav= new ModelAndView("classpath:/templates/showPosition2.html");
		Integer id_bk=component.getBeacon().getId_bk();
		List<Component> allActiveComponents=componentService.getAllActiveComponents();
		if (!allActiveComponents.isEmpty()) {
			mav.addObject("allActiveComponents", allActiveComponents);
		}
		List<Object> returnedValues=historyService.getLocation(id_bk);
		byte[] location=(byte[]) returnedValues.get(0);
		String lastHistoryDate=(String) returnedValues.get(1);
		if(location!=null&&location.length>0) {
			String base64Encoded = Base64.getEncoder().encodeToString(location);
	        mav.addObject("mapImage", "data:image/png;base64,"+base64Encoded);
	        mav.addObject("lastHistoryDate", lastHistoryDate);

		}
		else {
			mav.addObject("errorNotData", "No hay localizaciones recientes para el componente");
		}
		return mav;
		
	}
	
}
