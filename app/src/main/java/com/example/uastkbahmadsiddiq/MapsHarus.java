package com.example.uastkbahmadsiddiq;

import androidx.fragment.app.FragmentActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.uastkbahmadsiddiq.databinding.ActivityMapsHarusBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MapsHarus extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MapsHarus.class.getSimpleName();
    private static final String URL =        "https://juaraharus.000webhostapp.com/rumahdijual.php";
                                            //"http://192.168.95.1/ServerUASAhmadSiddiq/rumahdijual.php";
    private GoogleMap mMap;
    private static final float ZOOM_MAP = 13;
    private ArrayList<LatLng> latLngs = new ArrayList<>();
    private MarkerOptions marker = new MarkerOptions();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_harus);
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
        mMap = googleMap;
        enableMapStyles(mMap);
//        enableLongClick(mMap);
        latLngs.add(new LatLng(-0.912237, 100.357327));
        latLngs.add(new LatLng(-0.924092, 100.439357));
        latLngs.add(new LatLng(-0.925572, 100.435934));
        latLngs.add(new LatLng(-0.926227, 100.432244));
        latLngs.add(new LatLng(-0.949808, 100.355794));
        mMap.addMarker(new MarkerOptions().position(latLngs.get(1)).title("Spassbox")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)));
        mMap.addMarker(new MarkerOptions().position(latLngs.get(2)).title("Alvanza")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        mMap.addMarker(new MarkerOptions().position(latLngs.get(3)).title("BRI")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        mMap.addMarker(new MarkerOptions().position(latLngs.get(4)).title("Plaza Andalas")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));


        // Add a marker in Sydney and move the camera
        LatLng basko = new LatLng(-0.901733, 100.350783);
        mMap.addMarker(new MarkerOptions().position(basko).title("Basko Grand Mall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(basko, ZOOM_MAP));
        enableDynamicMarker();

    }

    private void enableDynamicMarker() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    private JSONArray result;


                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject jsonObject1 = array.optJSONObject(i);
                                String latPoint = jsonObject1.getString("latitude");
                                String longPoint = jsonObject1.getString("longitude");
                                String locationName = jsonObject1.getString("nama");
                                String nama = jsonObject1.getString("alamat");
                                String bensin = jsonObject1.getString("harga");
                                mMap.addMarker(new MarkerOptions()
                                        .position(new LatLng(Double.parseDouble(latPoint),
                                                Double.parseDouble(longPoint)))
                                        .title(Double.valueOf(latPoint).toString()
                                                + "," + Double.valueOf(longPoint).toString())
                                        .icon(BitmapDescriptorFactory.defaultMarker
                                                (BitmapDescriptorFactory.HUE_GREEN))
                                        .snippet(locationName +" , " +nama +" , "+bensin ));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom
                                        (new LatLng(-0.9021187, 100.3489041), 13.0f));

                            }
                        } catch (NullPointerException e) {
                            e.printStackTrace();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MapsHarus.this, error.getMessage(),
                        Toast.LENGTH_LONG).show();
            }
        });
        int socketTimeout = 10000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }


    private void enableMapStyles(GoogleMap mMap) {
        try {
            boolean success = mMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.maps_style));
            if (!success) {
                Log.e(TAG, "Style parsing gagal.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Tidak dapat menemukan style. Error: ", e);
        }
    }
}