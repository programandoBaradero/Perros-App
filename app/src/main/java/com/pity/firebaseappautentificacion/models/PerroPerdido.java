package com.pity.firebaseappautentificacion.models;

public class PerroPerdido {
    String nombre;
    String descripcion;



    public PerroPerdido() {
    }

    public PerroPerdido(String nombre, String descripcion, int foto_perfil) {
        this.nombre = nombre;
        this.descripcion = descripcion;

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
}
