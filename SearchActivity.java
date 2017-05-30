package com.example.admin.vibring.googlemaps;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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

public class SearchActivity extends FragmentActivity implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, GoogleMap.OnMarkerDragListener, GoogleMap.OnMapLongClickListener ,OnMapReadyCallback,LocationListener {
    MarkerOptions markerOptions;

    private GoogleMap mMap;
    private Button btnCurrentLocation, btn_search;
    private GoogleApiClient googleApiClient;
    private double latitude, longitude;
    private EditText edtsearch;
    private Marker marker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map11);
        mapFragment.getMapAsync(this);

        //When you want to make a connection to one of the Google APIs provided in the Google Play services
        // library (such as Google Sign-In, Games, or Drive), you need to create an
        // instance of GoogleApiClient ("Google API Client"). The Google API Client
        // provides a common entry point to all the Google Play services and manages the
        // network connection between the user's device and each Google service.

        //Initializing googleApiClient
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        //findViewByID
        btnCurrentLocation = (Button) findViewById(R.id.btnCurrentLocation);
        // btn_search = (Button) findViewById(R.id.btnsearch);//Adding onClickListener
        btnCurrentLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mMap.clear();


                //Creating a location object
                if (ActivityCompat.checkSelfPermission(SearchActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(SearchActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
                if (location != null) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                }
                //  reversegeocoding();

                //Moving the map to location
                moveMap();


            }
        });


    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.clear();
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mMap.setMyLocationEnabled(true);

        //Creating a location object
        if (ActivityCompat.checkSelfPermission(SearchActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(SearchActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        Location location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if (location != null) {
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }
        //  reversegeocoding();

        //Moving the map to location
        // moveMap();


        //mohali
        //  Latitude : 30.7046486
        //  Longitude : 76.7178726

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(30.2110994, 74.9454745);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Mohali"));
//        mMap.moveCamera(CameraUpdateFactory.zoomIn());
//        mMap.setOnMarkerDragListener(this);
//        mMap.setOnMapLongClickListener(this);
       mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {


            }
        });
        getLatLong(latitude, longitude);

    }
@Override
public void onLocationChanged(Location location)
{
    double latitude= location.getLatitude();
    double longitude=location.getLongitude();
    LatLng latLng = new LatLng(latitude,longitude);
    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    mMap.animateCamera(CameraUpdateFactory.zoomTo(20),2000,null);

}

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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

    @Override
    public void onMapLongClick(LatLng latLng) {

    }

    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }

    public void getLatLong(Double latitude, Double longitude) {

    }


    private class ReverseGeocodingTask extends AsyncTask<LatLng, Void, String> {
        Context mContext;

        public ReverseGeocodingTask(Context context) {
            super();
            mContext = context;
        }

        // Finding address using reverse geocoding
        @Override
        protected String doInBackground(LatLng... params) {
            Geocoder geocoder = new Geocoder(mContext);
            double latitude = params[0].latitude;
            double longitude = params[0].longitude;

            List<Address> addresses = null;
            String addressText = "";

            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);

                addressText = String.format("%s, %s, %s",
                        address.getMaxAddressLineIndex() > 0 ? address.getAddressLine(0) : "",
                        address.getLocality(),
                        address.getCountryName());
            }

            return addressText;
        }

        @Override
        protected void onPostExecute(final String addressText) {
            // Setting the title for the marker.
            // This will be displayed on taping the marker
            markerOptions.title(addressText);

            // Placing a marker on the touched position
            mMap.addMarker(markerOptions).setTitle(addressText);
//            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
//                @Override
//                public boolean onMarkerClick(Marker marker) {
//                    Intent intent = new Intent(SearchActivity.this, LocationActivity.class);
//                    //   SENDING DATA TO NEXT ACTIVITY LOCATION ACTIVITY
//                    intent.putExtra("locationsearch", addressText);
//                    intent.putExtra("latitude", latitude);
//                    intent.putExtra("longitude", longitude);
//                    startActivity(intent);
//
//                    return true;
//                }
//            });

        }
    }


    public void moveMap() {

        LatLng latLng = new LatLng(latitude, longitude);
        //Adding marker
        mMap.addMarker(new MarkerOptions().position(latLng));

        //Moving the camera
       mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

        //Animating the camera
        mMap.animateCamera(CameraUpdateFactory.zoomIn());
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

// Clears the previously touched position
                mMap.clear();

                // Animating to the touched position
               // mMap.animateCamera(CameraUpdateFactory.zoomTo(50.0f));

                // Creating a marker
                markerOptions = new MarkerOptions();

                // Setting the position for the marker
                markerOptions.position(latLng);

                // Placing a marker on the touched position
                mMap.addMarker(markerOptions);

                // Adding Marker on the touched location with address
                new ReverseGeocodingTask(getBaseContext()).execute(latLng);

              // mMap.addMarker(new MarkerOptions().position(latLng));
            }
        });
      // String s =latLng.toString();
      //  marker.setTitle(s);
    }

    //Getting Current Location

}