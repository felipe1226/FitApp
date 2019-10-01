package com.app.fitness.Modelo.PlanEntrenamiento;

import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinas;

import java.util.ArrayList;

public class ListaPlanRutinas {

    private int id;
    private String nombre;
    private String descripcion;
    private int dias;
    private ArrayList<ListaRutinas> rutinas;

    public ListaPlanRutinas(int id, String nombre, String descripcion, int dias, ArrayList<ListaRutinas> rutinas) {
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

    public ArrayList<ListaRutinas> getRutinas() {
        return rutinas;
    }

    public void setRutinas(ArrayList<ListaRutinas> rutinas) {
        this.rutinas = rutinas;
    }
}
