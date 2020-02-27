package com.app.fitness.Vista.CalendarioPlan;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinasPersona;
import com.app.fitness.R;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinas;

import java.util.ArrayList;

public class AdapterCalendarioPlan extends RecyclerView.Adapter<AdapterCalendarioPlan.MyViewHolder> {

    CalendarioPlanRutina calendarioPlanRutina;
    Calendario calendario;
    Context context;
    ArrayList<ListaRutinasPersona> rutinas;

    private int diaActual;
    private int diaInicial;
    private String mes;
    private int diasMes;

    int indiceAnimacion = 0;
    int auxPosicion;
    int auxDia;

    public AdapterCalendarioPlan(CalendarioPlanRutina calendarioPlanRutina, Calendario calendario, ArrayList<ListaRutinasPersona> rutinas, int diaActual, int diaInicial, String mes, int diasMes) {

        this.calendarioPlanRutina = calendarioPlanRutina;
        this.calendario = calendario;
        this.context = calendario.getContext();
        this.rutinas = rutinas;

        this.diaActual = diaActual;
        this.diaInicial = diaInicial;
        this.mes = mes;
        this.diasMes = diasMes;

        auxPosicion = 0;
        auxDia = 1;
    }

    private void animacion(View view) {
        view.animate().cancel();
        view.setTranslationY(100);
        view.setAlpha(0);
        view.animate().alpha(1.0f).translationY(0).setDuration(300).setStartDelay(indiceAnimacion * 20);
        indiceAnimacion ++;
    }

    @NonNull
    @Override
    public AdapterCalendarioPlan.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_calendario, parent, false);
        final AdapterCalendarioPlan.MyViewHolder holder = new AdapterCalendarioPlan.MyViewHolder(v);

        animacion(holder.itemView);

        return holder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull final AdapterCalendarioPlan.MyViewHolder holder, int position) {

        int estado = 0;

        if(position >= diaInicial - 1){
            holder.tvDia.setText(String.valueOf(auxDia));

            if(auxPosicion < rutinas.size()){
                if(rutinas.get(auxPosicion).getOrden() == auxDia ){
                    holder.tvRutina.setText(rutinas.get(auxPosicion).getNombre());
                    if(auxDia < diaActual){
                        holder.item_calendario.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.item_calendario_done));
                        holder.tvDia.setTextColor(context.getResources().getColor(R.color.text_calendario));
                        holder.tvRutina.setTextColor(context.getResources().getColor(R.color.text_calendario));
                        estado = 1;
                    }
                    else{
                        if (auxDia == diaActual) {
                            estado = 2;
                            holder.item_calendario.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.item_calendario_done));
                        }
                        else {
                            estado = 0;
                            holder.item_calendario.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.item_calendario_rutina));
                        }
                    }

                    final int finalEstado = estado;
                    holder.item_calendario.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            calendarioPlanRutina.verEjercicios(rutinas.get(auxPosicion).getEjercicios(),
                                    holder.tvDia.getText().toString(),
                                    rutinas.get(auxPosicion).getId(),
                                    holder.tvRutina.getText().toString(),
                                    finalEstado);
                        }
                    });
                    if(rutinas.size() > auxPosicion + 1){
                        auxPosicion++;
                    }
                }
                else{
                    holder.item_calendario.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.item_calendario));

                    holder.item_calendario.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            calendarioPlanRutina.ocultarEjercicios();
                        }
                    });
                }
            }
            if (auxDia == diaActual) {
                holder.item_calendario.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.item_calendario_actual));
            }
            auxDia ++;
        }
        else{ holder.item_calendario.setVisibility(View.GONE); }
    }

    @Override
    public int getItemCount() {
        return diasMes + diaInicial - 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public ConstraintLayout item_calendario;
        public TextView tvDia;
        public TextView tvRutina;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_calendario =  itemView.findViewById(R.id.item_calendario);
            tvDia =  itemView.findViewById(R.id.tvDia);
            tvRutina =  itemView.findViewById(R.id.tvRutina);
        }
    }
}
