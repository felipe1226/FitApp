package com.app.fitness.Vista.Hoy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fitness.R;

import java.util.ArrayList;

public class AdapterHoy extends RecyclerView.Adapter<AdapterHoy.MyViewHolder> {

    Context context;
    ArrayList<String> listaHoy;

    public AdapterHoy(Context context, ArrayList<String> listaHoy) {
        this.context = context;
        this.listaHoy = listaHoy;
    }

    @NonNull
    @Override
    public AdapterHoy.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_plan_hoy, parent, false);
        AdapterHoy.MyViewHolder holder = new AdapterHoy.MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterHoy.MyViewHolder holder, int position) {

        holder.tvPlan.setText(listaHoy.get(position));
    }

    @Override
    public int getItemCount() {
        return listaHoy.size();
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
