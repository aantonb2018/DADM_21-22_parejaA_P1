package com.example.dadm_21_22_parejaa_p1_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class Ajustes extends AppCompatActivity {

    SeekBar barra_volumen;
    AudioManager audioManager;
    Button btn_vol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        // JUEGO EN PANTALLA COMPLETA
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // #####################################################################
        // BARRA VOLUMEN
        // #####################################################################

        // encontrar el elemento seekBar de la activity y asignar a la variable
        barra_volumen = findViewById(R.id.barraVolumen);

        // asignar al manager el audio del móvil
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //obtener máximo volumen
        int maxVolumen = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        // obtener volumen actual
        int actualVolumen = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

        // asignar máximo volumen a la barra de volumen
        barra_volumen.setMax(maxVolumen);

        // asignar volumen actual a la barra
        barra_volumen.setProgress(actualVolumen);

        // para los cambios en la barra de volumen
        barra_volumen.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // #####################################################################
        // BOTON VOLVER AL MENU
        // #####################################################################

        // encontrar botón
        btn_vol = (Button) findViewById(R.id.btn_volverMenu_resul);

        // definición de evento onClick del botón "volver al menú"
        btn_vol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // se pasa a la siguiente actividad
                Intent empezar = new Intent(Ajustes.this, MenuPrincipal.class);

                //empezar.putExtra("nick", (inputTexto.getText().toString()));
                startActivity(empezar);
            }
        });
    }
}