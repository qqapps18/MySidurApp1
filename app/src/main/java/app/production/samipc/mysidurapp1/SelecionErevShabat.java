package app.production.samipc.mysidurapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import app.production.samipc.mysidurapp1.HebrewDate.HebrewDate;

import java.util.Calendar;

public class SelecionErevShabat extends AppCompatActivity {


    public static final String PAGINA = "pag" ;
    public static final String PAGINAT = "pagt" ;
    public static final String REZO = "rezo";
    public static final String SIDURT = "rezot";
    public static final String SIDUR = "sidur";
    private int pag;
    private int pagt;
    private int hora;
    private int dia;
    private int mes;
    private int año;
    private int rezo;
    private int diadesemana;
    private String libro;
    private String librot;
    private Calendar calendar;
    private Button botonminjashabat;
    private Button botonkalabatshabat;
    private Button botonarvitshabat;
    private TextView verhora;
    private TextView yomview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecion_erev_shabat);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botonminjashabat = findViewById(R.id.botonminjashabat);
        botonkalabatshabat = findViewById(R.id.botonkalabatshabat);
        botonarvitshabat = findViewById(R.id.botonarvitshabat);
        verhora = findViewById(R.id.verhora);
        yomview = findViewById(R.id.yomview);

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

    }

    public void leerminjashabat (View view)
    {
        pag = 50;
        pagt = 56;
        rezo = 1;
        libro = "sidurkseitanhinv.pdf";
        librot = "sidurkseitantinv.pdf";
        Intent intent = new Intent(this, SeleccionModo.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        intent.putExtra(LeerSidur.PAGINAT, pagt);
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        intent.putExtra(LeerSidur.SIDURT, librot);
        startActivity(intent);
    }

    public void leerkabalatshabat (View view)
    {
        pag = 32;
        pagt = 34;
        rezo = 1;
        libro = "sidurkseitanhinv.pdf";
        librot = "sidurkseitantinv.pdf";
        Intent intent = new Intent(this, SeleccionModo.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        intent.putExtra(LeerSidur.PAGINAT, pagt);
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        intent.putExtra(LeerSidur.SIDURT, librot);
        startActivity(intent);
    }

    public void leerarvitshabat (View view)
    {
        pag = 22;
        pagt = 24;
        rezo = 1;
        libro = "sidurkseitanhinv.pdf";
        librot = "sidurkseitantinv.pdf";
        Intent intent = new Intent(this, SeleccionModo.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        intent.putExtra(LeerSidur.PAGINAT, pagt);
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        intent.putExtra(LeerSidur.SIDURT, librot);
        startActivity(intent);
    }

    public void leerkidushshabat (View view)
    {
        pag = 0;
        pagt = 0;
        rezo = 1;
        libro = "kidushshabat.pdf";
        librot = "kidushshabattrans.pdf";
        Intent intent = new Intent(this, SeleccionModo.class);
        intent.putExtra(LeerSidur.PAGINA,pag);
        intent.putExtra(LeerSidur.PAGINAT, pagt);
        intent.putExtra(LeerSidur.REZO, rezo);
        intent.putExtra(LeerSidur.SIDUR, libro);
        intent.putExtra(LeerSidur.SIDURT, librot);
        startActivity(intent);
    }
}
