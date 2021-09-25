package excelApp.dataAccess.concretes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excelApp.dataAccess.abstracts.DataDao;
import excelApp.entities.concretes.Data;
import excelApp.entities.concretes.Input;

public class PoiDataDao implements DataDao {

	Data data = new Data();
	// XSSFCell excelName;

	@Override
	public void getDataFromExcel(File excelFile, FileInputStream excelFIS, BufferedInputStream excelBIS,
			XSSFWorkbook excelJTableImport) {

		String sheetNum = JOptionPane.showInputDialog("Dosyadaki 'Görevliler' sayfasýnýn, sayfa numarasýný giriniz.");
		int sheet = Integer.parseInt(sheetNum);

		XSSFSheet excelSheet = excelJTableImport.getSheetAt(sheet - 1);

		int id = 0;

		for (int row = 4; row < excelSheet.getLastRowNum() + 1; row++) {
			XSSFRow excelRow = excelSheet.getRow(row);// row deðerinin

			int column = 0;
			// int unvanAdedi = 1;

			int dataID = id;

			Cell cellAd = excelRow.getCell(column);
			String dataAD = cellAd.getStringCellValue();// data.setGorevliAdi(excelRow.getCell(column)); // 0 ad soyad

			Cell cellTC = excelRow.getCell(column + 1);// 1 tc no
			String dataTC = (String.valueOf((long) cellTC.getNumericCellValue())); // Tc num = numeric cell

			Cell cellGYer = excelRow.getCell(column + 2);
			String dataGYer = cellGYer.getStringCellValue(); // data.setGorevYeri(excelRow.getCell(column + 2)); //
																// kurum
			Cell cellGorev = excelRow.getCell(column + 4);
			String dataGorev = cellGorev.getStringCellValue(); // .setGorev(cell2.getStringCellValue()); //
																// data.setGorev(excelRow.getCell(column + 4)); // gorev

			Cell cellIban = excelRow.getCell(column + 5); // data.setIban(excelRow.getCell(column + 5)); // 4 iban
			String dataIban = cellIban.getStringCellValue();

			Cell cellMatr = excelRow.getCell(column + 8); // data.setMatrah(cell3.getNumericCellValue());
			double dataMatr = cellMatr.getNumericCellValue();
			double dataMatrformat = Math.round(dataMatr * 100.0) / 100.0;

			gorevliListesi.add(new Data(dataID, dataAD, dataTC, dataIban, dataMatrformat, dataGYer, dataGorev));

			id++;
//			if (id != data.getGorevId()) {
//				gorevler.add(new Data(data.getGorevId(), data.getGorev(), data.getUnvanAdedi()));
//			}

		}

	}

	@Override
	public ArrayList<Data> gorevli_olustur() {

		return gorevliListesi;
	}

	@Override
	public ArrayList<Data> gorev_tut() {

		return gorevler;
	}

	@Override
	public void writetoExcel(int index, TableModel model, File exportFile, FileOutputStream excelFOS,
			BufferedOutputStream excelBOS, XSSFWorkbook tableExport, String tur, String tarih) { 

		if (index == 2) {

			XSSFSheet Spreadsheet = tableExport.createSheet(tarih + " BORDROSU");

			XSSFFont font = tableExport.createFont(); // bold font
			font.setFontHeightInPoints((short) 10);
			font.setFontName("Arial Unicode MS");
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(true);
			font.setItalic(false);

			CellStyle cellStyle = tableExport.createCellStyle(); // bold ve center
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setFont(font);

			String title = "DÜZCE ÝL MÝLLÝ EÐÝTÝM MÜDÜRLÜÐÜ DÖNER SERMAYE ÝÞLETMESÝ SINAV GÖREVLÝ ÜCRETÝ BORDROSU";
			XSSFRow titleRow = Spreadsheet.createRow(0);
			XSSFCell titleCell = titleRow.createCell(0);
			Spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 10));
			titleCell.setCellValue(title);
			titleCell.setCellStyle(cellStyle); // bold and center

