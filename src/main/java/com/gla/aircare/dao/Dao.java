package com.gla.aircare.dao;

import com.gla.aircare.dao.impl.FlightDaoImpl;
import com.gla.aircare.dao.impl.MRODaoImpl;
import com.gla.aircare.dao.impl.MaintenanceDaoImpl;
import com.gla.aircare.dao.impl.PlaneDaoImpl;
import com.gla.aircare.dao.impl.TaskDaoImpl;
import com.gla.aircare.dao.impl.UserDaoImpl;
import com.gla.aircare.dao.inter.FlightDao;
import com.gla.aircare.dao.inter.MRODao;
import com.gla.aircare.dao.inter.MaintenanceDao;
import com.gla.aircare.dao.inter.PlaneDao;
import com.gla.aircare.dao.inter.TaskDao;
import com.gla.aircare.dao.inter.UserDao;

public class Dao {

	public final static String NAMEBASE = "aircare";
	public final static String NAMETABLETASK = "Task";
	public final static String NAMETABLEPLANE = "Plane";
	public final static String NAMETABLEFLIGHT = "Flight";
	public final static String NAMETABLEMAINTENANCE = "Maintenance";
	public final static String NAMETABLEMRO = "MRO";
	public final static String NAMETABLEUSER = "User";

	public final static int MAXGET = 50;

	private static MaintenanceDao maintencanceDao = new MaintenanceDaoImpl();
	private static FlightDao flightDao = new FlightDaoImpl();;
	private static TaskDao taskDao = new TaskDaoImpl();
	private static PlaneDao planeDao = new PlaneDaoImpl();
	private static MRODao mroDao = new MRODaoImpl();
	private static UserDao userDao = new UserDaoImpl();

	public static MaintenanceDao getMaintenanceDao() {
		return maintencanceDao;
	}

	public static FlightDao getFlightDao() {
		return flightDao;
	}

	public static TaskDao getTaskDao() {
		return taskDao;
	}

	public static PlaneDao getPlaneDao() {
		return planeDao;
	}

	public static MRODao getMRODao() {
		return mroDao;
	}

	public static UserDao getUserDao() {
		return userDao;
	}

}
