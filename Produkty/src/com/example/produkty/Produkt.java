package com.example.produkty;



public class Produkt {

Ceny cena = new Ceny();	
	
	String audi;
	String audinazwa = "Audii";
	String audimodel = "A4";
	int audirok = 1991;
	
	String vw;
	String vwnazwa = "VW";
	String vwmodel = "Polo";
	int vwrok = 1994;
	
	String peugeot;
	String peugeotnazwa = "Peugoet";
	String peugeotmodel = "406";
	int peugeotrok = 1996;
	
	String fiat;
	String fiatnazwa = "Fiat";
	String fiatmodel = "126p";
	int fiatrok = 1986;
	
public Produkt(){
	
audi = ("Nazwa: " +audinazwa+ "\n"+ "Model: " +audimodel+ "\n"+ "Rok: " +audirok+ "\n" +cena.dane);
peugeot = ("Nazwa: " +peugeotnazwa+ "\n"+ "Model: " +peugeotmodel+ "\n"+ "Rok: " +peugeotrok);	
vw = ("Nazwa: " +vwnazwa+ "\n"+ "Model: " +vwmodel+ "\n"+ "Rok: " +vwrok);
fiat = ("Nazwa: " +fiatnazwa+ "\n"+ "Model: " +fiatmodel+ "\n"+ "Rok: " +fiatrok);	
}


}