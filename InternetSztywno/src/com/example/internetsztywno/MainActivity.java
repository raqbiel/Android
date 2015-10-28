package com.example.internetsztywno;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	Button b;
	Button b2;
	EditText e;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b = (Button)findViewById(R.id.button1);
		//b2 = (Button)findViewById(R.id.button2);
		OnClickListener l = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				e = (EditText)findViewById(R.id.editText1);
				
				Uri u = Uri.parse("http://"+e.getText());
				Intent i = new Intent(Intent.ACTION_VIEW,u);
				startActivity(i);
				
				
			}
		};
		b.setOnClickListener(l);	
		/*OnClickListener p = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				e = (EditText)findViewById(R.id.editText1);
				
				Uri u = Uri.parse("http://"+e.getText());
				Intent i = new Intent(Intent.ACTION_VIEW,u);
				startActivity(i);
				
				
			}
		};
		b2.setOnClickListener(p);*/	
		}

	
		public void zakoncz(View arg0){
		Button b3 = (Button)findViewById(R.id.button2);
		OnClickListener kon = new OnClickListener() {
			
			@Override
			public void onClick(View v) {

				
		
		AlertDialog.Builder zakoncz = new AlertDialog.Builder(MainActivity.this);
		zakoncz.setMessage("Czy na pewno chcesz zakoñczyæ?");
		zakoncz.setPositiveButton("Tak", new view.OnClickListener() {
		
		public void onClick(DialogInterface dialog, int which) {
			MainActivity.this.finish();
			
		}
	});
		zakoncz.setNegativeButton("Nie", new view.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		AlertDialog alert = zakoncz.create();
		alert.show();
			}
		};
		b3.setOnClickListener(kon);
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
