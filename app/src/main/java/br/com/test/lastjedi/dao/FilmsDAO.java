package br.com.test.lastjedi.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import br.com.test.lastjedi.database.DataBaseCore;
import br.com.test.lastjedi.model.Films;
import br.com.test.lastjedi.model.People;

/**
 * Created by Samurai on 08/10/2017.
 */

public class FilmsDAO {

    public DataBaseCore helper;
    public FilmsDAO(Context context){
        helper = new DataBaseCore(context);
    }
    public void insert(Films films){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = getContentValues(films);
        db.insert("films", null, values);
    }

    public void update (Films films){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = getContentValues(films);
        String[] args = {String.valueOf(films.getId())};
        db.update("films", values, "WHERE id = ?",args);
    }

    public void delete (Films films){
        SQLiteDatabase db = helper.getWritableDatabase();
        String[] args = {String.valueOf(films.getId())};
        db.delete("films", "WHERE id = ?", args);
    }

    private ContentValues getContentValues(Films films) {
        ContentValues values = new ContentValues();
        values.put("title", films.getTitle());
        values.put("episodeId", films.getEpisodeId());
        values.put("openingCrawl", films.getOpeningCrawl());
        values.put("releaseDate", films.getReleaseDate());
        values.put("url", films.getUrl());
        values.put("imageUrl", films.getImageUrl());
        return values;
    }

    public Films getFilmsByUrl(String url) {
        SQLiteDatabase db = helper.getReadableDatabase();
        Films films = null;
        String sql = "SELECT * FROM films WHERE url = ?;";
        String[] args = {url};
        Cursor cursor = db.rawQuery(sql, args);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()) {
            films = getFilmsFromDB(cursor);
        }
        cursor.close();
        return films;
    }

    public List<Films> getList(){
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "SELECT * FROM films;";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        List<Films> filmsList = new ArrayList<>();
        while(!cursor.isAfterLast()){
            filmsList.add(getFilmsFromDB(cursor));
            cursor.moveToNext();
        }
        cursor.close();
        return filmsList;
    }

    public Films getFilmsFromDB(Cursor cursor){
        Films films = new Films();
        films.setId(cursor.getInt(cursor.getColumnIndex("id")));
        films.setTitle(cursor.getString(cursor.getColumnIndex("title")));
        films.setEpisodeId(cursor.getInt(cursor.getColumnIndex("episodeId")));
        films.setOpeningCrawl(cursor.getString(cursor.getColumnIndex("openingCrawl")));
        films.setReleaseDate(cursor.getString(cursor.getColumnIndex("releaseDate")));
        films.setUrl(cursor.getString(cursor.getColumnIndex("url")));
        films.setImageUrl(cursor.getString(cursor.getColumnIndex("imageUrl")));
        return films;
    }

    public boolean checkIfExistsByUrl(String filmUrl) {
        Films checkFilms = getFilmsByUrl(filmUrl);
        return checkFilms != null;
    }
}
