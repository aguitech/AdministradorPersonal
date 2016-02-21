package com.aguitech.administradorpersonal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class CerrarSesionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cerrar_sesion);

        SharedPreferences mispreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = mispreferencias.edit();
        //String nombre = txtnombre.getText().toString();
        //editor.putString(“nombre”, nombre);
        editor.putString("ID", "");
        editor.putString("Nombre", "");

        editor.commit();


        Toast.makeText(getApplicationContext(), "Cerrando Sesion", Toast.LENGTH_SHORT).show();

        CargarPreferencias();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void CargarPreferencias(){
        //SharedPreferences mispreferencias = getSharedPreferences("PreferenciasUsuario", Context.NODE_PRIVATE);
        SharedPreferences mispreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
        //txtnombre.setText(mispreferencias.getString(“nombre”, “”));
        Toast.makeText(getApplicationContext(), "Cargando Preferencias", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), mispreferencias.getString("Nombre", ""), Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), mispreferencias.getString("ID", ""), Toast.LENGTH_SHORT).show();
        String IDUsuario = mispreferencias.getString("ID", "");
        Toast.makeText(getApplicationContext(), "Cargando Preferencias Iniciales", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), IDUsuario, Toast.LENGTH_SHORT).show();

        if(IDUsuario == ""){
            Toast.makeText(getApplicationContext(), "Tenemos sesion activa", Toast.LENGTH_SHORT).show();

            Intent i = new Intent();
            i.putExtra("Nombre", "Mi nombre es Hector");
            //i.setClass(MainActivity.this, PantallaActivity.class);
            //i.setClass(MainActivity.this, RegistroActivity.class);
            //i.setClass(PreRegistroActivity.this, RegistroActivity.class);
            i.setClass(CerrarSesionActivity.this, PreRegistroActivity.class);
            startActivity(i);

        }

    }

}
