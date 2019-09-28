package com.app.fitness.Vista.Entreno;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.akaita.android.morphview.MorphView;
import com.app.fitness.R;

import java.util.ArrayList;

import static com.app.fitness.R.*;

public class AdapterEjercicios extends RecyclerView.Adapter<AdapterEjercicios.MyViewHolder>{

    private Entrenar entrenar;
    private Context context;
    private ArrayList<ListaEjercicios> ejercicios;

    int indice = -1;
    boolean entrenando;

    public AdapterEjercicios(Entrenar entrenar, Context context, ArrayList<ListaEjercicios> ejercicios) {
        this.entrenar = entrenar;
        this.context = context;
        this.ejercicios = ejercicios;

        this.entrenando = false;
    }

    public void setEntrenando(boolean entrenando) {
        this.entrenando = entrenando;
    }

    public void actualizarTiempo(String horas, String minutos, String segundos){

        ejercicios.get(indice).setTiempo(horas, minutos, segundos);
        notifyItemChanged(indice);
    }

    public void finalizarEjercicio(){
        ejercicios.get(indice).setEstado(2);
        entrenando = false;

        notifyItemChanged(indice);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(layout.item_ejercicio_inicio, parent, false);
        final AdapterEjercicios.MyViewHolder holder = new AdapterEjercicios.MyViewHolder(v);

        animate(v, holder.getAdapterPosition());

        holder.item_ejercicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!entrenando && ejercicios.get(holder.getAdapterPosition()).getEstado() != 2){
                    if(ejercicios.get(holder.getAdapterPosition()).getEstado() == 0){
                        indice = holder.getAdapterPosition();
                        ejercicios.get(indice).setEstado(1);
                        entrenar.btnIniEjercicio.setEnabled(true);
                        notifyDataSetChanged();
                    }
                }
            }
        });

        holder.ibShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.layout_info.getVisibility() == View.GONE){
                    holder.layout_info.setVisibility(View.VISIBLE);
                    holder.ibShow.setImageResource(drawable.ic_expand_less);
                }
                else{
                    holder.layout_info.setVisibility(View.GONE);
                    holder.ibShow.setImageResource(drawable.ic_expand);
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.tvEjercicio.setText(ejercicios.get(position).getEjercicio());
        holder.tvResumen.setText(ejercicios.get(position).getResumen());

        holder.tvHoras.setText(ejercicios.get(position).getHoras());
        holder.tvMinutos.setText(ejercicios.get(position).getMinutos());
        holder.tvSegundos.setText(ejercicios.get(position).getSegundos());

        holder.tvSeries.setText(String.valueOf(ejercicios.get(position).getSeries()));
        holder.tvRepeticiones.setText(String.valueOf(ejercicios.get(position).getRepeticiones()));
        holder.tvPeso.setText(ejercicios.get(position).getPeso());
        holder.tvDescanso.setText(ejercicios.get(position).getDescanso());

        holder.tvDescripcion.setText(ejercicios.get(position).getDescripcion());

        if (ejercicios.get(position).getEstado() == 2){
            holder.item_ejercicio.setBackgroundDrawable(ContextCompat.getDrawable(context, drawable.item_ejercicio_entreno_fin));
        }
        else{
            if (indice == position){
                holder.item_ejercicio.setBackgroundDrawable(ContextCompat.getDrawable(context, drawable.item_ejercicio_entreno_select));
            }
            else{
                ejercicios.get(position).setEstado(0);
                holder.item_ejercicio.setBackgroundDrawable(ContextCompat.getDrawable(context, drawable.item_ejercicio_entreno));
            }
        }
    }

    @Override
    public int getItemCount() {
        return ejercicios.size();
    }

    private void animate(View view, final int pos) {
        view.animate().cancel();
        view.setTranslationY(100);
        view.setAlpha(0);
        view.animate().alpha(1.0f).translationY(0).setDuration(300).setStartDelay(pos * 100);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder                       {

        private LinearLayout item_ejercicio;
        private ConstraintLayout layout_item;
        private TextView tvEjercicio;
        private TextView tvResumen;
        private TextView tvHoras;
        private TextView tvMinutos;
        private TextView tvSegundos;
        private ImageButton ibShow;

        private ConstraintLayout layout_info;
        private TextView tvSeries;
        private TextView tvRepeticiones;
        private TextView tvPeso;
        private TextView tvDescanso;
        private TextView tvDescripcion;
        private ImageView ivImagen;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_ejercicio = itemView.findViewById(id.item_ejercicio);
            layout_item = itemView.findViewById(id.layout_item);
            tvEjercicio = itemView.findViewById(id.tvEjercicio);
            tvResumen = itemView.findViewById(id.tvResumen);
            tvHoras = itemView.findViewById(id.tvHoras);
            tvMinutos = itemView.findViewById(id.tvMinutos);
            tvSegundos = itemView.findViewById(id.tvSegundos);
            ibShow = itemView.findViewById(id.ibShow);

            layout_info = itemView.findViewById(id.layout_info);
            tvSeries = itemView.findViewById(id.tvSeries);
            tvRepeticiones = itemView.findViewById(id.tvRepeticiones);
            tvPeso = itemView.findViewById(id.tvPeso);
            tvDescanso = itemView.findViewById(id.tvDescanso);
            tvDescripcion = itemView.findViewById(id.tvDescripcion);
            ivImagen = itemView.findViewById(id.ivImagen);
        }
    }
}
