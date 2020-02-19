package com.example.examenfebrero.FragmentsPKG;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.examenfebrero.Ciudades;
import com.example.examenfebrero.Clases.Foto;
import com.example.examenfebrero.DataBasePKG.BDAdapter;
import com.example.examenfebrero.InformacionDialogo;
import com.example.examenfebrero.MainActivity;
import com.example.examenfebrero.ProveedorServicios;
import com.example.examenfebrero.R;
import com.example.examenfebrero.Recycler;
import com.example.examenfebrero.Utilities.Adapter;
import com.example.examenfebrero.Utilities.AdapterComunidades;
import com.example.examenfebrero.VisualizadorImagenes;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ComunidadesFragment extends Fragment {

    List<Ciudades> listaCiudades;
    RecyclerView recyclerViewComunidades;
    AdapterComunidades adapterComunidades;
    Context contexto;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.comunidades_fragment, container, false);

        recyclerViewComunidades = rootView.findViewById(R.id.comunidadesRecyclerView);
        cargarCiudades();

        return rootView;
    }

    public void cargarCiudades() {
        ProveedorServicios proveedorServicios = crearRetrofit();
        Call<List<Ciudades>> responseCall = proveedorServicios.getCiudades();
        responseCall.enqueue(new Callback<List<Ciudades>>() {
            @Override
            public void onResponse(Call<List<Ciudades>> call, Response<List<Ciudades>> response) {
                List<Ciudades> usuariosResponse = response.body();
                if (usuariosResponse != null) {
                    listaCiudades = usuariosResponse;

                    cargarRecycler();
                } else Toast.makeText(getActivity(),response.message(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Ciudades>> call, Throwable t) {
                Log.e("Error", t.toString());
                Toast.makeText(getActivity(), "Error" + t.toString(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void cargarRecycler()
    {
        adapterComunidades = new AdapterComunidades(listaCiudades,getActivity());
        recyclerViewComunidades.setAdapter(adapterComunidades);
        recyclerViewComunidades.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));
    }

    private ProveedorServicios crearRetrofit() {
        // String url = "http://10.0.2.2/examenciudades/"; //Para avd de android conectada a apirest local
        //String url = "http://192.168.0.106/examenciudades/";  //Para móvil conectado a apirest local (poner la ip del pc)
        String url ="http://xusa.iesdoctorbalmis.info/examenciudades/"; //Para apirest en servidor ies, con telefono o avd con salida a internet
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(ProveedorServicios.class);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
