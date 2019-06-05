package com.example.hw7;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private final static String TAG = "From MapActivity :=";

    private FusedLocationProviderClient mLocationClient;
    private boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    private String StreetAddress;

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
        if (mLocationPermissionGranted){
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
                        StreetAddress = streetAddress(actualLocation);
                        StreetAddress = addressFilter(StreetAddress, latLong);
                        /*Log.i(TAG, "Street Address:" + StreetAddress);*/
                        mMap.addMarker(new MarkerOptions()
                                .position(d_loc)
                                .title("Current Address:")
                                .snippet(StreetAddress)
                                .icon(BitmapDescriptorFactory
                                        .defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

                        //Camera Location
                        Intent intent = getIntent();
                        String[] camData = intent.getStringArrayExtra(MainActivity.MESSAGE_ID);
                        Double camLat = Double.parseDouble(camData[0]);
                        Double camLng = Double.parseDouble(camData[1]);
                        String camDescription = camData[2];
                        /*String imgUrl = camData[3];*/
                        LatLng c_loc = new LatLng(camLat, camLng);
                        mMap.addMarker(new MarkerOptions().position(c_loc)
                                .title("Camera @ " + camDescription)
                                /*.snippet(imgUrl)*/);

                        /*mMap.setInfoWindowAdapter(new MapInfoWindowAdapter());*/
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
            mLocationPermissionGranted = true;
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
        mLocationPermissionGranted = false;
        switch (requestCode) {
            // requestCode is our ID of our request
            case 1: {
                if (grantResults.length>0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    mLocationPermissionGranted = true;
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

    /*reference dev doc on geocoder:
        https://developer.android.com/training/location/display-address.html*/
    private String streetAddress(Location location){
        String stAddress;
        List<Address> addresses = null;

        try{
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            addresses = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
        } catch (IOException ioExp){
            Log.e(TAG, "GMS geocoder service not available" + ioExp);
            stAddress = "service_not_available";
        } catch (IllegalArgumentException illegalArgumentException){
            Log.e(TAG, "Invaild LatLng at: "
                    + "Lat-" + location.getLatitude()
                    + "Lng-" + location.getLongitude()
                    , illegalArgumentException);
            stAddress = "location_illegal";
        }

        if (addresses == null || addresses.isEmpty()){
            Log.e(TAG, "Street address not found.");
            stAddress = "location_not_found";
        } else {
            Address address = addresses.get(0);
            /*not sure why doc is using ArrayList and such, multiple place on method limit only one
            address acquired.*/
            ArrayList<String> addressFragments = new ArrayList<String>();

            //Fetch the address Lines use getAddressLine
            for (int i = 0; i <= address.getMaxAddressLineIndex(); i++){
                addressFragments.add(address.getAddressLine(i));
            }

            //Log.i(TAG, addressFragments.get(0));
            stAddress = addressFragments.get(0);
        }
        return stAddress;
    }

    private String addressFilter(String stAddress, String latLong){

        if (stAddress == "service_not_available"
                || stAddress == "location_illegal" ){
            Toast toast = Toast.makeText(
                    this, stAddress,Toast.LENGTH_SHORT);
            toast.show();
        } else if (stAddress == "location_not_found"){
            stAddress = latLong;
        }

        return stAddress;
    }

    @Deprecated
    class MapInfoWindowAdapter implements GoogleMap.InfoWindowAdapter{

        private final View camView;

        MapInfoWindowAdapter(){
            camView = getLayoutInflater().inflate(R.layout.info_window_camera, null);
        }

        //https://developers.google.com/android/reference/com/google/android/gms/maps/GoogleMap.InfoWindowAdapter
        //http://android-er.blogspot.com/2013/01/create-custom-info-contents-for-by.html
        @Override
        public View getInfoContents(Marker marker){
            TextView view = (TextView)camView.findViewById(R.id.title_camera);
            view.setText(marker.getTitle());
            /*ImageView camImage = (ImageView)camView.findViewById(R.id.image_camera);*/
            String imgUrl = marker.getSnippet();
            Log.d(TAG, imgUrl);
            if (imgUrl == StreetAddress){
                TextView address = new TextView(null);
                address.setText(StreetAddress);
            } else {
                ImageView camImage = new ImageView(null);

                Picasso.get().load(imgUrl).into(camImage);
            }

            return camView;
        }

        @Override
        public View getInfoWindow(Marker marker){
            return null;
        }


    }

}
