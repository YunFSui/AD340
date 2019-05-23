package com.example.hw5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemSelectedListener{

    final static String TAG = "**FromMain: ";

    String isSugar;
    double price;
    double volume;


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
        spinner.setOnItemSelectedListener(this);

        Button btnSubmit = (Button) findViewById(R.id.submit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){

                TextView priceResult = (TextView) findViewById(R.id.priceResult);

                Context context = getApplicationContext();
                Toast typeErrorToast = Toast.makeText(context,
                        "Input must be numeric", Toast.LENGTH_SHORT);

                EditText pricebox = (EditText) findViewById(R.id.price_box);
                String priceString = pricebox.getText().toString();
                try {
                    price = Double.parseDouble(priceString);
                } catch (NumberFormatException err){
                    price = 0;
                    typeErrorToast.show();
                }

                EditText volumebox = (EditText) findViewById(R.id.vol_box);
                String volumeString = volumebox.getText().toString();
                try {
                    volume = Double.parseDouble(volumeString);
                } catch (NumberFormatException err){
                    volume = 0;
                    typeErrorToast.show();
                }

                Soda newSoda = new Soda(price, volume, isSugar);
                double taxPrice = newSoda.getTaxPrice();
                priceResult.setText("Soda Price After Tax: " + taxPrice);

                Log.i(TAG, "Price: " + price + " Volume: " + volume);
                Log.i(TAG, "Spinner selected with value: " + isSugar);
            }

        });
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {
        isSugar = (String) parent.getItemAtPosition(position).toString();
        Log.i(TAG, "Spinner selected with value: " + isSugar);
    }

    public void onNothingSelected(AdapterView<?> parent) {
        Log.i(TAG, "Nothing selected in spinner ...");
    }
}
