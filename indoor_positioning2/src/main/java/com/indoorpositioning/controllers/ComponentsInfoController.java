package com.indoorpositioning.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.indoorpositioning.persistence.Component;
import com.indoorpositioning.services.ComponentService;

import java.util.List;

@RestController




@RequestMapping("/componentsInfo")
public class ComponentsInfoController {
	@Autowired
	private ComponentService componentService;
	
	
	
	@GetMapping("")
	public ModelAndView renderPageGet_CI() {
		ModelAndView mav= new ModelAndView("classpath:/templates/componentsInfo.html");
		List<Component> components=componentService.getAllComponents();
		mav.addObject("componentsTableData",components);
		return mav;
	}	
}
