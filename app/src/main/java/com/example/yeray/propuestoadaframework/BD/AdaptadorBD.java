package com.example.yeray.propuestoadaframework.BD;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobandme.ada.Entity;
import com.mobandme.ada.exceptions.AdaFrameworkException;

import java.util.Date;

/**
 * Created by Yeray on 22/01/2015.
 */
public class AdaptadorBD {

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase mDb;

    private static final String DATABASE_NAME = "database.db";
    private static final int DATABASE_VERSION = 1;

    private final Context mCtx;
    private ContextoAplicacionDatos contexto;


    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    public AdaptadorBD(Context ctx, ContextoAplicacionDatos aplicacionDatos) {
        this.mCtx = ctx;
        this.contexto = aplicacionDatos;

    }

    public Cursor ObtenerAlumnos() {
        Cursor mCursor = mDb.rawQuery("Select DISTINCT id as _id, nombre, apellido, dni, fecha_alta, edad," +
                "activo FROM alumno ORDER BY nombre ASC", null);

                /*query("alumno", new String[] {KEY_ROWID,
                        "nombre","apellido","dni","fecha_alta","edad","activo"},
                null, null, null, null, null);*/

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor ObtenerAsignaturasAlumno(long id) {

        Cursor dniAlumno = mDb.rawQuery("Select dni FROM alumno" +
                " WHERE id=" + id, null);

        dniAlumno.moveToFirst();

        String dni = dniAlumno.getString(0);


        Cursor mCursor = mDb.rawQuery("Select id as _id, dni, nombre FROM alumno_por_curso" +
                " WHERE dni='" + dni + "'", null);


        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }

    public Cursor ObtenerDatosAsignatura(long id) {
        Cursor nombreAsignatura = mDb.rawQuery("Select nombre FROM alumno_por_curso" +
                " WHERE id=" + id, null);

        nombreAsignatura.moveToFirst();

        String asignatura = nombreAsignatura.getString(0);


        Cursor mCursor = mDb.rawQuery("Select DISTINCT id as _id, nombre, precio_hora, " +
                "numero_maximo_alumnos FROM asignatura" +
                " WHERE nombre='" + asignatura + "'", null);


        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }

    public Cursor ObtenerDatosProfesor(long id) {

        Cursor idProfesor = mDb.rawQuery("Select DISTINCT profesor_ID FROM LINK_asignatura_id_profesor_profesor" +
                " WHERE asignatura_ID=" + id, null);

        idProfesor.moveToFirst();

        String profe = idProfesor.getString(0);


        Cursor mCursor = mDb.rawQuery("Select DISTINCT id as _id, apellido, dni, " +
                "fecha_alta, nombre, activo, edad, numero_horas_clase FROM profesor" +
                " WHERE id='" + profe + "'", null);

        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;

    }


    public AdaptadorBD open() throws SQLException {
        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        if (mDbHelper != null) {
            mDbHelper.close();
        }
    }

    //--------------------------------------------------------


    public void guardarcampos() throws AdaFrameworkException {
        contexto.alumnoDao.save();
        contexto.asigDao.save();
        contexto.profDao.save();
        contexto.alumno_por_cursoDao.save();
    }

    public void añadiralumno(String nombre, String apellido, String dni, int edad, Date fecha, Boolean activo) throws AdaFrameworkException {
        Alumno al = new Alumno();
        al.setNombre(nombre);
        al.setApellido(apellido);
        al.setDni(dni);
        al.setEdad(edad);
        al.setFecha_alta(fecha);
        al.setActivo(activo);
        al.setStatus(Entity.STATUS_NEW);
        contexto.alumnoDao.add(al);
        guardarcampos();


    }

    public void añadirProfesor() throws AdaFrameworkException {
        Profesor p = new Profesor();
        p.setActivo(false);
        p.setNumero_horas_clase(1);
        p.setFecha_alta(null);
        p.setEdad(5);
        p.setDni(null);
        p.setNombre(null);
        p.setApellido(null);
        p.setStatus(Entity.STATUS_NEW);
        contexto.profDao.add(p);
        guardarcampos();
    }

    public void añadirAsignatura() throws AdaFrameworkException {
        Asignatura a = new Asignatura();
        a.setNombre("");
        a.setNumero_maximo_alumnos(1);
        a.setPrecio_hora("");
        a.setProfesor(null);
        a.setStatus(Entity.STATUS_NEW);
        contexto.asigDao.add(a);
        guardarcampos();
    }

    public void borraralum(long alum) throws AdaFrameworkException {
        Alumno a = new Alumno();
        a = contexto.alumnoDao.get((int) alum);
        a.setStatus(Entity.STATUS_DELETED);
        //contexto.alumnoDao.remove((int)alum);
        guardarcampos();
    }

    public void borrarasig(int asig) throws AdaFrameworkException {
        Asignatura as = new Asignatura();
        as = contexto.asigDao.get(asig);
        as.setStatus(Entity.STATUS_DELETED);
        guardarcampos();
    }

    public void borrarprof(int prof) throws AdaFrameworkException {
        Profesor p = new Profesor();
        p = contexto.profDao.get(prof);
        p.setStatus(Entity.STATUS_DELETED);
        guardarcampos();
    }

    public void editaralumno(Alumno al,String nombre, String apellido, String dni, int edad, Date fecha, Boolean activo) throws AdaFrameworkException {

        al.setNombre(nombre);
        al.setApellido(apellido);
        al.setDni(dni);
        al.setEdad(edad);
        al.setFecha_alta(fecha);
        al.setActivo(activo);
        al.setStatus(Entity.STATUS_UPDATED);
        guardarcampos();
    }

    public void editarasig(int asig) throws AdaFrameworkException {
        Asignatura as = new Asignatura();
        as = contexto.asigDao.get(asig);
        as.setNombre("");
        as.setNumero_maximo_alumnos(1);
        as.setPrecio_hora("");
        as.setProfesor(null);
        as.setStatus(Entity.STATUS_UPDATED);
        guardarcampos();
    }

    public void editarprof(int prof) throws AdaFrameworkException {
        Profesor p = new Profesor();
        p = contexto.profDao.get(prof);
        p.setActivo(false);
        p.setNumero_horas_clase(1);
        p.setFecha_alta(null);
        p.setEdad(5);
        p.setDni(null);
        p.setNombre(null);
        p.setApellido(null);
        p.setStatus(Entity.STATUS_UPDATED);
        guardarcampos();
    }


}