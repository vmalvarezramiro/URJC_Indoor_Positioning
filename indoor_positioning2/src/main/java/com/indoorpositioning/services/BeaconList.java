package com.indoorpositioning.services;

import java.util.List;

import com.indoorpositioning.persistence.Beacon;


public class BeaconList {
	
	
	private List<Beacon> beacons;
	
    public BeaconList() {}
	public BeaconList(List<Beacon> beacons) {
		this.beacons = beacons;
	}
	@Override
    public String toString() {
		String s = "Beacons [";
		for (int i = 0; i < this.getBeacons().size(); i++) {
			s += "[";
			s += this.getBeacons().get(i).toString();
			s += "]";
			//Si no es el ultimo beacon
			if (i+1 < this.getBeacons().size()) {
				s += ",";
			}
		}
		s += "]";
        return s;
    }
	public List<Beacon> getBeacons() {
		return beacons;
	}
	public void setBeacons(List<Beacon> beacons) {
		this.beacons = beacons;
	}
}
