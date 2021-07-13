package com.example.uastkbahmadsiddiq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnrumah,btnpeta,btnexit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            btnrumah =findViewById(R.id.btnListRumah);
            btnpeta = findViewById(R.id.btnmobil);
            btnexit = findViewById(R.id.btnkeluar);

        btnrumah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent rumah = new Intent(MainActivity.this, ListRumah.class);
                startActivity(rumah);
            }
        });

        btnpeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent peta = new Intent( MainActivity.this, MapsHarus.class);
                startActivity(peta);
            }
        });
        btnexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
                finish();
            }
        });
    }
}