package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CourseStandard extends Course {

	public CourseStandard(Date heuredebut, Date heurefin, String nomCourse, Date dureeMaxPilote, Date dureeConsMaxPilote, String commentaire, int nbMaxTours) {
		super(dureeConsMaxPilote, dureeConsMaxPilote, nomCourse, dureeConsMaxPilote, dureeConsMaxPilote, commentaire, nbMaxTours);
	}

	public CourseStandard() {
		super();
	}
	
	public void setNomCourse(String Course) {
		this.nomCourse = Course;
		this.setChanged();
		this.notifyObservers("CourseStandard-" + nomCourse);
	}
	
}
