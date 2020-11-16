package demo2;

public class program {

	public static void main(String[] args) {

		JuomaAutomaatti omaKone = new JuomaAutomaatti();
		JuomaAutomaatti naapurinKone = new JuomaAutomaatti(150, 200, 300);

		System.out.println(omaKone);
		System.out.println(naapurinKone);
		System.out.println();
		
		omaKone.valmistaKahvi();
		omaKone.valmistaKahvi();

		System.out.println(omaKone);
		System.out.println(naapurinKone);
		System.out.println();

		naapurinKone.valmistaKaakao();
		naapurinKone.valmistaKahvi();

		System.out.println(omaKone);
		System.out.println(naapurinKone);

		System.out.println();
		omaKone.valmistaTee();
		System.out.println(omaKone);
		System.out.println();

		omaKone.valmistaTee();
		System.out.println(omaKone);
		System.out.println();

		omaKone.valmistaTee();
		System.out.println(omaKone);
		System.out.println();

		omaKone.valmistaTee();
		System.out.println(omaKone);
		System.out.println();

		omaKone.valmistaTee();
		System.out.println(omaKone);
		System.out.println();
		
		omaKone.valmistaTee();
		System.out.println(omaKone);
		System.out.println();
		
		omaKone.valmistaTee();
		System.out.println(omaKone);
		System.out.println();

	}
}
