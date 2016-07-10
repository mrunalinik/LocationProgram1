package com.example.b19_location.location;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.example.b19_location.interfaces.LocationCallback;

/**
 * Created by chethan on 12/9/2015.
 */
public class GetLocation {

    private LocationManager locationManager;
    private Context context;
    private LocationCallback locationCallback;

    public GetLocation(Context context, LocationCallback locationCallback) {
        this.context = context;
        this.locationCallback = locationCallback;
    }

    public void getLocationFromGPS(){
        locationManager = (LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            locationCallback.getLocation(location);

            locationManager.removeUpdates(locationListener);
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
    };
}
