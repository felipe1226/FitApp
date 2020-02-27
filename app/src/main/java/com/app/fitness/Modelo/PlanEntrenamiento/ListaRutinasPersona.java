package com.app.fitness.Modelo.PlanEntrenamiento;

import java.util.ArrayList;

public class ListaRutinasPersona {

    private int id;
    private String nombre;
    private String orientacion;
    private int orden;
    private int idPlanEntrenamiento;
    private ArrayList<ListaEjerciciosPersona> ejercicios;

    public ListaRutinasPersona(int id, String nombre, String orientacion, int orden, int idPlanEntrenamiento) {
        this.id = id;
        this.nombre = nombre;
        this.orientacion = orientacion;
        this.orden = orden;
        this.idPlanEntrenamiento = idPlanEntrenamiento;
    }

    public ListaRutinasPersona(int id, String nombre, String orientacion, int orden, ArrayList<ListaEjerciciosPersona> ejercicios) {
        this.id = id;
        this.nombre = nombre;
        this.orientacion = orientacion;
        this.orden = orden;
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

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public ArrayList<ListaEjerciciosPersona> getEjercicios() {
        return ejercicios;
    }

    public void setEjercicios(ArrayList<ListaEjerciciosPersona> ejercicios) {
        this.ejercicios = ejercicios;
    }
}
