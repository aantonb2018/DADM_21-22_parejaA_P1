package com.example.dadm_21_22_parejaa_p1_info;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dadm_21_22_parejaa_p1_info.database.AppDatabase;
import com.example.dadm_21_22_parejaa_p1_info.database.entity.PreguntasQuiz;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepository;
import com.example.dadm_21_22_parejaa_p1_info.repository.PreguntasQuizRepositoryImpl;

import java.io.IOException;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AudioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioFragment extends Fragment {

    private TextView imageA;
    private ImageButton btnPlay;
    private ImageButton btnPause;
    private ImageButton btnStop;
    private MediaPlayer mediaplayer;

    List<PreguntasQuiz> preguntasList;

    int idx;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AudioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AudioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AudioFragment newInstance(String param1, String param2) {
        AudioFragment fragment = new AudioFragment();
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
        View root = inflater.inflate(R.layout.fragment_audio, container, false);

        idx = ((ActivityPreguntas)getActivity()).getSeleccion();

        AppDatabase db = AppDatabase.getInstance(root.getContext());
        PreguntasQuizRepository repo = new PreguntasQuizRepositoryImpl(db.itemDAO());
        preguntasList = repo.getAllItems();

        imageA = (TextView) root.findViewById(R.id.preguntaA);
        imageA.setText(preguntasList.get(idx).getPregunta());
        /*
        imageM = (ImageView) root.findViewById(R.id.imageM);
        imageM.setVisibility(View.VISIBLE);
        Resources rImages = root.getResources();
        Drawable idImages = rImages.getRaw(rImages.getIdentifier(((ActivityPreguntas) getActivity()).getImageId(), "raw", root.getContext().getPackageName()));
        imageM.setImageDrawable(idImages);
        */
        //Inicializamos la clase MediaPlayer asociandole el fichero de Audio
        /*
        int resID =   root.getResources().getIdentifier(((ActivityPreguntas) getActivity()).getAudioId(),"raw",root.getContext().getPackageName());

        root.getContext().getResources().openRawResource(resID);*/

        mediaplayer = MediaPlayer.create(getContext(), preguntasList.get(idx).getMultimedia());

        //Obtenemos los tres botones de la interfaz
        btnPlay= (ImageButton) root.findViewById(R.id.icPlay);
        btnStop= (ImageButton) root.findViewById(R.id.icStop);
        btnPause= (ImageButton) root.findViewById(R.id.icPause);

        //Y les asignamos el controlador de eventos

        btnPlay.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                audioManagement(0);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                audioManagement(2);
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                audioManagement(1);
            }
        });

        Toast.makeText(getActivity(),"Audio", Toast.LENGTH_LONG).show();

        return root;
    }

    public void audioManagement(int option) {
        //Comprobamos el identificador del boton que ha llamado al evento para ver
        //cual de los botones es y ejecutar la acción correspondiente
        switch(option){
            case 0:
                //Iniciamos el audio
                mediaplayer.start();
                break;
            case 1:
                //Pausamos el audio
                mediaplayer.pause();
                break;
            case 2:
                //Paramos el audio y volvemos a inicializar
                try {
                    mediaplayer.stop();
                    mediaplayer.prepare();
                    mediaplayer.seekTo(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;

        }
    }

}