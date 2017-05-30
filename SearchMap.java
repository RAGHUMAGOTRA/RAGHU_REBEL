package com.example.admin.vibring.googlemaps;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.vibring.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SearchMap extends FragmentActivity implements OnMapReadyCallback,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener {

    private GoogleMap mMap;
    private GoogleApiClient googleApiClient;
    Button btnsearch, btncurrentlocation;
    private double latitude, longitude;
    private LatLng latLng;

    String locationsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchmap);

        btnsearch = (Button) findViewById(R.id.btn_find);
        btncurrentlocation = (Button) findViewById(R.id.btnCurrentLocation);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        //mapFragment.getMapAsync((OnMapReadyCallback) this);

        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

//        btncurrentlocation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mMap.clear();
//
//                //Creating a location object
//                if (ActivityCompat.checkSelfPermission(SearchMap.this,
//                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                        && ActivityCompat.checkSelfPermission(SearchMap.this, Manifest.permission.ACCESS_COARSE_LOCATION)
//                        != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
//                Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
//                if (location != null) {
//                    latitude = location.getLatitude();
//                    longitude = location.getLongitude();
//                }
//              //  reversegeocoding();
//
//                //Moving the map to location
//                moveMap();
//
//
//            }
//        });
//


        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText edtsearch = (EditText) findViewById(R.id.et_location);
               locationsearch = edtsearch.getText().toString();
               // Toast.makeText(getBaseContext(),location,Toast.LENGTH_LONG).show();
                mMap.clear();
                List<Address> adddressList = null;
               // String addresstext="";
                if (locationsearch != null && !locationsearch.equals("")) {
                    //Toast.makeText(getBaseContext(), "Location is not empty", Toast.LENGTH_LONG).show();
                    Geocoder geocoder = new Geocoder(SearchMap.this, Locale.getDefault());
                    try {
                        adddressList = geocoder.getFromLocationName(locationsearch, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (adddressList != null && adddressList.size() > 0) {
                        Address address = adddressList.get(0);


                        latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        latitude = latLng.latitude;
                        longitude = latLng.longitude;
                    }
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title(locationsearch));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,30));
                   // mMap.animateCamera(CameraUpdateFactory.zoomIn());
                   // mMap.animateCamera(CameraUpdateFactory.zoomTo(30));

                    //  mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
                    mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
                        @Override
                        public void onMapClick(LatLng latLng) {
                            mMap.clear();
                            mMap.addMarker(new MarkerOptions().position(latLng).title(locationsearch));
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,30.0f));
                            //mMap.animateCamera(CameraUpdateFactory.zoomTo(50));
                        }
                    });
                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            Intent intent = new Intent(SearchMap.this, LocationActivity.class);
                            //   SENDING DATA TO NEXT ACTIVITY LOCATION ACTIVITY
                            intent.putExtra("locationsearch", locationsearch);
                            intent.putExtra("latitude", latitude);
                            intent.putExtra("longitude", longitude);
                            startActivity(intent);
                            //currentmarker.remove();
                            return true;
                        }
                    });
                }
                 else {
                    Toast.makeText(getBaseContext(), "Please Enter Location", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();


    }
    @Override
    protected void onStop() {
        super.onStop();
        googleApiClient.disconnect();
    }



    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.clear();



                        mMap.addMarker(new MarkerOptions().position(latLng).title("hello"));
                mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
            }
        });
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return true;
            }
        });






    }
 /*  public void reversegeocoding()
    {List<Address> adddressList = null;
        String addresstext="";

        Geocoder geocoder = new Geocoder(SearchMap.this);
        try {
            adddressList = geocoder.getFromLocationName(locationsearch, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(adddressList != null && adddressList.size()>0)
        {
            Address address = adddressList.get(0);
            addresstext=String.format("%s,%s,%s",
            address.getMaxAddressLineIndex()>0 ? address.getAddressLine(0) : "",address.getLocality(),address.getCountryName());


            latLng = new LatLng(address.getLatitude(), address.getLongitude());
            latitude = latLng.latitude;
            longitude = latLng.longitude;
            mMap.addMarker(new MarkerOptions().position(latLng).title(addresstext));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        }
    }*/
    public void moveMap() {

        LatLng latLng = new LatLng(latitude, longitude);

        //Adding marker
        mMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title("Current Location"));

        //Moving the camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomTo(40));
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
