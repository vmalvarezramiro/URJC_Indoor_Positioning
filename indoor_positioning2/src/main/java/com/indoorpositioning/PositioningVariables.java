package com.indoorpositioning;
//Global variables for positioning a beacon

import java.awt.Color;

public class PositioningVariables {
	//Numero minimo de histories
	public static Integer minHistoriesRcv=2;
	public static Integer maxHistoriesRcv=5;
	//Numero minimo de receptores necesitados para calcular la posicion
	public static Integer minRcvs=2;
	//Maximo numero de receptores usados para calcular la posicion
	public static Integer maxRcvs=3;
	public static Integer maxHistoriesDiff_Minutes=30;
	
	public static Integer dateDiffRange1_Hours=24;
	public static Integer dateDiffRange2_Hours=72;
	
	public static Color range1Color=Color.green;
	public static Color range2Color=Color.orange;
	public static Color range3Color=Color.red;
	
	public static Color usedRcvsColor=Color.blue;
	public static Color unusedRcvsColor=Color.yellow;
	
}