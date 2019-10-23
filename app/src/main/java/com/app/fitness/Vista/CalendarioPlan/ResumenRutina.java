package com.app.fitness.Vista.CalendarioPlan;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.fitness.R;
import com.app.fitness.Vista.Entreno.Entrenar;

public class ResumenRutina extends Fragment {

    private LinearLayout layout_acciones;

    private TextView tvMensaje;

    private Button btnPosponer;
    private Button btnEntrenar;

    public int idRutina;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        
        View v = inflater.inflate(R.layout.fragment_resumen_rutina, container, false);
        
        initView(v);
        
        return v;
    }

    private void initView(View v) {

        layout_acciones = v.findViewById(R.id.layout_acciones);

        tvMensaje = v.findViewById(R.id.tvMensaje);

        btnPosponer = v.findViewById(R.id.btnPosponer);
        btnPosponer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                posponer();
            }
        });

        btnEntrenar = v.findViewById(R.id.btnEntrenar);
        btnEntrenar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrenar();
            }
        });
    }

    private void posponer(){

    }

    private void entrenar(){

        Intent intent = new Intent(getContext(), Entrenar.class);
        intent.putExtra("rutina", idRutina);

        startActivity(intent);

    }

    public void actualizarResumen(int idRutina, int estado){
        this.idRutina = idRutina;

        switch (estado){
            case 0: layout_acciones.setVisibility(View.VISIBLE);
                    btnPosponer.setVisibility(View.VISIBLE);
                    btnEntrenar.setVisibility(View.GONE);
                    break;

            case 1: layout_acciones.setVisibility(View.GONE);
                    break;

            case 2: layout_acciones.setVisibility(View.VISIBLE);
                    btnEntrenar.setVisibility(View.VISIBLE);
                    btnEntrenar.setVisibility(View.VISIBLE);
                    break;
        }
    }
}