package com.gla.aircare.dao.inter;

import java.util.List;

import com.gla.aircare.dao.object.Flight;
import com.gla.aircare.dao.object.Maintenance;
import com.gla.aircare.dao.object.Plane;

public interface PlaneDao {

	public List<Plane> getPlanes();

	public Plane getPlane(String idPlane);

	public int addPlane(Plane plane);

	public List<Maintenance> getMaintenancesPlane(String idPlane);

	public List<Flight> getFlightPlane(String idPlane);

	public void updatePlane();
}
