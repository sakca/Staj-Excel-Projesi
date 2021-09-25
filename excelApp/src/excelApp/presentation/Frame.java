package excelApp.presentation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import excelApp.business.abstracts.DataService;
import excelApp.business.concretes.DataManager;
import excelApp.entities.concretes.Data;
import excelApp.entities.concretes.Input;

public class Frame extends JFrame {

	DataService dataSM = new DataManager();

	private Data data;// ?????
	Input input = new Input();

	int id;
	String adSoyad;
	String tcNo;
	String iban;
	double matrah;
	String gorevYer;
	String gorevi;
	boolean temp = false;
	boolean choseFlag = false;
	boolean listFlag = false;

	ArrayList<Data> gorevliL;
	// ArrayList<Input> inputL;
	ArrayList<Input> brutListe = new ArrayList<Input>();

	public Frame(DataService dataService, Data data) throws HeadlessException {
		super();
		this.dataSM = dataService;
		this.data = data;
	}

	private JPanel contentPane;
	private JTextField textField_ilSnvSrml;
	private JTextField textField_kursiyer;
	private JTextField textField_bakanlikTems;
	private JTextField textField_komBask;
	private JTextField textField_komUye;
	private JTextField textField_kontDenet;
	private JTextField textField_degerlUye;
	private JTextField textField_binaSoruml;
	private JTextField textField_sef;
	private JTextField textField_memur;
	private JTextField textField_sofor;
	private JTextField textField_binaGorevlisi;
	private JTextField textField_guvenlikMemuru;
	private JTextField textField_sinavTur;
	private JTable table_Havale;
	private JTable table_Eft;
	private JTextField txtMart;
	private JTextField textField_ilSnvSormlYardimci;
	private JTable table_vmBirles;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					// frame.pack();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void bilgiAktar(DefaultTableModel tableModel) {
		gorevliL = dataSM.gorevli_olustur();

