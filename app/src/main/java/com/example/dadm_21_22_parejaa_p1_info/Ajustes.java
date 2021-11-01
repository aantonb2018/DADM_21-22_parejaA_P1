package com.example.dadm_21_22_parejaa_p1_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;

public class Ajustes extends AppCompatActivity {

    SeekBar barra_volumen;
    AudioManager audioManager;
    Button btn_vol;

    private RadioButton radioBut[] = new RadioButton[3];
    private RadioButton radioDif[] = new RadioButton[3];

    int numPreguntas, dificultad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        // JUEGO EN PANTALLA COMPLETA
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        SharedPreferences ajustes = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        SharedPreferences.Editor editor = ajustes.edit();

        numPreguntas = ajustes.getInt("key_num", 10);
        dificultad = ajustes.getInt("key_dif", 0);

        // RADIOBUTTONS ----------------------------------------------
        radioBut[0] = (RadioButton) findViewById(R.id.radioBut_libre_clas);
        radioBut[1] = (RadioButton) findViewById(R.id.radioBut_facil_clas);
        radioBut[2] = (RadioButton) findViewById(R.id.radioBut_medio_clas);

        switch(numPreguntas){
            case 5:
                radioBut[0].setChecked(true);
                break;
            case 10:
                radioBut[1].setChecked(true);
                break;
            case 15:
                radioBut[2].setChecked(true);
                break;
        }

        radioDif[0] = (RadioButton) findViewById(R.id.radioBut_libre);
        radioDif[1] = (RadioButton) findViewById(R.id.radioBut_facil);
        radioDif[2] = (RadioButton) findViewById(R.id.radioBut_medio);

        switch(dificultad){
            case 0:
                radioDif[0].setChecked(true);
                break;
            case 1:
                radioDif[1].setChecked(true);
                break;
            case 2:
                radioDif[2].setChecked(true);
                break;
        }

        radioBut[0].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                selectNum(5);
            }
        });
        radioBut[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                selectNum(10);
            }
        });
        radioBut[2].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                selectNum(15);
            }
        });

        radioDif[0].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                selectDif(0);
            }
        });
        radioDif[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                selectDif(1);
            }
        });
        radioDif[2].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                selectDif(2);
            }
        });

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
                editor.putInt("key_num", numPreguntas);
                editor.putInt("key_dif", dificultad);

                editor.commit(); // commit changes

                // se pasa a la siguiente actividad
                Intent empezar = new Intent(Ajustes.this, MenuPrincipal.class);

                //empezar.putExtra("nick", (inputTexto.getText().toString()));
                startActivity(empezar);
            }
        });
    }

    public void selectNum(int i){
        numPreguntas = i;
    }

    public void selectDif(int i){
        dificultad = i;
    }
}