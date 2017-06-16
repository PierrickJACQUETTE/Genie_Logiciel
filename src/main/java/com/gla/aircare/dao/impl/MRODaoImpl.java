package com.gla.aircare.dao.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;

import com.gla.aircare.dao.Dao;
import com.gla.aircare.dao.Singleton;
import com.gla.aircare.dao.inter.MRODao;
import com.gla.aircare.dao.inter.MaintenanceDao;
import com.gla.aircare.dao.object.MRO;
import com.gla.aircare.dao.object.Maintenance;

public class MRODaoImpl implements MRODao {

	private Client client;

	public MRODaoImpl() {
		client = Singleton.getInstance().getClient();
	}

	
	public List<MRO> getMROs() {
		SearchResponse searchResponse = client.prepareSearch(Dao.NAMEBASE).setTypes(Dao.NAMETABLEMRO)
				.setSize(Dao.MAXGET).get();
		List<MRO> list = new ArrayList<MRO>();
		MRO mro = new MRO();
		for (SearchHit hit : searchResponse.getHits()) {
			list.add(mro.createObject(hit.getSourceAsString()));
		}
		return list;
	}


	public MRO getMRO(String idMRO) {
		MRO mro = new MRO();
		GetResponse getResponse = client.prepareGet(Dao.NAMEBASE, Dao.NAMETABLEMRO, idMRO).execute().actionGet();
		mro = mro.createObject(getResponse.getSourceAsString());
		return mro;
	}


	public List<Maintenance> getMaintenancesMRO(String idMRO) {
		MaintenanceDao maintenances = Dao.getMaintenanceDao();
		List<Maintenance> listMaintenanceMRO = maintenances.getMaintenances();
		Iterator<Maintenance> it = listMaintenanceMRO.iterator();
		while (it.hasNext()) {
			Maintenance maintenance = it.next();
			if (!(maintenance.getIdMRO().equals(idMRO))) {
				it.remove();
			}
		}
		return listMaintenanceMRO;
	}

	
	public int addMRO(MRO mro) {
		IndexResponse indexResponse = client.prepareIndex(Dao.NAMEBASE, Dao.NAMETABLEMRO, mro.getIdMRO())
				.setSource(mro.create()).get();
		return indexResponse.status().getStatus();
	}

}
