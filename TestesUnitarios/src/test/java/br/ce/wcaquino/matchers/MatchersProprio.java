package br.ce.wcaquino.matchers;

import java.util.Calendar;

public class MatchersProprio {
	
	public static DiaSemanaMatcher caiEm(Integer diaSemana) {
		return new DiaSemanaMatcher(diaSemana);
	}
	
	public static DiaSemanaMatcher caiNumaSegunda() {
		return new DiaSemanaMatcher(Calendar.MONDAY);
	}
	
	public static DataDiferencaDiaMatcher ehHoejeComDiferencaDias(Integer dias) {
		return new DataDiferencaDiaMatcher(dias);
	}
	
	public static DataDiferencaDiaMatcher ehHoeje() {
		return new DataDiferencaDiaMatcher(0);
	}

}
