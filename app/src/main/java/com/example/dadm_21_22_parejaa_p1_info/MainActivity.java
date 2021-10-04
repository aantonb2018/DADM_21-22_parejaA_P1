package com.example.dadm_21_22_parejaa_p1_info;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private int idxRespuestas[] = {
            R.id.respuesta0, R.id.respuesta1, R.id.respuesta2, R.id.respuesta3
    };

    private TextView pregunta; //Texto que muestra la pregunta
    private RadioGroup grupoRespuestas; //Grupo para los radioButtons de las respuestas
    private Button siguiente; //Boton para pasar a la siguiente pregunta
    private Button anterior; //Boton para volver a la pregunta anterior (probablemente haya que quitarlo)

    private String[][] preguntas; //Array de arrays, contiene los arrays donde en la pos 0 esta la pregunta y en el resto las respuestas
    private int idxPregunta = 0; //Indice de la pregunta actual
    private int soluciones[]; //Array con los indices de las soluciones correctas del array de arrays preguntas
    int aciertos, fallos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        leerItems(); //Metodo para leer los items de string.xml y poder usarlos en el MainActivity

        pregunta = (TextView) findViewById(R.id.pregunta);
        grupoRespuestas = (RadioGroup) findViewById(R.id.radioGroup);
        siguiente = (Button) findViewById(R.id.siguiente);
        anterior = (Button) findViewById(R.id.anterior);

        mostrarPreguntas();
        //Toast.makeText(MainActivity.this, preguntas[0][1], Toast.LENGTH_LONG).show();
    }

    private void leerItems(){
        String auxPreguntas[] = getResources().getStringArray(R.array.test); //Obtenemos los items que son las preguntas y respuestas
        preguntas = new String[auxPreguntas.length][5];
        soluciones = new int[auxPreguntas.length];
        for(int i = 0; i < auxPreguntas.length; i++){
            String respuestas[] = auxPreguntas[i].split(";"); //Separamos cada elemento del array por los ; para tener en cada posicion una respuesta diferente
            //Toast.makeText(MainActivity.this, respuestas[4], Toast.LENGTH_LONG).show();
            for(int j = 0; j < respuestas.length; j++){
                if(respuestas[j].charAt(0) == '*'){ //Si la respuesta empieza con * es que es la correcta
                    soluciones[i] = j; //Almacenamos en la posicion de la pregunta el indice de la respuesta correcta
                    respuestas[j] = respuestas[j].substring(1); //Partimos el String a partir del * para quitarlo del mismo
                }
                preguntas[i][j] = respuestas[j]; //Guardamos el array que contiene una pregunta y sus respuestas dentro del array de todas las preguntas
            }
        }
    }

    private void mostrarPreguntas(){
        grupoRespuestas.clearCheck(); //Limpia la respuesta seleccionada

        pregunta.setText(preguntas[idxPregunta][0]); //Escribe la pregunta actual en la pantalla
        for(int i = 0; i < idxRespuestas.length; i++){
            RadioButton resp = (RadioButton) findViewById(idxRespuestas[i]);
            resp.setText(preguntas[idxPregunta][i+1]); //Escribe la respuesta del indice correspondiente en la pantalla

            /*
            if(soluciones[idxPregunta] == i){ //Comprueba si esa respuesat es al correcta
                resp.setChecked(true); //Autoselecciona la respuesta correcta
            }
            */

        }
    }

    private void comprobarRespuesta(){
        int respuestaSelec = grupoRespuestas.getCheckedRadioButtonId(); //Recoge el id de la respuesta marcada

        if(soluciones[idxPregunta] == respuestaSelec){ //Comprueba si la respuesta es correcta o erronea
            aciertos++;
        }else{
            fallos++;
        }
    }
}