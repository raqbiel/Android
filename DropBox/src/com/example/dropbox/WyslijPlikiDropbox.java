package com.example.dropbox;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;
import com.dropbox.client2.DropboxAPI;
import com.dropbox.client2.exception.DropboxException;

public class WyslijPlikiDropbox extends AsyncTask<Void, Void, Boolean> {

	private DropboxAPI<?> dropbox;
	private String sciezka;
	private Context context;
	
	
	public WyslijPlikiDropbox(Context context, DropboxAPI<?> dropbox, String sciezka){
	this.context = context.getApplicationContext();	
	this.dropbox = dropbox;
	this.sciezka = sciezka;
	}
	
	
	@Override
	protected Boolean doInBackground(Void... params) {
		final File tempDir = context.getCacheDir();
		File tempPlik;
		FileWriter fr;
		try{
			tempPlik = File.createTempFile("plik", ".txt", tempDir);
			fr = new FileWriter(tempPlik);
			fr.write("Plik");
			fr.close();
			
			FileInputStream fileInputStream = new FileInputStream(tempPlik);
			dropbox.putFile(sciezka + "pliktekstowy.txt", fileInputStream, tempPlik.length(), null, null);
			tempPlik.delete();
			return true;	
		} catch(IOException e){
			e.printStackTrace();
		} catch(DropboxException e){
			e.printStackTrace();
		}
		return false;
	}


	@Override
	protected void onPostExecute(Boolean rezultat) {
		if(rezultat){
			Toast.makeText(context, "Plik zosta³ umieszczony w Dropbox", Toast.LENGTH_SHORT).show();
			
		}else{
			Toast.makeText(context, "Nie uda³o siê umieœciæ pliku w Dropbox", Toast.LENGTH_SHORT).show();
		}
		
	}
	
}
