package com.example.tworzeniepliku;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	
	private EditText et;
	private TextView tv;
	
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
	
	public void zapiszPlik(View view) throws IOException{
		et = (EditText) findViewById(R.id.editText1);
		//przypisanie textu
		String zawartosc = et.getText().toString();
		
		//zapisz do pliku textowego MODE PRIVATE - dostep tylko dla mojego urzadzenia
		FileOutputStream fos = openFileOutput("nowyplik.txt", MODE_PRIVATE);
		fos.write(zawartosc.getBytes());
		fos.close();

}
	
	public void otworzPlik(View view) throws IOException{
		tv = (TextView) findViewById(R.id.textView1);
		//otwarcie pliku
		FileInputStream fis = openFileInput("nowyplik.txt");
		BufferedInputStream bis = new BufferedInputStream(fis);
		//sb przechowuje wszystkie znaki
		StringBuffer sb = new StringBuffer();
		//odczytywanie zawartosci pliku (sprawdzamy czy sa jakies znaki) !=0 wartosc inna niz 0
		while(bis.available() !=0){
			char c = (char) bis.read();
			//dokladanie obiektu
			sb.append(c);
		}
		tv.setText(sb);
		//zamkniecie strumieni
		bis.close();
		fis.close();
	}
}