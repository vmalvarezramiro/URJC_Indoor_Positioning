package com.indoorpositioning.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.indoorpositioning.MapVariables;
import com.indoorpositioning.persistence.Receiver;
import com.indoorpositioning.services.ReceiverList;
import com.indoorpositioning.services.ReceiverService;

import java.util.List;
import javax.validation.Valid;
@RestController
@RequestMapping("/manageReceivers")
public class ManageReceiversController {
	@Autowired
	private ReceiverService receiverService;
	
	@GetMapping("")
	public ModelAndView renderPageGet_MR() {
		ModelAndView mav= new ModelAndView("classpath:/templates/manageReceivers.html");
		List<Receiver> receivers=receiverService.getReceivers();
		ReceiverList allReceivers=new ReceiverList();
		allReceivers.setReceivers(receivers);
		mav.addObject("allReceivers",allReceivers);
		return mav;
	}
	@PostMapping("")
	public ModelAndView renderPagePost_MR(@ModelAttribute("allReceivers") @Valid ReceiverList updatedReceivers, BindingResult bindingResult) {
		ModelAndView mav= new ModelAndView("classpath:/templates/manageReceivers.html");
		String feedback = "Los cambios se guardaron con exito";
		if (updatedReceivers==null) {
			feedback="No hay ningun receiver";
		}
		//If the form has errors
		if (bindingResult.hasErrors()) {
			feedback="Los valores de los ejex X e Y deben ser enteros";
		}
		else {
			try {
				Integer notUpdated=receiverService.updateAllReceivers(updatedReceivers.getReceivers());
				if(notUpdated>0) {
					feedback+=", pero el/los "+notUpdated+" receiver(s) "
							+ "no pueden ser actualizados correctamente, recuerda que el valor maximo "
							+ "del eje X es '"+MapVariables.pointX_max.intValue()+"' "
							+ "y el valor maximo del eje Y es '"+MapVariables.pointY_max.intValue()+"'";
				}
			} catch (Exception e) {
				e.printStackTrace();
				feedback="Los cambios no se han guardado con exito";
			}
		}
		List<Receiver> receivers=receiverService.getReceivers();
		ReceiverList allReceivers=new ReceiverList();
		allReceivers.setReceivers(receivers);
		mav.addObject("feedback",feedback);
		return mav;
	}
}