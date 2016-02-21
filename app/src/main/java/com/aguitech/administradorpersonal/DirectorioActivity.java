package com.aguitech.administradorpersonal;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

//public class DirectorioActivity extends AppCompatActivity implements Download_data_directorio.download_complete {
public class DirectorioActivity extends AppCompatActivity {

    public ListView list_directorio;
    public ArrayList<Countries> countries = new ArrayList<Countries>();
    public ListAdapterDirectorio adapterDirectorio;

    public String getNombreValue = "";
    public String getIDValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directorio);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            //String getNombreValue =(String) b.get("Nombre");
            getNombreValue =(String) b.get("Nombre");
            //Textv.setText(j);
            //Toast.makeText(getApplicationContext(), getNombreValue, Toast.LENGTH_SHORT).show();
            //String getIDValue =(String) b.get("ID");
            getIDValue =(String) b.get("ID");

            //Toast.makeText(getApplicationContext(), getIDValue, Toast.LENGTH_SHORT).show();
        }

        list_directorio = (ListView) findViewById(R.id.list_directorio);
        adapterDirectorio = new ListAdapterDirectorio(this);
        list_directorio.setAdapter(adapterDirectorio);

        list_directorio.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            //public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //String position_val =  position.toString();
                //Toast.makeText(getApplicationContext(), position_val, Toast.LENGTH_SHORT).show();
                //int yourtag1 = Log.d("Yourtag", position);
                String valor_posicion = String.valueOf(position);
                /*
                Toast.makeText(getApplicationContext(), "algun valor", Toast.LENGTH_SHORT).show();
                HashMap<String, Object> obj_nuevo = (HashMap<String, Object>) adapter.getItem(position);
                String name = (String) obj_nuevo.get("name");
                Log.d("Yourtag", name);
                Log.d("Position", adapter.getItem(position).toString());
                */
                //Toast.makeText(getApplicationContext(), , Toast.LENGTH_SHORT).show();
                Log.d("ROWSELECT", "" + id);
                Log.d("ROWSELECT2", "" + position);

                Intent i = new Intent();
                i.putExtra("Nombre", getNombreValue);
                i.putExtra("ID", getIDValue);
                i.putExtra("Dios", "Mi nombre es Hector");
                i.putExtra("IDBlog", valor_posicion);
                //i.setClass(DirectorioActivity.this, BlogDetalleActivity.class);
                startActivity(i);
                /**
                 HashMap<String, Object> obj = (HashMap<String, Object>) adapter.getItem(position);
                 String name = (String) obj.get("name");
                 Toast.makeText(getApplicationContext(), name, Toast.LENGTH_SHORT).show();
                 *
                 *
                 Object obj = list.getAdapter().getItem(position);
                 String valueID = obj.toString();

                 Toast.makeText(getApplicationContext(), valueID, Toast.LENGTH_SHORT).show();
                 */
                //Log.d("MyLog", "Value is: "+value);
                //String name = // how code to get name value.
            }


        });

        Download_data_directorio download_data = new Download_data_directorio((Download_data_directorio.download_complete) this);
        //download_data.download_data_from_link("http://www.kaleidosblog.com/tutorial/tutorial.json");
        //download_data.download_data_from_link("https://emocionganar.com/admin/panel/webservice_evento_android.php");
        download_data.download_data_from_link("https://emocionganar.com/admin/panel/webservice_blog_android_nuevo.php");

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

}
