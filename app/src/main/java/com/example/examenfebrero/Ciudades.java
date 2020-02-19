package com.example.examenfebrero;

import android.graphics.Bitmap;

public class Ciudades {
    public int codigopostal;
    public String codigoministerio, nombre;
    public int numeroFotos;
    public Bitmap imagen;
    public Ciudades(){}
    public Ciudades(int codigopostal, String codigoministerio, String nombre) {
        this.codigopostal = codigopostal;
        this.codigoministerio = codigoministerio;
        this.nombre = nombre;
    }
}
