package com.example.dori.failsafeforreal;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapView extends FragmentActivity {

    private GoogleMap mMap;
    public static boolean saver = false;

    public static final String TAG = mapView.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_view);
        setUpMapIfNeeded();


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        if (mMap == null) {
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void showCurrentLocation(Location location) {
        mMap.clear();

        LatLng currentPosition = new LatLng(location.getLatitude(), location.getLongitude());

        mMap.addMarker(new MarkerOptions().position(currentPosition)
                .title("Current Location"));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 18));
    }

    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(33.6405, -117.8443))
        .title("Current Location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(33.6405, -117.8443), 14.0f));

        addCircleToMap();

    }

    private void addCircleToMap() {

        // circle settings
        int radiusM = 1609;// your radius in meters
        double latitude = 33.6405;// your center latitude
        double longitude = -117.8443;// your center longitude
        //LatLng latLng = new LatLng(latitude,longitude);              //uci location
        LatLng latLng = new LatLng(latitude, longitude); //currentLocation
        // draw circle
        int d = 500; // diameter
        Bitmap bm = Bitmap.createBitmap(d, d, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bm);
        Paint p = new Paint();
        c.drawCircle(d / 2, d / 2, d / 2, p);


        // generate BitmapDescriptor from circle Bitmap
        BitmapDescriptor bmD = BitmapDescriptorFactory.fromBitmap(bm);

// mapView is the GoogleMap
        mMap.addGroundOverlay(new GroundOverlayOptions().
                image(bmD).
                position(latLng,radiusM*2,radiusM*2).
                transparency(0.8f));
    }
}





