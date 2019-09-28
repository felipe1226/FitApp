package com.app.fitness.Modelo.Registro;

import java.util.ArrayList;

public class DatosRegistro {

    public String instructor;

    public String email;
    public String password;

    public String tipo;
    public String identificacion;
    public String nombres;
    public String apellidos;

    public String movil;
    public String ciudad;

    public String estatura;
    public String peso;
    public String genero;
    public String fechaNacimiento;

    public String nivelAcondicionamiento;
    public String objetivo;
    public String fuma;
    public ArrayList<String> entrenamientos;


    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNivelAcondicionamiento() {
        return nivelAcondicionamiento;
    }

    public void setNivelAcondicionamiento(String nivelAcondicionamiento) {
        this.nivelAcondicionamiento = nivelAcondicionamiento;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getFuma() {
        return fuma;
    }

    public void setFuma(String fuma) {
        this.fuma = fuma;
    }

    public ArrayList<String> getEntrenamientos() {
        return entrenamientos;
    }

    public void setEntrenamientos(ArrayList<String> entrenamientos) {
        this.entrenamientos = entrenamientos;
    }
}