			CellStyle style1 = tableExport.createCellStyle();
			style1.setFont(font); // bold arial
			XSSFRow adRow = Spreadsheet.createRow(1);
			XSSFCell adCell = adRow.createCell(0);
			Spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 1));
			adCell.setCellValue("SINAV ADI");
			adCell.setCellStyle(style1);
			XSSFCell turCell = adRow.createCell(2);
			turCell.setCellValue(tur);
			turCell.setCellStyle(style1);

			XSSFRow tarihRow = Spreadsheet.createRow(2);
			XSSFCell tarihCell = tarihRow.createCell(0);
			Spreadsheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 1));
			tarihCell.setCellValue("SINAV TARÝHÝ");
			tarihCell.setCellStyle(style1);
			XSSFCell sinavTarihCell = tarihRow.createCell(2);
			sinavTarihCell.setCellValue(tarih);
			sinavTarihCell.setCellStyle(style1);

			XSSFFont font2 = tableExport.createFont(); // bold font
			font2.setFontHeightInPoints((short) 10);
			font2.setFontName("Times New Roman");
			font2.setColor(IndexedColors.BLACK.getIndex());
			font2.setBold(false);
			font2.setItalic(false);

			CellStyle style2 = tableExport.createCellStyle();
			style2.setVerticalAlignment(VerticalAlignment.CENTER);
			style2.setAlignment(HorizontalAlignment.CENTER);
			style2.setFont(font2); // normal times ve center

			XSSFRow columnNameRow = Spreadsheet.createRow(3);
			for (int i = 0; i < model.getColumnCount(); i++) {
				XSSFCell cNamesCell = columnNameRow.createCell(i);
				cNamesCell.setCellValue(model.getColumnName(i));
				cNamesCell.setCellStyle(style2);
				// Spreadsheet.autoSizeColumn(i);
			}

			for (int j = 4; j < model.getRowCount(); j++) {
				XSSFRow firstRow = Spreadsheet.createRow(j);
				for (int i = 0; i < model.getColumnCount(); i++) {
					XSSFCell cellCreate = firstRow.createCell(i);
					cellCreate.setCellValue(model.getValueAt(j - 4, i).toString());
					cellCreate.setCellStyle(style2);
					Spreadsheet.autoSizeColumn(i);

				}
			}

			String toplam = "GENEL TOPLAMLAR";
			XSSFRow toplamRow = Spreadsheet.createRow(model.getRowCount());
			XSSFCell toplamCell = toplamRow.createCell(4);
			toplamCell.setCellValue(toplam);
			toplamCell.setCellStyle(cellStyle);
			XSSFCell btCell = toplamRow.createCell(5);
			XSSFCell gvmCell = toplamRow.createCell(6);
			XSSFCell gvCell = toplamRow.createCell(8);
			XSSFCell dmCell = toplamRow.createCell(9);
			XSSFCell netOdCell = toplamRow.createCell(10);

			double tb = 0, tgvm = 0, tgv = 0, tdm = 0, tnet = 0;
			for (int i = 4; i < model.getRowCount(); i++) {
				XSSFRow topRow = Spreadsheet.getRow(i);

				XSSFCell brtopCell = topRow.getCell(5);
				double br = Double.parseDouble(String.valueOf(brtopCell));
				tb = tb + br;

				XSSFCell gvmtopCell = topRow.getCell(6);
				double gvm = Double.parseDouble(String.valueOf(gvmtopCell));
				tgvm = tgvm + gvm;

				XSSFCell gvtopCell = topRow.getCell(8);
				double gv = Double.parseDouble(String.valueOf(gvtopCell));
				tgv = gv + tgv;

				XSSFCell dmtopCell = topRow.getCell(9);
				double dm = Double.parseDouble(String.valueOf(dmtopCell));
				tdm = tdm + dm;

				XSSFCell nettopCell = topRow.getCell(10);
				double net = Double.parseDouble(String.valueOf(nettopCell));
				tnet = tnet + net;

			}

			Spreadsheet.setColumnWidth(5, 5000);
			Spreadsheet.setColumnWidth(8, 5000);
			Spreadsheet.setColumnWidth(9, 5000);
			Spreadsheet.setColumnWidth(10, 5000);

			btCell.setCellValue(tb);
			btCell.setCellStyle(style2);

			gvmCell.setCellValue(tgvm);
			gvmCell.setCellStyle(style2);

			gvCell.setCellValue(tgv);
			gvCell.setCellStyle(style2);

			dmCell.setCellValue(tdm);
			dmCell.setCellStyle(style2);

			netOdCell.setCellValue(tnet);
			netOdCell.setCellStyle(style2);

			String gercGorevli = "Gerçekleþtirme Görevlisi";
			XSSFRow gercRow = Spreadsheet.createRow(model.getRowCount() + 3);
			XSSFCell gercCell = gercRow.createCell(1);
			Spreadsheet.addMergedRegion(new CellRangeAddress(model.getRowCount() + 3, model.getRowCount() + 3, 1, 2));
			gercCell.setCellValue(gercGorevli);
			gercCell.setCellStyle(style2);

			String hyetki = "Harcama Yetkilisi";
			XSSFCell hyetkiCell = gercRow.createCell(8);
			Spreadsheet.addMergedRegion(new CellRangeAddress(model.getRowCount() + 3, model.getRowCount() + 3, 8, 10));
			hyetkiCell.setCellValue(hyetki);
			hyetkiCell.setCellStyle(style2);

			String gIsim = "Mustafa TERZÝOÐLU";
			XSSFRow gRow = Spreadsheet.createRow(model.getRowCount() + 6);
			XSSFCell gCell = gRow.createCell(1);
			Spreadsheet.addMergedRegion(new CellRangeAddress(model.getRowCount() + 6, model.getRowCount() + 6, 1, 2));
			gCell.setCellValue(gIsim);
			gCell.setCellStyle(style2);

			String meMudur = "Tamer KIRBAÇ";
			XSSFCell mudCell = gRow.createCell(8);
			Spreadsheet.addMergedRegion(new CellRangeAddress(model.getRowCount() + 6, model.getRowCount() + 6, 8, 10));
			mudCell.setCellValue(meMudur);
			mudCell.setCellStyle(style2);

			String gunvan = "Ýl Milli Eðitim Þube Müdürü";
			XSSFRow unvRow = Spreadsheet.createRow(model.getRowCount() + 7);
			XSSFCell unvCell = unvRow.createCell(1);
			Spreadsheet.addMergedRegion(new CellRangeAddress(model.getRowCount() + 7, model.getRowCount() + 7, 1, 2));
			unvCell.setCellValue(gunvan);
			unvCell.setCellStyle(style2);

			String mudur = "Ýl Milli Eðitim Müdürü";
			XSSFCell mudurCell = unvRow.createCell(8);
			Spreadsheet.addMergedRegion(new CellRangeAddress(model.getRowCount() + 7, model.getRowCount() + 7, 8, 10));
			mudurCell.setCellValue(mudur);
			mudurCell.setCellStyle(style2);

		}

