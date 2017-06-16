package com.gla.aircare.tools;

import java.util.List;

public class Mpd
{
	private String taskNumber;
	private int ATA;
	private String description;
	private List<String> thresholdInterval;
	private String reference;
	private String duree;
	private String applicability;
	private String hangar;
	private String base;

	public String getTaskNumber() {
		return taskNumber;
	}
	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getThresholdInterval() {
		return thresholdInterval;
	}
	public void setThresholdInterval(List<String> list) {
		this.thresholdInterval = list;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getApplicability() {
		return applicability;
	}
	public void setApplicability(String applicability) {
		this.applicability = applicability;
	}
	public int getATA() {
		return ATA;
	}
	public void setATA(int ATA) {
		this.ATA = ATA;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getHangar() {
		return hangar;
	}
	public void setHangar(String hangar) {
		this.hangar = hangar;
	}
	public String getBase() {
		return base;
	}
	public void setBase(String base) {
		this.base = base;
	}

	@Override
	public String toString() {
		return "MPD [taskNumber=" + taskNumber + ", ATA=" + ATA + ", description=" + description
				+ ", thresholdInterval=" + thresholdInterval + ", reference=" + reference + ", duree=" + duree
				+ ", applicability=" + applicability + ", hangar=" + hangar + ", base=" + base + "]";
	}

}