		for (int i = 0; i < gorevliL.size(); i++) {

			id = (gorevliL.get(i).getId()) + 1;
			adSoyad = gorevliL.get(i).getGorevliAdi();
			tcNo = gorevliL.get(i).getTcNo();
			iban = gorevliL.get(i).getIban();
			matrah = gorevliL.get(i).getMatrah();
			gorevYer = gorevliL.get(i).getGorevYeri();
			gorevi = gorevliL.get(i).getGorev();

			tableModel.addRow(new Object[] { id, adSoyad, tcNo, iban, matrah, gorevYer, gorevi });
//			tableModel.setModel(new DefaultTableModel(new Object[][] { { id, adSoyad, tcNo, iban, vergi, matrah } }, i));

		}

	}

	/**
	 * Create the frame.
	 */
	public Frame() {

		setTitle("Veri Hesap Uygulamasý");
		ImageIcon image = new ImageIcon(getClass().getClassLoader().getResource("indir.png"));
		setIconImage(image.getImage());
//		setIconImage(
//				Toolkit.getDefaultToolkit().getImage("D:\\Di\u011Feer\\eclipse-workspace\\excelApp\\image\\alliance-calgary-excel-png-logo-12-Transparent-Images.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1273, 770);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(51, 51, 51));
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(-13, 23, 1316, 71);
		panel.setBackground(SystemColor.inactiveCaption);
		contentPane.add(panel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 164, 1242, 553);
		tabbedPane.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		tabbedPane.setForeground(Color.BLACK);
		tabbedPane.setBackground(Color.GRAY);
		contentPane.add(tabbedPane);

		JLabel lbl_sonucBilgi = new JLabel(
				"Vergi indirimi uygulamak istedi\u011Finiz ki\u015Finin, sonu\u00E7 listesindeki GEL\u0130R VERG\u0130S\u0130 de\u011Ferine t\u0131klay\u0131n\u0131z. (Gelir vergisi 0 olacak, net de\u011Fere yans\u0131yacakt\u0131r.)");
		lbl_sonucBilgi.setFont(new Font("Times New Roman", Font.BOLD, 19));
		lbl_sonucBilgi.setForeground(new Color(51, 51, 51));
		// lbl_sonucBilgi.setForeground(Color.WHITE);
		lbl_sonucBilgi.setBounds(10, 104, 1242, 50);
		contentPane.add(lbl_sonucBilgi);

		JScrollPane sP_veri = new JScrollPane();
		tabbedPane.addTab("Excel Veri Tablosu", null, sP_veri, null);
		tabbedPane.setBackgroundAt(0, new Color(192, 192, 192));

		DefaultTableModel tableModel = new DefaultTableModel();
		JTable table = new JTable(tableModel);
		sP_veri.setViewportView(table);

		DefaultTableModel tableModel2 = new DefaultTableModel();
		JTable table_2 = new JTable(tableModel2);

		DefaultTableModel tableModelEft = new DefaultTableModel();
		DefaultTableModel tableModelHavale = new DefaultTableModel();
		DefaultTableModel tableModelBirles = new DefaultTableModel();

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		JButton b_ListeleH = new JButton("Listele");
		JButton b_ListeleE = new JButton("Listele");
		JButton b_ListeleVMBirlestirme = new JButton("Listele");

		JButton b_sec = new JButton("Dosya Sec");
		b_sec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (choseFlag == false) {
					try {

						JFileChooser findExcel = new JFileChooser();
						findExcel.setCurrentDirectory(new File("C:\\"));
						Action details = findExcel.getActionMap().get("Go Up");
						details.actionPerformed(null);
						details.actionPerformed(null);

						FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Çalýþma Sayfasý",
								"xlsx", "xls", "xlsm");
						findExcel.setFileFilter(filter);
						findExcel.setDialogTitle("Excel dosyasini seciniz");

						int excelChooser = findExcel.showOpenDialog(null);
						File excelFile;
						FileInputStream excelFIS = null;
						BufferedInputStream excelBIS = null;
						XSSFWorkbook excelJTableImport = null;

						excelFile = findExcel.getSelectedFile();

						if (excelChooser == JFileChooser.APPROVE_OPTION) {

							try {
								excelFIS = new FileInputStream(excelFile);
								excelBIS = new BufferedInputStream(excelFIS);
								excelJTableImport = new XSSFWorkbook(excelBIS);

								excelFile = findExcel.getSelectedFile();

								dataSM.readExcel(excelFile, excelFIS, excelBIS, excelJTableImport);

							} catch (FileNotFoundException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage());

							} catch (IOException e1) {
								JOptionPane.showMessageDialog(null, e1.getMessage());
							}
							tableModel.addColumn("Sýra No");
							tableModel.addColumn("Ad Soyad");
							tableModel.addColumn("T.C. Numarasý");
							tableModel.addColumn("Hesap Nuamrasý");
							tableModel.addColumn("Matrah");
							tableModel.addColumn("Kurumu");
							tableModel.addColumn("Görevi"); // 6

							table.getColumnModel().getColumn(0).setResizable(false);
							table.getColumnModel().getColumn(0).setPreferredWidth(20);
							table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

							table.getColumnModel().getColumn(1).setPreferredWidth(75);
							table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

							table.getColumnModel().getColumn(2).setResizable(false);
							table.getColumnModel().getColumn(2).setPreferredWidth(65);
							table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

							table.getColumnModel().getColumn(3).setResizable(false);
							table.getColumnModel().getColumn(3).setPreferredWidth(111);
							table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

							table.getColumnModel().getColumn(4).setResizable(false);
							table.getColumnModel().getColumn(4).setPreferredWidth(5);
							table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

							table.getColumnModel().getColumn(5).setPreferredWidth(65);
							table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

							table.getColumnModel().getColumn(6).setResizable(false);
							table.getColumnModel().getColumn(6).setPreferredWidth(97);
							table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
							table.setRowHeight(30);
							bilgiAktar(tableModel);
							choseFlag = true;
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Bu deðer boþ býrakýlamaz.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);

					}

				}

				else {
					JOptionPane.showMessageDialog(null,
							"Yeni dosya seçmeden önce lütfen 'Hesaplama Verilerini Sil' butonuna basýnýz.", "Uyarý",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}

		});
		b_sec.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b_sec.setBackground(SystemColor.inactiveCaption);

		JButton b_temizle = new JButton("Hesaplama verilerini sil");
		b_temizle.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b_temizle.setBackground(SystemColor.inactiveCaption);

		JButton b_disaAktar = new JButton("Tabloyu Yazdýr");
		b_disaAktar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (choseFlag == true && listFlag == true) {
					int index = tabbedPane.getSelectedIndex();
					System.out.println("index: " + index);

					if (index == 0) {
						JOptionPane.showMessageDialog(null, "'Excel Veri Tablosu' sayfasý dýþa aktarýlamaz.\n");
					} else if (index == 1) {
						JOptionPane.showMessageDialog(null, "'Hesaplama Verileri' sayfasý dýþa aktarýlamaz.\n");
					}

					else {

						JScrollPane scrollPane = (JScrollPane) (tabbedPane.getSelectedComponent());
						JViewport viewport = scrollPane.getViewport();
						JTable tempTable = (JTable) viewport.getView();
						TableModel tempModel = tempTable.getModel();

						JFileChooser createExcel = new JFileChooser();
						createExcel.setCurrentDirectory(new File("C:\\"));// kaydetmek için lokasyon seçme
						Action details = createExcel.getActionMap().get("Go Up");
						details.actionPerformed(null);
						details.actionPerformed(null);
						createExcel.setDialogTitle("Kaydet ..");
						FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Çalýþma Sayfasý",
								"xlsx", "xls", "xlsm");
						createExcel.setFileFilter(filter);
						createExcel.setDialogTitle("Dosayayý buraya oluþtur.");
						int excelChooser = createExcel.showOpenDialog(null);
						File exportFile = createExcel.getSelectedFile();

						if (excelChooser == JFileChooser.APPROVE_OPTION) {

							FileOutputStream excelFOS = null;
							BufferedOutputStream excelBOS = null;
							XSSFWorkbook tableExport = new XSSFWorkbook();

							try {

								System.out.println("tablo indexi: " + index);
								System.out.println("tablo: " + tempTable.getValueAt(2, 4).toString());
								dataSM.exportTables(index, tempModel, exportFile, excelFOS, excelBOS, tableExport,
										input.getSinavTur(), input.getSinavTarihi());

								excelFOS = new FileOutputStream(exportFile + ".xlsx");
								excelBOS = new BufferedOutputStream(excelFOS);
								tableExport.write(excelBOS);
								JOptionPane.showMessageDialog(null, "Baþarýyla aktarýldý.");

								File xlsx = new File(createExcel.getSelectedFile().getAbsolutePath() + ".xlsx");

								try {

									Runtime.getRuntime().exec("rundll32 SHELL32.DLL,ShellExec_RunDLL \"" + xlsx + "\"");
								} catch (Exception exception) {
									exception.printStackTrace();
								}

							} catch (Exception ex) {
								ex.printStackTrace();

							} finally {
								try {
									if (excelBOS != null) {
										excelBOS.close();

									}
									if (excelFOS != null) {
										excelFOS.close();
									}

									if (tableExport != null) {
										tableExport.close();
									}
								} catch (IOException ex) {
									ex.printStackTrace();
								}
							}
						}

					}

				} else {
					if (listFlag == false) {
						JOptionPane.showMessageDialog(null, "Önce Hesaplama Verileri Girilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Önce Dosya Seçilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});

		b_disaAktar.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		b_disaAktar.setBackground(SystemColor.inactiveCaption);

		JButton b_disaAktarTum = new JButton("Tüm Tablolarý Yazdýr");
		b_disaAktarTum.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (choseFlag == true && listFlag == true) {
					for (int i = 2; i < 6; i++) {
						JScrollPane scrollPane = (JScrollPane) (tabbedPane.getComponent(i));
						JViewport viewport = scrollPane.getViewport();
						JTable tempTable = (JTable) viewport.getView();
						TableModel tempModel = tempTable.getModel();

						JFileChooser createExcel = new JFileChooser();
						createExcel.setCurrentDirectory(new File("C:\\"));// kaydetmek için lokasyon seçme
						Action details = createExcel.getActionMap().get("Go Up");
						details.actionPerformed(null);
						details.actionPerformed(null);

						if (i == 2) {
							createExcel.setDialogTitle("Sonuç Listesini Kaydet ..");
						} else if (i == 3) {
							createExcel.setDialogTitle("Havale Listesini Kaydet ..");
						} else if (i == 4) {
							createExcel.setDialogTitle("Eft Listesini Kaydet ..");
						} else if (i == 5) {
							createExcel.setDialogTitle("Vergi Matrah Birleþtirme Listesini Kaydet ..");
						}

						FileNameExtensionFilter filter = new FileNameExtensionFilter("Microsoft Excel Çalýþma Sayfasý",
								"xlsx", "xls", "xlsm");
						createExcel.setFileFilter(filter);
						// createExcel.setDialogTitle("Dosayayý buraya oluþtur.");
						int excelChooser = createExcel.showOpenDialog(null);
						File exportFile = createExcel.getSelectedFile();

						if (excelChooser == JFileChooser.APPROVE_OPTION) {

							FileOutputStream excelFOS = null;
							BufferedOutputStream excelBOS = null;
							XSSFWorkbook tableExport = new XSSFWorkbook();

							try {

								dataSM.exportTables(i, tempModel, exportFile, excelFOS, excelBOS, tableExport,
										input.getSinavTur(), input.getSinavTarihi());

								excelFOS = new FileOutputStream(exportFile + ".xlsx");
								excelBOS = new BufferedOutputStream(excelFOS);
								tableExport.write(excelBOS);
								JOptionPane.showMessageDialog(null, (i - 1) + ". dosya baþarýyla aktarýldý.");

							} catch (Exception ex) {
								ex.printStackTrace();

							} finally {
								try {
									if (excelBOS != null) {
										excelBOS.close();

									}
									if (excelFOS != null) {
										excelFOS.close();
									}

									if (tableExport != null) {
										tableExport.close();
									}
								} catch (IOException ex) {
									ex.printStackTrace();
								}
							}
						}

					}
				}

				else {
					if (listFlag == false) {
						JOptionPane.showMessageDialog(null, "Önce Hesaplama Verileri Girilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Önce Dosya Seçilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});
		b_disaAktarTum.setForeground(new Color(255, 255, 240));
		b_disaAktarTum.setFont(new Font("Times New Roman", Font.BOLD, 16));
		b_disaAktarTum.setBackground(new Color(51, 0, 102));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap(336, Short.MAX_VALUE)
						.addComponent(b_sec, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(b_temizle)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(b_disaAktar, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(b_disaAktarTum, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
						.addGap(67)));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(b_sec, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(b_temizle, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(b_disaAktar, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE)
						.addComponent(b_disaAktarTum, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

		JPanel p_hesap = new JPanel();
		p_hesap.setBackground(SystemColor.control);
		tabbedPane.addTab("Hesaplama Verileri", null, p_hesap, null);
		tabbedPane.setBackgroundAt(1, new Color(192, 192, 192));

		JLabel lbl_kursiyer = new JLabel("KURSÝYER SAYISI");
		lbl_kursiyer.setBounds(7, 7, 160, 57);
		lbl_kursiyer.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_bakanlikT = new JLabel("BAKANLIK TEMS\u0130LC\u0130S\u0130");
		lbl_bakanlikT.setBounds(7, 68, 184, 57);
		lbl_bakanlikT.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_komBask = new JLabel("SINAV Y\u00DCR\u00DCTME KOM. BA\u015EK.");
		lbl_komBask.setBounds(7, 184, 215, 77);
		lbl_komBask.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_komUye = new JLabel("SINAV Y\u00DCR\u00DCTME KOM.\u00DCYES\u0130");
		lbl_komUye.setBounds(7, 249, 215, 65);
		lbl_komUye.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_kontDenet = new JLabel("SINAV KOM. KONT.DENET");
		lbl_kontDenet.setBounds(7, 311, 215, 57);
		lbl_kontDenet.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_ilSnvSrml = new JLabel("\u0130L SINAV SORUMLUSU");
		lbl_ilSnvSrml.setBounds(7, 129, 184, 57);
		lbl_ilSnvSrml.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_degerlUye = new JLabel("SINAV DE\u011EERL. \u00DCYES\u0130");
		lbl_degerlUye.setBounds(7, 372, 184, 50);
		lbl_degerlUye.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_binaSorml = new JLabel("B\u0130NA SORUMLUSU");
		lbl_binaSorml.setBounds(466, 8, 141, 54);
		lbl_binaSorml.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_sef = new JLabel("\u015EEF");
		lbl_sef.setBounds(466, 68, 175, 57);
		lbl_sef.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_Memur = new JLabel("MEMUR");
		lbl_Memur.setBounds(466, 129, 175, 57);
		lbl_Memur.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_sofor = new JLabel("\u015EOF\u00D6R");
		lbl_sofor.setBounds(466, 197, 175, 50);
		lbl_sofor.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_binaGorevlisi = new JLabel("B\u0130NA G\u00D6REVL\u0130S\u0130");
		lbl_binaGorevlisi.setBounds(466, 256, 153, 50);
		lbl_binaGorevlisi.setFont(new Font("Times New Roman", Font.PLAIN, 15));

		JLabel lbl_guvenlikMemuru = new JLabel("G\u00DCVENL\u0130K POL\u0130S MEMUR");
		lbl_guvenlikMemuru.setBounds(466, 311, 195, 57);
		lbl_guvenlikMemuru.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		p_hesap.setLayout(null);
		p_hesap.add(lbl_kursiyer);
		p_hesap.add(lbl_binaSorml);
		p_hesap.add(lbl_bakanlikT);
		p_hesap.add(lbl_sef);
		p_hesap.add(lbl_ilSnvSrml);
		p_hesap.add(lbl_Memur);
		p_hesap.add(lbl_komBask);
		p_hesap.add(lbl_komUye);
		p_hesap.add(lbl_kontDenet);
		p_hesap.add(lbl_sofor);
		p_hesap.add(lbl_binaGorevlisi);
		p_hesap.add(lbl_guvenlikMemuru);
		p_hesap.add(lbl_degerlUye);

		textField_ilSnvSrml = new JTextField();
		textField_ilSnvSrml.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_ilSnvSrml.setHorizontalAlignment(SwingConstants.CENTER);
		textField_ilSnvSrml.setBounds(235, 143, 135, 28);
		textField_ilSnvSrml.setColumns(10);
		p_hesap.add(textField_ilSnvSrml);

		textField_kursiyer = new JTextField();
		textField_kursiyer.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_kursiyer.setHorizontalAlignment(SwingConstants.CENTER);
		textField_kursiyer.setColumns(10);
		textField_kursiyer.setBounds(235, 21, 135, 28);
		p_hesap.add(textField_kursiyer);

		textField_bakanlikTems = new JTextField();
		textField_bakanlikTems.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_bakanlikTems.setHorizontalAlignment(SwingConstants.CENTER);
		textField_bakanlikTems.setColumns(10);
		textField_bakanlikTems.setBounds(235, 82, 135, 28);
		p_hesap.add(textField_bakanlikTems);

		textField_komBask = new JTextField();
		textField_komBask.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_komBask.setHorizontalAlignment(SwingConstants.CENTER);
		textField_komBask.setColumns(10);
		textField_komBask.setBounds(235, 208, 135, 28);
		p_hesap.add(textField_komBask);

		textField_komUye = new JTextField();
		textField_komUye.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_komUye.setHorizontalAlignment(SwingConstants.CENTER);
		textField_komUye.setColumns(10);
		textField_komUye.setBounds(235, 267, 135, 28);
		p_hesap.add(textField_komUye);

		textField_kontDenet = new JTextField();
		textField_kontDenet.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_kontDenet.setHorizontalAlignment(SwingConstants.CENTER);
		textField_kontDenet.setColumns(10);
		textField_kontDenet.setBounds(235, 325, 135, 28);
		p_hesap.add(textField_kontDenet);

		textField_degerlUye = new JTextField();
		textField_degerlUye.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_degerlUye.setHorizontalAlignment(SwingConstants.CENTER);
		textField_degerlUye.setColumns(10);
		textField_degerlUye.setBounds(235, 383, 135, 28);
		p_hesap.add(textField_degerlUye);

		textField_binaSoruml = new JTextField();
		textField_binaSoruml.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_binaSoruml.setHorizontalAlignment(SwingConstants.CENTER);
		textField_binaSoruml.setColumns(10);
		textField_binaSoruml.setBounds(681, 21, 135, 28);
		p_hesap.add(textField_binaSoruml);

		textField_sef = new JTextField();
		textField_sef.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_sef.setHorizontalAlignment(SwingConstants.CENTER);
		textField_sef.setColumns(10);
		textField_sef.setBounds(681, 82, 135, 28);
		p_hesap.add(textField_sef);

		textField_memur = new JTextField();
		textField_memur.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_memur.setHorizontalAlignment(SwingConstants.CENTER);
		textField_memur.setColumns(10);
		textField_memur.setBounds(681, 143, 135, 28);
		p_hesap.add(textField_memur);

		textField_sofor = new JTextField();
		textField_sofor.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_sofor.setHorizontalAlignment(SwingConstants.CENTER);
		textField_sofor.setColumns(10);
		textField_sofor.setBounds(681, 208, 135, 28);
		p_hesap.add(textField_sofor);

		textField_binaGorevlisi = new JTextField();
		textField_binaGorevlisi.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_binaGorevlisi.setHorizontalAlignment(SwingConstants.CENTER);
		textField_binaGorevlisi.setColumns(10);
		textField_binaGorevlisi.setBounds(681, 267, 135, 28);
		p_hesap.add(textField_binaGorevlisi);

		textField_guvenlikMemuru = new JTextField();
		textField_guvenlikMemuru.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_guvenlikMemuru.setHorizontalAlignment(SwingConstants.CENTER);
		textField_guvenlikMemuru.setColumns(10);
		textField_guvenlikMemuru.setBounds(681, 325, 135, 28);
		p_hesap.add(textField_guvenlikMemuru);

		JScrollPane sP_sonuc = new JScrollPane();
		tabbedPane.addTab("Sonuç Listesi", null, sP_sonuc, null);

		sP_sonuc.setViewportView(table_2);

		// JTable table2 = new JTable(tableModel);

		JButton b_dilimDeg = new JButton("Vergi Dilim ve Miktarýnda Deðiþiklik Yapmak Ýçin Týklayýnýz");
		b_dilimDeg.setForeground(new Color(128, 0, 0));
		b_dilimDeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int m1, m2, m3, d1, d2, d3;
				try {
					do {
						JTextField dilim1 = new JTextField(5);
						JTextField miktar1 = new JTextField(10);
						JTextField dilim2 = new JTextField(5);
						JTextField miktar2 = new JTextField(10);
						JTextField dilim3 = new JTextField(5);
						JTextField miktar3 = new JTextField(10);
						JPanel vergiPanel = new JPanel();
						vergiPanel.add(Box.createVerticalStrut(100));
						vergiPanel.add(new JLabel("1. Vergi Yüzdelik Dilimi"));
						vergiPanel.add(dilim1);
						vergiPanel.add(new JLabel("Vergi Miktarý"));
						vergiPanel.add(miktar1);
						vergiPanel.add(Box.createHorizontalStrut(50)); // a spacer
						vergiPanel.add(Box.createVerticalStrut(100));
						vergiPanel.add(new JLabel("2. Vergi Yüzdelik Dilimi"));
						vergiPanel.add(dilim2);
						vergiPanel.add(new JLabel("Vergi Miktarý"));
						vergiPanel.add(miktar2);
						vergiPanel.add(Box.createHorizontalStrut(50)); // a spacer
						vergiPanel.add(Box.createVerticalStrut(100));
						vergiPanel.add(new JLabel("3. Vergi Yüzdelik Dilimi"));
						vergiPanel.add(dilim3);
						vergiPanel.add(new JLabel("Vergi Miktarý"));
						vergiPanel.add(miktar3);
						vergiPanel.add(Box.createHorizontalStrut(50)); // a spacer
						JOptionPane.showConfirmDialog(null, vergiPanel,
								"En küçükten baþlayarak yeni vergi deðerlerini giriniz.", JOptionPane.OK_CANCEL_OPTION);

						m1 = Integer.parseInt(miktar1.getText());
						m2 = Integer.parseInt(miktar2.getText());
						m3 = Integer.parseInt(miktar3.getText());
						d1 = Integer.parseInt(dilim1.getText());
						d2 = Integer.parseInt(dilim2.getText());
						d3 = Integer.parseInt(dilim3.getText());
						if (m1 > m2 || m1 > m3 || d1 > d2 || d1 > d3) {
							JOptionPane.showMessageDialog(null,
									"1. Vergi miktari veya yüzdesi diðerlerinden büyük olamaz.", "Hata",
									JOptionPane.INFORMATION_MESSAGE);
						}
						if (m2 > m3 || d2 > d3) {
							JOptionPane.showMessageDialog(null,
									"2. Vergi miktari veya yüzdesi 3. deðerlerden büyük olamaz.", "Hata",
									JOptionPane.INFORMATION_MESSAGE);
						}

					} while (m1 > m2 || m1 > m3 || m2 > m3 || d1 > d2 || d1 > d3 || d2 > d3);

					int value[] = { m1, m2, m3, d1, d2, d3 };
					input.setValue(value);
					temp = true;

				} catch (Exception ex) {
					//System.out.println(ex.getMessage());
				}

			}

		});
		b_dilimDeg.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		b_dilimDeg.setBackground(new Color(255, 255, 255));
		b_dilimDeg.setBounds(841, 447, 386, 57);
		p_hesap.add(b_dilimDeg);

		JLabel lbl_sinavTur = new JLabel();
		String turS = "SINAV TÜRÜ (Src, Mtsk, Ýþ Makineleri )";
		lbl_sinavTur.setText(
				"<html>" + turS.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");

		lbl_sinavTur.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbl_sinavTur.setBounds(466, 369, 167, 57);
		p_hesap.add(lbl_sinavTur);

		textField_sinavTur = new JTextField();
		textField_sinavTur.setHorizontalAlignment(SwingConstants.CENTER);
		textField_sinavTur.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_sinavTur.setColumns(10);
		textField_sinavTur.setBounds(681, 383, 135, 28);
		p_hesap.add(textField_sinavTur);

		JLabel lbl_ilSorumluYardimci = new JLabel();
		String yardimciS = "ÝL SINAV SORUMLUSU YARDIMCISI (Görevli yoksa 0 giriniz.)";
		lbl_ilSorumluYardimci.setText("<html>"
				+ yardimciS.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br/>") + "</html>");
		lbl_ilSorumluYardimci.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbl_ilSorumluYardimci.setBounds(7, 439, 261, 50);
		p_hesap.add(lbl_ilSorumluYardimci);

		textField_ilSnvSormlYardimci = new JTextField();
		textField_ilSnvSormlYardimci.setHorizontalAlignment(SwingConstants.CENTER);
		textField_ilSnvSormlYardimci.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textField_ilSnvSormlYardimci.setColumns(10);
		textField_ilSnvSormlYardimci.setBounds(278, 447, 122, 28);
		p_hesap.add(textField_ilSnvSormlYardimci);

		JLabel lbl_sinavTarih = new JLabel("SINAV TARÝHÝ");
		lbl_sinavTarih.setText("<html>SINAV TAR\u0130H\u0130 (\u00F6rn: 6-3-2021)</html>");
		lbl_sinavTarih.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		lbl_sinavTarih.setBounds(466, 436, 135, 57);
		p_hesap.add(lbl_sinavTarih);

		txtMart = new JTextField();
		txtMart.setForeground(Color.BLACK);
		txtMart.setHorizontalAlignment(SwingConstants.CENTER);
		txtMart.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		txtMart.setColumns(10);
		txtMart.setBounds(681, 441, 135, 28);
		p_hesap.add(txtMart);
		tabbedPane.setBackgroundAt(2, new Color(192, 192, 192));

		JButton b_Hesaplama = new JButton("Hesapla");
		b_Hesaplama.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (choseFlag == true) {

					try {

						// gorevler için girilen brut degerleri
						String kursiyer = textField_kursiyer.getText().replace(",", ".");
						float kursyr = Float.parseFloat(kursiyer);
						String ilSrml = textField_ilSnvSrml.getText().replace(",", ".");
						float ilSorumlu = Float.parseFloat(ilSrml);
						String kBaskan = textField_komBask.getText().replace(",", ".");
						float komBask = Float.parseFloat(kBaskan);
						String kUye = textField_komUye.getText().replace(",", ".");
						float komUye = Float.parseFloat(kUye);
						String kDenet = textField_kontDenet.getText().replace(",", ".");
						float kontDenet = Float.parseFloat(kDenet);
						String bSorumlu = textField_binaSoruml.getText().replace(",", ".");
						float binaSorumlu = Float.parseFloat(bSorumlu);
						String sefS = textField_sef.getText().replace(",", ".");
						float sef = Float.parseFloat(sefS);
						String memurS = textField_memur.getText().replace(",", ".");
						float memur = Float.parseFloat(memurS);
						String soforS = textField_sofor.getText().replace(",", ".");
						float sofor = Float.parseFloat(soforS);
						String bGorevli = textField_binaGorevlisi.getText().replace(",", ".");
						float binaGorevl = Float.parseFloat(bGorevli);
						String guvMemur = textField_guvenlikMemuru.getText().replace(",", ".");
						float guvnlkM = Float.parseFloat(guvMemur);
						String dUye = textField_degerlUye.getText().replace(",", ".");
						float degUye = Float.parseFloat(dUye);
						String bTemsil = textField_bakanlikTems.getText().replace(",", ".");
						float bTemsilci = Float.parseFloat(bTemsil);
						String sinavTuru = textField_sinavTur.getText();
						String tarih = txtMart.getText().replace("/", " ").replace(".", " ");
						String ilYardm = textField_ilSnvSormlYardimci.getText().replace(",", ".");
						float ilSorumluYardim = Float.parseFloat(ilYardm);

						SimpleDateFormat dots = new SimpleDateFormat("dd.MM.yyyy");// , new Locale("tr"));
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
						Date date = sdf.parse(tarih);
						String fDate = dots.format(date);

						String names[] = { "src", "sRC", "srC", "SrC", "Src", "SRc", "SRC", "sRc", "MTSK", "Mtsk",
								"mtsk", "MTsk", "MTSk", "mTsk", "mTSk", "mTSK", "mtSk", "mtSK", "MtSk", "MtSK", "mtsK",
								"iþ makineleri", "iþ makinelerý", "iþ makýneleri", "iþ makýnelerý", "is makineleri",
								"is makýnelerý", "is makinelerý", "is makýneleri", "ýþ makineleri", "ýþ makinelerý",
								"ýþ makýneleri", "ýþ makýnelerý", "ýs makineleri", "ýs makinelerý", "ýs makýneleri",
								"ýs makýnelerý" };

						// int num = 0;
						boolean turCheck = false;
						for (int i = 0; i < names.length; i++) {
							if (sinavTuru.equals(names[i])) {
								turCheck = true;
							}
						}

						if (turCheck == false) {
							JOptionPane.showMessageDialog(null, "Sýnav Türünü doðru formatta giriniz.");
						}

						else {
							input.setSinavTarihi(fDate);
							input.setSinavTur(sinavTuru);
							brutListe.add(new Input(0, lbl_ilSnvSrml.getText(), ilSorumlu));
							brutListe.add(new Input(1, lbl_komBask.getText(), komBask));
							brutListe.add(new Input(2, lbl_komUye.getText(), komUye));
							brutListe.add(new Input(3, lbl_kontDenet.getText(), kontDenet));
							brutListe.add(new Input(4, lbl_binaSorml.getText(), binaSorumlu));
							brutListe.add(new Input(5, lbl_sef.getText(), sef));
							brutListe.add(new Input(6, lbl_Memur.getText(), memur));
							brutListe.add(new Input(7, lbl_sofor.getText(), sofor));
							brutListe.add(new Input(8, lbl_binaGorevlisi.getText(), binaGorevl));
							brutListe.add(new Input(9, lbl_guvenlikMemuru.getText(), guvnlkM));
							brutListe.add(new Input(10, lbl_degerlUye.getText(), degUye));
							brutListe.add(new Input(11, lbl_bakanlikT.getText(), bTemsilci));
							brutListe.add(new Input(12, lbl_kursiyer.getText(), kursyr));
							brutListe.add(new Input(13, lbl_ilSorumluYardimci.getText(), ilSorumluYardim));

							if (temp == true) {

								int value[] = input.getValue();
								System.out.println("tiklandi. value1 : " + value[0]);
								dataSM.input_olustur(brutListe, value[0], value[1], value[2], value[3], value[4],
										value[5]);

							} else {
								int m1 = 24000;
								int m2 = 53000;
								int m3 = 130000;
								int d1 = 15;
								int d2 = 20;
								int d3 = 27;
								dataSM.input_olustur(brutListe, m1, m2, m3, d1, d2, d3);

							}

							tableModel2.addColumn("Sýra No"); // 0
							tableModel2.addColumn("Adý Soyadý");
							tableModel2.addColumn("T.C. Kimlik No");
							tableModel2.addColumn("Hesap Numarasý");
							tableModel2.addColumn("Görevi"); // 4
							tableModel2.addColumn("Brüt");
							tableModel2.addColumn("Gelir Vergisi Matrahý");
							tableModel2.addColumn("Devreden Toplam Matrahý / Dilimi"); // 7
							tableModel2.addColumn("Gelir Vergisi");
							tableModel2.addColumn("Damga Vergisi");
							tableModel2.addColumn("Net Ödenen"); // 10

							table_2.getColumnModel().getColumn(0).setResizable(false);
							table_2.getColumnModel().getColumn(0).setPreferredWidth(5);
							table_2.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(1).setPreferredWidth(100);
							table_2.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(2).setResizable(false);
							table_2.getColumnModel().getColumn(2).setPreferredWidth(65);
							table_2.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(3).setResizable(false);
							table_2.getColumnModel().getColumn(3).setPreferredWidth(135);
							table_2.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(4).setPreferredWidth(130);
							table_2.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(5).setResizable(false); // brut
							table_2.getColumnModel().getColumn(5).setPreferredWidth(10);
							table_2.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(6).setPreferredWidth(50); // gelirVMat
							table_2.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(7).setResizable(false);
							table_2.getColumnModel().getColumn(7).setPreferredWidth(40);
							table_2.getColumnModel().getColumn(7).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(8).setResizable(false);
							table_2.getColumnModel().getColumn(8).setPreferredWidth(20);
							table_2.getColumnModel().getColumn(8).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(9).setResizable(false);
							table_2.getColumnModel().getColumn(9).setPreferredWidth(35);
							table_2.getColumnModel().getColumn(9).setCellRenderer(centerRenderer);

							table_2.getColumnModel().getColumn(10).setResizable(false);
							table_2.getColumnModel().getColumn(10).setPreferredWidth(20);
							table_2.getColumnModel().getColumn(10).setCellRenderer(centerRenderer);
							table_2.setRowHeight(30);

							for (int i = 0; i < gorevliL.size(); i++) {
								id = (gorevliL.get(i).getId()) + 1;
								adSoyad = gorevliL.get(i).getGorevliAdi();
								tcNo = gorevliL.get(i).getTcNo();
								iban = gorevliL.get(i).getIban();
								gorevi = gorevliL.get(i).getGorev();
								float brutT = gorevliL.get(i).getBrut();
								float gvm = gorevliL.get(i).getGelirVergisiMatrahi();
								matrah = gorevliL.get(i).getMatrah();

								double gv = gorevliL.get(i).getGelirVergisi();
								double damga = gorevliL.get(i).getDamgaVergisi();
								double net = gorevliL.get(i).getNetOdenen();

								tableModel2.addRow(new Object[] { id, adSoyad, tcNo, iban, gorevi, brutT, gvm, matrah,
										gv, damga, net });

							}

							b_Hesaplama.setEnabled(false);
							JOptionPane.showMessageDialog(null,
									"Hesaplama tamamlandý. Sonuç Listesi'ne bakabilirsiniz.");
							listFlag = true;
							lbl_sonucBilgi.setForeground(Color.white);

							table_2.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

								int i = 1;

								@Override
								public void valueChanged(ListSelectionEvent e) {

									if (i == 1) {
										JOptionPane.showMessageDialog(null,
												"DÝKKAT! Gelir vergisi indirimi uygulanýr!\n	Ýþlem geri alýnamaz.\n Uygulamak istiyorsanýz tekrar týklayýnýz.");
										i = 2;
									}

									if (i == 2) {

										if (table_2.getSelectedColumn() == 8 && !e.getValueIsAdjusting()) {

											double value = Double.parseDouble(String
													.valueOf(tableModel2.getValueAt(table_2.getSelectedRow(), 8)));
											System.out.println("vlaue: " + value);
											double vNet = Double.parseDouble(String
													.valueOf(tableModel2.getValueAt(table_2.getSelectedRow(), 10)));
											System.out.println("newNet: " + vNet);
											double newNet = vNet + value;
											System.out.println("newt: " + newNet + "\n");
											tableModel2.setValueAt(0, table_2.getSelectedRow(), 8); // vergi indirimi
											tableModel2.setValueAt(newNet, table_2.getSelectedRow(), 10);
											i = 1;
										}

									}

								}

							});

						}

					}

					catch (Exception ex) {
						JOptionPane.showMessageDialog(null,
								" Lütfen boþ deðer býrakmayýnýz ve Tarihi uygun formatta giriniz.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					}

					b_ListeleH.setEnabled(true);
					b_ListeleE.setEnabled(true);
					b_ListeleVMBirlestirme.setEnabled(true);

				}

				else {

					JOptionPane.showMessageDialog(null, "Önce Dosya Seçilmelidir.");
				}

				int index = tabbedPane.getSelectedIndex();
				System.out.println("lbl indexx" + index);

				if (index == 2) {

					JLabel lbl_sonucBilgi = new JLabel(
							"Vergi indirimi uygulamak istedi\u011Finiz ki\u015Finin GEL\u0130R VERG\u0130S\u0130 de\u011Ferine t\u0131klay\u0131n\u0131z. (Gelir vergisi 0 olacak ve net de\u011Fere yans\u0131yacakt\u0131r.)");
					lbl_sonucBilgi.setFont(new Font("Times New Roman", Font.BOLD, 20));
					lbl_sonucBilgi.setForeground(Color.WHITE);
					lbl_sonucBilgi.setBounds(10, 104, 1242, 50);
					contentPane.add(lbl_sonucBilgi);

				}

			}
		});
		b_Hesaplama.setForeground(new Color(255, 255, 255));
		b_Hesaplama.setFont(new Font("Times New Roman", Font.BOLD, 20));
		b_Hesaplama.setBackground(new Color(139, 0, 0));
		b_Hesaplama.setBounds(841, 340, 386, 97);
		p_hesap.add(b_Hesaplama);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(139, 0, 0));
		panel_1.setBounds(841, 27, 386, 83);
		p_hesap.add(panel_1);
						
								JLabel lbl_bilgi = new JLabel("G\u00F6revlilerin Br\u00FCt \u00DCcret De\u011Ferlerini Giriniz");
								panel_1.add(lbl_bilgi);
								lbl_bilgi.setHorizontalAlignment(SwingConstants.CENTER);
								lbl_bilgi.setForeground(new Color(255, 255, 255));
								lbl_bilgi.setBackground(Color.WHITE);
								lbl_bilgi.setFont(new Font("Times New Roman", Font.BOLD, 20));
				
						JLabel lbl_bilgi_1 = new JLabel("(Bulunmayan g\u00F6revler i\u00E7in 0 giriniz.)");
						panel_1.add(lbl_bilgi_1);
						lbl_bilgi_1.setHorizontalAlignment(SwingConstants.CENTER);
						lbl_bilgi_1.setForeground(new Color(255, 255, 255));
						lbl_bilgi_1.setFont(new Font("Times New Roman", Font.PLAIN, 20));
						lbl_bilgi_1.setBackground(Color.WHITE);

		JScrollPane sP_Havale = new JScrollPane();
		tabbedPane.addTab("Havale Listesi", null, sP_Havale, null);
		tabbedPane.setBackgroundAt(3, new Color(143, 188, 143));

		JScrollPane sP_Eft = new JScrollPane();
		tabbedPane.addTab("Eft Listesi", null, sP_Eft, null);
		tabbedPane.setBackgroundAt(4, new Color(143, 188, 143));

		table_Havale = new JTable(tableModelHavale);
		sP_Havale.setViewportView(table_Havale);

		table_Eft = new JTable(tableModelEft);
		sP_Eft.setViewportView(table_Eft);

		b_ListeleH.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (choseFlag == true && listFlag == true) {
					int flag;
					tableModelHavale.addColumn("Sýra No"); // 0
					tableModelHavale.addColumn("Personel Hesap No");
					tableModelHavale.addColumn("Tutar"); // 2
					tableModelHavale.addColumn("T.C. Kimlik No");
					tableModelHavale.addColumn("Adý Soyadý");

					table_Havale.getColumnModel().getColumn(0).setResizable(false);
					table_Havale.getColumnModel().getColumn(0).setPreferredWidth(5);
					table_Havale.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

					table_Havale.getColumnModel().getColumn(1).setPreferredWidth(100);
					table_Havale.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

					table_Havale.getColumnModel().getColumn(2).setResizable(false);
					table_Havale.getColumnModel().getColumn(2).setPreferredWidth(65);
					table_Havale.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

					table_Havale.getColumnModel().getColumn(3).setResizable(false);
					table_Havale.getColumnModel().getColumn(3).setPreferredWidth(135);
					table_Havale.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

					table_Havale.getColumnModel().getColumn(4).setPreferredWidth(130);
					table_Havale.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

					table_Havale.setRowHeight(30);

					int havaleid = 0;
					for (int i = 0; i < gorevliL.size(); i++) {
						flag = dataSM.compareIban(i);

						if (flag != 1) { // == -1
							havaleid++;
							iban = gorevliL.get(i).getIban();
							double net = gorevliL.get(i).getNetOdenen();
							tcNo = gorevliL.get(i).getTcNo();
							adSoyad = gorevliL.get(i).getGorevliAdi();

							tableModelHavale.addRow(new Object[] { havaleid, iban, net, tcNo, adSoyad });

						}

					}

					b_ListeleH.setEnabled(false);
				}

				else {
					if (listFlag == false) {
						JOptionPane.showMessageDialog(null, "Önce Hesaplama Verileri Girilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Önce Dosya Seçilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});
		b_ListeleH.setForeground(Color.WHITE);
		b_ListeleH.setFont(new Font("Times New Roman", Font.BOLD, 20));
		b_ListeleH.setBackground(new Color(139, 0, 0));
		sP_Havale.setRowHeaderView(b_ListeleH);

		b_ListeleE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (choseFlag == true && listFlag == true) {

					int flag;
					tableModelEft.addColumn("Sýra No"); // 0
					tableModelEft.addColumn("Personel Hesap No");
					tableModelEft.addColumn("Tutar"); // 2
					tableModelEft.addColumn("T.C. Kimlik No");
					tableModelEft.addColumn("Adý Soyadý");

					table_Eft.getColumnModel().getColumn(0).setResizable(false);
					table_Eft.getColumnModel().getColumn(0).setPreferredWidth(5);
					table_Eft.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

					table_Eft.getColumnModel().getColumn(1).setPreferredWidth(100);
					table_Eft.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

					table_Eft.getColumnModel().getColumn(2).setResizable(false);
					table_Eft.getColumnModel().getColumn(2).setPreferredWidth(65);
					table_Eft.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

					table_Eft.getColumnModel().getColumn(3).setResizable(false);
					table_Eft.getColumnModel().getColumn(3).setPreferredWidth(135);
					table_Eft.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

					table_Eft.getColumnModel().getColumn(4).setPreferredWidth(130);
					table_Eft.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

					table_Eft.setRowHeight(30);

					int eftid = 0;
					for (int i = 0; i < gorevliL.size(); i++) {
						flag = dataSM.compareIban(i);

						if (flag == 1) {
							eftid++;
							String eiban = gorevliL.get(i).getIban();
							double net = gorevliL.get(i).getNetOdenen();
							String etcNo = gorevliL.get(i).getTcNo();
							String eadSoyad = gorevliL.get(i).getGorevliAdi();

							tableModelEft.addRow(new Object[] { eftid, eiban, net, etcNo, eadSoyad });

						}

					}

					b_ListeleE.setEnabled(false); // bir kere listelensin

				} else {

					if (listFlag == false) {
						JOptionPane.showMessageDialog(null, "Önce Hesaplama Verileri Girilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Önce Dosya Seçilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					}

				}

			}
		});
		b_ListeleE.setForeground(Color.WHITE);
		b_ListeleE.setFont(new Font("Times New Roman", Font.BOLD, 20));
		b_ListeleE.setBackground(new Color(139, 0, 0));
		sP_Eft.setRowHeaderView(b_ListeleE);

		JScrollPane sP_vMatrahBirles = new JScrollPane();
		tabbedPane.addTab("Vergi Matrah Birle\u015Ftirme Listesi", null, sP_vMatrahBirles, null);
		tabbedPane.setBackgroundAt(5, new Color(143, 188, 143));

		table_vmBirles = new JTable(tableModelBirles);
		sP_vMatrahBirles.setViewportView(table_vmBirles);

		b_ListeleVMBirlestirme.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (choseFlag == true && listFlag == true) {
					tableModelBirles.addColumn("Sýra No"); // 0
					tableModelBirles.addColumn("T.C. Kimlik No");
					tableModelBirles.addColumn("Adý Soyadý");
					tableModelBirles.addColumn("Brüt");
					tableModelBirles.addColumn("Kurumu"); // 4

					table_vmBirles.getColumnModel().getColumn(0).setResizable(false);
					table_vmBirles.getColumnModel().getColumn(0).setPreferredWidth(5);
					table_vmBirles.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

					table_vmBirles.getColumnModel().getColumn(1).setPreferredWidth(100);
					table_vmBirles.getColumnModel().getColumn(1).setResizable(false);
					table_vmBirles.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);

					table_vmBirles.getColumnModel().getColumn(2).setResizable(false);
					table_vmBirles.getColumnModel().getColumn(2).setPreferredWidth(65);
					table_vmBirles.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

					table_vmBirles.getColumnModel().getColumn(3).setPreferredWidth(100);
					table_vmBirles.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

					table_vmBirles.getColumnModel().getColumn(4).setPreferredWidth(150);
					table_vmBirles.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

					table_vmBirles.setRowHeight(30);

					int idb = 0;
					for (int i = 0; i < gorevliL.size(); i++) {
						tcNo = gorevliL.get(i).getTcNo();
						adSoyad = gorevliL.get(i).getGorevliAdi();
						float brutT = gorevliL.get(i).getBrut();
						String gYer = gorevliL.get(i).getGorevYeri();
						idb++;
						tableModelBirles.addRow(new Object[] { idb, tcNo, adSoyad, brutT, gYer });

					}

					b_ListeleVMBirlestirme.setEnabled(false);
				}

				else {
					if (listFlag == false) {
						JOptionPane.showMessageDialog(null, "Önce Hesaplama Verileri Girilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Önce Dosya Seçilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});
		b_ListeleVMBirlestirme.setForeground(Color.WHITE);
		b_ListeleVMBirlestirme.setFont(new Font("Times New Roman", Font.BOLD, 20));
		b_ListeleVMBirlestirme.setBackground(new Color(139, 0, 0));
		sP_vMatrahBirles.setRowHeaderView(b_ListeleVMBirlestirme);

		b_temizle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				if (choseFlag == true || listFlag == true) {

					Object[] options = { "Evet", "Hayýr" };
					int dialogButton = JOptionPane.showOptionDialog(null,
							"Verileri sýfýrlamak istediðinizden emin misiniz?", "UYARI", JOptionPane.DEFAULT_OPTION,
							JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

					if (dialogButton == JOptionPane.YES_OPTION) {

						gorevliL.clear();
						brutListe.clear();

						tableModel.setRowCount(0);
						tableModel.setColumnCount(0);

						tableModel2.setRowCount(0);
						tableModel2.setColumnCount(0);

						tableModelEft.setRowCount(0);
						tableModelEft.setColumnCount(0);

						tableModelHavale.setRowCount(0);
						tableModelHavale.setColumnCount(0);

						tableModelBirles.setRowCount(0);
						tableModelBirles.setColumnCount(0);

						textField_kursiyer.setText(null);
						textField_ilSnvSrml.setText(null);
						textField_komBask.setText(null);
						textField_komUye.setText(null);
						textField_kontDenet.setText(null);
						textField_binaSoruml.setText(null);
						textField_sef.setText(null);
						textField_memur.setText(null);
						textField_sofor.setText(null);
						textField_binaGorevlisi.setText(null);
						textField_guvenlikMemuru.setText(null);
						textField_degerlUye.setText(null);
						textField_bakanlikTems.setText(null);
						textField_sinavTur.setText(null);
						txtMart.setText(null);
						textField_ilSnvSormlYardimci.setText(null);

						choseFlag = false;
						listFlag = false;
						b_Hesaplama.setEnabled(true);
						b_ListeleE.setEnabled(true);
						b_ListeleH.setEnabled(true);
						b_ListeleVMBirlestirme.setEnabled(true);
						lbl_sonucBilgi.setForeground(new Color(51, 51, 51));
					}

				} else {
					if (choseFlag == false) {
						JOptionPane.showMessageDialog(null, "Önce Dosya Seçilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);

					} else {
						JOptionPane.showMessageDialog(null, "Önce Hesaplama Verileri Girilmelidir.", "Uyarý",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}

			}
		});

	}
}
