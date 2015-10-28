package com.example.kalkulator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void wywolajfunkcje(View view){
		EditText a = (EditText)findViewById(R.id.editText1);
		EditText b = (EditText)findViewById(R.id.editText2);
		TextView wynik = (TextView)findViewById(R.id.textView1);
		
		float num1 = Float.parseFloat(a.getText().toString());
		float num2 = Float.parseFloat(b.getText().toString());
		float eq = 0;
		
		switch(view.getId()){
			case R.id.button1:
				eq = num1 + num2;
				break;
			case R.id.button2:
				eq = num1 - num2;
				break;
			case R.id.button3:
				eq = num1 * num2;
				break;
		}
		
		wynik.setText(String.format("%f", eq));
	}
	
	public void wybor(View arg0){
		AlertDialog.Builder budowa = new AlertDialog.Builder(MainActivity.this);
		budowa.setMessage("Czy na pewno chcesz zakoñczyæ?");
		budowa.setCancelable(false);
		budowa.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.finish();
				
				
			}
		});
		budowa.setNegativeButton("Nie",new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		AlertDialog alert = budowa.create();
		alert.show();
				
		};
		
	
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
