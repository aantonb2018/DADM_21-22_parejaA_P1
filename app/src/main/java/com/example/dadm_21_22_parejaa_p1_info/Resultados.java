package com.example.dadm_21_22_parejaa_p1_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaParser;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Resultados extends AppCompatActivity {

    private String nick;
    private int score;

    private Button ib_reiniciar;
    private Button ib_volverMenu;

    private TextView comentario;
    private TextView puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        // ESCONDER TITULO DE ACTIVIDAD
        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nick = extras.getString("nick2");
            score= extras.getInt("score");
        }

        // LOCALIZAR ELEMENTOS -----------------------------------------
        puntuacion = (TextView) findViewById(R.id.puntuacion);
        comentario = (TextView) findViewById(R.id.comentario);

        ib_reiniciar = (Button) findViewById(R.id.btn_restart);
        ib_volverMenu = (Button) findViewById(R.id.btn_volverMenu);
        // -------------------------------------------------------------

        puntuacion.setText(nick + " - " + score + "/10");

        if(score < 5){
            puntuacion.setTextColor(Color.parseColor("#FF000000"));
            comentario.setText("Es una puntuación un poco baja, seguro que puedes hacerlo mejor");
            comentario.setTextColor(Color.parseColor("#FF000000"));
        }else if(score > 4 && score < 9){
            puntuacion.setTextColor(Color.parseColor("#FF000000"));
            comentario.setText("Has aprobado con creces. Eres todo un entendido del anime");
            comentario.setTextColor(Color.parseColor("#FF000000"));
        }else if(score > 8 && score < 11){
            puntuacion.setTextColor(Color.parseColor("#FF000000"));
            comentario.setText("Eres un auténtico otaku, estamos a tus pies sensei");
            comentario.setTextColor(Color.parseColor("#FF000000"));
        }

        // Cuando se pulsa sobre el botón "reiniciar" se llama a este evento
        ib_reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverJuego = new Intent(Resultados.this,ActivityPreguntas.class);

                volverJuego.putExtra("nick", nick);

                startActivity(volverJuego);
                finish();
            }
        });

        // Cuando se pulsa sobre el botón "volver al menú" se llama a este evento
        ib_volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverMenu = new Intent(Resultados.this,MenuPrincipal.class);
                startActivity(volverMenu);
                finish();
            }
        });

    }

}