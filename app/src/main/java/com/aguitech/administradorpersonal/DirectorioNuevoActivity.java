package com.aguitech.administradorpersonal;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;

public class DirectorioNuevoActivity extends AppCompatActivity {

    private EditText directorioNombre;
    private EditText directorioCelular;
    private EditText directorioTelefono;
    private EditText directorioNextel;
    private EditText directorioOficina;
    private EditText directorioEmail;
    private EditText directorioExtension;
    private Button directorioGuardar;

    private ProgressDialog pDialog;
    private HashMap<String,String> data;
    //private String url = "http://emocionganar.com/admin/panel/registro_android.php";
    private String url = "http://administradorpersonal.com/directorio/panel/directorio_nuevo.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directorio_nuevo);

        directorioNombre = (EditText) findViewById(R.id.directorioNombre);
        directorioCelular = (EditText) findViewById(R.id.directorioCelular);
        directorioTelefono = (EditText) findViewById(R.id.directorioTelefono);
        directorioNextel = (EditText) findViewById(R.id.directorioNextel);
        directorioOficina = (EditText) findViewById(R.id.directorioOficina);
        directorioEmail = (EditText) findViewById(R.id.directorioEmail);
        directorioExtension = (EditText) findViewById(R.id.directorioExtension);
        directorioGuardar = (Button) findViewById(R.id.directorioGuardar);

        directorioGuardar.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View v){
                new connectPhp().execute();

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public class connectPhp extends AsyncTask<String, String, String> {

        String directorioNombreValue = directorioNombre.getText().toString();
        String directorioCelularValue = directorioCelular.getText().toString();
        String directorioTelefonoValue = directorioTelefono.getText().toString();
        String directorioNextelValue = directorioNextel.getText().toString();
        String directorioOficinaValue = directorioOficina.getText().toString();
        String directorioEmailValue = directorioEmail.getText().toString();
        String directorioExtensionValue = directorioExtension.getText().toString();


        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            pDialog = new ProgressDialog(DirectorioNuevoActivity.this);
            pDialog.setIndeterminate(false);
            pDialog.setMessage("Connecting...");
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... params){
            //List <NameValuePair> args = new ArrayList<NameValuePair>();
            //args.add(new BasicNameValuePair("name", getEdittextValue));//this is key and value to post data
            data = new HashMap<String, String>();
            //data.put("name", getEdittextValue);
            //data.put("nombre", getEdittextValue);
            data.put("nombre", directorioNombreValue);
            data.put("celular", directorioCelularValue);
            data.put("telefono", directorioTelefonoValue);
            data.put("nextel", directorioNextelValue);
            data.put("oficina", directorioOficinaValue);
            data.put("email", directorioEmailValue);
            data.put("extension", directorioExtensionValue);

            SharedPreferences mispreferencias = getSharedPreferences("PreferenciasUsuario", Context.MODE_PRIVATE);
            //txtnombre.setText(mispreferencias.getString(“nombre”, “”));
            //Toast.makeText(getApplicationContext(), "Cargando Preferencias", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), mispreferencias.getString("Nombre", ""), Toast.LENGTH_SHORT).show();
            //Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();
            String IDusuario = mispreferencias.getString("ID", "");

            data.put("id_registro", IDusuario);

            try{

                //JSONObject json = jsonParser.makeHttpRequest(url, "POST", args);//to pass url, method, and args
                //now connect using JSONParsr class
                JSONObject json = HttpUrlConnectionParser.makehttpUrlConnection(url,data);
                int succ = json.getInt("success");//get response from server
                if(succ == 0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if(succ == 2) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Registro duplicado", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                if(succ == 1){
                    JSONArray jsonArray = json.getJSONArray("result");//get parent node

                    JSONObject child = jsonArray.getJSONObject(0);//get first child value
                    final String getValue = child.optString("reply");


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //result.setText(getValue.toString());
                            Toast.makeText(getApplicationContext(), "Funciona XD", Toast.LENGTH_SHORT).show();
                            //result.setText(getValue.toString());

                            Intent i = new Intent();
                            //i.setClass(MainActivity.this, PantallaActivity.class);
                            //i.setClass(MainActivity.this, RegistroActivity.class);



                            //i.setClass(DirectorioNuevoActivity.this, MenuPrincipalActivity.class);
                            i.setClass(DirectorioNuevoActivity.this, DirectorioActivity.class);
                            startActivity(i);
                            /*
                            Intent i = new Intent();
                            i.putExtra("Nombre", "Mi nombre es Hector");
                            //i.setClass(MainActivity.this, PantallaActivity.class);
                            //i.setClass(MainActivity.this, RegistroActivity.class);
                            i.setClass(RegistroActivity.this, RegistroActivity.class);
                            startActivity(i);
                            */
                        }
                    });
                }
                /*
                int succ = json.getInt("success");//get response from server
                if(succ == 0){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    JSONArray jsonArray = json.getJSONArray("result");//get parent node

                    JSONObject child = jsonArray.getJSONObject(0);//get first child value
                    final String getValue = child.optString("reply");

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //result.setText(getValue.toString());
                            Toast.makeText(getApplicationContext(), "Funciona XD", Toast.LENGTH_SHORT).show();
                            result.setText(getValue.toString());

                            Intent i = new Intent();
                            i.putExtra("Nombre", "Mi nombre es Hector");
                            //i.setClass(MainActivity.this, PantallaActivity.class);
                            //i.setClass(MainActivity.this, RegistroActivity.class);
                            i.setClass(MainActivity.this, RegistroActivity.class);
                            startActivity(i);
                        }
                    });
                }
                */
            }catch(Exception e){

            }

            /**
             * private EditText valueNombre;
             private EditText valueCelular;
             private EditText valueTelefonoCasa;
             private EditText valueEmail;
             private EditText valueEmailConfirm;
             private EditText valueEdad;
             private EditText valueCodigoPostal;
             private EditText valueUsername;
             private EditText valuePassword;
             private EditText valuePasswordConfirm;
             *
             try{

             //JSONObject json = jsonParser.makeHttpRequest(url, "POST", args);//to pass url, method, and args
             //now connect using JSONParsr class
             JSONObject json = HttpUrlConnectionParser.makehttpUrlConnection(url,data);
             int succ = json.getInt("success");//get response from server
             if(succ == 0){
             runOnUiThread(new Runnable() {
            @Override
            public void run() {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            }
            });
             }else{
             JSONArray jsonArray = json.getJSONArray("result");//get parent node

             JSONObject child = jsonArray.getJSONObject(0);//get first child value
             final String getValue = child.optString("reply");

             runOnUiThread(new Runnable() {
            @Override
            public void run() {
            //result.setText(getValue.toString());
            Toast.makeText(getApplicationContext(), "Funciona XD", Toast.LENGTH_SHORT).show();
            result.setText(getValue.toString());

            Intent i = new Intent();
            i.putExtra("Nombre", "Mi nombre es Hector");
            //i.setClass(MainActivity.this, PantallaActivity.class);
            //i.setClass(MainActivity.this, RegistroActivity.class);
            i.setClass(MainActivity.this, RegistroActivity.class);
            startActivity(i);
            }
            });
             }
             }catch(Exception e){

             }
             */


            return null;
        }

        @Override
        protected void onPostExecute(String a){
            super.onPostExecute(a);
            pDialog.cancel();
        }
    }

}
