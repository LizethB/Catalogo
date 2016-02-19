package com.lizeth.codigo.catalogo.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by FAMILIA on 02/02/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String DBName="catalogo";
    private static final int Version_Schema=1;

    public DBHelper(Context contexto) {
        super(contexto, DBName, null, Version_Schema);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Se ejecuta la sentencia SQL de creación de la tabla
        db.execSQL(DB_Control.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva) {


        db.execSQL("DROP TABLE IF EXISTS Aplicacion");

        //db.execSQL();
    }
}
