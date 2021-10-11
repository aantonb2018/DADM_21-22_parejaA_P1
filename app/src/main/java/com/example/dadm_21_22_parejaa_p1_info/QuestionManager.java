package com.example.dadm_21_22_parejaa_p1_info;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

public class QuestionManager {

    private Context context;
    private String[][] preguntas; //Array de arrays, contiene los arrays donde en la pos 0 esta la pregunta y en el resto las respuestas
    private int idxPregunta = 0; //Indice de la pregunta actual
    private int[] soluciones; //Array con los indices de las soluciones correctas del array de arrays preguntas
    public int aciertos, fallos = 0;
    private static FragmentManager fragmentManager;
    private static Resources resources;

    public QuestionManager(Context context){
        this.context = context;

        leerItems();
    }

    public static void init(FragmentManager fCManager, Resources r){
        fragmentManager = fCManager;
        resources = r;
    }

    public void leerItems(){
        String auxPreguntas[] = context.getResources().getStringArray(R.array.test); //Obtenemos los items que son las preguntas y respuestas
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
    /*
    public void mostrarPreguntas(View view, TextView buttonQ, Button button[]){
        //grupoRespuestas.clearCheck(); //Limpia la respuesta seleccionada

        buttonQ.setText(preguntas[idxPregunta][0]); //Escribe la pregunta actual en la pantalla

        for(int i = 0; i < button.length; i++){
            button[i] = (Button) view.findViewById(idxRespuestasButton[i]);
            button[i].setText(preguntas[idxPregunta][i+1]); //Escribe la respuesta del indice correspondiente en la pantalla


            if(soluciones[idxPregunta] == i){ //Comprueba si esa respuesat es al correcta
                resp.setChecked(true); //Autoselecciona la respuesta correcta
            }


        }
    }*/

    public String[][] getPreguntas(){
        return preguntas;
    }

    public int getSoluciones(int idxPregunta){
        return soluciones[idxPregunta];
    }

    public int getIdxPregunta(){
        return idxPregunta;
    }

    public int getAciertos(){
        return aciertos;
    }

    public void setAciertos(int aciertos){
        this.aciertos = aciertos;
    }

    public int getFallos(){
        return fallos;
    }

    public void setFallos(int fallos){
        this.fallos = fallos;
    }
}
