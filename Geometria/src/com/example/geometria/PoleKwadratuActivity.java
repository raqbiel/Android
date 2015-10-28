package com.example.geometria;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PoleKwadratuActivity extends Activity {

	Button guzik;
	EditText bok;
	TextView wynik;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pole_kwadratu);
		
		guzik = (Button) findViewById(R.id.button1);
		bok  = (EditText) findViewById(R.id.editText1);
		wynik = (TextView) findViewById(R.id.textView4);
		
		OnClickListener l = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
					wynik.setText((Double.parseDouble(bok.getText().toString())	
						)*(	
						Double.parseDouble(bok.getText().toString()))+"");
			}
		};
		guzik.setOnClickListener(l);
	}

	
}
