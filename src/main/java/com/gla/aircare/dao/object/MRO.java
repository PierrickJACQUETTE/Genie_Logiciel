package com.gla.aircare.dao.object;

import org.codehaus.jackson.annotate.JsonProperty;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MRO {

	@JsonProperty
	private String idMRO;
	@JsonProperty
	private long hangars;
	@JsonProperty
	private long nbTechniciens;
	@JsonProperty
	private String airport;

	public MRO(String idMRO, long hangars, long nbTechniciens, String airport) {
		this.idMRO = idMRO;
		this.hangars = hangars;
		this.nbTechniciens = nbTechniciens;
		this.airport = airport;
	}

	public MRO() {
	}

	public String getIdMRO() {
		return idMRO;
	}

	public long getHangars() {
		return hangars;
	}

	public void setHangars(int hangars) {
		this.hangars = hangars;
	}

	public long getNbTechniciens() {
		return nbTechniciens;
	}

	public void setNbTechniciens(long nbTechniciens) {
		this.nbTechniciens = nbTechniciens;
	}

	public String getAirport() {
		return airport;
	}

	public void setAirport(String airport) {
		this.airport = airport;
	}

	@Override
	public String toString() {
		return "MRO [idMRO=" + idMRO + ", hangars=" + hangars + ", nbTechniciens=" + nbTechniciens + ", airport="
				+ airport + "]";
	}

	public String create() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public MRO createObject(String sourceAsString) {
		if (sourceAsString != null) {
			Gson gson = new GsonBuilder().create();
			return gson.fromJson(sourceAsString, MRO.class);
		}
		return null;
	}

}
