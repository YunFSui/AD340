package com.example.hw2;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }

    protected void onClick(View button) {

        EditText textbox = (EditText) findViewById(R.id.message_box);
        String message = textbox.getText().toString();

        Intent intent = new Intent(this, SecondActivity.class);

        startActivity(intent);
    }


}
