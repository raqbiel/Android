package com.example.dropbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.android.AndroidAuthSession;
import com.dropbox.client2.session.AccessTokenPair;
import com.dropbox.client2.session.AppKeyPair;
import com.dropbox.client2.session.Session.AccessType;
import com.dropbox.client2.session.TokenPair;

public class Dropbox extends Activity implements OnClickListener {
	private DropboxAPI<AndroidAuthSession> dropbox;
	private final static String KATALOG = "/Boxik/";
	private final static String NAZWA  = "Boxik";
	private final static String KLUCZ = "l9qbna2ab1s9usl";
	private final static String KLUCZ_SEKRET = "j1dmi26sza6o0su";
	
	private boolean Zalogowany;
	private Button Zaloguj, wyslijPlik, listaPlikow;
	public LinearLayout contrainer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dropbox);
		
		Zaloguj = (Button)findViewById(R.id.dropbox_login);
		Zaloguj.setOnClickListener(this);
		wyslijPlik = (Button)findViewById(R.id.upload_file);
		wyslijPlik.setOnClickListener(this);
		listaPlikow = (Button)findViewById(R.id.list_files);
		listaPlikow.setOnClickListener(this);
		
		Logowanie(false);
		AndroidAuthSession sesja;
		AppKeyPair pair = new AppKeyPair(KLUCZ, KLUCZ_SEKRET);
		
		SharedPreferences prefs = getSharedPreferences(NAZWA, 0);
		String klucz = prefs.getString(KLUCZ, null);
		String sekret = prefs.getString(KLUCZ_SEKRET, null);
		
		if(klucz != null && sekret != null){
			AccessTokenPair token = new AccessTokenPair(klucz, sekret);
			sesja = new AndroidAuthSession(pair, AccessType.APP_FOLDER, token);
		}else{
			sesja = new AndroidAuthSession(pair, AccessType.APP_FOLDER);
		}
		dropbox = new DropboxAPI<AndroidAuthSession>(sesja);
	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
		
		AndroidAuthSession sesja = dropbox.getSession();
		if(sesja.authenticationSuccessful()){
			try{
				sesja.finishAuthentication();
				TokenPair tokeny = sesja.getAccessTokenPair();
				SharedPreferences prefs = getSharedPreferences(NAZWA, 0);
				Editor edycja = prefs.edit();
				edycja.putString(KLUCZ, tokeny.key);
				edycja.putString(KLUCZ_SEKRET, tokeny.secret);
				edycja.commit();
				Logowanie(true);
			}catch (IllegalStateException e){
				Toast.makeText(this, "B³¹d podczas logowania", Toast.LENGTH_SHORT).show();
				
			}
		}
	}


	protected void Logowanie(boolean Logowany) {
		Zalogowany = Logowany;
		wyslijPlik.setEnabled(Logowany);
		listaPlikow.setEnabled(Logowany);
		Zaloguj.setText(Logowany ? "Wyloguj" : "Zaloguj");
		
	}

	private final Handler handler = new Handler(){
		public void handleWiadomosc(Message wiad){
			ArrayList<String> rezultat = wiad.getData().getStringArrayList("data");
			for(String nazwaPliku : rezultat){
			Log.i("Lista Plików", nazwaPliku);
			TextView tv = new TextView(Dropbox.this);
			tv.setText(nazwaPliku);
			contrainer.addView(tv);
			}
		}
	};


 public void onClick(View v){
	 switch(v.getId()){
	 case R.id.dropbox_login:
		 if(Zalogowany){
			 dropbox.getSession().unlink();
			 Logowanie(false);
		 }else{
			 dropbox.getSession().startAuthentication(Dropbox.this);
		 }
		break;
		case R.id.list_files:
			ListaPlikowDropbox list = new ListaPlikowDropbox(dropbox, KATALOG,
					handler);
			list.execute();
			break;
		case R.id.upload_file:
			WyslijPlikiDropbox upload = new WyslijPlikiDropbox(this, dropbox,
					KATALOG);
			upload.execute();
			break;
		default:
			break;
		 }
	 }


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dropbox, menu);
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
