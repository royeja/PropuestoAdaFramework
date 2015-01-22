package com.example.yeray.propuestoadaframework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yeray.propuestoadaframework.BD.Alumno;
import com.example.yeray.propuestoadaframework.BD.Asignatura;
import com.example.yeray.propuestoadaframework.BD.ContextoAplicacionDatos;
import com.example.yeray.propuestoadaframework.BD.Profesor;
import com.mobandme.ada.Entity;
import com.mobandme.ada.exceptions.AdaFrameworkException;


public class MainActivity extends Activity {

    ListView lista;
    private ContextoAplicacionDatos contexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            contexto = new ContextoAplicacionDatos(this);
            contexto.profDao.fill("name");
        } catch (Exception e) {
            e.printStackTrace();
        }


        lista = (ListView) findViewById(R.id.LvAlumnos);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, AsignaturasActivity.class);

                startActivity(i);

            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.Nuevo_contacto) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void guardarcampos() throws AdaFrameworkException {
        contexto.alumnoDao.save();
        contexto.asigDao.save();
        contexto.profDao.save();
        contexto.alumno_por_cursoDao.save();
    }

    public void añadiralumno() throws AdaFrameworkException {
        Alumno al =new Alumno();
        al.setNombre("");
        al.setApellido("");
        al.setDni("");
        al.setEdad(5);
        al.setFecha_alta(null);
        al.setActivo(null);
        al.setStatus(Entity.STATUS_NEW);
        contexto.alumnoDao.add(al);
        guardarcampos();


    }
    public void añadirProfesor() throws AdaFrameworkException {
        Profesor p= new Profesor();
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
        a.setProfesor(15);
        a.setStatus(Entity.STATUS_NEW);
        contexto.asigDao.add(a);
        guardarcampos();
    }

    public void borraralum(int alum) throws AdaFrameworkException {
        Alumno a = new Alumno();
        a = contexto.alumnoDao.get(alum);
        a.setStatus(Entity.STATUS_DELETED);
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
    public void editaralumno(int alum) throws AdaFrameworkException{
        Alumno a = new Alumno();
        a = contexto.alumnoDao.get(alum);
        a.setNombre("");
        a.setApellido("");
        a.setDni("");
        a.setEdad(5);
        a.setFecha_alta(null);
        a.setActivo(null);
        a.setStatus(Entity.STATUS_UPDATED);
        guardarcampos();
    }
    public void editarasig(int asig) throws AdaFrameworkException {
        Asignatura as = new Asignatura();
        as = contexto.asigDao.get(asig);
        as.setNombre("");
        as.setNumero_maximo_alumnos(1);
        as.setPrecio_hora("");
        as.setProfesor(15);
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
