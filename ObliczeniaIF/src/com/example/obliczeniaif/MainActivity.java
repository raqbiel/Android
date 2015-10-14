package com.example.obliczeniaif;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean licz(View view){
		RadioButton dod = (RadioButton)findViewById(R.id.radio0);
		RadioButton odej = (RadioButton)findViewById(R.id.radio1);
		RadioButton mno = (RadioButton)findViewById(R.id.radio2);
		TextView tv = (TextView)findViewById(R.id.textView1);
		EditText licz1 = (EditText)findViewById(R.id.editText1);
		EditText licz2 = (EditText)findViewById(R.id.editText2);
		float eq = 0;
		
		float num1 = Float.parseFloat(licz1.getText().toString());
		float num2 = Float.parseFloat(licz2.getText().toString());
		
		if(dod.isChecked()){
			eq = num1 + num2;
		}
		if(odej.isChecked()){
			eq = num1 - num2;	
			}
		if(mno.isChecked()){
			eq = num1 * num2;
		}
		tv.setText(String.format("%f", eq));
		
		return false;
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
