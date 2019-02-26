package app.production.samipc.mysidurapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.barteksc.pdfviewer.PDFView;

public class LeerSidur extends AppCompatActivity {

    public static final String PAGINA = "pag" ;
    public static final String PAGINAT = "pagt" ;
    public static final String REZO = "rezo";
    public static final String SIDURT = "rezot";
    public static final String SIDUR = "sidur";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leer_sidur);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        int pag = intent.getIntExtra(PAGINA, -1);
        int rezo = intent.getIntExtra(REZO, 1);
        String libro = intent.getStringExtra(SIDUR);

        PDFView pdfView = findViewById(R.id.pdfView);

        pdfView.fromAsset(libro).swipeHorizontal(true).defaultPage(pag).spacing(0).load();




    }

}
