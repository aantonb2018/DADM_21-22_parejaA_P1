package com.example.dadm_21_22_parejaa_p1_info;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RepositorioPreguntas {

    private PreguntasDAO PreguntasDAO_repo;
    private LiveData<List<TablaPreguntas>> todasPreguntas_repo;

    public RepositorioPreguntas(Application app){
        PreguntasBD bd = PreguntasBD.getInstance(app);
        PreguntasDAO_repo = bd.palabraDAO();
        todasPreguntas_repo = PreguntasDAO_repo.todasPreguntas();
    }

    public LiveData<List<TablaPreguntas>> get_todasPreguntasREPO(){
        return todasPreguntas_repo;
    }
}
