package com.example.loginmenu;

public class Uzytkownik {

String nazwa,login,haslo,email;


	public Uzytkownik (String nazwa, String login, String haslo, String email){
		this.nazwa = nazwa;
		this.login = login;
		this.haslo = haslo;
		this.email = email;
	}
	
	public Uzytkownik (String nazwa, String haslo){
		this.nazwa = nazwa;
		//this.login = login;
		this.haslo = haslo;
		//this.email = email;
	}
	}


