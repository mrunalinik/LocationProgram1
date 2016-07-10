package com.example.b19_location.location;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

import com.example.b19_location.interfaces.AddressCallback;

import java.io.IOException;
import java.util.List;

/**
 * Created by chethan on 12/9/2015.
 */
public class GeoCoderTask extends AsyncTask<Void, Void, List<Address>>{

    private Context context;
    private Location locationObj;
    private AddressCallback addressCallback;

    public GeoCoderTask(Context context, Location locationObj, AddressCallback addressCallback) {
        this.context = context;
        this.locationObj = locationObj;
        this.addressCallback = addressCallback;
    }

    @Override
    protected List<Address> doInBackground(Void... params) {

        Geocoder geocoder = new Geocoder(context);

        List<Address> addressList = null;

        try {
            addressList = geocoder.getFromLocation(locationObj.getLatitude(), locationObj.getLongitude(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return addressList;
    }

    @Override
    protected void onPostExecute(List<Address> addressList) {
        addressCallback.getAddress(addressList);
    }
}
