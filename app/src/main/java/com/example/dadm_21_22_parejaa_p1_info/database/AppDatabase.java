package com.example.dadm_21_22_parejaa_p1_info.database;

import android.content.Context;

import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;
import com.example.dadm_21_22_parejaa_p1_info.database.dao.ItemDAO;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//Base de datos per se, en su sexta version por diferentes actualizaciones que ha ido teniendo
@Database(entities = {
        PreguntasQuiz.class //Tabla de items
}, version = 6 //version de la db
)
public abstract class AppDatabase extends RoomDatabase {

    //Unica instancia activa
    private static volatile AppDatabase INSTANCE;

    //Operaciones del ItemDAO
    public abstract ItemDAO itemDAO();


    public static AppDatabase getInstance(Context context) {

        if(INSTANCE == null){

            INSTANCE = Room.databaseBuilder(context,AppDatabase.class,"checklist.db")

                    .allowMainThreadQueries()

                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
}
