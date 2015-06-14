package com.example.cleveland.finding;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cleveland on 06-06-2015.
 */

public class BaseDeDatos {



    public  final void BaseDeDatos() {




    }

    public ArrayList<Double> getCoordenadas() {

       ArrayList<Double> coordenadas = new ArrayList<Double>();


        coordenadas.add(0 , -36.827012);
        coordenadas.add(1 , -73.063538);

        coordenadas.add(2 , -36.819077);
        coordenadas.add(3 , -73.059332);

        coordenadas.add(4 , -36.795815);
        coordenadas.add(5 , -73.05263);
        coordenadas.add(6 , -36.795815);
        coordenadas.add(7 , -73.05263);

        coordenadas.add(8 , -36.786467);
        coordenadas.add(9 , -73.097848);

        coordenadas.add(10 , -36.77973);
        coordenadas.add(11 , -73.087548);

        coordenadas.add(12 , -36.785504);
        coordenadas.add(13 , -73.10660);

        coordenadas.add(14 , -36.808735);
        coordenadas.add(15 , -73.027638);

        coordenadas.add(16 , -36.841301);
        coordenadas.add(17 , -73.050641);


        return coordenadas;
    }

    public String[] getstring() {
        String Locales[] = new String[9];
        Locales[0] = "Manso sadwish1";
        Locales[1] = "Manso sadwish2";
        Locales[2] = "Manso sadwish3";
        Locales[3] = "Manso sadwish4";
        Locales[4] = "Manso sadwish5";
        Locales[5] = "Manso sadwish6";
        Locales[6] = "Manso sadwish7";
        Locales[7] = "Manso sadwish8";
        Locales[8] = "Manso sadwish9";


        //Log.d("LOCALES1", Locales[0]);
        String[] c =  Locales;

        return Locales;
    }

}