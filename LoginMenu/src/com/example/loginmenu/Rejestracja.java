package com.example.loginmenu;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Rejestracja extends Activity {

	
	Button reg;
	EditText etNazwa,etLogin,etPass,etMail;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rejestracja);
		
		reg = (Button)findViewById(R.id.bReg);
		etNazwa = (EditText)findViewById(R.id.wpisznazwe);
		etLogin = (EditText)findViewById(R.id.wpiszlogin);
		etPass = (EditText)findViewById(R.id.wpiszhaslo);
		etMail = (EditText)findViewById(R.id.wpiszemail);
		
		OnClickListener l = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.bReg:
					
					String nazwa = etNazwa.getText().toString();
					String login = etLogin.getText().toString();
					String haslo = etPass.getText().toString();
					String email = etMail.getText().toString();
					Uzytkownik uzytkownik = new Uzytkownik(nazwa, login, haslo, email);
					
					
					rejestracjaUzytkownika(uzytkownik);
					break;
				}
				
			}
		};
		reg.setOnClickListener(l);
	}

	public void rejestracjaUzytkownika(Uzytkownik uzytkownik){
		ProsbaSerwera prosbaSerwera = new ProsbaSerwera(this);
		prosbaSerwera.DaneUzytkownikaWTle(uzytkownik, new OdpowiedzUzytkownika(){

			@Override
			public void done(Uzytkownik powrotUzytkownika) {
				Intent loginIntent = new Intent(Rejestracja.this, Login.class);
				 startActivity(loginIntent);
			
		}
	});
}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rejestracja, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
