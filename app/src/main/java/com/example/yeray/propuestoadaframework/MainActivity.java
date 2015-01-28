package com.example.yeray.propuestoadaframework;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.yeray.propuestoadaframework.BD.AdaptadorBD;
import com.example.yeray.propuestoadaframework.BD.Alumno;
import com.example.yeray.propuestoadaframework.BD.Asignatura;
import com.example.yeray.propuestoadaframework.BD.ContextoAplicacionDatos;
import com.example.yeray.propuestoadaframework.BD.Profesor;
import com.example.yeray.propuestoadaframework.BD.alumno_por_curso;
import com.mobandme.ada.exceptions.AdaFrameworkException;

import java.io.File;
import java.util.Date;


public class MainActivity extends Activity {

    ListView lista;
    private ContextoAplicacionDatos contexto;
    AdaptadorBD Adaptador;
    SimpleCursorAdapter cursorAdapter;
    File archivo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        archivo = getApplicationContext().getDatabasePath("database.db");

        try {
            contexto = new ContextoAplicacionDatos(this);
            if (archivo.exists() == false) {

                InsertarDatos();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Adaptador = new AdaptadorBD(this, contexto);
        Adaptador.open();

        MostrarAlumnos();
    }

    public void MostrarAlumnos() {
        Cursor cursor = Adaptador.ObtenerAlumnos();

        String[] columns = new String[]{"nombre", "apellido", "dni", "fecha_alta", "edad", "activo"
        };


        int[] to = new int[]{
                R.id.txtNombre_AL,
                R.id.txtApellidos_AL,
                R.id.txtDNI_AL,
                R.id.txtFecha_AL,
                R.id.txtEdadAL,
                R.id.txtActivoAL
        };

        cursorAdapter = new SimpleCursorAdapter(
                this, R.layout.alumnos_info,
                cursor,
                columns,
                to,
                0);

        lista = (ListView) findViewById(R.id.LvAlumnos);

        lista.setAdapter(cursorAdapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this, Asignaturas_alumnoActivity.class);
                i.putExtra("id", id);

                startActivity(i);

            }
        });

        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                final long posicion = id;
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("¿Desea eliminar este alumno?")
                        .setCancelable(true)
                        .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                try {
                                    Adaptador.borraralum(posicion);
                                    MostrarAlumnos();
                                } catch (AdaFrameworkException e) {
                                    e.printStackTrace();
                                }

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

                return true;
            }
        });

    }


    void InsertarDatos() throws AdaFrameworkException {

        // Insercion de Profesores, 5 profesores
        Profesor p = new Profesor();
        p.setDni("05369632W");
        p.setNombre("Pepe");
        p.setApellido("Balmaseda");
        p.setActivo(true);
        p.setEdad(45);
        p.setFecha_alta("13/01/2015");
        p.setNumero_horas_clase(4);

        contexto.profDao.add(p);
        contexto.profDao.save();

        Profesor p2 = new Profesor();
        p2.setDni("03698712Z");
        p2.setNombre("Pablo");
        p2.setApellido("Perez");
        p2.setActivo(true);
        p2.setEdad(50);
        p2.setFecha_alta("13/01/2015");
        p2.setNumero_horas_clase(2);

        contexto.profDao.add(p2);
        contexto.profDao.save();

        Profesor p3 = new Profesor();
        p3.setDni("012369874G");
        p3.setNombre("Juan");
        p3.setApellido("Muñoz");
        p3.setActivo(true);
        p3.setEdad(35);
        p3.setFecha_alta("13/01/2015");
        p3.setNumero_horas_clase(3);

        contexto.profDao.add(p3);
        contexto.profDao.save();

        Profesor p4 = new Profesor();
        p4.setDni("052287874A");
        p4.setNombre("Xusa");
        p4.setApellido("Gonzalez");
        p4.setActivo(true);
        p4.setEdad(42);
        p4.setFecha_alta("13/01/2015");
        p4.setNumero_horas_clase(4);

        contexto.profDao.add(p4);
        contexto.profDao.save();

        Profesor p5 = new Profesor();
        p5.setDni("4458545121H");
        p5.setNombre("Fermin");
        p5.setApellido("Gallardo");
        p5.setActivo(true);
        p5.setEdad(47);
        p5.setFecha_alta("13/01/2015");
        p5.setNumero_horas_clase(6);

        contexto.profDao.add(p5);
        contexto.profDao.save();

        Log.i("Info", "Profesores guardados con exito");

        //  Inserciones de Asignaturas, 10 Asignaturas
        Asignatura asi = new Asignatura();

        asi.setNombre("Programacion multimedia y dispositivos moviles");
        asi.setNumero_maximo_alumnos(5);
        asi.setPrecio_hora("5");
        asi.setProfesor(p);

        contexto.getAsigDao().add(asi);
        contexto.getAsigDao().save();

        Asignatura asi2 = new Asignatura();
        asi2.setNombre("Programacion de Servicios y Procesos");
        asi2.setNumero_maximo_alumnos(5);
        asi2.setPrecio_hora("5€");
        asi2.setProfesor(p);

        contexto.getAsigDao().add(asi2);
        contexto.getAsigDao().save();

        Asignatura asi3 = new Asignatura();

        asi3.setNombre("Acceso a Datos");
        asi3.setNumero_maximo_alumnos(5);
        asi3.setPrecio_hora("10€");
        asi3.setProfesor(p2);

        contexto.getAsigDao().add(asi3);
        contexto.getAsigDao().save();

        Asignatura asi4 = new Asignatura();

        asi4.setNombre("Sistemas de Gestion Empresarial");
        asi4.setNumero_maximo_alumnos(5);
        asi4.setPrecio_hora("10€");
        asi4.setProfesor(p2);

        contexto.getAsigDao().add(asi4);
        contexto.getAsigDao().save();

        Asignatura asi5 = new Asignatura();

        asi5.setNombre("Empresas e Iniciativa Empresarial");
        asi5.setNumero_maximo_alumnos(5);
        asi5.setPrecio_hora("25€");
        asi5.setProfesor(p3);

        contexto.getAsigDao().add(asi5);
        contexto.getAsigDao().save();

        Asignatura asi6 = new Asignatura();

        asi6.setNombre("Programacion");
        asi6.setNumero_maximo_alumnos(5);
        asi6.setPrecio_hora("12€");
        asi6.setProfesor(p3);

        contexto.getAsigDao().add(asi6);
        contexto.getAsigDao().save();

        Asignatura asi7 = new Asignatura();

        asi7.setNombre("Bases de Datos");
        asi7.setNumero_maximo_alumnos(5);
        asi7.setPrecio_hora("10€");
        asi7.setProfesor(p4);

        contexto.getAsigDao().add(asi7);
        contexto.getAsigDao().save();

        Asignatura asi8 = new Asignatura();

        asi8.setNombre("Desarrollo de Interfaces");
        asi8.setNumero_maximo_alumnos(5);
        asi8.setPrecio_hora("13€");
        asi8.setProfesor(p4);

        contexto.getAsigDao().add(asi8);
        contexto.getAsigDao().save();

        Asignatura asi9 = new Asignatura();

        asi9.setNombre("Entornos de Desarrollo");
        asi9.setNumero_maximo_alumnos(5);
        asi9.setPrecio_hora("9€");
        asi9.setProfesor(p5);

        contexto.getAsigDao().add(asi9);
        contexto.getAsigDao().save();

        Asignatura asi10 = new Asignatura();

        asi10.setNombre("Lenguaje de Marcas");
        asi10.setNumero_maximo_alumnos(5);
        asi10.setPrecio_hora("16€");
        asi10.setProfesor(p5);

        contexto.getAsigDao().add(asi10);
        contexto.getAsigDao().save();

        Log.i("Info", "Asignaturas Guardadas con exito");

        Date d = new Date();

        // Insercion Alumnos, 10 alumnos
        Alumno alu = new Alumno();
        alu.setDni("05296501S");
        alu.setNombre("Borja");
        alu.setApellido("Perez");
        alu.setActivo(true);
        alu.setEdad(25);
        alu.setFecha_alta(d);

        contexto.alumnoDao.add(alu);
        contexto.alumnoDao.save();

        Alumno alu2 = new Alumno();
        alu2.setDni("036369654N");
        alu2.setNombre("Juan");
        alu2.setApellido("Gonzalez");
        alu2.setActivo(true);
        alu2.setEdad(24);
        alu2.setFecha_alta(d);

        contexto.alumnoDao.add(alu2);
        contexto.alumnoDao.save();

        Alumno alu3 = new Alumno();
        alu3.setDni("012478596Z");
        alu3.setNombre("Adrian");
        alu3.setApellido("Muñiz");
        alu3.setActivo(true);
        alu3.setEdad(23);
        alu3.setFecha_alta(d);

        contexto.alumnoDao.add(alu3);
        contexto.alumnoDao.save();

        Alumno alu4 = new Alumno();
        alu4.setDni("025258985M");
        alu4.setNombre("Pedro");
        alu4.setApellido("Vallejo");
        alu4.setActivo(true);
        alu4.setEdad(18);
        alu4.setFecha_alta(d);

        contexto.alumnoDao.add(alu4);
        contexto.alumnoDao.save();

        Alumno alu5 = new Alumno();
        alu5.setDni("03698745A");
        alu5.setNombre("Alisa");
        alu5.setApellido("Muñoz");
        alu5.setActivo(true);
        alu5.setEdad(21);
        alu5.setFecha_alta(d);

        contexto.alumnoDao.add(alu5);
        contexto.alumnoDao.save();

        Alumno alu6 = new Alumno();
        alu6.setDni("01245785W");
        alu6.setNombre("Kevin");
        alu6.setApellido("Martinez");
        alu6.setActivo(true);
        alu6.setEdad(26);
        alu6.setFecha_alta(d);

        contexto.alumnoDao.add(alu6);
        contexto.alumnoDao.save();

        Alumno alu7 = new Alumno();
        alu7.setDni("445585411N");
        alu7.setNombre("Gimena");
        alu7.setApellido("Gil");
        alu7.setActivo(true);
        alu7.setEdad(21);
        alu7.setFecha_alta(d);

        contexto.alumnoDao.add(alu7);
        contexto.alumnoDao.save();

        Alumno alu8 = new Alumno();
        alu8.setDni("444857512V");
        alu8.setNombre("Francisco");
        alu8.setApellido("Herrero");
        alu8.setActivo(true);
        alu8.setEdad(22);
        alu8.setFecha_alta(d);

        contexto.alumnoDao.add(alu8);
        contexto.alumnoDao.save();

        Alumno alu9 = new Alumno();
        alu9.setDni("444745121Q");
        alu9.setNombre("Jorge");
        alu9.setApellido("Hernaez");
        alu9.setActivo(true);
        alu9.setEdad(19);
        alu9.setFecha_alta(d);

        contexto.alumnoDao.add(alu9);
        contexto.alumnoDao.save();

        Alumno alu10 = new Alumno();
        alu10.setDni("4448995312M");
        alu10.setNombre("Javier");
        alu10.setApellido("Pizarroso");
        alu10.setActivo(true);
        alu10.setEdad(26);
        alu10.setFecha_alta(d);

        contexto.alumnoDao.add(alu10);
        contexto.alumnoDao.save();

        Log.i("Info", "Alumnos guadardados con exito");


        //Inserciones de Alumnos por Asignatura
        alumno_por_curso por_curso = new alumno_por_curso();

        por_curso.setDni(alu.getDni());
        por_curso.setNombre(asi.getNombre());

        contexto.getAlumno_por_cursoDao().add(por_curso);
        contexto.getAlumno_por_cursoDao().save();

        alumno_por_curso por2 = new alumno_por_curso();

        por2.setDni(alu2.getDni());
        por2.setNombre(asi2.getNombre());

        contexto.getAlumno_por_cursoDao().add(por2);
        contexto.getAlumno_por_cursoDao().save();

        alumno_por_curso por3 = new alumno_por_curso();

        por3.setDni(alu3.getDni());
        por3.setNombre(asi3.getNombre());

        contexto.getAlumno_por_cursoDao().add(por3);
        contexto.getAlumno_por_cursoDao().save();

        alumno_por_curso por4 = new alumno_por_curso();

        por4.setDni(alu4.getDni());
        por4.setNombre(asi4.getNombre());

        contexto.getAlumno_por_cursoDao().add(por4);
        contexto.getAlumno_por_cursoDao().save();

        alumno_por_curso por5 = new alumno_por_curso();

        por5.setDni(alu5.getDni());
        por5.setNombre(asi5.getNombre());

        contexto.getAlumno_por_cursoDao().add(por5);
        contexto.getAlumno_por_cursoDao().save();

        alumno_por_curso por6 = new alumno_por_curso();

        por6.setDni(alu6.getDni());
        por6.setNombre(asi6.getNombre());

        contexto.getAlumno_por_cursoDao().add(por6);
        contexto.getAlumno_por_cursoDao().save();

        alumno_por_curso por7 = new alumno_por_curso();

        por7.setDni(alu7.getDni());
        por7.setNombre(asi7.getNombre());

        contexto.getAlumno_por_cursoDao().add(por7);
        contexto.getAlumno_por_cursoDao().save();

        alumno_por_curso por8 = new alumno_por_curso();

        por8.setDni(alu8.getDni());
        por8.setNombre(asi8.getNombre());

        contexto.getAlumno_por_cursoDao().add(por8);
        contexto.getAlumno_por_cursoDao().save();

        alumno_por_curso por9 = new alumno_por_curso();

        por9.setDni(alu9.getDni());
        por9.setNombre(asi9.getNombre());

        contexto.getAlumno_por_cursoDao().add(por9);
        contexto.getAlumno_por_cursoDao().save();

        alumno_por_curso por10 = new alumno_por_curso();

        por10.setDni(alu10.getDni());
        por10.setNombre(asi10.getNombre());

        contexto.getAlumno_por_cursoDao().add(por10);
        contexto.getAlumno_por_cursoDao().save();

        Log.i("Info", "Todo guardado con exito");

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

            Intent i = new Intent(MainActivity.this, InsertarAlumnoActivity.class);
            startActivityForResult(i, 1);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
