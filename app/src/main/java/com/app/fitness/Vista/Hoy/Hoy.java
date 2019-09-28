package com.app.fitness.Vista.Hoy;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.fitness.R;

import java.util.ArrayList;

public class Hoy extends Fragment {

    private RecyclerView rvHoy;

    public Hoy() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_hoy, container, false);

        initView(v);

        listar();

        return v;
    }

    private void initView(View v) {

        rvHoy = v.findViewById(R.id.rvHoy);
    }

    private void listar(){
        ArrayList<String> lista = new ArrayList<>();

        lista.add("Plan1");
        lista.add("Plan2");

        AdapterHoy adapter = new AdapterHoy(getContext(), lista);
        rvHoy.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvHoy.setAdapter(adapter);
    }

}
