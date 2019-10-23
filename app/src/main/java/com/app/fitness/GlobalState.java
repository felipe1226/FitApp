package com.app.fitness;

import android.app.Application;

import com.app.fitness.Modelo.Instructor.DatosInstructor;
import com.app.fitness.Modelo.Persona.DatosPersona;
import com.app.fitness.Modelo.Registro.DatosRegistro;
import com.app.fitness.Vista.Entreno.Entrenar;

public class GlobalState extends Application {

    public String ip = "http://fitapp.foodster.com.co/back-end/Android";

    public Entrenar entrenar;

    public DatosInstructor datosInstructor =  new DatosInstructor();
    public DatosPersona datosPersona =  new DatosPersona();
    public DatosRegistro datosRegistro =  new DatosRegistro();


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
}
