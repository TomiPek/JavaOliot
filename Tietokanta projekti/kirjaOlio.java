package kaikenlaista;

import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

public class kirjaOlio {
	
	public JToolBar toolBar;
	public ScrollPane scrollPane;
	public static JTable table;
	public static DefaultTableModel model;

	public kirjaOlio() {

		try {

			model = new DefaultTableModel();
			model.addColumn("Kirjan nimi");
			model.addColumn("Kirjoittaja");
			model.addColumn("Julkaisuvuosi");
	
			// Luodaan tietokantayhteys
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oma", "root", "");

			// Luodaan Statement-olio, joka keskustelee tietokannan kanssa
			Statement stmt = conn.createStatement();

			// Luodaan tulosjoukko, johon sijoitetaan kyselyn tulos
			ResultSet rs = stmt.executeQuery("SELECT * FROM kirjoja");
			// Tulosjoukko k‰yd‰‰n silmukassa l‰pi, joka kierroksella taulukkoon lis‰t‰‰n
			// dataa

			while (rs.next()) {
				System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
				model.addRow(new Object[] { rs.getString(1), rs.getString(2), rs.getString(3) });
			}

			// Suljetaan tietokanyhteys
			conn.close();

			// Varaudutaan virheisiin
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}



	public void uusiKirja() {


		JTextField nimi = new JTextField(20);
		JTextField kirjailija = new JTextField(20);
		JTextField jvuosi = new JTextField(10);

		JPanel myPanel = new JPanel();

		myPanel.add(new JLabel("Kirjan nimi:"));
		myPanel.add(nimi);

		myPanel.add(new JLabel("Kirjailija:"));
		myPanel.add(kirjailija);

		myPanel.add(new JLabel("Julkaisu vuosi:"));
		myPanel.add(jvuosi);

		int result = JOptionPane.showConfirmDialog(null, myPanel, "Lis‰‰ uusi kirja", JOptionPane.OK_CANCEL_OPTION);

		if (result == JOptionPane.OK_OPTION) {

			model.addRow(new Object[] { nimi.getText(), kirjailija.getText(), jvuosi.getText() });

			try {

				String knimi = nimi.getText();
				String tekij‰ = kirjailija.getText();
				int juvuosi = Integer.parseInt(jvuosi.getText());

				// Luodaan tietokantayhteys
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oma", "root", "");

				String query = (" insert into kirjoja (KirjanNimi, Kirjoittaja, JulkaisuVuosi)" + " values (?, ?, ?)");

				// create the mysql insert preparedstatement
				PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
				preparedStmt.setString(1, knimi);
				preparedStmt.setString(2, tekij‰);
				preparedStmt.setInt(3, juvuosi);

				// execute the preparedstatement
				preparedStmt.execute();

				// Suljetaan tietokanyhteys
				conn.close();

				// Varaudutaan virheisiin
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}
}
		
		
