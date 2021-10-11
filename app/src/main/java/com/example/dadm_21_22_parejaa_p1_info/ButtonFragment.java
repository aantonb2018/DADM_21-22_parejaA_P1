package com.example.dadm_21_22_parejaa_p1_info;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dadm_21_22_parejaa_p1_info.QuestionManager;
import com.example.dadm_21_22_parejaa_p1_info.ActivityPreguntas;
import com.example.dadm_21_22_parejaa_p1_info.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonFragment extends Fragment {

    private int idxRespuestasButton[] = {
            R.id.button0, R.id.button1, R.id.button2, R.id.button3
    };

    public QuestionManager qM;
    private Button button[] = new Button[4];
    private TextView buttonQ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ButtonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ButtonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ButtonFragment newInstance(String param1, String param2) {
        ButtonFragment fragment = new ButtonFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_button, container, false);

        buttonQ = (TextView) root.findViewById(R.id.buttonQ);
        buttonQ.setText(((ActivityPreguntas)getActivity()).getPreguntas()[((ActivityPreguntas)getActivity()).getIdxPregunta()][0]);

        button[0] = (Button) root.findViewById(R.id.button0);
        button[0].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta(0);
            }
        });

        button[1] = (Button) root.findViewById(R.id.button1);
        button[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta(1);
            }
        });

        button[2] = (Button) root.findViewById(R.id.button2);
        button[2].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta(2);
            }
        });

        button[3] = (Button) root.findViewById(R.id.button3);
        button[3].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta(3);
            }
        });

        mostrarPreguntas();

        return root;
    }

    public void comprobarRespuesta(int respuestaSelec){
        if((((ActivityPreguntas)getActivity()).getSoluciones()[((ActivityPreguntas) getActivity()).getIdxPregunta()]) == respuestaSelec+1){
            ((ActivityPreguntas) getActivity()).addAcierto();
        }else{
            ((ActivityPreguntas) getActivity()).addFallo();
        }
    }

    public void mostrarPreguntas(){
        buttonQ.setText(((ActivityPreguntas)getActivity()).getPreguntas()[((ActivityPreguntas)getActivity()).getIdxPregunta()][0]); //Escribe la pregunta actual en la pantalla

        for(int i = 0; i < button.length; i++){
            button[i].setText(((ActivityPreguntas)getActivity()).getPreguntas()[((ActivityPreguntas)getActivity()).getIdxPregunta()][i + 1]);//Escribe la respuesta del indice correspondiente en la pantalla
        }
    }
}