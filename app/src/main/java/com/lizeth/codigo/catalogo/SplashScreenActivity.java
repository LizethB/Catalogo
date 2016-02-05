package com.lizeth.codigo.catalogo;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.Toast;

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
       // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
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
                // Se pasa a la activity principal
                Intent i = new Intent(SplashScreenActivity.this, Resumenapp.class);
                startActivity(i);

                // Cerramos esta activity
                finish();
            }
        }, SPLASH_TIEMPO);


        ////////////////////////////////////////////////////////////////////////////////

       /* Thread splashThread = new Thread(){
            @Override
            public void run(){
                try{
                    int waited = 0;
                    while(active && (waited < splashTime)){
                        sleep(100);
                        if(active){
                            waited += 100;
                        }

                    }
                } catch(InterruptedException e){
                    Toast.makeText(getApplicationContext(), "error ", Toast.LENGTH_SHORT).show();


                } finally{
                    Toast.makeText(getApplicationContext(), "finally1 " , Toast.LENGTH_SHORT).show();

                    openApp();
                    interrupt();
                    Toast.makeText(getApplicationContext(), "finally2 " , Toast.LENGTH_SHORT).show();
                }

            }
        };
        Toast.makeText(getApplicationContext(), "start1 " , Toast.LENGTH_SHORT).show();
        splashThread.start();
        Toast.makeText(getApplicationContext(), "start2" , Toast.LENGTH_SHORT).show();


    }

    private void openApp(){
        finish();
        Toast.makeText(getApplicationContext(), "open1 " , Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, CategoActivity.class));
        Toast.makeText(getApplicationContext(), "open2" , Toast.LENGTH_SHORT).show();
*/
   }
}
