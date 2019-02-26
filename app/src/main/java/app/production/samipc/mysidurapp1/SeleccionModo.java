package app.production.samipc.mysidurapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import app.production.samipc.mysidurapp1.HebrewDate.HebrewDate;

import java.util.Calendar;

public class SeleccionModo extends AppCompatActivity {

    public static final String PAGINA = "pag" ;
    public static final String REZO = "rezo";
    public static final String SIDUR = "sidur";
    public static final String PAGINAT = "pagt" ;
    public static final String SIDURT = "sidurt";
    int all;
    private int dia;
    private int mes;
    private int año;
    private Calendar calendar;
    private TextView fechahoyview;
    private TextView yomview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_modo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fechahoyview = findViewById(R.id.fechahoyview);
        yomview = findViewById(R.id.yomview);

        calendar= Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes =calendar.get(Calendar.MONTH);
        año = calendar.get(Calendar.YEAR);

        mes = mes + 1;
        fechahoyview.setText(dia + "/"+ mes+ "/"+ año);

        HebrewDate date = new HebrewDate();
        yomview.setText(date.getHebrewDateAsString());

    }

    public void seleccionahebreo (View view)
    {
        Intent intent = getIntent();
        int page = intent.getIntExtra(LeerSidur.PAGINA, -1);
        int oracion = intent.getIntExtra(LeerSidur.REZO, 1);
        String book = intent.getStringExtra(LeerSidur.SIDUR);

        Intent intenth = new Intent(this, LeerSidur.class);
        intenth.putExtra(LeerSidur.PAGINA,page);
        intenth.putExtra(LeerSidur.REZO, oracion);
        intenth.putExtra(LeerSidur.SIDUR, book);

        startActivity(intenth);
    }

    public void seleccionatransliteral (View view)
    {
        Intent intent = getIntent();
        int paget = intent.getIntExtra(LeerSidur.PAGINAT, -1);
        int oracion = intent.getIntExtra(LeerSidur.REZO, 1);
        String bookt = intent.getStringExtra(LeerSidur.SIDURT);

        Intent intentt = new Intent(this, LeerSidur.class);
        intentt.putExtra(LeerSidur.PAGINA,paget);
        intentt.putExtra(LeerSidur.REZO, oracion);
        intentt.putExtra(LeerSidur.SIDUR, bookt);

        startActivity(intentt);
    }




}
