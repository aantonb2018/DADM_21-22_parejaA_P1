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
import android.widget.TextView;

import com.example.dadm_21_22_parejaa_p1_info.database.AppDatabase;
import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepository;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepositoryImpl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CheckboxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CheckboxFragment extends Fragment {

    private int idxRespuestasCheckbox[] = {
            R.id.checkbox0, R.id.checkbox1, R.id.checkbox2, R.id.checkbox3
    };

    private CheckBox checkbox[] = new CheckBox[4];

    List<PreguntasQuiz> preguntasList;

    int idx;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CheckboxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CheckboxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CheckboxFragment newInstance(String param1, String param2) {
        CheckboxFragment fragment = new CheckboxFragment();
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
        View root = inflater.inflate(R.layout.fragment_checkbox, container, false);

        idx = ((ActivityPreguntas)getActivity()).getSeleccion();

        AppDatabase db = AppDatabase.getInstance(root.getContext());
        PreguntasQuizRepository repo = new PreguntasQuizRepositoryImpl(db.itemDAO());
        preguntasList = repo.getAllItems();

        // CHECKBOXS ----------------------------------------------
        checkbox[0] = (CheckBox) root.findViewById(R.id.checkbox0);
        checkbox[1] = (CheckBox) root.findViewById(R.id.checkbox1);
        checkbox[2] = (CheckBox) root.findViewById(R.id.checkbox2);
        checkbox[3] = (CheckBox) root.findViewById(R.id.checkbox3);

        // ONCLICKS de los checkbox ------------------------------
        // Cuando se pulsa sobre uno se comprueba si es correcto o no y se pasa
        // directamente a la siguiente pregunta
        checkbox[0].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta();
            }
        });
        checkbox[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta();
            }
        });
        checkbox[2].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta();
            }
        });
        checkbox[3].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta();
            }
        });

        mostrarPreguntas();

        return root;
    }

    public void comprobarRespuesta(){
        int respuestaSelec = -1;
        boolean moreThan = false;
        for(int i = 0; i < idxRespuestasCheckbox.length; i++){
            if(checkbox[i].isChecked()){
                if(moreThan == false){
                    respuestaSelec = i;
                }else{
                    respuestaSelec = 5;
                }

            }
        }

        if(respuestaSelec != -1) {
            if (preguntasList.get(idx).getRespuesta() == respuestaSelec + 1) {
                checkbox[respuestaSelec].setTextColor(Color.parseColor("#B5DD86"));
                ((ActivityPreguntas) getActivity()).addAcierto();
            } else {
                checkbox[respuestaSelec].setTextColor(Color.parseColor("#CC7474"));
                ((ActivityPreguntas) getActivity()).addFallo();
            }
        }
    }

    public void mostrarPreguntas(){

        checkbox[0].setText(preguntasList.get(idx).getOpcion1());
        checkbox[1].setText(preguntasList.get(idx).getOpcion2());
        checkbox[2].setText(preguntasList.get(idx).getOpcion3());
        checkbox[3].setText(preguntasList.get(idx).getOpcion4());
    }
}