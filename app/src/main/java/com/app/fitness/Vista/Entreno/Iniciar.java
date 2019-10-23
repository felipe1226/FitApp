package com.app.fitness.Vista.Entreno;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.app.fitness.GlobalState;
import com.app.fitness.R;

public class Iniciar extends Fragment {

    GlobalState gs;

    Entrenar entrenar;

    public Button btnIniEjercicio, btnFinEjercicio, btnFinRutina;

    public TextView tvHoras, tvMinutos, tvSegundos;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gs = (GlobalState) getActivity().getApplicationContext();
        entrenar = gs.getEntrenar();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_iniciar, container, false);

        initView(v);

        return v;
    }

    private void initView(View v){

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
                entrenar.finalizarRutina();
            }
        });

    }

    public void actualizarTiempoTotal(String horas, String minutos, String segundos) {

        tvHoras.setText(horas);
        tvMinutos.setText(minutos);
        tvSegundos.setText(segundos);
    }

    private void iniciarEjercicio(){

        btnIniEjercicio.setVisibility(View.GONE);
        btnFinEjercicio.setVisibility(View.VISIBLE);

        entrenar.iniciarEjercicio();
    }

    private void finalizarEjercicio(){

        entrenar.finalizarEjercicio();

        btnIniEjercicio.setVisibility(View.VISIBLE);
        btnIniEjercicio.setEnabled(false);

        btnFinEjercicio.setVisibility(View.GONE);
        btnFinRutina.setEnabled(true);
    }

}
