package com.app.fitness.Vista.Perfil;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.app.fitness.R;
import com.app.fitness.Vista.HistoricoPeso.HistoricoPeso;
import com.app.fitness.Vista.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class Perfil extends Fragment {

    TabLayout tabPerfil;

    private ViewPager viewPager;

    private ImageView ivFoto;
    private ImageButton ibMenu;

    public Informe informe = new Informe();
    public ResumenPeriodo resumenPeriodo = new ResumenPeriodo();
    public HistoricoPeso historicoPeso = new HistoricoPeso();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        initView(v);

        return v;
    }

    private void initView(View v) {

        ivFoto = v.findViewById(R.id.ivFoto);
        ibMenu = v.findViewById(R.id.ibMenu);

        tabPerfil = v.findViewById(R.id.tab_perfil);

        viewPager = v.findViewById(R.id.container);
        septupViewPager(viewPager);

        TabLayout tabLayout = v.findViewById(R.id.tab_perfil);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void septupViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        adapter.addFragment(informe, "Informe");
        adapter.addFragment(resumenPeriodo, "Resumen periodo");
        adapter.addFragment(historicoPeso, getString(R.string.text_historico_pesos));
        viewPager.setAdapter(adapter);
    }

}
