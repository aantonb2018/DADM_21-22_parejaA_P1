package com.example.dadm_21_22_parejaa_p1_info.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;

import java.util.List;

/**
 * Las interfaces que hacen las operaciones en la base de datos
 * deben estar anotadas con @Dao
 */
@Dao
public interface ItemDAO {

    /**
     * con @Query podemos construir nuestras consultas a las tablas
     * @return Lista de Items
     */
    @Query("select * from PreguntasQuiz")
    List<PreguntasQuiz> getAll();

    @Query("select * from PreguntasQuiz where itemId = :itemId")
    PreguntasQuiz findById(int itemId);

    /**
     * inserta datos en la tabla
     * @param item item a insertar
     */
    @Insert
    void insert(PreguntasQuiz item);

    /**
     * actualiza un item en la tabla
     * @param item Item a actualizar
     */
    @Update
    void update(PreguntasQuiz item);

    /**
     * elimina un item de la tabla
     * @param item item a eliminar
     */
    @Delete
    void delete(PreguntasQuiz item);
}
