package app.production.samipc.mysidurapp1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import app.production.samipc.mysidurapp1.HebrewDate.HebrewDate;
import hotchemi.android.rate.AppRate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SelecionRezo extends AppCompatActivity {

    public static final String ALLPRAYES = "";

    private int allprayes;
    private int dia;
    private int mes;
    private int año;
    private String jodesh;
    private int yom;
    private int shana;
    private String prevjodesh;
    private int prevyom;
    private int prevshana;
    private String antprevjodesh;
    private int antprevyom;
    private int antprevshana;
    //private int endmonth;
    private String hebrewDateYurzaitString;
    private Calendar calendar;
    private TextView fechahoyview;
    private TextView yomview;
    private ImageView imageHoliday;
    private TextView yomholiday;
    private int intTime;
    private int isHoliday;
    private Boolean isLeapYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecion_rezo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        AppRate.with(this)
                .setInstallDays(1)
                .setLaunchTimes(1)
                .setRemindInterval(1)
                .setShowLaterButton(true)
                .setDebug(false)

                .monitor();

        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);

        fechahoyview = findViewById(R.id.fechahoyview);
        yomview = findViewById(R.id.yomview);
        imageHoliday = findViewById(R.id.imageHoliday);
        yomholiday = findViewById(R.id.yomholiday);

        isHoliday = 0;
        imageHoliday.setVisibility(View.INVISIBLE);
        yomholiday.setVisibility(View.INVISIBLE);

        calendar= Calendar.getInstance();
        dia = calendar.get(Calendar.DAY_OF_MONTH);
        mes = calendar.get(Calendar.MONTH);
        año = calendar.get(Calendar.YEAR);

        SimpleDateFormat hora = new SimpleDateFormat("kk");
        String time = hora.format(calendar.getTime());
        intTime = Integer.parseInt(time);

        mes = mes + 1;
        String fechahoyviewtxt = dia + "/"+ mes+ "/"+ año;
        fechahoyview.setText(fechahoyviewtxt);

        HebrewDate date = new HebrewDate();
        yomview.setText(date.getHebrewDateAsString());

        yom = HebrewDate.CURRENT_HDAY;
        jodesh =HebrewDate.CURRENT_HMONTH;
        shana = HebrewDate.CURRENT_HYEAR;
        isLeapYear = HebrewDate.IS_LEAP_YEAR;


        checkHoliday();

    }

    private void checkHoliday() {
        checkRoshHashana();
        checkTzomGedalia();
        checkYomKipur();
        checksukkot();
        checkJanuca();
        check10Tevet();
        checkTuBishvat();
        checkPurim();
        checkPassover();
        checkOmer();
        checkShavuot();
        check17Tamuz();
        check9Beav();


    }

   private void checkRoshHashana() {
        if (jodesh == "Elul") {
            if (yom == 29 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.roshhashana);
                yomholiday.setText(getString(R.string.roshhashana));
                todayIsHoliday();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }

        if (jodesh == "Tishrei") {
            if (yom == 1 || (yom == 2 && intTime < 19)) {
                imageHoliday.setImageResource(R.drawable.roshhashana);
                yomholiday.setText(getString(R.string.roshhashana));
                todayIsHoliday();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }
    }

    private void checkTzomGedalia() {
        if (jodesh == "Tishrei") {
            if (yom == 3 && (intTime > 5 && intTime < 19)) {
                yomholiday.setText(getString(R.string.tzomGedalia));
                todayIsTzom();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }
    }

    public void checkYomKipur() {
        if (jodesh == "Tishrei") {
            if ((yom == 9 && intTime > 17)|| (yom == 10 && intTime < 19)) {
                yomholiday.setText(getString(R.string.yomkipur));
                todayIsTzom();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }
    }

    private void checksukkot() {
        if (jodesh == "Tishrei") {
            if ((yom == 14 && intTime > 17)|| (yom > 14 && yom < 20) || (yom == 20 && intTime < 19)) {
                imageHoliday.setImageResource(R.drawable.sukkot);
                yomholiday.setText(getString(R.string.sukkot));
                todayIsHoliday();
            }  else if ((yom == 20 && intTime > 17)|| (yom == 21 && intTime < 19)) {
                imageHoliday.setImageResource(R.drawable.sukkot);
                yomholiday.setText(getString(R.string.hoshanaraba));
                todayIsHoliday();
            } else if ((yom == 21 && intTime > 17)|| (yom == 22 && intTime < 19)) {
                imageHoliday.setImageResource(R.drawable.sukkot);
                yomholiday.setText(getString(R.string.sheminiatzeret));
                todayIsHoliday();
            } else if ((yom == 22 && intTime > 17)|| (yom > 23 && intTime < 19)) {
                imageHoliday.setImageResource(R.drawable.sinchattorah);
                yomholiday.setText(getString(R.string.sinchattorah));
                todayIsHoliday();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }
    }

    private void checkJanuca() {
        int endmonth = 0;

        checkprevday();
        checkantprevday();

        if (jodesh.equals("Tevet")) {
            if (yom == 1 && prevyom == 29) {
                endmonth = 1;
            } else if (yom == 2 && (antprevyom == 29)) {
                endmonth = 1;
            } else if (yom == 1 && (prevyom == 30)) {
                endmonth = 2;
            } else if (yom == 2 && (antprevyom == 30)) {
                endmonth = 2;
            }
        }

        if (jodesh.equals("Kislev")) {
            if ((yom == 24 && intTime > 17) || (yom == 25 && intTime < 18)) {
                imageHoliday.setImageResource(R.drawable.januquilladia1);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if ((yom == 25 && intTime > 17) || (yom == 26 && intTime < 18)) {
                imageHoliday.setImageResource(R.drawable.januquilladia2);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            }  else if ((yom == 26 && intTime > 17) || (yom == 27 && intTime < 18)) {
                imageHoliday.setImageResource(R.drawable.januquilladia3);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            }  else if ((yom == 27 && intTime > 17) || (yom == 28 && intTime < 18)) {
                imageHoliday.setImageResource(R.drawable.januquilladia4);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if ((yom == 28 && intTime > 17) || (yom == 29 && intTime < 18)) {
                imageHoliday.setImageResource(R.drawable.januquilladia5);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if ((yom == 29 && intTime > 17) || (yom == 30  && intTime < 18)) {
                imageHoliday.setImageResource(R.drawable.januquilladia6);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if (yom == 30  && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.januquilladia7);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            }
        }

        if (jodesh.equals("Tevet")) {
            if (yom == 1 && endmonth == 1 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.januquilladia7);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if (yom == 1 && endmonth == 1 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.januquillacompleta);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if (yom == 2 && endmonth == 1 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.januquillacompleta);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if (yom == 1 && endmonth == 2 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.januquilladia7);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if (yom == 1 && endmonth == 2 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.januquillacompleta);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if (yom == 1 && endmonth == 2 && intTime > 18) {
                todayIsNotHoliday();
            } else if (yom == 2 && endmonth == 2 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.januquillacompleta);
                yomholiday.setText(getString(R.string.happyjanuca));
                todayIsHoliday();
            } else if (yom == 3 || (yom == 2 && intTime > 17)) {
                endmonth = 0;
                isHoliday = 0;
                todayIsNotHoliday();
            }
        }
    }

    private void check10Tevet() {
        if (jodesh == "Tevet") {
            if (yom == 10 && (intTime > 5 && intTime < 19)) {
                yomholiday.setText(getString(R.string.tzom10Tevet));
                todayIsTzom();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }
    }

    private void checkTuBishvat() {
        if (jodesh == "Shvat") {
            if (yom == 15) {
                imageHoliday.setImageResource(R.drawable.tubishvat);
                yomholiday.setText(getString(R.string.tubishvat));
                todayIsHoliday();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }
    }

    private void checkPurim() {

        if (isLeapYear) {
            if (jodesh == "Adar I") {
                if (yom == 14) {
                    imageHoliday.setImageResource(R.drawable.purimkatan);
                    yomholiday.setText(getString(R.string.purimkatan));
                    todayIsHoliday();
                } else if (yom == 15) {
                    imageHoliday.setImageResource(R.drawable.purimkatan);
                    yomholiday.setText(getString(R.string.sushanpurimkatan));
                    todayIsHoliday();
                } else if (isHoliday == 0) {
                    todayIsNotHoliday();
                }
            }
        }

        if (isLeapYear) {
            if (jodesh == "Adar II") {
                if (yom == 13) {
                    yomholiday.setText(getString(R.string.tzomEsther));
                    todayIsTzom();
                } else if (yom == 14) {
                    imageHoliday.setImageResource(R.drawable.purimnormal);
                    yomholiday.setText(getString(R.string.purim));
                    todayIsHoliday();
                } else if (yom == 15) {
                    imageHoliday.setImageResource(R.drawable.purimnormal);
                    yomholiday.setText(getString(R.string.sushanpurim));
                    todayIsHoliday();
                } else if (isHoliday == 0) {
                    todayIsNotHoliday();
                }
            }
        }

        if(!isLeapYear) {
            if (jodesh == "Adar") {
                if (yom == 13) {
                    yomholiday.setText(getString(R.string.tzomEsther));
                    todayIsTzom();
                } else if (yom == 14) {
                    imageHoliday.setImageResource(R.drawable.purimnormal);
                    yomholiday.setText(getString(R.string.purim));
                    todayIsHoliday();
                } else if (yom == 15) {
                    imageHoliday.setImageResource(R.drawable.purimnormal);
                    yomholiday.setText(getString(R.string.sushanpurim));
                    todayIsHoliday();
                } else if (isHoliday == 0) {
                    todayIsNotHoliday();
                }
            }
        }
    }

    private void checkPassover() {
        if (jodesh == "Nissan") {
            if (yom == 14 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaje1seder));
                todayIsHoliday();
            } else   if (yom == 15 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj));
                todayIsHoliday();
            } else if (yom == 15 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj2seder));
                todayIsHoliday();
            } else if (yom == 16 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj1omer));
                todayIsHoliday();
            } else if (yom == 16 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj2omer));
                todayIsHoliday();
            } else if (yom == 17 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj2omer));
                todayIsHoliday();
            } else if (yom == 17 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj3omer));
                todayIsHoliday();
            }else if (yom == 18 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj3omer));
                todayIsHoliday();
            } else if (yom == 18 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj4omer));
                todayIsHoliday();
            } else if (yom == 19 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj4omer));
                todayIsHoliday();
            } else if (yom == 19 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj5omer));
                todayIsHoliday();
            } else if (yom == 20  && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj5omer));
                todayIsHoliday();
            } else if (yom == 20  && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj6omer));
                todayIsHoliday();
            } else if (yom == 21 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj6omer));
                todayIsHoliday();
            } else if (yom == 21 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj7omer));
                todayIsHoliday();
            } else if (yom == 22  && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj7omer));
                todayIsHoliday();
            } else if (yom == 22  && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.pesaj);
                yomholiday.setText(getString(R.string.pesaj8omer));
                todayIsHoliday();
            }
        }
    }

    private void checkOmer() {
        if (jodesh == "Nissan") {
            if (yom == 23 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj8omer));
                todayIsTzom();
            } else if (yom == 23 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj9omer));
                todayIsTzom();
            } else if (yom == 24 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj9omer));
                todayIsTzom();
            } else if (yom == 24 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj10omer));
                todayIsTzom();
            } else if (yom == 25 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj10omer));
                todayIsTzom();
            } else if (yom == 25 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj11omer));
                todayIsTzom();
            }else if (yom == 26 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj11omer));
                todayIsTzom();
            } else if (yom == 26 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj12omer));
                todayIsTzom();
            } else if (yom == 27 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj12omer));
                todayIsTzom();
            } else if (yom == 27 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj13omer));
                todayIsTzom();
            } else if (yom == 28  && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj13omer));
                todayIsTzom();
            } else if (yom == 28  && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj14omer));
                todayIsTzom();
            } else if (yom == 29 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj14omer));
                todayIsTzom();
            } else if (yom == 29 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj15omer));
                todayIsTzom();
            } else if (yom == 30  && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj15omer));
                todayIsTzom();
            } else if (yom == 30  && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj16omer));
                todayIsTzom();
            }
        }

        if (jodesh == "Iyar") {
            if (yom == 1 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj16omer));
                todayIsTzom();
            } else if (yom == 1 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj17omer));
                todayIsTzom();
            } else if (yom == 2 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj17omer));
                todayIsTzom();
            } else if (yom == 2 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj18omer));
                todayIsTzom();
            } else if (yom == 3 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj18omer));
                todayIsTzom();
            } else if (yom == 3 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj19omer));
                todayIsTzom();
            }else if (yom == 4 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj19omer));
                todayIsTzom();
            } else if (yom == 4 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj20omer));
                todayIsTzom();
            } else if (yom == 5 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj20omer));
                todayIsTzom();
            } else if (yom == 5 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj21omer));
                todayIsTzom();
            } else if (yom == 6  && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj21omer));
                todayIsTzom();
            } else if (yom == 6  && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj22omer));
                todayIsTzom();
            } else if (yom == 7 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj22omer));
                todayIsTzom();
            } else if (yom == 7 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj23omer));
                todayIsTzom();
            } else if (yom == 8  && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj23omer));
                todayIsTzom();
            } else if (yom == 8  && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj24omer));
                todayIsTzom();
            } else if (yom == 9 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj24omer));
                todayIsTzom();
            } else if (yom == 9 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj25omer));
                todayIsTzom();
            } else if (yom == 10 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj25omer));
                todayIsTzom();
            } else if (yom == 10 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj26omer));
                todayIsTzom();
            } else if (yom == 11 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj26omer));
                todayIsTzom();
            } else if (yom == 11 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj27omer));
                todayIsTzom();
            }else if (yom == 12 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj27omer));
                todayIsTzom();
            } else if (yom == 12 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj28omer));
                todayIsTzom();
            } else if (yom == 13 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj28omer));
                todayIsTzom();
            } else if (yom == 13 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj29omer));
                todayIsTzom();
            } else if (yom == 14  && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj29omer));
                todayIsTzom();
            } else if (yom == 14  && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj30omer));
                todayIsTzom();
            } else if (yom == 15 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj30omer));
                todayIsTzom();
            } else if (yom == 15 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj31omer));
                todayIsTzom();
            } else if (yom == 16  && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj31omer));
                todayIsTzom();
            } else if (yom == 16  && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj32omer));
                todayIsTzom();
            } else if (yom == 17 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj32omer));
                todayIsTzom();
            } else if (yom == 17 && intTime > 17) {
                imageHoliday.setImageResource(R.drawable.lagbaomer);
                yomholiday.setText(getString(R.string.pesaj33omer));
                todayIsHoliday();
            } else if (yom == 18 && intTime < 18) {
                imageHoliday.setImageResource(R.drawable.lagbaomer);
                yomholiday.setText(getString(R.string.pesaj33omer));
                todayIsHoliday();
            } else if (yom == 18 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj34omer));
                todayIsTzom();
            } else if (yom == 19 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj34omer));
                todayIsTzom();
            } else if (yom == 19 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj35omer));
                todayIsTzom();
            }else if (yom == 20 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj35omer));
                todayIsTzom();
            } else if (yom == 20 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj36omer));
                todayIsTzom();
            } else if (yom == 21 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj36omer));
                todayIsTzom();
            } else if (yom == 21 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj37omer));
                todayIsTzom();
            } else if (yom == 22  && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj37omer));
                todayIsTzom();
            } else if (yom == 22  && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj38omer));
                todayIsTzom();
            } else if (yom == 23 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj38omer));
                todayIsTzom();
            } else if (yom == 23 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj39omer));
                todayIsTzom();
            } else if (yom == 24  && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj39omer));
                todayIsTzom();
            } else if (yom == 24  && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj40omer));
                todayIsTzom();
            } else if (yom == 25 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj40omer));
                todayIsTzom();
            } else if (yom == 25 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj41omer));
                todayIsTzom();
            } else if (yom == 26 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj41omer));
                todayIsTzom();
            } else if (yom == 26 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj42omer));
                todayIsTzom();
            } else if (yom == 27 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj42omer));
                todayIsTzom();
            } else if (yom == 27 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj43omer));
                todayIsTzom();
            }else if (yom == 28 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj43omer));
                todayIsTzom();
            } else if (yom == 28 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj44omer));
                todayIsTzom();
            } else if (yom == 29 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj44omer));
                todayIsTzom();
            } else if (yom == 29 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj45omer));
                todayIsTzom();
            }
        }

        if (jodesh == "Sivan") {
            if (yom == 1 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj45omer));
                todayIsTzom();
            } else if (yom == 1 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj46omer));
                todayIsTzom();
            } else if (yom == 2 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj46omer));
                todayIsTzom();
            } else if (yom == 2 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj47omer));
                todayIsTzom();
            } else if (yom == 3 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj47omer));
                todayIsTzom();
            } else if (yom == 3 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj48omer));
                todayIsTzom();
            } else if (yom == 4 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj48omer));
                todayIsTzom();
            } else if (yom == 4 && intTime > 17) {
                yomholiday.setText(getString(R.string.pesaj49omer));
                todayIsTzom();
            } else if (yom == 5 && intTime < 18) {
                yomholiday.setText(getString(R.string.pesaj49omer));
                todayIsTzom();
            }
        }
    }

    private void checkShavuot() {
        if (jodesh == "Sivan") {
            if ((yom == 5 && intTime > 17)|| yom == 6  || (yom == 7 && intTime < 18)) {
                imageHoliday.setImageResource(R.drawable.shavuot);
                yomholiday.setText(getString(R.string.shavuot));
                todayIsHoliday();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }
    }

    private void check17Tamuz() {
        if (jodesh == "Tamuz") {
            if (yom == 17 && (intTime > 5 && intTime < 19)) {
                yomholiday.setText(getString(R.string.tzom17tamuz));
                todayIsTzom();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }
    }

    public void check9Beav() {
        if (jodesh == "Av") {
            if ((yom == 8 && intTime > 17)|| (yom == 9 && intTime < 19)) {
                yomholiday.setText(getString(R.string.tzom9Beav));
                todayIsTzom();
            } else if (isHoliday == 0) {
                todayIsNotHoliday();
            }
        }
    }

    public void todayIsHoliday() {
        isHoliday = 1;
        yomholiday.setVisibility(View.VISIBLE);
        imageHoliday.setVisibility(View.VISIBLE);
    }

    private void todayIsNotHoliday() {
        isHoliday = 0;
        imageHoliday.setVisibility(View.INVISIBLE);
        yomholiday.setVisibility(View.INVISIBLE);
    }

    private void todayIsTzom() {
        isHoliday = 1;
        yomholiday.setVisibility(View.VISIBLE);
        imageHoliday.setVisibility(View.INVISIBLE);
    }




    private void checkprevday() {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        Date diayurzait = new Date((año - 1900), (mes - 1), (dia - 1));
        hebrewDateYurzaitString = dateFormat.format(diayurzait);

        HebrewDate hebrewDateYurzait = new HebrewDate(diayurzait);
        prevyom = HebrewDate.CURRENT_HDAY;
        prevjodesh =HebrewDate.CURRENT_HMONTH;
        prevshana = HebrewDate.CURRENT_HYEAR;
    }

    private void checkantprevday() {
        final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM);

        Date diayurzait = new Date((año - 1900), (mes - 1), (dia - 2));
        hebrewDateYurzaitString = dateFormat.format(diayurzait);

        HebrewDate hebrewDateYurzait = new HebrewDate(diayurzait);
        antprevyom = HebrewDate.CURRENT_HDAY;
        antprevjodesh =HebrewDate.CURRENT_HMONTH;
        antprevshana = HebrewDate.CURRENT_HYEAR;
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

    @Override
    public void onBackPressed() {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
        super.onBackPressed();


        }

}
