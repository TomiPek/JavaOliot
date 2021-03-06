package demo2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Lampotilamuunnin {

	public static void main(String[] args) {
		
		// Määritellään frame
		JFrame frame = new JFrame("Celcius Converter"); 
		
		// Määritellää framelle koko
		frame.setSize(550,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// Määritellään layot
	    frame.getContentPane().setLayout(new GridLayout(2,2));
	    
	    // Luodaan tekstikentät ja nappi
	    JLabel teksti = new JLabel("Celcius");
	    JButton b1=new JButton("Convert");	    
	    JTextField fAsteet = new JTextField();
	    JTextField cAsteet = new JTextField();
	
	    // Lisätään frameen nappi ja tekstikenttä
	    frame.add(teksti);
	    frame.add(cAsteet);
	    frame.add(b1);
	    frame.add(fAsteet);

	    // Lisätään toiminnallisuus napille
	    b1.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e)
			{
				// Lasku
				try {
				String tulos = cAsteet.getText();
				double tulos1 = Double.parseDouble(tulos);
				double lopullinen = (tulos1 * 1.8 + 32);
				fAsteet.setText("Fahrenheit " + lopullinen);
				} catch (Exception p) {
					System.out.println("Virheellinen syöte!");
				}
			}
				
			
	    });


		frame.setVisible(true);

		}

}
