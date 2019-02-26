package app.production.samipc.mysidurapp1;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
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

import app.production.samipc.mysidurapp1.HebrewDate.HebrewDate;

import java.util.Calendar;

public class Selecionahora extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout mDrawerLayout;
    DrawerLayout drawer;
    private ActionBarDrawerToggle mToggle;

    private int pag;
    private int hora;
    private int dia;
    private int mes;
    private int año;
    private int rezo;
    private int diadesemana;
    private String libro;
    private Calendar calendar;
    private Button botonshajrit;
    private Button botonminja;
    private Button botonarvit;
    private Button botonerevshabat;
    private Button botontodo;
    private TextView verhora;
    private TextView yomview;
    private TextView prayerspending;
    private String message1;
    private String message2;
    private String message3;
    private String message4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecionahora);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        botonshajrit = findViewById(R.id.botonshajrit);
        botonminja = findViewById(R.id.botonminja);
        botonarvit = findViewById(R.id.botonarvit);
        botonerevshabat = findViewById(R.id.botonerevshabat);
        botontodo = findViewById(R.id.birkatamazon);
        verhora = findViewById(R.id.verhora);
        yomview = findViewById(R.id.yomview);
        prayerspending = findViewById(R.id.prayerspending);

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
        int todo = intent.getIntExtra(SelecionRezo.ALLPRAYES, -1);

        if (todo == 1) {
            showAllButtons();

        }

        if (diadesemana == 6) {
            showButtonsShabat ();
        } else {
            showButtonsEveryDay();
        }

        mDrawerLayout = findViewById(R.id.drawer_layout);
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


    }

    private void showAllButtons() {
        botonshajrit.setVisibility(View.VISIBLE);
        botonminja.setVisibility(View.VISIBLE);
        botonarvit.setVisibility(View.VISIBLE);
        botonerevshabat.setVisibility(View.VISIBLE);
        prayerspending.setVisibility(View.INVISIBLE);
    }

    private void showButtonsEveryDay (){
        Intent intent = getIntent();
        int todo = intent.getIntExtra(SelecionRezo.ALLPRAYES, -1);

        botonerevshabat.setVisibility(View.INVISIBLE);

        if (todo == 0){
            prayerspending.setVisibility(View.VISIBLE);
        } else {
            prayerspending.setVisibility(View.INVISIBLE);
        }


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
                 }else {
                     showAllButtons();
                 }
            }
        }
    }

    private void showButtonsShabat (){
        Intent intent = getIntent();
        int todo = intent.getIntExtra(SelecionRezo.ALLPRAYES, -1);

        botonshajrit.setVisibility(View.INVISIBLE);
        botonarvit.setVisibility(View.INVISIBLE);
        botonminja.setVisibility(View.INVISIBLE);

        if (todo == 0 && hora < 12){
            botonshajrit.setVisibility(View.VISIBLE);
            botonerevshabat.setVisibility(View.VISIBLE);

        } else {
            if (todo == 0 && hora > 11) {
                botonerevshabat.setVisibility(View.VISIBLE);
            }else {
                showAllButtons();
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

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                  mDrawerLayout.closeDrawer(GravityCompat.START);
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

        if (id == R.id.kidushsahabat) {
            pag = 0;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            libro = "kidushshabat.pdf";
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


        if (id == R.id.nav_view) {

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

        if (id == R.id.tefilathaderej) {

            pag = 0;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            libro = "tefilataderej.pdf";
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);

        } else if (id == R.id.tefilathaderejtrans) {
            pag = 0;
            Intent intent = new Intent(this, LeerSidur.class);
            intent.putExtra(LeerSidur.PAGINA,pag);
            libro = "tefilataderejtrans.pdf";
            intent.putExtra(LeerSidur.SIDUR, libro);
            startActivity(intent);

        } else if (id == R.id.about) {

            message1 = getString(R.string.about1);
            message3 = getString(R.string.fon_tica_rabino_eitan_weisman);
            message4 = "                             פונטיקה הרב איתן וייסמן";
            message2 = getString(R.string.about2);


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.title);
            builder.setMessage(message1 + "\n" + "\n" + message3 + "\n" + message4 +  "\n" + "\n" + message2);
            builder.create().show();

            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }



        } else if (id == R.id.nav_share) {

            Intent shIntent = new Intent(Intent.ACTION_SEND);
            shIntent.setType("Text/plain");
            String shareBody = "Your body here";
            String shareSub = "Your Subject here";
            shIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            shIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(shIntent, "Share Using..."));


        }

        return true;
    }
}
