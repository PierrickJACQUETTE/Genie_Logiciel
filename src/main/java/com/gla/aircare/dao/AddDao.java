package com.gla.aircare.dao;

import com.gla.aircare.dao.object.Flight;
import com.gla.aircare.dao.object.MRO;
import com.gla.aircare.dao.object.Maintenance;
import com.gla.aircare.dao.object.Plane;
import com.gla.aircare.dao.object.Task;
import com.gla.aircare.dao.object.User;

public class AddDao {

	public AddDao() {
		addPlane();
		addFlight();
		addMaintenance();
		addMRO();
		addTask();
		addUser();
	}

	public void addPlane() {
		Plane plane = new Plane("A380", "2016-12-23 01:15:15", "FX-600", false);
		Plane plane1 = new Plane("A230", "2017-04-23 01:15:15", "AX-203", true);
		Plane plane2 = new Plane("A460", "2017-05-23 01:15:15", "GT-655", false);
		Plane plane3 = new Plane("A960", "2017-05-23 01:15:15", "GQ-102", true);
		Plane plane4 = new Plane("A237", "2017-03-23 01:15:15", "TF-705", false);
		Plane plane5 = new Plane("A381", "2017-02-23 01:15:15", "ZZ-606", true);
		Plane plane6 = new Plane("A211", "2017-01-23 01:15:15", "HG-589", false);
		Plane plane7 = new Plane("A221", "2017-02-23 01:15:15", "JK-923", true);
		Plane plane8 = new Plane("A380", "2017-03-23 01:15:15", "LM-002", false);
		Plane plane9 = new Plane("A179", "2017-04-23 01:15:15", "CX-729", true);
		Plane plane10 = new Plane("B79", "2017-04-23 01:15:15", "TX-729", true);
		Plane plane11 = new Plane("A380", "2017-03-23 01:15:15", "LM-004", false);
		Dao.getPlaneDao().addPlane(plane);
		Dao.getPlaneDao().addPlane(plane1);
		Dao.getPlaneDao().addPlane(plane2);
		Dao.getPlaneDao().addPlane(plane3);
		Dao.getPlaneDao().addPlane(plane4);
		Dao.getPlaneDao().addPlane(plane5);
		Dao.getPlaneDao().addPlane(plane6);
		Dao.getPlaneDao().addPlane(plane7);
		Dao.getPlaneDao().addPlane(plane8);
		Dao.getPlaneDao().addPlane(plane9);
		Dao.getPlaneDao().addPlane(plane10);
		Dao.getPlaneDao().addPlane(plane11);
	}

	public void addTask() {
		Task task = new Task("200127-01-1", "A380", 127, "ceci est une description", 22000, 140, true, 0.36, true);
		Task task1 = new Task("212100-01-1", "A230", 131, "ceci est une description", 18000, 100, false, 0.40, false);
		Task task2 = new Task("212300-02-1", "A380", 200, "ceci est une description", 7000, 60, false, 0.50, false);
		Task task3 = new Task("237300-08-1", "A380", 210, "ceci est une description", 9000, 80, true, 1, true);
		Task task4 = new Task("773200-C1-1", "A230", 280, "ceci est une description", 12000, 90, false, 2.36, false);
		Task task5 = new Task("240000-01-1", "A230", 574, "ceci est une description", 1000, 60, true, 2.20, true);
		Task task6 = new Task("792000-P2-1", "A380", 430, "ceci est une description", 3000, 20, true, 2.10, true);
		Task task7 = new Task("801110-C3-1", "A380", 430, "ceci est une description", 1200, 12, true, 0.68, true);
		Task task8 = new Task("501145-C8-4", "A960", 504, "ceci est une description", 2, 3, true, 0.45, true);
		Task task9 = new Task("501145-C8-4", "A380", 504, "ceci est une description", 2, 3, true, 0.45, true);
		Dao.getTaskDao().addTask(task);
		Dao.getTaskDao().addTask(task1);
		Dao.getTaskDao().addTask(task2);
		Dao.getTaskDao().addTask(task3);
		Dao.getTaskDao().addTask(task4);
		Dao.getTaskDao().addTask(task5);
		Dao.getTaskDao().addTask(task6);
		Dao.getTaskDao().addTask(task7);
		Dao.getTaskDao().addTask(task8);
		Dao.getTaskDao().addTask(task9);
	}

