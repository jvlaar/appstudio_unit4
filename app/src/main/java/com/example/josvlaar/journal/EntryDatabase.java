package com.example.josvlaar.journal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE entries( _id INTEGER PRIMARY KEY, title VARCHAR(100), content VARCHAR(500), mood FLOAT, time DATETIME DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(query);

        query = "INSERT INTO entries (title, content, mood) VALUES ('Lief dagboek...', 'Groetjes', 2.0);";
        db.execSQL(query);
        db.execSQL(query);
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE entries;";
        db.execSQL(query);
        onCreate(db);
    }
}
