package com.whdev.garagem.utils;

/**
 * Created by Lucas Altmann on 30/05/2017.
 */

import android.content.Context;
import android.location.LocationManager;

public class CheckStatus {

    public static boolean checkStatusGPS(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
