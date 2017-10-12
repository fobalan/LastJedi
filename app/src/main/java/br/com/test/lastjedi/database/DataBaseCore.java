package br.com.test.lastjedi.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ITST on 11/10/2017.
 */

public class DataBaseCore extends SQLiteOpenHelper {
    public DataBaseCore(Context context) {
        super(context, "LastJedi.sqlite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE people (" +
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
                "edited TEXT); ");

        db.execSQL("CREATE TABLE films (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "episodeId INT," +
                "openingCrawl TEXT," +
                "releaseDate TEXT," +
                "url TEXT," +
                "imageUrl TEXT," +
                "hamePage TEXT);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS people");
        db.execSQL("DROP TABLE IF EXISTS films");
        onCreate(db);
    }
}
