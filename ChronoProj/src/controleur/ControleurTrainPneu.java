package controleur;

import model.Evenement;
import model.Pneu;
import model.TrainPneu;
import vue.FenetreNewCar;
import vue.FenetreNewTyres;

public class ControleurTrainPneu {

	private Evenement eve;
	private TrainPneu tp;
	private Pneu ARD;
	private Pneu AVD;
	private Pneu ARG;
	private Pneu AVG;

	public ControleurTrainPneu(Evenement eve) {
		this.eve = eve;
	}
	
	public void ajouterObserver(FenetreNewCar fenetre) {
		tp.addObserver(fenetre);
	}
	
	public void setTrainPneu(int idTrainPneu, int idVoiture) {
		tp = eve.getVoiture(idVoiture).getTrainPneu(idTrainPneu);
	}

	public void enregistrerTrainPneu(String numero, String type, String durete) {
		tp.setDuretetrain(durete);
		tp.setNumero(numero);
		tp.setTypetrain(type);
		Pneu[] tableauDePneus = new Pneu[4];
		tableauDePneus[0] = ARD;
		tableauDePneus[1] = AVD;
		tableauDePneus[2] = ARG;
		tableauDePneus[3] = AVG;
		tp.setTableauDePneus(tableauDePneus);

	}

	public void enregistrerPneuARD(String pressionARD, String tempExtARD, String tempIntARD, String tempsMedARD) {
		ARD = new Pneu(pressionARD, tempExtARD, tempIntARD, tempsMedARD);
	}

	public void enregistrerPneuAVD(String pressionAVD, String tempExtAVD, String tempIntAVD, String tempsMedAVD) {
		AVD = new Pneu(pressionAVD, tempExtAVD, tempIntAVD, tempsMedAVD);
	}

	public void enregistrerPneuARG(String pressionARG, String tempExtARG, String tempIntARG, String tempsMedARG) {
		ARG = new Pneu(pressionARG, tempExtARG, tempIntARG, tempsMedARG);
	}

	public void enregistrerPneuAVG(String pressionAVG, String tempExtAVG, String tempIntAVG, String tempsMedAVG) {
		AVG = new Pneu(pressionAVG, tempExtAVG, tempIntAVG, tempsMedAVG);
	}

	public void modifierCourse (FenetreNewTyres fenetre) {
		fenetre.modifierTrainPneu(tp.getNumero(), tp.getTypetrain(), tp.getDuretetrain());
		Pneu ARD = tp.getTableauDePneus()[0];
		fenetre.modifierPneuARD(ARD.getPression(), ARD.getTemps_ext(), ARD.getTemps_int(), ARD.getTemps_centr());
		Pneu AVD = tp.getTableauDePneus()[1];
		fenetre.modifierPneuAVD(AVD.getPression(), AVD.getTemps_ext(), AVD.getTemps_int(), AVD.getTemps_centr());
		Pneu ARG = tp.getTableauDePneus()[2];
		fenetre.modifierPneuARG(ARG.getPression(), ARG.getTemps_ext(), ARG.getTemps_int(), ARG.getTemps_centr());
		Pneu AVG = tp.getTableauDePneus()[3];
		fenetre.modifierPneuAVG(AVG.getPression(), AVG.getTemps_ext(), AVG.getTemps_int(), AVG.getTemps_centr());
	}
}
