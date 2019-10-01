package com.app.fitness.Vista.Entreno;

public class ListaEjerciciosRealizados {

    private int idEjercicio;
    private int tiempo;
    private int inicio;
    private int fin;

    public ListaEjerciciosRealizados(int idEjercicio, int tiempo, int inicio, int fin) {
        this.idEjercicio = idEjercicio;
        this.tiempo = tiempo;
        this.inicio = inicio;
        this.fin = fin;
    }

    public int getIdEjercicio() {
        return idEjercicio;
    }

    public void setIdEjercicio(int idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getInicio() {
        return inicio;
    }

    public void setInicio(int inicio) {
        this.inicio = inicio;
    }

    public int getFin() {
        return fin;
    }

    public void setFin(int fin) {
        this.fin = fin;
    }
}
