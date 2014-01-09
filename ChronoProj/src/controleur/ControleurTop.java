package controleur;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import model.Course;
import model.Evenement;
import model.Pilote;
import model.Top;
import model.TrainPneu;
import model.Voiture;
import vue.FenetreNewTop;

public class ControleurTop {

	private Evenement eve;
	private Course c;
	private String[] listVoituresSel;
	private String[] listVoitures; 
	private Voiture v;

	public ControleurTop(Evenement eve) {
		this.eve = eve;
	}

	public void setCourse(String course, int id) {
		if (course.equals("Course Standard"))
		{ 
			this.c = eve.getCourseStandard(id);
		}
		else {
			this.c = eve.getSeanceEssai(id);
		}
		
	}

	public void setVoitures(String[] listVoituresSel, String[] listVoitures) {
		this.listVoituresSel = listVoituresSel;
		this.listVoitures = listVoitures;
	}

	public void miseEnPlaceCourse(FenetreNewTop fenetre) {
		fenetre.miseEnPlaceEvenement(c.getNomCourse(), eve.getNomCircuit(), c.getCommentaire());
		fenetre.miseEnPlaceVoitures(listVoitures, listVoituresSel);
		fenetre.miseEnPlaceHeuresTours(c.getHeuredebut(), c.getHeurefin(), c.getNbMaxTours());
	}


	public void miseEnPlacePilotesTrain(String num, FenetreNewTop fenetre) {
		v = eve.getVoiture(num);
		
		List<String> listPilotes = new ArrayList<String>();
		List<String> listTrainsPneu = new ArrayList<String>();

		Iterator<Pilote> itPil = v.getListPilote().iterator();
		Iterator<TrainPneu> itTp = v.getListTrainPneu().iterator();

		while(itPil.hasNext()){
			listPilotes.add(itPil.next().getNom());
		}		
		if (listPilotes.size()!=0) {
			fenetre.miseEnPlacePilotes(listPilotes);
		}
		
		while(itTp.hasNext()){
			listTrainsPneu.add(itTp.next().getNumero());
		}
		if (listTrainsPneu.size()!=0) {
			fenetre.miseEnPlaceTrain(listTrainsPneu);
		}

		fenetre.miseEnPlaceVoiture(v.getNbTour());

	}

	public void topPresse(FenetreNewTop fenetre, String num, String pil, String remarque) {
		v = eve.getVoiture(num);
		v.setNbTour(v.getNbTour()+1);
		
		long temps = fenetre.tempsCourant();
		long tempsEstimTour = temps / v.getNbTour();
		long tempsProchPassage = temps + tempsEstimTour;
		
		v.setTempsDernTour(temps);
		v.setTempsEstimTour(tempsEstimTour);
		v.setTempsProchPassage(tempsProchPassage);
		
		c.ajouterTop(new Top(num,pil,temps/v.getNbTour(),v.getNbTour(),temps,remarque));
		fenetre.miseEnPlaceTop(num, pil, temps/v.getNbTour(),v.getNbTour(),temps, remarque);
		
	}



}
