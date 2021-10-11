package com.example.dadm_21_22_parejaa_p1_info;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class ActivityPreguntas extends AppCompatActivity {

    private TextView pregunta; //Texto que muestra el indice de pregunta
    private TextView acierto;  //Texto que muestra el numero de aciertos
    private TextView fallo;    //Texto que muestra el numero de fallos
    private FragmentManager fragManager;

    private RadioGroup grupoRespuestas; //Grupo para los radioButtons de las respuestas
    private Button siguiente; //Boton para pasar a la siguiente pregunta
    //private Button anterior; //Boton para volver a la pregunta anterior (probablemente haya que quitarlo)

    private String[][] preguntas; //Array de arrays, contiene los arrays donde en la pos 0 esta la pregunta y en el resto las respuestas
    private int idxPregunta = 0; //Indice de la pregunta actual
    private int soluciones[]; //Array con los indices de las soluciones correctas del array de arrays preguntas
    int aciertos, fallos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        leerItems(); //Metodo para leer los items de string.xml y poder usarlos en el MainActivity

        pregunta = (TextView) findViewById(R.id.pregunta);
        pregunta.setText(idxPregunta + "/10");

        acierto = (TextView) findViewById(R.id.acierto);
        acierto.setText(aciertos + "/10");

        fallo = (TextView) findViewById(R.id.fallo);
        fallo.setText(fallos + "/10");

        fragManager = getSupportFragmentManager();
        fragManager.beginTransaction().replace(R.id.fragmentContainerView, new ButtonFragment(), "FRAGMENT_QUESTION");
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
/*
    private void mostrarPreguntas(){
        grupoRespuestas.clearCheck(); //Limpia la respuesta seleccionada

        pregunta.setText(preguntas[idxPregunta][0]); //Escribe la pregunta actual en la pantalla
        for(int i = 0; i < idxRespuestas.length; i++){
            RadioButton resp = (RadioButton) findViewById(idxRespuestas[i]);
            resp.setText(preguntas[idxPregunta][i+1]); //Escribe la respuesta del indice correspondiente en la pantalla


        }
    }
*/
    private void comprobarRespuesta(){
        int respuestaSelec = grupoRespuestas.getCheckedRadioButtonId(); //Recoge el id de la respuesta marcada

        if(soluciones[idxPregunta] == respuestaSelec){ //Comprueba si la respuesta es correcta o erronea
            aciertos++;
        }else{
            fallos++;
        }
    }

    public String[][] getPreguntas(){
        return preguntas;
    }

    public int[] getSoluciones(){
        return soluciones;
    }

    public int getIdxPregunta(){
        return idxPregunta;
    }

    public void addAcierto(){
        aciertos++;
        acierto.setText(aciertos + "/10");
        addIdxPregunta();
    }

    public void addFallo(){
        fallos++;
        fallo.setText(fallos + "/10");
        addIdxPregunta();
    }

    public void addIdxPregunta(){
        idxPregunta++;
        pregunta.setText(idxPregunta + "/10");

        if(idxPregunta < 10) {
            FragmentTransaction fragTransaction = fragManager.beginTransaction();
            int fragType = 0 + (int) (Math.random() * 4);
            switch (fragType) {
                case 0:
                    fragTransaction.replace(R.id.fragmentContainerView, new RadiobuttonFragment(), "FRAGMENT_QUESTION");
                    break;
                case 1:
                    fragTransaction.replace(R.id.fragmentContainerView, new ButtonFragment(), "FRAGMENT_QUESTION");
                    break;
                case 2:
                    fragTransaction.replace(R.id.fragmentContainerView, new CheckboxFragment(), "FRAGMENT_QUESTION");
                    break;
                case 3:
                    fragTransaction.replace(R.id.fragmentContainerView, new SpinnerFragment(), "FRAGMENT_QUESTION");
                    break;
            }
            fragTransaction.commit();
        }else{
            Intent resultado = new Intent(ActivityPreguntas.this, Resultados.class);
            startActivity(resultado);
        }
    }
}