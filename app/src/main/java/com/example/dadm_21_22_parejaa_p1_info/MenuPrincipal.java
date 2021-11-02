package com.example.dadm_21_22_parejaa_p1_info;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    EditText inputTexto;
    Button btn_empezar;
    Button btn_config;
    Button btn_clas;
    MediaPlayer sfx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        // JUEGO EN PANTALLA COMPLETA
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inicializacion input y botones
        inputTexto = (EditText) findViewById(R.id.txt_nombre);
        btn_empezar = (Button) findViewById(R.id.btn_jugar);
        btn_config = (Button) findViewById(R.id.btn_ajustes);
        btn_clas = (Button) findViewById(R.id.btn_clasificacion);

        // definición de evento onClick del botón "JUGAR"
        btn_empezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // si el campo de input está vacío se lanza un mensaje avisando de que no se ha introducido un nombre
                if ((inputTexto.getText().toString()).isEmpty()){
                    Toast.makeText(MenuPrincipal.this,"No has introducido un nombre", Toast.LENGTH_LONG).show();
                }else{
                    // se pasa a la siguiente actividad
                    Intent empezar = new Intent(MenuPrincipal.this, ActivityPreguntas.class);

                    empezar.putExtra("nick", (inputTexto.getText().toString()));

                    startActivity(empezar);
                }
            }
        });

        // definición de evento onClick del botón "AJUSTES"
        btn_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent ajustes = new Intent(MenuPrincipal.this, Ajustes.class);
                startActivity(ajustes);

            }
        });

        // definición de evento onClick del botón "CLASIFICACIÓN"
        btn_clas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent irClas = new Intent(MenuPrincipal.this,ClasificacionActivity.class);
                startActivity(irClas);
                finish();
            }
        });
    }

}