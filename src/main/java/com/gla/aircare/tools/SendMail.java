package com.gla.aircare.tools;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.gla.aircare.dao.Dao;
import com.gla.aircare.dao.object.MRO;
import com.gla.aircare.dao.object.Maintenance;

public class SendMail {

	private final String DEBUT = "Bonjour,\n\n Dans une semaine vous devriez effectué les maintenances suivantes : \n";
	private final String FIN = "Cordialement, \nMCC\n";
	private StringBuilder corps;

	public SendMail() {
		corps = new StringBuilder(DEBUT);
	}

	public void sendMailMro() {
		List<MRO> listMro = Dao.getMRODao().getMROs();
		for (MRO m : listMro) {
			boolean send = false;
			List<Maintenance> listMain = Dao.getMRODao().getMaintenancesMRO(m.getIdMRO());
			for (Maintenance ma : listMain) {
				DateTime dateCourante = new DateTime();
				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
				DateTime dateMaintenance = formatter.parseDateTime(ma.getTaskDone());
				int days = Days.daysBetween(dateCourante, dateMaintenance).getDays();
				if (days == 7) {
					corps.append("\n  - " + ma);
					send = true;
				}
			}
			if (send) {
				corps.append("\n\n" + FIN);
				Mail mail = new Mail(corps.toString());
				mail.sendMail();
				corps = new StringBuilder(DEBUT);
			}
		}
	}

}
