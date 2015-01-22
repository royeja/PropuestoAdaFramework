package com.example.yeray.propuestoadaframework.BD;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;


/**
 * Created by fran on 08/01/2015.
 */

@Table(name = "alumno_por_curso")
public class alumno_por_curso extends Entity {

    /*
    @TableField(name = "alumno", datatype =  DATATYPE_ENTITY_LINK)
    private Alumno alumnos;
    @TableField(name = "asignatura", datatype = DATATYPE_ENTITY_LINK)
    private Asignatura asignatura;
    */
    @TableField(name = "dni", datatype = DATATYPE_STRING, required = true)
    private String dni;
    @TableField(name = "nombre", datatype = DATATYPE_STRING, required = true)
    private String nombre;

    public alumno_por_curso(String nombre, String dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public alumno_por_curso() {

    }
}

