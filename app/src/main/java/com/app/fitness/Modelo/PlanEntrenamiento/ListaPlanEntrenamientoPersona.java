package com.app.fitness.Modelo.PlanEntrenamiento;

import java.util.ArrayList;

public class ListaPlanEntrenamientoPersona {

    private int id;
    private String nombre;
    private String descripcion;
    private int dias;
    private ArrayList<ListaRutinasPersona> rutinas;

    public ListaPlanEntrenamientoPersona(int id, String nombre, String descripcion, int dias) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias = dias;
    }

    public ListaPlanEntrenamientoPersona(int id, String nombre, String descripcion, int dias, ArrayList<ListaRutinasPersona> rutinas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.dias = dias;
        this.rutinas = rutinas;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public ArrayList<ListaRutinasPersona> getRutinas() {
        return rutinas;
    }

    public void setRutinas(ArrayList<ListaRutinasPersona> rutinas) {
        this.rutinas = rutinas;
    }
}
