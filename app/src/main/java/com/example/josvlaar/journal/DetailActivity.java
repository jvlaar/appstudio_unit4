package com.example.josvlaar.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        JournalEntry entry = (JournalEntry) intent.getSerializableExtra("entry");

        TextView title = findViewById(R.id.detailTitle);
        TextView timestamp = findViewById(R.id.timestamp);
        RatingBar rating = findViewById(R.id.rating);
        TextView content = findViewById(R.id.detailContent);

        title.setText(entry.getTitle());
        timestamp.setText(entry.getTimestamp());
        rating.setRating(entry.getMood());
        content.setText(entry.getContent());
        
    }
}