	public void addFlight() {
		Flight flight = new Flight("ARN106", 2000, "FX-600", "2017-04-23 01:15:15", "2017-04-23 06:15:15", "Stockholm",
				"Paris");
		Flight flight1 = new Flight("SFO923", 2001, "AX-203", "2017-04-23 01:15:15", "2017-04-23 06:15:15",
				"Los Angeles", "San Francisco");
		Flight flight2 = new Flight("NCE233", 2002, "GT-655", "2017-04-23 01:15:15", "2017-04-23 06:15:15", "Nice",
				"Amsterdam");
		Flight flight3 = new Flight("SXF345", 2003, "GQ-102", "2017-04-23 01:15:15", "2017-04-23 06:15:15", "Berlin",
				"Lima");
		Flight flight4 = new Flight("NRT908", 2004, "TF-705", "2017-04-23 01:15:15", "2017-04-23 06:15:15", "Tokyo",
				"Munich");
		Flight flight5 = new Flight("GNB356", 2005, "ZZ-606", "2017-04-23 01:15:15", "2017-04-23 06:15:15", "Grenoble",
				"Londres");
		Flight flight6 = new Flight("OSL287", 2005, "HG-589", "2017-04-23 01:15:15", "2017-04-23 06:15:15", "Oslo",
				"Helsinki");
		Flight flight7 = new Flight("BOS092", 2005, "JK-923", "2017-04-23 01:15:15", "2017-04-23 06:15:15", "Boston",
				"Miami");
		Flight flight8 = new Flight("SYD456", 2005, "LM-002", "2017-04-23 01:15:15", "2017-04-23 06:15:15", "Sydney",
				"Canberra");
		Flight flight9 = new Flight("DPS858", 2005, "CX-729", "2017-04-23 01:15:15", "2017-04-23 06:15:15", "Bali",
				"New Delhi");
		Flight flight10 = new Flight("RAM307", 2006, "CX-729", "2017-06-01 07:30:00", "2017-06-01 09:20:00",
				"Amsterdam", "Paris");
		Flight flight11 = new Flight("RAM308", 2006, "CX-729", "2017-06-01 14:30:00", "2017-06-01 18:30:00", "Paris",
				"Agadir");
		Flight flight12 = new Flight("AF192", 2007, "GQ-102", "2017-06-01 09:00:00", "2017-06-01 17:20:00", "Paris",
				"Montréal");
		Flight flight13 = new Flight("AF177", 2007, "FX-600", "2017-06-01 19:00:00", "2017-06-02 07:20:00",
				"Los Angeles", "Berlin");
		Flight flight14 = new Flight("AF007", 2007, "AX-203", "2017-06-01 06:00:00", "2017-06-01 17:20:00", "Pekin",
				"Moscou");
		Dao.getFlightDao().addFlight(flight);
		Dao.getFlightDao().addFlight(flight1);
		Dao.getFlightDao().addFlight(flight2);
		Dao.getFlightDao().addFlight(flight3);
		Dao.getFlightDao().addFlight(flight4);
		Dao.getFlightDao().addFlight(flight5);
		Dao.getFlightDao().addFlight(flight6);
		Dao.getFlightDao().addFlight(flight7);
		Dao.getFlightDao().addFlight(flight8);
		Dao.getFlightDao().addFlight(flight9);
		Dao.getFlightDao().addFlight(flight10);
		Dao.getFlightDao().addFlight(flight11);
		Dao.getFlightDao().addFlight(flight12);
		Dao.getFlightDao().addFlight(flight13);
		Dao.getFlightDao().addFlight(flight14);
		/*Flight flight10 = new Flight("ARN107", 2006, "FX-600", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Paris",
				"Stockholm");*/
		Flight flight20 = new Flight("ARN108", 2025, "FX-600", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Panama",
				"Cuba");
		/*Flight flight11 = new Flight("SFO924", 2007, "AX-203", "2017-06-01 07:15:15", "2017-06-01 08:15:15",
				"San Fransisco", "Los Angeles");*/
		Flight flight21 = new Flight("SFO925", 2028, "AX-203", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Brasilia",
				"Sao Paulo");
		/*Flight flight12 = new Flight("NCE234", 2008, "GT-655", "2017-06-01 07:15:15", "2017-06-01 08:15:15",
				"Amsterdam", "Nice");*/
		Flight flight22 = new Flight("NCE235", 2029, "GT-655", "2017-02-02 07:15:15", "2017-02-02 08:15:15", "Budapest",
				"Prague");
		/*Flight flight13 = new Flight("SZF346", 2009, "GQ-102", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Lima",
				"Berlin");*/
		Flight flight23 = new Flight("SZF347", 2030, "GQ-102", "2017-02-02 07:15:15", "2017-02-02 08:15:15",
				"Copenhague", "Monaco");
		/*Flight flight14 = new Flight("NRP909", 2019, "TF-705", "2017-06-01 07:15:15", "2017-06-01 08:15:15", "Munich",
				"Tokyo");*/
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

		/*Dao.getFlightDao().addFlight(flight10);
		Dao.getFlightDao().addFlight(flight11);
		Dao.getFlightDao().addFlight(flight12);
		Dao.getFlightDao().addFlight(flight13);
		Dao.getFlightDao().addFlight(flight14);
		*/Dao.getFlightDao().addFlight(flight15);
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

	// Maintenance(idMaintenance, idPlane, idMRO, idTask, taskDone,
	// idMaintenanceElastic, done)
	public void addMaintenance() {
		Maintenance maintenance = new Maintenance("500", "FX-600", "1000", "200127-01-1", "2015-12-11 10:09", "1",
				false);
		Maintenance maintenance1 = new Maintenance("501", "AX-203", "1001", "212100-01-1", "2018-10-05 10:27", "2",
				false);
		Maintenance maintenance2 = new Maintenance("502", "GT-655", "1000", "212300-02-1", "2016-07-11 11:17", "3",
				false);
		Maintenance maintenance3 = new Maintenance("503", "GQ-102", "1006", "237300-08-1", "2018-03-11 12:35", "4",
				false);
		Maintenance maintenance4 = new Maintenance("504", "TF-705", "1000", "240000-01-1", "2017-05-11 13:30", "5",
				true);
		Maintenance maintenance5 = new Maintenance("505", "ZZ-606", "1000", "773200-C1-1", "2017-04-11 14:46", "6",
				false);
		Maintenance maintenance6 = new Maintenance("506", "HG-589", "1007", "200127-01-1", "2017-07-11 15:54", "7",
				false);
		Maintenance maintenance7 = new Maintenance("507", "JK-923", "1000", "212100-01-1", "2017-04-11 16:42", "8",
				false);
		Maintenance maintenance8 = new Maintenance("508", "LM-002", "1008", "212300-02-1", "2017-05-11 17:40", "9",
				false);
		Maintenance maintenance9 = new Maintenance("509", "CX-729", "1000", "237300-08-1", "2017-05-11 18:41", "10",
				false);
		Maintenance maintenance10 = new Maintenance("510", "FR-789", "1000", "240000-01-1", "2017-05-11 18:41", "11",
				true);
		Maintenance maintenance11 = new Maintenance("511", "BL-909", "1000", "773200-C1-1", "2017-05-11 19:41", "12",
				false);
		Maintenance maintenance12 = new Maintenance("512", "NL-003", "1000", "212300-02-1", "2017-05-11 20:41", "13",
				false);
		Maintenance maintenance13 = new Maintenance("513", "KH-929", "1000", "237300-08-1", "2017-05-11 21:41", "14",
				false);
		Maintenance maintenance14 = new Maintenance("500", "FX-600", "1000", "212100-01-1", "2015-12-11 10:09", "15",
				false);
		Maintenance maintenance15 = new Maintenance("500", "FX-600", "1000", "212300-02-1", "2015-12-11 10:09", "16",
				false);
		Maintenance maintenance16 = new Maintenance("517", "FX-600", "1004", "212300-02-1", "2015-12-22 10:09", "17",
				false);
		Maintenance maintenance17 = new Maintenance("517", "AX-203", "1007", "792000-P2-1", "2015-07-11 10:27", "18",
				false);
		Maintenance maintenance18 = new Maintenance("517", "AX-203", "1007", "801110-C3-1", "2015-07-11 10:27", "19",
				false);
		Maintenance maintenance19 = new Maintenance("517", "AX-203", "1007", "801110-C3-1", "2017-06-03 13:27", "20",
				false);
		Maintenance maintenance20 = new Maintenance("520", "CX-729", "1000", "200127-01-1", "2017-06-01 10:00", "21",
				false);
		Maintenance maintenance21 = new Maintenance("521", "GQ-102", "1011", "240000-01-1", "2017-06-01 19:00", "22",
				false);
		Maintenance maintenance22 = new Maintenance("514", "FX-600", "1001", "773200-C1-1", "2017-06-01 06:00", "23",
				false);
		Maintenance maintenance23 = new Maintenance("519", "GQ-102", "1000", "501145-C8-4", "2017-05-30 08:00", "24",
				false);
		Maintenance maintenance24 = new Maintenance("518", "GQ-102", "1000", "501145-C8-4", "2017-06-08 18:00", "25",
				false);
		Maintenance maintenance25 = new Maintenance("516", "CX-729", "1000", "200127-01-1", "2017-06-08 18:00", "26",
				false);
		Maintenance maintenance26 = new Maintenance("515", "AX-203", "1000", "212100-01-1", "2017-06-08 18:00", "27",
				false);
		Maintenance maintenance27 = new Maintenance("522", "FX-600", "1000", "501145-C8-4", "2017-06-08 18:00", "28",
				false);
		Dao.getMaintenanceDao().addMaintenance(maintenance);
		Dao.getMaintenanceDao().addMaintenance(maintenance1);
		Dao.getMaintenanceDao().addMaintenance(maintenance2);
		Dao.getMaintenanceDao().addMaintenance(maintenance3);
		Dao.getMaintenanceDao().addMaintenance(maintenance4);
		Dao.getMaintenanceDao().addMaintenance(maintenance5);
		Dao.getMaintenanceDao().addMaintenance(maintenance6);
		Dao.getMaintenanceDao().addMaintenance(maintenance7);
		Dao.getMaintenanceDao().addMaintenance(maintenance8);
		Dao.getMaintenanceDao().addMaintenance(maintenance9);
		Dao.getMaintenanceDao().addMaintenance(maintenance10);
		Dao.getMaintenanceDao().addMaintenance(maintenance11);
		Dao.getMaintenanceDao().addMaintenance(maintenance12);
		Dao.getMaintenanceDao().addMaintenance(maintenance13);
		Dao.getMaintenanceDao().addMaintenance(maintenance14);
		Dao.getMaintenanceDao().addMaintenance(maintenance15);
		Dao.getMaintenanceDao().addMaintenance(maintenance16);
		Dao.getMaintenanceDao().addMaintenance(maintenance17);
		Dao.getMaintenanceDao().addMaintenance(maintenance18);
		Dao.getMaintenanceDao().addMaintenance(maintenance19);
		Dao.getMaintenanceDao().addMaintenance(maintenance20);
		Dao.getMaintenanceDao().addMaintenance(maintenance21);
		Dao.getMaintenanceDao().addMaintenance(maintenance22);
		Dao.getMaintenanceDao().addMaintenance(maintenance23);
		Dao.getMaintenanceDao().addMaintenance(maintenance24);
		Dao.getMaintenanceDao().addMaintenance(maintenance25);
		Dao.getMaintenanceDao().addMaintenance(maintenance26);
		Dao.getMaintenanceDao().addMaintenance(maintenance27);
	}

	// MRO(idMro, hangar, tech, airport)
	public void addMRO() {
		MRO mro = new MRO("1000", 10, 15, "CDG");
		MRO mro1 = new MRO("1001", 6, 20, "LAX");
		MRO mro2 = new MRO("1002", 2, 8, "NCE");
		MRO mro3 = new MRO("1003", 16, 12, "SXF");
		MRO mro4 = new MRO("1004", 7, 11, "NRT");
		MRO mro5 = new MRO("1005", 7, 9, "LHR");
		MRO mro6 = new MRO("1006", 7, 6, "HEL");
		MRO mro7 = new MRO("1007", 7, 14, "MIA");
		MRO mro8 = new MRO("1008", 7, 10, "CBR");
		MRO mro9 = new MRO("1009", 7, 13, "DEL");
		MRO mro10 = new MRO("1010", 8, 7, "AGA");
		MRO mro11 = new MRO("1011", 8, 7, "YUL");
		MRO mro12 = new MRO("1012", 12, 8, "SVO");
		Dao.getMRODao().addMRO(mro);
		Dao.getMRODao().addMRO(mro1);
		Dao.getMRODao().addMRO(mro2);
		Dao.getMRODao().addMRO(mro3);
		Dao.getMRODao().addMRO(mro4);
		Dao.getMRODao().addMRO(mro5);
		Dao.getMRODao().addMRO(mro6);
		Dao.getMRODao().addMRO(mro7);
		Dao.getMRODao().addMRO(mro8);
		Dao.getMRODao().addMRO(mro9);
		Dao.getMRODao().addMRO(mro10);
		Dao.getMRODao().addMRO(mro11);
		Dao.getMRODao().addMRO(mro12);
	}

	// User( idUser, name, firstname, email, password, status)
	public void addUser() {
		User user = new User("12", "Alphonse", "Tibault", "alphonse.tibault", "azerty", false);
		User user1 = new User("13", "Martin", "Matthieu", "martin.matthieu", "azerty", true);
		User user2 = new User("14", "Dupond", "Nicolas", "dupond.nicolas", "azerty", false);
		User user3 = new User("15", "Thomas", "Louis", "thomas.louis", "azerty", true);
		User user4 = new User("16", "Bernard", "Clement", "bernard.clement", "azerty", false);
		User user5 = new User("17", "Robert", "Laurent", "robert.laurent", "azerty", true);
		User user6 = new User("18", "Marchand", "Simone", "marchand.simone", "azerty", false);
		Dao.getUserDao().addUser(user);
		Dao.getUserDao().addUser(user1);
		Dao.getUserDao().addUser(user2);
		Dao.getUserDao().addUser(user3);
		Dao.getUserDao().addUser(user4);
		Dao.getUserDao().addUser(user5);
		Dao.getUserDao().addUser(user6);

	}
}
