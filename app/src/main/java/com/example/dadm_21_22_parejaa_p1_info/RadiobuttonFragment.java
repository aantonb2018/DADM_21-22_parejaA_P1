package com.example.dadm_21_22_parejaa_p1_info;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.dadm_21_22_parejaa_p1_info.database.AppDatabase;
import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepository;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepositoryImpl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RadiobuttonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RadiobuttonFragment extends Fragment {

    private int idxRespuestasRadio[] = {
            R.id.radio0, R.id.radio1, R.id.radio2, R.id.radio3
    };

    private RadioButton radio[] = new RadioButton[4];


    List<PreguntasQuiz> preguntasList;

    int idx;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RadiobuttonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RadiobuttonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RadiobuttonFragment newInstance(String param1, String param2) {
        RadiobuttonFragment fragment = new RadiobuttonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_radiobutton, container, false);

        idx = ((ActivityPreguntas)getActivity()).getSeleccion();

        AppDatabase db = AppDatabase.getInstance(root.getContext());
        PreguntasQuizRepository repo = new PreguntasQuizRepositoryImpl(db.itemDAO());
        preguntasList = repo.getAllItems();

        // RADIOBUTTONS ----------------------------------------------
        radio[0] = (RadioButton) root.findViewById(R.id.radio0);
        radio[1] = (RadioButton) root.findViewById(R.id.radio1);
        radio[2] = (RadioButton) root.findViewById(R.id.radio2);
        radio[3] = (RadioButton) root.findViewById(R.id.radio3);

        // ONCLICKS de los radiobutton ------------------------------
        // Cuando se pulsa sobre uno se comprueba si es correcto o no y se pasa
        // directamente a la siguiente pregunta
        radio[0].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta();
            }
        });
        radio[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta();
            }
        });
        radio[2].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta();
            }
        });
        radio[3].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta();
            }
        });

        mostrarPreguntas();

        return root;
    }

    public void comprobarRespuesta(){

        int respuestaSelec = -1;
        //boolean moreThan = false;
        for(int i = 0; i < idxRespuestasRadio.length; i++){
            if(radio[i].isChecked()){
                respuestaSelec = i;
            }

        }

        if(respuestaSelec != -1) {
            if (preguntasList.get(idx).getRespuesta() == respuestaSelec + 1) {
                radio[respuestaSelec].setTextColor(Color.parseColor("#B5DD86"));
                ((ActivityPreguntas) getActivity()).addAcierto();
            } else {
                radio[respuestaSelec].setTextColor(Color.parseColor("#CC7474"));
                ((ActivityPreguntas) getActivity()).addFallo();
            }
        }
    }

    public void mostrarPreguntas(){

        radio[0].setText(preguntasList.get(idx).getOpcion1());
        radio[1].setText(preguntasList.get(idx).getOpcion2());
        radio[2].setText(preguntasList.get(idx).getOpcion3());
        radio[3].setText(preguntasList.get(idx).getOpcion4());
        /*
        for(int i = 0; i < radio.length; i++){
            radio[i].setText(((ActivityPreguntas)getActivity()).getPreguntas()[((ActivityPreguntas)getActivity()).getIdxPregunta()][i + 1]);//Escribe la respuesta del indice correspondiente en la pantalla
        }*/
    }
}