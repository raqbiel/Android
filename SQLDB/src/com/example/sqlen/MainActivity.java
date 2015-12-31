package com.example.sqlen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity  {
    ObslugaDB dbObsluga = new ObslugaDB(this);

    
   
    Button add, view, update, delete;
    EditText placeid, place, country,pin;
    TextView infotext,wiadomosc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        placeid = (EditText) findViewById(R.id.edplaceid);
        place = (EditText) findViewById(R.id.edplace);
        country = (EditText) findViewById(R.id.edcountry);
        add = (Button) findViewById(R.id.btnadd);
        update = (Button) findViewById(R.id.btnupdate);
        delete = (Button) findViewById(R.id.btndelete);
        view = (Button) findViewById(R.id.btnview);
        infotext = (TextView) findViewById(R.id.txtresulttext);
		pin = (EditText)findViewById(R.id.username);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PlaceList.class);
                startActivity(i);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (place.getText().toString().trim().equals("") || country.getText().toString().trim().equals("")) {
                        infotext.setText("Wpisz miasto oraz kraj...");
                    } else {
                    	dbObsluga = new ObslugaDB(getApplicationContext());
                        SQLiteDatabase db = dbObsluga.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("miejsce", place.getText().toString());
                        cv.put("kraj", country.getText().toString());
                        db.insert("miejsca", null, cv);
                        db.close();
                        infotext.setText("Miejsce dodane poprawnie!");
                    }
                } catch (Exception ex) {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if ((place.getText().toString().trim().equals("") && country.getText().toString().trim().equals("")) || placeid.getText().toString().trim().equals("")) {
                        infotext.setText("Wpisz dane do zaaktualizowania rekordu...");
                    } else {
                    	dbObsluga = new ObslugaDB(getApplicationContext());
                        SQLiteDatabase db = dbObsluga.getWritableDatabase();
                        ContentValues cv = new ContentValues();
                        cv.put("miejsce", place.getText().toString());
                        cv.put("kraj", country.getText().toString());
                        db.update("miejsca", cv, "id=" + placeid.getText().toString(), null);
                        Toast.makeText(MainActivity.this,
                                "Pomy渓nie Zaaktualizowany", Toast.LENGTH_SHORT)
                                .show();
                        infotext.setText("Pomy渓nie Zaaktualizowany");
                    }
                } catch (Exception ex) {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });
    
       /* delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (placeid.getText().toString().trim().equals("")) {
                        infotext.setText("Please insert place ID to delete..");
                    } else {
                    	dbObsluga = new ObslugaDB(getApplicationContext());
                        SQLiteDatabase db = dbObsluga.getWritableDatabase();
                        db.delete("miejsca", "id=" + placeid.getText().toString(), null);
                        Toast.makeText(MainActivity.this,
                                "deleted successfully", Toast.LENGTH_SHORT)
                                .show();
                        infotext.setText("Deleted Successfully");
                    }
                } catch (Exception ex) {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });*/
    }
        @SuppressLint("NewApi")
		public void wybor (View arg0){
    		AlertDialog.Builder budowa = new AlertDialog.Builder(MainActivity.this);
    		
    		
    		TextView wiadomosc = new TextView(this);
    		wiadomosc.setText("Czy na pewno chcesz usun规?");
    		wiadomosc.setGravity(Gravity.CENTER_HORIZONTAL);
    		budowa.setIcon(R.drawable.delete_16x16);
    		budowa.setTitle("Czy na pewno chcesz usun规?");
    		//budowa.setMessage("Czy na pewno chcesz usun规?");
    		budowa.setCancelable(false);
    		budowa.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
    			
    			@Override
    			public void onClick(DialogInterface dialog, int which) {
    				try {
                        if (placeid.getText().toString().trim().equals("")) {
                            infotext.setText("Wpisz ID, aby usun规..");
                        } else {
                        	dbObsluga = new ObslugaDB(getApplicationContext());
                            SQLiteDatabase db = dbObsluga.getWritableDatabase();
                            db.delete("miejsca", "id=" + placeid.getText().toString(), null);
                            Toast.makeText(MainActivity.this,
                                    "Poprawnie Usuni阾y", Toast.LENGTH_SHORT)
                                    .show();
                            infotext.setText("Poprawnie Usuni阾y!");
                        }
                    } catch (Exception ex) {
                        infotext.setText(ex.getMessage().toString());
                    }
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}