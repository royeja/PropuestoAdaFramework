package com.example.yeray.propuestoadaframework;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.yeray.propuestoadaframework.BD.AdaptadorBD;
import com.example.yeray.propuestoadaframework.BD.ContextoAplicacionDatos;

/**
 * Created by Yeray on 13/01/2015.
 */
public class Asignaturas_alumnoActivity extends Activity {

    ListView lista;
    private ContextoAplicacionDatos contexto;
    AdaptadorBD Adaptador;
    SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_asignaturas_alumno);
        try {
            contexto = new ContextoAplicacionDatos(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Adaptador = new AdaptadorBD(this, contexto);
        Adaptador.open();

        Bundle b = getIntent().getExtras();
        lista = (ListView) findViewById(R.id.LvAsignaturas);

        MostrarAsignaturasAlumno(b.getLong("id"));
    }


    public void MostrarAsignaturasAlumno(long d) {

        Cursor cursor = Adaptador.ObtenerAsignaturasAlumno(d);

        String[] columns = new String[]{"dni", "nombre"};


        int[] to = new int[]{
                R.id.txtNOMBRE_AL_ASIG,
                R.id.txtDNI_AL_ASIG,
        };

        cursorAdapter = new SimpleCursorAdapter(
                this, R.layout.asignaturas_alumno_info,
                cursor,
                columns,
                to,
                0);

        lista.setAdapter(cursorAdapter);


        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(Asignaturas_alumnoActivity.this, AsignaturaActivity.class);
                i.putExtra("id", id);

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
}