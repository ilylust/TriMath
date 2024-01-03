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

            // dva kuta, pov i opseg, opis i dijagona
            if ((!kutAlpha.getText().toString().isEmpty() && !kutBeta.getText().toString().isEmpty()) && checkEmpty(strA, strB, strC, visina, povrsina, opseg, opis, upis)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!povrsina.getText().toString().isEmpty() && !opseg.getText().toString().isEmpty()) && checkEmpty(strA, strB, strC, visina, opis, upis, kutAlpha, kutBeta)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            }
             else if ((!strA.getText().toString().isEmpty() && !strB.getText().toString().isEmpty()) && checkEmpty(strC, visina, povrsina, opseg, opis, upis, kutAlpha, kutBeta)) {
                izracunaj(strA, strB, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !strC.getText().toString().isEmpty()) && checkEmpty(strB, visina, povrsina, opseg, opis, upis, kutAlpha, kutBeta)) {
                izracunaj(strA, strC, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !visina.getText().toString().isEmpty()) && checkEmpty(strB, strC, povrsina, opseg, kutAlpha, kutBeta,opis,upis)) {
                izracunaj(strA, visina, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !kutAlpha.getText().toString().isEmpty()) && checkEmpty(strB, strC, kutBeta, opseg, povrsina, visina,opis,upis)) {
                izracunaj(strA, kutAlpha, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !kutBeta.getText().toString().isEmpty()) && checkEmpty(strB, strC, kutAlpha, opseg, povrsina, visina,opis,upis)) {
                izracunaj(strA, kutBeta, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !povrsina.getText().toString().isEmpty()) && checkEmpty(strB, strC, kutAlpha, visina, opseg, kutBeta, opis, upis)) {
                izracunaj(strA, povrsina, "cm");
            } else if ((!strB.getText().toString().isEmpty() && !strC.getText().toString().isEmpty()) && checkEmpty(strA, kutAlpha, kutBeta, visina, povrsina, opseg,  opis, upis)) {
                izracunaj(strB, strC, "cm");
            } else if ((!strB.getText().toString().isEmpty() && !kutAlpha.getText().toString().isEmpty()) && checkEmpty(strA, strC, kutBeta, visina, povrsina, opseg,  opis, upis)) {
                izracunaj(strB, kutAlpha, "cm");
            } else if ((!strB.getText().toString().isEmpty() && !kutBeta.getText().toString().isEmpty()) && checkEmpty(strA, strC, kutAlpha, visina, povrsina, opseg,  opis, upis)) {
                izracunaj(strB, kutBeta, "cm");
            } else if ((!strB.getText().toString().isEmpty() && !visina.getText().toString().isEmpty()) && checkEmpty(strA, strC, kutAlpha, kutBeta, povrsina, opseg,  opis, upis)) {
                izracunaj(strB, visina, "cm");
            } else if ((!strB.getText().toString().isEmpty() && !povrsina.getText().toString().isEmpty()) && checkEmpty(strA, strC, kutAlpha, visina, opseg, kutBeta, opis, upis)) {
                izracunaj(strB, povrsina, "cm");
            } else if ((!kutAlpha.getText().toString().isEmpty() && !strC.getText().toString().isEmpty()) && checkEmpty(strA, strB, visina, opseg, povrsina, kutBeta, opis, upis)) {
                izracunaj(kutAlpha, strC, "cm");
            } else if ((!kutAlpha.getText().toString().isEmpty() && !visina.getText().toString().isEmpty()) && checkEmpty(strA, strB, strC, opseg, povrsina, kutBeta, opis, upis)) {
                izracunaj(kutAlpha, visina, "cm");
            } else if ((!kutBeta.getText().toString().isEmpty() && !strC.getText().toString().isEmpty()) && checkEmpty(strA, strB, visina, opseg, povrsina, kutAlpha, opis, upis)) {
                izracunaj(kutBeta, strC, "cm");
            } else if ((!kutBeta.getText().toString().isEmpty() && !visina.getText().toString().isEmpty()) && checkEmpty(strA, strB, strC, opseg, povrsina, kutAlpha, opis, upis)) {
                izracunaj(kutBeta, visina, "cm");
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



            strA.append(text + " (stranica A)");
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(strA) && ed2.equals(strC)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strB = Math.sqrt((Math.pow(_strC,2) - Math.pow(_strA,2)));

            if (_strA >= _strC || _strB >= _strC) {
                st.toastShort(this, getString(R.string.nije_moguce));
                changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, upis, opis, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }

            strA.append(text + " (stranica A)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            strC.append(text + " (stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(strA) && ed2.equals(visina)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(visina.getText().toString());
            double _kutBeta = Math.asin(_visina/_strA);

            double _strC = _strA/Math.cos(_kutBeta);
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            strA.append(text + " (stranica A)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(_kutBeta)));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(strA) && ed2.equals(kutAlpha)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Double.parseDouble(ed2.getText().toString());
            double _strC = _strA/Math.sin(Math.toRadians(_kutAlpha));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            strA.append(text + " (stranica A)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }

        if (ed.equals(strA) && ed2.equals(kutBeta)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _strC = _strA/Math.cos(Math.toRadians(_kutBeta));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            strA.append(text + " (stranica A)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }

        if (ed.equals(strA) && ed2.equals(povrsina)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strB = 2*_povrsina/_strA;
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            strA.append(text + " (stranica A)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
            if (ed.equals(strB) && ed2.equals(strC)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strA = Math.sqrt(Math.pow(_strC,2) - Math.pow(_strB,2));

            if (_strA >= _strC || _strB >= _strC) {
                st.toastShort(this, getString(R.string.nije_moguce));
                changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, upis, opis, kutAlpha, kutBeta, povrsina, opseg,visina);
                    return;
            }
            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            strB.append(text + " (stranica B)");
            strC.append(text + " (stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(strB) && ed2.equals(kutAlpha)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _kutAlfa = Double.parseDouble(ed2.getText().toString());
            double _strC = _strB/Math.cos(Math.toRadians(_kutAlfa));
            double _strA = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strB,2));

            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(strB) && ed2.equals(kutBeta)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _strC = _strB/Math.sin(Math.toRadians(_kutBeta));
            double _strA = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strB,2));

            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(strB) && ed2.equals(visina)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(visina.getText().toString());
            double _kutAlpha = Math.asin(_visina/_strB);

            double _strC = _strB/Math.cos(_kutAlpha);
            double _strA = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strB,2));

            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(_kutAlpha)));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(strB) && ed2.equals(povrsina)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strA = 2*_povrsina/_strB;
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(kutAlpha) && ed2.equals(strC)) {
            double _kutAlpha = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strA = _strC * Math.sin(Math.toRadians(_kutAlpha));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(kutAlpha) && ed2.equals(visina)) {
            double _kutAlpha = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(ed2.getText().toString());
            double _strB =  _visina/Math.sin(Math.toRadians(_kutAlpha));
            double _strA =  _visina/Math.sin(Math.toRadians(90-_kutAlpha));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_strC))));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(kutBeta) && ed2.equals(strC)) {
            double _kutBeta = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strA = _strC * Math.cos(Math.toRadians(_kutBeta));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/_strC));
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }
        if (ed.equals(kutBeta) && ed2.equals(visina)) {
            double _kutBeta = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(ed2.getText().toString());
            double _strA =  _visina/Math.sin(Math.toRadians(_kutBeta));
            double _strB =  _visina/Math.sin(Math.toRadians(90-_kutBeta));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            strC.append(String.format(Locale.getDefault(), "%.2f", _strC));
            strC.append(text + " (Stranica C)");
            opis.append(String.format(Locale.getDefault(), "%.2f", _strC));
            opis.append(text + " (polumjer opisane)");
            upis.append(String.format(Locale.getDefault(), "%.2f", (_strA+_strB-_strC)/2));
            upis.append(text + " (polumjer upisane)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_strC))));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", _strA + _strB+_strC));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB/2));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            visina.append(text + getString(R.string.nakvadratznak) + " (visina)");
        }


        // vrati nazad originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, upis, opis, kutAlpha, kutBeta, povrsina, opseg,visina);
    }



    protected boolean checkEmpty(EditText... ed) {
        int c = 0;
        for (EditText editText : ed) {
            if (editText.getText().toString().isEmpty()) {
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