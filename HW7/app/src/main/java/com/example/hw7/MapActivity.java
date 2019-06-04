package com.example.hw7;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

//device loc
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final static String TAG = "From MapActivity :=";

    private FusedLocationProviderClient mLocationClient;
    private boolean mLocationPremissionGranted = false;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mLocationClient = LocationServices
                .getFusedLocationProviderClient(this);

        SupportMapFragment mapFragment =
                (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @SuppressLint("MissingPermission")
    private void getLocation() {
        if (mLocationPremissionGranted){
            Task location = mLocationClient.getLastLocation();

            location.addOnCompleteListener(new OnCompleteListener<Location>() {

                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    Location actualLocation = task.getResult();
                    if (actualLocation != null) {
                        String latLong = String.format("Lat: %f, Long: %f",
                                actualLocation.getLatitude(),
                                actualLocation.getLongitude());

                        mMap.setMyLocationEnabled(true);
                        mMap.getUiSettings().setMyLocationButtonEnabled(true);

                        //Device Location
                        LatLng d_loc = new LatLng(actualLocation.getLatitude(),
                                actualLocation.getLongitude());
                        mMap.addMarker(new MarkerOptions()
                                .position(d_loc).title("Current Location")
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                        //Camera Location
                        Intent intent = getIntent();
                        String[] camData = intent.getStringArrayExtra(MainActivity.MESSAGE_ID);
                        Double camLat = Double.parseDouble(camData[0]);
                        Double camLng = Double.parseDouble(camData[1]);
                        String camDescription = camData[2];
                        LatLng c_loc = new LatLng(camLat, camLng);
                        mMap.addMarker(new MarkerOptions().position(c_loc).title(camDescription));

                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(d_loc, 10));

                        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
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

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getLocationPermission();

        getLocation();
    }
}
