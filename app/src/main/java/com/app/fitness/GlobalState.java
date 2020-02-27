package com.app.fitness;

import android.app.Application;

import com.app.fitness.Modelo.Instructor.DatosInstructor;
import com.app.fitness.Modelo.Persona.DatosPersona;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaEjerciciosPersona;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaPlanEntrenamientoPersona;
import com.app.fitness.Modelo.PlanEntrenamiento.ListaRutinasPersona;
import com.app.fitness.Modelo.Registro.DatosRegistro;
import com.app.fitness.Vista.Entreno.Entrenar;

import java.util.ArrayList;

public class GlobalState extends Application {

    public String ip = "http://fit.centromotors.com.co/back-end/Android";

    public Entrenar entrenar;

    public DatosInstructor datosInstructor =  new DatosInstructor();
    public DatosPersona datosPersona =  new DatosPersona();
    public DatosRegistro datosRegistro =  new DatosRegistro();

    public ArrayList<ListaEjerciciosPersona> listaEjerciciosPersona = new ArrayList<>();
    public ArrayList<ListaRutinasPersona> listaRutinasPersona = new ArrayList<>();
    public ArrayList<ListaPlanEntrenamientoPersona> listaPlanEntrenamientoPersona = new ArrayList<>();


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Entrenar getEntrenar() {
        return entrenar;
    }

    public void setEntrenar(Entrenar entrenar) {
        this.entrenar = entrenar;
    }

    public DatosInstructor getDatosInstructor() {
        return datosInstructor;
    }

    public void setDatosInstructor(DatosInstructor datosInstructor) {
        this.datosInstructor = datosInstructor;
    }

    public DatosPersona getDatosPersona() {
        return datosPersona;
    }

    public void setDatosPersona(DatosPersona datosPersona) {
        this.datosPersona = datosPersona;
    }

    public DatosRegistro getDatosRegistro() {
        return datosRegistro;
    }

    public void setDatosRegistro(DatosRegistro datosRegistro) {
        this.datosRegistro = datosRegistro;
    }

    public ArrayList<ListaEjerciciosPersona> getListaEjerciciosPersona() {
        return listaEjerciciosPersona;
    }

    public void setListaEjerciciosPersona(ArrayList<ListaEjerciciosPersona> listaEjerciciosPersona) {
        this.listaEjerciciosPersona = listaEjerciciosPersona;
    }


    public ArrayList<ListaRutinasPersona> getListaRutinasPersona() {
        return listaRutinasPersona;
    }

    public void setListaRutinasPersona(ArrayList<ListaRutinasPersona> listaRutinasPersona) {
        this.listaRutinasPersona = listaRutinasPersona;
    }

    public ArrayList<ListaPlanEntrenamientoPersona> getListaPlanEntrenamientoPersona() {
        return listaPlanEntrenamientoPersona;
    }

    public void setListaPlanEntrenamientoPersona(ArrayList<ListaPlanEntrenamientoPersona> listaPlanEntrenamientoPersona) {
        this.listaPlanEntrenamientoPersona = listaPlanEntrenamientoPersona;
    }
}
