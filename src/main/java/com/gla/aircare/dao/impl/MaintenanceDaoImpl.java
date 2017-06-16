package com.gla.aircare.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;

import com.gla.aircare.dao.Dao;
import com.gla.aircare.dao.Singleton;
import com.gla.aircare.dao.inter.MaintenanceDao;
import com.gla.aircare.dao.object.Maintenance;
import com.gla.aircare.dao.object.Task;

public class MaintenanceDaoImpl implements MaintenanceDao {

	private Client client;

	public MaintenanceDaoImpl() {
		client = Singleton.getInstance().getClient();
	}

	public List<Maintenance> getMaintenances() {
		SearchResponse searchResponse = client.prepareSearch(Dao.NAMEBASE).setTypes(Dao.NAMETABLEMAINTENANCE)
				.setSize(Dao.MAXGET).get();
		List<Maintenance> list = new ArrayList<Maintenance>();
		Maintenance maintenance = new Maintenance();
		for (SearchHit hit : searchResponse.getHits()) {
			list.add(maintenance.createObject(hit.getSourceAsString()));
		}
		return list;
	}

	public List<Maintenance> getMaintenance(String idMaintenance) {
		SearchResponse searchResponse = client.prepareSearch(Dao.NAMEBASE).setTypes(Dao.NAMETABLEMAINTENANCE)
				.setSize(Dao.MAXGET).get();
		List<Maintenance> list = new ArrayList<Maintenance>();
		Maintenance maintenance = new Maintenance();
		for (SearchHit hit : searchResponse.getHits()) {
			Maintenance m = maintenance.createObject(hit.getSourceAsString());
			if (m.getIdMaintenance().equals(idMaintenance)) {
				list.add(m);
			}
		}
		return list;
	}

	public Maintenance getMaintenanceTask(String idMaintenance, String idTask) {
		SearchResponse searchResponse = client.prepareSearch(Dao.NAMEBASE).setTypes(Dao.NAMETABLEMAINTENANCE)
				.setSize(Dao.MAXGET).get();
		Maintenance maintenance = new Maintenance();
		for (SearchHit hit : searchResponse.getHits()) {
			Maintenance m = maintenance.createObject(hit.getSourceAsString());
			if (m.getIdMaintenance().equals(idMaintenance) && m.getIdTask().equals(idTask)) {
				return m;
			}
		}
		return null;
	}

	
	public int setMaintenance(Maintenance maintenance) {
		UpdateResponse updateResponse = client
				.prepareUpdate(Dao.NAMEBASE, Dao.NAMETABLEMAINTENANCE, maintenance.getIdMaintenanceElastic())
				.setDoc(maintenance.create()).get();
		return updateResponse.status().getStatus();
	}


	public int addMaintenance(Maintenance maintenance) {
		IndexResponse indexResponse = client
				.prepareIndex(Dao.NAMEBASE, Dao.NAMETABLEMAINTENANCE, maintenance.getIdMaintenanceElastic())
				.setSource(maintenance.create()).get();
		return indexResponse.status().getStatus();
	}

	
	public int removeMaintenance(String idMaintenance) {
		DeleteResponse deleteResponse = client.prepareDelete(Dao.NAMEBASE, Dao.NAMETABLEMAINTENANCE, idMaintenance)
				.get();
		return deleteResponse.status().getStatus();
	}


	public List<Task> getTasksMaintenance(String idMaintenance) {
		List<Maintenance> list = new ArrayList<Maintenance>();
		list = getMaintenances();
		List<Task> listTaskMaintenance = new ArrayList<Task>();
		Iterator<Maintenance> it = list.iterator();
		while (it.hasNext()) {
			Maintenance maintenance = it.next();
			if (maintenance.getIdMaintenance().equals(idMaintenance)) {
				listTaskMaintenance.add(Dao.getTaskDao().getTask(maintenance.getIdTask()));
			}
		}
		return listTaskMaintenance;
	}

}
