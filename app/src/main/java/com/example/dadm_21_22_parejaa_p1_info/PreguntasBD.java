package com.example.dadm_21_22_parejaa_p1_info;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TablaPreguntas.class}, version = 1)
public abstract class PreguntasBD extends RoomDatabase {

    private static PreguntasBD INSTANCIA;

    public abstract PreguntasDAO palabraDAO();

    public static synchronized PreguntasBD getInstance(final Context contexto){
        if (INSTANCIA == null){
            INSTANCIA = Room.databaseBuilder(contexto.getApplicationContext(),
                    PreguntasBD.class, "preguntas_bd").fallbackToDestructiveMigration().
                    addCallback(RoomDBCallback).build();
        }

        return INSTANCIA;
    }

    private static Callback RoomDBCallback = new Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDBAsyncTask (INSTANCIA).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void,Void,Void> {

        private PreguntasDAO palabraDAO;

        private PopulateDBAsyncTask(PreguntasBD bd){
            palabraDAO = bd.palabraDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            palabraDAO.insert(new TablaPreguntas(0,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(1,"¿Cómo se llamaba el padre de Natsu?","Furiyama","Inferno",
                    "Nacho","Igneel","Igneel"));

            palabraDAO.insert(new TablaPreguntas(2,"¿Cuál es el digimon de Tai?","Agumon","Patamon",
                    "Kakamon","Palmon","Agumon"));

            palabraDAO.insert(new TablaPreguntas(3,"¿En qué escuela estudia Hinata Shoyo?","Instituto Karasuno","Instituto Eleven",
                    "Instituto Kakugawa","Instituto Kaminari","Instituto Karasuno"));

            palabraDAO.insert(new TablaPreguntas(4,"En el pendiente de Hawk aparece escrito...","Deadly Pork","Star Boar",
                    "Fire Beast","Win Pig","Star Boar"));

            palabraDAO.insert(new TablaPreguntas(5,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(6,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(7,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(8,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(9,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(10,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(11,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(12,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(13,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(14,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(15,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(16,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(17,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(18,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            palabraDAO.insert(new TablaPreguntas(19,"¿Cuál es el sueño de Luffy?","Encontrar a su padre","Ser el Rey de los Piratas",
                    "Casarse con Nami","Comerse el jamón mas grande del mundo","Ser el Rey de los Piratas"));

            return null;
        }
    }
}
