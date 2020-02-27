package com.app.fitness.Vista.CalendarioPlan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fitness.GlobalState;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjerciciosPersona;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaPlanEntrenamientoPersona;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinasPersona;
import com.app.fitness.R;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjercicios;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaPlanRutinas;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinas;
import com.app.fitness.Vista.PagerAdapterCalendario;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class CalendarioPlanRutina extends AppCompatActivity {

    GlobalState gs;

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

    private ArrayList<ListaEjerciciosPersona> ejercicios;
    private ArrayList<ListaRutinasPersona> rutinas;
    private ArrayList<ListaPlanEntrenamientoPersona> planesEntrenamiento;

    private TextView tvRutina, tvMsgSeleccionaRutina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario_plan_rutina);

        gs = (GlobalState) getApplication();

        int id = getIntent().getExtras().getInt("id");

        initView();

        getPlanesEntrenamiento();
        getRutinas();
        getEjercicios();

        calendario1 = new Calendario(id, this, planesEntrenamiento);

        ejerciciosCalendario = new Ejercicios();
        ejerciciosCalendario.onStart();

        resumenRutina = new ResumenRutina();
        ejerciciosCalendario.onStart();

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
        PagerAdapterCalendario adapter = new PagerAdapterCalendario(getSupportFragmentManager());
        adapter.addFragment(calendario1, "Octubre");
        //adapter.addFragment(calendario2, "Noviembre");
        viewPagerMeses.setAdapter(adapter);

        adapter = new PagerAdapterCalendario(getSupportFragmentManager());
        adapter.addFragment(resumenRutina, tabRutina1);
        adapter.addFragment(ejerciciosCalendario, tabRutina2);
        viewPagerRutina.setAdapter(adapter);
    }

    private void getPlanesEntrenamiento(){

        planesEntrenamiento = new ArrayList<>();
        planesEntrenamiento = gs.getListaPlanEntrenamientoPersona();
    }

    private void getRutinas(){

        rutinas = new ArrayList<>();
        rutinas = gs.getListaRutinasPersona();
    }

    private void getEjercicios(){

        ejercicios = new ArrayList<>();
        ejercicios = gs.getListaEjerciciosPersona();
    }

    public void verEjercicios(ArrayList<ListaEjerciciosPersona> ejercicios, String dia, int idRutina, String rutina, int estado){

        tvRutina.setText(dia + "- " + rutina);

        switch (estado){
            case 0: tvRutina.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_calendario_rutina));
                    break;

            case 1: tvRutina.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_calendario_done));
                    break;

            case 2: tvRutina.setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.item_calendario_actual));
                    break;
        }
        ejerciciosCalendario.setEjercicios(ejercicios);
        ejerciciosCalendario.verEjercicios();

        resumenRutina.actualizarResumen(idRutina, estado);
        layout_resumen.setVisibility(View.VISIBLE);

        tvRutina.setVisibility(View.VISIBLE);

        tvMsgSeleccionaRutina.setVisibility(View.GONE);
    }

    public void ocultarEjercicios(){

        tvRutina.setVisibility(View.GONE);
        layout_resumen.setVisibility(View.GONE);
        tvMsgSeleccionaRutina.setVisibility(View.VISIBLE);
    }
}
