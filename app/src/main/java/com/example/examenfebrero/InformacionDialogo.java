package com.example.examenfebrero;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.examenfebrero.Clases.Comunidad;
import com.example.examenfebrero.Utilities.Utilidades;

import java.util.ArrayList;

public class InformacionDialogo extends DialogFragment {
    String provincias;
    String comunidad;

    public InformacionDialogo(Ciudades ciudad)
    {
        int codigoComunidadEncontrada=-1;

        provincias="";
        for (Comunidad c: Utilidades.comunidadesAutonomas())
            if(ciudad.codigopostal==c.codigoPostalProvincia) {
                codigoComunidadEncontrada = c.codigoComunidad;
                comunidad=c.nombreComunidad;
            }
        for (Comunidad c:Utilidades.comunidadesAutonomas())
           if(c.codigoComunidad==codigoComunidadEncontrada)
                    provincias+=c.nombrPRovincia+"\n";
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view=inflater.inflate(R.layout.informaciondialogo, null);
        TextView comunidad=view.findViewById(R.id.comunidautonoma);
        TextView provincias=view.findViewById(R.id.listaDeProvincias);
        comunidad.setText(this.comunidad);
        provincias.setText(this.provincias);
        builder.setTitle("Información de la Comunidad");

        builder.setView(view)
                // Add action buttons
                .setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                    }
                });
        return builder.create();
    }
}
