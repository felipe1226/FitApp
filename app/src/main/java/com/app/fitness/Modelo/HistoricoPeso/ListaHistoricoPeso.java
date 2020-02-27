package com.app.fitness.Modelo.HistoricoPeso;

import java.util.ArrayList;

public class ListaHistoricoPeso {

    public int id;
    public float peso;
    String objetivo;
    public String fecha;

    public ArrayList<ListaMedidaHistoricoPeso> medidas = new ArrayList<>();

    public ListaHistoricoPeso(int id, float peso, String objetivo, String fecha) {
        this.id = id;
        this.peso = peso;
        this.objetivo = objetivo;
        this.fecha = fecha;
    }

    public void addListaMedidas(String descripcion, double medida){
        medidas.add(new ListaMedidaHistoricoPeso(descripcion, medida));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<ListaMedidaHistoricoPeso> getMedidas() {
        return medidas;
    }

    public void setMedidas(ArrayList<ListaMedidaHistoricoPeso> medidas) {
        this.medidas = medidas;
    }
}
