package com.app.fitness.Vista.RegistroUsuario;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.fitness.GlobalState;
import com.app.fitness.Modelo.Registro.DatosRegistro;
import com.app.fitness.R;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class InformacionFisica extends AppCompatActivity {

    GlobalState gs;

    DatosRegistro datosRegistro;

    private CheckBox cbCrossfit, cbGimnasio, cbPesoCorporal, cbCardio;
    private Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_fisica);

        gs = (GlobalState) getApplication();
        datosRegistro = gs.getDatosRegistro();

        cbCrossfit = findViewById(R.id.rbCrossfit);
        cbGimnasio = findViewById(R.id.rbGimnasio);
        cbPesoCorporal = findViewById(R.id.rbPesoCorporal);
        cbCardio = findViewById(R.id.rbCardio);

        btnRegistrar = findViewById(R.id.btnRegistrar);
    }

    public void registrar(View view) {

        if(validarDatos()){
            registrarUsuario();
        }
    }

    private boolean validarDatos(){

        String nivel = "Novato";
        String objetivo = "Subir masa muscular";
        String fuma = "No";

        ArrayList<String> entrenamientos = new ArrayList<>();

        if(cbCrossfit.isChecked()){
            entrenamientos.add("Crossfit");
        }
        if(cbGimnasio.isChecked()){
            entrenamientos.add("Gimnasio");
        }
        if(cbPesoCorporal.isChecked()){
            entrenamientos.add("Peso corporal");
        }
        if(cbCardio.isChecked()){
            entrenamientos.add("Cardio");
        }

        if(entrenamientos.size() > 0){

            datosRegistro.setNivelAcondicionamiento(nivel);
            datosRegistro.setObjetivo(objetivo);
            datosRegistro.setFuma(fuma);
            datosRegistro.setEntrenamientos(entrenamientos);

            gs.setDatosRegistro(datosRegistro);

            return true;
        }
        else{
            Toast.makeText(this, getString(R.string.toast_formulario_incompleto), Toast.LENGTH_SHORT).show();
        }
        return false;
    }

    private void registrarUsuario(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, gs.getIp() + "/Persona/registrar_persona.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("1")) {
                            Toast.makeText(getApplicationContext(), "Se ha registrado satisfactoriamente", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Error al registrar, intente de nuevo", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Error " + error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> params = new Hashtable<String, String>();

                params.put("instructor", datosRegistro.getInstructor());
                params.put("password", datosRegistro.getPassword());
                params.put("tipo", datosRegistro.getTipo());
                params.put("identificacion", datosRegistro.getIdentificacion());
                params.put("nombres", datosRegistro.getNombres());
                params.put("apellidos", datosRegistro.getApellidos());
                params.put("email", datosRegistro.getEmail());
                params.put("movil", datosRegistro.getMovil());
                params.put("ciudad", datosRegistro.getCiudad());
                params.put("peso", datosRegistro.getPeso());
                params.put("estatura", datosRegistro.getEstatura());
                params.put("fecha", datosRegistro.getFechaNacimiento());
                params.put("genero", datosRegistro.getGenero());
                params.put("nivel", datosRegistro.getNivelAcondicionamiento());
                params.put("objetivo", datosRegistro.getObjetivo());
                params.put("fuma", datosRegistro.getFuma());

                String entrenamientos = "";
                for(int i=0;i<datosRegistro.getEntrenamientos().size();i++){
                    if(i == 0){ entrenamientos = datosRegistro.getEntrenamientos().get(i); }
                    else{ entrenamientos += "," + datosRegistro.getEntrenamientos().get(i); }
                }
                params.put("entrenamientos", entrenamientos);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
