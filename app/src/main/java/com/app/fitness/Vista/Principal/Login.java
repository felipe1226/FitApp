package com.app.fitness.Vista.Principal;

import android.content.Intent;
import android.os.Bundle;

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
import com.app.fitness.Modelo.Persona.DatosPersona;
import com.app.fitness.R;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Login extends AppCompatActivity {

    GlobalState gs;

    EditText etEmail;
    EditText etPassword;

    Button btnLogin;

    TextView tvReestablecer;

    RequestQueue request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        gs = (GlobalState) getApplication();

        request = Volley.newRequestQueue(this);

        initView();
    }

    private void initView() {

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvReestablecer = findViewById(R.id.etEmail);
    }

    public void login(View view) {
        //validarDatos();
        ingresar();
    }

    private void reestablecer(View view){

    }

    private void validarDatos() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if(validarCampo(email) && validarCampo(password)){
            consultarUsuario(email, password);
        }
        else{
            Toast.makeText(this, "Complete los campos, por favor", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validarCampo(String cadena){
        if(!cadena.equalsIgnoreCase("")){
            return true;
        }
        return false;
    }

    private int calcularEdad(String fecha)  {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date dob = null;
        try {
            dob = dateFormat.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        GregorianCalendar cal = (GregorianCalendar) Calendar.getInstance();
        cal.setGregorianChange(new Date(Long.MIN_VALUE));
        cal.clear();
        cal.set(Calendar.YEAR, 0);
        cal.setTimeInMillis( cal.getTimeInMillis() + new Date().getTime() - dob.getTime());

        Formatter fmtr = new Formatter();
        if (cal.get(Calendar.YEAR) > 0) {
            fmtr.format("%d", cal.get(Calendar.YEAR));
        }

        return Integer.parseInt(fmtr.toString());
    }

    private void obtenerDatos(JSONObject jsonObject) {

        int edad = calcularEdad(jsonObject.optString("fecha_nacimiento"));

        DatosPersona datosPersona = new DatosPersona(jsonObject.optInt("id"),
                jsonObject.optString("descripcion"),
                jsonObject.optString("identificacion"),
                jsonObject.optString("nombres"),
                jsonObject.optString("apellidos"),
                jsonObject.optString("genero"),
                edad,
                jsonObject.optString("fecha_nacimiento"),
                jsonObject.optString("email"),
                jsonObject.optString("movil"),
                jsonObject.optString("ciudad"),
                jsonObject.optString("estado"));

        gs.setDatosPersona(datosPersona);

        ingresar();
    }

    private void ingresar(){
        Intent intent = new Intent(this, Navigation.class);
        startActivity(intent);
    }

    private void consultarUsuario(String email, String password){
        String url = gs.getIp() + "/Login/login_email.php?email=" + email + "&password=" + password;
        url = url.replace(" ", "%20");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray datos = response.optJSONArray("login");
                JSONObject jsonObject = null;
                try {
                    jsonObject = datos.getJSONObject(0);
                    if (!jsonObject.optString("id").equals("0")) {
                        obtenerDatos(jsonObject);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),getString(R.string.toast_usuario_invalido), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

