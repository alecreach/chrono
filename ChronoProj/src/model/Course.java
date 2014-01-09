package model;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Course extends Observable{

	protected Date heuredebut;
	protected Date heurefin;
	protected String nomCourse;
	protected Date dureeMaxPilote;
	protected Date dureeConsMaxPilote;
	protected String commentaire;
	protected int nbMaxTours;
	protected List<Voiture> listeVoiture;
	protected List<Top> ListeTop;
	protected List<Classement> ListeClassement; 

	public Course(Date heuredebut, Date heurefin, String nomCourse, Date dureeMaxPilote, Date dureeConsMaxPilote, String commentaire, int nbMaxTours) {
		this.setNbMaxTours(nbMaxTours);
		this.setCommentaire(commentaire); 
		this.setHeuredebut(heuredebut);
		this.setHeurefin(heurefin);
		this.nomCourse = nomCourse; 
		this.setDureeMaxPilote(dureeMaxPilote);
		this.setDureeConsMaxPilote(dureeConsMaxPilote);
		this.listeVoiture = new ArrayList<Voiture>();
		this.ListeTop = new ArrayList<Top>();
		ListeClassement = new ArrayList<Classement>();

	}

	public Course() {
		this.listeVoiture = new ArrayList<Voiture>();
		this.ListeTop = new ArrayList<Top>();
		ListeClassement = new ArrayList<Classement>();
	}

	public void ajouterTop(Top t) {
		ListeTop.add(t);
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public void setHeuredebut(Date heuredebut) {
		this.heuredebut = heuredebut;
	}

	public Date getHeuredebut() {
		return heuredebut;
	}

	public void setHeurefin(Date heurefin) {
		this.heurefin = heurefin;
	}

	public Date getHeurefin() {
		return heurefin;
	}

	public String getNomCourse() {
		return nomCourse;
	}

	public void setDureeMaxPilote(Date dureeMaxPilote) {
		this.dureeMaxPilote = dureeMaxPilote;
	}

	public Date getDureeMaxPilote() {
		return dureeMaxPilote;
	}

	public void setDureeConsMaxPilote(Date dureeConsMaxPilote) {
		this.dureeConsMaxPilote = dureeConsMaxPilote;
	}

	public Date getDureeConsMaxPilote() {
		return dureeConsMaxPilote;
	}

	public int getNbMaxTours() {
		return nbMaxTours;
	}

	public void setNbMaxTours(int nbMaxTours) {
		this.nbMaxTours = nbMaxTours;
	}

}
