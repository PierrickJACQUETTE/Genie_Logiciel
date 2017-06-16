package com.gla.aircare.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gla.aircare.dao.Dao;
import com.gla.aircare.dao.object.Task;

public class ParseCSV {

	public String contenu;
	final private List<Mpd> examens;

	public ParseCSV(String contenu) {
		this.contenu = contenu;
		this.examens = new ArrayList<Mpd>();
	}

	private Mpd findMpd(String row) {
		Mpd mpd = new Mpd();
		Task task = new Task();
		String[] colonne = row.split(";");
		for (int i = 0; i < colonne.length; i++) {
				switch (i) {

				case 0:
					String idTask=colonne[0];
					task.setIdTask(idTask);
					break;

				case 1:
					double ATA = Double.parseDouble(colonne[1]);
					task.setATA((int)(ATA));
					break;

				case 2:
					task.setDescription(colonne[2]);
					break;

				case 3:

					String moisHours = colonne[3];
					mpd.setThresholdInterval(Arrays.asList(moisHours.split(" OR ")));

					if(mpd.getThresholdInterval().size()>1){
						int length=mpd.getThresholdInterval().get(0).length();
						if(mpd.getThresholdInterval().get(0).substring(length-2, length).equals("MO")){
							int mo=Integer.parseInt(mpd.getThresholdInterval().get(0).replaceAll(" MO", "").trim());
							int fh=Integer.parseInt(mpd.getThresholdInterval().get(1).replaceAll(" FH", "").trim());
							task.setPeriodeTemps(mo);
							task.setPeriodeNbHeureVol(fh);
						}else {
							int mo=Integer.parseInt(mpd.getThresholdInterval().get(1).replaceAll(" MO", "").trim());
							int fh=Integer.parseInt(mpd.getThresholdInterval().get(0).replaceAll(" FH", "").trim());
							//System.out.println(mpd.getThresholdInterval().get(1).replaceAll(" MO", ""));
							//System.out.println(mpd.getThresholdInterval().get(0).replaceAll(" FH", ""));
							task.setPeriodeTemps(mo);
							task.setPeriodeNbHeureVol(fh);
						}
					}else{
						int length=mpd.getThresholdInterval().get(0).length();
						if(mpd.getThresholdInterval().get(0).substring(length-2, length).equals("MO")){
							int mo=Integer.parseInt(mpd.getThresholdInterval().get(0).replaceAll(" MO", "").trim());
							task.setPeriodeTemps(mo);
						}else {
							int fh=Integer.parseInt(mpd.getThresholdInterval().get(0).replaceAll(" FH", "").trim());
							task.setPeriodeNbHeureVol(fh);
						}
					}
					break;

				case 4:
					mpd.setReference(colonne[4]);
					break;

				case 5:
					task.setDuree(Double.parseDouble(colonne[5]));
					break;

				case 6:
					task.setApplicability(colonne[6]);
					break;

				case 7:
					String hang=colonne[7];
					if(hang.equals("true"))
						task.setHangar(true);
					else task.setHangar(false);
					break;

				case 8:
					String bas=colonne[8];
					if(bas.equals("true"))
						task.setBase(true);
					else task.setBase(false);
					break;
				}

		}
		Dao.getTaskDao().addTask(task);	

		return mpd;
	}

	public void findAllMpd() {
		contenu = contenu.replaceAll("\"", "");
		contenu = contenu.replaceAll("\\\\r\\\\n",System.getProperty("line.separator"));
		String[] ligne = contenu.split(System.getProperty("line.separator"));
		for(int i = 1; i < ligne.length; i++){
			final Mpd examen = findMpd(ligne[i]);
			if (examen == null) {
				System.err.println("Ligne non ajoute: " + i);
			} else {
				examens.add(examen);
			}
		}
	}

	public StringBuilder myToString() {
		StringBuilder resultat = new StringBuilder();
		for (Mpd e : this.examens) {
			resultat.append(e.toString() + "\n");
		}
		return resultat;
	}

}
