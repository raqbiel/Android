package com.example.produkty;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProduktyMainActivity extends Activity {

	String[] auta = {
			"Audi",
			"VW",
			"Fiat",
			"Peugeot",
	};
	
	TextView tv;
	EditText et;
	String klucz;
	Produkt produkt1 = new Produkt();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.produkty_main);
		
	tv = (TextView)findViewById(R.id.textView1);		
	et = (EditText)findViewById(R.id.autoCompleteTextView1);
	klucz = et.getText().toString();
	
	ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,auta);
	
	AutoCompleteTextView autocomplete = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
	autocomplete.setThreshold(2);
	autocomplete.setAdapter(adapter);
	
	}
	public void Szukaj(View v){
		
		tv = (TextView)findViewById(R.id.textView1);		
		et = (EditText)findViewById(R.id.autoCompleteTextView1);
		klucz = et.getText().toString();	
		tv.setShadowLayer(0, 0, 0, Color.RED);
	if("Audi".equals(klucz) || "audi".equals(klucz)){
	//tv.setText(produkt1.nazwa+ "\n" +produkt1.model+ "\n" +produkt1.rok);	
	 tv.setText(produkt1.audi);	
	}
	else if("VW".equals(klucz) || "vw".equals(klucz)){
		 tv.setText(produkt1.vw);	
	}else if("Peugeot".equals(klucz) || "peugeot".equals(klucz)){
		 tv.setText(produkt1.peugeot);
	}else if("Fiat".equals(klucz) || "fiat".equals(klucz)){
		 tv.setText(produkt1.fiat);
	}else{
		tv.setText("Nie ma takiego samochodu");
	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.produkty_main, menu);
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
