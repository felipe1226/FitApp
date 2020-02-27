package com.app.fitness.Modelo.HistoricoPeso;

import com.app.fitness.R;

public class ListaItemMedidaCompara {


    public String nombre;

    public double medida1;
    public double medida2;

    public double valorComparacion;

    public static int MAYOR = R.drawable.ic_trending_up;
    public static int MENOR = R.drawable.ic_trending_down;
    public static int IGUAL = R.drawable.ic_trending_same;

    private int estado;

    public ListaItemMedidaCompara(String nombre, double medida) {
        this.nombre = nombre;
        this.medida2 = medida;
        this.medida1 = 0;
        this.estado = IGUAL;
    }

    public ListaItemMedidaCompara(String nombre, double medida1, double medida2) {
        this.nombre = nombre;
        this.medida1 = medida1;
        this.medida2 = medida2;

        compararMedidas(medida1, medida2);
    }

    public void compararMedidas(double medida1, double medida2)
    {
        double valor = medida2 - medida1;

        if(valor == 0){ this.estado = MAYOR; }
        else{
            if(valor > 0){ this.estado = MAYOR; }
            else{ this.estado = MENOR; }
        }

        this.valorComparacion = valor;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMedida1() {
        return medida1;
    }

    public void setMedida1(double medida1) {
        this.medida1 = medida1;
    }


    public double getMedida2() {
        return medida2;
    }

    public void setMedida2(double medida2) {
        this.medida2 = medida2;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public double getValorComparacion() {
        return valorComparacion;
    }

    public void setValorComparacion(double valorComparacion) {
        this.valorComparacion = valorComparacion;
    }
}
