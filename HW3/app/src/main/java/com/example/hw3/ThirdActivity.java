package com.example.hw3;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class ThirdActivity extends AppCompatActivity{

    final static String TAG = "3rd Child says";

    private LegoKit[] kits;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_third);

        Intent intent = getIntent();
        String pos = intent.getStringExtra(SecondActivity.MESSAGE_ID);
        int position = Integer.parseInt(pos);

        this.kits = LegoKit.initKits();

        Log.i(TAG, "position " + position);
        Log.i(TAG, kits[position].getTitle());

        String tit = kits[position].getTitle();

        TextView title = (TextView)findViewById(R.id.title);
        title.setText(tit);

        TextView year = (TextView)findViewById(R.id.year);
        year.setText("Year: " + kits[position].getYear());

        TextView director = (TextView)findViewById(R.id.director);
        director.setText("Director: " + kits[position].getDirector());

        TextView description = (TextView)findViewById(R.id.description);
        description.setText(kits[position].getDescription());

        ImageView image = (ImageView)findViewById(R.id.image);
        String url = kits[position].getUri();

        Log.i(TAG, url);

        Picasso.get().load(url).into(image);





    }


}
