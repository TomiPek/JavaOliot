package kaikenlaista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Font;
import java.awt.Color;

public class KirjastoTk extends JFrame {

	// N‰kyvyydet ja itselleni pikku vinkki, ett‰ Jpaneliin taulukon saaminen
	// n‰kyville on tuskaisaa.. scrollpanel paljon helpompi
	private JPanel contentPane;
	private JToolBar toolBar;
	private ScrollPane scrollPane;
	static JTable table;
	static DefaultTableModel model;

	// Sovelluksen k‰ynnistys
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pakup frame = new pakup();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Ikkunan valmistus
	public KirjastoTk() {

		// Taulukon otsikot ja n‰kyvyys
		model = new DefaultTableModel();
		model.addColumn("Kirjan nimi");
		model.addColumn("Kirjoittaja");
		model.addColumn("Julkaisuvuosi");

		table = new JTable(model);
		JScrollPane scrollPane = new JScrollPane();

		// Ikkunan m‰‰rittely

		JFrame ikkuna = new JFrame();
		ikkuna.setFont(new Font("Arial Black", Font.BOLD, 13));
		ikkuna.setForeground(Color.ORANGE);
		ikkuna.setTitle("Kirjaston tietokanta");

		// layoutin m‰‰rittely

		ikkuna.getContentPane().add(scrollPane, BorderLayout.CENTER);

		// Taulukon n‰kyvyys gui:ssa scrollpanen kautta
		scrollPane.setViewportView(table);

		// Jpanel nappeja varten layoutin pohjalle
		JPanel panel_1 = new JPanel();
		ikkuna.getContentPane().add(panel_1, BorderLayout.SOUTH);

		// Lis‰‰ kirja nappi ja metodin kutsu
		JButton btnNewButton = new JButton("Lis‰‰ kirja");
		btnNewButton.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				uusiKirja();

			}
		});
		panel_1.add(btnNewButton);

		// Poista kirja nappi ja toiminto
		JButton btnNewButton_1 = new JButton("Poista kirja");
		btnNewButton_1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
				poistaKirja(rowIndex);
			}
		});
		panel_1.add(btnNewButton_1);

		// P‰ivit‰ nappi ja p‰ivit‰ metodin kutsu
		JButton btnNewButton_2 = new JButton("P‰ivit‰");
		btnNewButton_2.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 13));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p‰ivit‰();

			}
		});
		panel_1.add(btnNewButton_2);

		// N‰kyvyys ja komponentit skaalautumaan oikein
		ikkuna.pack();

		JMenuBar menuBar = new JMenuBar();

		// Menubar ja close toiminto
		ikkuna.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("File");
		mnNewMenu.setFont(new Font("Times New Roman", Font.BOLD, 12));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Close");
		mntmNewMenuItem.setFont(new Font("Times New Roman", Font.BOLD, 12));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		ikkuna.setVisible(true);

		try {

			// Luodaan local tietokantayhteys
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oma", "root", "");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM kirjoja");
			// Tulosjoukko k‰yd‰‰n silmukassa ja lis‰t‰‰n tarpeen ollen rivej‰

			while (rs.next()) {
				System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
				model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) });
			}

			// Yhteyden sulkeminen
			conn.close();

			// Varaudutaan virheisiin ja ilmoitetaan jos ei toimi
		} catch (Exception e) {
			System.out.print("Do not connect to DB - Error:" + e);
		}

	}

	private static void p‰ivit‰() {

		try {
			// ilman t‰t‰ p‰ivit‰ nappi lis‰‰ kaikki rivit joka kerta uusiks taulukkoon
			model.setRowCount(0);

			// Luodaan local tietokantayhteys
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oma", "root", "");

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * FROM kirjoja");
			// Tulosjoukko k‰yd‰‰n silmukassa ja lis‰t‰‰n tarpeen ollen rivej‰

			while (rs.next()) {
				System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
				model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) });
			}
			System.out.println("-------------------------------------");
			// Yhteyden sulkeminen
			conn.close();

			// Varaudutaan virheisiin ja ilmoitetaan jos ei toimi
		} catch (Exception e) {
			System.out.print("Do not connect to DB - Error:" + e);
		}
	}

	private static void poistaKirja(int rowIndex) {

		try {

			// Ker‰t‰‰n tietoa taulukosta poistamista varten. T‰ss‰ k‰ytin pelk‰st‰‰ nime‰
			// ja kirjailijaa
			String bNimi = (String) table.getModel().getValueAt(rowIndex, 0);
			String kirjanKirjoittaja = (String) table.getModel().getValueAt(rowIndex, 1);

			// Luodaan tietokantayhteys
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oma", "root", "");

			// Poistetaan nimen ja kirjoittajan perusteella
			String query = "delete from kirjoja where KirjanNimi = ? AND Kirjoittaja = ?";

			PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
			preparedStmt.setString(1, bNimi);
			preparedStmt.setString(2, kirjanKirjoittaja);

			// Suoritetaan komento
			preparedStmt.execute();

			// Suljetaan tietokanyhteys
			conn.close();

			// Varaudutaan virheisiin
		} catch (Exception e) {
			System.out.print("Do not connect to DB - Error:" + e);
		}

		((DefaultTableModel) table.getModel()).removeRow(rowIndex);
	}

	private static void uusiKirja() {

		try {

			// Kirjan tietojen lis‰yst‰ varten kent‰t ja niiden koot
			JTextField nimi = new JTextField(35);
			JTextField kirJoittaja = new JTextField(25);
			JTextField julVuos = new JTextField(10);

			JPanel myPanel = new JPanel();

			// Otsikot kentille
			myPanel.add(new JLabel("Kirjan nimi:"));
			myPanel.add(nimi);

			myPanel.add(new JLabel("Kirjailija nimi:"));
			myPanel.add(kirJoittaja);

			myPanel.add(new JLabel("Kirjan julkaisuvuosi:"));
			myPanel.add(julVuos);

			int hyv‰ksynt‰ = JOptionPane.showConfirmDialog(null, myPanel, "Lis‰‰ kirja", JOptionPane.OK_CANCEL_OPTION);

			if (hyv‰ksynt‰ == JOptionPane.OK_OPTION) {
				// Uuden rivin lis‰‰minen

				model.addRow(new Object[] { nimi.getText(), kirJoittaja.getText(), julVuos.getText() });

				String uusiNimi = nimi.getText();
				String uusiKirjoittaja = kirJoittaja.getText();
				int uusiJulkaisuVuosi = Integer.parseInt(julVuos.getText());

				// Luodaan tietokantayhteys
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oma", "root", "");

				// Tietokantaan arvot talteen

				String query = (" insert into kirjoja (KirjanNimi, Kirjoittaja, JulkaisuVuosi)" + " values (?, ?, ?)");

				PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
				preparedStmt.setString(1, uusiNimi);
				preparedStmt.setString(2, uusiKirjoittaja);
				preparedStmt.setInt(3, uusiJulkaisuVuosi);

				// Suoritus
				preparedStmt.execute();

				// Suljetaan tietokanyhteys
				conn.close();

				// Varaudutaan virheisiin
			}
		} catch (Exception e) {
			System.out.print("Do not connect to DB - Error:" + e);
		}

	}
}