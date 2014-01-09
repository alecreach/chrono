package controleur;

import java.sql.Date;

import model.CourseStandard;
import model.DureteTrain;
import model.Evenement;
import model.Pilote;
import model.SeanceEssai;
import model.TrainPneu;
import model.TypeTrain;
import model.Voiture;


public class ControleurChrono {

	private Evenement eve;

	public ControleurChrono(Evenement eve) {
		this.eve = new Evenement();
	}

	public Evenement getEve() {
		return eve;
	}

	public void sauvegarderEve() {

	}

	public void enregistrerEve(String nom, String circuit, String longueur ) {
		eve.setNomEvenement(nom);
		eve.setNomCircuit(circuit);
		eve.setLongueur(longueur);

	}
	
	// méthodes manipulation de l'evenement

	public void supprimerCourse(int rang)  {
		eve.supprimerCourseSndard(rang);
	}

	public void ajouterCourse(Date heuredebut, Date heurefin, String nomCourse, Date dureeMaxPilote, Date dureeConsMaxPilote) {
		CourseStandard c = new CourseStandard(heuredebut, heurefin, nomCourse, dureeMaxPilote, dureeConsMaxPilote);
		eve.ajouterCourseStandard(c);
	}

	public void modifierCourseStandard(int rangCs, Date heuredebut, Date heurefin, String nomCourse, Date dureeMaxPilote, Date dureeConsMaxPilote) {
		CourseStandard cs = new CourseStandard(heuredebut, heurefin, nomCourse, dureeMaxPilote, dureeConsMaxPilote);
		eve.modifierCourseStandard(cs, rangCs);
	}
	public void supprimerSceanceEssai (int rang) {
		eve.supprimerSeanceEssai(rang);
	}

	public void ajouterSeanceEssai(Date heuredebut, Date heurefin, String nomCourse, Date dureeMaxPilote, Date dureeConsMaxPilote) {
		SeanceEssai se = new SeanceEssai(heuredebut, heurefin, nomCourse, dureeMaxPilote, dureeConsMaxPilote);
		eve.ajouterSeanceEssai(se);
	}

	public void modifierSeanceEssai(int rangSe, Date heuredebut, Date heurefin, String nomCourse, Date dureeMaxPilote, Date dureeConsMaxPilote) {
		SeanceEssai se = new SeanceEssai(heuredebut, heurefin, nomCourse, dureeMaxPilote, dureeConsMaxPilote);
		eve.modifierSeanceEssai(se, rangSe);
	}

	public void supprimerVoiture (int rang) {
		eve.supprimerVoiture(rang);
	}

	public void ajouterVoiture(int numero, String lienImg, String couleur, int nbToursParRelai) {
		Voiture v = new Voiture(numero, lienImg, couleur, nbToursParRelai);
		eve.ajouterVoiture(v);
	}

	public void modifierVoiture(int rangV, int numero, String lienImg, String couleur, int nbToursParRelai) {
		Voiture v = new Voiture(numero, lienImg, couleur, nbToursParRelai);
		eve.modifierVoiture(v, rangV);
	}

	// méthodes manipulation d'une voiture

	public void supprimerPilote(int rangVoiture, int rangPilote) {
		eve.getVoiture(rangVoiture).supprimerPilote(rangPilote);
	}

	public void ajouterPilote(int rangVoiture, boolean actif, String nom, String couleur, String lienImg) {
		Pilote pilote = new Pilote(actif, nom, couleur, lienImg);
		eve.getVoiture(rangVoiture).ajouterPilote(pilote);
	}

	public void modifierPilote(int rangVoiture, int rangPilote, boolean actif, String nom, String couleur, String lienImg) {
		Pilote pilote = new Pilote(actif, nom, couleur, lienImg);
		eve.getVoiture(rangVoiture).modifierPilote(rangPilote, pilote);
	}

	public void supprimerTrainPneu(int rangVoiture, int rangTrainPneu) {
		eve.getVoiture(rangVoiture).supprimerTrainPneu(rangTrainPneu);
	}

	public void ajouterTrainPneu(int rangVoiture, int numero, int nbtours_pneu, boolean afficher, TypeTrain typetrain, DureteTrain duretetrain) {
		TrainPneu tp = new TrainPneu(numero, nbtours_pneu, afficher, typetrain, duretetrain);
		eve.getVoiture(rangVoiture).ajouterTrainPneu(tp);
	}

	public void modifierTrainPneu(int rangVoiture, int rangTrainPneu, int numero, int nbtours_pneu, boolean afficher, TypeTrain typetrain, DureteTrain duretetrain) {
		TrainPneu tp = new TrainPneu(numero, nbtours_pneu, afficher, typetrain, duretetrain);
		eve.getVoiture(rangVoiture).modifierTrainPneu(rangTrainPneu, tp);
	}



}
