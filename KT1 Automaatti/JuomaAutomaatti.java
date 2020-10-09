package demo2;


public class JuomaAutomaatti {
	

	private int tee;
	private int kahvi;
	private int kaakao;

	public JuomaAutomaatti() {
		this.tee = 50;
		this.kahvi = 50;
		this.kaakao = 50;
	}

	public JuomaAutomaatti(int tee, int kahvia, int kaakaota) {
		this.tee = tee;
		this.kahvi = kahvia;
		this.kaakao = kaakaota;
	}

	public int getTee() {
		return tee;
	}

	public void setTee(int tee) {
		this.tee = tee;
	}

	public int getKahvia() {
		return kahvi;
	}

	public void setKahvia(int kahvia) {
		this.kahvi = kahvia;
	}

	public int getKaakaota() {
		return kaakao;
	}

	public void setKaakaota(int kaakaota) {
		this.kaakao = kaakaota;
	}

	public void valmistaKahvi() {
		kahvi = kahvi - 10;
		System.out.println("Odota hetki, kahvisi valmistuu.");
		if (kahvi < 0) {
			kahvi = 0;
			System.out.println("Kahvia ei ole enää jäljellä! Täytä säiliö.");
		}

	}

	public void valmistaTee() {
		tee = tee - 10;
		System.out.println("Odota hetki, teesi valmistuu.");
		if (tee < 0) {
			tee = 0;
			System.out.println("Teetä ei ole enää jäljellä! Täytä säiliö.");
		}

	}

	public void valmistaKaakao() {
		kaakao = kaakao - 10;
		System.out.println("Odota hetki, kaakaosi valmistuu.");
		if (kaakao < 0) {
			kaakao = 0;
			System.out.println("Kaakaota ei ole enää jäljellä! Täytä säiliö.");
		}
	
	}

	
	public String toString() {
		return "JuomaAutomaatti [tee=" + tee + ", kahvi=" + kahvi + ", kaakao=" + kaakao + "]";
	}

}