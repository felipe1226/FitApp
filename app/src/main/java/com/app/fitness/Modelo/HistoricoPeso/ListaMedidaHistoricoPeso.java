package com.app.fitness.Modelo.HistoricoPeso;

public class ListaMedidaHistoricoPeso {

    public String descripcion;
    public double medida;

    public ListaMedidaHistoricoPeso(String descripcion, double medida) {
        this.descripcion = descripcion;
        this.medida = medida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getMedida() {
        return medida;
    }

    public void setMedida(double medida) {
        this.medida = medida;
    }
}
