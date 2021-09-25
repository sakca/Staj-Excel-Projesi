package excelApp.entities.concretes;

import org.apache.poi.xssf.usermodel.XSSFCell;
import excelApp.entities.abstracts.Entity;

public class Data implements Entity {

	private int id; // +
	private String gorevliAdi; // +
	private String tcNo; // +
	private String iban; // +
	private double matrah; // + == devreden toplam matrah
	private String gorevYeri;// ++
	private String gorev; // +

	private int gorevId;
	private XSSFCell sayfaAdi;
	private int unvanAdedi; // +

	private float brut; // +
	private float gelirVergisiMatrahi; // +
	private double gelirVergisi;
	private double damgaVergisi; // +
	private double netOdenen;

	public Data() {
		super();
	}

	public Data(int id, String gorevliAdi, String tcNo, String iban, double matrah, String gorevYeri, String gorev) {
		super();
		this.id = id;
		this.gorevliAdi = gorevliAdi;
		this.matrah = matrah;
		this.tcNo = tcNo;
		this.iban = iban;
		this.gorevYeri = gorevYeri;
		this.gorev = gorev;
	}

	public Data(XSSFCell sayfaAdi, String gorevliAdi, double matrah, int id, String tcNo, String iban, String gorev,
			String gorevYeri) {
		super();
		this.sayfaAdi = sayfaAdi;
		this.gorevliAdi = gorevliAdi;
		this.matrah = matrah;
		this.id = id;
		this.tcNo = tcNo;
		this.iban = iban;
		this.gorev = gorev;
		this.gorevYeri = gorevYeri;
	}

	public Data(int gorevId, String gorev, int unvanAdedi) {
		super();
		this.gorevId = gorevId;
		this.gorev = gorev;
		this.unvanAdedi = unvanAdedi;
	}

	public XSSFCell getSayfaAdi() {
		return sayfaAdi;
	}

	public void setSayfaAdi(XSSFCell excelName) {
		this.sayfaAdi = excelName;
	}

	public String getGorevliAdi() {
		return gorevliAdi;
	}

	public void setGorevliAdi(String gorevliAdi) {
		this.gorevliAdi = gorevliAdi;
	}

	public double getMatrah() {
		return matrah;
	}

	public void setMatrah(double matrah) {
		this.matrah = matrah;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTcNo() {
		return tcNo;
	}

	public void setTcNo(String tcNo) {
		this.tcNo = tcNo;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getGorev() {
		return gorev;
	}

	public void setGorev(String gorev) {
		this.gorev = gorev;
	}

	public String getGorevYeri() {
		return gorevYeri;
	}

	public void setGorevYeri(String gorevYeri) {
		this.gorevYeri = gorevYeri;
	}

	public int getGorevId() {
		return gorevId;
	}

	public void setGorevId(int gorevId) {
		this.gorevId = gorevId;
	}

	public int getUnvanAdedi() {
		return unvanAdedi;
	}

	public void setUnvanAdedi(int unvanAdedi) {
		this.unvanAdedi = unvanAdedi;
	}

	public float getBrut() {
		return brut;
	}

	public void setBrut(float brut) {
		this.brut = brut;
	}

	public float getGelirVergisiMatrahi() {
		return gelirVergisiMatrahi;
	}

	public void setGelirVergisiMatrahi(float brutTemp) {
		this.gelirVergisiMatrahi = brutTemp;
	}

	public double getGelirVergisi() {
		return gelirVergisi;
	}

	public void setGelirVergisi(double vergiTemp) {
		this.gelirVergisi = vergiTemp;
	}

	public double getDamgaVergisi() {
		return damgaVergisi;
	}

	public void setDamgaVergisi(double damgaTemp) {
		this.damgaVergisi = damgaTemp;
	}

	public double getNetOdenen() {
		return netOdenen;
	}

	public void setNetOdenen(double net) {
		this.netOdenen = net;
	}

}
