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
    private Button ib_clasificacion;

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
        ib_clasificacion = (Button) findViewById(R.id.btn_clasif_resultados);
        // -------------------------------------------------------------

        SharedPreferences ajustes = getApplicationContext().getSharedPreferences("MyPref", 0);
        int longitud = ajustes.getInt("key_num", 10);

        puntuacion.setText(nick + " - " + score + "/" + longitud);


        if(((score*10)/longitud) < 5){
            puntuacion.setTextColor(Color.parseColor("#FF000000"));
            comentario.setText("Es una puntuación un poco baja, seguro que puedes hacerlo mejor");
            comentario.setTextColor(Color.parseColor("#FF000000"));
        }else if(((score*10)/longitud) > 4 && ((score*10)/longitud) < 9){
            puntuacion.setTextColor(Color.parseColor("#FF000000"));
            comentario.setText("Has aprobado con creces. Eres todo un entendido del anime");
            comentario.setTextColor(Color.parseColor("#FF000000"));
        }else if(((score*10)/longitud) > 8){
            puntuacion.setTextColor(Color.parseColor("#FF000000"));
            comentario.setText("Eres un auténtico otaku, estamos a tus pies sensei");
            comentario.setTextColor(Color.parseColor("#FF000000"));
        }

        updateRanking();

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

        ib_clasificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irClas = new Intent(Resultados.this,ClasificacionActivity.class);
                startActivity(irClas);
                finish();
            }
        });

    }

    public void updateRanking(){
        SharedPreferences ajustes = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = ajustes.edit();
        int dificultad = ajustes.getInt("key_dif", 0);
        //longitud = ajustes.getInt("key_num", 10);
        //dificultad = ajustes.getInt("key_dif", 0);
        String nicks[] = new String[6];
        int puntos[] = new int[6];
        switch(dificultad){
            case 0:
                for(int i = 0; i < 5; i++){
                    nicks[i] = ajustes.getString("rankingNick" + i,"");
                    puntos[i] = ajustes.getInt("rankingPunto" + i, -1);
                }
                break;
            case 1:
                for(int i = 0; i < 5; i++){
                    nicks[i] = ajustes.getString("rankingNickFacil" + i,"");
                    puntos[i] = ajustes.getInt("rankingPuntoFacil" + i, -1);
                }
                break;
            case 2:
                for(int i = 0; i < 5; i++){
                    nicks[i] = ajustes.getString("rankingNickMedio" + i,"");
                    puntos[i] = ajustes.getInt("rankingPuntoMedio" + i, -1);
                }
                break;
        }


        nicks[5] = nick;
        puntos[5] = score;

        for (int i = 0; i < 5; i++)
        {

            /* find the min element in the unsorted a[i .. aLength-1] */

            /* assume the min is the first element */
            //int auxValue = puntos[i];

            /* test against elements after i to find the smallest */
            for (int j = i+1; j < 6; j++)
            {
                /* if this element is less, then it is the new minimum */
                if (puntos[i] < puntos[j])
                {
                    /* found new minimum; remember its index */
                    //swap(i, auxIdx,puntos);
                    int temp = puntos[i];
                    puntos[i] = puntos[j];
                    puntos[j] = temp;
                    //swap(i, auxIdx,nicks);
                    String auxTemp = nicks[i];
                    nicks[i] = nicks[j];
                    nicks[j] = auxTemp;
                }
            }

        }

        switch(dificultad){
            case 0:
                for(int i = 0; i < 5;i++){
                    editor.putString("rankingNick"+i, nicks[i]);
                    editor.putInt("rankingPunto"+i, puntos[i]);
                }
                break;
            case 1:
                for(int i = 0; i < 5;i++){
                    editor.putString("rankingNickFacil"+i, nicks[i]);
                    editor.putInt("rankingPuntoFacil"+i, puntos[i]);
                }
                break;
            case 2:
                for(int i = 0; i < 5;i++){
                    editor.putString("rankingNickMedio"+i, nicks[i]);
                    editor.putInt("rankingPuntoMedio"+i, puntos[i]);
                }
                break;
        }

        editor.commit();

    }

    public void swap(int x, int y, int[] arr) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public void swap(int x, int y, String[] arr) {
        String temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

}