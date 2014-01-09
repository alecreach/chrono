package model;

public class Pneu {

	private String pression;
	private String temps_ext;
	private String temps_int;
	private String temps_centr;
	
	
	public Pneu(String pression, String temps_ext, String temps_int, String temps_centr) {
		this.setPression(pression);
		this.setTemps_ext(temps_ext);
		this.setTemps_int(temps_int);
		this.setTemps_centr(temps_centr);
	}


	public String getPression() {
		return pression;
	}


	public void setPression(String pression) {
		this.pression = pression;
	}


	public String getTemps_ext() {
		return temps_ext;
	}


	public void setTemps_ext(String temps_ext) {
		this.temps_ext = temps_ext;
	}


	public String getTemps_int() {
		return temps_int;
	}


	public void setTemps_int(String temps_int) {
		this.temps_int = temps_int;
	}


	public String getTemps_centr() {
		return temps_centr;
	}


	public void setTemps_centr(String temps_centr) {
		this.temps_centr = temps_centr;
	}
	
	
	
}
