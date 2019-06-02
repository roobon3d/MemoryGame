package com.example.memoryjuego;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.util.Property;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Random;

public class Tablero extends AppCompatActivity {

    int aciertos = 0;

    ImageView fondo;
    Animation fondoAnim;
    LinearLayout tableroLayout;
    LinearLayout pantallaLayout;

    ImageView c0, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10, c11, c12, c13, c14, c15, c16, c17, c18, c19;

    int avatares[];
    int trasera;
    ImageView[] cartas = new ImageView[20];

    ImageView primeraPulsada;
    ImageView segundaPulsada;
    int indexAnterior;

    boolean primera = true;
    boolean esSegunda = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);


        cargarImagenes();
        barajear(avatares, 20);
        cargarCartas();
        dibujarCartas();
        animacionTablero();
        Log.d ("MIAPP", "avatares " + avatares);







        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                voltearCartas();

            }
        }, 6000);

      /*  final Handler handler2 = new Handler();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                animarCartaMostrar(cartas[0],0);

            }
        }, 7000);*/

      /*  final Handler handler3 = new Handler();
        handler3.postDelayed(new Runnable() {
            @Override
            public void run() {
                animarCartaOcultar(cartas[0],0);

            }
        }, 11000);*/

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


    // Asignar los onClicks a todas las cartas
    public void setOnClicks() {

        for (int i = 0; i < cartas.length; i++) {

            final int j=i;

            cartas[i].setEnabled(true);
            cartas[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // cuando se pulsa una carta la mostramos

                   if (primera && !esSegunda) {
                       primeraPulsada = (ImageView) v;
                       // cuando se pulsa una carta la mostramos
                       animarCartaMostrar(cartas[j],j);
                       indexAnterior=j;
                       v.setEnabled(false);
                       esSegunda = true;


                       //TODO continuar aqui
                   } else{
                      segundaPulsada = (ImageView) v;
                       // cuando se pulsa una carta la mostramos
                       animarCartaMostrar(cartas[j],j);
                       v.setEnabled(false);
                       primera = true;

                   }

                   if (primeraPulsada==segundaPulsada){

                       aciertos++;
                       Log.d("MIAPP", "Aciertos" + aciertos);


                   } else{
                       if (!primera && esSegunda) {
                           v.setEnabled(true);
                           cartas[j].setEnabled(true);
                           primera = true;
                           // esperamos 2 segundos y la ocultamos de nuevo
                           final Handler handler3 = new Handler();
                           handler3.postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   animarCartaOcultar(cartas[j], j);
                                   animarCartaOcultar(cartas[indexAnterior], indexAnterior);

                               }
                           }, 2000);
                       }
                   }

                   //

                }
            });
        }

    }



    public void animarCartaMostrar(ImageView carta,int pos){

        final ImageView v = carta;
        final int indice = pos;
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.pageflip01);

        mp.start();
// primer cuarto de vuelta
        v.animate().withLayer()
                .rotationY(90)
                .setDuration(800)
                .withEndAction(
                        new Runnable() {
                            @Override public void run() {

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

    }

    public void animarCartaOcultar(ImageView carta,int pos){

        final ImageView v = carta;
        final int indice = pos;
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.pageflip03);

// primer cuarto de vuelta
        v.animate().withLayer()
                .rotationY(-90)
                .setDuration(300)
                .withEndAction(
                        new Runnable() {
                            @Override public void run() {

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
