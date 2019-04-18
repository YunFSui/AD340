package com.example.hw2;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String MESSAGE_ID = "my.message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    protected void onClick(View button) {

        EditText textbox = (EditText) findViewById(R.id.message_box);
        String message = textbox.getText().toString();

        Intent intentToSecond = new Intent(this, SecondActivity.class);
        intentToSecond.putExtra(MESSAGE_ID, message);
        startActivity(intentToSecond);
    }


}
