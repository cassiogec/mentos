package com.whdev.garagem.activits;

/**
 * Created by Lucas Altmann on 30/05/2017.
 */

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.whdev.garagem.R;
import com.whdev.garagem.alerts.AlertsGaragem;
import com.whdev.garagem.beans.EnviarMensagem;
import com.whdev.garagem.utils.CheckStatus;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    protected LocationManager locationManager;
    private int count;
    private ProgressDialog progressDialog;
    Double latitude;
    Double longitude;
    String msgPacote;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.obtain_location));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        count = 0;

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        if (!CheckStatus.checkStatusGPS(this)) {
            AlertsGaragem alertsGaragem = new AlertsGaragem(this);
            alertsGaragem.createAlert(R.string.check_status_gps, R.string.close);
            return;
        } else
            progressDialog.show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            return;

        mMap.setMyLocationEnabled(true);
    }

    @Override
    public void onLocationChanged(Location location) {

        if (location.getLatitude() != 0 && count == 0)
            count += 1;

        if (count == 1) {
            if (location != null) {
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                        .zoom(13)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                count += 1;
                progressDialog.dismiss();
            }
        }
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        msgPacote = "LATITUDE " + latitude.toString() + " LONGITUDE " + longitude.toString();
        thread = new Thread(new EnviarMensagem(msgPacote.toString()));
        thread.start();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("onStatusChanged", "status " + provider.getBytes());
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d("onProviderEnabled", "provider " + provider.getBytes());
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d("onProviderDisabled", "provider " + provider.getBytes());
    }
}