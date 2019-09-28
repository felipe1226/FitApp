package com.app.fitness;

import android.app.Application;

import com.app.fitness.Modelo.Persona.DatosPersona;
import com.app.fitness.Modelo.Registro.DatosRegistro;

public class GlobalState extends Application {

    public String ip = "http://fitapp.foodster.com.co/back-end/Android";

    public DatosPersona datosPersona =  new DatosPersona();
    public DatosRegistro datosRegistro =  new DatosRegistro();


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
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
