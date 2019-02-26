package com.example.samipc.mysidurapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.samipc.mysidurapp1.HebrewDate.HebrewDate;

import java.util.Calendar;

public class SeleccionaRezoShajrit extends AppCompatActivity {

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
    Button buttonBirkotHashajar;
    Button buttonTallit;
    Button buttonTefillin;
    Button buttonHodu;
    Button buttonShemaIsrael;
    Button buttonAmida;
    TextView verhora;
    TextView yomview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecciona_rezo_shajrit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botonshajrit = (Button) findViewById(R.id.botonshajrit);
        buttonTallit = (Button) findViewById(R.id.buttonTallit);
        buttonTefillin = (Button) findViewById(R.id.buttonTefillin);
        buttonBirkotHashajar = (Button) findViewById(R.id.buttonBirkotHashajar);
        buttonHodu = (Button) findViewById(R.id.buttonHodu);
        buttonShemaIsrael = (Button) findViewById(R.id.buttonShemaIsrael);
        buttonAmida = (Button) findViewById(R.id.buttonAmida);
        verhora = (TextView) findViewById(R.id.verhora);
        yomview = (TextView) findViewById(R.id.yomview);

        calendar= Calendar.getInstance();
        hora = calendar.get(Calendar.HOUR_OF_DAY);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        diadesemana = calendar.get(Calendar.DAY_OF_WEEK);
        mes =calendar.get(Calendar.MONTH);
        año = calendar.get(Calendar.YEAR);

        mes = mes +1;
        verhora.setText(dia + "/"+ mes+ "/"+ año);

        HebrewDate date = new HebrewDate();
        yomview.setText(date.getHebrewDateAsString());



    }



    public void leershajrit (View view)
    {
        pag = 99;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        rezo = 1;
        libro = "sidduraskshajritinv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

    public void ponerTallit (View view)
    {
        pag = 94;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        rezo = 1;
        libro = "sidduraskshajritinv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

    public void ponerTefillin (View view)
    {
        pag = 93;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        rezo = 1;
        libro = "sidduraskshajritinv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

    public void birkotHashajar (View view) {
        pag = 81;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA, pag);
        rezo = 1;
        libro = "sidduraskshajritinv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

    public void hodu (View view) {
        pag = 63;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA, pag);
        rezo = 1;
        libro = "sidduraskshajritinv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

    public void shemaIsrael (View view) {
        pag = 47;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA, pag);
        rezo = 1;
        libro = "sidduraskshajritinv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

    public void amida (View view) {
        pag = 43;
        Intent intent = new Intent(this, LeerSidur.class);
        intent.putExtra(LeerSidur.PAGINA, pag);
        rezo = 1;
        libro = "sidduraskshajritinv.pdf";
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        startActivity(intent);
    }

}
