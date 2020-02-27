package com.app.fitness.Vista.Hoy;

import android.os.Bundle;

import androidx.annotation.ArrayRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjerciciosPersona;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaPlanEntrenamientoPersona;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinasPersona;
import com.app.fitness.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Hoy extends Fragment {

    GlobalState gs;

    private ArrayList<ListaEjerciciosPersona> ejerciciosPersona;
    private ArrayList<ListaRutinasPersona> rutinasPersona;
    private ArrayList<ListaPlanEntrenamientoPersona> planEntrenamientoPersona;

    private RecyclerView rvHoy;

    RequestQueue request;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gs = (GlobalState)getActivity().getApplication();
        request = Volley.newRequestQueue(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_hoy, container, false);

        initView(v);

        int idPersona = gs.datosPersona.getId();

        //consultarPlanesEntrenamientoPersona(idPersona);

        consultarEjerciciosPersona(idPersona);
        //consultarRutinasPersona(idPersona);

        return v;
    }

    private void initView(View v) {

        rvHoy = v.findViewById(R.id.rvHoy);
    }

    private void consultarEjerciciosPersona(final int idPersona){
        String url = gs.getIp() + "/Ejercicios/listar_ejercicios_persona.php?idPersona=" + idPersona;
        url = url.replace(" ", "%20");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray datos = response.optJSONArray("ejercicios");
                JSONObject jsonObject = null;
                try {
                    jsonObject = datos.getJSONObject(0);
                    if (!jsonObject.optString("id").equals("0")) {
                        ejerciciosPersona = new ArrayList<>();
                        for (int i = 0; i < datos.length(); i++) {
                            jsonObject = datos.getJSONObject(i);
                            setEjercicios(jsonObject);
                        }
                        gs.setListaEjerciciosPersona(ejerciciosPersona);
                        consultarRutinasPersona(idPersona);
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

    private void consultarRutinasPersona(int idPersona){
        String url = gs.getIp() + "/Rutinas/listar_rutinas_persona.php?idPersona=" + idPersona;
        url = url.replace(" ", "%20");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray datos = response.optJSONArray("rutinas");
                JSONObject jsonObject = null;
                try {
                    jsonObject = datos.getJSONObject(0);
                    if (!jsonObject.optString("id").equals("0")) {
                        rutinasPersona = new ArrayList<>();
                        for (int i = 0; i < datos.length(); i++) {
                            jsonObject = datos.getJSONObject(i);
                            setRutinas(jsonObject);
                        }
                        gs.setListaRutinasPersona(rutinasPersona);
                        generarPlanesRutinas();
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

    private void consultarPlanesEntrenamientoPersona(int idPersona){
        String url = gs.getIp() + "/PlanEntrenamiento/listar_planes_persona.php?idPersona=" + idPersona;
        url = url.replace(" ", "%20");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray datos = response.optJSONArray("plan");
                JSONObject jsonObject = null;
                try {
                    jsonObject = datos.getJSONObject(0);
                    if (!jsonObject.optString("id").equals("0")) {
                        planEntrenamientoPersona = new ArrayList<>();
                        for (int i = 0; i < datos.length(); i++) {
                            jsonObject = datos.getJSONObject(i);
                            generarPlanesRutinas();
                        }
                        gs.setListaPlanEntrenamientoPersona(planEntrenamientoPersona);
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

    private void setEjercicios(JSONObject jsonObject){

        int id = jsonObject.optInt("id");
        String nombre = jsonObject.optString("nombre");
        String descripcion = jsonObject.optString("descripcion");
        String foto= jsonObject.optString("foto");
        String tipo= jsonObject.optString("tipo");
        int series= jsonObject.optInt("series");
        int repeticiones= jsonObject.optInt("repeticiones");
        int tiempo= jsonObject.optInt("tiempo");
        int  idRutina= jsonObject.optInt("id_rutina");

        ejerciciosPersona.add(new ListaEjerciciosPersona(id,
                nombre,
                descripcion,
                foto,
                tipo,
                series,
                repeticiones,
                tiempo,
                idRutina));
    }

    private void setRutinas(JSONObject jsonObject){

        int id = jsonObject.optInt("id");
        String nombre = jsonObject.optString("nombre");
        String orientacion = jsonObject.optString("orientacion");
        int orden = jsonObject.optInt("orden");
        String idPlan = jsonObject.optString("id_plan_rutina");

        ArrayList<ListaEjerciciosPersona> ejercicios = new ArrayList<>();

        for(int i=0;i<ejerciciosPersona.size();i++){
            if(id == ejerciciosPersona.get(i).getIdRutina()){
                ejercicios.add(ejerciciosPersona.get(i));
            }
        }

        if(!idPlan.equals("null")){
            rutinasPersona.add(new ListaRutinasPersona(id,
                    nombre,
                    orientacion,
                    orden,
                    Integer.parseInt( idPlan )));
        }
        else{
            rutinasPersona.add(new ListaRutinasPersona(id,
                    nombre,
                    orientacion,
                    orden,
                    0));
        }
        rutinasPersona.get(rutinasPersona.size()-1).setEjercicios(ejercicios);
    }

    private void setPlanEntrenamiento(JSONObject jsonObject){

        /*ArrayList<ListaEjercicios> listaEjercicios = new ArrayList<>();

        listaEjercicios.add(ejercicios.get(0));
        listaEjercicios.add(ejercicios.get(1));

        rutinas.add(new ListaRutinas(1,
                "Pecho resistencia",
                "Metabolica",
                2,
                "70 min",
                listaEjercicios));*/
    }

    private void generarPlanesRutinas(){

        planEntrenamientoPersona = new ArrayList<>();




        planEntrenamientoPersona.add(new ListaPlanEntrenamientoPersona(1,
                "Reduccion de grasa",
                "Grasa localizada",
                10,
                rutinasPersona));

        gs.setListaPlanEntrenamientoPersona(planEntrenamientoPersona);

        AdapterHoy adapter = new AdapterHoy(getContext(), planEntrenamientoPersona);
        rvHoy.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvHoy.setAdapter(adapter);
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
