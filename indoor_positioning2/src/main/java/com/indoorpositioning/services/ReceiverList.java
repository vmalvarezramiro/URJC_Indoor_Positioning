package com.indoorpositioning.services;

import java.util.List;

import com.indoorpositioning.persistence.Receiver;


public class ReceiverList {
	
	
	private List<Receiver> receivers;
	
    public ReceiverList() {}
	public ReceiverList(List<Receiver> receivers) {
		this.receivers = receivers;
	}
	@Override
    public String toString() {
		String s="Receivers [";
		for (int i = 0; i < this.getReceivers().size(); i++) {
			s+="[";
			s+=this.getReceivers().get(i).toString();
			s+="]";
			//If it is not the last beacon
			if (i+1<this.getReceivers().size()) {
				s+=",";
			}
		}
		s+="]";
        return s;
    }
	public List<Receiver> getReceivers() {
		return receivers;
	}
	public void setReceivers(List<Receiver> receivers) {
		this.receivers = receivers;
	}
	
}
