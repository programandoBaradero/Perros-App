package com.pity.firebaseappautentificacion.models;

public class Usuario {

    private String nombre;
    private String apellido;
    private String email;
    boolean mailVerificado;
    private int perrosPerdidosPublicados;
    private int perrosAdoptarPublicados;
    private int perrosEncontrados;
    private int cantidadPerrosTransicion;
    private int cantidadPerrosApadrinados;

    public Usuario(){}

    public Usuario(String nombre, String apellido, String email){
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.perrosPerdidosPublicados = 0;
        this.perrosAdoptarPublicados = 0;
        this.perrosEncontrados = 0;
        this.cantidadPerrosTransicion = 0;
        this.cantidadPerrosApadrinados = 0;
        this.mailVerificado = false;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getEmail() {
        return email;
    }

    public int getPerrosPerdidosPublicados() {
        return perrosPerdidosPublicados;
    }

    public int getPerrosAdoptarPublicados() {
        return perrosAdoptarPublicados;
    }

    public int getPerrosEncontrados() {
        return perrosEncontrados;
    }

    public int getCantidadPerrosTransicion() {
        return cantidadPerrosTransicion;
    }

    public int getCantidadPerrosApadrinados() {
        return cantidadPerrosApadrinados;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
