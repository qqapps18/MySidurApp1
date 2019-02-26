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

public class SeleccionaRezoShajrit extends AppCompatActivity {

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
    private Button buttonBirkotHashajar;
    private Button buttonTallit;
    private Button buttonTefillin;
    private Button buttonHodu;
    private Button buttonShemaIsrael;
    private Button buttonAmida;
    private TextView verhora;
    private TextView yomview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecciona_rezo_shajrit);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        botonshajrit = findViewById(R.id.botonshajrit);
        buttonTallit = findViewById(R.id.buttonTallit);
        buttonTefillin = findViewById(R.id.buttonTefillin);
        buttonBirkotHashajar = findViewById(R.id.buttonBirkotHashajar);
        buttonHodu = findViewById(R.id.buttonHodu);
        buttonShemaIsrael = findViewById(R.id.buttonShemaIsrael);
        buttonAmida = findViewById(R.id.buttonAmida);
        verhora = findViewById(R.id.verhora);
        yomview = findViewById(R.id.yomview);

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
