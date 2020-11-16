package demo2;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
public class Lampotilamuunnin {

	public static void main(String[] args) {
		
		// M‰‰ritell‰‰n frame
		JFrame frame = new JFrame("Celcius Converter"); 
		
		// M‰‰ritell‰‰ framelle koko
		frame.setSize(550,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		// M‰‰ritell‰‰n layot
	    frame.getContentPane().setLayout(new GridLayout(2,2));
	    
	    // Luodaan tekstikent‰t ja nappi
	    JLabel teksti = new JLabel("Celcius");
	    JButton b1=new JButton("Convert");	    
	    JTextField fAsteet = new JTextField();
	    JTextField cAsteet = new JTextField();
	
	    // Lis‰t‰‰n frameen nappi ja tekstikentt‰
	    frame.add(teksti);
	    frame.add(cAsteet);
	    frame.add(b1);
	    frame.add(fAsteet);

	    // Lis‰t‰‰n toiminnallisuus napille
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
					System.out.println("Virheellinen syˆte!");
				}
			}
				
			
	    });


		frame.setVisible(true);

		}

}
