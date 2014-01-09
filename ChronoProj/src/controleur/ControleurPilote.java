package controleur;

import model.Evenement;
import model.Pilote;
import vue.FenetreNewCar;
import vue.FenetreNewPilote;

public class ControleurPilote {

	private Evenement eve;
	private Pilote pil;

	public ControleurPilote(Evenement eve) {
		this.eve = eve;
	}

	public void setPilote(int idVoiture, int idPilote ) {
		pil = eve.getVoiture(idVoiture).getPilote(idPilote);
	}

	public void enregistrerPil(String nomPilote, String couleur, String lien) {
		pil.setCouleur(couleur);
		pil.setNom(nomPilote);
		pil.setLienImg(lien);
	}

	public void ajouterObserver(FenetreNewCar fenetre) {
		pil.addObserver(fenetre);
	}
	
	public void modifierCourse (FenetreNewPilote fenetre) {
		fenetre.modifierPil(pil.getNom(), pil.getCouleur(), pil.getLienImg());
	}

}
