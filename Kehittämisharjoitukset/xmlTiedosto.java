package demo2;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class xmlTiedosto {

	public static void main(String[] args) throws IOException {

		Kirja uusiKirja = new Kirja("Sune Egyptiläinen", "Waltari, Mika", 1950, 29.90);
		Kirja toinenKirja = new Kirja("Turms Kuolematon", "Waltari, Mika", 1962, 15.50);

		
		
		XMLDecoder dec = null;
		FileOutputStream tiedosto = new FileOutputStream("Kirja.xml");
		XMLEncoder enc = new XMLEncoder(new BufferedOutputStream(tiedosto));


			enc.writeObject(uusiKirja);
			enc.writeObject(toinenKirja);
			enc.close();

			dec = new XMLDecoder(new BufferedInputStream(new FileInputStream("Kirja.xml")));


			System.out.println(uusiKirja);
			System.out.println(toinenKirja);
		}
	}

