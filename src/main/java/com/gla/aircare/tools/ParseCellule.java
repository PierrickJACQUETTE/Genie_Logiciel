package com.gla.aircare.tools;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ParseCellule {

	private Cell cellule;
	private CellType attendu;
	private int numeroLigne;
	private int numeroColonne;

	public ParseCellule(Cell cellule, CellType attendu, int numeroLigne, int numeroColonne) {
		this.cellule = cellule;
		this.attendu = attendu;
		this.numeroLigne = numeroLigne;
		this.numeroColonne = numeroColonne;
	}

	private void printErreur(String message) {
		System.err.println(
				"Type cellule (" + message + "): ligne: " + this.numeroLigne + " colonne: " + this.numeroColonne);
	}

	private boolean isNull() {
		boolean resultat = false;
		if (this.cellule == null) {
			this.printErreur("NULL");
			resultat = true;
		}
		return resultat;
	}

	public boolean isCorrect() {
		boolean resultat = false;
		if (!isNull()) {
			switch (this.cellule.getCellTypeEnum()) {
			case BOOLEAN:
				resultat = (this.attendu == CellType.BOOLEAN) ? true : false;
				break;
			case NUMERIC:
				resultat = (this.attendu == CellType.NUMERIC) ? true : false;
				break;
			case STRING:
				resultat = (this.attendu == CellType.STRING) ? true : false;
				break;
			default:
				break;
			}
			if (resultat == false) {
				this.printErreur(this.cellule.getCellTypeEnum().toString() + ", attendu: " + this.attendu);
			}
		}
		return resultat;
	}

}
