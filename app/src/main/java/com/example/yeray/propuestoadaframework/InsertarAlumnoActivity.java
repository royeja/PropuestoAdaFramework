package com.example.yeray.propuestoadaframework;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.yeray.propuestoadaframework.BD.AdaptadorBD;
import com.example.yeray.propuestoadaframework.BD.ContextoAplicacionDatos;
import com.mobandme.ada.exceptions.AdaFrameworkException;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Yeray on 28/01/2015.
 */
public class InsertarAlumnoActivity extends Activity {

    EditText nombre;
    EditText apellido;
    EditText dni;
    EditText edad;
    Spinner activo;
    DatePicker fecha;
    Button insertar;
    private ContextoAplicacionDatos contexto;
    AdaptadorBD Adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insertar_alumno);

        try {
            contexto = new ContextoAplicacionDatos(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Adaptador = new AdaptadorBD(this, contexto);


        nombre = (EditText) findViewById(R.id.insertar_alumno_nombre);
        apellido = (EditText) findViewById(R.id.insertar_alumno_apellido);
        dni = (EditText) findViewById(R.id.insertar_alumno_dni);
        edad = (EditText) findViewById(R.id.insertar_alumno_edad);
        activo = (Spinner) findViewById(R.id.insertar_alumno_activo);
        fecha = (DatePicker) findViewById(R.id.insertar_alumno_fecha);
        insertar = (Button) findViewById(R.id.btnInsertar);

        ArrayList<String> datos = new ArrayList<>();
        datos.add("0");
        datos.add("1");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, datos);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activo.setAdapter(adapter);


        insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nom = nombre.getText().toString();
                String ape = apellido.getText().toString();
                String dnii = dni.getText().toString();
                int ed = Integer.parseInt(edad.getText().toString());
                Boolean act = false;

                if (activo.getSelectedItem().toString().equals("1")) {

                    act = true;
                }

                Date fech = new Date(fecha.getYear(), fecha.getMonth(), fecha.getDayOfMonth());

                try {
                    Adaptador.añadiralumno(nom, ape, dnii, ed, fech, act);
                } catch (AdaFrameworkException e) {
                    e.printStackTrace();
                }

            }
        });

        this.finish();

    }
}
