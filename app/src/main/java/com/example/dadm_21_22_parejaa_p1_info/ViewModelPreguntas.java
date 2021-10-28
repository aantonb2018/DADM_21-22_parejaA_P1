package com.example.dadm_21_22_parejaa_p1_info;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModelPreguntas extends AndroidViewModel {

    private RepositorioPreguntas repositorio;
    private LiveData<List<TablaPreguntas>> todasPreguntasVIEWMODEL;

    public ViewModelPreguntas(Application application) {
        super(application);

        repositorio = new RepositorioPreguntas(application);
        todasPreguntasVIEWMODEL = repositorio.get_todasPreguntasREPO();
    }

    LiveData<List<TablaPreguntas>> get_todasPreguntasVIEWMODEL(){
        return todasPreguntasVIEWMODEL;
    }
}
