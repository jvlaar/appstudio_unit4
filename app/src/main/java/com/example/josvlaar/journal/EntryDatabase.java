package com.example.josvlaar.journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class EntryDatabase extends SQLiteOpenHelper {

    private static EntryDatabase instance;

    private EntryDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public static EntryDatabase getInstance(Context context) {
        if (instance != null) {
            return instance;
        } else {
            return new EntryDatabase(context, "database", null, 1);
        }
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

    public Cursor selectAll() {
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM entries;";
        return db.rawQuery(query, null);
    }

    public void insert(JournalEntry entry) {
        ContentValues cv = new ContentValues();
        cv.put("title", entry.getTitle());
        cv.put("content", entry.getContent());
        cv.put("mood", entry.getMood());

        SQLiteDatabase db = getWritableDatabase();
        db.insert("entries", null, cv);
    }

    public void delete(long id) {
        Log.d("DELETE", "deleting id: " + id);
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM entries WHERE _id=?";
        db.delete("entries", "_id=?", new String[] {Long.toString(id)});
        Log.d("DELETE", "deleted id: " + id);
    }
}
