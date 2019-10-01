package com.app.fitness.Vista.RegistroUsuario;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.fitness.GlobalState;
import com.app.fitness.Modelo.Registro.DatosRegistro;
import com.app.fitness.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InformacionUsuario extends AppCompatActivity {

    GlobalState gs;

    DatosRegistro datosRegistro;

    private EditText etPeso, etEstatura, etFecha;
    private RadioButton rbFemenino, rbMasculino;

    Calendar calendario = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_usuario);

        gs = (GlobalState) getApplication();
        datosRegistro =  gs.getDatosRegistro();

        initView();
    }

    private void initView(){etEstatura = findViewById(R.id.etEstatura);

        etPeso = findViewById(R.id.etPeso);

        rbFemenino = findViewById(R.id.rbFemenino);
        rbMasculino = findViewById(R.id.rbMasculino);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendario.set(Calendar.YEAR, year);
                calendario.set(Calendar.MONTH, month);
                calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                actualizarCampoFecha();
            }
        };

        etFecha = findViewById(R.id.etFecha);
        etFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(InformacionUsuario.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });}

    public void siguiente(View view) {

        if(validarDatos()){
            Intent intent = new Intent(this, InformacionFisica.class);
            startActivity(intent);
        }
        else{

        }
    }

    private boolean validarDatos() {

        String peso = etPeso.getText().toString().trim();
        String estatura = etEstatura.getText().toString().trim();
        String fecha = etFecha.getText().toString().trim();
        String genero = "";

        if(rbFemenino.isChecked()){ genero = "Femenino"; }
        else{
            if (rbMasculino.isChecked()){ genero = "Masculino"; }
        }

        if(validarCampo(peso) && validarCampo(estatura) && validarCampo(fecha) && validarCampo(genero)){
            datosRegistro.setPeso(peso);
            datosRegistro.setEstatura(estatura);
            datosRegistro.setFechaNacimiento(fecha);
            datosRegistro.setGenero(genero);

            gs.setDatosRegistro(datosRegistro);

            return true;
        }
        else{
            Toast.makeText(this, getString(R.string.toast_formulario_incompleto), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private boolean validarCampo(String texto){
        if(!texto.equalsIgnoreCase("")){
            return true;
        }
        return false;
    }

    private void actualizarCampoFecha() {
        String formatoDeFecha = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        etFecha.setText(sdf.format(calendario.getTime()));
    }
}
