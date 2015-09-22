package com.example.loginmenu;


/***********************
 *****Pawe³ Kryspin*****
 ***********************/
import java.util.ArrayList;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.app.ProgressDialog;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ProsbaSerwera {

		ProgressDialog progressDialog;
		
		public static final int CONNECTION_TIME  = 1000 * 15;
		public static final String SERVER_ADDRESS = "http://androidtestowanie.webatu.com";
	
		public ProsbaSerwera(Context context){
			progressDialog = new ProgressDialog(context);
			progressDialog.setCancelable(false);
			progressDialog.setTitle("Pracuje..");
			progressDialog.setMessage("Proszê czekaæ...");
		}
		
		public void DaneUzytkownikaWTle(Uzytkownik uzytkownik, OdpowiedzUzytkownika odpowiedzuzytkownika){
			progressDialog.show();
			new DaneUzytkownikaAsyncTask(uzytkownik, odpowiedzuzytkownika).execute();
			
			
		}
		
		public void PobierzDaneUzytkownikaWTle(Uzytkownik uzytkownik, OdpowiedzUzytkownika odpowiedzuzytkownika ){
			progressDialog.show();
			new PobierzDaneUzytkownikaAsyncTask(uzytkownik, odpowiedzuzytkownika).execute();
			
		}
		
		public class DaneUzytkownikaAsyncTask extends AsyncTask<Void, Void, Void>{

			Uzytkownik uzytkownik;
			OdpowiedzUzytkownika odpowiedzuzytkownika;
			
			public DaneUzytkownikaAsyncTask(Uzytkownik uzytkownik, OdpowiedzUzytkownika odpowiedzuzytkownika){
				this.uzytkownik = uzytkownik;
				this.odpowiedzuzytkownika = odpowiedzuzytkownika;
			}
			
			@Override
			protected Void doInBackground(Void... params) {
				ArrayList<NameValuePair> daneDoWyslania = new ArrayList<NameValuePair>();
				daneDoWyslania.add(new BasicNameValuePair("nazwa", uzytkownik.nazwa));
				daneDoWyslania.add(new BasicNameValuePair("login", uzytkownik.login));
				daneDoWyslania.add(new BasicNameValuePair("haslo", uzytkownik.haslo));
				daneDoWyslania.add(new BasicNameValuePair("email", uzytkownik.email));
				
				HttpParams httpRequestParams = new BasicHttpParams();

		            HttpClient client = new DefaultHttpClient(httpRequestParams);
		            HttpPost post = new HttpPost(SERVER_ADDRESS
		                    + "public_html/Register.php");

				
				try{
					post.setEntity(new UrlEncodedFormEntity(daneDoWyslania));
					client.execute(post);
				}catch (Exception e){
					e.printStackTrace();
				}
				
				return null;
			}
			private HttpParams getHttpRequestParams() {
		            HttpParams httpRequestParams = new BasicHttpParams();
		            HttpConnectionParams.setConnectionTimeout(httpRequestParams,
		                    CONNECTION_TIME);
		            HttpConnectionParams.setSoTimeout(httpRequestParams,
		                    CONNECTION_TIME);
		            return httpRequestParams;
		        }
			@Override
			protected void onPostExecute(Void aVoid) {
				progressDialog.dismiss();
				odpowiedzuzytkownika.done(null);
				super.onPostExecute(aVoid);
			}
		}
		public class PobierzDaneUzytkownikaAsyncTask extends AsyncTask<Void, Void, Uzytkownik>{

			Uzytkownik uzytkownik;
			OdpowiedzUzytkownika odpowiedzuzytkownika;
			
			public PobierzDaneUzytkownikaAsyncTask(Uzytkownik uzytkownik, OdpowiedzUzytkownika odpowiedzuzytkownika){
				this.uzytkownik = uzytkownik;
				this.odpowiedzuzytkownika = odpowiedzuzytkownika;
			}
		protected Uzytkownik doInBackground(Void... params) {
			ArrayList<NameValuePair> daneDoWyslania = new ArrayList<NameValuePair>();
			daneDoWyslania.add(new BasicNameValuePair("login", uzytkownik.login));
			daneDoWyslania.add(new BasicNameValuePair("haslo", uzytkownik.haslo));
	
			HttpParams httpRequestParams = new BasicHttpParams();
			HttpClient client = new DefaultHttpClient(httpRequestParams);
			HttpConnectionParams.setConnectionTimeout(httpRequestParams,
                    CONNECTION_TIME);
            HttpConnectionParams.setSoTimeout(httpRequestParams,
                    CONNECTION_TIME);
			HttpPost post = new HttpPost(SERVER_ADDRESS + "PobierzDaneUzytkownika.php");
			
			Uzytkownik uzytkownik = null;
			
			try{
				post.setEntity(new UrlEncodedFormEntity(daneDoWyslania));
				HttpResponse httpResponse =  client.execute(post);
				
				HttpEntity entity = httpResponse.getEntity();
				String rezultat = EntityUtils.toString(entity);
				JSONObject jObject = new JSONObject(rezultat);
				
				if(jObject.length() == 0){
					uzytkownik = null;
				}else{
					String nazwa = jObject.getString("nazwa");
					
					uzytkownik = new Uzytkownik(nazwa,uzytkownik.login, uzytkownik.haslo, uzytkownik.email);
				}
				
			}catch (Exception e){
				e.printStackTrace();
			}
			
			return uzytkownik;
		}
		@Override
		protected void onPostExecute(Uzytkownik powrotUzytkownika) {
			progressDialog.dismiss();
			odpowiedzuzytkownika.done(powrotUzytkownika);
			super.onPostExecute(powrotUzytkownika);
		}
	}
}


