package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Voiture extends Observable {

	private String numero;
	private String lienImg;
	private String couleur;
	private int nbToursParRelai;
	private List<TrainPneu> ListTrainPneu;
	private List<Pilote> ListPilote;
	private List<Top> ListTop;
	private int nbTour;
	private long tempsProchPassage;
	private long tempsDernTour;
	private long tempsEstimTour;
	private int relaisEnCours;
	
	public Voiture (String numero, String lienImg, String couleur, int nbToursParRelai) {
		this.numero = numero;
		this.couleur = couleur;
		this.lienImg = lienImg;
		this.nbTour = 0;
		this.tempsDernTour = 0;
		this.tempsEstimTour = 0;
		this.relaisEnCours = 0;
		this.tempsProchPassage = 0;
		this.nbToursParRelai = nbToursParRelai;
		ListTrainPneu = new ArrayList<TrainPneu>();
		ListPilote = new ArrayList<Pilote>();
		ListTop = new ArrayList<Top>();
	}
	
	public long getTempsProchPassage() {
		return tempsProchPassage;
	}
	public void setTempsProchPassage(long tempsProchPassage) {
		this.tempsProchPassage = tempsProchPassage;
	}
	public List<TrainPneu> getListTrainPneu() {
		return ListTrainPneu;
	}

	public List<Pilote> getListPilote() {
		return ListPilote;
	}

	public List<Top> getListTop() {
		return ListTop;
	}

	public void setListTrainPneu(List<TrainPneu> listTrainPneu) {
		ListTrainPneu = listTrainPneu;
	}

	public void setListPilote(List<Pilote> listPilote) {
		ListPilote = listPilote;
	}

	public void setListTop(List<Top> listTop) {
		ListTop = listTop;
	}

	public long getTempsDernTour() {
		return tempsDernTour;
	}

	public long getTempsEstimTour() {
		return tempsEstimTour;
	}

	public int getRelaisEnCours() {
		return relaisEnCours;
	}

	public void setTempsDernTour(long tempsDernTour) {
		this.tempsDernTour = tempsDernTour;
	}

	public void setTempsEstimTour(long tempsEstimTour) {
		this.tempsEstimTour = tempsEstimTour;
	}

	public void setRelaisEnCours(int relaisEnCours) {
		this.relaisEnCours = relaisEnCours;
	}

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}	

	public Voiture () {
		ListTrainPneu = new ArrayList<TrainPneu>();
		ListPilote = new ArrayList<Pilote>();
		ListTop = new ArrayList<Top>();
	}	

	public String getLienImg() {
		return lienImg;
	}

	public void setLienImg(String lienImg) {
		this.lienImg = lienImg;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public int getNbToursParRelai() {
		return nbToursParRelai;
	}

	public void setNbToursParRelai(int nbToursParRelai) {
		this.nbToursParRelai = nbToursParRelai;
	}

	public void setNumero(String numero) {
		this.numero = numero;
		setChanged();
		notifyObservers("Voiture-" + numero);
	}

	public String getNumero() {
		return numero;

	}
	public void supprimerTrainPneu(int rang) {
		ListTrainPneu.remove(rang);	
	}

	public void ajouterTrainPneu(TrainPneu tp) {
		ListTrainPneu.add(tp);
	}
	
	public void ajouterTrainPneu() {
		ListTrainPneu.add(new TrainPneu());
	}

	public void modifierTrainPneu(int rang, TrainPneu tp) {
		ListTrainPneu.set(rang, tp);
	}

	public TrainPneu getTrainPneu(int rang) {
		return ListTrainPneu.get(rang);
	}

	public void supprimerPilote(int rang) {
		ListPilote.remove(rang);
	}

	public void ajouterPilote(Pilote p) {
		ListPilote.add(p);
	}

	public void ajouterPilote() {
		ListPilote.add(new Pilote());
	}
	
	public void modifierPilote(int rang, Pilote p) {
		ListPilote.set(rang, p);
	}

	public Pilote getPilote(int rang) {
		return ListPilote.get(rang);
	}

	public void supprimerTop(int rang) {
		ListTop.remove(rang);
	}

	public void ajouterTop(Top p) {
		ListTop.add(p);
	}

	public void modifierTop(int rang, Top t) {
		ListTop.set(rang, t);
	}

	public Top getTop(int rang) {
		return ListTop.get(rang);
	}


}
