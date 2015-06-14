package com.example.cleveland.finding;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.util.Timer;

public class MainActivity extends ActionBarActivity {
        private Timer t;
        private int sec; //Segundos antes de pasar al Mapa
        private RelativeLayout rl;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_title);

            rl = (RelativeLayout)findViewById(R.id.Rltitle);

            rl.setBackgroundColor(Color.rgb(70, 170, 188));
            final ImageButton b = (ImageButton) findViewById(R.id.imageButton);

            b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //Creamos el Intent
                    Bundle b1 = new Bundle();


                    Intent i = new Intent(MainActivity.this, Categorias.class);
                    startActivity(i);
                }

            });
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
