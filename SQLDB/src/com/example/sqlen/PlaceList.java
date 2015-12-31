package com.example.sqlen;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.HashMap;
import java.util.List;
public class PlaceList extends Activity {
	ObslugaDB dbObsluga = new ObslugaDB(this);
    ListView ls;
    TextView infotext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.placeslist);
        ls = (ListView) findViewById(R.id.placeslist);
        infotext = (TextView) findViewById(R.id.txtresulttext);
        try {
            List<HashMap<String, String>> data = dbObsluga.getAllPlace();
            if (data.size() != 0) {
                // Srno, RMCode, Fileno, Loc, FileDesc, TAGNos
                SimpleAdapter adapter = new SimpleAdapter(
                        PlaceList.this, data, R.layout.rows,
                        new String[]{"id", "miejsce", "kraj"}, new int[]{
                        R.id.txtplaceid, R.id.txtplacename,
                        R.id.txtcountry});
                ls.setAdapter(adapter);
                String length = String.valueOf(data.size());
                infotext.setText(length + " miejsca");
            } else {
                infotext.setText("Brak danych w bazie danych");
            }
        } catch (Exception ex) {
            infotext.setText(ex.getMessage().toString());
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}