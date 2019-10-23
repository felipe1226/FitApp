package com.app.fitness.Vista.CalendarioPlan;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.fitness.Modelo.Calendario.Meses;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjercicios;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaPlanRutinas;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinas;
import com.app.fitness.R;
import com.app.fitness.Vista.Entreno.AdapterEjercicios;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Calendario extends Fragment {

    CalendarioPlanRutina calendarioPlanRutina;

    private AdapterCalendarioPlan adapterCalendarioPlan;

    private RecyclerView rvCalendario;

    private ArrayList<ListaPlanRutinas> planRutinas;

    public int id;

    int diaIncial;
    int diaActual;
    String mes;
    int diasMes;

    public Calendario(int id, CalendarioPlanRutina calendarioPlanRutina, ArrayList<ListaPlanRutinas> planRutinas) {
        this.id = id;
        this.calendarioPlanRutina = calendarioPlanRutina;
        this.planRutinas = planRutinas;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_calendario, container, false);

        initView(v);

        try {
            fechaActual();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        generarCalendario();

        return v;
    }

    private void initView(View v){

        rvCalendario = v.findViewById(R.id.rvCalendario);
    }

    public void generarCalendario() {

        for (int i=0;i<planRutinas.size();i++){
            if(planRutinas.get(i).getId() == id){
                adapterCalendarioPlan = new AdapterCalendarioPlan(calendarioPlanRutina, this, planRutinas.get(i).getRutinas(), diaActual, diaIncial, mes, diasMes);
                rvCalendario.setLayoutManager(new GridLayoutManager(getContext(), 7));
                rvCalendario.setAdapter(adapterCalendarioPlan);
                break;
            }
        }
    }

    private void fechaActual() throws ParseException {

        Calendar c = Calendar.getInstance();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] fecha = dateFormat.format(c.getTime()).split("-");

        Meses meses = new Meses();
        meses.verificarBiciesto(Integer.parseInt(fecha[0]));

        c.set(Integer.parseInt(fecha[2]),Integer.parseInt(fecha[1]),1);
        int diaSemana =  c.get(Calendar.DAY_OF_WEEK);

        diaActual = Integer.parseInt(fecha[2]);
        diaIncial = diaSemana;
        mes = meses.getMes(Integer.parseInt(fecha[1]));
        diasMes = meses.getDias(Integer.parseInt(fecha[1]));
    }
}
