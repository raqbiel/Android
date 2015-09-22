package com.example.loginmenu;

import org.apache.http.impl.execchain.ServiceUnavailableRetryExec;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity {

	Button bLogin, reg;
	EditText etUser,etPass;
	//po³aczenie do ->>
	UzytkownikLokalny UzytkownikLokalny;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		bLogin = (Button)findViewById(R.id.bLogin);
		etUser = (EditText)findViewById(R.id.wpiszlogin);
		etPass = (EditText)findViewById(R.id.wpiszhaslo);
		reg = (Button)findViewById(R.id.reg);
		
		UzytkownikLokalny = new UzytkownikLokalny(this);
		
		}			

	public void ladujLogin(View v) {

			String login = etUser.getText().toString();
			String pass = etPass.getText().toString();

			Uzytkownik uzytkownik = new Uzytkownik(login, pass);
			
			uwierzytelnianie(uzytkownik);
			
			UzytkownikLokalny.DaneUzytkownika(uzytkownik);
			UzytkownikLokalny.UstawZalogowanegoUzytkownika(true);	
		}




		public void ladujReg(View v) {
				Intent intent = new Intent(Login.this,Rejestracja.class);
				startActivity(intent);
		}


	public void uwierzytelnianie(Uzytkownik uzytkownik){
		ProsbaSerwera prosbaSerwera = new ProsbaSerwera(this);
		prosbaSerwera.PobierzDaneUzytkownikaWTle(uzytkownik, new OdpowiedzUzytkownika(){

			@Override
			public void done(Uzytkownik powrotUzytkownika) {
				if(powrotUzytkownika == null){
					pokazBladWiadomosci();
			}else{
				UzytkownikZalogowany(powrotUzytkownika);
			}
		}
	});
}
	
	private void pokazBladWiadomosci(){
		AlertDialog.Builder dialogbuilder = new AlertDialog.Builder(Login.this);
		dialogbuilder.setMessage("Niepoprawne dane u¿ytkownika");
		dialogbuilder.setPositiveButton("Ok", null);
		dialogbuilder.show();
	}
	
	private void UzytkownikZalogowany(Uzytkownik uzytkownik){
		UzytkownikLokalny.DaneUzytkownika(uzytkownik);
		UzytkownikLokalny.UstawZalogowanegoUzytkownika(true);
		
		startActivity(new Intent(this,MainActivity.class));
	}
	
	
}

