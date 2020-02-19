package com.example.examenfebrero.Utilities;



import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfebrero.Ciudades;
import com.example.examenfebrero.Clases.Foto;
import com.example.examenfebrero.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xusa on 22/11/2015.
 */
public class Adapter extends RecyclerView.Adapter implements View.OnClickListener , View.OnLongClickListener {
    List<Ciudades> datos;
    View.OnClickListener listener;
    View.OnLongClickListener listenerLong;  
    Context context;
    boolean todos;

    public Adapter(List<Ciudades> datos, Context context, Boolean todos)
    {
        this.todos=todos;
        this.datos = datos;
        this.context=context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.linea_recycler,parent,false);
        Holder holder=new Holder(view,context);
        view.setOnClickListener(this);
        view.setOnLongClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(todos)((Holder)holder).bind(datos.get(position));
        else
        {
            if(datos.get(position).numeroFotos>0)((Holder)holder).bind(datos.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void miOnLongClick(View.OnLongClickListener listener)
    {
        this.listenerLong=listener;
    }

    @Override
    public boolean onLongClick(View v) {
        if(listenerLong!=null) listenerLong.onLongClick(v);
        return false;
    }
    public void miOnClick(View.OnClickListener listener)
    {
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null) listener.onClick(v);

    }
}

class Holder extends  RecyclerView.ViewHolder {

    TextView nombreCiudad, numeroFotos;
    FrameLayout frameLayout;
    Context context;

    public Holder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        nombreCiudad = itemView.findViewById(R.id.nombreCiudad);
        numeroFotos = itemView.findViewById(R.id.numeroFotos);
        frameLayout = itemView.findViewById(R.id.fondo);
    }
    public  void bind(Ciudades ciudad)
    {
        nombreCiudad.setText(ciudad.nombre);
        numeroFotos.setText(Integer.toString(ciudad.numeroFotos));

        frameLayout.setBackground(Foto.convertirBitMapADrawable(ciudad.imagen,context ));

    }
}

