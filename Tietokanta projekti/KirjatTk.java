package kaikenlaista;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import java.awt.ScrollPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class KirjatTk extends JFrame {

	private JToolBar toolBar;
	private ScrollPane scrollPane;
	static JTable table;
	static DefaultTableModel model;

	public static void main(String[] args) {


		// K‰ynnistet‰‰n p‰‰ohjelma
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				kirjaOlio uus = new kirjaOlio();
				System.out.println("=====================================================================");

				try {
					KirjatTk ikkuna = new KirjatTk(uus);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public KirjatTk(kirjaOlio uus) {
		kirjaOlio omaKirjasto = new kirjaOlio();


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
				omaKirjasto.uusiKirja();				

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
			// Yhteyden sulkeminen
		conn.close();
		System.out.println("=====================================================================");

		// Varaudutaan virheisiin ja ilmoitetaan jos ei toimi
		} catch (Exception e) {
			System.out.print("Do not connect to DB - Error:"+e);
		}
	}
	
	private static void poistaKirja(int rowIndex) {
				
		try {
			
			// Ker‰t‰‰n tietoa taulukosta poistamista varten. T‰ss‰ k‰ytin pelk‰st‰‰ nime‰ ja kirjailijaa 
			String bNimi = (String) table.getModel().getValueAt(rowIndex, 0);
			String kirjanKirjoittaja = (String) table.getModel().getValueAt(rowIndex, 1);
			

			// Luodaan tietokantayhteys
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/oma", "root", "");

			// Poistetaan nimen ja kirjoittajan perusteella
			String query = "delete from kirjoja where KirjanNimi = ? AND Kirjoittaja = ?";
			
			
		      PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
		      preparedStmt.setString (1, bNimi);
		      preparedStmt.setString (2, kirjanKirjoittaja);

		      // Suoritetaan komento
		      preparedStmt.execute();
				
		// Suljetaan tietokanyhteys
		conn.close();

		// Varaudutaan virheisiin
		} catch (Exception e) {
			System.out.print("Do not connect to DB - Error:"+e);
		}
		
		((DefaultTableModel)table.getModel()).removeRow(rowIndex);
	}
	


		
	}
