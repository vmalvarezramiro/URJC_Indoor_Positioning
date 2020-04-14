package com.indoorpositioning.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.indoorpositioning.PositioningVariables;
import com.indoorpositioning.persistence.History;
import com.indoorpositioning.persistence.HistoryRepository;
import com.indoorpositioning.persistence.Receiver;
import com.indoorpositioning.persistence.ReceiverRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.awt.Color;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class HistoryService {
	@Resource
	private HistoryRepository historyRepository;
	@Resource
	private ReceiverRepository receiverRepository;

	
	public List<Object> getLocation(Integer id_bk){
		
		//Pageable limita el numero de filas devueltos por la bd
		Pageable pagRcvHistories = PageRequest.of(0,PositioningVariables.maxHistoriesRcv);
		//Obtener todos los receivers
		List<Receiver> rcvList = receiverRepository.findAll();
		//Esta lista contiene el historial conocido de las n ultimas n(pageable) historias de cada receptor
		List<History> historyList = new ArrayList<>();
		Integer id_rcv;
		//Esta lista contendra  This list will contain the last n (pageable) histories of a receiver
		List<History> lastHistories=new ArrayList<>();
		
		Integer n=0;
		Pageable pagLastNHistory;
		//Adaptamos el formato de las fechas
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		History lastHistory;
		Date lastHistoryDate;
		Date dateLimit;
		String lastHistoryDateStr;
		String dateLimitStr;
		while (historyList.isEmpty()) {
			//Obtener la n-th history ordenada por fecha descendiente
			pagLastNHistory=PageRequest.of(n, 1);
			
			try {
				lastHistory=historyRepository.getLastHistoryOfBk(id_bk,pagLastNHistory).get(0);
				System.out.println("Ultima hist: "+lastHistory.getDate());
			} catch (NullPointerException e) {
				e.printStackTrace();
				//No hay mas histories para el beacon
				return null;
			}
			
			lastHistoryDate=lastHistory.getDate();
			//lastHistory date -maxHistoriesDiff_Minutes minutes
			dateLimit=subtractMinToDate(lastHistoryDate, PositioningVariables.maxHistoriesDiff_Minutes);
			lastHistoryDateStr=dateFormat.format(lastHistoryDate);
			dateLimitStr=dateFormat.format(dateLimit);
			System.out.println("lhd: "+lastHistoryDateStr);
			System.out.println("dlim: "+dateLimitStr);
			

			

			//Bucle a traves de todos los rcvs
			for (int i = 0; i < rcvList.size(); i++) {
				id_rcv = rcvList.get(i).getId_rcv();
				System.out.println("RCV: "+id_rcv);
				lastHistories = historyRepository.getLastHistoriesOfBkInRcv(id_bk, id_rcv,dateLimitStr,lastHistoryDateStr, pagRcvHistories);
				System.out.println("Ultims hist: "+lastHistories.size());
				//Si hay histories del receiver para el beacon seleccionado calcula la ubicacion conocida rssi
				if (lastHistories.size() >= PositioningVariables.minHistoriesRcv) {
					History meanHistory = calculateRSSIMean(lastHistories);
					historyList.add(meanHistory);
					System.out.println("mean: "+meanHistory.getId_history()+" "+meanHistory.getRssi()+" "+meanHistory.getDate());
				}
			}
			if (historyList.size()<PositioningVariables.minRcvs) {
				System.out.println("EMPTY");
				System.out.println("SIZE: "+historyList.size());
				System.out.println("MINSIZE: "+PositioningVariables.minRcvs);
				//Vacia el historyList array por que no hay demasiados receivers para el calculo
				historyList.clear();
				//Para obtener el siguientes last history
				n++;
			}
			
		}

		//Ordena la lista por rssi: rssi1>rss2>....
		historyList.sort((o1, o2) -> o2.getRssi().compareTo(o1.getRssi()));
		
		//Crea una nueva lista con el primer (best) n receivers (maxRcvs)
		List<History> historySubList = new ArrayList<>();
		//Si hay menos receivers que el maximo permitido para hacer el calculo entonces obtener todos
		if (historyList.size() < PositioningVariables.maxRcvs) {
			List<Receiver> unusedReceivers=getUnusedReceivers(historyList, rcvList);
			return getRcvPositionsAndDistances(historyList,unusedReceivers);
		}
		//If there are more receivers than the maximum permitted to make the calculation then get the first (best) n receivers (maxRcvs)
		//Si hay mas receivers que el maximo permitido para hacer el calculo entonces obtener el primer (best) n receivers (maxRcvs)
		else {
			for (int i=0; i<PositioningVariables.maxRcvs; i++) {
				historySubList.add(historyList.get(i));
			}
			List<Receiver> unusedReceivers=getUnusedReceivers(historySubList, rcvList);
			return getRcvPositionsAndDistances(historySubList,unusedReceivers);
		}
	}
	private Date subtractMinToDate(Date d1,Integer minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(d1);
		cal.add(Calendar.MINUTE, -minutes);
		return cal.getTime();
	}
	private List<Receiver> getUnusedReceivers(List<History> usedHistories,List<Receiver> rcvList){
		List<Receiver> unusedReceivers=new ArrayList<>();
		Boolean rcvUsed;
		for (int i = 0; i < rcvList.size(); i++) {
			rcvUsed=false;
			for (History history : usedHistories) {
				if (history.getReceiver().getId_rcv()==rcvList.get(i).getId_rcv()) {
					rcvUsed=true;
					break;
				}
			}
			if (!rcvUsed) {
				unusedReceivers.add(rcvList.get(i));
			}
		}
		return unusedReceivers;
		
	}
	//Crea una history con el rssi conocido del ultimo n (especificado by pageable variable) histories
	private History calculateRSSIMean(List<History> lastHistories) {
		Float calc = (float) 0.0;
		//Getting the last history as reference
		//Obtener la ultima historia como referencia
		History history = lastHistories.get(0);
		for (int i=0; i<lastHistories.size(); i++) {
			calc += lastHistories.get(i).getRssi();
		}
		Integer meanRSSI = Math.round(calc / lastHistories.size());
		history.setRssi(meanRSSI);
		
		return history;
	}
	

	// Genera una lista 'x_y_distance' con la posición x e y del receptor y la distancia (basada en el rssi) de cada historial seleccionado (listo para evaluar)
	private List<Object> getRcvPositionsAndDistances(List<History> historySubList, List<Receiver> unusedReceivers){
		// Fecha de la historia más reciente
		String usedLastHistoryDate = getLastHistoryDate(historySubList);
		History h;
		Double distance = 0.0;
		// Simula una lista de x_pos, y_pos y tripletas de distancia
		List<Double> x_y_distance = new ArrayList<>();
		for (int i=0; i<historySubList.size(); i++) {
			h = historySubList.get(i);
			distance = rssiFormula((double)h.getRssi());
			x_y_distance.add((double)h.getReceiver().getX_pos());
			x_y_distance.add((double)h.getReceiver().getY_pos());
			x_y_distance.add(distance);
		}
		return calculatePosition(x_y_distance,unusedReceivers,usedLastHistoryDate);
	}
	private String getLastHistoryDate(List<History> historySubList) {
		String lastHistoryDate=null;
		String historyDate=null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (History history : historySubList) {
			historyDate=dateFormat.format(history.getDate());
			// Si es el primer historial que se evalúa o la fecha guardada es anterior a la evaluada
			//A.compareTo(B): 0 si son iguales, 1 si A> B y -1 si A <B
			if (lastHistoryDate==null||lastHistoryDate.compareTo(historyDate)==-1) {
				lastHistoryDate=historyDate;
			}
		}
		return lastHistoryDate;
	}
	// Para calcular la diferencia horaria entre la fecha del historial y ahora
	private Integer calculateDayDiff(String lastHistoryDateStr) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now=new Date();
		Date lastHistoryDate=null;
		try {
			lastHistoryDate = dateFormat.parse(lastHistoryDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// Fecha de diferencia en milisegundos
		long diff_millis = now.getTime() - lastHistoryDate.getTime();
		
		Integer diff_hours=(int) (diff_millis / (1000 * 60 * 60));
		
		return diff_hours;
	}
	// Para determinar el color de la baliza dependiendo de cuándo llegó el último historial
	private Color calculateBeaconColor(Integer diff_hours) {
		Color c=null;
		if (diff_hours<PositioningVariables.dateDiffRange1_Hours) {
			c=PositioningVariables.range1Color;
		}
		else if (diff_hours>=PositioningVariables.dateDiffRange1_Hours&&diff_hours<PositioningVariables.dateDiffRange2_Hours) {
			c=PositioningVariables.range2Color;
		}
		else {
			c=PositioningVariables.range3Color;
		}
		return c;
	}
	// Para calcular la distancia en función del valor rssi
	private static Double rssiFormula(Double rssi) {
		Double n = 1.7;
		Double rssiFactor = -55.0;
		Double raise = (rssiFactor-rssi) / (10*n);
		Double result = Math.pow(10, raise);
		
		return result;
	}
	
	// Itera a través de la lista 'x_y_distance' para calcular la posición x e y de la baliza
	private List<Object> calculatePosition(List<Double> x_y_distance,List<Receiver> unusedReceivers,String usedLastHistoryDate){
		Double sumFactors = 0.0;
		Double x_pos = 0.0;
		Double y_pos = 0.0;
		// Iterar a través de la lista para obtener los sumFactors
		for (int i=0; i<x_y_distance.size(); i=i+3) {
			Double distance = x_y_distance.get(i+2);
			sumFactors += (1/distance);
		}
		for (int j=0; j<x_y_distance.size(); j=j+3) {
			Double x = x_y_distance.get(j);
			Double y = x_y_distance.get(j+1);
			Double distance = x_y_distance.get(j+2);
			//Factor de multiplicación
			Double f = (1/distance) / sumFactors;
			x_pos += x*f;
			y_pos += y*f;
		}
		List<Receiver> usedReceiverData = new ArrayList<>();
		Receiver r;
		for (int i=0; i<x_y_distance.size(); i=i+3) {
			// Objeto receptor temporal para pintarlo en el mapa
			r = new Receiver(0, (int)Math.round(x_y_distance.get(i)),(int)Math.round(x_y_distance.get(i+1)));
			usedReceiverData.add(r);
		}
		if(x_pos != null && y_pos != null) {
			// Redondeando la posición
			Integer pointX = (int) Math.round(x_pos);
			Integer pointY = (int) Math.round(y_pos);
			// Determinar la diferencia de fecha y el color relacionado
			Integer diff_hours=calculateDayDiff(usedLastHistoryDate);
			Color color=calculateBeaconColor(diff_hours);
			LocationPainter lp = new LocationPainter();
			try {
				
				// Pinta la ubicación de la baliza y todos los receptores (incluso los que no se utilizan en el cálculo) en el mapa / imagen
				byte [] bi = lp.createImageWithLocation("src/main/resources/static/images/plano-airbus.png", pointX, pointY, usedReceiverData,unusedReceivers,color);
				// Para devolver ambas informaciones: el mapa / ubicación pintada y la fecha del historial más reciente utilizado
				List<Object> response=new ArrayList<>();
				response.add(bi);
				response.add(usedLastHistoryDate);
				return response;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return null;		
	}
	
}
