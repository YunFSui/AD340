package com.example.hw7;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

//device loc
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "From MapActivity :=";

    private FusedLocationProviderClient mLocationClient;
    private boolean mLocationPremissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mLocationClient = LocationServices
                .getFusedLocationProviderClient(this);
        Button button =findViewById(R.id.get_location);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        getLocationPermission();

        getLocation();
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {
        if (mLocationPremissionGranted){
            Task location = mLocationClient.getLastLocation();
            location.addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location actualLocation) {
                    if (actualLocation != null) {
                        String latLong = String.format("Lat: %f, Long: %f",
                                actualLocation.getLatitude(),
                                actualLocation.getLongitude());

                        TextView textView = findViewById(R.id.location_data);
                        textView.setText(latLong);
                    } else {
                        Log.e(TAG, "Location is null ...");
                    }
                }
            });
            location.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, e.getLocalizedMessage());
                }
            });
        }
    }

    private void getLocationPermission() {
        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            mLocationPremissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    1);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        mLocationPremissionGranted = false;
        switch (requestCode) {
            // requestCode is our ID of our request
            case 1: {
                if (grantResults.length>0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mLocationPremissionGranted = true;
                    getLocation();
                }
            }
        }
    }
}
