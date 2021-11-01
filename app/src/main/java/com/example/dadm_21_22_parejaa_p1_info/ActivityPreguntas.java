package com.example.dadm_21_22_parejaa_p1_info;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dadm_21_22_parejaa_p1_info.database.AppDatabase;
import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepository;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepositoryImpl;

import java.util.List;
import java.util.Random;


public class ActivityPreguntas extends AppCompatActivity {

    // variables para la BASE DE DATOS ------------
    /*
    private ViewModelPreguntas viewModelPreguntas;
    List<TablaPreguntas> listaPreguntas;
    TablaPreguntas preguntaActual;
    int pregID = 0;*/
    // --------------------------------------------

    private String nick;

    private TextView pregunta; //Texto que muestra el indice de pregunta
    private TextView acierto;  //Texto que muestra el numero de aciertos
    private TextView fallo;    //Texto que muestra el numero de fallos
    private FragmentManager fragManager;//, fragManager2;

    private RadioGroup grupoRespuestas; //Grupo para los radioButtons de las respuestas
    private Button siguiente; //Boton para pasar a la siguiente pregunta

    private String[][] preguntas; //Array de arrays, contiene los arrays donde en la pos 0 esta la pregunta y en el resto las respuestas
    private int idxPregunta = 0; //Indice de la pregunta actual
    private int soluciones[]; //Array con los indices de las soluciones correctas del array de arrays preguntas
    private int seleccion[]; //Indice de las preguntas seleccionadas
    int aciertos, fallos = 0;
    int longitud, dificultad;

    List<PreguntasQuiz> preguntasList;

    private int[] multimedia = {R.drawable.image0, R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4,
            R.drawable.image5,R.drawable.image6,R.drawable.image7,R.drawable.image8,R.drawable.image9,R.drawable.image10,
            R.drawable.image11,R.drawable.image12,R.drawable.image13,R.drawable.image14,R.drawable.image15,R.drawable.image16,
            R.drawable.image17,R.drawable.image18,R.drawable.image19,R.drawable.image20,R.drawable.image21,R.drawable.image22,
            R.drawable.image23,R.drawable.image24,R.drawable.image25,R.drawable.image26,R.drawable.image27,R.drawable.image28,
            R.drawable.image29, R.raw.audio30, R.raw.audio31, R.raw.audio32, R.raw.audio33, R.raw.audio34, R.raw.audio35,
            R.raw.audio36, R.raw.audio37, R.raw.audio38, R.raw.audio39, R.raw.audio40, R.raw.audio41, R.raw.audio42, R.raw.audio43,
            R.raw.video44, R.raw.video45, R.raw.video46, R.raw.video47, R.raw.video48, R.raw.video49, R.raw.video50};//Array con las imagenes asociadas al trivial

    //Resources resources = getResources();
    //Drawable imageId = resources.getDrawable(resources.getIdentifier(R.id.pregunta, "drawable", getPackageName()));
    int resourceId = R.drawable.image17;
    int resourceId2 = R.raw.audio35;
    int resourceId3 = R.raw.video45;

