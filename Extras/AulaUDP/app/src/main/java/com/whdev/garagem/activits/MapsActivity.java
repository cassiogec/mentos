package com.whdev.garagem.activits;

/**
 * Created by Lucas Altmann on 30/05/2017.
 */

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.twitter.sdk.android.tweetcomposer.TweetComposer;
import com.whdev.garagem.R;
import com.whdev.garagem.alerts.AlertsGaragem;
import com.whdev.garagem.beans.EnviarMensagem;
import com.whdev.garagem.utils.CheckStatus;

import java.util.List;

import static com.whdev.garagem.activits.LoginActivity.pref;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;
    protected LocationManager locationManager;
    private int count;
    private ProgressDialog progressDialog;
    private Double latitude;
    private Double longitude;
    private String msgPacote;
    private Thread thread;
    private Button share;
    private String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        share = (Button) findViewById(R.id.btn_share);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage(getResources().getString(R.string.obtain_location));
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        count = 0;

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TweetComposer.Builder builder = new TweetComposer.Builder(MapsActivity.this)
                        .text("#Partindo de " + address + " ;-)");
                builder.show();
            }
        });

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
                LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                getAddress(latLng);
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(location.getLatitude(), location.getLongitude()))
                        .zoom(13)
                        .build();
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                count += 1;
                progressDialog.dismiss();
            }

            latitude = location.getLatitude();
            longitude = location.getLongitude();
            msgPacote = pref.getString("placa", null).toUpperCase() +
                    ":" + latitude.toString() +
                    ":" + longitude.toString();
            thread = new Thread(new EnviarMensagem(msgPacote.toString()));
            thread.start();
        }
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

    public String getAddress(LatLng point) {
        try {
            Geocoder geocoder;
            List<Address> addresses;
            geocoder = new Geocoder(this);
            if (point.latitude != 0 || point.longitude != 0) {
                addresses = geocoder.getFromLocation(point.latitude ,
                        point.longitude, 1);
                address = addresses.get(0).getAddressLine(1);
                return address;

            } else {
                Toast.makeText(this, "Não possível determinar o nome da Cidade",
                        Toast.LENGTH_LONG).show();
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}