package com.example.dadm_21_22_parejaa_p1_info.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Tabla de estructura de las preguntas
@Entity(tableName = "PreguntasQuiz")
public class PreguntasQuiz {

    //Clave primaria que funciona como id, se genera automaticamente al introducir el item en la BD
    @PrimaryKey(autoGenerate = true)
    private int itemId;

    //Columna de obligado valor que se corresponde con el indice de la respuesta
    @NonNull
    @ColumnInfo(name = "respuesta")
    private int respuesta;

    //Columna de obligado valor que se corresponde con la pregunta
    @NonNull
    @ColumnInfo(name = "pregunta")
    private String pregunta;

    //Columna de obligado valor que se corresponde con la primera opcion de respuesta
    @NonNull
    @ColumnInfo(name = "opcion1")
    private String opcion1;

    //Columna de obligado valor que se corresponde con la segunda opcion de respuesta
    @NonNull
    @ColumnInfo(name = "opcion2")
    private String opcion2;

    //Columna de obligado valor que se corresponde con la tercera opcion de respuesta
    @NonNull
    @ColumnInfo(name = "opcion3")
    private String opcion3;

    //Columna de obligado valor que se corresponde con la cuarta opcion de respuesta
    @NonNull
    @ColumnInfo(name = "opcion4")
    private String opcion4;

    //Columna de obligado valor que se corresponde con el indice que marca el tipo de multimedia asociado
    @NonNull
    @ColumnInfo(name = "tipo")
    private int tipo;

    //Columna de obligado valor que se corresponde con el id del archivo multimedia a mostrar
    @NonNull
    @ColumnInfo(name = "multimedia")
    private int multimedia;

    //Columna de obligado valor que se corresponde con la dificultad de la pregunta
    @NonNull
    @ColumnInfo(name = "nivel")
    private int nivel;


   //Getters y setters de cada columna de la tabla
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getRespuesta(){
        return respuesta;
    }

    public void setRespuesta(int respuesta){
        this.respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getOpcion1(){
        return opcion1;
    }

    public void setOpcion1(String opcion1){
        this.opcion1 = opcion1;
    }

    public String getOpcion2(){
        return opcion2;
    }

    public void setOpcion2(String opcion2){
        this.opcion2 = opcion2;
    }

    public String getOpcion3(){
        return opcion3;
    }

    public void setOpcion3(String opcion3){
        this.opcion3 = opcion3;
    }

    public String getOpcion4(){
        return opcion4;
    }

    public void setOpcion4(String opcion4){
        this.opcion4 = opcion4;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getMultimedia() {
        return multimedia;
    }

    public void setMultimedia(int multimedia) {
        this.multimedia = multimedia;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }


}
