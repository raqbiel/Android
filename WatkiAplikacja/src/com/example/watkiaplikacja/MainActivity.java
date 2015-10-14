package com.example.watkiaplikacja;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;


public class MainActivity extends Activity {

	
	Button b;
	ProgressBar pb;
	
private class OsobnyWatek extends AsyncTask<Void, Void, Void>{
		
		protected void onPreExecute(){
			Log.d("Czeœæ", "jestem w¹tkiem");
			pb.setVisibility(ProgressBar.VISIBLE);
			
		}
		
		protected Void doInBackground(Void... params){
			Log.d("wiszê...", "Sory taki mamy klimat...");
			try{
				Thread.sleep(10000);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			return null;
		}
		protected void onPostExecute(Void result){
			pb.setVisibility(ProgressBar.INVISIBLE);
			Log.d("Do", "zobaczenia");
		}
		
	}
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		b = (Button)findViewById(R.id.button1);
		pb = (ProgressBar)findViewById(R.id.progressBar1);
		
		pb.setVisibility(ProgressBar.INVISIBLE);
		OnClickListener l = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new OsobnyWatek().execute();
				
			}
		};
		b.setOnClickListener(l);
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
