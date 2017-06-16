package com.gla.aircare.dao.object;

import org.codehaus.jackson.annotate.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//toString(DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z")

public class Flight {

	@JsonProperty
	private String idFlight;
	@JsonProperty
	private long commercialNumber;
	@JsonProperty
	private String idPlane;
	@JsonProperty
	private String dateDepart;
	@JsonProperty
	private String dateArrivee;
	@JsonProperty
	private String destination;
	@JsonProperty
	private String depart;

	public Flight() {
	}

	public Flight(String idFlight, long commercialNumber, String idPlane, String dateDepart, String dateArrivee,
			String destination, String depart) {
		this.idFlight = idFlight;
		this.commercialNumber = commercialNumber;
		this.idPlane = idPlane;
		this.dateDepart = dateDepart;
		this.dateArrivee = dateArrivee;
		this.destination = destination;
		this.depart = depart;
	}

	public String getIdFlight() {
		return idFlight;
	}

	public long getCommercialNumber() {
		return commercialNumber;
	}

	public void setCommercialNumber(long commercialNumber) {
		this.commercialNumber = commercialNumber;
	}

	public String getIdPlane() {
		return idPlane;
	}

	public String getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(String dateDepart) {
		this.dateDepart = dateDepart;
	}

	public String getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(String dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getDepart() {
		return depart;
	}

	public void setDepart(String depart) {
		this.depart = depart;
	}

	@Override
	public String toString() {
		return "Flight [idFlight=" + idFlight + ", commercialNumber=" + commercialNumber + ", idPlane=" + idPlane
				+ ", dateDepart=" + dateDepart + ", dateArrivee=" + dateArrivee + ", destination=" + destination
				+ ", depart=" + depart + "]";
	}

	public String create() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public Flight createObject(String sourceAsString) {
		if (sourceAsString != null) {
			Gson gson = new GsonBuilder().create();
			return gson.fromJson(sourceAsString, Flight.class);
		}
		return null;
	}

}
