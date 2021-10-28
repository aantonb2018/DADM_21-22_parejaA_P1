package com.example.dadm_21_22_parejaa_p1_info;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_preguntas")
public class TablaPreguntas {

    // COLUMNAS --------------------------------
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "pregunta")
    private String pregunta;

    @ColumnInfo(name = "respuesta")
    private String respuesta;

    @ColumnInfo(name = "opcion1")
    private String opcion1;
    @ColumnInfo(name = "opcion2")
    private String opcion2;
    @ColumnInfo(name = "opcion3")
    private String opcion3;
    @ColumnInfo(name = "opcion4")
    private String opcion4;

    // CONSTRUCTORES -------------------------
    public TablaPreguntas() {
        id = 0;
        pregunta="";
        opcion1="";
        opcion2="";
        opcion3="";
        opcion4="";
        respuesta="";
    }

    public TablaPreguntas(int id, String pregunta, String opcion1, String opcion2, String opcion3, String opcion4, String respuesta) {
        this.id = id;
        this.pregunta = pregunta;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.opcion4 = opcion4;
        this.respuesta = respuesta;
    }

    // GETTERS Y SETTERS ------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(String opcion1) {
        this.opcion1 = opcion1;
    }

    public String getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(String opcion2) {
        this.opcion2 = opcion2;
    }

    public String getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(String opcion3) {
        this.opcion3 = opcion3;
    }

    public String getOpcion4() {
        return opcion4;
    }

    public void setOpcion4(String opcion4) {
        this.opcion4 = opcion4;
    }
}
