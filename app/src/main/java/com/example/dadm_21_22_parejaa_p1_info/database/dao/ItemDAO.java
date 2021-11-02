package com.example.dadm_21_22_parejaa_p1_info.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;

import java.util.List;


@Dao
public interface ItemDAO {

    //Devuelve toda la tabla de PreguntasQuiz
    @Query("select * from PreguntasQuiz")
    List<PreguntasQuiz> getAll();

    //Devuelve las preguntas de nivel 1 de la tabla de PreguntasQuiz
    @Query("select * from PreguntasQuiz where nivel = 1")
    List<PreguntasQuiz> getEasy();

    //Devuelve las preguntas de nivel 2 de la tabla de PreguntasQuiz
    @Query("select * from PreguntasQuiz where nivel = 2")
    List<PreguntasQuiz> getMedium();

    //Devuelve el elemento con el id introducido
    @Query("select * from PreguntasQuiz where itemId = :itemId")
    PreguntasQuiz findById(int itemId);

    //Introduce un item en la tabla
    @Insert
    void insert(PreguntasQuiz item);

    //Actualiza un item ya existente en la tabla
    @Update
    void update(PreguntasQuiz item);

    //Borra un elemento de la tabla
    @Delete
    void delete(PreguntasQuiz item);
}
