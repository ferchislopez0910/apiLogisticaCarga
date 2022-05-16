package com.tacmo.apilogistica.common;


public class Utils {
	
	public static boolean fechasCorrectasQuery(java.util.Date fechaInicio, java.util.Date fechaFin) {
		if(fechaInicio.compareTo(fechaFin) < 0)
			return true;
		return false;
	}

}
