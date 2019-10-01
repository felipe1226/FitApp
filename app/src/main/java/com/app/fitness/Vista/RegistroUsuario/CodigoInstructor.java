package com.app.fitness.Vista.RegistroUsuario;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class CodigoInstructor extends AppCompatActivity {

    GlobalState gs;
    DatosRegistro datosRegistro;

    private EditText etCodigo;
    private Button btnVerificar, btnAceptar;

    private ConstraintLayout layout_informacion;
    private TextView tvIdentificacion, tvNombre, tvEmail, tvCiudad;

    boolean codigoCorrecto;

    RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo_instructor);

        gs = (GlobalState) getApplicationContext();
        datosRegistro = gs.getDatosRegistro();

        request = Volley.newRequestQueue(this);

        codigoCorrecto = false;

        initView();
    }

    private void initView() {

        etCodigo = findViewById(R.id.etCodigo);

        btnVerificar = findViewById(R.id.btnVerificar);

        layout_informacion = findViewById(R.id.layout_informacion);
        layout_informacion.setVisibility(View.GONE);
        tvIdentificacion = findViewById(R.id.tvIdentificacion);
        tvNombre = findViewById(R.id.tvNombre);
        tvEmail = findViewById(R.id.tvEmail);
        tvCiudad = findViewById(R.id.tvCiudad);

        btnAceptar = findViewById(R.id.btnAceptar);
    }

    public void verificar(View view) {
        String codigo = etCodigo.getText().toString().trim();
        if(!codigo.equalsIgnoreCase(""))
            consultarCodigo(codigo);
        else{
            Toast.makeText(getApplicationContext(), getString(R.string.toast_codigo_vacio), Toast.LENGTH_SHORT).show();
        }
    }

    public void aceptar(View view) {
        if(codigoCorrecto){
            registroUsuario();
        }
    }

    private void consultarCodigo(final String codigo) {

        String url = gs.getIp() + "/Instructor/verificar_codigo.php?codigo=" + codigo;
        url = url.replace(" ", "%20");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray datos = response.optJSONArray("instructor");
                JSONObject jsonObject = null;
                try {
                    jsonObject = datos.getJSONObject(0);
                    if (jsonObject.optString("id").compareTo("0") != 0) {
                        verInstructor(jsonObject);
                    }
                    else{
                        layout_informacion.setVisibility(View.GONE);
                        codigoCorrecto = false;
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_codigo_invalido), Toast.LENGTH_SHORT).show();
                    }
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

    private void verInstructor(JSONObject jsonObject){

        datosRegistro.setInstructor(jsonObject.optString("id"));
        String identificacion = jsonObject.optString("identificacion");
        String nombres = jsonObject.optString("nombres");
        String apellidos = jsonObject.optString("apellidos");
        String email = jsonObject.optString("email");
        String departamento = jsonObject.optString("departamento");
        String ciudad = jsonObject.optString("ciudad");

        tvIdentificacion.setText(identificacion);
        tvNombre.setText(nombres + " " + apellidos);
        tvEmail.setText(email);
        tvCiudad.setText(ciudad + ", " + departamento);

        gs.setDatosRegistro(datosRegistro);

        layout_informacion.setVisibility(View.VISIBLE);
        btnAceptar.setEnabled(true);
        codigoCorrecto = true;
    }

    private void registroUsuario(){
        Intent intent = new Intent(this, InformacionContacto.class);
        startActivity(intent);
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
