package com.example.sqlen;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PIN extends Activity {

	ObslugaDB dbObsluga = new ObslugaDB(this);
	EditText pin, placeid;
	Button accept;
	TextView textView1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_name);
		
	
		pin = (EditText)findViewById(R.id.username);
		accept = (Button)findViewById(R.id.haslo);	
		textView1 = (TextView)findViewById(R.id.textView1);
		
      /*  accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (pin.getText().toString().trim().equals("")) {
                    	textView1.setText("Please insert place ID to delete..");
                    } else {
                    	dbObsluga = new ObslugaDB(getApplicationContext());
                        SQLiteDatabase db = dbObsluga.getWritableDatabase();
                        db.delete("miejsca", "id=" + placeid.getText().toString(), null);
                        Toast.makeText(PIN.this,
                                "deleted successfully", Toast.LENGTH_SHORT)
                                .show();
                        textView1.setText("Deleted Successfully");
                    }
                } catch (Exception ex) {
                	textView1.setText(ex.getMessage().toString());
                }
            }
        });*/
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pin, menu);
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
