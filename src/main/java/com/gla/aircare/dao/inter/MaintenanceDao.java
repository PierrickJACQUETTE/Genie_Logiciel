package com.gla.aircare.dao.inter;

import java.util.List;

import com.gla.aircare.dao.object.Maintenance;
import com.gla.aircare.dao.object.Task;

public interface MaintenanceDao {

	public List<Maintenance> getMaintenances();

	public List<Maintenance> getMaintenance(String idMaintenance);

	public int setMaintenance(Maintenance maintenance);

	public int addMaintenance(Maintenance maintenance);

	public int removeMaintenance(String idMaintenance);

	public List<Task> getTasksMaintenance(String idMaintenance);

	public Maintenance getMaintenanceTask(String idMaintenance, String idTask);

}
