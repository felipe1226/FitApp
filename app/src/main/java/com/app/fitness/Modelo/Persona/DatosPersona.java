package com.app.fitness.Modelo.Persona;

public class DatosPersona {

    public int id;
    public String foto;
    public String tipo;
    public String identificacion;
    public String nombres;
    public String apellidos;
    public String genero;
    public int edad;
    public String fecha;
    public String email;
    public String movil;
    public String ciudad;
    public String estado;

    public DatosPersona() {
    }

    public DatosPersona(int id, String tipo, String identificacion, String nombres, String apellidos,
                        String genero, int edad, String fecha, String email, String movil, String ciudad, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.edad = edad;
        this.fecha = fecha;
        this.email = email;
        this.movil = movil;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public DatosPersona(int id, String foto, String tipo, String identificacion, String nombres, String apellidos,
                        String genero, int edad, String fecha, String email, String movil, String ciudad, String estado) {
        this.id = id;
        this.foto = foto;
        this.tipo = tipo;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.genero = genero;
        this.edad = edad;
        this.fecha = fecha;
        this.email = email;
        this.movil = movil;
        this.ciudad = ciudad;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
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

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
