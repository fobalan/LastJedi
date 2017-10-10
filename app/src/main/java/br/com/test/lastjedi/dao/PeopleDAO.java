package br.com.test.lastjedi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.test.lastjedi.model.People;
import retrofit2.http.PUT;

/**
 * Created by Samurai on 08/10/2017.
 */

public class PeopleDAO extends SQLiteOpenHelper {

    public PeopleDAO(Context context) {
        super(context, "LastJedi.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE people (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "url TEXT," +
                "name TEXT," +
                "height INT," +
                "mass INT," +
                "hairColor TEXT," +
                "skinColor TEXT," +
                "eyeColor TEXT," +
                "birthYear TEXT," +
                "gender TEXT," +
                "homeworld TEXT," +
                "films TEXT," +
                "species TEXT," +
                "vehicles TEXT," +
                "starships TEXT," +
                "created TEXT," +
                "edited TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS people";
        db.execSQL(sql);
        onCreate(db);
    }

    public void insert(People people){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = getContentValues(people);
        db.insert("people", null, values);
    }

    public void update (People people){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = getContentValues(people);
        String[] args = {String.valueOf(people.getId())};
        db.update("people", values, "WHERE id = ?",args);
    }

    public void delete (People people){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {String.valueOf(people.getId())};
        db.delete("people", "WHERE id = ?", args);
    }

    private ContentValues getContentValues(People people) {
        ContentValues values = new ContentValues();
        values.put("url", people.getUrl());
        values.put("name", people.getName());
        values.put("height", people.getHeight());
        values.put("mass", people.getMass());
        values.put("hairColor", people.getHairColor());
        values.put("skinColor", people.getSkinColor());
        values.put("eyeColor", people.getEyeColor());
        values.put("birthYear", people.getBirthYear());
        values.put("gender", people.getGender());
        values.put("homeworld", people.getHomeworld());
        //values.put("films", people.getFilms());
        //values.put("species", people.getSpecies());
        //values.put("vehicles", people.getVehicles());
        //values.put("starships", people.getStarships());
        values.put("created", people.getCreated());
        values.put("edited", people.getEdited());
        return values;
    }

    public People getPeopleByUrl(String url) {
        SQLiteDatabase db = getReadableDatabase();
        People people = null;
        String sql = "SELECT * FROM people WHERE url = ?";
        String[] args = {url};
        Cursor cursor = db.rawQuery(sql, args);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            people = getPeopleFromDB(cursor);
        }
        cursor.close();
        return people;
    }

    public List<People> getList(){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT * FROM people;";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<People> peopleList = new ArrayList<>();
        while(!cursor.isAfterLast()){
            peopleList.add(getPeopleFromDB(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return peopleList;
    }

    public People getPeopleFromDB(Cursor cursor){
        People people = new People();
        people.setId(cursor.getInt(cursor.getColumnIndex("id")));
        people.setUrl(cursor.getString(cursor.getColumnIndex("url")));
        people.setName(cursor.getString(cursor.getColumnIndex("name")));
        people.setHeight(cursor.getInt(cursor.getColumnIndex("height")));
        people.setMass(cursor.getInt(cursor.getColumnIndex("mass")));
        people.setHairColor(cursor.getString(cursor.getColumnIndex("hairColor")));
        people.setSkinColor(cursor.getString(cursor.getColumnIndex("skinColor")));
        people.setEyeColor(cursor.getString(cursor.getColumnIndex("eyeColor")));
        people.setBirthYear(cursor.getString(cursor.getColumnIndex("birthYear")));
        people.setGender(cursor.getString(cursor.getColumnIndex("gender")));
        people.setHomeworld(cursor.getString(cursor.getColumnIndex("homeworld")));
        //people.setFilms(cursor.getString(cursor.getColumnIndex("films")));
        //people.setSpecies(cursor.getString(cursor.getColumnIndex("species")));
        //people.setVehicles(cursor.getString(cursor.getColumnIndex("vehicles")));
        //people.setStarships(cursor.getString(cursor.getColumnIndex("starships")));
        people.setCreated(cursor.getString(cursor.getColumnIndex("created")));
        people.setEdited(cursor.getString(cursor.getColumnIndex("edited")));
        return people;
    }

    public boolean checkIfExists(People people){
        People checkPeople = getPeopleByUrl(people.getUrl());
        return checkPeople != null;

    }

}
