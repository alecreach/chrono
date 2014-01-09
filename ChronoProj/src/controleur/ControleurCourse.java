package controleur;

import java.util.Date;

import model.CourseStandard;
import model.Evenement;

import vue.FenetreNewEvent;
import vue.FenetreNewRace;

public class ControleurCourse {

	private Evenement eve;
	private CourseStandard c;

	public ControleurCourse(Evenement eve) {
		this.eve = eve;
	}
	
	public void setCourseStandard(int idCourseStandard) {
		c = eve.getCourseStandard(idCourseStandard);
	}
	
	
	public void enregistrerC(String nomCourse, Date dHeureDebut, int nbMaxTours, Date dHeureFin, Date dDureeConsMaxPilote, Date dDureeMaxPilote, String commentaire) {

		c.setNomCourse(nomCourse);
		c.setDureeMaxPilote(dDureeMaxPilote);
		c.setDureeConsMaxPilote(dDureeConsMaxPilote);
		c.setHeuredebut(dHeureDebut);
		c.setHeurefin(dHeureFin);
		c.setCommentaire(commentaire);
		c.setNbMaxTours(nbMaxTours);
	}
	
	public void ajouterObserver(FenetreNewEvent fenetre) {
		c.addObserver(fenetre);
	}
	
	public void modifierCourse (FenetreNewRace fenetre) {
		fenetre.modifierCourse(c.getNomCourse(), c.getHeuredebut(), c.getNbMaxTours(), c.getHeurefin(), c.getDureeConsMaxPilote(), c.getDureeMaxPilote(), c.getCommentaire());
	}
}
