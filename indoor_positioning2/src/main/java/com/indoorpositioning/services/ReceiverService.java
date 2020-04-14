package com.indoorpositioning.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;

import com.indoorpositioning.MapVariables;
import com.indoorpositioning.persistence.Receiver;
import com.indoorpositioning.persistence.ReceiverRepository;


@Service
public class ReceiverService {
	@Resource
	private ReceiverRepository receiverRepository;
	
	public List<Receiver> getReceivers(){
		// Receptores en orden ascendente (id_rcv)
		return receiverRepository.getReceivers();
	}

	public Integer updateAllReceivers(List<Receiver> updatedReceivers) {
		// Para dar al usuario algunos comentarios sobre los receptores no actualizados debido a puntos de eje x o y incorrectos
		Integer notUpdated=0;
		for (int i = 0; i < updatedReceivers.size(); i++) {
			Receiver r=updatedReceivers.get(i);
			if(r.getX_pos()>=0 && r.getX_pos()<=MapVariables.pointX_max&&(r.getY_pos()>=0 && r.getY_pos()<=MapVariables.pointY_max)) {
				receiverRepository.updateReceiver(r.getId_rcv(),r.getX_pos().intValue(),r.getY_pos().intValue());
			}
			else {
				notUpdated++;
			}
		}
		return notUpdated;
	}

	
	
	
}
