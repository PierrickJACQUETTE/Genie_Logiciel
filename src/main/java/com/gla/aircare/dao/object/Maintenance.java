package com.gla.aircare.dao.object;

import org.codehaus.jackson.annotate.JsonProperty;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Maintenance {

	@JsonProperty
	private String idMaintenance;
	@JsonProperty
	private String idPlane;
	@JsonProperty
	private String idMRO;
	@JsonProperty
	private String idTask;
	@JsonProperty
	private String taskDone;
	@JsonProperty
	private boolean done;
	@JsonProperty
	private String idMaintenanceElastic;

	public Maintenance() {
	}

	public Maintenance(String idMaintenance, String idPlane, String idMRO, String idTask, String taskDone,
			String idMaintenanceElastic, boolean done) {
		this.idMaintenance = idMaintenance;
		this.idPlane = idPlane;
		this.idMRO = idMRO;
		this.idTask = idTask;
		this.taskDone = taskDone;
		this.idMaintenanceElastic = idMaintenanceElastic;
		this.done = done;
	}

	public String getIdMaintenance() {
		return idMaintenance;
	}

	public String getIdPlane() {
		return idPlane;
	}

	public String getIdMRO() {
		return idMRO;
	}

	public String getIdTask() {
		return idTask;
	}

	public String getTaskDone() {
		return taskDone;
	}

	public String getIdMaintenanceElastic() {
		return idMaintenanceElastic;
	}

	public void setTaskDone(String taskDone) {
		this.taskDone = taskDone;
	}

	@Override
	public String toString() {
		return "Maintenance [idMaintenance=" + idMaintenance + ", idPlane=" + idPlane + ", idMRO=" + idMRO + ", idTask="
				+ idTask + ", taskDone=" + taskDone + ", id="+idMaintenanceElastic+", done="+done+"]";
	}

	public String create() {
		Gson gson = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
		return gson.toJson(this);
	}

	public Maintenance createObject(String sourceAsString) {
		if (sourceAsString != null) {
			Gson gson = new GsonBuilder().create();
			return gson.fromJson(sourceAsString, Maintenance.class);
		}
		return null;
	}

}
