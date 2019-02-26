package com.example.samipc.mysidurapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.StaticLayout;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samipc.mysidurapp1.HebrewDate.HebrewDate;
import com.example.samipc.mysidurapp1.HebrewDate.HebrewDateException;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SelecionRezo extends AppCompatActivity {

    public static final String ALLPRAYES = "";

    int allprayes;
    int dia;
    int mes;
    int año;
    int jodesh;
    int yom;
    int shana;
    Calendar calendar;
    TextView fechahoyview;
    TextView yomview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecion_rezo);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fechahoyview = (TextView) findViewById(R.id.fechahoyview);
        yomview = (TextView) findViewById(R.id.yomview);


        calendar= Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        año = calendar.get(Calendar.YEAR);

        mes = mes + 1;
        fechahoyview.setText(dia + "/"+ mes+ "/"+ año);

        //Toast toast = Toast.makeText(this, "dia de la semana ,  hora y todo " + mes, Toast.LENGTH_LONG);
        //toast.show();

        HebrewDate date = new HebrewDate();


        //String TAG = "HebrewExamples";
        //Log.d(TAG, "Date: " + date.getHebrewDateAsString());
        //Log.d(TAG, "Month: " + date.getHebrewMonthAsString());
        //Log.d(TAG, "Year: " + date.getHebrewYear());
        //Log.d(TAG, "Leap Year?: " + date.isHebrewLeapYear());

        yomview.setText(date.getHebrewDateAsString());


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void seleccionahora (View view)
    {
        allprayes = 0;

        Intent intent = new Intent(this, Selecionahora.class);
        intent.putExtra(ALLPRAYES, allprayes);
        startActivity(intent);

    }

    public void seleccionatodo (View view)
    {
        allprayes = 1;

        Intent intent = new Intent(this, Selecionahora.class);
        intent.putExtra(ALLPRAYES, allprayes);
        startActivity(intent);

    }



}
