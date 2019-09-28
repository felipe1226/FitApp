package com.app.fitness.Vista.Entreno;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fitness.R;

import java.util.ArrayList;

public class AdapterMusculos extends RecyclerView.Adapter<AdapterMusculos.MyViewHolder> {

    Context context;
    ArrayList<String> musculo;

    public AdapterMusculos(Context context, ArrayList<String> musculo) {
        this.context = context;
        this.musculo = musculo;
    }

    @NonNull
    @Override
    public AdapterMusculos.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_musculo_entrenar, parent, false);

        AdapterMusculos.MyViewHolder holder = new AdapterMusculos.MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMusculos.MyViewHolder holder, int position) {

        holder.tvMusculo.setText(musculo.get(position));
    }

    @Override
    public int getItemCount() {
        return musculo.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvMusculo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMusculo = itemView.findViewById(R.id.tvMusculo);
        }
    }
}
