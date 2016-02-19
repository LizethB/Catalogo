package com.lizeth.codigo.catalogo.Activity;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;

import com.lizeth.codigo.catalogo.R;

/**
 * Created by FAMILIA on 29/01/2016.     SPLASH
 */
public class SplashScreenActivity extends Activity {

    protected boolean active = true;
    protected int splashTime = 1000;
    private static int SPLASH_TIEMPO = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
        ///////////////////////


        new Handler().postDelayed(new Runnable() {

			/*
			* Mostramos la pantalla de bienvenida con un temporizador.
			* De esta forma se puede mostrar el logo de la app o
			* compañia durante unos segundos.
			*/

            @Override
            public void run() {
                // Este método se ejecuta cuando se consume el tiempo del temporizador.

                Intent i = new Intent(SplashScreenActivity.this, MenuActivity.class);
                startActivity(i);


                finish();
            }
        }, SPLASH_TIEMPO);



   }
}
