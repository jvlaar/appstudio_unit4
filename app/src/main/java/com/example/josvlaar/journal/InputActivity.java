package com.example.josvlaar.journal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class InputActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Button submitButton = findViewById(R.id.submit);
        submitButton.setOnClickListener(new onSubmitButtonClick());
    }

    private class onSubmitButtonClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            EntryDatabase db = EntryDatabase.getInstance(getApplicationContext());

            TextView title = findViewById(R.id.inputTitle);
            TextView content = findViewById(R.id.inputContent);
            RatingBar rating = findViewById(R.id.inputRating);

            Log.d("DEBUG", "Title " + title);
            Log.d("DEBUG", "Content " + content);
            Log.d("DEBUG", "Ratingbar " + rating);


            JournalEntry entry = new JournalEntry();
            entry.setTitle(title.getText() + "");
            entry.setContent(content.getText() + "");
            entry.setMood(rating.getRating());

            Log.d("DEBUG", "Inserting new entry to db!");
            db.insert(entry);

            finish();
        }
    }
}
