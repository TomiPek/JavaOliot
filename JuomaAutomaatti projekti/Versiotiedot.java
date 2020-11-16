package demo2;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Versiotiedot extends JFrame {
	public Versiotiedot() {
		getContentPane().setLayout(null);
		setSize(450, 300);

		JLabel lblNewLabel = new JLabel("JUOMA-AUTOMAATTI V. 1.0");
		lblNewLabel.setFont(new Font("Papyrus", Font.BOLD, 16));
		lblNewLabel.setBounds(59, 101, 320, 52);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(Versiotiedot.class.getResource("/demo3/kone.png")));
		lblNewLabel_1.setBounds(58, 24, 64, 63);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("TEKIJ\u00C4: Tomi Pekurinen");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_2.setBounds(59, 140, 298, 70);
		getContentPane().add(lblNewLabel_2);
	}
}
