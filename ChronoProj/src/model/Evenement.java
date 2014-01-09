package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;



public class Evenement extends Observable {

	private String nomEvenement;
	private String nomCircuit; 
	private String Longueur;
	private List<CourseStandard> ListCS;
	private List<SeanceEssai> ListSE;
	private List<Voiture> ListVoiture;

	public Evenement() {
		ListCS = new ArrayList<CourseStandard>();
		ListSE = new ArrayList<SeanceEssai>();
		ListVoiture = new ArrayList<Voiture>();	
	}

	public Evenement(String nomEvenement, String nomCircuit, String Longueur) {

		this.setNomEvenement(nomEvenement);
		this.setNomCircuit(nomCircuit);
		this.setLongueur(Longueur);
		ListCS = new ArrayList<CourseStandard>();
		ListSE = new ArrayList<SeanceEssai>();
		ListVoiture = new ArrayList<Voiture>();
	}

	public void setNomEvenement(String nomEvenement) {
		this.nomEvenement = nomEvenement;
	}

	public String getNomEvenement() {
		return nomEvenement;
	}

	public void setNomCircuit(String nomCircuit) {
		this.nomCircuit = nomCircuit;
	}

	public String getNomCircuit() {
		return nomCircuit;
	}

	public void setLongueur(String longueur) {
		Longueur = longueur;
	}

	public String getLongueur() {
		return Longueur;
	}

	public void supprimerSeanceEssai(int rangSe) {
		ListSE.remove(rangSe);
	}

	public void ajouterSeanceEssai(SeanceEssai se) {
		ListSE.add(se);
	}
	
	public void ajouterSeanceEssai() {
		ListSE.add(new SeanceEssai());
	}

	public void supprimerCourseStandard(int rangCs) {
		ListCS.remove(rangCs);
	}

	public void ajouterCourseStandard(CourseStandard cs) {
		ListCS.add(cs);
	}
	
	public void ajouterCourseStandard() {
		ListCS.add(new CourseStandard());
	}

	public void modifierCourseStandard(CourseStandard cs, int rangCs) {
		ListCS.set(rangCs, cs);
	}

	public void modifierSeamodifernceEssai(SeanceEssai se, int rangSe) {
		ListSE.set(rangSe, se);
	}

	public void ajouterVoiture(Voiture v) {
		ListVoiture.add(v);	
	}
	
	public void ajouterVoiture() {
		ListVoiture.add(new Voiture());
	}

	public void supprimerVoiture(int rang) {
		ListVoiture.remove(rang);	
	}

	public void modifierVoiture(Voiture v, int rangV) {
		ListVoiture.set(rangV, v);		
	}

	public Voiture getVoiture(int rang) {
		return ListVoiture.get(rang);
	}

	public CourseStandard getCourseStandard(int rang) {
		return ListCS.get(rang);
	}

	public SeanceEssai getSeanceEssai(int rang) {
		return ListSE.get(rang);
	}

	public boolean isNomVoiturePresent(String num) {
		Iterator<Voiture> itV = ListVoiture.iterator();

		while(itV.hasNext()){
			if (num.equals(itV.next().getNumero())){
				return true;
			}
		}
		return false;
	}
	
	public Voiture getVoiture(String num) {
		Iterator<Voiture> itV = ListVoiture.iterator();
		Voiture v;
		while(itV.hasNext()){
			v = itV.next();
			if (num.equals(v.getNumero())){
				return v;
			}
		}
		return null;
	}
}
