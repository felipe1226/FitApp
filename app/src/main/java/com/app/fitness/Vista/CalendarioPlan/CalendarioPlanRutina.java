package com.app.fitness.Vista.CalendarioPlan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.app.fitness.Modelo.Calendario.Meses;
import com.app.fitness.R;
import com.app.fitness.Vista.Entreno.AdapterEjercicios;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjercicios;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaPlanRutinas;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CalendarioPlanRutina extends AppCompatActivity {

    private AdapterCalendarioPlan adapterCalendarioPlan;

    private ArrayList<ListaEjercicios> ejercicios;
    private ArrayList<ListaRutinas> rutinas;
    private ArrayList<ListaPlanRutinas> planRutinas;

    private AdapterEjercicios adapterEjercicios;

    private RecyclerView rvCalendario;
    private RecyclerView rvEjercicios;

    int diaIncial;
    int diaActual;
    String mes;
    int diasMes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_plan_rutina);

        int id = getIntent().getExtras().getInt("id");

        initView();

        generarEjercicios();
        generarRutinas();
        generarPlanesRutinas();

        try {
            fechaActual();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        generarCalendario(id);
    }

    private void initView(){

        rvCalendario = findViewById(R.id.rvCalendario);
        rvEjercicios = findViewById(R.id.rvEjercicios);
    }

    private void fechaActual() throws ParseException {

        Calendar c = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] fecha = dateFormat.format(c.getTime()).split("-");

        Meses meses = new Meses();
        meses.verificarBiciesto(Integer.parseInt(fecha[0]));

        c.set(Integer.parseInt(fecha[2]),Integer.parseInt(fecha[1]),1);
        int diaSemana =  c.get(Calendar.DAY_OF_WEEK);

        diaActual = Integer.parseInt(fecha[2]);
        diaIncial = diaSemana;
        mes = meses.getMes(Integer.parseInt(fecha[1]));
        diasMes = meses.getDias(Integer.parseInt(fecha[1]));
    }

    private void generarEjercicios(){
        ejercicios = new ArrayList<>();

        ejercicios.add(new ListaEjercicios(1,
                "Press pecho",
                4,
                10,
                "20 Kg",
                "30 Seg",
                "Agarrar la barra..."));

        ejercicios.add(new ListaEjercicios(2,
                "Press militar",
                4,
                8,
                "15 Kg",
                "45 Seg",
                "Agarrar la barra..."));

        ejercicios.add(new ListaEjercicios(3,
                "Banco plano",
                4,
                10,
                "25 Kg",
                "60 Seg",
                "Agarrar la barra..."));
    }

    private void generarRutinas(){
        rutinas = new ArrayList<>();

        ArrayList<ListaEjercicios> listaEjercicios = new ArrayList<>();

        listaEjercicios.add(ejercicios.get(0));
        listaEjercicios.add(ejercicios.get(1));

        rutinas.add(new ListaRutinas(1,
                "Pecho resistencia",
                "Metabolica",
                2,
                "70 min",
                listaEjercicios));

        listaEjercicios = new ArrayList<>();

        listaEjercicios.add(ejercicios.get(0));
        listaEjercicios.add(ejercicios.get(2));

        rutinas.add(new ListaRutinas(2,
                "Pierna intensivo",
                "Metabolica",
                3,
                "60 min",
                listaEjercicios));

        listaEjercicios = new ArrayList<>();

        listaEjercicios.add(ejercicios.get(1));
        listaEjercicios.add(ejercicios.get(2));

        rutinas.add(new ListaRutinas(3,
                "Espalda bombeo",
                "Metabolica",
                5,
                "50 min",
                listaEjercicios));

        listaEjercicios = new ArrayList<>();

        listaEjercicios.add(ejercicios.get(1));
        listaEjercicios.add(ejercicios.get(2));

        rutinas.add(new ListaRutinas(4,
                "Espalda bombeo",
                "Metabolica",
                30,
                "50 min",
                listaEjercicios));

        listaEjercicios = new ArrayList<>();

        listaEjercicios.add(ejercicios.get(1));
        listaEjercicios.add(ejercicios.get(2));

        rutinas.add(new ListaRutinas(5,
                "Espalda bombeo",
                "Metabolica",
                2,
                "50 min",
                listaEjercicios));
    }

    private void generarPlanesRutinas(){
        planRutinas = new ArrayList<>();

        ArrayList<ListaRutinas> listaRutinas = new ArrayList<>();

        listaRutinas.add(rutinas.get(0));
        listaRutinas.add(rutinas.get(1));
        listaRutinas.add(rutinas.get(2));
        listaRutinas.add(rutinas.get(3));
        listaRutinas.add(rutinas.get(4));

        planRutinas.add(new ListaPlanRutinas(1,
                "Reduccion de grasa",
                "Grasa localizada",
                10,
                listaRutinas));


        listaRutinas = new ArrayList<>();

        listaRutinas.add(rutinas.get(0));
        listaRutinas.add(rutinas.get(1));

        planRutinas.add(new ListaPlanRutinas(2,
                "Aumento de masa muscular",
                "Mejora de fuerza",
                8,
                listaRutinas));
    }

    private void generarCalendario(int id) {

        rutinas = new ArrayList<>();

        for (int i=0;i<planRutinas.size();i++){
            if(planRutinas.get(i).getId() == id){
                adapterCalendarioPlan = new AdapterCalendarioPlan(this, planRutinas.get(i).getRutinas(), diaActual, diaIncial, mes, diasMes);
                rvCalendario.setLayoutManager(new GridLayoutManager(this, 7));
                rvCalendario.setAdapter(adapterCalendarioPlan);
                break;
            }
        }
    }

    public void verEjercicios(ArrayList<ListaEjercicios> ejercicios){

        adapterEjercicios = new AdapterEjercicios(null, this, ejercicios);
        rvEjercicios.setLayoutManager(new GridLayoutManager(this, 1));
        rvEjercicios.setAdapter(adapterEjercicios);
    }
}
