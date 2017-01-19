package riksasuviana.apps.foodreservation;

import android.content.Context;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.identity.intents.Address;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    GPSTracker gps;

    private GoogleMap mMap;

    double longitude, latitude;

    LocationManager lm;
    LocationListener ll;

    @BindView(R.id.mymaptext) TextView text;

    @OnClick(R.id.mymapbtn) void click(){
        text.setText("Longitude : "+longitude+" - Latitude : "+latitude);

        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());

        try {
//
            List<android.location.Address> addresses = geocoder.getFromLocation(longitude, latitude, 1);
//
            String kota = addresses.get(0).getLocality();
//
//            if (addresses.size() > 0) {
//                text.setText(kota);
//            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        ButterKnife.bind(this);
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
        gps = new GPSTracker(MapsActivity.this);
        mMap = googleMap;
//        mMap.clear();

        mMap.setMyLocationEnabled(true);

        Location l = mMap.getMyLocation();

        LatLng mymark = new LatLng(-6.956453, 107.682828);

//        mMap.addMarker(new MarkerOptions()
//        .position(mymark).title("Hello World"));

        Circle c = mMap.addCircle(new CircleOptions().center(mymark).radius(1).fillColor(Color.BLUE));

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Toast.makeText(MapsActivity.this, "Click", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(107.6821881, -6.95722148);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
