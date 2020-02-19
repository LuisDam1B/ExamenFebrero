package com.example.examenfebrero.Utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.examenfebrero.Clases.Foto;
import com.example.examenfebrero.Clases.Imagen;
import com.example.examenfebrero.R;

import java.util.ArrayList;

public class AdapterImagenes extends RecyclerView.Adapter {

    ArrayList<Imagen> listaImagenes;
    Context contexto;

    public AdapterImagenes(ArrayList<Imagen> listaImagenes, Context contexto) {

        this.listaImagenes = listaImagenes;
        this.contexto = contexto;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_recycler_ciudades,parent,false);
        HolderCiudades holderCiudades = new HolderCiudades(view,contexto);

        return holderCiudades;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((HolderCiudades)holder).bindCiudades(listaImagenes.get(position));

    }

    @Override
    public int getItemCount() {
        return listaImagenes.size();
    }
}

class HolderCiudades extends RecyclerView.ViewHolder{

    TextView codigoPostalTV, descripcionTV;
    ImageView imagenCiudad;
    Context contexto;

    public HolderCiudades(@NonNull View itemView, Context contexto) {
        super(itemView);

        this.contexto = contexto;

        codigoPostalTV = itemView.findViewById(R.id.codigoPostal);
        descripcionTV = itemView.findViewById(R.id.descripcion);
        imagenCiudad = itemView.findViewById(R.id.imageViewCiudad);

    }

    public void bindCiudades(Imagen imagen){
        codigoPostalTV.setText(Integer.toString(imagen.codigopostal));
        descripcionTV.setText(imagen.descripcion);
        imagenCiudad.setImageBitmap(Foto.convertirStringBitmap(imagen.imagen));
    }
}