package com.lizeth.codigo.catalogo.Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.lizeth.codigo.catalogo.Control.Aplicacion;
import com.lizeth.codigo.catalogo.DataBase.DBHelper;
import com.lizeth.codigo.catalogo.DataBase.DB_Control;
import com.lizeth.codigo.catalogo.Control.JsonParser;
import com.lizeth.codigo.catalogo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by FAMILIA on 29/01/2016.    MENU CATEGORIAS
 */
public class MenuActivity extends Activity {
    DBHelper base = new DBHelper(this);
    ArrayAdapter<String> adaptador;
    private ListView listaa;
    String searchTerm;
    List<String> registro;
    String URL = "https://itunes.apple.com/us/rss/topfreeapplications/limit=20/json";

    Activity a;
    Context context;
    static ArrayList<Aplicacion> lista;
    JSONArray pers;
    DB_Control control= new DB_Control(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menuapp);
        lista = new ArrayList<Aplicacion>();
        a = this;
        context = getApplicationContext();
        listaa = (ListView) findViewById(R.id.listViewLista);
        new GetContacts(listaa).execute();
        listaa.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                 Intent i = new Intent(a, Resumenapp.class );
                i.putExtra("posicion", position);
                startActivity(i);
            }

        });

    }


    private class GetContacts extends AsyncTask<Void, Void, Void> {
        ListView list;
        private ProgressDialog pDialog;

        public GetContacts(ListView listaa) {
            this.list = listaa;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MenuActivity.this);
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


                    pers = jsonObj.getJSONArray("entry");


                    for (int i = 0; i < pers.length(); i++) {
                        JSONObject c = pers.getJSONObject(i);
                        Aplicacion e = new Aplicacion();
                        JSONObject app = (JSONObject) pers.get(i);

                        JSONObject imName = (JSONObject) app.get("im:name");
                        String label = imName.getString("label");
                        e.setName(label);
                        JSONArray imImage = (JSONArray) app.get("im:image");
                        if (imImage.length() >= 3) {
                            JSONObject imageUrl = (JSONObject) imImage.get(0);
                            String url = imageUrl.getString("label");
                            e.setUrlImag53(url);
                            imageUrl = (JSONObject) imImage.get(1);
                            url = imageUrl.getString("label");
                            e.setUrlImag75(url);
                            imageUrl = (JSONObject) imImage.get(2);
                            url = imageUrl.getString("label");
                            e.setUrlImag100(url);

                        }
                        JSONObject object = (JSONObject) app.get("summary");
                        label = object.getString("label");
                        e.setSummary(label);
                        object = (JSONObject) app.get("im:price");
                        object = (JSONObject) object.get("attributes");
                        double price = object.getDouble("amount");
                        e.setPrice(price);
                        label = object.getString("currency");
                        e.setCurrency(label);
                        object = (JSONObject) app.get("im:contentType");
                        object = (JSONObject) object.get("attributes");
                        label = object.getString("label");
                        e.setType(label);
                        object = (JSONObject) app.get("rights");
                        label = object.getString("label");
                        e.setRights(label);
                        object = (JSONObject) app.get("title");
                        label = object.getString("label");
                        e.setTitle(label);
                        object = (JSONObject) app.get("link");
                        object = (JSONObject) object.get("attributes");
                        label = object.getString("href");
                        e.setDownload(label);
                        object = (JSONObject) app.get("id");
                        object = (JSONObject) object.get("attributes");
                        long id = object.getLong("im:id");
                        e.setIdApp(id);
                        object = (JSONObject) app.get("im:artist");
                        label = object.getString("label");
                        e.setArtist(label);
                        object = (JSONObject) app.get("category");
                        object = (JSONObject) object.get("attributes");
                        label = object.getString("label");
                        e.setCategory(label);
                        object = (JSONObject) app.get("im:releaseDate");
                        object = (JSONObject) object.get("attributes");
                        label = object.getString("label");
                        e.setReleaseDate(label);


                        // adding contact to contact list
                        lista.add(e);

                    }
                    //Guardar en Base de datos
                    control.Insert_db(lista);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e("ServiceHandler", "Esta habiendo problemas para cargar el JSON");
                /// BUSCAR EN LA BD PARA CARGAR DATOS DE AHI

                control.Consulta_all();

             //Mostrar en lista
            }

            return null;
        }


        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing()){
                pDialog.dismiss();
            }
            new CargarListTask().execute();
        }
        //HILO PARA CARGAR LOS DATOS EN EL LISTVIEW
        class CargarListTask extends AsyncTask<Void,String,Adapter>{
            @Override
            protected void onPreExecute() {
                // TODO Auto-generated method stub
                super.onPreExecute();
            }

            protected Adapter doInBackground(Void... arg0) {
                // TODO Auto-generated method stub

                try{

                }catch(Exception ex){
                    ex.printStackTrace();
                }

                Adapter adaptador = new Adapter(a,lista);
                return adaptador;
            }

            @Override
            protected void onPostExecute(Adapter result) {
                // TODO Auto-generated method stub
                super.onPostExecute(result);
                listaa.setAdapter(result);

            }
        }
    }
}