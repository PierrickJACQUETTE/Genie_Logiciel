package com.gla.aircare.dao.object;

import org.codehaus.jackson.annotate.JsonProperty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Plane {

	@JsonProperty
	private String type;
	@JsonProperty
	private String miseEnService;
	@JsonProperty
	private String idPlane;
	@JsonProperty
	private boolean status; // true pour navigable et false pour non navigable
	@JsonProperty
	private int nbCycle;
	@JsonProperty
	private int nbHeureVol;

	public Plane() {
	}

	public Plane(String type, String miseEnService, String idPlane, boolean status) {
		this.type = type;
		this.miseEnService = miseEnService;
		this.idPlane = idPlane;
		this.status = status;
		int cycle = (int)(Math.random()*1000)+100;
		this.nbCycle = cycle;
		int nbHeure = (int)(Math.random()*10)+1;
		this.nbHeureVol = cycle * nbHeure;
	}

	public String getType() {
		return type;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	protected boolean getStatus() {
		return status;
	}
	
	public void setNbCycle(int nbCycle) {
		this.nbCycle = nbCycle;
	}

	public int getNbCycle() {
		return nbCycle;
	}
	
	public void setNbHeureVol(int nbHeureVol) {
		this.nbHeureVol = nbHeureVol;
	}

	public int getNbHeureVol() {
		return nbHeureVol;
	}

	protected void setType(String type) {
		this.type = type;
	}

	public String getMiseEnService() {
		return miseEnService;
	}

	protected void setMiseEnService(String miseEnService) {
		this.miseEnService = miseEnService;
	}

	public String getIdPlane() {
		return idPlane;
	}

	@Override
	public String toString() {
		return "Plane [type=" + type + ", miseEnService=" + miseEnService + ", idPlane=" + idPlane + ", status="
				+ status + ", nbCycle=" + nbCycle + ", nbHeureVol=" + nbHeureVol + "]";
	}

	public String create() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public Plane createObject(String sourceAsString) {
		if (sourceAsString != null) {
			Gson gson = new GsonBuilder().create();
			return gson.fromJson(sourceAsString, Plane.class);
		}
		return null;
	}

}
