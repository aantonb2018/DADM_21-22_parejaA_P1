package com.example.dadm_21_22_parejaa_p1_info;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SpinnerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SpinnerFragment extends Fragment {

    private Spinner spinner;
    private TextView spinnerQ;
    private Button spinnerC;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SpinnerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SpinnerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SpinnerFragment newInstance(String param1, String param2) {
        SpinnerFragment fragment = new SpinnerFragment();
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
        View root = inflater.inflate(R.layout.fragment_spinner, container, false);

        spinnerQ = (TextView) root.findViewById(R.id.spinnerQ);
        spinnerQ.setText(((ActivityPreguntas)getActivity()).getPreguntas()[((ActivityPreguntas)getActivity()).getIdxPregunta()][0]);

        spinner = (Spinner) root.findViewById(R.id.spinner);

        spinnerC = (Button) root.findViewById(R.id.spinnerC);
        spinnerC.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                comprobarRespuesta();
            }
        });
        mostrarPreguntas();

        return root;
    }

    public void mostrarPreguntas(){
        String opcion0 = ((ActivityPreguntas)getActivity()).getPreguntas()[((ActivityPreguntas)getActivity()).getIdxPregunta()][1];
        String opcion1 = ((ActivityPreguntas)getActivity()).getPreguntas()[((ActivityPreguntas)getActivity()).getIdxPregunta()][2];
        String opcion2 = ((ActivityPreguntas)getActivity()).getPreguntas()[((ActivityPreguntas)getActivity()).getIdxPregunta()][3];
        String opcion3 = ((ActivityPreguntas)getActivity()).getPreguntas()[((ActivityPreguntas)getActivity()).getIdxPregunta()][4];

        String [] opcionesSpinner = {opcion0, opcion1, opcion2, opcion3};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, opcionesSpinner);
        spinner.setAdapter(adapter);
    }

    public void comprobarRespuesta(){
        int respuestaSelec = spinner.getSelectedItemPosition();

        if ((((ActivityPreguntas) getActivity()).getSoluciones()[((ActivityPreguntas) getActivity()).getIdxPregunta()]) == respuestaSelec + 1) {
            ((ActivityPreguntas) getActivity()).addAcierto();
        } else {
            ((ActivityPreguntas) getActivity()).addFallo();
        }
    }
}