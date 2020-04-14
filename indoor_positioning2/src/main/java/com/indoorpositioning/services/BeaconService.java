package com.indoorpositioning.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.indoorpositioning.persistence.Beacon;
import com.indoorpositioning.persistence.BeaconRepository;


@Service
public class BeaconService {
	@Resource
	private BeaconRepository beaconRepository;
	
	public List<Beacon> getBeacons(){
		//Beacons ordenados
		return beaconRepository.getAllBeacons();
	}
	public List<Beacon> getAllUnusedBeacons(){
		return beaconRepository.getAllUnusedBeacons();
	}
	public void updateAllBeacons(List<Beacon> updatedBeacons) {
		for (int i=0; i<updatedBeacons.size(); i++) {
			Beacon b = updatedBeacons.get(i);
			beaconRepository.updateBeacon(b.getName(), b.getId_bk());
			
		}
	}

}
