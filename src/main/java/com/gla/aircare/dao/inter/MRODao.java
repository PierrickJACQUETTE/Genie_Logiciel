package com.gla.aircare.dao.inter;

import java.util.List;

import com.gla.aircare.dao.object.MRO;
import com.gla.aircare.dao.object.Maintenance;

public interface MRODao {

	public List<MRO> getMROs();

	public MRO getMRO(String idMRO);

	public List<Maintenance> getMaintenancesMRO(String idMRO);

	public int addMRO(MRO mro);
}
