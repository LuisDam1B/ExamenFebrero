package com.example.examenfebrero.FragmentsPKG;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.examenfebrero.Clases.Imagen;
import com.example.examenfebrero.R;
import com.example.examenfebrero.Utilities.AdapterImagenes;
import com.example.examenfebrero.Utilities.Utilidades;

import java.util.ArrayList;


public class ImagenesCiudadesFragment extends Fragment {

    ArrayList<Imagen> listaImagenes;
    RecyclerView recyclerViewCiudades;
    AdapterImagenes adapterImagenes;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.imagenes_ciudades_fragment,container,false);
        recyclerViewCiudades = rootView.findViewById(R.id.CiudadesRecyclerView);

        listaImagenes = Utilidades.listaDeImagenesCiudades(getActivity());
        adapterImagenes = new AdapterImagenes(listaImagenes,getActivity());
        recyclerViewCiudades.setAdapter(adapterImagenes);
        recyclerViewCiudades.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));


        return rootView;

    }
}