    MediaPlayer sfx_mal;
    MediaPlayer sfx_bien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);
        // JUEGO EN PANTALLA COMPLETA
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    /*
        viewModelPreguntas = ViewModelProviders.of(this).get(ViewModelPreguntas.class);
        viewModelPreguntas.get_todasPreguntasVIEWMODEL().observe(this, new Observer<List<TablaPreguntas>>() {
            @Override
            public void onChanged(List<TablaPreguntas> preguntas) {

                fetchPreguntas(preguntas);
            }
        });*/
        dificultad = 0;
        longitud = 10;

        SharedPreferences ajustes = getApplicationContext().getSharedPreferences("MyPref", 0);
        longitud = ajustes.getInt("key_num", 10);
        dificultad = ajustes.getInt("key_dif", 0);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            nick = extras.getString("nick");
        }



        seleccion = new int[longitud];

        AppDatabase db = AppDatabase.getInstance(this.getApplicationContext());

        /**
         * creamos una instancia del repositorio de items
         */
        PreguntasQuizRepository repo = new PreguntasQuizRepositoryImpl(db.itemDAO());

        /**
         * creamos un objeto de la tabla Item
         */
        //leerItems(); //Metodo para leer los items de string.xml y poder usarlos en el MainActivity

        //PreguntasQuiz pQ = new PreguntasQuiz();
        //pQ.setTipo(3);

        /*
        PreguntasQuiz[] pQ = new PreguntasQuiz[50];
        for(int i = 0; i < 50; i++){
            pQ[i] = new PreguntasQuiz();
            //pQ[i].setItemId(i);
            pQ[i].setPregunta(preguntas[i][0]);
            pQ[i].setRespuesta(soluciones[i]);
            pQ[i].setOpcion1(preguntas[i][1]);
            pQ[i].setOpcion2(preguntas[i][2]);
            pQ[i].setOpcion3(preguntas[i][3]);
            pQ[i].setOpcion4(preguntas[i][4]);
            if(i < 30){
                pQ[i].setTipo(0);
            }else if(i > 29 && i <44){
                pQ[i].setTipo(1);
            }else{
                pQ[i].setTipo(2);
            }
            pQ[i].setMultimedia(multimedia[i]);

            repo.insertItem(pQ[i]);
        }
        */
        /*
        PreguntasQuiz pQ = repo.findItemById(40);
        pQ.setOpcion1("Meliodas");
        repo.updateItem(pQ);*/


        preguntasList = repo.getAllItems();
        for(PreguntasQuiz i : preguntasList) {
            Log.d("Prueba Database", "Num Pregunta: " + i.getItemId() + ", Pregunta: " +
                    i.getPregunta() + ", Respuesta: " + i.getRespuesta() + ", Opcion1: " + i.getOpcion1() +
                    ", Opcion2: " + i.getOpcion2() + ", Opcion3: " + i.getOpcion3() + ", Opcion4: " + i.getOpcion4() +
                    ", Tipo: " + i.getTipo() + ", Id Multimedia: " + i.getMultimedia());
        }

        seleccionarPreguntas();

        Toast.makeText(ActivityPreguntas.this,"longitud " + longitud + " dificultad " + dificultad, Toast.LENGTH_LONG).show();

        sfx_bien = MediaPlayer.create(this,R.raw.correcto);
        sfx_mal = MediaPlayer.create(this,R.raw.incorrecto);

        /*
        Resources resources = root.getResources();
        Drawable imageId = resources.getDrawable(resources.getIdentifier(multimediaSource, "drawable", root.getContext().getPackageName()));
        iv.setImageDrawable(imageId);
         */


        //iv.setImageDrawable(imageId);

        pregunta = (TextView) findViewById(R.id.pregunta);
        pregunta.setText("-" + idxPregunta + "/" + longitud + "-");

        acierto = (TextView) findViewById(R.id.acierto);
        acierto.setText("-" + aciertos + "-");

        fallo = (TextView) findViewById(R.id.fallo);
        fallo.setText("-" + fallos + "-");

        fragManager = getSupportFragmentManager();
        fragManager.beginTransaction().replace(R.id.fragmentContainerView, new ButtonFragment(), "FRAGMENT_QUESTION");
        fragManager.beginTransaction().replace(R.id.fragmentContainerMultimedia, new ImageFragment(), "FRAGMENT_MULTIMEDIA");
        /*
        fragManager2 = getSupportFragmentManager();
        fragManager2.beginTransaction().replace(R.id.fragmentContainerMultimedia, new AudioFragment(), "FRAGMENT_MULTIMEDIA");
        */
    }

    /*
    private void fetchPreguntas(List<TablaPreguntas> preguntas){

        listaPreguntas = preguntas;

        Collections.shuffle(listaPreguntas);

        preguntaActual = listaPreguntas.get(pregID);


    }*/

    private void leerItems(){

        String auxPreguntas[] = getResources().getStringArray(R.array.test); //Obtenemos los items que son las preguntas y respuestas
        //auxPreguntas = randomizar(auxPreguntas);
        longitud = auxPreguntas.length;
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

    private void seleccionarPreguntas(){
        boolean sinRepetir = true;
        int numA = 0, numV = 0;
        switch(longitud){
            case 5:
                numA = 2;
                numV = 1;
                break;
            case 10:
                numA = 3;
                numV = 2;
                break;
            case 15:
                numA = 4;
                numV = 3;
        }

        for(int i = 0; i < longitud - (numA + numV); i++){
            do{
                sinRepetir = true;
                seleccion[i] = (int) Math.floor(Math.random()*(29-0+1)+0);
                for(int j = 0; j < i; j++){
                    if(seleccion[i] == seleccion[j]){
                        sinRepetir = false;
                    }
                }
            }while(sinRepetir == false);

        }
        for(int i = longitud - (numA + numV); i < longitud - numV; i++){
            do{
                sinRepetir = true;
                seleccion[i] = (int) Math.floor(Math.random()*(42-30+1)+30);
                for(int j = 0; j < i; j++){
                    if(seleccion[i] == seleccion[j]){
                        sinRepetir = false;
                    }
                }
            }while(sinRepetir == false);

        }
        for(int i = longitud - numV; i < longitud; i++){
            do{
                sinRepetir = true;
                seleccion[i] = (int) Math.floor(Math.random()*(49-43+1)+43);
                for(int j = 0; j < i; j++){
                    if(seleccion[i] == seleccion[j]){
                        sinRepetir = false;
                    }
                }
            }while(sinRepetir == false);

        }
        /*
        for (int i = longitud - 1; i > 1; i--)
        {
            Random random = new Random();

            int index = random.nextInt(i + 1);
            // Cambio
            int intTemp = seleccion[index];
            //int idTemp = imagenes[index];

            seleccion[index] = seleccion[i];
            //imagenes[index] = imagenes[i];

            seleccion[i] = intTemp;
            //imagenes[i] = idTemp;
        }*/
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
            //int idTemp = imagenes[index];

            aux[index] = aux[i];
            //imagenes[index] = imagenes[i];

            aux[i] = textoTemp;
            //imagenes[i] = idTemp;
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

    public int getSeleccion(){
        return seleccion[idxPregunta];
    }
/*
    public int[] getImagenes(){
        return imagenes;
    }*/

    public int getImageId(){
        return resourceId;
    }

    public int getAudioId(){
        return resourceId2;
    }

    public int getVideoId(){
        return resourceId3;
    }

    public void addAcierto(){
        aciertos++;
        sfx_bien.release();
        sfx_bien = null;
        sfx_bien = MediaPlayer.create(this,R.raw.correcto);
        sfx_bien.start();
        acierto.setText("-" + aciertos + "-");
        addIdxPregunta();
    }

    public void addFallo(){
        fallos++;
        sfx_mal.release();
        sfx_mal = null;
        sfx_mal = MediaPlayer.create(this,R.raw.incorrecto);
        sfx_mal.start();
        fallo.setText("-" + fallos + "-");
        addIdxPregunta();
    }

    public void addIdxPregunta(){
        idxPregunta++;
        pregunta.setText("-" + idxPregunta + "/" + longitud + "-");

        if(idxPregunta < longitud) {//Si no se esta en la ultima pregunta...
            FragmentTransaction fragTransaction = fragManager.beginTransaction();
            //FragmentTransaction fragTransaction2 = fragManager2.beginTransaction();
            int fragType = 0 + (int) (Math.random() * 4);
            switch (fragType) {//Se escoge aleatoriamente un tipo de pregunta para la siguiente
                case 0:
                    fragTransaction.replace(R.id.fragmentContainerView, new RadiobuttonFragment(), "FRAGMENT_QUESTION");
                    //fragTransaction.replace(R.id.fragmentContainerMultimedia, new VideoFragment(), "FRAGMENT_MULTIMEDIA");
                    break;
                case 1:
                    fragTransaction.replace(R.id.fragmentContainerView, new ButtonFragment(), "FRAGMENT_QUESTION");
                    //fragTransaction.replace(R.id.fragmentContainerMultimedia, new VideoFragment(), "FRAGMENT_MULTIMEDIA");
                    break;
                case 2:
                    fragTransaction.replace(R.id.fragmentContainerView, new CheckboxFragment(), "FRAGMENT_QUESTION");
                    //fragTransaction.replace(R.id.fragmentContainerMultimedia, new VideoFragment(), "FRAGMENT_MULTIMEDIA");
                    break;
                case 3:
                    //SPINNER
                    fragTransaction.replace(R.id.fragmentContainerView, new ListviewFragment(), "FRAGMENT_QUESTION");
                    //fragTransaction.replace(R.id.fragmentContainerMultimedia, new VideoFragment(), "FRAGMENT_MULTIMEDIA");
                    break;
            }
            fragType = preguntasList.get(seleccion[idxPregunta]).getTipo();
            switch (fragType) {//Se escoge aleatoriamente un tipo de pregunta para la siguiente
                case 0:
                    //fragTransaction.replace(R.id.fragmentContainerView, new RadiobuttonFragment(), "FRAGMENT_QUESTION");
                    fragTransaction.replace(R.id.fragmentContainerMultimedia, new ImageFragment(), "FRAGMENT_MULTIMEDIA");
                    break;
                case 1:
                    //fragTransaction.replace(R.id.fragmentContainerView, new ButtonFragment(), "FRAGMENT_QUESTION");
                    fragTransaction.replace(R.id.fragmentContainerMultimedia, new AudioFragment(), "FRAGMENT_MULTIMEDIA");
                    break;
                case 2:
                    //fragTransaction.replace(R.id.fragmentContainerView, new CheckboxFragment(), "FRAGMENT_QUESTION");
                    fragTransaction.replace(R.id.fragmentContainerMultimedia, new VideoFragment(), "FRAGMENT_MULTIMEDIA");
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