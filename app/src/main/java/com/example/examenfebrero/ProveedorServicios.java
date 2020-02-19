package com.example.examenfebrero;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProveedorServicios {
    @GET("ciudades")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<List<Ciudades>> getCiudades();
}
