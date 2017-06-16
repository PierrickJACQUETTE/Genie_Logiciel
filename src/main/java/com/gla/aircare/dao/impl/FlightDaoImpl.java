package com.gla.aircare.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;

import com.gla.aircare.dao.Dao;
import com.gla.aircare.dao.Singleton;
import com.gla.aircare.dao.inter.FlightDao;
import com.gla.aircare.dao.object.Flight;

public class FlightDaoImpl implements FlightDao {

	private Client client;

	public FlightDaoImpl() {
		client = Singleton.getInstance().getClient();
	}

	public List<Flight> getFlights() {
		SearchResponse searchResponse = client.prepareSearch(Dao.NAMEBASE).setTypes(Dao.NAMETABLEFLIGHT)
				.setSize(Dao.MAXGET).get();
		List<Flight> list = new ArrayList<Flight>();
		Flight flight = new Flight();
		for (SearchHit hit : searchResponse.getHits()) {
			list.add(flight.createObject(hit.getSourceAsString()));
		}
		return list;
	}

	public int addFlight(Flight flight) {
		IndexResponse indexResponse = client.prepareIndex(Dao.NAMEBASE, Dao.NAMETABLEFLIGHT, flight.getIdFlight())
				.setSource(flight.create()).get();
		return indexResponse.status().getStatus();
	}

}
