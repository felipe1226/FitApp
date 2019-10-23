package com.app.fitness.Vista.CalendarioPlan;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjercicios;
import com.app.fitness.R;
import com.app.fitness.Vista.Entreno.AdapterEjercicios;

import java.util.ArrayList;

public class Ejercicios extends Fragment {

    private RecyclerView rvEjercicios;

    private AdapterEjercicios adapterEjercicios;
    private ArrayList<ListaEjercicios> ejercicios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_ejercicios, container, false);

        initView(v);

        if(ejercicios != null){
            verEjercicios();
        }

        return v;
    }

    private void initView(View v) {

        rvEjercicios = v.findViewById(R.id.rvEjercicios);
    }

    public void verEjercicios(){
        if(rvEjercicios != null){
            adapterEjercicios = new AdapterEjercicios(null, getContext(), ejercicios);
            rvEjercicios.setLayoutManager(new GridLayoutManager(getContext(), 1));
            rvEjercicios.setAdapter(adapterEjercicios);
        }
    }

    public ArrayList<ListaEjercicios> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<ListaEjercicios> ejercicios) {
        this.ejercicios = ejercicios;
    }
}


































