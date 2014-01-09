package controleur;

import java.util.Date;

import model.Evenement;
import model.SeanceEssai;

import vue.FenetreNewEvent;
import vue.FenetreNewRace;

public class ControleurSeanceEssai  {

	private Evenement eve;
	private SeanceEssai se;

	public ControleurSeanceEssai(Evenement eve) {
		this.eve = eve;
	}

	public void setSeanceEssai(int idSeanceEssai) {
		se = eve.getSeanceEssai(idSeanceEssai);
	}

	public void enregistrerSE(String nomCourse, Date dHeureDebut, int nbMaxTours, Date dHeureFin, Date dDureeConsMaxPilote, Date dDureeMaxPilote, String commentaire) {
		se.setDureeConsMaxPilote(dDureeConsMaxPilote);
		se.setDureeMaxPilote(dDureeMaxPilote);
		se.setHeuredebut(dHeureDebut);
		se.setHeurefin(dHeureFin);
		se.setNomCourse(nomCourse);
		se.setCommentaire(commentaire);
		se.setNbMaxTours(nbMaxTours);
	}
	
	public void ajouterObserver(FenetreNewEvent fenetre) {
		se.addObserver(fenetre);
	}

	public void modifierCourse (FenetreNewRace fenetre) {
		fenetre.modifierCourse (se.getNomCourse(), se.getHeuredebut(), se.getNbMaxTours(), se.getHeurefin(), se.getDureeConsMaxPilote(), se.getDureeMaxPilote(), se.getCommentaire());
	}
}	

