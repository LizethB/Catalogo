package com.lizeth.codigo.catalogo;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by FAMILIA on 02/02/2016. // DESCRIPCION DE LA APLICACION SELECCIONADA
 */
public class Resumenapp extends Activity {
    String nombre,titulo,descrip,precio,autor,categoria,url;
    Sqlite base = new Sqlite(this, "ejemplo1", null, 1);
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resumenapp);
        String elemento = getIntent().getStringExtra("elemento");
        BuscaElemento(elemento);
}

public void BuscaElemento(String a) {
    SQLiteDatabase db = base.getReadableDatabase();
    Cursor c = db.rawQuery("Select nombre,titulo,descripcion,precio,autor,categoria,urlimagen from Aplicacion where nombre=" + a + "", null);

//Nos aseguramos de que existe al menos un registro
    if (c.moveToFirst()) {
        //Recorremos el cursor hasta que no haya más registros
        do {
            nombre = c.getString(0);
            titulo = c.getString(1);
            descrip = c.getString(2);
            precio = c.getString(3);
            autor = c.getString(4);
            categoria = c.getString(5);
            url = c.getString(6);

        } while (c.moveToNext());
    }

    TextView nombrev = (TextView) findViewById(R.id.nombre);
    nombrev.setText(nombre);
    TextView titulov = (TextView) findViewById(R.id.titulo);
    titulov.setText("TITULO:" + titulo);
    TextView descripcionv = (TextView) findViewById(R.id.descripcion);
    descripcionv.setText("DESCRIPCION:" + descrip);
    TextView preciov = (TextView) findViewById(R.id.precio);
    preciov.setText("PRECIO:" + precio);
    TextView autorv = (TextView) findViewById(R.id.autor);
    autorv.setText("AUTOR:" + autor);
    TextView categoriav = (TextView) findViewById(R.id.categoria);
    categoriav.setText(categoria);
    ImageView img = (ImageView) findViewById(R.id.imageView1);
    if (img != null) {
        new LoadImage(img).execute(url);
    }
}
    class LoadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public LoadImage(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                mIcon11 = BitmapFactory.decodeStream((InputStream) new URL(urldisplay).getContent());

            } catch (Exception e) {
                //Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }


}

