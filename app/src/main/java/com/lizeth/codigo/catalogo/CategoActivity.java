package com.lizeth.codigo.catalogo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FAMILIA on 29/01/2016.    MENU CATEGORIAS
 */
public class CategoActivity extends Activity {
    Sqlite base = new Sqlite(this,
            "ejemplo1", null, 1);
    ArrayAdapter<String> adaptador;
    private ListView listaa;
    String searchTerm;
    List<String> registro;
    String URL = "https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";

    Activity a;
    Context context;
    static ArrayList<Aplicacion> lista;
    JSONArray pers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categ);


        lista = new ArrayList<Aplicacion>();
        a = this;
        context = getApplicationContext();
        listaa = (ListView) findViewById(R.id.listView);
        new GetContacts(listaa).execute();

    }



    ////
    private class GetContacts extends AsyncTask<Void, Void, Void> {
        ListView list;
        private ProgressDialog pDialog;

        public GetContacts(ListView listaa) {
            this.list = listaa;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(CategoActivity.this);
            pDialog.setMessage("Getting Data ...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) { //// leer los datos de la aplicacion y las categorias
            // CREAMOS LA INSTANCIA DE LA CLASE
            JsonParser sh = new JsonParser();

            String jsonStr = sh.makeServiceCall(URL, JsonParser.GET);


            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Getting JSON Array node
                    pers = jsonObj.getJSONArray("entry");

                    // looping through All Equipos
                    for (int i = 0; i < pers.length(); i++) {
                        JSONObject c = pers.getJSONObject(i);

                        String descripcion = c.getString("summary");
                        String name = c.getString("name");
                        String titulo = c.getString("title");
                        String precio = c.getString("price");
                        String autor = c.getString("autor");
                        String categoria = c.getString("categoria");
                        String url = c.getString("image");



                        Aplicacion e = new Aplicacion();

                        e.setNombre(name);
                        e.setDescripcion(descripcion);

                        // adding contact to contact list
                        lista.add(e);

                    }
                    //Guardar en Base de datos

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Esta habiendo problemas para cargar el JSON");
                /// BUSCAR EN LA BD PARA CARGAR DATOS DE AHI
                SQLiteDatabase db = base.getReadableDatabase();
                Cursor c=db.rawQuery("Select DISTINCT categoria from Aplicacion ", null);

                //Nos aseguramos de que existe al menos un registro
                if (c.moveToFirst()) {
                    //Recorremos el cursor hasta que no haya más registros
                    do {
                        registro.add(c.getString(0));
                    } while(c.moveToNext());
                }
             //Mostrar en lista
            }

            return null;
        }

        /*
                @Override
                protected void onPostExecute(Void result) {
                    super.onPostExecute(result);
                    // Dismiss the progress dialog
                    if (pDialog.isShowing()) {
                        pDialog.dismiss();
                    }
                     new CargarListTask().execute();
                }
                //HILO PARA CARGAR LOS DATOS EN EL LISTVIEW
                 class CargarListTask extends AsyncTask<Void,String,Adapter> {
                    @Override
                    protected void onPreExecute() {
                        // TODO Auto-generated method stub
                        super.onPreExecute();
                    }

                }

        */
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_spla, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.action_settings) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }
    }
}
