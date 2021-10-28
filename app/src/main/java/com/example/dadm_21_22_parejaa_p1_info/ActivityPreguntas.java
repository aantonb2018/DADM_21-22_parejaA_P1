package com.example.dadm_21_22_parejaa_p1_info;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;


public class ActivityPreguntas extends AppCompatActivity {

    // variables para la BASE DE DATOS ------------
    private ViewModelPreguntas viewModelPreguntas;
    List<TablaPreguntas> listaPreguntas;
    TablaPreguntas preguntaActual;
    int pregID = 0;
    // --------------------------------------------

    private String nick;

    private TextView pregunta; //Texto que muestra el indice de pregunta
    private TextView acierto;  //Texto que muestra el numero de aciertos
    private TextView fallo;    //Texto que muestra el numero de fallos
    private FragmentManager fragManager;

    private RadioGroup grupoRespuestas; //Grupo para los radioButtons de las respuestas
    private Button siguiente; //Boton para pasar a la siguiente pregunta

    private String[][] preguntas; //Array de arrays, contiene los arrays donde en la pos 0 esta la pregunta y en el resto las respuestas
    private int idxPregunta = 0; //Indice de la pregunta actual
    private int soluciones[]; //Array con los indices de las soluciones correctas del array de arrays preguntas
    int aciertos, fallos = 0;

    private int[] imagenes = {R.drawable.image0, R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,
            R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,
            R.drawable.image11,R.drawable.image12,R.drawable.image13,R.drawable.image14,R.drawable.image15,R.drawable.image16,
            R.drawable.image17,R.drawable.image18,R.drawable.image19,R.drawable.image20,R.drawable.image21,R.drawable.image22,
            R.drawable.image23,R.drawable.image24,R.drawable.image25,R.drawable.image26,R.drawable.image27,R.drawable.image28,
            R.drawable.image29};//Array con las imagenes asociadas al trivial

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        // JUEGO EN PANTALLA COMPLETA
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nick = extras.getString("nick");
        }

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
        auxPreguntas = randomizar(auxPreguntas);
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

    private void comprobarRespuesta(){
        int respuestaSelec = grupoRespuestas.getCheckedRadioButtonId(); //Recoge el id de la respuesta marcada

        if(soluciones[idxPregunta] == respuestaSelec){ //Comprueba si la respuesta es correcta o erronea
            aciertos++;
        }else{
            fallos++;
        }
    }

    private String[] randomizar(String[] aux)
    {
        //Cambia aleatoriamente las posiciones de las preguntas y las imagenes en sus arrays
        Random random = new Random();
        for (int i = aux.length - 1; i > 0; i--)
        {
            int index = random.nextInt(i + 1);
            // Cambio
            String textoTemp = aux[index];
            int idTemp = imagenes[index];

            aux[index] = aux[i];
            imagenes[index] = imagenes[i];

            aux[i] = textoTemp;
            imagenes[i] = idTemp;
        }

        return aux;
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

    public int[] getImagenes(){
        return imagenes;
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

        if(idxPregunta < 10) {//Si no se esta en la ultima pregunta...
            FragmentTransaction fragTransaction = fragManager.beginTransaction();
            int fragType = 0 + (int) (Math.random() * 4);
            switch (fragType) {//Se escoge aleatoriamente un tipo de pregunta para la siguiente
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

            resultado.putExtra("nick2", nick);//Envia el nick del jugador a la siguiente actividad
            resultado.putExtra("score", aciertos);//Envia la puntuacion a la siguiente actividad

            startActivity(resultado);//Inicializa la actividad de resultados
            finish();
        }
    }
}