package com.indoorpositioning.services;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.indoorpositioning.ResourceNotFoundException;
import com.indoorpositioning.persistence.Beacon;
import com.indoorpositioning.persistence.Component;
import com.indoorpositioning.persistence.ComponentRepository;


@Service
public class ComponentService {
	@Resource
	private ComponentRepository componentRepository;
	
	public Component getComponentById(Integer id) {
		return componentRepository.findById(id)
	    .orElseThrow(() -> new ResourceNotFoundException("Component", "id", id));
	}
	public List<Component> getComponents(){
		return componentRepository.findAll();
	}
	
	public Component updateComponent(Integer id, Component componentData) {
		Component c = componentRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Component", "id", id));
	    //c.setBattery(historyData.getBattery());
	    Component updatedC = componentRepository.save(c);
	    return updatedC;
	}
	public List<Component> getAllComponents(){
		return componentRepository.getAllComponents();
	}
	public List<Component> getAllActiveComponents(){
		return componentRepository.getAllActiveComponents();
	}
	public List<Component> getAllInactiveComponents() {
		
		return componentRepository.getAllInactiveComponents();
	}
	public void saveComponent(Component c) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
		Date date =  calendar.getTime();
		c.setS_date(date);
		//Si la opcion "No beacon" se ha seleccionado
		if (c.getBeacon()==null) {
			c.setS_date(null);
			c.setBeacon(new Beacon(null,0,0));
		}
		componentRepository.save(c);
	}
	public void pairComponent(Integer id_bk,Integer id_cmp) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
		Date date =  calendar.getTime();
		componentRepository.pairComponent(id_bk,id_cmp,date);
		
	}
	public void unpairComponent(Integer id_cmp) {
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid"));
		Date date =  calendar.getTime();
		componentRepository.unpairComponent(id_cmp,date);
		
	}
	
	

}
