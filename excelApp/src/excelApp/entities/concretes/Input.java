package excelApp.entities.concretes;

import excelApp.entities.abstracts.Entity;

public class Input implements Entity {

	private int idInput;
	private float brut;
	private String gorev;
	private String sinavTarihi;
	private String sinavTur;
	private int value[];

	public Input() {
		super();
	}

	public Input(int idInput, String gorev, float brut) {
		super();
		this.setIdInput(idInput);
		this.setBrut(brut);
		this.setGorev(gorev);
	}

	public int getIdInput() {
		return idInput;
	}

	public void setIdInput(int idInput) {
		this.idInput = idInput;
	}

	public float getBrut() {
		return brut;
	}

	public void setBrut(float brut) {
		this.brut = brut;
	}

	public String getGorev() {
		return gorev;
	}

	public void setGorev(String gorev) {
		this.gorev = gorev;
	}

	public int[] getValue() {
		return value;
	}

	public void setValue(int value[]) {
		this.value = value;
	}

	public String getSinavTarihi() {
		return sinavTarihi;
	}

	public void setSinavTarihi(String string) {
		this.sinavTarihi = string;
	}

	public String getSinavTur() {
		return sinavTur;
	}

	public void setSinavTur(String sinavTur) {
		this.sinavTur = sinavTur;
	}

}
