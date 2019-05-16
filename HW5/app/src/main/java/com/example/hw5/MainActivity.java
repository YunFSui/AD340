package com.example.hw5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemSelectedListener{

    final static String TAG = "FromMain: ";

    String selectedItem;
    double price;
    int volume;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Spinner Setup
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.isSugar,
                android.R.layout.simple_spinner_item
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinner.setAdapter(adapter);

        Button btnSubmit = (Button) findViewById(R.id.submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){

                EditText pricebox = (EditText) findViewById(R.id.price_box);
                String priceString = pricebox.getText().toString();
                price = Double.parseDouble(priceString);

                EditText volumebox = (EditText) findViewById(R.id.vol_box);
                String volumeString = volumebox.getText().toString();
                volume = Integer.parseInt(volumeString);

                Log.i(TAG, "Price: " + price + " Volume: " + volume);
                Log.i(TAG, "Spinner selected with value: " + selectedItem);
            }

        });
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
        selectedItem = (String) parent.getItemAtPosition(position).toString();
        Log.i(TAG, "Spinner selected with value: " + selectedItem);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        Log.i(TAG, "Nothing selected in spinner ...");
    }
}
