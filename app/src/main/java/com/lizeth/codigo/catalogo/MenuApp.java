package com.lizeth.codigo.catalogo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

/**
 * Created by FAMILIA on 01/02/2016.       MENU DE APLICACIONES SEGUN UNA CATEGORIA SELECCIONADA
 */
public class MenuApp extends Activity {
    Sqlite base = new Sqlite(this, "ejemplo1", null, 1);
    String categoria;
    ListView lista;
    static ArrayList<String> Menu;
    ArrayAdapter<String> adaptador;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuapp);
        categoria = getIntent().getStringExtra("categoria");
        MostrarAplicaciones();
        MostrarDescripcion();


}
    public void  MostrarAplicaciones(){
        SQLiteDatabase db = base.getReadableDatabase();
       Cursor c=db.rawQuery("Select nombre,UrlImagen from Aplicacion where categoria="+categoria,null);

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {

                String nombre= c.getString(0);
                String urlimagen = c.getString(1);
            } while(c.moveToNext());
        }
        ///Mostrar en lista
    }
    private void MostrarDescripcion() {

        /// selecciona la lista en pantalla segun su ID
        ListView lista1 = (ListView) findViewById(R.id.listMenu);

        // registra una accion para el evento click
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                /// Obtiene el valor de la casilla elegida
                String itemSeleccionado = adapterView.getItemAtPosition(i).toString();

                // muestra un mensaje
                //Toast.makeText(getApplicationContext(), "Haz hecho click en " + itemSeleccionado, Toast.LENGTH_SHORT).show();
                Intent se = new Intent (MenuApp.this, Resumenapp.class);
                se.putExtra("elemento", itemSeleccionado);

                startActivity(se);
            }
        });

    }
}
