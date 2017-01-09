package riksasuviana.apps.foodreservation;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

/**
 * Created by Riksa Suviana on 07/01/2017.
 */

public class GetMyLocation implements LocationListener {

    LocationManager lm;
    LocationListener ll;
    Context context;
    String latitude, longitude;

    public GetMyLocation(){

    }

    @Override
    public void onLocationChanged(Location location) {

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
}
