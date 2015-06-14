package com.example.cleveland.finding;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Cleveland on 21-05-2015.
 */
public class Mapa  extends FragmentActivity {
    private int dismiss = 0, dismiss2 = 0, ReqGPS = 0, ReqSwitch = 0;
    private LocationManager locManager;
    private LocationListener locListener;
    private RelativeLayout config;
    private LinearLayout Lay1;
    private EditText EdSearch;
    private ImageButton loc;
    ListView myList;
    //  private Switch swmodo,swvista;
    private boolean searching = false;
    String provider;
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    LocationManager mLocationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
        // Lay1 = (LinearLayout)findViewById(R.id.Lsearch);


        config = (RelativeLayout) findViewById(R.id.Rlconfig);
       /* swmodo = (Switch)findViewById(R.id.Swmodo);
        swvista = (Switch)findViewById(R.id.Swvista);
       */
        //  Lay1.setBackgroundColor(Color.argb(102, 70, 170, 188));
        //  Lay1.setX(730);
        ArrayList<Object[]> Datainfo = new ArrayList<Object[]>();
        Datainfo = (ArrayList<Object[]>) getIntent().getExtras().getSerializable("Datainfo");
        String myStringArray[];
        Log.d("INFORMACION1", Datainfo.toString());

        myStringArray = new String[8];
        Location loc1[] = new Location[9];
        System.out.println("miralo" +GetLocation());
        Location loc = GetLocation();
        Location locaux = loc;
        ArrayList<Object[]> temp2 = new ArrayList<Object[]>();
        float temp3 = -1;
        float temp4 = -1;
        ArrayList<Local> LOCALES = new ArrayList<Local>();
        int aux = 0;

        for (int i = 0; i < Datainfo.size(); i++) {
            Object[] temp = Datainfo.get(i);


            loc1[i] = new Location("temp");

            loc1[i].setLatitude((double) temp[1]);
            loc1[i].setLongitude((double) temp[2]);


            LOCALES.add(new Local((String) temp[0].toString(), loc1[i]));

            //myMap.put(loc.distanceTo(loc1[i]),loc1[i]);



            MarkerOptions marker = new MarkerOptions().position(new LatLng((double) temp[1], (double) temp[2])).title((String) temp[0]);
            mMap.addMarker(marker);
        }
        final ArrayList<Local> LOCALES2 = new ArrayList<Local>();
        for (int i = 0; i < LOCALES.size(); i++) {
            for (int j = 0; j < LOCALES.size(); j++) {
                System.out.println("CASAPAN jota  " + j);
                System.out.println("CASAPAN size  " +  LOCALES.size());

                locaux = LOCALES.get(j).loc;


                temp4 = loc.distanceTo(locaux);

                if (temp4 < temp3 || temp3 == -1) {
                    System.out.println("CASAPAN jota  " + j);

                    aux = j;
                    System.out.println("CASAPAN aux  " + aux);
                    temp3=temp4;

                }

            }
            System.out.println("CASAPAN LOCALES2 ANTES  "+aux + LOCALES2.toString());
            System.out.println("CASAPAN LOCALES ANTES  " + LOCALES.toString());
            LOCALES2.add(LOCALES.get(aux));
            LOCALES.remove(aux);
            i=0;
            temp3=-1;

            System.out.println("CASAPAN LOCALES2 des  " + LOCALES2.toString());
            System.out.println("CASAPAN LOCALES des  " + LOCALES.toString());
        }

        // Arrays.sort(loc1);
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(new LatLng(LOCALES2.get(0).loc.getLatitude(), LOCALES2.get(0).loc.getLongitude()))
                        .zoom(14)
                        .build();


        mMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition),
                100,
                new GoogleMap.CancelableCallback() {

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onCancel() {
                    }
                }


        );

        for (int i = 0; i < LOCALES2.size(); i++) {
            myStringArray[i] =new String( LOCALES2.get(i).name);
            System.out.println("jojojoj"+myStringArray.length+" ... "+myStringArray[i]);
            System.out.println("jojojoj"+LOCALES2.size()+" ... ");
        }
        System.out.println("jojojoj"+myStringArray.length+" ... "+myStringArray[1]);
        ListView lista1 = (ListView) findViewById(R.id.list_item);
        ArrayAdapter<String> myAdapter = new
                ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                myStringArray
                );

        myList = (ListView)
                findViewById(R.id.listView);
        myList.setAdapter(myAdapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String itemValue = (String) myList.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "NAME :" + itemPosition + "  ListItem : " + itemValue, Toast.LENGTH_LONG)
                        .show();

            }

        });


        double latitude = -36.82901;
        double longitude = -73.043911;

        LatLng ltlng = new LatLng(latitude, longitude);
