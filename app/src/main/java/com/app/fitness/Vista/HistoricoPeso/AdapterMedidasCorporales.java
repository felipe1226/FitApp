package com.app.fitness.Vista.HistoricoPeso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.app.fitness.Modelo.HistoricoPeso.ListaItemMedidaCompara;
import com.app.fitness.R;

import java.util.ArrayList;

public class AdapterMedidasCorporales extends RecyclerView.Adapter<AdapterMedidasCorporales.MyViewHolder> {

    private Context context;
    private ArrayList<ListaItemMedidaCompara> medidas;

    public AdapterMedidasCorporales(Context context, ArrayList<ListaItemMedidaCompara> medidas) {

        this.context = context;
        this.medidas = medidas;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_medida_compara, parent, false);
        final MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvNombre.setText(medidas.get(position).getNombre());
        holder.tvMedida2.setText(String.valueOf( medidas.get(position).getMedida2() ));

        double medida = medidas.get(position).getMedida1();

        if(medida != 0){
            holder.tvMedida1.setText(String.valueOf( medida ));
            holder.ivTrend.setImageResource(medidas.get(position).getEstado());
            holder.tvValCompara.setText(String.valueOf( medidas.get(position).getValorComparacion() ));
        }
        else{
            holder.tvMedida1.setText("");
            holder.tvValCompara.setText(String.valueOf( medidas.get(position).getMedida2() ));
            holder.ivTrend.setImageResource(medidas.get(position).getEstado());
        }


    }

    @Override
    public int getItemCount() {
        return medidas.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private ConstraintLayout item_medida;

        private TextView tvNombre;
        private TextView tvMedida1;
        private TextView tvMedida2;

        private ImageView ivTrend;
        private TextView tvValCompara;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            item_medida = itemView.findViewById(R.id.item_medida);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvMedida1 = itemView.findViewById(R.id.tvMedida1);
            tvMedida2 = itemView.findViewById(R.id.tvMedida2);

            ivTrend = itemView.findViewById(R.id.ivTrend);
            tvValCompara = itemView.findViewById(R.id.tvValCompara);
        }
    }
}
