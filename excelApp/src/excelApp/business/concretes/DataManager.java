package excelApp.business.concretes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import javax.swing.table.TableModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import excelApp.business.abstracts.DataService;
import excelApp.dataAccess.abstracts.DataDao;
import excelApp.dataAccess.concretes.PoiDataDao;
import excelApp.entities.concretes.Data;
import excelApp.entities.concretes.Input;

public class DataManager implements DataService {

	// private Data data;
	Input input = new Input();
	ArrayList<Data> gorevliL;
	ArrayList<Data> gorevList;
	DataDao dataDao = new PoiDataDao();

	public DataManager() {
		super();
	}

	public DataManager(DataDao dataDao) {
		super();
		this.dataDao = dataDao;
	}

	public DataManager(ArrayList<Data> gorevliL) {
		super();
		this.gorevliL = gorevliL;
	}

	@Override
	public void readExcel(File file, FileInputStream excelFIS, BufferedInputStream excelBIS,
			XSSFWorkbook excelJTableImport) {

		dataDao.getDataFromExcel(file, excelFIS, excelBIS, excelJTableImport);
		System.out.println("Dosya okuma basarili.");

	}

	@Override
	public void input_olustur(ArrayList<Input> brutListe, int vergiM1, int vergiM2, int vergiM3, int dilim1, int dilim2,
			int dilim3) {

		System.out.println(" veriler alindi.   ");

		float brutTemp; // brut, gelir vergisi matrahi, damga vergisi
		double damgaTemp; // gelir vergisi, net odenen
		for (int k = 0; k < gorevliL.size(); k++) {
			for (int j = 0; j < brutListe.size(); j++) {

				if (gorevliL.get(k).getGorev().equals(brutListe.get(j).getGorev())) {

					brutTemp = brutListe.get(j).getBrut();
					gorevliL.get(k).setBrut(brutTemp);
					gorevliL.get(k).setGelirVergisiMatrahi(brutTemp); // brut deger = gelir vergisi matrahi
					damgaTemp = brutTemp * ((double) 759 / 1000); // 0,759 olan oranýnýn yuzdelik degeri alinir
					damgaTemp = Math.round(damgaTemp); // yakin degere yuvarlanir
					damgaTemp = damgaTemp / 100;// alinan degerin yuzdesi hesaplanir
					gorevliL.get(k).setDamgaVergisi(damgaTemp);

				}
			}
		}

		double gvmTemp, matTemp, farkTemp1, farkTemp2, gvTemp1, gvTemp2;
		for (int i = 0; i < gorevliL.size(); i++) {

			gvmTemp = gorevliL.get(i).getGelirVergisiMatrahi();
			matTemp = gorevliL.get(i).getMatrah();

			if (matTemp + gvmTemp <= vergiM1) {
				gvTemp1 = gvmTemp * ((float) dilim1 / 100); // %15 ini bulmak, dilim deðiþebileceði için atadýk
				gvTemp1 = Math.round(gvTemp1 * 100) / 100.0d;
				gorevliL.get(i).setGelirVergisi(gvTemp1);

			}

			else if ((matTemp + gvmTemp) > vergiM1 && (matTemp + gvmTemp) <= vergiM2) {

				if (matTemp < vergiM1 && (matTemp + gvmTemp) != vergiM2) {
					// m1e gore sinir degerde degilse ve m2ye esit degilse

					farkTemp1 = (matTemp + gvmTemp) - vergiM1;
					gvTemp1 = farkTemp1 * ((float) dilim2 / 100); // yuvarlanacak

					farkTemp2 = gvmTemp - farkTemp1;
					gvTemp2 = farkTemp2 * ((float) dilim1 / 100);
					gorevliL.get(i).setGelirVergisi(Math.round((gvTemp1 + gvTemp2) * 100) / 100.0d);

				}

				else { // esitse veya sinirda degilse

					gvTemp1 = gvmTemp * ((float) dilim2 / 100);
					gvTemp1 = Math.round((gvTemp1) * 100) / 100.0d;
					gorevliL.get(i).setGelirVergisi((gvTemp1));
				}

			}

			else if ((matTemp + gvmTemp) > vergiM2 && (matTemp + gvmTemp) <= vergiM3) {

				if (matTemp < vergiM2 && (matTemp + gvmTemp) != vergiM3) {

					farkTemp1 = (matTemp + gvmTemp) - vergiM2;
					gvTemp1 = farkTemp1 * ((float) dilim3 / 100);

					farkTemp2 = gvmTemp - farkTemp1;
					gvTemp2 = farkTemp2 * ((float) dilim2 / 100);

					gorevliL.get(i).setGelirVergisi(Math.round((gvTemp1 + gvTemp2) * 100) / 100.0d);
				}

				else {
					gvTemp1 = gvmTemp * ((float) dilim3 / 100);
					gvTemp1 = Math.round((gvTemp1) * 100) / 100.0d;
					gorevliL.get(i).setGelirVergisi((gvTemp1));
				}

			}

			double net = gorevliL.get(i).getBrut()
					- (gorevliL.get(i).getGelirVergisi() + gorevliL.get(i).getDamgaVergisi());

			net = Math.round(net * 100) / 100.0d;
			gorevliL.get(i).setNetOdenen(net);

		}

	}

	@Override
	public ArrayList<Data> gorevli_olustur() {
		gorevliL = dataDao.gorevli_olustur();

		return gorevliL;
	}

	@Override
	public void exportTables(int index, TableModel model, File file, FileOutputStream excelFOS,
			BufferedOutputStream excelBOS, XSSFWorkbook tableExport, String tur, String tarih) {

		if (tur.toLowerCase().equals("src")) {
			String sinavTuru = "SRC SINAVI";
			dataDao.writetoExcel(index, model, file, excelFOS, excelBOS, tableExport, sinavTuru, tarih);
		} else if (tur.toLowerCase().equals("mtsk")) {

			if (index == 3 || index == 4) {
				String sinavTuru2 = "DÝREKSÝYON SINAVI";
				dataDao.writetoExcel(index, model, file, excelFOS, excelBOS, tableExport, sinavTuru2, tarih);
			}

			else {
				String sinavTuru = "MOTORLU TAÞIT SÜRÜCÜ KURSLARI DÝREKSÝYON EÐÝTÝMÝ UYGULAMA SINAVI";
				dataDao.writetoExcel(index, model, file, excelFOS, excelBOS, tableExport, sinavTuru, tarih);
			}

		} else if (tur.toLowerCase().equals("iþ makineleri") || tur.toLowerCase().equals("is makineleri")) {
			String sinavTuru = "ÝÞ MAKÝNELERÝ EÐÝTÝMÝ UYGULAMA SINAVI";
			dataDao.writetoExcel(index, model, file, excelFOS, excelBOS, tableExport, sinavTuru, tarih);
		}

	}

	@Override
	public int compareIban(int i) {
		// TR97000120..... diye ilerleyen banýn
		// TR97 '00012' 0... kýsmý lazým
		// halk bank = 00012

		String ibanTemp, mainPart;
		ibanTemp = gorevliL.get(i).getIban();
		ibanTemp.trim();// bosluk varsa siler
		mainPart = ibanTemp.substring(4, 9);
		String banka = "00012";

		if (mainPart.compareTo(banka) == 0) { // esit durumu == havale
			return -1;
			// return 1; // eft
		}

		else {
			return 1;
//			return -1; // havale 
		}

	}

}
