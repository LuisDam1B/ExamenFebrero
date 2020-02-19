package com.example.examenfebrero.Clases;

import androidx.annotation.NonNull;

public class Comunidad {
    public int codigoComunidad;
    public String nombreComunidad;
    public int codigoPostalProvincia;
    public String nombrPRovincia;

    public Comunidad(int codigoComunidad, String nombreComunidad, int codigoPostalProvincia, String nombrPRovincia) {
        this.codigoComunidad = codigoComunidad;
        this.nombreComunidad = nombreComunidad;
        this.codigoPostalProvincia = codigoPostalProvincia;
        this.nombrPRovincia = nombrPRovincia;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
