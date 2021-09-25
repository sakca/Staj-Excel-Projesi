package excelApp.dataAccess.abstracts;

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

public interface DataDao {

	void getDataFromExcel(File file, FileInputStream excelFIS, BufferedInputStream excelBIS,
			XSSFWorkbook excelJTableImport);

	void deleteInput(Input input);

	void writetoExcel(int index, TableModel model, File file, FileOutputStream excelFOS, BufferedOutputStream excelBOS,
			XSSFWorkbook tableExport, String tur, String tarih);

	ArrayList<Data> gorevliListesi = new ArrayList<>();

	ArrayList<Data> gorevli_olustur();

	ArrayList<Data> gorevler = new ArrayList<>();

	ArrayList<Data> gorev_tut();

}
