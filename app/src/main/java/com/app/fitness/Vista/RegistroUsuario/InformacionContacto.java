package com.app.fitness.Vista.RegistroUsuario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.fitness.GlobalState;
import com.app.fitness.Modelo.Registro.DatosRegistro;
import com.app.fitness.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class InformacionContacto extends AppCompatActivity {

    GlobalState gs;

    DatosRegistro datosRegistro;
    ArrayList<String> localidades;

    private Spinner spTipo;
    private EditText etPassword, etIdentificacion, etNombres, etApellidos, etEmail, etMovil;
    private AutoCompleteTextView etDepartamento, etLocalidad;

    private Button btnRegresar, btnSiguiente;

    RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_contacto);

        gs = (GlobalState) getApplication();
        datosRegistro = gs.getDatosRegistro();

        request = Volley.newRequestQueue(this);

        initView();

        listarLocalidades();
        cargarTipos();
    }

    private void initView(){

        etPassword = findViewById(R.id.etPassword);

        spTipo = findViewById(R.id.spTipo);
        etIdentificacion = findViewById(R.id.etIdentificacion);
        etNombres = findViewById(R.id.etNombres);
        etApellidos = findViewById(R.id.etApellidos);
        etEmail = findViewById(R.id.etEmail);
        etMovil = findViewById(R.id.etMovil);

        etDepartamento = findViewById(R.id.etLocalidad);
        etLocalidad = findViewById(R.id.etLocalidad);

        btnRegresar = findViewById(R.id.btnRegresar);
        btnSiguiente = findViewById(R.id.btnSiguiente);
    }

    public void regresar(View view) {

    }

    public void siguiente(View view) {

        if (validarDatos()) {
            Intent intent = new Intent(this, InformacionUsuario.class);
            startActivity(intent);
        }
        else{

        }
    }

    private boolean validarDatos() {

        String password = etPassword.getText().toString().trim();

        String tipo = spTipo.getSelectedItem().toString();
        String identificacion = etIdentificacion.getText().toString().trim();
        String nombres = etNombres.getText().toString().trim();
        String apellidos = etApellidos.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String movil = etMovil.getText().toString().trim();

        String ciudad[] = etLocalidad.getText().toString().trim().split(",");

        if(validarCampo(password) && validarCampo(identificacion) && validarCampo(nombres)
                && validarCampo(apellidos) && validarCampo(email) & validarCampo(movil)){
            datosRegistro.setPassword(password);
            datosRegistro.setTipo(tipo);
            datosRegistro.setIdentificacion(identificacion);
            datosRegistro.setNombres(nombres);
            datosRegistro.setApellidos(apellidos);
            datosRegistro.setEmail(email);
            datosRegistro.setMovil(movil);

            datosRegistro.setCiudad(ciudad[0]);

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

    private void cargarTipos(){
        ArrayList<String> tipo = new ArrayList<>();

        tipo.add("Cedula de ciudadania");
        tipo.add("Tarjeta de identidad");

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, tipo);

        spTipo.setAdapter(adapter);
    }

    private void cargarLocalidades(){

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, localidades);
        etLocalidad.setAdapter(adapter);
    }

    private void listarLocalidades() {

        String url = gs.getIp() + "/Localidad/listar_localidades.php";
        url = url.replace(" ", "%20");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray datos = response.optJSONArray("localidad");
                JSONObject jsonObject = null;
                try {
                    localidades = new ArrayList<>();
                    for(int i=0;i<datos.length();i++){
                        jsonObject = datos.getJSONObject(i);

                        localidades.add(jsonObject.optString("localidad"));
                    }
                    cargarLocalidades();
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                detectarError(error);
            }
        });
        request.add(jsonObjectRequest);
    }

    private void detectarError(VolleyError error){
        if (error instanceof AuthFailureError){
            Log.e("VOLLEY", "Se ha producido un fallo con las credenciales. " + error.getMessage() );
        } else if (error instanceof NetworkError) {
            Log.e("VOLLEY", "Se ha producido un fallo en la red. "+ error.getMessage());
        } else if (error instanceof NoConnectionError) {
            Log.e("VOLLEY", "Se ha producido un fallo en la conexión. "+ error.getMessage());
        } else if (error instanceof TimeoutError) {
            Log.e("VOLLEY", "Tiempo de espera. "+ error.getMessage());
        }
    }
}
