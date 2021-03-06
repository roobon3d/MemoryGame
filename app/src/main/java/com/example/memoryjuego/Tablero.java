package com.example.memoryjuego;

import android.media.MediaPlayer;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class Tablero extends AppCompatActivity {

    int aciertos = 0;

    ImageView fondo;
    Animation fondoAnim;
    LinearLayout tableroLayout;
    LinearLayout pantallaLayout;
    Chronometer cronometro;
    LinearLayout cronoLayout;
    ImageView c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19;

    int avatares[];
    int trasera;
    ImageView[] cartas = new ImageView[20];


    int indexAnterior;

    boolean esPrimera = true;
    boolean esSegunda = false;
    boolean bloquear = false;
    boolean comienza = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);

        cargarImagenes();
        barajear(avatares, 20);
        barajear(avatares, 20);
        cargarCartas();
        dibujarCartas();
        animacionTablero();
        Log.d("MIAPP", "avatares " + avatares);


        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                voltearCartas();
            }
        }, 5000);

        //Creamos los onClicks
        setOnClicks();

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

    // coloca un avatar en cada imageview
    public void dibujarCartas() {

        for (int i = 0; i < cartas.length; i++) {

            cartas[i].setImageResource(avatares[i]);
        }
    }


    // voltea todas las cartas sin animacion
    public void voltearCartas() {

        for (int i = 0; i < cartas.length; i++) {

            cartas[i].setImageResource(R.drawable.cartatrasera);
        }

    }


    // Asignar los onClicks a todas las cartas y lanza comparar() en cada pulsación
    public void setOnClicks() {

        for (int i = 0; i < cartas.length; i++) {

            final int j = i;

            cartas[i].setEnabled(true);
            cartas[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(!comienza){
                        // comienza el juego empieza a contar el crono
                        comienza = true;
                        cronometro.setBase(SystemClock.elapsedRealtime());
                        cronometro.start();
                    }

                    if (!bloquear) {
                        if (esSegunda){
                            bloquear=true;
                        }
                        comparar((ImageView) v, j);


                    }
                }

            });
        }

    }

// ****************** COMPARAR LAS DOS CARTAS *******************

    public void comparar(final ImageView v, int i) {

        final int j = i;
        bloquear = true;

        if (esPrimera && !esSegunda) {



            // cuando se pulsa una carta la mostramos
            int index = animarCartaMostrar(cartas[j], j);

            indexAnterior = j;
            esSegunda = true;
            esPrimera = false;
            bloquear = false;


        } else {

            // cuando se pulsa una carta la mostramos
            final int index = animarCartaMostrar(cartas[j], j);
            //segundaPulsada.setImageDrawable(getResources().getDrawable(avatares[index]));
            final int imagen1 = avatares[indexAnterior];
            final int imagen2 = avatares[index];

            esPrimera = true;
            esSegunda = false;


            final Handler handler3 = new Handler();
            handler3.postDelayed(new Runnable() {
                @Override
                public void run() {


                    if (imagen1 == imagen2) {

                        aciertos++;
                        Log.d("MIAPP", "Aciertos" + aciertos);
                        cartas[index].setEnabled(false);
                        cartas[indexAnterior].setEnabled(false);
                        bloquear = false;

                    } else {

                        v.setEnabled(true);
                        cartas[j].setEnabled(true);
                        esPrimera = true;
                        // esperamos 1 segundos y la ocultamos de nuevo
                        final Handler handler3 = new Handler();
                        handler3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                animarCartaOcultar(cartas[j], j);
                                animarCartaOcultar(cartas[indexAnterior], indexAnterior);

                            }
                        }, 1000);

                    }


                }
            }, 100);




        }


    }

    //******* Animacion mostrar carta ********

    public int animarCartaMostrar(ImageView carta, int pos) {
        final ImageView v = carta;
        final int indice = pos;
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.pageflip01);

       // if (!bloquear) {
            bloquear = true;
            mp.start();
            // primer cuarto de vuelta
            v.animate().withLayer()
                    .rotationY(90)
                    .setDuration(800)
                    .withEndAction(
                            new Runnable() {
                                @Override
                                public void run() {

                                    v.setImageResource(avatares[indice]);

                                    // segundo cuarto de vuelta
                                    v.setRotationY(-90);
                                    v.animate().withLayer()
                                            .rotationY(0)
                                            .setDuration(100)
                                            .start();
                                }
                            }
                    ).start();


        //
        // }

        return indice;
    }

    //******* Animacion ocultar carta ********

    public void animarCartaOcultar(ImageView carta, int pos) {

        final ImageView v = carta;
        final int indice = pos;
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.pageflip03);

        // primer cuarto de vuelta
        v.animate().withLayer()
                .rotationY(-90)
                .setDuration(300)
                .withEndAction(
                        new Runnable() {
                            @Override
                            public void run() {

                                v.setImageResource(R.drawable.cartatrasera);

                                // segundo cuarto de vuelta
                                v.setRotationY(90);
                                v.animate().withLayer()
                                        .rotationY(0)
                                        .setDuration(400)
                                        .start();
                            }
                        }
                ).start();
        mp.start();
        bloquear=false;


    }

    // ******** Animacion introducción ***********
    public void animacionTablero() {
        fondo = (ImageView) findViewById(R.id.fonditoId);
        tableroLayout = findViewById(R.id.tableroId);
        pantallaLayout = findViewById(R.id.pantallaId);
        cronoLayout = findViewById(R.id.cronoLayoutId);
        cronometro = findViewById(R.id.cronometro);

        cronoLayout.setAlpha(0);
        fondo.setImageAlpha(0);

        fondoAnim = AnimationUtils.loadAnimation(this, R.anim.fondoanim);
        fondo.startAnimation(fondoAnim);


        tableroLayout.setAlpha(0);

        fondo.animate().setStartDelay(2400).setDuration(500).alpha(1);

        fondo.animate().setStartDelay(4000).translationYBy(-800).setDuration(900).alpha(0);

        tableroLayout.animate().setStartDelay(3500).translationYBy(-250).alpha(1).setDuration(1000);

        cronoLayout.animate().setStartDelay(3500).translationYBy(-250).alpha(1).setDuration(1000);

        cronoLayout.setVisibility(View.VISIBLE);




    }


}
