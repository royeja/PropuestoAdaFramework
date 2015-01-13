package com.example.yeray.propuestoadaframework.BD;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

/**
 * Created by BrutslomPC on 08/01/2015.
 */
@Table(name = "profesor")
public class Profesor extends Entity {
    @TableField(name = "numero_horas_clase", datatype = DATATYPE_INTEGER)
    private int numero_horas_clase;
    @TableField(name = "nombre", datatype = DATATYPE_STRING, required = true)
    private String nombre;
    @TableField(name = "apellido", datatype = DATATYPE_STRING, required = true)
    private String apellido;
    @TableField(name = "dni", datatype = DATATYPE_STRING)
    private String dni;
    @TableField(name = "fecha_alta", datatype = DATATYPE_DATE, required = true)
    private String fecha_alta;
    @TableField(name = "edad", datatype = DATATYPE_INTEGER)
    private int edad;
    @TableField(name = "activo", datatype = DATATYPE_BOOLEAN)
    private boolean activo;

    public Profesor() {
        super();
    }


    public int getNumero_horas_clase() {
        return numero_horas_clase;
    }

    public void setNumero_horas_clase(int numero_horas_clase) {
        this.numero_horas_clase = numero_horas_clase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
}

