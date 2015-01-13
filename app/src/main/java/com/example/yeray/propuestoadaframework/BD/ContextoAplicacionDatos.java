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

        this.asigDao = new ObjectSet(Asignatura.class, this);
        this.profDao = new ObjectSet(Profesor.class, this);
        this.alumnoDao = new ObjectSet(Alumno.class, this);
        this.alumno_por_cursoDao = new ObjectSet(alumno_por_curso.class, this);
    }

}
