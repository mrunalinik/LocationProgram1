package com.example.b19_location;

import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.b19_location.interfaces.AddressCallback;
import com.example.b19_location.interfaces.LocationCallback;
import com.example.b19_location.location.GeoCoderTask;
import com.example.b19_location.location.GetLocation;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button getLocationButton;
    private TextView locationTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getLocationButton = (Button)findViewById(R.id.getLocationBtn);
        locationTv = (TextView)findViewById(R.id.locationTv);

        getLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetLocation getLocation = new GetLocation(MainActivity.this, locationCallback);
                getLocation.getLocationFromGPS();
            }
        });
    }

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void getLocation(Location location) {
            String locationString = "Lat = "+location.getLatitude()+"Long = "+location.getLongitude();

            locationTv.setText(locationString);


            //Start the GeoCoder Task

            GeoCoderTask geoCoderTask = new GeoCoderTask(MainActivity.this, location, addressCallback);
            geoCoderTask.execute();
        }
    };

    AddressCallback addressCallback = new AddressCallback() {
        @Override
        public void getAddress(List<Address> addressList) {

            if(addressList != null && addressList.size() >0){

                Address address = addressList.get(0);

                String addressString = address.getCountryName()+" "+address.getAddressLine(0);

                locationTv.setText(addressString);
            }

        }
    };
}
