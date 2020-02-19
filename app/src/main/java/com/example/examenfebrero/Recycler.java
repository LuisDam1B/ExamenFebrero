package com.example.examenfebrero;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examenfebrero.Clases.Foto;
import com.example.examenfebrero.DataBasePKG.BDAdapter;
import com.example.examenfebrero.Utilities.Adapter;
import com.example.examenfebrero.Utilities.Utilidades;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Recycler extends Fragment {
    List<Ciudades> ciudades;
    RecyclerView recyclerView;
    Adapter adapter;
    ListView listaComunidades;
    BDAdapter bdAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState)  {
        View rootView = inflater.inflate(R.layout.recycler, container, false);
        recyclerView=rootView.findViewById(R.id.recycler);
        listaComunidades=rootView.findViewById(R.id.listaComunidades);
        setHasOptionsMenu(true);
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
                    ciudades = usuariosResponse;

                     bdAdapter=new BDAdapter(getActivity(),"bdimagenes",1);
                    for (int i=0; i<ciudades.size();i++)
                    {
                        ciudades.get(i).numeroFotos=bdAdapter.numeroImagenesConCodigoCiudad(ciudades.get(i).codigopostal);
                        if (ciudades.get(i).numeroFotos > 0)
                                ciudades.get(i).imagen= bdAdapter.seleccionarPrimeraImagenPorCodigoCiudad(ciudades.get(i).codigopostal);
                        else if(MainActivity.todos)
                                ciudades.get(i).imagen = Foto.convertirRecursoBitmap(R.drawable.sinimagen, getActivity());
                              else {ciudades.remove(i);i--;}
                    }
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
        adapter=new Adapter(ciudades,getActivity(),MainActivity.todos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.VERTICAL,false));

        adapter.miOnClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int codigoPostal=ciudades.get(recyclerView.getChildAdapterPosition(v)).codigopostal;
                ((MainActivity)getActivity()).cargarFragment(new VisualizadorImagenes(codigoPostal) );
            }
        });
        adapter.miOnLongClick(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               InformacionDialogo i=new InformacionDialogo(ciudades.get(recyclerView.getChildAdapterPosition(v)));
               i.show(getFragmentManager().beginTransaction(),null);
                return false;
            }
        });
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.opciones,menu);
       /* final MenuItem itemBusquedaComunidades = menu.findItem(R.id.busquedaComunidades);

        SearchView searchView=((SearchView)itemBusquedaComunidades.getActionView());
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
         @Override
         public boolean onClose() {
             listaComunidades.setVisibility(View.INVISIBLE);
             recyclerView.setVisibility(View.VISIBLE);
           //  searchView.clearFocus();
             itemBusquedaComunidades.collapseActionView();
             return true;
         }
     });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapterListView!=null) {
                    if (TextUtils.isEmpty(newText)) listaComunidades.clearTextFilter();
                    else listaComunidades.setFilterText(newText);
                }
                return true;
            }
        });*/

        super.onCreateOptionsMenu(menu, inflater);
    }
    private ArrayAdapter<String> cargarAdapter()
    {
       return new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Utilidades.nombreComunidadesAutonomas());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId())
        {
            /*
           case android.R.id.home:
                listaComunidades.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

                break;
            case R.id.busquedaComunidades:
                recyclerView.setVisibility(View.INVISIBLE);
                listaComunidades.setVisibility(View.VISIBLE);
                listaComunidades.setTextFilterEnabled(true);
                adapterListView=cargarAdapter();
                listaComunidades.setAdapter(adapterListView);
                listaComunidades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    }

                });
                break;*/
            case R.id.todos:
                MainActivity.todos=true;
                break;
            case R.id.confotos:
                MainActivity.todos=false;
                break;
        }
        cargarCiudades();
        return super.onOptionsItemSelected(item);
    }

}
