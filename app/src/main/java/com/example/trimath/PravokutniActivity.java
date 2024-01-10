package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class PravokutniActivity extends AppCompatActivity {
    // Deklaracija tekstnih objekata
    EditText strA, strB, strC, upis, opis, kutAlpha, kutBeta, povrsina, opseg, visina;
    // Deklaracija gumba
    Button btn;
    SimplifiedToast st = new SimplifiedToast();

    int f = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pravokutni);
        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        // Inicijalizacija tekstnih objekata
        strA = findViewById(R.id.editTextNumber11);
        strB = findViewById(R.id.editTextNumber12);
        strC = findViewById(R.id.editTextNumber13);
        upis = findViewById(R.id.editTextNumber14);
        opis = findViewById(R.id.editTextNumber15);
        kutAlpha = findViewById(R.id.editTextNumber16);
        kutBeta = findViewById(R.id.editTextNumber17);
        povrsina = findViewById(R.id.editTextNumber18);
        opseg = findViewById(R.id.editTextNumber19);
        visina = findViewById(R.id.editTextNumber20);
        // Inicijalizacija gumba
        btn = findViewById(R.id.button2);


        btn.setOnClickListener(v -> {
            // provjera je li jedna vrijednost unesena
            if (checkEmpty(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina)) {
                // ako nije prikaži poruku
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
                return;
            }
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }

            //nemoguce kombinacije
            if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta)) && checkEmpty(strA, strB, strC, visina, povrsina, opseg, opis, upis)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(povrsina) && !checkEmpty(opseg)) && checkEmpty(strA, strB, strC, visina, opis, upis, kutAlpha, kutBeta)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(povrsina) && !checkEmpty(visina)) && checkEmpty(strA, strB, strC, opseg, kutBeta, kutAlpha, opis, upis)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(povrsina) && !checkEmpty(strC)) && checkEmpty(strA, strB, visina, opseg, kutBeta, kutAlpha, opis, upis)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(strC)) && checkEmpty(strA, strB, visina, povrsina, kutBeta, kutAlpha, opis, upis)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(strB)) && checkEmpty(strA, strC, visina, povrsina, kutBeta, kutAlpha, opis, upis)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(strA)) && checkEmpty(strB, strC, visina, povrsina, kutBeta, kutAlpha, opis, upis)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            }
            //moguce kombinacije
            else if ((!checkEmpty(strA) && !checkEmpty(strB)) && checkEmpty(strC, visina, povrsina, opseg, opis, upis, kutAlpha, kutBeta)) {
                izracunaj(strA, strB, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC)) && checkEmpty(strB, visina, povrsina, opseg, opis, upis, kutAlpha, kutBeta)) {
                izracunaj(strA, strC, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(visina)) && checkEmpty(strB, strC, povrsina, opseg, kutAlpha, kutBeta,opis,upis)) {
                izracunaj(strA, visina, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(kutAlpha)) && checkEmpty(strB, strC, kutBeta, opseg, povrsina, visina,opis,upis)) {
                izracunaj(strA, kutAlpha, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(kutBeta)) && checkEmpty(strB, strC, kutAlpha, opseg, povrsina, visina,opis,upis)) {
                izracunaj(strA, kutBeta, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(povrsina)) && checkEmpty(strB, strC, kutAlpha, visina, opseg, kutBeta, opis, upis)) {
                izracunaj(strA, povrsina, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)) && checkEmpty(strA, kutAlpha, kutBeta, visina, povrsina, opseg,  opis, upis)) {
                izracunaj(strB, strC, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(kutAlpha)) && checkEmpty(strA, strC, kutBeta, visina, povrsina, opseg,  opis, upis)) {
                izracunaj(strB, kutAlpha, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(kutBeta)) && checkEmpty(strA, strC, kutAlpha, visina, povrsina, opseg,  opis, upis)) {
                izracunaj(strB, kutBeta, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(visina)) && checkEmpty(strA, strC, kutAlpha, kutBeta, povrsina, opseg,  opis, upis)) {
                izracunaj(strB, visina, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(povrsina)) && checkEmpty(strA, strC, kutAlpha, visina, opseg, kutBeta, opis, upis)) {
                izracunaj(strB, povrsina, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(strC)) && checkEmpty(strA, strB, visina, opseg, povrsina, kutBeta, opis, upis)) {
                izracunaj(kutAlpha, strC, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(visina)) && checkEmpty(strA, strB, strC, opseg, povrsina, kutBeta, opis, upis)) {
                izracunaj(kutAlpha, visina, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(strC)) && checkEmpty(strA, strB, visina, opseg, povrsina, kutAlpha, opis, upis)) {
                izracunaj(kutBeta, strC, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(visina)) && checkEmpty(strA, strB, strC, opseg, povrsina, kutAlpha, opis, upis)) {
                izracunaj(kutBeta, visina, "cm");
            } else {
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
            }

            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
        });

    }

    protected void izracunaj(EditText ed, EditText ed2, String text) {
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, strA, strB, strC, upis, opis, kutAlpha, kutBeta, povrsina, opseg,visina);
        if (ed.equals(strA) && ed2.equals(strB)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _strC = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB,2)));
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);

        }
        if (ed.equals(strA) && ed2.equals(strC)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strB = Math.sqrt((Math.pow(_strC,2) - Math.pow(_strA,2)));

            if (_strA >= _strC || _strB >= _strC) {
                st.toastShort(this, getString(R.string.nije_moguce));
                //changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, upis, opis, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(visina)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(visina.getText().toString());
            double _kutBeta = Math.asin(_visina/_strA);

            double _strC = _strA/Math.cos(_kutBeta);
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(kutAlpha)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Double.parseDouble(ed2.getText().toString());
            double _strC = _strA/Math.sin(Math.toRadians(_kutAlpha));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }

        if (ed.equals(strA) && ed2.equals(kutBeta)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _strC = _strA/Math.cos(Math.toRadians(_kutBeta));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }

        if (ed.equals(strA) && ed2.equals(povrsina)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strB = 2*_povrsina/_strA;
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(strC)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strA = Math.sqrt(Math.pow(_strC,2) - Math.pow(_strB,2));

            if (_strA >= _strC || _strB >= _strC) {
                st.toastShort(this, getString(R.string.nije_moguce));
                //changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, upis, opis, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(kutAlpha)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _kutAlfa = Double.parseDouble(ed2.getText().toString());
            double _strC = _strB/Math.cos(Math.toRadians(_kutAlfa));
            double _strA = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(kutBeta)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _strC = _strB/Math.sin(Math.toRadians(_kutBeta));
            double _strA = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(visina)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(visina.getText().toString());
            double _kutAlpha = Math.asin(_visina/_strB);

            double _strC = _strB/Math.cos(_kutAlpha);
            double _strA = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(povrsina)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strA = 2*_povrsina/_strB;
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(strC)) {
            double _kutAlpha = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strA = _strC * Math.sin(Math.toRadians(_kutAlpha));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(visina)) {
            double _kutAlpha = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(ed2.getText().toString());
            double _strB =  _visina/Math.sin(Math.toRadians(_kutAlpha));
            double _strA =  _visina/Math.sin(Math.toRadians(90-_kutAlpha));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(strC)) {
            double _kutBeta = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strA = _strC * Math.cos(Math.toRadians(_kutBeta));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(visina)) {
            double _kutBeta = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(ed2.getText().toString());
            double _strA =  _visina/Math.sin(Math.toRadians(_kutBeta));
            double _strB =  _visina/Math.sin(Math.toRadians(90-_kutBeta));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina, opis, upis,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }



        // vrati nazad originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, upis, opis, kutAlpha, kutBeta, povrsina, opseg,visina);
    }

    protected void appendToText(String text,double _strA, double _strB, double _strC, double _povrsina, double _opseg, double _opis, double _upis, double _visina, double _kutAlpha, double _kutBeta) {
        strA.append(String.format(Locale.getDefault(), "%.2f", _strA) + text + " " + getString(R.string.stranica_A));
        strB.append(String.format(Locale.getDefault(), "%.2f", _strB) + text + " " + getString(R.string.stranica_B));
        strC.append(String.format(Locale.getDefault(), "%.2f", _strC) + text + " " + getString(R.string.stranica_C));
        kutAlpha.append(String.format(Locale.getDefault(), "%.2f", _kutAlpha) + text + " " + getString(R.string.Kut_alpha));
        kutBeta.append(String.format(Locale.getDefault(), "%.2f", _kutBeta) + getString(R.string.beta) + " " + getString(R.string.Kut_beta));
        opseg.append(String.format(Locale.getDefault(), "%.2f", _opseg) + text + " " + getString(R.string.opseg));
        povrsina.append(String.format(Locale.getDefault(), "%.2f", _povrsina) + text + getString(R.string.nakvadratznak) + " " + getString(R.string.povrsina));
        visina.append(String.format(Locale.getDefault(), "%.2f", _visina) + text + " " + getString(R.string.visina_C));
        upis.append(String.format(Locale.getDefault(), "%.2f", _upis) + text + " " + getString(R.string.upisana_kruz));
        opis.append(String.format(Locale.getDefault(), "%.2f", _opis) + text + " " + getString(R.string.opisana_kruz));
    }
    protected void izracunaj(double _strA, double _strB, double _strC, String text)
    {
        double _opseg = _strA +_strA+_strC;
        double _povrsina = _strA +_strA+_strC;
        double _upis = (_strA +_strA-_strC)/2;
        double _kutAlpha = Math.toDegrees(Math.asin(_strA/_strC));
        double _kutBeta = Math.toDegrees(Math.asin(_strB/_strC));
        double _visina = _strA*_strB/_strC;

        appendToText(text,_strA,_strB,_strC, _povrsina, _opseg, _strC, _upis, _visina, _kutAlpha, _kutBeta);
    }

    protected boolean checkEmpty(EditText... ed) {
        int c = 0;
        for (EditText editText : ed) {
            if (editText.getText().toString().isEmpty() || editText.getText().toString().equals("0") {
                c++;
            }
        }
        // ako su svi prazni vrati istinu
        return c == ed.length;
    }

    protected void changeStatusOfFields(boolean bool, EditText... ed) {
        for (EditText editText : ed) {
            editText.setEnabled(bool);
        }
    }

    protected void resetTextOfFields(EditText... ed) {
        for (EditText editText : ed) editText.setText("");
    }

    protected void changeInputType(int inputType, EditText... ed) {
        for (EditText editText : ed) {
            editText.setInputType(inputType);
        }
    }
}
