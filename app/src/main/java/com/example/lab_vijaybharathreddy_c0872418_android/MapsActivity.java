package com.example.lab_vijaybharathreddy_c0872418_android;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.lab_vijaybharathreddy_c0872418_android.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private int PermissionCode = 1;
    String a;
    double lati,longi ;



    private static final String TAG = "MainActivity";
    LocationManager locationManager;

    LocationListener locationListener;

    private GoogleMap mMap;

    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }


    private void requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            //explains why we need Permission
            new AlertDialog.Builder(this)
                    .setTitle("permission needed")
                    .setMessage("need permission to access map")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MapsActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PermissionCode);
                        }

                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    }).create().show();

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PermissionCode);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PermissionCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //toast
                startListening();
                Toast.makeText(this, "permission Granted", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "permission not Granted", Toast.LENGTH_LONG).show();

            }
        }
    }



    private void startListening() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        }
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;




        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                Log.i(TAG, "onLocationChanged: " + location);
                updateLocationInfo(location);
            }
        };
        startListening();


        if (ContextCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(MapsActivity.this, "Permission already Granted", Toast.LENGTH_LONG).show();
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location lasKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (lasKnownLocation != null)
                updateLocationInfo(lasKnownLocation);
            startListening();

        } else {

            requestLocationPermission();
        }

            LatLng sydney = new LatLng(lati, longi);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 1.0f));



    }
    private void updateLocationInfo(Location location) {

        String address = "Could not find the address";

        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addressList != null && addressList.size() > 0) {
                address = "";
                lati = location.getLatitude();
                longi = location.getLongitude();

                // street name
                if (addressList.get(0).getThoroughfare() != null)
                    address += addressList.get(0).getThoroughfare() + "\n";
                if (addressList.get(0).getLocality() != null)
                    address += addressList.get(0).getLocality() + " ";
                if (addressList.get(0).getPostalCode() != null)
                    address += addressList.get(0).getPostalCode() + " ";
                if (addressList.get(0).getAdminArea() != null)
                    address += addressList.get(0).getAdminArea();
                a = address;



            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LatLng z = new LatLng(lati,longi);
        mMap.addMarker(new MarkerOptions().position(z).title(a));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(z, 7.0f));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {
                AlertDialog.Builder alert = new AlertDialog.Builder(MapsActivity.this);
                alert.setPositiveButton("Add to Favorite", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MapsActivity.this ,MainActivity.class);
                        startActivity(intent);

                    }
                }).create().show();

                return false;
            }
        });


    }


    }





