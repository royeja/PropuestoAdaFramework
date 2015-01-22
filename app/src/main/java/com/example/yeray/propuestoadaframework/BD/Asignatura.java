package com.example.yeray.propuestoadaframework.BD;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

/**
 * Created by BrutslomPC on 08/01/2015.
 */
@Table(name = "asignatura")
public class Asignatura extends Entity {

    @TableField(name = "numero_maximo_alumnos", datatype = DATATYPE_INTEGER)
    private int numero_maximo_alumnos;

    @TableField(name = "nombre", datatype = DATATYPE_STRING, required = true)
    private String nombre;

    @TableField(name = "precio_hora", datatype = DATATYPE_STRING)
    private String precio_hora;

    @TableField(name = "id_profesor", datatype = DATATYPE_ENTITY_LINK)
    private Profesor profesor;

    public Asignatura() {
        super();
    }

    public int getNumero_maximo_alumnos() {
        return numero_maximo_alumnos;
    }

    public void setNumero_maximo_alumnos(int numero_maximo_alumnos) {
        this.numero_maximo_alumnos = numero_maximo_alumnos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio_hora() {
        return precio_hora;
    }

    public void setPrecio_hora(String precio_hora) {
        this.precio_hora = precio_hora;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

}