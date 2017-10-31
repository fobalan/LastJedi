package br.com.test.lastjedi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.JsonWriter;
import android.view.ViewAnimationUtils;

import com.google.gson.Gson;

import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;

import br.com.test.lastjedi.database.DataBaseCore;
import br.com.test.lastjedi.model.People;
import retrofit2.http.PUT;

/**
 * Created by Samurai on 08/10/2017.
 */

public class PeopleDAO {

    public DataBaseCore helper;
    public PeopleDAO(Context context){
        helper = new DataBaseCore(context);
    }
    public void insert(People people){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = getContentValues(people);
        db.insert("people", null, values);
    }

    public void update (People people){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = getContentValues(people);
        String[] args = {String.valueOf(people.getId())};
        db.update("people", values, "id = ?",args);
    }

    public void delete (People people){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] args = {String.valueOf(people.getId())};
        db.delete("people", "id = ?", args);
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
        values.put("films", new Gson().toJson(people.getFilms()));
        values.put("species", new Gson().toJson(people.getSpecies()));
        values.put("vehicles", new Gson().toJson(people.getVehicles()));
        values.put("starships", new Gson().toJson(people.getStarships()));
        values.put("created", people.getCreated());
        values.put("edited", people.getEdited());
        values.put("latitude", people.getLatitude());
        values.put("longitude", people.getLongitude());
        return values;
    }

    public People getPeopleByUrl(String url) {
        SQLiteDatabase db = helper.getReadableDatabase();
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
        SQLiteDatabase db = helper.getReadableDatabase();
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
        people.setFilms(new Gson().fromJson(cursor.getString(cursor.getColumnIndex("films")),List.class));
        people.setSpecies(new Gson().fromJson(cursor.getString(cursor.getColumnIndex("species")),List.class));
        people.setVehicles(new Gson().fromJson(cursor.getString(cursor.getColumnIndex("vehicles")),List.class));
        people.setStarships(new Gson().fromJson(cursor.getString(cursor.getColumnIndex("starships")),List.class));
        people.setCreated(cursor.getString(cursor.getColumnIndex("created")));
        people.setEdited(cursor.getString(cursor.getColumnIndex("edited")));
        return people;
    }

    public boolean checkIfExists(People people){
        People checkPeople = getPeopleByUrl(people.getUrl());
        return checkPeople != null;

    }

}
