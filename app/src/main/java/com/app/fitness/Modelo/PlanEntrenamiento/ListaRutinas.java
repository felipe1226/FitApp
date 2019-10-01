package com.app.fitness.Modelo.PlanEntrenamiento;

import java.util.ArrayList;

public class ListaRutinas {

    private int id;
    private String nombre;
    private String orientacion;
    private int dia;
    private String duracion;
    private ArrayList<ListaEjercicios> ejercicios;


    public ListaRutinas(int id, String nombre, String orientacion, int dia, String duracion, ArrayList<ListaEjercicios> ejercicios) {
        this.id = id;
        this.nombre = nombre;
        this.orientacion = orientacion;
        this.dia = dia;
        this.duracion = duracion;
        this.ejercicios = ejercicios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getOrientacion() {
        return orientacion;
    }

    public void setOrientacion(String orientacion) {
        this.orientacion = orientacion;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public ArrayList<ListaEjercicios> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<ListaEjercicios> ejercicios) {
        this.ejercicios = ejercicios;
    }

}
