package com.example.dadm_21_22_parejaa_p1_info;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositorioPreguntas {

    private PreguntasDAO mPreguntasDAO;
    private LiveData<List<TablaPreguntas>> mTodasPreguntas;

    public RepositorioPreguntas(Application app){
        PreguntasBD bd = PreguntasBD.getInstance(app);
        mPreguntasDAO = bd.palabraDAO();
        mTodasPreguntas = mPreguntasDAO.todasPreguntas();
    }

    public LiveData<List<TablaPreguntas>> getmTodasPreguntas(){
        return mTodasPreguntas;
    }
}
