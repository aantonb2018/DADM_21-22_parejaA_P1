package com.example.dadm_21_22_parejaa_p1_info.repository;

import com.example.dadm_21_22_parejaa_p1_info.database.dao.ItemDAO;
import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;

import java.util.List;

/**
 * creamos una clase que implemente ItemRepository
 */
public class PreguntasQuizRepositoryImpl implements PreguntasQuizRepository {
    /**
     * declaramos una variable global de nuestro DAO
     */
    ItemDAO dao;

    /**
     * necesitamos un constructor que recibe una instancia de nuestro dao
     * @param dao instancia de ItemDAO
     */
    public PreguntasQuizRepositoryImpl(ItemDAO dao) {
        this.dao = dao;
    }

    /**
     * obtiene la lista de todos los items
     * @return lista de items
     */
    @Override
    public List<PreguntasQuiz> getAllItems() {
        return dao.getAll();
    }

    /**
     * obtiene un Item filtrado por id
     * @param id del item
     * @return un item
     */
    @Override
    public PreguntasQuiz findItemById(int id) {
        return dao.findById(id);
    }

    /**
     * inserta un item en la db
     * @param item
     */
    @Override
    public void insertItem(PreguntasQuiz item) {
        dao.insert(item);
    }

    /**
     * actualiza un item en la db
     * @param item
     */
    @Override
    public void updateItem(PreguntasQuiz item) {
        dao.update(item);
    }

    /**
     * elimina un item de la db
     * @param item
     */
    @Override
    public void deleteItem(PreguntasQuiz item) {
        dao.delete(item);
    }
}
