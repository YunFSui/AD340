package com.example.hw3;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    final static  String TAG = "From MainActivity - ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected void toSecond(View button) {

        Intent intentToSecond = new Intent(this, SecondActivity.class);
        Log.i(TAG, "Go to 2nd Activity");
        startActivity(intentToSecond);

    }

    protected void getToast(View button) {
        Context context = getApplicationContext();
        CharSequence text = "Toasted!";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
