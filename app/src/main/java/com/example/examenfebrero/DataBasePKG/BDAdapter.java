package com.example.examenfebrero.DataBasePKG;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;

import com.example.examenfebrero.Clases.Foto;
import com.example.examenfebrero.Clases.Imagen;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xusa on 07/01/2018.
 */

public class BDAdapter {

    BDImagenesOH bdImagenes;


    public  BDAdapter(Context context, String name,int version)
    {
        bdImagenes =new BDImagenesOH(context,name, null,version);
    }


    public Cursor seleccionarImagenes()
    {
        SQLiteDatabase auxBD= bdImagenes.getReadableDatabase();
        Cursor cursor=auxBD.rawQuery("Select * from imagenes", null);
        return  cursor;
    }
    public Cursor seleccionarImagenesPorCodigoCiudad(int codigo)
    {
        SQLiteDatabase auxBD= bdImagenes.getReadableDatabase();
        Cursor cursor=auxBD.rawQuery("Select * from imagenes where codigopostal="+codigo, null);
        return  cursor;
    }
    public Bitmap seleccionarPrimeraImagenPorCodigoCiudad(int codigo)
    {
        Cursor cursor=seleccionarImagenesPorCodigoCiudad(codigo);
        if(cursor!=null) cursor.moveToFirst();
        return Foto.getFromBitmapBytes(cursor.getBlob(3));
    }
    public int numeroImagenesConCodigoCiudad(int codigo)
    {
        Cursor cursor=seleccionarImagenesPorCodigoCiudad(codigo);
        int numeroImagenes=cursor.getCount();
        cursor.close();
        return numeroImagenes;
    }

    public void insertarImagenes(List<Imagen> imagenes)
    {
        SQLiteDatabase auxBD= bdImagenes.getWritableDatabase();
        auxBD.delete("imagenes",null,null);
        for (Imagen x:imagenes)
        {
          auxBD.insert("imagenes",null, crearContentValuesImagen(x))   ;
        }
        auxBD.close();
    }
    public void insertarImagen(Imagen imagen)
    {
        SQLiteDatabase auxBD= bdImagenes.getWritableDatabase();

            auxBD.insert("imagenes",null, crearContentValuesImagen(imagen))   ;

        auxBD.close();
    }


    public void eliminarImagen(int codigoPotal)
    {
        SQLiteDatabase auxBD= bdImagenes.getWritableDatabase();
        auxBD.execSQL("DELETE FROM imagenes WHERE ID="+ codigoPotal);
        auxBD.close();
    }
    
     ContentValues crearContentValuesImagen(Imagen imagen)
    {
        ContentValues values=new ContentValues();
       // values.put("id",imagen.id);
        values.put("codigopostal",imagen.codigopostal);
        values.put("descripcion",imagen.descripcion);
        values.put("imagen",Foto.getBytesFromBitmap(Foto.convertirStringBitmap(imagen.imagen)));
        return  values;
    }

    class BDImagenesOH extends SQLiteOpenHelper {

        Context context;

        public BDImagenesOH(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            this.context=context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE imagenes (ID  INTEGER PRIMARY KEY  AUTOINCREMENT NOT NULL, codigopostal INTEGER, descripcion TEXT, imagen BLOB);");


        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

    }
}
