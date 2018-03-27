package com.example.cetys.ahorcado;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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

    public void botonPulsado(View vista){
        Button boton = (Button) findViewById(vista.getId());
        boton.setEnabled(false);
    }



}
