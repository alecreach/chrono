package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class SeanceEssai extends Course {

	public SeanceEssai(Date heuredebut, Date heurefin, String nomCourse, Date dureeMaxPilote, Date dureeConsMaxPilote, String commentaire, int nbMaxTours) {
		super(dureeConsMaxPilote, dureeConsMaxPilote, nomCourse, dureeConsMaxPilote, dureeConsMaxPilote, commentaire, nbMaxTours);
	}
	
	public SeanceEssai() {
		super();
	}
	
	public void setNomCourse(String Course) {
		this.nomCourse = Course;
		this.setChanged();
		this.notifyObservers("SeanceEssai-" + nomCourse);
	}
}
