package com.example.yeray.propuestoadaframework.BD;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;

import java.util.Date;

/**
 * Created by fran on 08/01/2015.
 */

@Table(name = "Alumno")
public class Alumno extends Entity{

    @TableField(name = "nombre", datatype = DATATYPE_TEXT, required = true)
    private String nombre;
    @TableField(name = "apellido", datatype = DATATYPE_TEXT, required = true)
    private String apellido;
    @TableField(name = "dni", datatype = DATATYPE_TEXT, required = true)
    private String dni;
    @TableField(name = "fecha_alta", datatype = DATATYPE_DATE, required = true)
    private Date fecha_alta;
    @TableField(name="edad",datatype = DATATYPE_INTEGER,required = true)
    private Integer edad;
    @TableField(name="activo",datatype = DATATYPE_BOOLEAN,required = true)
    private Boolean activo;

    public Alumno(){
        super();
    }

    public Alumno(String nombre,String apellido,String dni,Date fecha_alta,Integer edad,Boolean activo){
        super();
        this.nombre=nombre;
        this.apellido=apellido;
        this.dni=dni;
        this.fecha_alta=fecha_alta;
        this.edad=edad;
        this.activo=activo;
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

    public Date getFecha_alta() {
        return fecha_alta;
    }

    public void setFecha_alta(Date fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
}
