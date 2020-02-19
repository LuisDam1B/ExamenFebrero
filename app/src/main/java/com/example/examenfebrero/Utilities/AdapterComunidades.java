package com.example.examenfebrero.Utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfebrero.Ciudades;
import com.example.examenfebrero.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterComunidades extends RecyclerView.Adapter {

    List<Ciudades> listaCiudades;
    Context contexto;

    public AdapterComunidades(List<Ciudades> listaCiudades, Context contexto) {
        this.listaCiudades = listaCiudades;
        this.contexto = contexto;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.linear_recycler_comunidades,parent,false);

        HolderComunidades holderComunidades = new HolderComunidades(view);
        return holderComunidades;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((HolderComunidades)holder).bindHC(listaCiudades.get(position));
    }

    @Override
    public int getItemCount() {
        return listaCiudades.size();
    }
}

class HolderComunidades extends RecyclerView.ViewHolder{

    TextView codigoPostalTV, nombreTV, codMinisterioTV;

    public HolderComunidades(@NonNull View itemView) {
        super(itemView);

        codigoPostalTV = itemView.findViewById(R.id.cpTV);
        nombreTV = itemView.findViewById(R.id.nombreTV);
        codMinisterioTV = itemView.findViewById(R.id.codMinisterioTV);

    }

    public  void bindHC(Ciudades ciudad){
        codigoPostalTV.setText(Integer.toString(ciudad.codigopostal));
        nombreTV.setText(ciudad.nombre);
        codMinisterioTV.setText(ciudad.codigoministerio);
    }
}
