package com.example.examenfebrero;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.examenfebrero.Clases.Comunidad;
import com.example.examenfebrero.Clases.Foto;
import com.example.examenfebrero.DataBasePKG.BDAdapter;
import com.example.examenfebrero.Utilities.Utilidades;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class VisualizadorImagenes extends Fragment implements View.OnClickListener {
    int codigoPostal;
    ImageView imagenCiudad;
    TextView nombreCiudad, descripcionCiudad;
    ImageButton botonAvanzar, botonRetroceso;
    FloatingActionButton añadirImagen;
    Cursor cursor;
    BDAdapter bdAdapter;
    View rootView;
    public  VisualizadorImagenes(int codigoCiudad)
    {
        this.codigoPostal=codigoCiudad;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        rootView = inflater.inflate(R.layout.visualizadorimagenes, container, false);
        cursor=null;
        imagenCiudad=rootView.findViewById(R.id.imagenCiudad);
        nombreCiudad=rootView.findViewById(R.id.nombreCiudad);
        descripcionCiudad=rootView.findViewById(R.id.descripcionCiudad);
        rootView.findViewById(R.id.avanzarImagen).setOnClickListener(this);
        rootView.findViewById(R.id.retrocederImagen).setOnClickListener(this);
        rootView.findViewById(R.id.añadirImagen).setOnClickListener(this);

        nombreCiudad.setText(nombreCiudad());
        bdAdapter=new BDAdapter(getActivity(),"bdimagenes",1);
        seleccionarImagenesParaMostrar();
        registerForContextMenu(imagenCiudad);
        return rootView;
    }

    private void seleccionarImagenesParaMostrar()
    {
        if(bdAdapter.numeroImagenesConCodigoCiudad(codigoPostal)==0) imagenCiudad.setImageResource(R.drawable.sinimagen);
        else {
            cursor = bdAdapter.seleccionarImagenesPorCodigoCiudad(codigoPostal);
            if (cursor != null) {
                cursor.moveToFirst();
                mostrarInformacion(cursor);
            }
        }
    }
    private void mostrarInformacion(Cursor cursor)
    {
        descripcionCiudad.setText(cursor.getString(2));
        imagenCiudad.setImageBitmap(Foto.getFromBitmapBytes(cursor.getBlob(3)));
    }

    private String nombreCiudad()
    {
        for(Comunidad c : Utilidades.comunidadesAutonomas()) if(c.codigoPostalProvincia==codigoPostal) return  c.nombrPRovincia;
        return  "NO EXISTE CIUDAD";
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getActivity().getMenuInflater().inflate(R.menu.contexto,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

       if(item.getItemId()==R.id.eliminar) {
         Snackbar snackbar=Snackbar.make(rootView.findViewById(R.id.view), "Desea eliminar la imagen?", Snackbar.LENGTH_LONG);
                   snackbar.setActionTextColor(getResources().getColor(android.R.color.holo_red_dark))
                   .setAction("Aceptar", new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           if (cursor != null) {
                           int codigoCiudad=cursor.getInt(0);
                           bdAdapter.eliminarImagen(codigoCiudad);
                           seleccionarImagenesParaMostrar();}
                       }
                   }).show();
           }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.añadirImagen:
                bdAdapter.insertarImagen(Utilidades.generarImagenAInserta(codigoPostal,getActivity()));
                seleccionarImagenesParaMostrar();
                break;
            case R.id.avanzarImagen:
                if(cursor!=null) if(!cursor.isLast())
                {
                    cursor.moveToNext();
                    mostrarInformacion(cursor);
                }
                break;
            case R.id.retrocederImagen:
                if(cursor!=null) if(!cursor.isFirst()) {
                    cursor.moveToPrevious();
                    mostrarInformacion(cursor);
                }
                break;
        }
    }
}