// create marker
        MarkerOptions marker2 = new MarkerOptions().position(new LatLng(latitude, longitude)).title("Rengo Lientur 102");

// Changing marker icon
        //  marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcherweb));

// adding marker

    }

    private Location convertLatLngToLocation(LatLng latLng) {
        Location location = new Location("someLoc");
        location.setLatitude(latLng.latitude);
        location.setLongitude(latLng.longitude);
        return location;
    }

    private float bearingBetweenLatLngs(LatLng beginLatLng, LatLng endLatLng) {
        Location beginLocation = convertLatLngToLocation(beginLatLng);
        Location endLocation = convertLatLngToLocation(endLatLng);
        return beginLocation.bearingTo(endLocation);
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private Location GetLocation() { //Creamos el Intent
        mMap.setMyLocationEnabled(true);
        //  hola= map.getMyLocation().getLongitude();
        //    hola2	=   map.getMyLocation().getLatitude();

        //sd.setHint("Mi ubicaci�n");
        //Obtenemos una referencia al LocationManager
//        System.out.println("MY location  "+mMap.getMyLocation().toString());
        locManager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);


        //Obtenemos la �ltima posici�n conocida
        Criteria criteria = new Criteria();
        provider = locManager.getBestProvider(criteria, false);
        Location loc =
                locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        System.out.println("cazuela"+locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
        System.out.println("cazuela11"+locManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER));
        System.out.println("cazuela2"+locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
        System.out.println("cazuela3"+locManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER));
        System.out.println("cazuela3"+locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        System.out.println("CRITERIA"+provider);
        if(provider!=null && !provider.equals("")){
            System.out.println("ENTRO");
            // Get the location from the given provider
            loc = locManager.getLastKnownLocation(provider);
            System.out.println("location"+loc.toString());


        }else{
            Toast.makeText(getBaseContext(), "No Provider Found", Toast.LENGTH_SHORT).show();
        }
        if (loc == null){System.out.println("NO se pudo1");
            loc = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);}
        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {
            }
            @Override
            public void onProviderDisabled(String arg0) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProviderEnabled(String provider) {
                locManager.removeUpdates(locListener);
                System.out.println("cazuela"+locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER));
                System.out.println("cazuela11"+locManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER));
                System.out.println("cazuela2"+locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER));
                System.out.println("cazuela3"+locManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER));
                System.out.println("cazuela3"+locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));

            }
            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // TODO Auto-generated method stub
            }
        };
        locManager.requestLocationUpdates(
                LocationManager.GPS_PROVIDER, 30000, 0, locListener);
        System.out.println("CAZUELA##"+locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
        if (loc != null) {
            double latitude = loc.getLatitude();
            double longitude = loc.getLongitude();
            String g = Double.toString(loc.getLatitude()) + "," + Double.toString(loc.getLongitude());
            //String g ="asdas";
         //   atvPlaces.setText(g);
            ReqGPS = 1;

        } else {
            System.out.println("NO se pudo");

            mMap.setMyLocationEnabled(true);

            locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

            loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            locListener = new LocationListener() {
                public void onLocationChanged(Location location) {  	    	}
                @Override
                public void onProviderDisabled(String arg0) {
                    // TODO Auto-generated method stub
                }
                @Override
                public void onProviderEnabled(String provider) {
                    // TODO Auto-generated method stub
                }
                @Override
                public void onStatusChanged(String provider, int status,
                                            Bundle extras) {
                    // TODO Auto-generated method stub
                }
            };

            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locListener);

            if ( loc !=  null )
            {
                double latitude = loc . getLatitude();
                double longitude = loc . getLongitude ();
                LatLng ltlng = new LatLng(latitude, longitude);



            }
        }
           // atvPlaces.setText("Ubicaci�n no disponible GPS no est� activado");


        // txtNombre.setText(s);
        return loc;
    }




    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        mMap.setMyLocationEnabled(true);

        locManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);

        Location loc = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        locListener = new LocationListener() {
            public void onLocationChanged(Location location) {  	    	}
            @Override
            public void onProviderDisabled(String arg0) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onProviderEnabled(String provider) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStatusChanged(String provider, int status,
                                        Bundle extras) {
                // TODO Auto-generated method stub
            }
        };

        locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 0, locListener);

        if ( loc !=  null )
        {
            double latitude = loc . getLatitude();
            double longitude = loc . getLongitude ();
            LatLng ltlng = new LatLng(latitude, longitude);

            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
            mMap.addMarker(new MarkerOptions().position(ltlng).title("You are here"));

        }
    }
}

