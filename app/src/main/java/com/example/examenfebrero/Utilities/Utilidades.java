package com.example.examenfebrero.Utilities;

import android.content.Context;

import com.example.examenfebrero.Clases.Comunidad;
import com.example.examenfebrero.Clases.Foto;
import com.example.examenfebrero.Clases.Imagen;
import com.example.examenfebrero.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Utilidades {

    public static ArrayList<Imagen> listaDeImagenesCiudades(Context context) {

        ArrayList<Imagen> imagenes = new ArrayList<Imagen>();
        imagenes.add(new Imagen(3, "Fotografía del puerto de Alicante desde el castillo de Santa Bárbara", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.marinacaltillo, context))));
        imagenes.add(new Imagen(3, "Fotografía del castillo de Santa Bárbara vista desde el paseo de la playa", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.catillopaseo, context))));
        imagenes.add(new Imagen(3, "Fotografía del castillo de Santa Bárbara desde la playa", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.playacastillo, context))));
        imagenes.add(new Imagen(30, "Fotografía de la catedral de Murcia", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.catedralmurcia, context))));
        imagenes.add(new Imagen(30, "Fotografía de la plaza de Santo Domingo en Murcia", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.plazasantodomingo, context))));
        imagenes.add(new Imagen(9, "Fotografía de la magnifica catedral de Burgos", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.burgoscatedral, context))));
        imagenes.add(new Imagen(9, "Fotografía del paseo de los plataneros en Burgos", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.burgospaseo, context))));
        imagenes.add(new Imagen(9, "Fotografía de la plaza del rey con fondo a la catedral de Burgos", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.plazadelrey, context))));
        imagenes.add(new Imagen(35, "Fotografía de figuras de arena en la playa de las canteras", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.playacanteras, context))));
        imagenes.add(new Imagen(35, "Fotografía preciosa del casco antiguo de gran canarias", Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.cascoantiguo, context))));

        return imagenes;
    }
    public static Imagen generarImagenAInserta(int codigoPostal, Context context)
    {
        List<Imagen>imagenes=new ArrayList<>();
        imagenes.add(new Imagen(codigoPostal,"Fotografía de paisaje de ...." ,Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.ciudad1,context))));
        imagenes.add(new Imagen(codigoPostal,"Fotografía de paisaje de ....",Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.ciudad2,context))));
        imagenes.add(new Imagen(codigoPostal,"Fotografía de paisaje de ....a",Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.ciudad3,context))));
        imagenes.add(new Imagen(codigoPostal,"Fotografía de paisaje de ....",Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.ciudad4,context))));
        imagenes.add(new Imagen(codigoPostal,"Fotografía de paisaje de ....",Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.ciudad5,context))));
        imagenes.add(new Imagen(codigoPostal,"Fotografía de paisaje de ....",Foto.ConvertirImagenString(Foto.convertirRecursoBitmap(R.drawable.ciudad6,context))));
        return  imagenes.get( (new Random()).nextInt(imagenes.size()-1));
    }

    public  static List<String> nombreComunidadesAutonomas()
    {
        List<String> nombresComunidades=new ArrayList<>();
        for (Comunidad c : comunidadesAutonomas())
        {
            if(!nombresComunidades.contains(c.nombreComunidad)) nombresComunidades.add(c.nombreComunidad);
        }
        return  nombresComunidades;
    }
    public static  ArrayList<Integer> codigosCiudadesComunidad(int codigoComunidad)
    {
        ArrayList<Integer> codigoCiudadesComunidad=new ArrayList<>();
        for (Comunidad c : comunidadesAutonomas())
        {
            if(c.codigoComunidad==codigoComunidad) codigoCiudadesComunidad.add(c.codigoPostalProvincia);
        }
        return  codigoCiudadesComunidad;
    }
    public static List<Comunidad> comunidadesAutonomas() {
        List<Comunidad> comunidades = Arrays.asList(new Comunidad[]{
                new Comunidad(01, "Andalucía", 04, "Almería"),
                new Comunidad(01, "Andalucía", 11, "Cádiz"),
                new Comunidad(01, "Andalucía", 14, "Córdoba"),
                new Comunidad(01, "Andalucía", 18, "Granada"),
                new Comunidad(01, "Andalucía", 21, "Huelva"),
                new Comunidad(01, "Andalucía", 23, "Jaén"),
                new Comunidad(01, "Andalucía", 29, "Málaga"),
                new Comunidad(01, "Andalucía", 41, "Sevilla"),
                new Comunidad(02, "Aragón", 22, "Huesca"),
                new Comunidad(02, "Aragón", 44, "Teruel"),
                new Comunidad(02, "Aragón", 50, "Zaragoza"),
                new Comunidad(03, "Principado de Asturias", 33, "Asturias"),
                new Comunidad(04, "Illes Balears", 07, "Illes Balears"),
                new Comunidad(05, "Canarias", 35, "Las Palmas"),
                new Comunidad(05, "Canarias", 38, "Santa Cruz de Tenerife"),
                new Comunidad(06, "Cantabria", 39, "Cantabria"),
                new Comunidad(07, "Castilla y León", 05, "Ávila"),
                new Comunidad(07, "Castilla y León", 9, "Burgos"),
                new Comunidad(07, "Castilla y León", 24, "León"),
                new Comunidad(07, "Castilla y León", 34, "Palencia"),
                new Comunidad(07, "Castilla y León", 37, "Salamanca"),
                new Comunidad(07, "Castilla y León", 40, "Segovia"),
                new Comunidad(07, "Castilla y León", 42, "Soria"),
                new Comunidad(07, "Castilla y León", 47, "Valladolid"),
                new Comunidad(07, "Castilla y León", 49, "Zamora"),
                new Comunidad(8, "Castilla La Mancha", 02, "Albacete"),
                new Comunidad(8, "Castilla La Mancha", 13, "Ciudad Real"),
                new Comunidad(8, "Castilla La Mancha", 16, "Cuenca"),
                new Comunidad(8, "Castilla-La Mancha", 19, "Guadalajara"),
                new Comunidad(8, "Castilla-La Mancha", 45, "Toledo"),
                new Comunidad(9, "Cataluña", 8, "Barcelona"),
                new Comunidad(9, "Cataluña", 17, "Girona"),
                new Comunidad(9, "Cataluña", 25, "Lleida"),
                new Comunidad(9, "Cataluña", 43, "Tarragona"),
                new Comunidad(10, "Comunitat Valenciana", 03, "Alicante/Alacant"),
                new Comunidad(10, "Comunitat Valenciana", 12, "Castellón/Castelló"),
                new Comunidad(10, "Comunitat Valenciana", 46, "Valencia/València"),
                new Comunidad(11, "Extremadura", 06, "Badajoz"),
                new Comunidad(11, "Extremadura", 10, "Cáceres"),
                new Comunidad(12, "Galicia", 15, "A Coruña"),
                new Comunidad(12, "Galicia", 27, "Lugo"),
                new Comunidad(12, "Galicia", 32, "Ourense"),
                new Comunidad(12, "Comunidad de Madrid", 28, "Madrid"),
                new Comunidad(14, "Región de, Murcia", 30, "Murcia"),
                new Comunidad(15, "Comunidad Foral de Navarra", 31, "Navarra"),
                new Comunidad(16, "País Vasco", 01, "Araba/Álava"),
                new Comunidad(16, "País Vasco", 48, "Bizkaia"),
                new Comunidad(16, "País Vasco", 20, "Gipuzkoa"),
                new Comunidad(17, "La Rioja", 26, "La Rioja"),
                new Comunidad(18, "Ceuta", 51, "Ceuta"),
                new Comunidad(19, "Melilla", 52, "Melilla")});
        return comunidades;
    }
}

