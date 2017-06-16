package com.gla.aircare.dao;

import com.gla.aircare.dao.object.Flight;

public class AddFlight {
	public void addNewFlight() {
		Flight flight10 = new Flight("ARN107", 2006, "FX-600", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Paris",
				"Stockholm");
		Flight flight20 = new Flight("ARN108", 2025, "FX-600", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Panama",
				"Cuba");
		Flight flight11 = new Flight("SFO924", 2007, "AX-203", "2017-06-01 07:15:15", "2017-06-01 08:15:15",
				"San Fransisco", "Los Angeles");
		Flight flight21 = new Flight("SFO925", 2028, "AX-203", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Brasilia",
				"Sao Paulo");
		Flight flight12 = new Flight("NCE234", 2008, "GT-655", "2017-06-01 07:15:15", "2017-06-01 08:15:15",
				"Amsterdam", "Nice");
		Flight flight22 = new Flight("NCE235", 2029, "GT-655", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Budapest",
				"Prague");
		Flight flight13 = new Flight("SZF346", 2009, "GQ-102", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Lima",
				"Berlin");
		Flight flight23 = new Flight("SZF347", 2030, "GQ-102", "2017-02-02 07:15:15", "2017-02-02 08:15:15",
				"Copenhague", "Monaco");
		Flight flight14 = new Flight("NRP909", 2019, "TF-705", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Munich",
				"Tokyo");
		Flight flight24 = new Flight("NRP910", 2031, "TF-705", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Pekin",
				"Taiwan");
		Flight flight15 = new Flight("GNT357", 2020, "ZZ-606", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Londres",
				"Grenoble");
		Flight flight25 = new Flight("GNT358", 2032, "ZZ-606", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Nantes",
				"Bordeaux");
		Flight flight16 = new Flight("OSL288", 2021, "HG-589", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Helsinki",
				"Olso");
		Flight flight26 = new Flight("OSL289", 2033, "HG-589", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Madrid",
				"Barcelone");
		Flight flight17 = new Flight("BOS093", 2022, "JK-923", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Miami",
				"Boston");
		Flight flight27 = new Flight("BOS094", 2034, "JK-923", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Alger",
				"Faro");
		Flight flight18 = new Flight("SYD457", 2023, "LM-002", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Canberra",
				"Sydney");
		Flight flight28 = new Flight("SYD458", 2035, "LM-002", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Florence",
				"Rome");
		Flight flight19 = new Flight("DPS859", 2024, "CX-729", "2017-06-01 07:15:15", "2017-06-01 08:15:15",
				"New Delhi", "Bali");
		Flight flight29 = new Flight("DPS860", 2036, "CX-729", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Santorin",
				"Istanbul");

		Dao.getFlightDao().addFlight(flight10);
		Dao.getFlightDao().addFlight(flight11);
		Dao.getFlightDao().addFlight(flight12);
		Dao.getFlightDao().addFlight(flight13);
		Dao.getFlightDao().addFlight(flight14);
		Dao.getFlightDao().addFlight(flight15);
		Dao.getFlightDao().addFlight(flight16);
		Dao.getFlightDao().addFlight(flight17);
		Dao.getFlightDao().addFlight(flight18);
		Dao.getFlightDao().addFlight(flight19);
		Dao.getFlightDao().addFlight(flight20);
		Dao.getFlightDao().addFlight(flight21);
		Dao.getFlightDao().addFlight(flight22);
		Dao.getFlightDao().addFlight(flight23);
		Dao.getFlightDao().addFlight(flight24);
		Dao.getFlightDao().addFlight(flight25);
		Dao.getFlightDao().addFlight(flight26);
		Dao.getFlightDao().addFlight(flight27);
		Dao.getFlightDao().addFlight(flight28);
		Dao.getFlightDao().addFlight(flight29);
	}

}
