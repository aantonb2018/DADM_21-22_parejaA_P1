package com.example.dadm_21_22_parejaa_p1_info.repository;

import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;
import com.example.dadm_21_22_parejaa_p1_info.database.dao.ItemDAO;

import java.util.List;

//Implementacion de los metodos del repositorio
public class PreguntasQuizRepositoryImpl implements PreguntasQuizRepository {

    ItemDAO dao;

    //Constructor
    public PreguntasQuizRepositoryImpl(ItemDAO dao) {
        this.dao = dao;
    }

    //Devuelve todos los items del repositorio
    @Override
    public List<PreguntasQuiz> getAllItems() {
        return dao.getAll();
    }

    //Devuelve todos los items de nivel:1 del repositorio
    @Override
    public List<PreguntasQuiz> getAllEasy() {
        return dao.getEasy();
    }

    //Devuelve todos los items de nivel:2 del repositorio
    @Override
    public List<PreguntasQuiz> getAllMedium() {
        return dao.getMedium();
    }

    //Devuelve un item dado su identificador
    @Override
    public PreguntasQuiz findItemById(int id) {
        return dao.findById(id);
    }

    //Inserta un item en el repositorio
    @Override
    public void insertItem(PreguntasQuiz item) {
        dao.insert(item);
    }

    //Actualiza un item de la BD
    @Override
    public void updateItem(PreguntasQuiz item) {
        dao.update(item);
    }

    //Elimina un item de la BD
    @Override
    public void deleteItem(PreguntasQuiz item) {
        dao.delete(item);
    }
}
