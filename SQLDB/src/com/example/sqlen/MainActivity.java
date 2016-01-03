package com.example.sqlen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends Activity  {
	
    ObslugaDB dbObsluga = new ObslugaDB(this);
    Button add, view, update, delete,accept;
    EditText placeid, place, country,pin,password1;
    TextView infotext,wiadomosc,textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);

        
        
		
        password1 = (EditText)findViewById(R.id.EditText_Pwd1);
       // placeid = (EditText) findViewById(R.id.edplaceid);
        accept = (Button)findViewById(R.id.button1);
		textView1 = (TextView)findViewById(R.id.textView1);
		
        placeid = (EditText) findViewById(R.id.edplaceid);
        place = (EditText) findViewById(R.id.edplace);
        country = (EditText) findViewById(R.id.edcountry);
        add = (Button) findViewById(R.id.btnadd);
        update = (Button) findViewById(R.id.btnupdate);
        delete = (Button) findViewById(R.id.btndelete);
        view = (Button) findViewById(R.id.btnview);
        infotext = (TextView) findViewById(R.id.txtresulttext);

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
                                "Pomyœlnie Zaaktualizowany", Toast.LENGTH_SHORT)
                                .show();
                        infotext.setText("Pomyœlnie Zaaktualizowany");
                    }
                } catch (Exception ex) {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });
    }
        
     /*   delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (placeid.getText().toString().trim().equals("")) {
                        infotext.setText("Wpisz ID do usuniêcia...");
                    } else {
                    	dbObsluga = new ObslugaDB(getApplicationContext());
                        SQLiteDatabase db = dbObsluga.getWritableDatabase();
                        db.delete("miejsca", "id=" + placeid.getText().toString(), null);
                        Toast.makeText(MainActivity.this,
                                "usuniêty poprawnie!", Toast.LENGTH_SHORT)
                                .show();
                        infotext.setText("Usuniêty poprawnie!");
                    }
                } catch (Exception ex) {
                    infotext.setText(ex.getMessage().toString());
                }
            }
        });
    }*/
        @SuppressLint("NewApi")
		public void wybor (View arg0){
    		final AlertDialog.Builder budowa = new AlertDialog.Builder(MainActivity.this);
    		
 
    		TextView wiadomosc = new TextView(this);
    		wiadomosc.setText("Czy na pewno chcesz usun¹æ?");
    		wiadomosc.setGravity(Gravity.CENTER_HORIZONTAL);
    		budowa.setIcon(R.drawable.delete_16x16);
    		budowa.setTitle("Czy na pewno chcesz usun¹æ?");
    		//budowa.setMessage("Czy na pewno chcesz usun¹æ?");
    		budowa.setCancelable(false);
    		budowa.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
    			
    			public void onClick(DialogInterface dialog, int which) {	
    				setContentView(R.layout.user_name);
    			    accept = (Button)findViewById(R.id.button1);
    			    password1 = (EditText)findViewById(R.id.EditText_Pwd1);
    			    textView1 = (TextView)findViewById(R.id.textView1);
    			    
    			    accept.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
							try {			  
		    				      if (password1.getText().toString().trim().equals("chuj")) {
		    				    	  dbObsluga = new ObslugaDB(getApplicationContext());
		    			                SQLiteDatabase db = dbObsluga.getWritableDatabase();
		    			                db.delete("miejsca", "id=" + placeid.getText().toString(), null);
		    			                Toast.makeText(MainActivity.this,
		    			                        "Poprawnie Usuniêty", Toast.LENGTH_SHORT)
		    			                        .show();
		    			                textView1.setText("Poprawnie Usuniêty!");	
		    			                Intent i = new Intent(MainActivity.this, MainActivity.class);
		    			                startActivity(i);
		    		          } else {
		    		        		textView1.setText("B£ÊDNE HAS£O!");

		    		                }
		    		      } catch (Exception ex) {
		    		    	  textView1.setText(ex.getMessage().toString());
		    		      }
		    				
		    			}

					});
	
    		
    				/*try {
    					  budowa.setView(R.layout.user_name);
    					  String strPassword1 = password1.getText().toString();
    				      String strPassword2 = password2.getText().toString();
    				      if (strPassword1.equals("chuj")) {
    				    	  dbObsluga = new ObslugaDB(getApplicationContext());
                              SQLiteDatabase db = dbObsluga.getWritableDatabase();
                              db.delete("miejsca", "id=" + placeid.getText().toString(), null);
                              Toast.makeText(MainActivity.this,
                                      "Poprawnie Usuniêty", Toast.LENGTH_SHORT)
                                      .show();
                              infotext.setText("Poprawnie Usuniêty!");

                        } else {
                        	infotext.setText("B£¥D!");
                        }
                    } catch (Exception ex) {
                        infotext.setText(ex.getMessage().toString());
                    }*/
    		};
    		});
    		

    		budowa.setNegativeButton("Nie",new DialogInterface.OnClickListener() {
    			
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
