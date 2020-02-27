package com.app.fitness.Vista.HistoricoPeso;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.app.fitness.GlobalState;
import com.app.fitness.Modelo.HistoricoPeso.ListaHistoricoPeso;
import com.app.fitness.Modelo.HistoricoPeso.ListaItemMedidaCompara;
import com.app.fitness.Modelo.HistoricoPeso.ListaMedidaHistoricoPeso;
import com.app.fitness.R;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.listener.OnDrawListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class HistoricoPeso extends Fragment implements OnChartValueSelectedListener, OnDrawListener {

    GlobalState gs;

    private LineChart grafPesos;

    private CircleImageView ivFoto1;
    private CircleImageView ivFoto2;
    private TextView tvFechaMedidas1;
    private TextView tvFechaMedidas2;

    private RecyclerView rvMedidas;

    private AdapterMedidasCorporales adapterMedidasCorporales;

    ArrayList<ListaHistoricoPeso> historicoPeso;

    private ArrayList<ListaItemMedidaCompara> medidas = new ArrayList<>();

    RequestQueue request;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gs = (GlobalState) getActivity().getApplicationContext();

        request = Volley.newRequestQueue(getContext());

        int idPersona = (gs.datosPersona.getId());

        consultarHistoricoPeso(idPersona);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_historico_peso, container, false);

        grafPesos = v.findViewById(R.id.grafPesos);
        grafPesos.setOnChartValueSelectedListener(this);
        grafPesos.setOnDrawListener(this);

        ivFoto1 = v.findViewById(R.id.ivFoto1);
        ivFoto2 = v.findViewById(R.id.ivFoto2);

        tvFechaMedidas1 = v.findViewById(R.id.tvFechaMedidas1);
        tvFechaMedidas2 = v.findViewById(R.id.tvFechaMedidas2);

        rvMedidas = v.findViewById(R.id.rvMedidas);

        return v;
    }

    private void generarGraficaPesos(){

        LineDataSet lineDataSet = new LineDataSet(setListaPesos(), "Objetivo1");
        lineDataSet.setColor(Color.GREEN);
        lineDataSet.setCircleRadius(5);
        lineDataSet.setCircleColor(Color.GRAY);
        lineDataSet.setValueTextSize(12);

        ArrayList<ILineDataSet> dataSet = new ArrayList<>();
        dataSet.add(lineDataSet);

        YAxis yAxis = grafPesos.getAxisLeft();
        yAxis.setDrawGridLines(false);
        yAxis.setEnabled(false);
        yAxis.setDrawLabels(false);
        yAxis.setDrawLimitLinesBehindData(false);

        XAxis xAxis = grafPesos.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(formatoFechas()));
        xAxis.setGranularity(1);
        xAxis.setTextSize(7f);

        LineData data = new LineData(dataSet);

        grafPesos.setScaleEnabled(false);
        grafPesos.getDescription().setEnabled(true);
        grafPesos.getDescription().setText("");
        grafPesos.setDrawGridBackground(false);
        grafPesos.setPinchZoom(false);
        grafPesos.setData(data);
        grafPesos.invalidate();
    }

    private ArrayList<Entry> setListaPesos(){
        ArrayList<Entry> pesos = new ArrayList<Entry>();
        for(int i=0;i<historicoPeso.size();i++){
            float peso = Float.valueOf( historicoPeso.get(i).getPeso() );
            pesos.add(new Entry(i, peso));
        }

        return pesos;
    }

    private String[] formatoFechas(){
        String[] fechas = new String[historicoPeso.size()];

        for(int i = 0;i<historicoPeso.size();i++){
            fechas[i] = (historicoPeso.get(i).getFecha());
        }

        return fechas;
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {

        int index = (int) e.getX();

        LineData lineData = grafPesos.getData();

        ILineDataSet lineDataSet = lineData.getDataSetForEntry(e);
        //lineDataSet.setValueTextSize(18);

        seleccionarMedida(index);
    }

    private void seleccionarMedida(int index){

        tvFechaMedidas2.setText(historicoPeso.get(index).getFecha());

        if(index > 0){
            ivFoto1.setVisibility(View.VISIBLE);
            tvFechaMedidas1.setText(historicoPeso.get(index - 1).getFecha());

            setCompararMedidasCorporales(index, index - 1);
        }
        else{
            ivFoto1.setVisibility(View.GONE);
            tvFechaMedidas1.setText("");

            setListaMedidasCorporales();
        }
    }

    @Override
    public void onNothingSelected() {


    }

    @Override
    public void onEntryAdded(Entry entry) {
        Log.i(Chart.LOG_TAG, entry.toString());
    }

    @Override
    public void onDrawFinished(DataSet<?> dataSet) {
        Log.i(Chart.LOG_TAG, "DataSet drawn. " + dataSet.toSimpleString());

        // prepare the legend again
        grafPesos.getLegendRenderer().computeLegend(grafPesos.getData());
    }

    @Override
    public void onEntryMoved(Entry entry) {
        Log.i(Chart.LOG_TAG, "Point moved " + entry.toString());
    }


    private void setListaMedidasCorporales(){

        medidas = new ArrayList<>();

        ArrayList<ListaMedidaHistoricoPeso> medidaHistorico = historicoPeso.get(0).getMedidas();

        for(int i = 0;i<medidaHistorico.size();i++) {
            medidas.add(new ListaItemMedidaCompara(medidaHistorico.get(i).getDescripcion(),
                    medidaHistorico.get(i).getMedida()));
        }

        recyclerListaMedidas();
    }

    private void setCompararMedidasCorporales(int index1, int index2){

        medidas = new ArrayList<>();

        ArrayList<ListaMedidaHistoricoPeso> medidaHistorico1 = historicoPeso.get(index1).getMedidas();
        ArrayList<ListaMedidaHistoricoPeso> medidaHistorico2 = historicoPeso.get(index2).getMedidas();

        for(int i = 0;i<medidaHistorico1.size();i++) {
            medidas.add(new ListaItemMedidaCompara(medidaHistorico1.get(i).getDescripcion(),
                    medidaHistorico2.get(i).getMedida(),
                    medidaHistorico1.get(i).getMedida()));
        }

        recyclerListaMedidas();
    }

    private void recyclerListaMedidas(){

        adapterMedidasCorporales = new AdapterMedidasCorporales(getContext(), medidas);
        rvMedidas.setLayoutManager(new GridLayoutManager(getContext(), 1));
        rvMedidas.setAdapter(adapterMedidasCorporales);
    }

    private void obtenerHistoricoPeso(JSONArray datos, JSONObject jsonObject){
        try {
            historicoPeso = new ArrayList<>();

            int idAux = 0;
            int indHistorico = 0;

            for (int i = 0; i < datos.length(); i++) {
                jsonObject = datos.getJSONObject(i);
                int idHistorico = jsonObject.optInt("id");
                if(idHistorico != idAux){

                    double peso = jsonObject.optDouble("peso");
                    String objetivo = jsonObject.optString("objetivo");
                    String[] fecha = jsonObject.optString("fecha").split(" ");

                    historicoPeso.add(new ListaHistoricoPeso(idHistorico, (float)peso, objetivo, fecha[0]));

                    idAux = idHistorico;
                    indHistorico ++;
                }

                String descripcion = jsonObject.optString("descripcion");
                double medida = jsonObject.optDouble("medida");

                historicoPeso.get(indHistorico - 1).addListaMedidas(descripcion, medida);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        generarGraficaPesos();
        seleccionarMedida(historicoPeso.size() - 1);
    }

    private void consultarHistoricoPeso(int idPersona){
        String url = gs.getIp() + "/HistoricoPeso/consultar_historico_peso.php?idPersona=" + idPersona;
        url = url.replace(" ", "%20");

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray datos = response.optJSONArray("historico_peso");
                JSONObject jsonObject = null;
                try {
                    jsonObject = datos.getJSONObject(0);
                    if (!jsonObject.optString("id").equals("0")) {
                        obtenerHistoricoPeso(datos, jsonObject);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                /*detectarError(error);*/
            }
        });
        request.add(jsonObjectRequest);
    }
}
