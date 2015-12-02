package com.example.produkty;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class ProduktyMainActivity extends Activity {

	String[] auta = {
			"Audi",
			"VW",
			"Fiat",
			"Peugeot",
			"Mazda",
			"KIA",
	};
	
	SeekBar zmien;
	TextView tv;
	EditText et;
	static String klucz;
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
	
	zmien = (SeekBar)findViewById(R.id.seekBar1);
	zmien.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
		 public void onStopTrackingTouch(SeekBar arg0) {
             // TODO Auto-generated method stub

         }

         public void onStartTrackingTouch(SeekBar arg0) {
             // TODO Auto-generated method stub

         }

         public void onProgressChanged(SeekBar arg0, int progress, boolean arg2) {
        	 tv.setText(progress+"");
             if(progress >= 10 && progress < 25){
            	 tv.setText(produkt1.audi);
            	 tv.setTextColor(Color.BLUE);
         }else if(progress >= 25 && progress < 50){
        	     tv.setText(produkt1.vw);
            	 tv.setTextColor(Color.BLACK);
         }else if(progress >= 50 && progress <= 75){
        	     tv.setText(produkt1.peugeot);
            	 tv.setTextColor(Color.GREEN);
         }else if(progress >= 75 && progress <= 100){
    	     tv.setText(produkt1.fiat);
        	 tv.setTextColor(Color.GREEN);
         }else 
                 tv.setTextColor(Color.RED);
         }
     });
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
