package com.example.dadm_21_22_parejaa_p1_info.database;

import android.content.Context;

import com.example.dadm_21_22_parejaa_p1_info.database.dao.ItemDAO;
import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * Singleton para acceder a la base de datos
 * Se debe anotar con @Database con los parametros entities: que es un arreglo de todas
 * nuestras clases anotadas con @Entity las cuales representan las tablas de nuesta db,
 * version es la versión actual de nuestra db
 */
@Database(entities = {
        PreguntasQuiz.class //Tabla de items
}, version = 2 //version de la db
)
public abstract class AppDatabase extends RoomDatabase {

    /**
     * declaramos de forma global una variable static de nuestra clase AppDatabase para
     * controlar que no existan mas de una instancia activa
     */
    private static volatile AppDatabase INSTANCE;

    /**
     * declaramos funciones abstract de nuestros daos para ejecutar las operaciones
     * en la db
     * @return DAO
     */
    public abstract ItemDAO itemDAO();

    /**
     * metodo static para obtener una instancia de esta clase
     * @param context el contexto en el cual se esta inicializando la instancia
     * @return devuelve la instancia de esta clase
     */
    public static AppDatabase getInstance(Context context) {
        /**si la instancia esta nula, creamos una nueva instancia*/
        if(INSTANCE == null){
            /**
             * para crear la instancia de Room necesitamos el context de la aplicacion,
             * la clase que tiene la logica para crear las tablas (@Entity) y las funcones
             * para acceder a los DAO y por último el nombre con el cual se creará el archivo
             * fisico de nuesta db
             */
            INSTANCE = Room.databaseBuilder(context,AppDatabase.class,"checklist.db")
                    /**
                     *para este ejemplo ejecutaremos nuestras consultas en el MainThread
                     * esta opcion no se debe utilizar en produccion.
                     */
                    .allowMainThreadQueries()
                    /**
                     * en este ejemplo no utilizaremos Migrations
                     * con este metodo la base de datos se recreara cada vez que actualicemos
                     * nuesta aplicación
                     */
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
}
