package com.example.yeray.propuestoadaframework.BD;

import com.mobandme.ada.Entity;
import com.mobandme.ada.annotations.Table;
import com.mobandme.ada.annotations.TableField;


/**
 * Created by fran on 08/01/2015.
 */

@Table(name = "alumno_por_curso")
public class alumno_por_curso extends Entity {

    @TableField(name = "id_curso", datatype = DATATYPE_ENTITY_LINK, required = true)
    private Integer id_curso;
    @TableField(name = "id_Alumno", datatype = DATATYPE_ENTITY_LINK, required = true)
    private Integer id_Alumno;

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public Integer getId_Alumno() {
        return id_Alumno;
    }

    public void setId_Alumno(Integer id_Alumno) {
        this.id_Alumno = id_Alumno;
    }

    public alumno_por_curso(){
        super();
    }

    public alumno_por_curso(Integer id_curso, Integer id_Alumno){
        super();
        this.id_curso=id_curso;
        this.id_Alumno=id_Alumno;
    }
}

