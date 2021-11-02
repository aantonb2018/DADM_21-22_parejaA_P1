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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dadm_21_22_parejaa_p1_info.database.AppDatabase;
import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepository;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepositoryImpl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ButtonFragment extends Fragment {

    private int idxRespuestasButton[] = {
            R.id.button0, R.id.button1, R.id.button2, R.id.button3
    };

    private Button button[] = new Button[4];
    private TextView buttonQ;
    private ImageView buttonI;


    List<PreguntasQuiz> preguntasList;

    int idx;

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

        idx = ((ActivityPreguntas)getActivity()).getSeleccion();

        AppDatabase db = AppDatabase.getInstance(root.getContext());
        PreguntasQuizRepository repo = new PreguntasQuizRepositoryImpl(db.itemDAO());
        preguntasList = repo.getAllItems();

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
        if(preguntasList.get(idx).getRespuesta() == respuestaSelec+1){
            //Si la respuestaSeleccionada coincide con la almacenada en el array de soluciones...
            button[respuestaSelec].setBackgroundColor(Color.parseColor("#B5DD86"));
            ((ActivityPreguntas) getActivity()).addAcierto();
        }else{
            //si no es el caso...
            button[respuestaSelec].setBackgroundColor(Color.parseColor("#CC7474"));
            ((ActivityPreguntas) getActivity()).addFallo();
        }
    }

    public void mostrarPreguntas(){
        button[0].setText(preguntasList.get(idx).getOpcion1());
        button[1].setText(preguntasList.get(idx).getOpcion2());
        button[2].setText(preguntasList.get(idx).getOpcion3());
        button[3].setText(preguntasList.get(idx).getOpcion4());
    }
}