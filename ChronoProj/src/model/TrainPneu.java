package model;

import java.util.ArrayList;
import java.util.Observable;


public class TrainPneu extends Observable {

	private String numero;
	private Pneu[] tableauDePneus;
	private String typetrain;
	private String duretetrain;
	
	
	public TrainPneu(String numero, String typetrain, String duretetrain) {
		this.setNumero(numero);
		this.setTypetrain(typetrain);
		this.setDuretetrain(duretetrain);
		this.setTableauDePneus(new Pneu[4]);
	}

	public TrainPneu() {
		// TODO Auto-generated constructor stub
	}


	public String getNumero() {
		return numero;
	}


	public void setNumero(String numero) {
		this.numero = numero;
		setChanged();
		notifyObservers("TrainPneu-" + numero);
	}


	public void setTableauDePneus(Pneu[] tableauDePneus) {
		this.tableauDePneus = tableauDePneus;
	}


	public Pneu[] getTableauDePneus() {
		return tableauDePneus;
	}


	public void setTypetrain(String typetrain) {
		this.typetrain = typetrain;
	}


	public String getTypetrain() {
		return typetrain;
	}


	public void setDuretetrain(String duretetrain) {
		this.duretetrain = duretetrain;
	}


	public String getDuretetrain() {
		return duretetrain;
	}
	
	
}

