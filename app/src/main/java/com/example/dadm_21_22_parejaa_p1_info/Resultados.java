package com.example.dadm_21_22_parejaa_p1_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaParser;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Resultados extends AppCompatActivity {

    private String nick;
    private int score;

    private TextView comentario;
    private TextView puntuacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        getSupportActionBar().hide();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nick = extras.getString("nick2");
            score= extras.getInt("score");
        }

        puntuacion = (TextView) findViewById(R.id.puntuacion);
        comentario = (TextView) findViewById(R.id.comentario);

        puntuacion.setText(nick + " - " + score + "/10");

        if(score < 5){
            puntuacion.setTextColor(Color.parseColor("#CC7474"));
            comentario.setText("Es una puntaucón un poco baja, seguro que puedes hacerlo mejor");
            comentario.setTextColor(Color.parseColor("#CC7474"));
        }else if(score > 4 && score < 9){
            puntuacion.setTextColor(Color.parseColor("#FF000000"));
            comentario.setText("Has aprobado con creces. Eres todo un entendido del anime");
            comentario.setTextColor(Color.parseColor("#FF000000"));
        }else if(score > 8 && score < 11){
            puntuacion.setTextColor(Color.parseColor("#B5DD86"));
            comentario.setText("Eres un auténtico otaku, estamos a tus pies sensei");
            comentario.setTextColor(Color.parseColor("#B5DD86"));
        }

    }

    public void volverMenu(View view){
        Intent volverMenu = new Intent(this,MenuPrincipal.class);
        startActivity(volverMenu);
    }

    public void volverJuego(View view){
        Intent volverJuego = new Intent(this,ActivityPreguntas.class);

        volverJuego.putExtra("nick", nick);

        startActivity(volverJuego);
    }
}