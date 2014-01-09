package model;

import java.sql.Date;

public class Top {

	
	private int nbToursCons;
	private int nbToursTot;
	private Date tempsDernierTop;
	private Date HeurePassage;
	private int nbTour;
	private long l;
	private String pil;
	private String num;
	private long temps;

	public Top(String num, String pil, long l, int nbTour, long temps,
			String remarque) {
		this.num = num;
		this.pil = pil;
		this.l = l;
		this.nbTour = nbTour;
		this.temps = temps;		
	}

	public int getNbTour() {
		return nbTour;
	}

	public long getL() {
		return l;
	}

	public String getPil() {
		return pil;
	}

	public String getNum() {
		return num;
	}

	public long getTemps() {
		return temps;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}

	public void setL(long l) {
		this.l = l;
	}

	public void setPil(String pil) {
		this.pil = pil;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public void setTemps(long temps) {
		this.temps = temps;
	}

	public int getNbToursCons() {
		return nbToursCons;
	}

	public void setNbToursCons(int nbToursCons) {
		this.nbToursCons = nbToursCons;
	}

	public int getNbToursTot() {
		return nbToursTot;
	}

	public void setNbToursTot(int nbToursTot) {
		this.nbToursTot = nbToursTot;
	}

	public Date getTempsDernierTop() {
		return tempsDernierTop;
	}

	public void setTempsDernierTop(Date tempsDernierTop) {
		this.tempsDernierTop = tempsDernierTop;
	}


	public void setHeurePassage(Date heurePassage) {
		HeurePassage = heurePassage;
	}

	public Date getHeurePassage() {
		return HeurePassage;
	}
	
}
