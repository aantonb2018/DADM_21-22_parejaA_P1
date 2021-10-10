package com.example.dadm_21_22_parejaa_p1_info;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MenuPrincipal extends AppCompatActivity {

    EditText inputTexto;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        getSupportActionBar().hide();

        // Inicializacion input y botón
        inputTexto = (EditText) findViewById(R.id.txt_nombre);
        btn = (Button) findViewById(R.id.btn_empezar);

        // Cuando se pulsa sobre el botón "empezar" se llama a este evento
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // si el campo de input está vacío se lanza un mensaje pidiendo el nombre
                if ((inputTexto.getText().toString()).isEmpty()){
                    Toast.makeText(MenuPrincipal.this,"Introduce un nombre", Toast.LENGTH_LONG).show();
                }
                else{
                    // en caso contrario, se pasa a la siguiente actividad
                    Intent empezar = new Intent(MenuPrincipal.this, ActivityPreguntas.class);
                    startActivity(empezar);
                }
            }
        });
    }

}