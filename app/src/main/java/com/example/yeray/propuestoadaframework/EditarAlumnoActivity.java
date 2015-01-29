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
import com.example.yeray.propuestoadaframework.BD.Alumno;
import com.example.yeray.propuestoadaframework.BD.ContextoAplicacionDatos;
import com.mobandme.ada.Entity;
import com.mobandme.ada.exceptions.AdaFrameworkException;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by BrutslomPC on 29/01/2015.
 */
public class EditarAlumnoActivity extends Activity {

    EditText nombre;
    EditText apellido;
    EditText dni;
    EditText edad;
    Spinner activo;
    DatePicker fecha;
    Button editar;
    private ContextoAplicacionDatos contexto;
    AdaptadorBD Adaptador;
    Alumno a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_alumno);

        try {
            contexto = new ContextoAplicacionDatos(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Adaptador = new AdaptadorBD(this, contexto);
        Adaptador.open();


        nombre = (EditText) findViewById(R.id.editar_alumno_nombre);
        apellido = (EditText) findViewById(R.id.editar_alumno_apellido);
        dni = (EditText) findViewById(R.id.editar_alumno_dni);
        edad = (EditText) findViewById(R.id.editar_alumno_edad);
        activo = (Spinner) findViewById(R.id.editar_alumno_activo);
        fecha = (DatePicker) findViewById(R.id.editar_alumno_fecha);
        editar = (Button) findViewById(R.id.btnEditar);

        ArrayList<String> datos = new ArrayList<>();
        datos.add("0");
        datos.add("1");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, datos);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        activo.setAdapter(adapter);


        int pos = (int) getIntent().getExtras().getLong("pos");

        try {
            a = Adaptador.ObtenerAlumno(pos);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        nombre.setText(a.getNombre());
        apellido.setText(a.getApellido());
        dni.setText(a.getDni());
        edad.setText(a.getEdad().toString());
        if(a.getActivo()) activo.setSelection(1);
        else activo.setSelection(0);
        //fecha.updateDate(a.getFecha_alta().getYear(),a.getFecha_alta().getMonth(),a.getFecha_alta().getDay());



        editar.setOnClickListener(new View.OnClickListener() {
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
                    Adaptador.editaralumno(a,nom, ape, dnii, ed, fech, act);
                } catch (AdaFrameworkException e) {
                    e.printStackTrace();
                }

                finish();

            }
        });


    }
}