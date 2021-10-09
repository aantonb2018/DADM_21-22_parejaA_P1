package com.example.dadm_21_22_parejaa_p1_info;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.os.Bundle;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

public class MenuPrincipal extends AppCompatActivity {

    EditText inputTexto;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

//        inputTexto = (EditText) findViewById(R.id.txt_nombre);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (TextUtils.isEmpty(inputTexto.getText().toString())){
//                    Toast.makeText(MenuPrincipal.this,"Introduce un nombre", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    //Metodo para el boton empezar
    public void empezar(View view){
        Intent empezar = new Intent(this, ActivityPreguntas.class);
        startActivity(empezar);

    }
}