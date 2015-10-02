package com.example.loginmenu;

import android.content.Context;
import android.content.SharedPreferences;

public class UzytkownikLokalny {

public static final String SP_NAME = "userDetails";
	SharedPreferences userLocalDatabase;
	
	public UzytkownikLokalny(Context context){
		userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
		
	}

	public void DaneUzytkownika(Uzytkownik uzytkownik){
		SharedPreferences.Editor edycja = userLocalDatabase.edit();
		edycja.putString("nazwa", uzytkownik.nazwa);
		edycja.putString("login", uzytkownik.login);
		edycja.putString("has³o", uzytkownik.haslo);
		edycja.putString("email", uzytkownik.email);
		edycja.commit();
		
	}
	
	public Uzytkownik ZalogowanyUzytkownik(){
		/* if (userLocalDatabase.getBoolean("loggedIn", false) == false) {
	            return null;
	        }*/
		String nazwa = userLocalDatabase.getString("nazwa", "");
		String login = userLocalDatabase.getString("login", "");
		String haslo = userLocalDatabase.getString("has³o", "");
		String email = userLocalDatabase.getString("email", "");
		
		Uzytkownik uzytkownik = new  Uzytkownik (nazwa,login,haslo,email);
		return uzytkownik;
	}
	
	public void UstawZalogowanegoUzytkownika(boolean zalogowany){
		SharedPreferences.Editor edycja = userLocalDatabase.edit();
		edycja.putBoolean("Zalogowany", zalogowany);
		edycja.commit();
	}
	
	public boolean bierzDaneZalogowanegoUzytkownika(){
		if(userLocalDatabase.getBoolean("Zalogowany", false) == true){
			return true;
		}else{
			return false;
	}
}
	
	public void WyczyscDaneUzytkownika(){
		SharedPreferences.Editor edycja = userLocalDatabase.edit();
		edycja.clear();
		edycja.commit();
	}
	
}
