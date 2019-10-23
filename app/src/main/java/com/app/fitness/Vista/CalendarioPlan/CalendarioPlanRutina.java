package com.app.fitness.Vista.CalendarioPlan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fitness.R;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjercicios;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaPlanRutinas;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinas;
import com.app.fitness.Vista.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class CalendarioPlanRutina extends AppCompatActivity {

    private ViewPager viewPagerMeses;
    private ViewPager viewPagerRutina;

    private static final String tabMeses1 = null;
    private static final String tabMeses2 = null;

    private static final String tabRutina1 = "Resumen";
    private static final String tabRutina2 = "Ejercicios";

    private TabLayout tabMeses;
    private TabLayout tabRutina;

    public Calendario calendario1;
    public Calendario calendario2;

    private LinearLayout layout_resumen;

    public Ejercicios ejerciciosCalendario;
    public ResumenRutina resumenRutina;

    private ArrayList<ListaEjercicios> ejercicios;
    private ArrayList<ListaRutinas> rutinas;
    private ArrayList<ListaPlanRutinas> planRutinas;

    private TextView tvRutina, tvMsgSeleccionaRutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_plan_rutina);

        int id = getIntent().getExtras().getInt("id");

        initView();

        generarEjercicios();
        generarRutinas();
        generarPlanesRutinas();

        calendario1 = new Calendario(id, this, planRutinas);
        calendario2 = new Calendario(id, this, planRutinas);

        ejerciciosCalendario = new Ejercicios();
        resumenRutina = new ResumenRutina();

        septupViewPager(viewPagerMeses, viewPagerRutina);
        tabMeses.setupWithViewPager(viewPagerMeses);
        tabRutina.setupWithViewPager(viewPagerRutina);
    }

    private void initView(){

        viewPagerMeses = findViewById(R.id.containerCalendario);
        viewPagerRutina = findViewById(R.id.container);

        tabMeses = findViewById(R.id.tab_meses);

        layout_resumen = findViewById(R.id.layout_resumen);

        tabRutina = findViewById(R.id.tab_page);

        tvRutina = findViewById(R.id.tvRutina);
        tvRutina.setVisibility(View.GONE);
        layout_resumen.setVisibility(View.GONE);

        tvMsgSeleccionaRutina = findViewById(R.id.tvMsgSeleccionaRutina);
    }

    private void septupViewPager(ViewPager viewPagerMeses, ViewPager viewPagerRutina) {
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(calendario1, "Octubre");
        adapter.addFragment(calendario2, "Noviembre");
        viewPagerMeses.setAdapter(adapter);

        adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(resumenRutina, tabRutina1);
        adapter.addFragment(ejerciciosCalendario, tabRutina2);
        viewPagerRutina.setAdapter(adapter);
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

    public void verEjercicios(ArrayList<ListaEjercicios> ejercicios, String dia, int idRutina, String rutina, int estado){

        tvRutina.setText(dia + "- " + rutina);

        switch (estado){
            case 0: tvRutina.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_calendario_rutina));
                    break;

            case 1: tvRutina.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_calendario_done));
                    break;

            case 2: tvRutina.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_calendario_actual));
                    break;
        }

        resumenRutina.actualizarResumen(idRutina, estado);
        layout_resumen.setVisibility(View.VISIBLE);

        ejerciciosCalendario.setEjercicios(ejercicios);
        ejerciciosCalendario.verEjercicios();

        tvRutina.setVisibility(View.VISIBLE);

        tvMsgSeleccionaRutina.setVisibility(View.GONE);
    }

    public void ocultarEjercicios(){

        tvRutina.setVisibility(View.GONE);
        layout_resumen.setVisibility(View.GONE);
        tvMsgSeleccionaRutina.setVisibility(View.VISIBLE);
    }
}
