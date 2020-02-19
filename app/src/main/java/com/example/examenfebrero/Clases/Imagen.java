package com.example.examenfebrero.Clases;

public class Imagen {
    public int id;
    public int codigopostal;
    public String descripcion;
    public String imagen;
    public Imagen(){}

    public Imagen(int codigopostal, String descripcion, String imagen) {
        this.codigopostal = codigopostal;
        this.imagen = imagen;
        this.descripcion=descripcion;
    }
}
