package com.example.josvlaar.journal;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private EntryDatabase db;
    private EntryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View floatingButton = findViewById(R.id.floatingActionButton);
        floatingButton.setOnClickListener(new onFloatingActionButtonClick());

        ListView listView = findViewById(R.id.listView);
        listView.setOnItemClickListener(new onListItemClick());
        listView.setOnItemLongClickListener(new onListItemClick());

        db = EntryDatabase.getInstance(getApplicationContext());
        Cursor cursor = db.selectAll();
        adapter = new EntryAdapter(this, cursor);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateData();
    }

    private void updateData() {
        Cursor cursor = db.selectAll();
        adapter.swapCursor(cursor);
    }

    private class onFloatingActionButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intent);
        }
    }

    private class onListItemClick implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            JournalEntry entry = new JournalEntry();
            entry.setTitle(cursor.getString(cursor.getColumnIndex("title")));
            entry.setContent(cursor.getString(cursor.getColumnIndex("content")));
            entry.setTimestamp(cursor.getString(cursor.getColumnIndex("time")));
            entry.setMood(cursor.getFloat(cursor.getColumnIndex("mood")));

            Intent intent = new Intent(MainActivity.this, DetailActivity.class);
            intent.putExtra("entry", entry);
            startActivity(intent);
        }

        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            int dbId = cursor.getInt(cursor.getColumnIndex("_id"));
            Log.d("DELETE", "Going to delete id: " + dbId);
            db.delete(dbId);
            updateData();
            return true;
        }
    }
}
