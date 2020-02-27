package com.app.fitness.Vista.Entreno;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.fitness.GlobalState;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjercicios;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjerciciosPersona;
import com.app.fitness.R;
import com.app.fitness.Vista.PagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Entrenar extends Fragment {

    GlobalState gs;

    public Detalles detalles = new Detalles();
    public Iniciar iniciar = new Iniciar();

    private static final String tab1 = "Detalles";
    private static final String tab2 = "Iniciar";

    public HiloEntrenoEjercicio hiloEntrenoEjercicio = null;

    private ViewPager viewPager;

    private RecyclerView rvEjercicios;

    public AdapterEjercicios adapterEjercicios;

    private ArrayList<ListaEjerciciosPersona> ejercicios;
    private ArrayList<ListaEjerciciosRealizados> ejerciciosRealizados = new ArrayList<>();

    RequestQueue request;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gs = (GlobalState) getActivity().getApplicationContext();
        gs.setEntrenar(this);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            int idRutina = bundle.getInt("idRutina", 0);
        }

        request = Volley.newRequestQueue(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_entrenar, container, false);

        initView(v);

        listarEjercicios();

        return v;
    }

    private void initView(View v) {

        viewPager = v.findViewById(R.id.container);
        septupViewPager(viewPager);

        TabLayout tabLayout = v.findViewById(R.id.tab_inicio);
        tabLayout.setupWithViewPager(viewPager);

        rvEjercicios = v.findViewById(R.id.rvEjercicios);
    }

    private void septupViewPager(ViewPager viewPager) {
        PagerAdapter adapter = new PagerAdapter(getFragmentManager());
        adapter.addFragment(detalles, tab1);
        adapter.addFragment(iniciar, tab2);
        viewPager.setAdapter(adapter);
    }

    private void listarEjercicios(){

        ejercicios = new ArrayList<>();

        ejercicios = gs.getListaEjerciciosPersona();

        adapterEjercicios = new AdapterEjercicios(this, iniciar, getContext(), ejercicios);
        rvEjercicios.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvEjercicios.setAdapter(adapterEjercicios);
    }

    public void iniciarEjercicio(){
        adapterEjercicios.setEntrenando(true);

        if(hiloEntrenoEjercicio == null){
            hiloEntrenoEjercicio = new HiloEntrenoEjercicio(this, iniciar);
            hiloEntrenoEjercicio.execute();
        }
        else{ hiloEntrenoEjercicio.initTiempoEjercicio(); }
    }

    public void finalizarEjercicio(){

        hiloEntrenoEjercicio.finalizarEjercicio();
        agregarEjercicioRealizado();

        adapterEjercicios.finalizarEjercicio();

    }

    public void finalizarRutina(){

        hiloEntrenoEjercicio.onCancelled();
    }

    private void agregarEjercicioRealizado() {

        int indice = adapterEjercicios.indice;
        int tiempo = hiloEntrenoEjercicio.getTiempoEjercicio();
        int inicio = hiloEntrenoEjercicio.getInicioEjercicio();
        int fin = hiloEntrenoEjercicio.getFinEjercicio();

        ejerciciosRealizados.add(new ListaEjerciciosRealizados(ejercicios.get(indice).getId(), tiempo, inicio, fin));
    }

    public void actualizarTiempoEjercicio(String horas, String minutos, String segundos) {

        adapterEjercicios.actualizarTiempo(horas, minutos, segundos);
    }
}
