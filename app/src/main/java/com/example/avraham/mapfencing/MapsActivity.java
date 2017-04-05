package com.example.avraham.mapfencing;

import android.location.Location;
import android.location.LocationListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        LocationListener,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{

    private GoogleMap mMap;
    private Marker mPosKini;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        // Add a marker in Sydney and move the camera

        //batas lokasi upi
        //urutan harus kiri bawah,kanan atas kotak
        //ini untuk buat marker
        LatLng GedungIlkom = new LatLng(-6.860418, 107.589889);
        mMap.addMarker(new MarkerOptions().position(GedungIlkom).title("Marker di GIK"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(GedungIlkom));

        LatLng posKini= new LatLng(-6.8601418,107.589889);
        mPosKini= mMap.addMarker(new MarkerOptions().position(posKini).title("Posisi Sekarang"));

        LatLngBounds UPI= new LatLngBounds(new LatLng(-6.863273,107.587212), new LatLng(-6.858025,107.597839));

        //mengeset batas kamera ke UPI
        int width= getResources().getDisplayMetrics().widthPixels;
        int heigth= getResources().getDisplayMetrics().heightPixels;
        int padding= (int)(width*0.12); //offset dari edges
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(UPI,width,heigth,padding));

    }

    @Override
    public void onLocationChanged(Location location) {
        AlertDialog ad = new AlertDialog.Builder(this).create();
        ad.setMessage("Update Lokasi");
        ad.show();
        mPosKini.setPosition(new LatLng(location.getLatitude(),location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

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
