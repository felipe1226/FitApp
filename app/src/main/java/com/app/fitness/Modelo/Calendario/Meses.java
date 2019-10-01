package com.app.fitness.Modelo.Calendario;

public class Meses {

    private String[] mes = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                            "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    private int[] dias = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public Meses() {
    }

    public void verificarBiciesto(int year){

        if(year % 4 == 0){ dias[1] = 29; }
        else{ dias[1] = 28; }
    }

    public String getMes(int ind) {
        return mes[ind-1];
    }

    public int getDias(int ind) {
        return dias[ind-1];
    }
}
