package com.example.sqlen;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
public class ObslugaDB extends SQLiteOpenHelper {
	private static final String nazwatabeli = "miejsca";  // Nazwa Tabeli
	private static final String miejsce = "miejsce"; // Nazwa rekordu
	private static final String id = "ID"; // nie trzeba t³umaczyc
	static final String kraj = "kraj"; // Nazwa rekordu
	private static final String nazwaDB = "miejscainfo"; // nazwa DB
	private static final int versja = 1; // versja db
	public ObslugaDB(Context context){
		super(context, nazwaDB, null, versja);
	}
	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		query = "CREATE TABLE IF NOT EXISTS " + nazwatabeli + "(" + id + " integer primary key, " + miejsce + " text, " + kraj + " text)";
        database.execSQL(query);
    }

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		String query;
		query = "DROP TABLE IF EXISTS " + nazwatabeli;
		database.execSQL(query);
		onCreate(database);
		
	}

	public ArrayList<HashMap<String, String>> getAllPlace(){
		ArrayList<HashMap<String, String>> wordList;
		wordList = new ArrayList<HashMap<String, String>>();
		String selectQuery = "SELECT * FROM " + nazwatabeli;
		SQLiteDatabase database = this.getWritableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if(cursor.moveToFirst()){
			do{
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("id", cursor.getString(0));
				map.put("miejsce", cursor.getString(1));
				map.put("kraj", cursor.getString(2));
				wordList.add(map);
			} while (cursor.moveToNext());
		}
		return wordList;
	}
}
