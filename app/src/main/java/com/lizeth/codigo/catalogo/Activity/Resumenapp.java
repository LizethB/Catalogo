package com.lizeth.codigo.catalogo.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lizeth.codigo.catalogo.Control.Aplicacion;
import com.lizeth.codigo.catalogo.DataBase.DBHelper;
import com.lizeth.codigo.catalogo.DataBase.DB_Control;
import com.lizeth.codigo.catalogo.R;

import java.util.ArrayList;

/**
 * Created by FAMILIA on 02/02/2016. // DESCRIPCION DE LA APLICACION SELECCIONADA
 */
public class Resumenapp extends Activity {
    String nombre, titulo, descrip, precio, autor, categoria, url;
    DBHelper base = new DBHelper(this);
    DB_Control control = new DB_Control(this);
    String posicion;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumenapp);
        posicion = getIntent().getStringExtra("posicion");
        vista();


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.resumenapp, container, false);
    }

    public void vista() {

        ArrayList<Aplicacion> lista = new ArrayList<Aplicacion>();
        lista = control.Consulta(posicion);
        Aplicacion dir = lista.get(0);

        TextView nombre = (TextView)findViewById(R.id.Name);
        TextView autor = (TextView)findViewById(R.id.Author);
        TextView precio = (TextView)findViewById(R.id.Price);
        TextView descarga = (TextView)findViewById(R.id.Download);
        TextView descripcion = (TextView)findViewById(R.id.Summary);
        TextView derechos = (TextView)findViewById(R.id.Right);


        nombre.setText("" +dir.getName());
        autor.setText("Author:" +dir.getArtist());
        precio.setText("Price:" +dir.getPrice());
        descarga.setText("" +dir.getDownload());
        descripcion.setText("Description:" +dir.getSummary());
        derechos.setText("" +dir.getRights());


}


}

