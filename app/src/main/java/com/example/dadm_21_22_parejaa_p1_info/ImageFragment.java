package com.example.dadm_21_22_parejaa_p1_info;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dadm_21_22_parejaa_p1_info.database.AppDatabase;
import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepository;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepositoryImpl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ImageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageFragment extends Fragment {

    private TextView imageQ;
    private ImageView imageM;

    List<PreguntasQuiz> preguntasList;

    int idx;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ImageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageFragment newInstance(String param1, String param2) {
        ImageFragment fragment = new ImageFragment();
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
        View root = inflater.inflate(R.layout.fragment_image, container, false);

        idx = ((ActivityPreguntas)getActivity()).getSeleccion();

        AppDatabase db = AppDatabase.getInstance(root.getContext());
        PreguntasQuizRepository repo = new PreguntasQuizRepositoryImpl(db.itemDAO());
        preguntasList = repo.getAllItems();

        // PREGUNTA ----------------------------------------------
        imageQ = (TextView) root.findViewById(R.id.preguntaA);
        imageQ.setText(preguntasList.get(idx).getPregunta());

        // IMAGEN ------------------------------------------------
        imageM = (ImageView) root.findViewById(R.id.imageM);
        imageM.setVisibility(View.VISIBLE);
        Resources rImages = root.getResources();
        //Drawable idImages = ((ActivityPreguntas) getActivity()).getImageId();
        //imageM.setImageDrawable(idImages);
        //imageM.setImageResource(((ActivityPreguntas)getActivity()).getImageId());
        imageM.setImageResource(preguntasList.get(idx).getMultimedia());

        Toast.makeText(getActivity(),"Imagen", Toast.LENGTH_LONG).show();

        return root;
        //return inflater.inflate(R.layout.fragment_image, container, false);
    }
}