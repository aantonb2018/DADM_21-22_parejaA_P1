package com.example.dadm_21_22_parejaa_p1_info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ClasificacionActivity extends AppCompatActivity {

    private int idx_clas[] = {R.id.txt_rank1, R.id.txt_rank2, R.id.txt_rank3, R.id.txt_rank4, R.id.txt_rank5};
    private TextView tv_clas[] = new TextView[5];

    private String nicks[] = new String[5];
    private int puntos[] = new int[5];

    private Button ib_volverMenu;

    private RadioButton radioDif[] = new RadioButton[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasificacion);

        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        leerRankings(1);

        for(int i = 0; i < 5; i++){
            tv_clas[i] = (TextView) findViewById(idx_clas[i]);
            tv_clas[i].setText((i+1) + " - " + nicks[i] + " - " + puntos[i]);
        }

        radioDif[0] = (RadioButton) findViewById(R.id.radioBut_libre_clas);
        radioDif[1] = (RadioButton) findViewById(R.id.radioBut_facil_clas);
        radioDif[2] = (RadioButton) findViewById(R.id.radioBut_medio_clas);

        radioDif[1].setChecked(true);

        radioDif[0].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                viewTable(0);
            }
        });
        radioDif[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                viewTable(1);
            }
        });
        radioDif[2].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                viewTable(2);
            }
        });

        ib_volverMenu = (Button) findViewById(R.id.btn_volverMenu_clas);
        ib_volverMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent volverMenu = new Intent(ClasificacionActivity.this,MenuPrincipal.class);
                startActivity(volverMenu);
                finish();
            }
        });


    }

    private void leerRankings(int dificultad){
        SharedPreferences ajustes = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = ajustes.edit();

        switch(dificultad){
            case 0:
                for(int i = 0; i < 5; i++){
                    nicks[i] = ajustes.getString("rankingNick" + i,"");
                    puntos[i] = ajustes.getInt("rankingPunto" + i, -1);
                }
                break;
            case 1:
                for(int i = 0; i < 5; i++){
                    nicks[i] = ajustes.getString("rankingNickFacil" + i,"");
                    puntos[i] = ajustes.getInt("rankingPuntoFacil" + i, -1);
                }
                break;
            case 2:
                for(int i = 0; i < 5; i++){
                    nicks[i] = ajustes.getString("rankingNickMedio" + i,"");
                    puntos[i] = ajustes.getInt("rankingPuntoMedio" + i, -1);
                }
                break;
        }

    }

    private void viewTable(int i){
        leerRankings(i);
        for(int j = 0; j < 5; j++){
            tv_clas[j] = (TextView) findViewById(idx_clas[j]);
            tv_clas[j].setText((j+1) + " - " + nicks[j] + " - " + puntos[j]);
        }
    }
}