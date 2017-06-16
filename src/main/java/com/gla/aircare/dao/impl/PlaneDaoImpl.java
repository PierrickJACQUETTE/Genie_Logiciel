package com.gla.aircare.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.joda.time.DateTime;
import org.joda.time.Hours;
import org.joda.time.Months;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.gla.aircare.dao.Dao;
import com.gla.aircare.dao.Singleton;
import com.gla.aircare.dao.inter.FlightDao;
import com.gla.aircare.dao.inter.MaintenanceDao;
import com.gla.aircare.dao.inter.PlaneDao;
import com.gla.aircare.dao.object.Flight;
import com.gla.aircare.dao.object.Maintenance;
import com.gla.aircare.dao.object.Plane;
import com.gla.aircare.dao.object.Task;

public class PlaneDaoImpl implements PlaneDao {

	private Client client;

	public PlaneDaoImpl() {
		client = Singleton.getInstance().getClient();
	}


	public int addPlane(Plane plane) {
		IndexResponse indexResponse = client.prepareIndex(Dao.NAMEBASE, Dao.NAMETABLEPLANE, plane.getIdPlane())
				.setSource(plane.create()).get();
		return indexResponse.status().getStatus();
	}


	public Plane getPlane(String idPlane) {
		Plane plane = new Plane();
		GetResponse getResponse = client.prepareGet(Dao.NAMEBASE, Dao.NAMETABLEPLANE, idPlane).execute().actionGet();
		return plane.createObject(getResponse.getSourceAsString());
	}


	public List<Plane> getPlanes() {
		SearchResponse searchResponse = client.prepareSearch(Dao.NAMEBASE).setTypes(Dao.NAMETABLEPLANE)
				.setSize(Dao.MAXGET).get();
		List<Plane> list = new ArrayList<Plane>();
		Plane plane = new Plane();
		for (SearchHit hit : searchResponse.getHits()) {
			list.add(plane.createObject(hit.getSourceAsString()));
		}
		return list;

	}
	

	public List<Maintenance> getMaintenancesPlane(String idPlane) {
		MaintenanceDao maintenances = Dao.getMaintenanceDao();
		List<Maintenance> listMaintenancePlane = maintenances.getMaintenances();
		Iterator<Maintenance> it = listMaintenancePlane.iterator();
		while (it.hasNext()) {
			Maintenance maintenance = it.next();
			if (!(maintenance.getIdPlane().equals(idPlane))) {
				it.remove();
			}
		}
		return listMaintenancePlane;
	}


	public List<Flight> getFlightPlane(String idPlane) {
		FlightDao flights = Dao.getFlightDao();
		List<Flight> listFlightPlane = flights.getFlights();
		Iterator<Flight> it = listFlightPlane.iterator();
		while (it.hasNext()) {
			Flight flight = it.next();
			if (!(flight.getIdPlane().equals(idPlane))) {
				it.remove();
			}
		}
		return listFlightPlane;
	}
	
	
	public int setPlane(Plane plane) {
		UpdateResponse updateResponse = client
				.prepareUpdate(Dao.NAMEBASE, Dao.NAMETABLEPLANE, plane.getIdPlane())
				.setDoc(plane.create()).get();
		return updateResponse.status().getStatus();
	}

	
	public void updatePlane() {
		List<Plane> planes = getPlanes();
		List<Task> tasks = Dao.getTaskDao().getTasks();
		DateTime dateCourante = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
		for (Plane p : planes) {
			updateStatus(p, tasks, formatter, dateCourante);
			updateNb(p, formatter);
		}
	}
	
	
	private void updateStatus(Plane p, List<Task> tasks, DateTimeFormatter formatter, DateTime dateCourante){
		List<String> resultat = new ArrayList<String>();
		List<Maintenance> mforPlane = getMaintenancesPlane(p.getIdPlane());
		for (Task t : tasks) {
			// si la tache correspond a l'avion courant
			if (t.getApplicability().equals(p.getType())) {
				List<Maintenance> mforPlaneforTask = new ArrayList<Maintenance>();
				for (Maintenance m : mforPlane) {
					// ajouter celle qui correspondent à la tache courante
					if ((m.getIdTask().equals(t.getIdTask()))) {
						mforPlaneforTask.add(m);
					}
				}
				// aucune maintenance faite pour cette tache
				if (mforPlaneforTask.isEmpty()) {
					resultat.add(compareDate(p.getMiseEnService(), t, p, formatter, dateCourante));
				} else { // au moins une maintenance faite pour cette tache
					String last = lastDone(mforPlaneforTask, formatter);
					resultat.add(compareDate(last, t, p, formatter, dateCourante));
				}
			}
		}
		resultat.remove(null);
		p.setStatus(resultat.isEmpty());
		setPlane(p);
	}
	
	
	private void updateNb(Plane p, DateTimeFormatter formatter){
		List<Flight> flightforPlane = getFlightPlane(p.getIdPlane());
		p.setNbCycle(flightforPlane.size()+p.getNbCycle());
		int nbrHeure = p.getNbHeureVol();
		Iterator<Flight> it = flightforPlane.iterator();		
		while (it.hasNext()) {
			Flight flight = it.next();
			DateTime depart = formatter.parseDateTime(flight.getDateDepart());
			DateTime arrivee = formatter.parseDateTime(flight.getDateArrivee());			
			int diff = Math.abs(Hours.hoursBetween(depart, arrivee).getHours());
			nbrHeure += diff;
		}
		p.setNbHeureVol(nbrHeure);
		setPlane(p);
	}
	

	private String compareDate(String date, Task t, Plane p, DateTimeFormatter formatter, DateTime dateCourante) {
		String resultat = null;
		DateTime dt = formatter.parseDateTime(date);
		int months = Math.abs(Months.monthsBetween(dateCourante, dt).getMonths());
		List<Flight> flightforPlane = getFlightPlane(p.getIdPlane());
		if (t.getPeriodeTemps() != -1 && months > t.getPeriodeTemps()) {
			resultat = t.getIdTask();
		} else if (t.getPeriodeNbHeureVol() != -1) {
			if (flightforPlane.size() > t.getPeriodeNbHeureVol()) {
				resultat = t.getIdTask();
			}
		}
		return resultat;
	}

	
	private String lastDone(List<Maintenance> mforPlaneforTask, DateTimeFormatter formatter) {
		String lastDate = mforPlaneforTask.get(0).getTaskDone();
		// je cherche la plus recente
		for (Maintenance m : mforPlaneforTask) {
			if (formatter.parseDateTime(lastDate).getMillis() < formatter.parseDateTime(m.getTaskDone()).getMillis()) {
				lastDate = m.getTaskDone();
			}
		}
		return lastDate;
	}
}
