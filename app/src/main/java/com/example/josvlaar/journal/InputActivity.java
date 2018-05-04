package com.example.josvlaar.journal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
            return;
        }
    }
}
