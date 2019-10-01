package com.app.fitness.Vista.CalendarioPlan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fitness.R;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinas;

import java.util.ArrayList;

public class AdapterCalendarioPlan extends RecyclerView.Adapter<AdapterCalendarioPlan.MyViewHolder> {

    CalendarioPlanRutina calendarioPlanRutina;
    Context context;
    ArrayList<ListaRutinas> rutinas;

    private int diaActual;
    private int diaInicial;
    private String mes;
    private int diasMes;

    int indiceAnimacion = 0;
    int auxPosicion;
    int auxDia;

    public AdapterCalendarioPlan(CalendarioPlanRutina calendarioPlanRutina, ArrayList<ListaRutinas> rutinas, int diaActual, int diaInicial, String mes, int diasMes) {

        this.calendarioPlanRutina = calendarioPlanRutina;
        this.context = calendarioPlanRutina.getApplicationContext();
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

    @Override
    public void onBindViewHolder(@NonNull final AdapterCalendarioPlan.MyViewHolder holder, int position) {
        boolean rutina = false;

        if(position >= diaInicial - 1){
            holder.tvDia.setText(String.valueOf(auxDia));
            if(rutinas.size() > auxPosicion && rutinas.get(auxPosicion).getDia() == auxDia ){
                holder.tvRutina.setText(rutinas.get(auxPosicion).getNombre());
                if(auxDia < diaActual){
                    holder.item_calendario.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.item_calendario_done));
                    holder.tvDia.setTextColor(context.getResources().getColor(R.color.text_calendario));
                    holder.tvRutina.setTextColor(context.getResources().getColor(R.color.text_calendario));
                }
                else if (auxDia == diaActual) { holder.item_calendario.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.item_calendario_actual)); }

                holder.item_calendario.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        calendarioPlanRutina.verEjercicios(rutinas.get(auxPosicion).getEjercicios());
                    }
                });
                if(rutinas.size() > auxPosicion + 1){
                    auxPosicion++;
                }
                rutina = true;
            }
            if(!rutina){ holder.item_calendario.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.item_calendario)); }

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
