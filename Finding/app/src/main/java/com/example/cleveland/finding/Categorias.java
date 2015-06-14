package com.example.cleveland.finding;

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import java.util.ArrayList;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.Timer;
import java.util.TimerTask;

public class Categorias extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categorias);

        final ImageButton b = (ImageButton) findViewById(R.id.imageButton2);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Librerias();

            }

        });
    }

    public void Librerias(){


                //Creamos el Intent
                Bundle b1 = new Bundle();


                Intent i = new Intent(Categorias.this, Mapa.class);
                ArrayList<Object[]> Datainfo2 = new ArrayList<Object[]>();
                Datainfo2= Data();
                i.putExtra("Datainfo",Datainfo2);
                startActivity(i);



    }
    public ArrayList<Object[]> Data(){


        BaseDeDatos bd = new BaseDeDatos();
        ArrayList<Double> coor = bd.getCoordenadas();
        String[] Locales = bd.getstring();

        System.out.println("LOCALES: " + Locales);
        ArrayList<Object[]> Datainfo = new ArrayList<Object[]>();
        int o=0;
        for(int i=0;i<Locales.length;i++ ) {
            Object[] temp = {Locales[i],coor.get(o),coor.get(o+1)};
            o++;
            o++;
            Datainfo.add(i,temp);
        }


        return Datainfo;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
