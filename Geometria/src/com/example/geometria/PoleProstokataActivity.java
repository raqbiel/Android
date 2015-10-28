package com.example.geometria;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PoleProstokataActivity extends Activity {

	
	Button guzik;
	EditText bok;
	EditText bok2;
	TextView wynik;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pole_prostokata);
		
	}
	
	public void LiczPole(View v){
		
		guzik = (Button)findViewById(R.id.button1);
		bok = (EditText)findViewById(R.id.editText1);
		bok2 = (EditText)findViewById(R.id.editText2);
		wynik = (TextView)findViewById(R.id.textView4);
		
		float num1 = Float.parseFloat(bok.getText().toString());
		float num2 = Float.parseFloat(bok2.getText().toString());
		float eq = 0;
		
		switch(v.getId()){
		case R.id.button1:
			eq = num1 * num2;
			break;
			
		}
		wynik.setText(String.format("%f", eq));
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pole_prostokata, menu);
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
