package controleur;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Evenement;
import model.Pilote;
import model.TrainPneu;
import model.Voiture;

import vue.FenetreNewCar;
import vue.FenetreNewEvent;

public class ControleurVoiture {

	private Evenement eve;
	private Voiture v;

	public ControleurVoiture(Evenement eve) {
		this.eve = eve;
	}

	public void setVoiture(int idVoiture) {
		v = eve.getVoiture(idVoiture);
	}

	public void enregistrerVoiture(String num, String couleur, int nbTourRelai, String lien, FenetreNewCar fenetre, boolean isEdit) {
		if (!eve.isNomVoiturePresent(num)||isEdit){
		v.setCouleur(couleur);
		v.setLienImg(lien);
		v.setNumero(num);
		v.setNbToursParRelai(nbTourRelai);
		fenetre.fermerFenetre();
		}
		else {
			fenetre.popUpErreurVoiture();
		}
	}
	
	public void ajouterTP() {
		v.ajouterTrainPneu();
	}
	
	public void ajouterPil() {
		v.ajouterPilote();
	}

	public void ajouterObserver(FenetreNewEvent fenetre) {
		v.addObserver(fenetre);
	}
	
	public void supprimerTrainPneu(int idTrainPneu) {
		v.supprimerTrainPneu(idTrainPneu);
	}

	public void supprimerPilote(int idPilote) {
		v.supprimerPilote(idPilote);
	}

	public void supprimerTop(int idTop) {
		v.supprimerTop(idTop);
	}

	public void modifierCourse (FenetreNewCar fenetre) {
		
		List<String> listPilotes = new ArrayList<String>();
		List<String> listTrainsPneu = new ArrayList<String>();
		
		Iterator<Pilote> itPil = v.getListPilote().iterator();
		Iterator<TrainPneu> itTp = v.getListTrainPneu().iterator();

		while(itPil.hasNext()){
			listPilotes.add(itPil.next().getNom());
		}
		while(itTp.hasNext()){
			listTrainsPneu.add(itTp.next().getNumero());
		}
		
		fenetre.modifierVoiture(v.getNumero(), v.getCouleur(), v.getNbToursParRelai(), v.getLienImg(), listPilotes, listTrainsPneu);
	}
}
