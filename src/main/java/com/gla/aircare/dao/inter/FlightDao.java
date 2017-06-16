package com.gla.aircare.dao.inter;

import java.util.List;

import com.gla.aircare.dao.object.Flight;

public interface FlightDao {

	public List<Flight> getFlights();

	public int addFlight(Flight flight);

}
