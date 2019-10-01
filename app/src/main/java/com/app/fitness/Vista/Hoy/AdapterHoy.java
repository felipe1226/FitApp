package com.app.fitness.Vista.Hoy;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fitness.Modelo.PlanEntrenamiento.ListaPlanRutinas;
import com.app.fitness.R;
import com.app.fitness.Vista.CalendarioPlan.CalendarioPlanRutina;

import java.util.ArrayList;

public class AdapterHoy extends RecyclerView.Adapter<AdapterHoy.MyViewHolder> {

    Context context;
    ArrayList<ListaPlanRutinas> planRutina;

    int indiceAnimacion = 0;

    public AdapterHoy(Context context, ArrayList<ListaPlanRutinas> planRutina) {
        this.context = context;
        this.planRutina = planRutina;
    }

    private void animacion(View view) {
        view.animate().cancel();
        view.setTranslationY(100);
        view.setAlpha(0);
        view.animate().alpha(1.0f).translationY(0).setDuration(300).setStartDelay(indiceAnimacion * 100);
        indiceAnimacion ++;
    }

    private void verPlan(int id){

        Intent intent = new Intent(context, CalendarioPlanRutina.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    @NonNull
    @Override
    public AdapterHoy.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_plan_hoy, parent, false);
        final AdapterHoy.MyViewHolder holder = new AdapterHoy.MyViewHolder(v);

        animacion(holder.itemView);

        holder.item_hoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verPlan(planRutina.get(holder.getAdapterPosition()).getId());
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHoy.MyViewHolder holder, int position) {

        holder.tvPlan.setText(planRutina.get(position).getNombre());
    }

    @Override
    public int getItemCount() {
        return planRutina.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        public LinearLayout item_hoy;
        public TextView tvPlan;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_hoy = itemView.findViewById(R.id.item_hoy);
            tvPlan = itemView.findViewById(R.id.tvPlan);
        }
    }
}
