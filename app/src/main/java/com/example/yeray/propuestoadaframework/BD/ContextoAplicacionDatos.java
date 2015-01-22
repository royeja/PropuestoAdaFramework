package com.example.yeray.propuestoadaframework.BD;

import android.content.Context;

import com.mobandme.ada.ObjectContext;
import com.mobandme.ada.ObjectSet;

/**
 * Created by fran on 08/01/2015.
 */
public class ContextoAplicacionDatos extends ObjectContext {

    public ObjectSet<Asignatura> asigDao;
    public ObjectSet<Profesor> profDao;
    public ObjectSet<Alumno> alumnoDao;
    public ObjectSet<alumno_por_curso> alumno_por_cursoDao;

    public ContextoAplicacionDatos(Context pContext) throws Exception {
        super(pContext);

        this.asigDao = new ObjectSet<Asignatura>(Asignatura.class, this);
        this.profDao = new ObjectSet<Profesor>(Profesor.class, this);
        this.alumnoDao = new ObjectSet<Alumno>(Alumno.class, this);
        this.alumno_por_cursoDao = new ObjectSet<alumno_por_curso>(alumno_por_curso.class, this);
    }

    public ObjectSet<Asignatura> getAsigDao() {
        return asigDao;
    }

    public void setAsigDao(ObjectSet<Asignatura> asigDao) {
        this.asigDao = asigDao;
    }

    public ObjectSet<Profesor> getProfDao() {
        return profDao;
    }

    public void setProfDao(ObjectSet<Profesor> profDao) {
        this.profDao = profDao;
    }

    public ObjectSet<Alumno> getAlumnoDao() {
        return alumnoDao;
    }

    public void setAlumnoDao(ObjectSet<Alumno> alumnoDao) {
        this.alumnoDao = alumnoDao;
    }


    public ObjectSet<alumno_por_curso> getAlumno_por_cursoDao() {
        return alumno_por_cursoDao;
    }

    public void setAlumno_por_cursoDao(ObjectSet<alumno_por_curso> alumno_por_cursoDao) {
        this.alumno_por_cursoDao = alumno_por_cursoDao;
    }


}
