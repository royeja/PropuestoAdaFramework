package com.example.yeray.propuestoadaframework;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.yeray.propuestoadaframework.BD.ContextoAplicacionDatos;


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
}
