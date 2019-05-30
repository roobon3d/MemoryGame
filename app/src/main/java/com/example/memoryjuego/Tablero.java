package com.example.memoryjuego;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Tablero extends AppCompatActivity {
    
    ImageView fondo;
    Animation fondoAnim;
    LinearLayout tableroLayout;
    LinearLayout pantallaLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablero);

        fondo = (ImageView) findViewById(R.id.fonditoId);
        tableroLayout = findViewById(R.id.tableroId);
        pantallaLayout = findViewById(R.id.pantallaId);

        fondo.setImageAlpha(0);

        fondoAnim = AnimationUtils.loadAnimation(this,R.anim.fondoanim);
        fondo.startAnimation(fondoAnim);



        tableroLayout.setAlpha(0);

        fondo.animate().setStartDelay(2400).setDuration(500).alpha(1);

        fondo.animate().setStartDelay(4000).translationYBy(-800).setDuration(900).alpha(0);

        tableroLayout.animate().setStartDelay(3500).translationYBy(-200).alpha(1).setDuration(1200);

        int altoPantalla = pantallaLayout.getHeight();
        int altoTablero = tableroLayout.getHeight();





    }
}
