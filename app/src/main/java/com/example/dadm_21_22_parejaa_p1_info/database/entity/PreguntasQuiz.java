package com.example.dadm_21_22_parejaa_p1_info.database.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Las clases que representan nuestras tablas deben estar anotadas con @Entity
 * opcionalmente se puede establecer el nombre de la tabla si queremos que nuestra
 * tabla y nuestra clase tengan nombre diferentes
 */
@Entity(tableName = "PreguntasQuiz")
public class PreguntasQuiz {

    /**
     * Con Room todas nuestras tablas deben tener un primary key y la anotamos con @PrimaryKey
     * ademas podemos definir si esta será auto generada o no (true/false)
     */
    @PrimaryKey(autoGenerate = true)
    private int itemId;
    /**
     * @ColumnInfo: opcionalmente podemos establecer el nombre de la columna
     * por defecto la columna tendra el mismo nombre que la variable
     * @NoNull: columna no acepta nulos
     */

    /**
     * por defecto los tipos de datos primitivos no aceptan valores nulos
     * establecemos un valor por defecto en false
     */
    @NonNull
    @ColumnInfo(name = "respuesta")
    private int respuesta;

    @NonNull
    @ColumnInfo(name = "pregunta")
    private String pregunta;

    @NonNull
    @ColumnInfo(name = "opcion1")
    private String opcion1;

    @NonNull
    @ColumnInfo(name = "opcion2")
    private String opcion2;

    @NonNull
    @ColumnInfo(name = "opcion3")
    private String opcion3;

    @NonNull
    @ColumnInfo(name = "opcion4")
    private String opcion4;

    @NonNull
    @ColumnInfo(name = "tipo")
    private int tipo;

    @NonNull
    @ColumnInfo(name = "multimedia")
    private int multimedia;

    @NonNull
    @ColumnInfo(name = "nivel")
    private int nivel;


    /******************************************************************************
     * Importante no olvidar agregar los metodos get y set para cada columna
     *****************************************************************************/
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
