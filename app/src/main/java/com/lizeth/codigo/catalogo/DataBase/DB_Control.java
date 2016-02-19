package com.lizeth.codigo.catalogo.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.lizeth.codigo.catalogo.Control.Aplicacion;

import java.util.ArrayList;

/**
 * Created by FAMILIA on 18/02/2016.
 */
public class DB_Control {
    public static final String Table_Name="aplicaciones";

    public static final String  Id="_Id";
    public static final String  Name="name";
    public static final String  Summary="summary";
    public static final String  Price="price";
    public static final String  Currency="currency";
    public static final String  Type="type";
    public static final String  Rights="rights";
    public static final String  Title="title";
    public static final String  Download="download";
    public static final String Artist="artist";
    public static final String Category="category";
    public static final String ReleaseDate="relaseDate";
    public static final String Imag="imag53";

    public static final String CREATE_TABLE=  "create table" +Table_Name+
                   "(" +Id +"integer primary key autoincrement,"
                       +Name+ "text not null,"
                       +Summary+ "text not null,"
                       +Price+ "text not null,"
                       +Currency+ "text not null,"
                       +Type+ "text not null,"
                       +Rights+ "text not null,"
                       +Title+ "text,"
                       +Download+ "text,"
                       +Artist+ "text,"
                       +Category+ "text,"
                       +ReleaseDate+ "text,"
                       +Imag+"  )";
    private DBHelper base;
    SQLiteDatabase db;

    public DB_Control(Context context) {
         base = new DBHelper(context);
         db = base.getWritableDatabase();

    }

    public void Insert_db(ArrayList<Aplicacion> e ){
        ContentValues registro = new ContentValues();
        for( int i = 0 ; i < e.size() ; i++ ){
        registro.put("name",(e.get(i)).getName());
            registro.put("summary", (e.get(i)).getSummary());
        registro.put("price",(e.get(i)).getPrice());
            registro.put("currency",(e.get(i)).getCurrency());
        registro.put("type",(e.get(i)).getType());
        registro.put("rights",(e.get(i)).getRights());
        registro.put("title",(e.get(i)).getTitle());
            registro.put("Download",(e.get(i)).getDownload());
        registro.put("artist",(e.get(i)).getArtist());
            registro.put("Category",(e.get(i)).getCategory());
            registro.put("releaseDate", (e.get(i)).getReleaseDate());
            registro.put("Imag", (e.get(i)).geturlImag53());
        db.insert(Table_Name, null, registro);


    } db.close();
  }

    public ArrayList<Aplicacion> Consulta_all(){

        ArrayList<Aplicacion> lista = new ArrayList<Aplicacion>();
        String[] valores_recuperar = {"nombre", "artist", "price", "download", "summary", "rights","imag53"};
        Cursor c = db.query("contactos", valores_recuperar,
                null, null, null, null, null, null);
        c.moveToFirst();
        do {
            Aplicacion contactos = new Aplicacion();

            contactos.setName(c.getString(0));
            contactos.setArtist(c.getString(1));
            contactos.setPrice(c.getDouble(2));
            contactos.setDownload(c.getString(3));
            contactos.setSummary(c.getString(4));
            contactos.setRights(c.getString(5));
            contactos.setUrlImag53(c.getString(6));

            lista.add(contactos);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista;
    }


    public ArrayList<Aplicacion> Consulta(String position){

        ArrayList<Aplicacion> lista = new ArrayList<Aplicacion>();
        String[] valores_recuperar = {"nombre", "artist", "price", "download", "summary", "rights","imag53"};
        Cursor c = db.query("contactos", valores_recuperar,"_id="+position+"", null, null, null, null);
        c.moveToFirst();
        do {
            Aplicacion contactos = new Aplicacion();

            contactos.setName(c.getString(0));
            contactos.setArtist(c.getString(1));
            contactos.setPrice(c.getDouble(2));
            contactos.setDownload(c.getString(3));
            contactos.setSummary(c.getString(4));
            contactos.setRights(c.getString(5));
            contactos.setUrlImag53(c.getString(6));

            lista.add(contactos);
        } while (c.moveToNext());
        db.close();
        c.close();
        return lista;
    }


}



//