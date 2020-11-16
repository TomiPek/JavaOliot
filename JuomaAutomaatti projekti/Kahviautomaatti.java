package demo2;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Kahviautomaatti extends JFrame {

	// Lisätään componentit tänne, että ne on käytössä koko luokassa.
	private JTextField txtKahvia;
	private JTextField txtTeet;
	private JTextField txtKaakaota;
	private JPanel contentPane;

	private JButton Kahvin;
	private JButton Teen;
	private JButton Kaakaon;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem kahvinMaara;
	private JMenuItem teenMaara;
	private JMenuItem kaakaonMaara;
	private JMenuItem tallennus;
	private JMenuItem lataus;
	private JMenuItem lopetus;
	private JMenuItem mntmNewMenuItem_6;
	private JMenu tiedot;

	public static void main(String[] args) {
		
		// Käynnistetään pääohjelma
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				// JuomaAutomaatin kutsu
				JuomaAutomaatti uus = new JuomaAutomaatti();
				try {
					Kahviautomaatti frame = new Kahviautomaatti(uus);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Kahviautomaatti(JuomaAutomaatti uus) {

		JuomaAutomaatti omaKone = new JuomaAutomaatti();
		
		// Framen käyttöönotto

		JFrame frame = new JFrame("Kahviautomaatti GUI");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Kahviautomaatti.class.getResource("/demo3/kone.png")));
		frame.getContentPane().setLayout(new GridLayout(3, 3));
		frame.setSize(750, 450);
		
		// Kahvin valmistus nappi ja varoitus väri kun aine loppumassa ja täytön jälkeen värin palautus

		JButton Kahvin = new JButton("Kahvi");
		Kahvin.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		Kahvin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				omaKone.valmistaKahvi();
				txtKahvia.setText("Jäljellä: " + omaKone.getKahvia());
				if (omaKone.getKahvia() <= 20) {
					txtKahvia.setForeground(Color.RED);
				} else if (omaKone.getKahvia() > 20) {
					txtKahvia.setForeground(Color.BLACK);

				}
			}
		});
		Kahvin.setIcon(new ImageIcon(Kahviautomaatti.class.getResource("/demo3/kahvi.png")));
		frame.getContentPane().add(Kahvin);
		
		// Kahville tekstikenttä

		txtKahvia = new JTextField();
		txtKahvia.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtKahvia.setText("Kahvia:");
		frame.getContentPane().add(txtKahvia);
		txtKahvia.setColumns(10);
		
		// Teen nappi

		JButton Teen = new JButton("Tee");
		Teen.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		Teen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				omaKone.valmistaTee();
				txtTeet.setText("Jäljellä: " + omaKone.getTee());
				if (omaKone.getTee() <= 20) {
					txtTeet.setForeground(Color.RED);
				} else if (omaKone.getTee() > 20) {
					txtTeet.setForeground(Color.BLACK);
				}
			}
		});
		Teen.setIcon(new ImageIcon(Kahviautomaatti.class.getResource("/demo3/tee.png")));
		frame.getContentPane().add(Teen);
		
		// Teen teksikenttä

		txtTeet = new JTextField();
		txtTeet.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtTeet.setText("Teet\u00E4:");
		frame.getContentPane().add(txtTeet);
		txtTeet.setColumns(10);
		
		// Kaakaon nappi

		JButton Kaakaon = new JButton("Kaakao");
		Kaakaon.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		Kaakaon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				omaKone.valmistaKaakao();
				txtKaakaota.setText("Jäljellä: " + omaKone.getKaakaota());
				if (omaKone.getKaakaota() <= 20) {
					txtKaakaota.setForeground(Color.RED);
				} else if (omaKone.getKaakaota() > 20) {
					txtKaakaota.setForeground(Color.BLACK);

				}
			}
		});
		Kaakaon.setIcon(new ImageIcon(Kahviautomaatti.class.getResource("/demo3/kaakao.png")));
		frame.getContentPane().add(Kaakaon);
		
		// Kaakaon tekstikenttä

		txtKaakaota = new JTextField();
		txtKaakaota.setFont(new Font("Times New Roman", Font.BOLD, 18));
		txtKaakaota.setText("Kaakaota:");
		frame.getContentPane().add(txtKaakaota);
		txtKaakaota.setColumns(10);
		
		// Menu valikko

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Yll\u00E4pito");
		menuBar.add(mnNewMenu);
		
		// Menun valikon alta löytyvät toiminnot
		
		// Kahvin täyttö

		JMenuItem kahvinMaara = new JMenuItem("Aseta kahvin m\u00E4\u00E4r\u00E4");
		kahvinMaara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String uusiArvo = JOptionPane.showInputDialog(null, "Anna uusi arvo: ");
				int uusiKahvi = Integer.parseInt(uusiArvo);
				omaKone.setKahvia(uusiKahvi);
				txtKahvia.setText("Jäljellä: " + uusiKahvi);

			}
		});
		mnNewMenu.add(kahvinMaara);
		
		// Teen täyttö

		JMenuItem teenMaara = new JMenuItem("Aseta teen m\u00E4\u00E4r\u00E4");
		teenMaara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String uusiArvo = JOptionPane.showInputDialog(null, "Anna uusi arvo: ");
				int uusiTee = Integer.parseInt(uusiArvo);
				omaKone.setTee(uusiTee);
				txtTeet.setText("Jäljellä: " + uusiTee);
			}
		});
		mnNewMenu.add(teenMaara);
		
		// Kaakaon täyttö

		JMenuItem kaakaonMaara = new JMenuItem("Aseta kaakaon m\u00E4\u00E4r\u00E4");
		kaakaonMaara.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String uusiArvo = JOptionPane.showInputDialog(null, "Anna uusi arvo: ");
				int uusiKaakao = Integer.parseInt(uusiArvo);
				omaKone.setKaakaota(uusiKaakao);
				txtKaakaota.setText("Jäljellä: " + uusiKaakao);
			}
		});
		mnNewMenu.add(kaakaonMaara);
		
		// Automaatin tilan tallennus toiminto

		JMenuItem tallennus = new JMenuItem("Tallenna automaatin tila");
		tallennus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Sarjallistamista.kirjoitaTiedostoon(omaKone);

				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}
		});
		mnNewMenu.add(tallennus);
		
		// Automaatin tilan muistin lataaminen

		JMenuItem lataus = new JMenuItem("Lataa automaatti");
		lataus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					JuomaAutomaatti uusi = Sarjallistamista.lueTiedostosta();
					omaKone.setKaakaota(uusi.getKaakaota());
					omaKone.setKahvia(uusi.getKahvia());
					omaKone.setTee(uusi.getTee());
					txtKahvia.setText("Jäljellä: " + uusi.getKahvia());
					txtTeet.setText("Jäljellä: " + uusi.getTee());
					txtKaakaota.setText("Jäljellä: " + uusi.getKaakaota());

				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		mnNewMenu.add(lataus);
		
		// Automaatin sulkeminen

		JMenuItem lopetus = new JMenuItem("Lopeta");
		lopetus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnNewMenu.add(lopetus);

		JMenu tiedot = new JMenu("Tietoja ohjelmasta");
		menuBar.add(tiedot);
		
		// Automaatin versiotiedot

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Versiotiedot");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Versiotiedot ikkuna = new Versiotiedot();
				ikkuna.setVisible(true);

			}
		});
		tiedot.add(mntmNewMenuItem_6);
		
		// Sulkeminen ja ikkunan näkyvyyden määrittely
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);
	}

}
