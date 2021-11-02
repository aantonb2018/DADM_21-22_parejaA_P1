package com.example.dadm_21_22_parejaa_p1_info.repository;

import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;

import java.util.List;

//Interfaz del repositorio de items
public interface PreguntasQuizRepository {

    List<PreguntasQuiz> getAllItems();
    List<PreguntasQuiz> getAllEasy();
    List<PreguntasQuiz> getAllMedium();
    PreguntasQuiz findItemById(int id);
    void insertItem(PreguntasQuiz item);
    void updateItem(PreguntasQuiz item);
    void deleteItem(PreguntasQuiz item);
}
