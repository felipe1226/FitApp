package com.app.fitness.Modelo.PlanEntrenamiento;

public class ListaEjercicios {

    private int id;
    private String ejercicio;
    private String resumen;
    private String horas;
    private String minutos;
    private String segundos;

    private int series;
    private int repeticiones;
    private String peso;
    private String descanso;
    private String descripcion;

    private int estado;

    private boolean info;


    public ListaEjercicios(int id, String ejercicio, int series, int repeticiones, String peso, String descanso, String descripcion) {
        this.id = id;
        this.ejercicio = ejercicio;
        this.series = series;
        this.repeticiones = repeticiones;
        this.peso = peso;
        this.descanso = descanso;
        this.descripcion = descripcion;
        this.estado = 0;

        this.horas = "00";
        this.minutos = "00";
        this.segundos = "00";

        this.resumen = series + "/" + repeticiones + "/" + peso + "/" + descanso ;

        info = false;
    }

    public void setTiempo(String horas, String minutos, String segundos) {
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticiones() {
        return repeticiones;
    }

    public void setRepeticiones(int repeticiones) {
        this.repeticiones = repeticiones;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getDescanso() {
        return descanso;
    }

    public void setDescanso(String descanso) {
        this.descanso = descanso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getMinutos() {
        return minutos;
    }

    public void setMinutos(String minutos) {
        this.minutos = minutos;
    }

    public String getSegundos() {
        return segundos;
    }

    public void setSegundos(String segundos) {
        this.segundos = segundos;
    }

    public boolean isInfo() {
        return info;
    }

    public void setInfo(boolean info) {
        this.info = info;
    }
}
