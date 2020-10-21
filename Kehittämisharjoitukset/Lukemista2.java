package demo2;

import java.util.Scanner;
import java.io.*;

public class Lukemista2 {

	public static void main(String[] args) {
		Scanner lukija = null;
		File tiedosto = new File("C:\\Users\\galtt\\Desktop\\teksti.txt");
		
		try {
			Scanner omalukija = new Scanner(tiedosto);
			int rivinumero = 0;

			while (omalukija.hasNextLine()) {
				rivinumero++;

				String rivi = omalukija.nextLine();
				rivi = rivi.replace("Java", "****");
				System.out.println(rivinumero + ": " +rivi);

			}

		} catch (Exception e) {
			System.out.println("virhe!");
		}
	


	}

}
