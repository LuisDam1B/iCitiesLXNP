package com.example.icitieslxnp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class BDAdapter{

    private DBCiudades bdCiudades;

    public BDAdapter(Context contexto){
        bdCiudades = new DBCiudades(contexto,"BDCiudades",null,1);
        Ciudad ciudad = new Ciudad("Alicante","España");

        insertarUnDato(ciudad,sqLiteDatabase);
    }

    public void insertarDatos(){
        ArrayList<Ciudad> listaciudades = new ArrayList<Ciudad>();
        listaciudades.add(new Ciudad("Alicante","España"));
        listaciudades.add(new Ciudad("Madrid","España"));
        listaciudades.add(new Ciudad("Valencia","España"));
        SQLiteDatabase sqlCiudades = bdCiudades.getWritableDatabase();

        if (sqlCiudades != null){

            for (Ciudad ciudad: listaciudades) {
                String sentencia="INSERT INTO ciudades (nombre, pais) VALUES" +
                        " ('"+ciudad.getNombre()+"','"+ciudad.getPais()+"');";
                sqlCiudades.execSQL(sentencia);
            }

            sqlCiudades.close();

        }
    }

    public void insertarUnDato(Ciudad ciudad,SQLiteDatabase sqLiteDatabase){

        sqLiteDatabase = bdCiudades.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre",ciudad.getNombre());
        contentValues.put("pais",ciudad.getPais());

        sqLiteDatabase.insert("ciudades",null,contentValues);
        sqLiteDatabase.close();

    }

    public Cursor consultarDatos()
    {
        SQLiteDatabase  sqLiteDatabase = bdCiudades.getReadableDatabase();
        if(sqLiteDatabase!=null) {

            return sqLiteDatabase.rawQuery("select  nombre, pais from ciudades", null);
        }
        return null;
    }

    public void cerrarBD()
    {
        bdCiudades.close();
    }



    class DBCiudades extends SQLiteOpenHelper{



        String sentencia = "create table if not exists ciudades (nombre VARCHAR PRIMARY KEY NOT NULL, pais VARCHAR);";

        public DBCiudades(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(sentencia);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }




}
