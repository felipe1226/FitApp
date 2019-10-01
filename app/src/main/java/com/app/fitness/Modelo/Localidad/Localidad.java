package com.app.fitness.Modelo.Localidad;

public class Localidad {

    private int idPais;
    private String pais;

    private int idLocalidad;
    private String localidad;

    public Localidad(int idPais, String pais, int idLocalidad, String localidad) {
        this.idPais = idPais;
        this.pais = pais;
        this.idLocalidad = idLocalidad;
        this.localidad = localidad;
    }


    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
