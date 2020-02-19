package com.example.examenfebrero;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.examenfebrero.DataBasePKG.BDAdapter;
import com.example.examenfebrero.FragmentsPKG.ComunidadesFragment;
import com.example.examenfebrero.FragmentsPKG.ImagenesCiudadesFragment;
import com.example.examenfebrero.Utilities.Utilidades;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener,
            ComunidadesFragment.OnFragmentInteractionListener{

    public static Boolean todos;
    DrawerLayout mDrawerLaout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        mDrawerLaout = findViewById(R.id.drawler_layout);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        insertarDatosBD();
        todos=true;
        cargarFragment(new Usuariocontrasenya());
    }

    void cargarFragment(Fragment fragment){
        FragmentManager FM = getSupportFragmentManager();
        FragmentTransaction FT = FM.beginTransaction();

        FT.replace(R.id.contenedor, fragment);
        FT.addToBackStack(null);
        FT.commit();
    }

    private void insertarDatosBD()
    {
        new BDAdapter(this,"bdimagenes",1).insertarImagenes(Utilidades.listaDeImagenesCiudades(this));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        Fragment miFragment = null;
        boolean fragmentSeleccionado = false;

        switch (id){
            case R.id.navigation_item_ciudades:
                miFragment = new ImagenesCiudadesFragment();
                fragmentSeleccionado = true;
                break;
            case R.id.navigation_item_comunidad:
                miFragment = new ComunidadesFragment();
                fragmentSeleccionado = true;
                break;
            default:
                break;
        }

        if (fragmentSeleccionado == true){
            cargarFragment(miFragment);
        }


        return false;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
