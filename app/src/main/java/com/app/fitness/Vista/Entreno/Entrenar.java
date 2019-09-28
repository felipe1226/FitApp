package com.app.fitness.Vista.Entreno;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.fitness.R;

import java.util.ArrayList;

public class Entrenar extends Fragment {

    private HiloEntrenoEjercicio hiloEntrenoEjercicio;

    private Button btnDetalles, btnEntreno;
    public Button btnIniEjercicio, btnFinEjercicio, btnFinRutina;

    private ConstraintLayout layout_detalles;
    private LinearLayout layout_entreno;
    public TextView tvHoras, tvMinutos, tvSegundos;
    private ImageView ivMusculos;
    private RecyclerView rvMusculos, rvEjercicios;

    private AdapterEjercicios adapterEjercicios;

    private ArrayList<String> listaMusculos;

    private ArrayList<ListaEjercicios> ejercicios;

    RequestQueue request;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        request = Volley.newRequestQueue(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_entrenar, container, false);

        initView(v);

        listarMusculos();
        listarEjercicios();

        return v;
    }

    private void initView(View v) {
        layout_detalles = v.findViewById(R.id.layout_detalles);
        layout_entreno = v.findViewById(R.id.layout_entreno);
        layout_entreno.setVisibility(View.GONE);

        tvHoras = v.findViewById(R.id.tvHoras);
        tvMinutos = v.findViewById(R.id.tvMinutos);
        tvSegundos = v.findViewById(R.id.tvSegundos);

        btnIniEjercicio = v.findViewById(R.id.btnIniEjercicio);
        btnIniEjercicio.setEnabled(false);
        btnIniEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarEjercicio();
            }
        });

        btnFinEjercicio = v.findViewById(R.id.btnFinEjercicio);
        btnFinEjercicio.setVisibility(View.GONE);
        btnFinEjercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizarEjercicio();
            }
        });

        btnFinRutina = v.findViewById(R.id.btnFinRutina);
        btnFinRutina.setEnabled(false);
        btnFinRutina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalizarRutina();
            }
        });

        btnDetalles = v.findViewById(R.id.btnDetalles);
        btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_detalles.setVisibility(View.VISIBLE);
                layout_entreno.setVisibility(View.GONE);
            }
        });

        btnEntreno = v.findViewById(R.id.btnEntreno);
        btnEntreno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_detalles.setVisibility(View.GONE);
                layout_entreno.setVisibility(View.VISIBLE);
            }
        });

        ivMusculos = v.findViewById(R.id.ivMusculos);

        rvMusculos = v.findViewById(R.id.rvMusculos);
        rvMusculos.setNestedScrollingEnabled(false);

        rvEjercicios = v.findViewById(R.id.rvEjercicios);
        rvEjercicios.setItemAnimator(new DefaultItemAnimator());
    }

    private void iniciarEjercicio(){
        btnIniEjercicio.setVisibility(View.GONE);
        btnFinEjercicio.setVisibility(View.VISIBLE);

        adapterEjercicios.setEntrenando(true);

        hiloEntrenoEjercicio = new HiloEntrenoEjercicio(Entrenar.this);
        hiloEntrenoEjercicio.execute();
    }

    private void finalizarEjercicio(){

        hiloEntrenoEjercicio.finalizarEjercicio();
        adapterEjercicios.finalizarEjercicio();

        btnIniEjercicio.setVisibility(View.VISIBLE);
        btnIniEjercicio.setEnabled(false);

        btnFinEjercicio.setVisibility(View.GONE);
    }

    private void finalizarRutina(){
        hiloEntrenoEjercicio.onCancelled();
    }


    private void listarMusculos(){
        listaMusculos = new ArrayList<>();

        listaMusculos.add("Biceps");
        listaMusculos.add("Pectorales");
        listaMusculos.add("Triceps");

        AdapterMusculos adapterMusculos = new AdapterMusculos(getContext(), listaMusculos);
        rvMusculos.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvMusculos.setAdapter(adapterMusculos);
    }

    private void listarEjercicios(){
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

        adapterEjercicios = new AdapterEjercicios(this, getContext(), ejercicios);
        rvEjercicios.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvEjercicios.setAdapter(adapterEjercicios);
    }

    public void actualizarTiempoTotal(String horas, String minutos, String segundos) {

        tvHoras.setText(horas);
        tvMinutos.setText(minutos);
        tvSegundos.setText(segundos);
    }

    public void actualizarTiempoEjercicio(String horas, String minutos, String segundos) {

        adapterEjercicios.actualizarTiempo(horas, minutos, segundos);
    }
}
