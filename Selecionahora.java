package com.example.samipc.mysidurapp1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.DisplayMetrics;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samipc.mysidurapp1.HebrewDate.HebrewDate;

import java.util.Calendar;

public class Selecionahora extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    DrawerLayout drawer;
    private ActionBarDrawerToggle mToggle;

    int pag;
    int hora;
    int dia;
    int mes;
    int año;
    int rezo;
    int diadesemana;
    String libro;
    Calendar calendar;
    Button botonshajrit;
    Button botonminja;
    Button botonarvit;
    Button botonerevshabat;
    Button botontodo;
    TextView verhora;
    TextView yomview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionahora);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        botonshajrit = (Button) findViewById(R.id.botonshajrit);
        botonminja = (Button) findViewById(R.id.botonminja);
        botonarvit = (Button) findViewById(R.id.botonarvit);
        botonerevshabat = (Button) findViewById(R.id.botonerevshabat);
        botontodo = (Button) findViewById(R.id.birkatamazon);
        verhora = (TextView) findViewById(R.id.verhora);
        yomview = (TextView) findViewById(R.id.yomview);

        calendar= Calendar.getInstance();
        hora = calendar.get(Calendar.HOUR_OF_DAY);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        diadesemana = calendar.get(Calendar.DAY_OF_WEEK);
        mes =calendar.get(Calendar.MONTH);
        año = calendar.get(Calendar.YEAR);

        mes = mes + 1;
        verhora.setText(dia + "/"+ mes+ "/"+ año);

        HebrewDate date = new HebrewDate();
        yomview.setText(date.getHebrewDateAsString());

        Intent intent = getIntent();
        int allprayes = intent.getIntExtra(SelecionRezo.ALLPRAYES, -1);

        if (diadesemana == 6) {
            showButtonsShabat ();
        } else {
            showButtonsEveryDay();
        }

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
//        mDrawerLayout.addDrawerListener(mToggle);
////        mToggle.syncState();
//
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(mToggle);

        mToggle.syncState();


     //   Toast toast = Toast.makeText(this, "dia de la semana ,  hora y todo " + diadesemana + "  " + hora + " " + yomview, Toast.LENGTH_LONG);
     //   toast.show();


    //     if (diadesemana == 6) {
    //          botonerevshabat.setVisibility(View.VISIBLE);
    //       } else {
    //          botonerevshabat.setVisibility(View.INVISIBLE);
    //      }
    //
    //
    //        if (todo == 0 && hora > 11 && hora < 18) {
    //            if (diadesemana == 6) {
    //
    //                botonshajrit.setVisibility(View.INVISIBLE);
    //                botonminja.setVisibility(View.INVISIBLE);
    //                botonerevshabat.setVisibility(View.VISIBLE);
    //                botonarvit.setVisibility(View.INVISIBLE);
    //            } else {
    //                botonshajrit.setVisibility(View.INVISIBLE);
    //                botonminja.setVisibility(View.VISIBLE);
    //                botonerevshabat.setVisibility(View.INVISIBLE);
    //                botonarvit.setVisibility(View.VISIBLE);
    //            }
    //
    //        } else {
    //            if (todo == 0 &&  hora > 17) {
    //
    //                if (diadesemana == 6) {
    //
    //                    botonshajrit.setVisibility(View.INVISIBLE);
    //                    botonminja.setVisibility(View.INVISIBLE);
    //                    botonerevshabat.setVisibility(View.VISIBLE);
    //                    botonarvit.setVisibility(View.INVISIBLE);
    //                } else {
    //                    botonshajrit.setVisibility(View.INVISIBLE);
    //                    botonminja.setVisibility(View.INVISIBLE);
    //                    botonerevshabat.setVisibility(View.INVISIBLE);
    //                    botonarvit.setVisibility(View.VISIBLE);
    //                 }
    //            }

                if (allprayes == 1) {

                    botonshajrit.setVisibility(View.VISIBLE);
                    botonminja.setVisibility(View.VISIBLE);
                    botonarvit.setVisibility(View.VISIBLE);
                    botonerevshabat.setVisibility(View.VISIBLE);

                }

    //       }


    }

    private void showButtonsEveryDay (){
        Intent intent = getIntent();
        int todo = intent.getIntExtra(SelecionRezo.ALLPRAYES, -1);

        botonerevshabat.setVisibility(View.INVISIBLE);

        if (todo == 0 && hora < 12){
            botonshajrit.setVisibility(View.VISIBLE);
            botonminja.setVisibility(View.VISIBLE);
            botonarvit.setVisibility(View.VISIBLE);
            } else {
                if (todo == 0 && hora > 11 && hora < 18) {
                    botonshajrit.setVisibility(View.INVISIBLE);
                    botonminja.setVisibility(View.VISIBLE);
                    botonarvit.setVisibility(View.VISIBLE);

                }else {
                    if (todo == 0 &&  hora > 17) {
                        botonshajrit.setVisibility(View.INVISIBLE);
                        botonminja.setVisibility(View.INVISIBLE);
                        botonarvit.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    private void showButtonsShabat (){
        Intent intent = getIntent();
        int todo = intent.getIntExtra(SelecionRezo.ALLPRAYES, -1);

        botonarvit.setVisibility(View.INVISIBLE);
        botonminja.setVisibility(View.INVISIBLE);

        if (todo == 0 && hora < 12){
            botonshajrit.setVisibility(View.VISIBLE);
            botonerevshabat.setVisibility(View.VISIBLE);

        } else {
            if (todo == 0 && hora > 11) {
                botonerevshabat.setVisibility(View.VISIBLE);
            }
        }
    }


    public void leershajrit (View view)
    {
        Intent intent = new Intent(this, SeleccionaRezoShajrit.class);
        startActivity(intent);

    }

    public void leerminja (View view)
    {
        pag = 20;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        rezo = 2;
        libro = "sidduraskminjainv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

    public void leerarvit (View view)
    {
        pag = 55;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        rezo = 3;
        libro = "siduraskarvinv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

    public void leererevshabat (View view)
    {
        Intent intent = new Intent(this, SelecionErevShabat.class);
        startActivity(intent);
    }

    public void leerbirkathamazon (View view)
    {
        pag = 8;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        rezo = 3;
        libro = "bircatamazoninv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

       @Override
    public void onBackPressed() {
//           drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//
//           mToggle = new ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close);
////           drawer.addDrawerListener(mToggle);
//           mToggle.syncState();
//           getSupportActionBar().setDisplayHomeAsUpEnabled(true);

           if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
          super.onBackPressed();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.selecionahora, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.kadishAlIsrael) {
            pag = 0;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            libro = "Kadishderabanan.pdf";
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);
        }

        if (id == R.id.kadishYatom) {
            pag = 0;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            libro = "Kadishyatom.pdf";
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);
        }

        if (id == R.id.tefilathaderej) {
            pag = 0;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            libro = "tefilataderej.pdf";
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);
        }

        if (id == R.id.tefilathaderejtrans) {
            pag = 0;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            libro = "tefilataderejtrans.pdf";
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);
        }

        if (id == R.id.kidushsahabttrnas) {
            pag = 0;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            libro = "kidushshabattrans.pdf";
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);
        }

        if (id == R.id.convertdate) {

            Intent intent = new Intent(this, HebrewDateConverter.class);
            startActivity(intent);
        }

        if (id == R.id.about) {
            Intent intent = new Intent(this, About.class);
            startActivity(intent);


//            setContentView(R.layout.content_about);
//            DisplayMetrics dm = new DisplayMetrics();
//            getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//            int width = dm.widthPixels;
//            int heigh = dm.heightPixels;
//
//            getWindow().setLayout((int) (width * 0.8), (int) (heigh * 0.6));

        }

        if (id == R.id.shareApp) {

            Intent shIntent = new Intent(Intent.ACTION_SEND);
            shIntent.setType("Text/plain");
            String shareBody = "Your body here";
            String shareSub = "Your Subject here";
            shIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            shIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(shIntent, "Share Using..."));

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mToggle.onConfigurationChanged(newConfig);
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Toast toast = Toast.makeText(this, "id seleccionado " + id, Toast.LENGTH_LONG);
        toast.show();

        if (id == R.id.shajarit) {

            pag = 99;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            rezo = 1;
            libro = "sidduraskshajritinv.pdf";
            intent.putExtra(LeerSidur.REZO, rezo);
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);

        } else if (id == R.id.minja) {
            pag = 20;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            rezo = 2;
            libro = "sidduraskminjainv.pdf";
            intent.putExtra(LeerSidur.REZO, rezo);
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);

        } else if (id == R.id.arvit) {
            pag = 55;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            rezo = 3;
            libro = "siduraskarvinv.pdf";
            intent.putExtra(LeerSidur.REZO, rezo);
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);

        } else if (id == R.id.erevshabat) {

            Intent intent = new Intent(this, SelecionErevShabat.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

            Intent shIntent = new Intent(Intent.ACTION_SEND);
            shIntent.setType("Text/plain");
            String shareBody = "Your body here";
            String shareSub = "Your Subject here";
            shIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            shIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(shIntent, "Share Using..."));


        }

//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
