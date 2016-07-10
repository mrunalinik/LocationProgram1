package com.example.b19_location.interfaces;

import android.location.Address;

import java.util.List;

/**
 * Created by chethan on 12/9/2015.
 */
public interface AddressCallback {
    void getAddress(List<Address> addressList);
}