//		else if (index == 3 || index==4) {
//			XSSFSheet Spreadsheet = tableExport.createSheet(tarih + " Ödeme Listesi");
//
//			XSSFFont font = tableExport.createFont(); // bold font
//			font.setFontHeightInPoints((short) 10);
//			font.setFontName("Arial Unicode MS");
//			font.setColor(IndexedColors.BLACK.getIndex());
//			font.setBold(true);
//			font.setItalic(false);
//
//			XSSFFont font2 = tableExport.createFont();
//			font2.setFontHeightInPoints((short) 10);
//			font2.setFontName("Calibri");
//			font2.setColor(IndexedColors.BLACK.getIndex());
//			font2.setBold(false);
//			font2.setItalic(false);
//
//			CellStyle tableCellSty = tableExport.createCellStyle();
//			tableCellSty.setVerticalAlignment(VerticalAlignment.CENTER);
//			tableCellSty.setAlignment(HorizontalAlignment.CENTER);
//			tableCellSty.setFont(font2);
//
//			CellStyle cellStyle = tableExport.createCellStyle();
//			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//			cellStyle.setAlignment(HorizontalAlignment.CENTER);
//			cellStyle.setFont(font);
//
//			CellStyle style1 = tableExport.createCellStyle();
//			style1.setFont(font);
//
//			CellStyle noteStyle = tableExport.createCellStyle();
//			noteStyle.setVerticalAlignment(VerticalAlignment.CENTER);
//			noteStyle.setAlignment(HorizontalAlignment.CENTER);
//			noteStyle.setWrapText(true);
//			noteStyle.setFont(font);
//
//			CellStyle toplamStyl = tableExport.createCellStyle();
//			toplamStyl.setVerticalAlignment(VerticalAlignment.CENTER);
//			toplamStyl.setAlignment(HorizontalAlignment.RIGHT);
//			toplamStyl.setFont(font);
//
//			String title = "BANKA LÝSTESÝ";
//			XSSFRow titleRow = Spreadsheet.createRow(0);
//			XSSFCell titleCell = titleRow.createCell(0);
//			Spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
//			titleCell.setCellValue(title);
//			titleCell.setCellStyle(cellStyle);
//
//			XSSFRow adRow = Spreadsheet.createRow(1);
//			XSSFCell adCell = adRow.createCell(0);
//			adCell.setCellValue("KURUMU"); // bold tipine dönüþtür
//			adCell.setCellStyle(style1);
//
//			XSSFCell turCell = adRow.createCell(1);
//			Spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
//			turCell.setCellValue("DÜZCE ÝL MÝLLÝ EÐÝTÝM MÜDÜRLÜÐÜ"); // bold tipine dönüþtür
//			turCell.setCellStyle(cellStyle);
//			turCell.setCellStyle(cellStyle);
//
//			XSSFRow tarihRow = Spreadsheet.createRow(2);
//			XSSFCell tarihCell = tarihRow.createCell(0);
//			Spreadsheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));
//			tarihCell.setCellValue("SINAV ADI");
//			tarihCell.setCellStyle(style1);
//
//			XSSFCell sinavTurCell = tarihRow.createCell(1);
//			sinavTurCell.setCellValue(tur);
//			sinavTurCell.setCellStyle(style1);
//
//			XSSFCell sinavTarihCell = tarihRow.createCell(4);
//			sinavTarihCell.setCellValue(tarih);
//			sinavTarihCell.setCellStyle(cellStyle);
//
//			XSSFRow columnNameRow = Spreadsheet.createRow(3);
//			for (int i = 0; i < model.getColumnCount(); i++) {
//				System.out.println("eft kolon adlar");
//				XSSFCell cNamesCell = columnNameRow.createCell(i);
//				cNamesCell.setCellValue(model.getColumnName(i));
//				cNamesCell.setCellStyle(tableCellSty);
//			}
//
//			for (int j = 4; j < model.getRowCount(); j++) {
//				System.out.println("eft kolon row");
//				XSSFRow firstRow = Spreadsheet.createRow(j);
//				for (int i = 0; i < model.getColumnCount(); i++) {
//					System.out.println("eft kolon cell");
//					XSSFCell cellCreate = firstRow.createCell(i);
//					cellCreate.setCellValue(model.getValueAt(j - 4, i).toString());
//					cellCreate.setCellStyle(tableCellSty);
//					Spreadsheet.autoSizeColumn(i);
//
//				}
//			}
//
//			String toplam = "TOPLAM";
//			XSSFRow toplamRow = Spreadsheet.createRow(model.getRowCount());
//			XSSFCell toplamCell = toplamRow.createCell(1);
//			toplamCell.setCellValue(toplam);
//			toplamCell.setCellStyle(toplamStyl);
//			XSSFCell btCell = toplamRow.createCell(2);
//
//			double tnet = 0;
//			for (int i = 4; i < model.getRowCount(); i++) {
//				XSSFRow topRow = Spreadsheet.getRow(i);
//				XSSFCell nettopCell = topRow.getCell(2);
//				double net = Double.parseDouble(String.valueOf(nettopCell));
//				tnet = tnet + net;
//
//			}
//
//			btCell.setCellValue(tnet);
//			btCell.setCellStyle(tableCellSty);
//			Spreadsheet.setColumnWidth(2, 5000);
//			Spreadsheet.setColumnWidth(3, 5000);
//
//			String note = "Yukarýda bilgileri bulunan personellerin hesaplarýna dekonlarda isimleri belirtilerek Müdürlüðümüz 32705000174 nolu Döner Sermaye iþletmesi hesabýndan aktarýlmasýný arz ederim.";
//			XSSFRow noteRow = Spreadsheet.createRow(model.getRowCount() + 3);
//			// noteRow.setHeightInPoints((2 * Spreadsheet.getDefaultRowHeightInPoints()));
//			XSSFCell noteCell = noteRow.createCell(0);
//			Spreadsheet.addMergedRegion(new CellRangeAddress(model.getRowCount() + 3, model.getRowCount() + 6, 0, 4));
//			noteCell.setCellValue(note);
//			noteCell.setCellStyle(noteStyle);
//
//			String muhYetkili = "Sedat BAÞ";
//			XSSFRow muhRow = Spreadsheet.createRow(model.getRowCount() + 10);
//			XSSFCell muhCell = muhRow.createCell(1);
//			muhCell.setCellValue(muhYetkili);
//			muhCell.setCellStyle(cellStyle);
//			String meMudur = "Tamer KIRBAÇ";
//			XSSFCell mudCell = muhRow.createCell(3);
//			mudCell.setCellValue(meMudur);
//			mudCell.setCellStyle(cellStyle);
//
//			String mYetki = "Muhasebe Yetkilisi";
//			XSSFRow myRow = Spreadsheet.createRow(model.getRowCount() + 11);
//			XSSFCell myCell = myRow.createCell(1);
//			myCell.setCellValue(mYetki);
//			myCell.setCellStyle(cellStyle);
//			String mudur = "Ýl Milli Eðitim Müdürü";
//			XSSFCell mudurCell = myRow.createCell(3);
//			mudurCell.setCellValue(mudur);
//			mudurCell.setCellStyle(cellStyle);
//
//			String hyetki = "Harcama Yetkilisi";
//			XSSFRow hyetkiRow = Spreadsheet.createRow(model.getRowCount() + 12);
//			XSSFCell hyetkiCell = hyetkiRow.createCell(3);
//			hyetkiCell.setCellValue(hyetki);
//			hyetkiCell.setCellStyle(cellStyle);
//
//		} 
		else if (index == 3 || index == 4) {
			XSSFSheet Spreadsheet = tableExport.createSheet(tarih + " Ödeme Listesi");

			XSSFFont font = tableExport.createFont(); // bold font
			font.setFontHeightInPoints((short) 10);
			font.setFontName("Arial Unicode MS");
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(true);
			font.setItalic(false);

			XSSFFont font2 = tableExport.createFont();
			font2.setFontHeightInPoints((short) 10);
			font2.setFontName("Calibri");
			font2.setColor(IndexedColors.BLACK.getIndex());
			font2.setBold(false);
			font2.setItalic(false);

			CellStyle tableCellSty = tableExport.createCellStyle();
			tableCellSty.setVerticalAlignment(VerticalAlignment.CENTER);
			tableCellSty.setAlignment(HorizontalAlignment.CENTER);
			tableCellSty.setFont(font2);

			CellStyle cellStyle = tableExport.createCellStyle();
			cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			cellStyle.setFont(font);

			CellStyle style1 = tableExport.createCellStyle();
			style1.setFont(font);

			CellStyle noteStyle = tableExport.createCellStyle();
			noteStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			noteStyle.setAlignment(HorizontalAlignment.CENTER);
			noteStyle.setWrapText(true);
			noteStyle.setFont(font);

			CellStyle toplamStyl = tableExport.createCellStyle();
			toplamStyl.setVerticalAlignment(VerticalAlignment.CENTER);
			toplamStyl.setAlignment(HorizontalAlignment.RIGHT);
			toplamStyl.setFont(font);

			String title = "BANKA LÝSTESÝ";
			XSSFRow titleRow = Spreadsheet.createRow(0);
			XSSFCell titleCell = titleRow.createCell(0);
			Spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			titleCell.setCellValue(title);
			titleCell.setCellStyle(cellStyle);

			XSSFRow adRow = Spreadsheet.createRow(1);
			XSSFCell adCell = adRow.createCell(0);
			adCell.setCellValue("KURUMU"); // bold tipine dönüþtür
			adCell.setCellStyle(style1);

			XSSFCell turCell = adRow.createCell(1);
			Spreadsheet.addMergedRegion(new CellRangeAddress(1, 1, 1, 4));
			turCell.setCellValue("DÜZCE ÝL MÝLLÝ EÐÝTÝM MÜDÜRLÜÐÜ"); // bold tipine dönüþtür
			turCell.setCellStyle(cellStyle);
			turCell.setCellStyle(cellStyle);

			XSSFRow tarihRow = Spreadsheet.createRow(2);
			XSSFCell tarihCell = tarihRow.createCell(0);
			Spreadsheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));
			tarihCell.setCellValue("SINAV ADI");
			tarihCell.setCellStyle(style1);

			XSSFCell sinavTurCell = tarihRow.createCell(1);
			sinavTurCell.setCellValue(tur);
			sinavTurCell.setCellStyle(style1);

			XSSFCell sinavTarihCell = tarihRow.createCell(4);
			sinavTarihCell.setCellValue(tarih);
			sinavTarihCell.setCellStyle(cellStyle);

			XSSFRow columnNameRow = Spreadsheet.createRow(3);
			for (int i = 0; i < model.getColumnCount(); i++) {

				XSSFCell cNamesCell = columnNameRow.createCell(i);
				cNamesCell.setCellValue(model.getColumnName(i));
				cNamesCell.setCellStyle(tableCellSty);
			}

			for (int j = 0; j < model.getRowCount(); j++) {
				XSSFRow firstRow = Spreadsheet.createRow(j + 4);
				for (int i = 0; i < model.getColumnCount(); i++) {

					XSSFCell cellCreate = firstRow.createCell(i);
					cellCreate.setCellValue(model.getValueAt(j, i).toString());
					cellCreate.setCellStyle(tableCellSty);
					Spreadsheet.autoSizeColumn(i);

				}
			}

			String toplam = "TOPLAM";
			XSSFRow toplamRow = Spreadsheet.createRow(model.getRowCount() + 4);
			XSSFCell toplamCell = toplamRow.createCell(1);
			toplamCell.setCellValue(toplam);
			toplamCell.setCellStyle(toplamStyl);
			XSSFCell btCell = toplamRow.createCell(2);

			double tnet = 0;
			for (int i = 0; i < model.getRowCount(); i++) {
				XSSFRow topRow = Spreadsheet.getRow(i + 4);
				XSSFCell nettopCell = topRow.getCell(2);
				double net = Double.parseDouble(String.valueOf(nettopCell));
				tnet = tnet + net;

			}

			btCell.setCellValue(tnet);
			btCell.setCellStyle(tableCellSty);
			Spreadsheet.setColumnWidth(2, 5000);
			Spreadsheet.setColumnWidth(3, 5000);

			String note = "Yukarýda bilgileri bulunan personellerin hesaplarýna dekonlarda isimleri belirtilerek Müdürlüðümüz 32705000174 nolu Döner Sermaye iþletmesi hesabýndan aktarýlmasýný arz ederim.";
			XSSFRow noteRow = Spreadsheet.createRow(model.getRowCount() + 7);
			// noteRow.setHeightInPoints((2 * Spreadsheet.getDefaultRowHeightInPoints()));
			XSSFCell noteCell = noteRow.createCell(0);
			Spreadsheet.addMergedRegion(new CellRangeAddress(model.getRowCount() + 7, model.getRowCount() + 10, 0, 4));
			noteCell.setCellValue(note);
			noteCell.setCellStyle(noteStyle);

			String muhYetkili = "Sedat BAÞ";
			XSSFRow muhRow = Spreadsheet.createRow(model.getRowCount() + 14);
			XSSFCell muhCell = muhRow.createCell(1);
			muhCell.setCellValue(muhYetkili);
			muhCell.setCellStyle(cellStyle);
			String meMudur = "Tamer KIRBAÇ";
			XSSFCell mudCell = muhRow.createCell(3);
			mudCell.setCellValue(meMudur);
			mudCell.setCellStyle(cellStyle);

			String mYetki = "Muhasebe Yetkilisi";
			XSSFRow myRow = Spreadsheet.createRow(model.getRowCount() + 15);
			XSSFCell myCell = myRow.createCell(1);
			myCell.setCellValue(mYetki);
			myCell.setCellStyle(cellStyle);
			String mudur = "Ýl Milli Eðitim Müdürü";
			XSSFCell mudurCell = myRow.createCell(3);
			mudurCell.setCellValue(mudur);
			mudurCell.setCellStyle(cellStyle);

			String hyetki = "Harcama Yetkilisi";
			XSSFRow hyetkiRow = Spreadsheet.createRow(model.getRowCount() + 16);
			XSSFCell hyetkiCell = hyetkiRow.createCell(3);
			hyetkiCell.setCellValue(hyetki);
			hyetkiCell.setCellStyle(cellStyle);

		}

		else if (index == 5) {

			XSSFFont font = tableExport.createFont(); // bold font
			font.setFontHeightInPoints((short) 10);
			font.setFontName("Arial Unicode MS");
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setBold(true);
			font.setItalic(false);

			XSSFFont font2 = tableExport.createFont();
			font2.setFontHeightInPoints((short) 10);
			font2.setFontName("Calibri");
			font2.setColor(IndexedColors.BLACK.getIndex());
			font2.setBold(false);
			font2.setItalic(false);

			CellStyle tableCellSty = tableExport.createCellStyle();
			tableCellSty.setVerticalAlignment(VerticalAlignment.CENTER);
			tableCellSty.setAlignment(HorizontalAlignment.CENTER);
			tableCellSty.setFont(font2);

			CellStyle titleStyle = tableExport.createCellStyle();
			titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			titleStyle.setAlignment(HorizontalAlignment.CENTER);
			titleStyle.setFont(font);

			XSSFSheet Spreadsheet = tableExport.createSheet("Vergi Matrahý");
			String title = "GELÝR VERGÝSÝ BÝRLEÞTÝRME ÝÞLEMLERÝNE ÝLÝÞKÝN LÝSTE";
			XSSFRow titleRow = Spreadsheet.createRow(0);
			XSSFCell titleCell = titleRow.createCell(0);
			Spreadsheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 4));
			titleCell.setCellValue(title);
			titleCell.setCellStyle(titleStyle);

			XSSFRow columnNameRow = Spreadsheet.createRow(1);
			for (int i = 0; i < model.getColumnCount(); i++) {
				XSSFCell cNamesCell = columnNameRow.createCell(i);
				cNamesCell.setCellValue(model.getColumnName(i));
				cNamesCell.setCellStyle(titleStyle);
			}

			for (int j = 2; j < model.getRowCount(); j++) {
				XSSFRow firstRow = Spreadsheet.createRow(j);
				for (int i = 0; i < model.getColumnCount(); i++) {
					XSSFCell cellCreate = firstRow.createCell(i);
					cellCreate.setCellValue(model.getValueAt(j - 2, i).toString());
					cellCreate.setCellStyle(tableCellSty);
					Spreadsheet.autoSizeColumn(i);

				}
			}

			Spreadsheet.setColumnWidth(3, 5000);

		}

	}

	@Override
	public void deleteInput(Input input) {
		// TODO Auto-generated method stub

	}

}
