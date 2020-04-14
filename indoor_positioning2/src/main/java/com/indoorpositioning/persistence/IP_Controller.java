package com.indoorpositioning.persistence;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IP_Controller {

	@GetMapping("/index")
	public String greeting() {

		return "index";
	}

}
