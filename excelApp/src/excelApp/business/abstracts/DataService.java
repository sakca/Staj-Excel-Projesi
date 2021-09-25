package excelApp.business.abstracts;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.table.TableModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excelApp.entities.concretes.Data;
import excelApp.entities.concretes.Input;

public interface DataService {

	void readExcel(File file, FileInputStream excelFIS, BufferedInputStream excelBIS, XSSFWorkbook excelJTableImport);

	ArrayList<Data> gorevli_olustur();

	void exportTables(int index, TableModel model, File file, FileOutputStream excelFOS, BufferedOutputStream excelBOS,
			XSSFWorkbook tableExport, String tur, String tarih);

	void input_olustur(ArrayList<Input> brutListe, int m1, int m2, int m3, int d1, int d2, int d3);

	ArrayList<Input> inputListesi = new ArrayList<>();

	int compareIban(int i);

}




