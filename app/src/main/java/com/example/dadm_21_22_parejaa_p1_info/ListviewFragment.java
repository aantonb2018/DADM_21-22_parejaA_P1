package com.example.dadm_21_22_parejaa_p1_info;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.dadm_21_22_parejaa_p1_info.database.AppDatabase;
import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepository;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepositoryImpl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListviewFragment extends Fragment {

    private ListView lv;

    List<PreguntasQuiz> preguntasList;
    int idx;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ListviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListviewFragment newInstance(String param1, String param2) {
        ListviewFragment fragment = new ListviewFragment();
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
        View root = inflater.inflate(R.layout.fragment_listview, container, false);

        idx = ((ActivityPreguntas)getActivity()).getSeleccion();

        AppDatabase db = AppDatabase.getInstance(root.getContext());
        PreguntasQuizRepository repo = new PreguntasQuizRepositoryImpl(db.itemDAO());
        preguntasList = repo.getAllItems();

        String[] respuestas = {preguntasList.get(idx).getOpcion1(),preguntasList.get(idx).getOpcion2(),
        preguntasList.get(idx).getOpcion3(),preguntasList.get(idx).getOpcion4()};

        lv = (ListView) root.findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.list_item, respuestas);

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(preguntasList.get(idx).getRespuesta() == i+1){
                    lv.getChildAt(i).setBackgroundColor(Color.parseColor("#B5DD86"));
                    ((ActivityPreguntas) getActivity()).addAcierto();
                }else{
                    lv.getChildAt(i).setBackgroundColor(Color.parseColor("#CC7474"));
                    ((ActivityPreguntas) getActivity()).addFallo();
                }
            }
        });

        return root;
    }
}