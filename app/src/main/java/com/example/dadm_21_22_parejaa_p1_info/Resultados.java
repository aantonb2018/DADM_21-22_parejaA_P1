package com.example.dadm_21_22_parejaa_p1_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaParser;
import android.os.Bundle;
import android.view.View;

public class Resultados extends AppCompatActivity {

    SharedPreferences sP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        getSupportActionBar().hide();
    }

    public void volverMenu(View view){
        Intent volverMenu = new Intent(this,MenuPrincipal.class);
        startActivity(volverMenu);
    }
}