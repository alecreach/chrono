package model;

import java.util.Observable;

public class Pilote extends Observable{

	private boolean actif;
	private String nom;
	private String couleur;
	private String lienImg;
	
	public Pilote(boolean actif, String nom, String couleur, String lienImg) {
		this.actif = actif;
		this.nom = nom;
		this.couleur = couleur;
		this.lienImg = lienImg;
	
	}

	public Pilote() {
		// TODO Auto-generated constructor stub
	}

	public void setActif(boolean actif) {
		this.actif = actif;
	}

	public boolean isActif() {
		return actif;
	}

	public void setNom(String nom) {
		this.nom = nom;
		setChanged();
		notifyObservers("Pilote-" + nom);
	}

	public String getNom() {
		return nom;
	}

	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}

	public String getCouleur() {
		return couleur;
	}

	public void setLienImg(String lienImg) {
		this.lienImg = lienImg;
	}

	public String getLienImg() {
		return lienImg;
	}

}
