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
public class InformacionProfesorActivity extends Activity {

    ListView lista;
    private ContextoAplicacionDatos contexto;
    AdaptadorBD Adaptador;
    SimpleCursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_informacion_profesor);


        try {
            contexto = new ContextoAplicacionDatos(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Adaptador = new AdaptadorBD(this, contexto);
        Adaptador.open();

        Bundle b = getIntent().getExtras();
        lista = (ListView) findViewById(R.id.LvInformacionProfesor);

        MostrarDatosProfesor(b.getLong("id"));


    }


    public void MostrarDatosProfesor(long d) {

        Cursor cursor = Adaptador.ObtenerDatosProfesor(d);

        String[] columns = new String[]{"nombre", "apellido", "dni", "edad", "fecha_alta", "activo", "numero_horas_clase"};


        int[] to = new int[]{
                R.id.nombre_PROFE,
                R.id.apellido_PROFE,
                R.id.dni_PROFE,
                R.id.edadPROFE,
                R.id.fecha_PROFE,
                R.id.activoPROFE,
                R.id.horasPROFE
        };

        cursorAdapter = new SimpleCursorAdapter(
                this, R.layout.profesor_info,
                cursor,
                columns,
                to,
                0);

        lista.setAdapter(cursorAdapter);

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