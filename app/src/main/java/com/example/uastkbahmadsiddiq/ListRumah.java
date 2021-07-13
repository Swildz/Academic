package com.example.uastkbahmadsiddiq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListRumah extends AppCompatActivity {

    List <rumah> list;
    RecyclerView recyclerView;

    private static final String rumah_Data_Url=
            "https://juaraharus.000webhostapp.com/rumahdijual.php";
//            "http://192.168.95.1/ServerUASAhmadSiddiq/rumahdijual.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_rumah);

        recyclerView = findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager
                (new LinearLayoutManager(this));
        list= new ArrayList<>();
        DataRumah();
    }

    private void DataRumah() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                rumah_Data_Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array = new JSONArray(response);
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject xr = array.getJSONObject(i);
                        list.add(new rumah(
                                xr.getInt("id"),
                                xr.getString("nama"),
                                xr.getString("keterangan"),
                                xr.getString("gambar"),
                                xr.getString("harga"),
                                xr.getString("alamat"),
                                xr.getString("latitude"),
                                xr.getString("alamat")
                        ));
                    }
                    adapterrumah adapter =
                            new adapterrumah(
                                    ListRumah.this, list);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException ex) {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: "+ error.getMessage());
            }
        });

        Volley.newRequestQueue(this).add(stringRequest);

    }
}