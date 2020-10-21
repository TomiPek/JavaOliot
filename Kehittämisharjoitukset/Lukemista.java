package demo2;

import java.util.Scanner;
import java.io.*;

public class Lukemista {

	public static void main(String[] args) throws IOException {
		File tiedosto = new File("C:\\Users\\galtt\\Desktop\\teksti.txt");
		Scanner lukija = new Scanner(System.in);
		try {

			FileReader fr = null;
			String str;
			System.out.print("Anna haettava sana: ");
			String sana = lukija.nextLine();

			int rivinumero = 0;
			fr = new FileReader(tiedosto);
			BufferedReader br = new BufferedReader(fr);
			while ((str = br.readLine()) != null) {
				rivinumero++;
				if (str.contains(sana)) {

					System.out.println(rivinumero + ": " + str);
				}

			}
		} catch (Exception e) {
			System.out.println("Virhe!");
		}
	}

}
