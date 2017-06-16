package com.gla.aircare.dao.object;

import org.codehaus.jackson.annotate.JsonProperty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Task {

	@JsonProperty
	private String idTask;
	@JsonProperty
	private String applicability;
	@JsonProperty
	private int ATA;
	@JsonProperty
	private String description;
	@JsonProperty
	private int periodeNbHeureVol;
	@JsonProperty
	private int periodeTemps;
	@JsonProperty
	private double duree;
	@JsonProperty
	private boolean hangar;
	@JsonProperty
	private boolean base; // true => base, false=> ligne

	public Task() {
	}

	public Task(String idTask,String applicability, int ATA,String description, int periodeNbHeureVol, int periodeTemps, boolean hangar, double duree,
			boolean base) {
		this.idTask = idTask;
		this.applicability=applicability;
		this.ATA=ATA;
		this.description=description;
		this.periodeNbHeureVol = periodeNbHeureVol;
		this.periodeTemps = periodeTemps;
		this.hangar = hangar;
		this.duree = duree;
		this.base = base;
	}

	public String getIdTask() {
		return idTask;
	}
	public void setIdTask(String idTask){
		this.idTask=idTask;
	}
	public String getApplicability() {
		return applicability;
	}
	public void setApplicability(String applicability){
		this.applicability=applicability;
	}
	public int getATA() {
		return this.ATA;
	}
	public void setATA(int ATA){
		this.ATA=ATA;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(String description){
		this.description=description;
	}
	public int getPeriodeNbHeureVol() {
		return periodeNbHeureVol;
	}

	public void setPeriodeNbHeureVol(int periodeNbHeureVol) {
		this.periodeNbHeureVol = periodeNbHeureVol;
	}

	public int getPeriodeTemps() {
		return periodeTemps;
	}

	public void setPeriodeTemps(int periodeTemps) {
		this.periodeTemps = periodeTemps;
	}

	public double getDuree() {
		return duree;
	}

	public void setDuree(double duree) {
		this.duree = duree;
	}

	public boolean isHangar() {
		return hangar;
	}

	public void setHangar(boolean hangar) {
		this.hangar = hangar;
	}

	public boolean isBase() {
		return base;
	}

	public void setBase(boolean base) {
		this.base = base;
	}

	@Override
	public String toString() {
		return "Task [idTask=" + idTask +"Applicability="+applicability+ "ATA="+ ATA +"description="+description+", periodeNbHeureVol=" + periodeNbHeureVol + ", periodeTemps=" + periodeTemps
				+", hangar=" + hangar + ", duree=" + duree + ", base=" + base + "]";
	}

	public String create() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public Task createObject(String sourceAsString) {
		if (sourceAsString != null) {
			Gson gson = new GsonBuilder().create();
			return gson.fromJson(sourceAsString, Task.class);
		}
		return null;
	}

}
