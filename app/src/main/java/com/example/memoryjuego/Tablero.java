package com.example.memoryjuego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.util.Property;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class Tablero extends AppCompatActivity {

    ImageView fondo;
    Animation fondoAnim;
    LinearLayout tableroLayout;
    LinearLayout pantallaLayout;

    ImageView c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19;

    int avatares[];
    int trasera;
    ImageView[] cartas = new ImageView[20];

   // Handler handler = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);


        cargarImagenes();
        barajear(avatares, 20);
        cargarCartas();
        dibujarCartas();
        animacionTablero();


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                voltearCartas();

            }
        }, 6000);

       animarCarta(cartas[0]);



    }


    // cargo un array de avatares con los png
    public void cargarImagenes() {
        avatares = new int[]{
                R.drawable.avatar1,
                R.drawable.avatar2,
                R.drawable.avatar3,
                R.drawable.avatar4,
                R.drawable.avatar5,
                R.drawable.avatar6,
                R.drawable.avatar7,
                R.drawable.avatar8,
                R.drawable.avatar9,
                R.drawable.avatar10,
                R.drawable.avatar1,
                R.drawable.avatar2,
                R.drawable.avatar3,
                R.drawable.avatar4,
                R.drawable.avatar5,
                R.drawable.avatar6,
                R.drawable.avatar7,
                R.drawable.avatar8,
                R.drawable.avatar9,
                R.drawable.avatar10
        };
        trasera = R.drawable.cartatrasera;
    }

    // cargo un array de con los id de los ImageViews
    public void cargarCartas() {
        c0 = (ImageView) findViewById(R.id.carta1);
        cartas[0] = c0;
        c1 = (ImageView) findViewById(R.id.carta2);
        cartas[1] = c1;
        c2 = (ImageView) findViewById(R.id.carta3);
        cartas[2] = c2;
        c3 = (ImageView) findViewById(R.id.carta4);
        cartas[3] = c3;
        c4 = (ImageView) findViewById(R.id.carta5);
        cartas[4] = c4;
        c5 = (ImageView) findViewById(R.id.carta6);
        cartas[5] = c5;
        c6 = (ImageView) findViewById(R.id.carta7);
        cartas[6] = c6;
        c7 = (ImageView) findViewById(R.id.carta8);
        cartas[7] = c7;
        c8 = (ImageView) findViewById(R.id.carta9);
        cartas[8] = c8;
        c9 = (ImageView) findViewById(R.id.carta10);
        cartas[9] = c9;
        c10 = (ImageView) findViewById(R.id.carta11);
        cartas[10] = c10;
        c11 = (ImageView) findViewById(R.id.carta12);
        cartas[11] = c11;
        c12 = (ImageView) findViewById(R.id.carta13);
        cartas[12] = c12;
        c13 = (ImageView) findViewById(R.id.carta14);
        cartas[13] = c13;
        c14 = (ImageView) findViewById(R.id.carta15);
        cartas[14] = c14;
        c15 = (ImageView) findViewById(R.id.carta16);
        cartas[15] = c15;
        c16 = (ImageView) findViewById(R.id.carta17);
        cartas[16] = c16;
        c17 = (ImageView) findViewById(R.id.carta18);
        cartas[17] = c17;
        c18 = (ImageView) findViewById(R.id.carta19);
        cartas[18] = c18;
        c19 = (ImageView) findViewById(R.id.carta20);
        cartas[19] = c19;
    }

    //Funcion para mezclar el array de ImageViews cartas[]
    public void barajear(int cartas[], int n) {


        Random random = new Random();

        for (int i = 0; i < n; i++) {
            int r = random.nextInt(n - i);
            int tmp = cartas[r];
            cartas[r] = cartas[i];
            cartas[i] = tmp;
        }
    }

    public void dibujarCartas() {

        for (int i = 0; i < cartas.length; i++) {

            cartas[i].setImageResource(avatares[i]);
        }
    }

    public void voltearCartas() {

        for (int i = 0; i < cartas.length; i++) {

            cartas[i].setImageResource(R.drawable.cartatrasera);
        }

    }


    public void animarCarta(ImageView carta){

        final ImageView v = carta;

// primer cuarto de vuelta
        v.animate().withLayer()
                .rotationY(90)
                .setDuration(8800)
                .withEndAction(
                        new Runnable() {
                            @Override public void run() {

                                cartas[0].setImageResource(avatares[0]);

                                // segundo cuarto de vuelta
                                v.setRotationY(-90);
                                v.animate().withLayer()
                                        .rotationY(0)
                                        .setDuration(8800)
                                        .start();
                            }
                        }
                ).start();

    }





    public void animacionTablero() {
        fondo = (ImageView) findViewById(R.id.fonditoId);
        tableroLayout = findViewById(R.id.tableroId);
        pantallaLayout = findViewById(R.id.pantallaId);

        fondo.setImageAlpha(0);

        fondoAnim = AnimationUtils.loadAnimation(this, R.anim.fondoanim);
        fondo.startAnimation(fondoAnim);


        tableroLayout.setAlpha(0);

        fondo.animate().setStartDelay(2400).setDuration(500).alpha(1);

        fondo.animate().setStartDelay(4000).translationYBy(-800).setDuration(900).alpha(0);

        tableroLayout.animate().setStartDelay(3500).translationYBy(-250).alpha(1).setDuration(1200);
    }


}
