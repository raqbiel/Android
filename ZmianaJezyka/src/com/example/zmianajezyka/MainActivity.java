package com.example.zmianajezyka;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

EditText email,password;
TextView logo, TV_Deutsch,TV_English,TV_Polish;
Locale mylocale;	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	//Wpisywanie danych	
	email = (EditText)findViewById(R.id.emailid);
	password = (EditText)findViewById(R.id.password);
	
	//Wyswietlanie danych
	logo = (TextView)findViewById(R.id.welcome);
	//Wybór jêzyka
	TV_Deutsch = (TextView)findViewById(R.id.TVDeutsch);
	TV_English = (TextView)findViewById(R.id.TVEnglish);
	TV_Polish = (TextView)findViewById(R.id.TVPolish);
	
//Ustawianie Niemieckiego	
TV_Deutsch.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Toast.makeText(MainActivity.this, "Deutsch Language", Toast.LENGTH_SHORT).show();
			setLanguage("de");
			
		}
	});	
//Ustawianie Angielskiego(domyœlny)
TV_English.setOnClickListener(new View.OnClickListener() {
		
	@Override
	public void onClick(View v) {
		Toast.makeText(MainActivity.this, "English Language", Toast.LENGTH_SHORT).show();
		setLanguage("en");
		
	}
});	
//Ustawianie Polskiego
TV_Polish.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		Toast.makeText(MainActivity.this, "Polish Language", Toast.LENGTH_SHORT).show();
		setLanguage("pl");
		
	}
});
}
protected void setLanguage(String language){
	mylocale = new Locale(language);
	Resources resources = getResources();
	DisplayMetrics dm = resources.getDisplayMetrics();
	Configuration konf = resources.getConfiguration();
	konf.locale = mylocale;
	resources.updateConfiguration(konf, dm);
	Intent refreshIntent = new Intent(MainActivity.this,MainActivity.class);
	finish();
	startActivity(refreshIntent);
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
