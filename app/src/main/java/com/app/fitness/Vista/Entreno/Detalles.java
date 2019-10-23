package com.app.fitness.Vista.Entreno;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.app.fitness.R;

import java.util.ArrayList;

public class Detalles extends Fragment {

    private ImageView ivMusculos;
    private RecyclerView rvMusculos;

    private ArrayList<String> listaMusculos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_detalles, container, false);

        initView(v);

        listarMusculos();

        return v;
    }


    private void initView(View v){

        ivMusculos = v.findViewById(R.id.ivMusculos);

        rvMusculos = v.findViewById(R.id.rvMusculos);
        rvMusculos.setNestedScrollingEnabled(false);

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

}
