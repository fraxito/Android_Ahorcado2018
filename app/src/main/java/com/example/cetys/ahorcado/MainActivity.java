package com.example.cetys.ahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String palabraOculta = "CETYS";
    int numeroDeFallos = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.ventanaJuego, new VentanaAhorcado())
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        palabraOculta = escogePalabra();
        String barras = "";
        for (int i = 0; i < palabraOculta.length(); i++) {
            barras += "_ ";
        }
        ((TextView) findViewById(R.id.palabraConGuiones)).setText(barras);
    }

    public void botonPulsado(View vista){
        Button boton = (Button) findViewById(vista.getId());
        boton.setEnabled(false);
        chequeaLetra(boton.getText().toString());
    }

    private void chequeaLetra(String letra){
        letra = letra.toUpperCase();
        ImageView imagenAhorcado = ((ImageView) findViewById(R.id.imagenAhorcado));
        TextView textoGuiones = ((TextView) findViewById(R.id.palabraConGuiones));
        String palabraConGuiones = textoGuiones.getText().toString(); //lee el texto que hay en _ _ _ _ _
        boolean acierto = false;

        for (int i=0; i<palabraOculta.length(); i++){
            if (palabraOculta.charAt(i) == letra.charAt(0)){
                palabraConGuiones = palabraConGuiones.substring(0, 2*i)
                        + letra
                        + palabraConGuiones.substring(2*i+1);
                acierto = true;
            }
        }
        if (!palabraConGuiones.contains("_")){
            imagenAhorcado.setImageResource(R.drawable.acertastetodo);
        }
        //actualizo el valor que se muestra en la pantalla con las letras
        //adivinadas
        textoGuiones.setText(palabraConGuiones);

        if (!acierto){
            numeroDeFallos++;
            switch (numeroDeFallos){
                case 0 : imagenAhorcado.setImageResource(R.drawable.ahorcado_0); break;
                case 1 : imagenAhorcado.setImageResource(R.drawable.ahorcado_1); break;
                case 2 : imagenAhorcado.setImageResource(R.drawable.ahorcado_2); break;
                case 3 : imagenAhorcado.setImageResource(R.drawable.ahorcado_3); break;
                case 4 : imagenAhorcado.setImageResource(R.drawable.ahorcado_4); break;
                case 5 : imagenAhorcado.setImageResource(R.drawable.ahorcado_5); break;
                default : imagenAhorcado.setImageResource(R.drawable.ahorcado_fin); break;
            }
        }
    }

    private String escogePalabra(){
        String palabra = "CETYS";
        String[] listaPalabras = {"hola","adios","esternocleidomastoideo","piña","CETYS"};
        Random random = new Random();
        palabra = listaPalabras[random.nextInt(listaPalabras.length)];
        palabra = palabra.toUpperCase();
        return palabra;
    }



}
