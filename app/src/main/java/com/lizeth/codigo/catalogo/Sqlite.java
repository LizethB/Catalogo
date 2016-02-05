package com.lizeth.codigo.catalogo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FAMILIA on 02/02/2016.
 */
public class Sqlite extends SQLiteOpenHelper {

    String sqlCreate= "create table Aplicacion(id integer primary key, nombre text, titulo text,descripcion text,precio integer, autor text,categoria text, urlimagen text)";

    public Sqlite(Context contexto, String nombre,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {


        db.execSQL("DROP TABLE IF EXISTS Aplicacion");

        db.execSQL(sqlCreate);
    }
}
