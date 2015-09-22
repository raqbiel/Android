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

public class MainActivity extends Activity {

	Button logout;
	EditText etNazwa,etLogin;
	UzytkownikLokalny UzytkownikLokalny;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		logout = (Button)findViewById(R.id.bLogout);
		etNazwa = (EditText)findViewById(R.id.wpisznazwe);
		etLogin = (EditText)findViewById(R.id.wpiszlogin);
		
		UzytkownikLokalny = new UzytkownikLokalny(this);
		
			
	
		OnClickListener l = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				switch(v.getId()){
				case R.id.bLogout:
					UzytkownikLokalny.WyczyscDaneUzytkownika();
					UzytkownikLokalny.UstawZalogowanegoUzytkownika(false);
					
					Intent intent = new Intent(MainActivity.this,Login.class);
					startActivity(intent);
					break;
					
				}
				
			}
		};
		logout.setOnClickListener(l);
	}

	protected void onStart(){
		super.onStart();
		if(uwierzytelnianie() == true){
			pokazDaneUzytkownika();
		}else{
			startActivity(new Intent(MainActivity.this, Login.class));
		}
	}
	
	private boolean uwierzytelnianie(){
		return UzytkownikLokalny.bierzDaneZalogowanegoUzytkownika();
	}
	
	private void pokazDaneUzytkownika(){
		Uzytkownik uzytkownik = UzytkownikLokalny.ZalogowanyUzytkownik();
		
		etNazwa.setText(uzytkownik.nazwa);
		etLogin.setText(uzytkownik.login);
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
