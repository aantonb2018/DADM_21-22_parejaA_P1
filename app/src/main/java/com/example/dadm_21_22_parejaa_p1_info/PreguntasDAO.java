package com.example.dadm_21_22_parejaa_p1_info;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PreguntasDAO {

    @Query("SELECT * from tabla_preguntas")
    List<TablaPreguntas> todasPreguntas();

    @Insert
    void insert(TablaPreguntas preguntas);
}
