package com.example.joanmorales.actividad4;

import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class ActividadBotones extends AppCompatActivity {


    private TextView texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_botones);

        texto = (TextView) findViewById(R.id.textView);
    }

    public void cambiarMensaje(View v){
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        Date fechaActual = Calendar.getInstance().getTime();

        String s = formato.format(fechaActual);
        texto.setText(String.format("Botón presionado: %s", s));
    }
}

