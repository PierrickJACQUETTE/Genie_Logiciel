package com.gla.aircare.ws;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.gla.aircare.dao.Dao;
import com.gla.aircare.dao.object.Flight;
import com.gla.aircare.dao.object.MRO;
import com.gla.aircare.dao.object.Maintenance;
import com.gla.aircare.dao.object.Plane;
import com.gla.aircare.dao.object.Task;
import com.gla.aircare.dao.object.User;
import com.gla.aircare.tools.ParseCSV;

@Path("/ressource")
public class Ressource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/maintenance")
	public List<Maintenance> getMaintenance() {
		return Dao.getMaintenanceDao().getMaintenances();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/maintenance/{id}")
	public List<Maintenance> getMaintenanceID(@PathParam("id") String id) {
		return Dao.getMaintenanceDao().getMaintenance(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/maintenance/{idM}/task/{idT}")
	public Maintenance getMaintenanceIDTaskID(@PathParam("idM") String idMaintenance, @PathParam("idT") String idTask) {
		return Dao.getMaintenanceDao().getMaintenanceTask(idMaintenance, idTask);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/flight")
	public List<Flight> getFlight() {
		return Dao.getFlightDao().getFlights();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/task")
	public List<Task> getTask() {
		return Dao.getTaskDao().getTasks();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/task/{id}")
	public Task getTask(@PathParam("id") String id) {
		return Dao.getTaskDao().getTask(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane")
	public List<Plane> getPlanes() {
		return Dao.getPlaneDao().getPlanes();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane/{id}")
	public Plane getPlanes(@PathParam("id") String id) {
		return Dao.getPlaneDao().getPlane(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane/{id}/maintenance")
	public List<Maintenance> getPlaneMaintenance(@PathParam("id") String id) {
		return Dao.getPlaneDao().getMaintenancesPlane(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/plane/{id}/flight")
	public List<Flight> getFlightPlane(@PathParam("id") String id) {
		return Dao.getPlaneDao().getFlightPlane(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/maintenance/{id}/task")
	public List<Task> getTasksMaintenance(@PathParam("id") String id) {
		return Dao.getMaintenanceDao().getTasksMaintenance(id);
	}

	// tester la connexion et le mot de passe
	// renvoyer l'id du user si mot de passe correct sinon null
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{id}/{passeWord}")
	public int getUser(@PathParam("id") String id, @PathParam("passeWord") String passeWord) {
		User u = Dao.getUserDao().getUser(id);
		return (u == null)? 1 : u.testConnexion(passeWord);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/user/{id}")
	public User getUser(@PathParam("id") String id) {
		return Dao.getUserDao().getUser(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/user/{id}")
	public int retrieveMainteance(User user) {
		return Dao.getUserDao().setUser(user);
	}

	@POST
	@Path("/maintenance")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int retrieveMainteance(Maintenance maintenance) {
		int res = 0;
		try {
			res = Dao.getMaintenanceDao().addMaintenance(maintenance);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/task/{id}")
	public int setTask(Task task) {
		return Dao.getTaskDao().setTask(task);
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/maintenance/{id}")
	public int delete(String id) {
		return Dao.getMaintenanceDao().removeMaintenance(id);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/maintenance")
	public int putMaintenance(Maintenance maintenance) {
		return Dao.getMaintenanceDao().addMaintenance(maintenance);
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/task")
	public int putTask(Task task) {
		return Dao.getTaskDao().addTask(task);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/mro")
	public List<MRO> getMRO() {
		return Dao.getMRODao().getMROs();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/mro/{id}")
	public MRO getMRO(@PathParam("id") String id) {
		return Dao.getMRODao().getMRO(id);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/mro/{id}/maintenance")
	public List<Maintenance> getMaintenancesMRO(@PathParam("id") String id) {
		return Dao.getMRODao().getMaintenancesMRO(id);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/mpd")
	public boolean putMpd(String nom) {
		ParseCSV parse = new ParseCSV(nom);
		parse.findAllMpd();
		return true;
	}
}
