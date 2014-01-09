package controleur;

import java.util.Observable;
import java.util.Observer;

import model.Evenement;


public class ControleurEvenement {

	private Evenement eve;

	public ControleurEvenement(Evenement eve) {
		this.eve = eve;
	}
	
	public void enregistrerEve(String nom, String circuit, String longueur ) {
		eve.setNomEvenement(nom);
		eve.setNomCircuit(circuit);
		eve.setLongueur(longueur);
	}
	
	public void ajouterCourseSE() {
		eve.ajouterSeanceEssai();
	}
	
	public void ajouterCourseC() {
		eve.ajouterCourseStandard();
	}
	
	public void ajouterV() {
		eve.ajouterVoiture();
	}
	
	public void supprimerCourse(int idC)  {
		eve.supprimerCourseStandard(idC);
	}
	
	public void supprimerSceanceEssai (int idSE) {
		eve.supprimerSeanceEssai(idSE);
	}
	
	public void supprimerVoiture (int rang) {
		eve.supprimerVoiture(rang);
	}

}
