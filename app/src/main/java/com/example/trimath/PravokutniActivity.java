package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class PravokutniActivity extends AppCompatActivity {
    // Deklaracija tekstnih objekata
    EditText strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg, visina;
    // Deklaracija gumba
    Button btn, btninf;
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
        kutAlpha = findViewById(R.id.editTextNumber16);
        kutBeta = findViewById(R.id.editTextNumber17);
        povrsina = findViewById(R.id.editTextNumber18);
        opseg = findViewById(R.id.editTextNumber19);
        visina = findViewById(R.id.editTextNumber20);
        // Inicijalizacija gumba
        btn = findViewById(R.id.button2);

        // prikaz informacija..
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(PravokutniActivity.this);
        dialog1.setTitle("Informacije o aplikaciji")
                .setMessage("Autori: Marino Tadić, Matija Modrić\n\nOva aplikacija je namijenjena učenicima i profesorima za lakše provjeravanje matematičih rezultata.\n\nAplikaciju koristite klikom na gumb za željeni kalkulator te unosite vrijednosti i stisnete gumb za izračun.\nMentor: Željko Turkalj")
                .setCancelable(true)
                .setPositiveButton("Zatvori", (dialog, id) -> dialog.cancel());
        AlertDialog alr1 = dialog1.create();

        (btninf = findViewById(R.id.button11)).setOnClickListener(v -> {
            try {
                alr1.show();
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

        btn.setOnClickListener(v -> {
            // provjera je li jedna vrijednost unesena
            if (checkEmpty(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina)) {
                // ako nije prikaži poruku
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
                return;
            }
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }

            //nemoguce kombinacije
            if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta)) && checkEmpty(strA, strB, strC, visina, povrsina, opseg)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(povrsina) && !checkEmpty(visina)) && checkEmpty(strA, strB, strC, opseg, kutBeta, kutAlpha)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            }
            //moguce kombinacije
            else if ((!checkEmpty(strA) && !checkEmpty(strB)) && checkEmpty(strC, visina, povrsina, opseg, kutAlpha, kutBeta)) {
                izracunaj(strA, strB, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC)) && checkEmpty(strB, visina, povrsina, opseg, kutAlpha, kutBeta)) {
                izracunaj(strA, strC, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(visina)) && checkEmpty(strB, strC, povrsina, opseg, kutAlpha, kutBeta)) {
                izracunaj(strA, visina, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(kutAlpha)) && checkEmpty(strB, strC, kutBeta, opseg, povrsina, visina)) {
                izracunaj(strA, kutAlpha, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(kutBeta)) && checkEmpty(strB, strC, kutAlpha, opseg, povrsina, visina)) {
                izracunaj(strA, kutBeta, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(povrsina)) && checkEmpty(strB, strC, kutAlpha, visina, opseg, kutBeta)) {
                izracunaj(strA, povrsina, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)) && checkEmpty(strA, kutAlpha, kutBeta, visina, povrsina, opseg)) {
                izracunaj(strB, strC, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(kutAlpha)) && checkEmpty(strA, strC, kutBeta, visina, povrsina, opseg)) {
                izracunaj(strB, kutAlpha, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(kutBeta)) && checkEmpty(strA, strC, kutAlpha, visina, povrsina, opseg)) {
                izracunaj(strB, kutBeta, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(visina)) && checkEmpty(strA, strC, kutAlpha, kutBeta, povrsina, opseg)) {
                izracunaj(strB, visina, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(povrsina)) && checkEmpty(strA, strC, kutAlpha, visina, opseg, kutBeta)) {
                izracunaj(strB, povrsina, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(strC)) && checkEmpty(strA, strB, visina, opseg, povrsina, kutBeta)) {
                izracunaj(kutAlpha, strC, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(visina)) && checkEmpty(strA, strB, strC, opseg, povrsina, kutBeta)) {
                izracunaj(kutAlpha, visina, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(strC)) && checkEmpty(strA, strB, visina, opseg, povrsina, kutAlpha)) {
                izracunaj(kutBeta, strC, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(visina)) && checkEmpty(strA, strB, strC, opseg, povrsina, kutAlpha)) {
                izracunaj(kutBeta, visina, "cm");
            } else if ((!checkEmpty(povrsina) && !checkEmpty(opseg)) && checkEmpty(strA, strB, strC, visina, kutAlpha, kutBeta)) {
                izracunaj(povrsina, opseg, "cm");
            } else if ((!checkEmpty(opseg) && !checkEmpty(strC)) && checkEmpty(strA, strB, visina, povrsina, kutBeta, kutAlpha)) {
                izracunaj(strC, opseg, "cm");
            } else if ((!checkEmpty(opseg) && !checkEmpty(strB)) && checkEmpty(strA, strC, visina, povrsina, kutBeta, kutAlpha)) {
                izracunaj(strB, opseg, "cm");
            } else if ((!checkEmpty(opseg) && !checkEmpty(strA)) && checkEmpty(strB, strC, visina, povrsina, kutBeta, kutAlpha)) {
                izracunaj(strA, opseg, "cm");
            } else if ((!checkEmpty(povrsina) && !checkEmpty(strC)) && checkEmpty(strA, strB, visina, opseg, kutBeta, kutAlpha)) {
                izracunaj(strC, povrsina, "cm");
            } else {
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
            }

            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
        });

    }

    protected void izracunaj(EditText ed, EditText ed2, String text) {
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg,visina);
        if (ed.equals(strA) && ed2.equals(strB)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _strC = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB,2)));
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strA) && ed2.equals(strC)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strB = Math.sqrt((Math.pow(_strC,2) - Math.pow(_strA,2)));

            if (_strA >= _strC || _strB >= _strC) {
                st.toastShort(this, getString(R.string.nije_moguce));
                changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strA) && ed2.equals(visina)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(visina.getText().toString());
            double _kutBeta = Math.asin(_visina/_strA);

            double _strC = _strA/Math.cos(_kutBeta);
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strA) && ed2.equals(kutAlpha)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Double.parseDouble(ed2.getText().toString());
            double _strC = _strA/Math.sin(Math.toRadians(_kutAlpha));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strA) && ed2.equals(kutBeta)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _strC = _strA/Math.cos(Math.toRadians(_kutBeta));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strA) && ed2.equals(povrsina)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strB = 2*_povrsina/_strA;
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strB) && ed2.equals(strC)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strA = Math.sqrt(Math.pow(_strC,2) - Math.pow(_strB,2));

            if (_strA >= _strC || _strB >= _strC) {
                st.toastShort(this, getString(R.string.nije_moguce));
                changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strB) && ed2.equals(kutAlpha)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _kutAlfa = Double.parseDouble(ed2.getText().toString());
            double _strC = _strB/Math.cos(Math.toRadians(_kutAlfa));
            double _strA = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strB) && ed2.equals(kutBeta)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _strC = _strB/Math.sin(Math.toRadians(_kutBeta));
            double _strA = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strB) && ed2.equals(visina)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(visina.getText().toString());
            double _kutAlpha = Math.asin(_visina/_strB);

            double _strC = _strB/Math.cos(_kutAlpha);
            double _strA = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strB) && ed2.equals(povrsina)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strA = 2*_povrsina/_strB;
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(kutAlpha) && ed2.equals(strC)) {
            double _kutAlpha = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strA = _strC * Math.sin(Math.toRadians(_kutAlpha));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(kutAlpha) && ed2.equals(visina)) {
            double _kutAlpha = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(ed2.getText().toString());
            double _strB =  _visina/Math.sin(Math.toRadians(_kutAlpha));
            double _strA =  _visina/Math.sin(Math.toRadians(90-_kutAlpha));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(kutBeta) && ed2.equals(strC)) {
            double _kutBeta = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _strA = _strC * Math.cos(Math.toRadians(_kutBeta));
            double _strB = Math.sqrt(Math.pow(_strC,2)-Math.pow(_strA,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(kutBeta) && ed2.equals(visina)) {
            double _kutBeta = Double.parseDouble(ed.getText().toString());
            double _visina = Double.parseDouble(ed2.getText().toString());
            double _strA =  _visina/Math.sin(Math.toRadians(_kutBeta));
            double _strB =  _visina/Math.sin(Math.toRadians(90-_kutBeta));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2));

            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(povrsina) && ed2.equals(opseg)) {
            double _povrsina = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            // vrijednost da se formule ne ponavljaju
            double _t = 16 * Math.pow(_povrsina, 2) + Math.pow(_opseg, 4);
            if ((24 * _povrsina * Math.pow(_opseg, 4)) > _t) {
                st.toastShort(this, getString(R.string.nije_moguce));
                changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }
            double _strA = (4 * _povrsina + Math.pow(_opseg, 2) + Math.sqrt(_t - (24 * _povrsina * Math.pow(_opseg, 4))))/4 * _opseg;
            double _strB = (2 * _povrsina)/_strA;
            double _strC = Math.sqrt(Math.pow(_strA, 2) + Math.pow(_strB, 2));
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strC) && ed2.equals(opseg)) {
            double _strC = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            if ((2 * _opseg * _strC + Math.pow(_strC, 2)) < Math.pow(_opseg, 2)) {
                st.toastShort(this, getString(R.string.nije_moguce));
                changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }
            double _strA = (_strC - _opseg + Math.sqrt(-(Math.pow(_opseg, 2) + (2 * _opseg * _strC) + Math.pow(_strC, 2))))/-2;
            double _strB = Math.sqrt(Math.pow(_strC, 2) - Math.pow(_strA, 2));
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strB) && ed2.equals(opseg)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            if (Math.pow(_opseg, 2) < (2 * _strB * _opseg)) {
                st.toastShort(this, getString(R.string.nije_moguce));
                changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }
            double _strA = (Math.pow(_opseg, 2) - 2 * _strB * _opseg)/(2*(_opseg - _strB));
            double _strC = Math.sqrt(Math.pow(_strA, 2) + Math.pow(_strB, 2));
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strA) && ed2.equals(opseg)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            if (Math.pow(_opseg, 2) < (2 * _strA * _opseg)) {
                st.toastShort(this, getString(R.string.nije_moguce));
                changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }
            double _strB = (Math.pow(_opseg, 2) - 2 * _strA * _opseg)/(2*(_opseg - _strA));
            double _strC = Math.sqrt(Math.pow(_strA, 2) + Math.pow(_strB, 2));
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        else if (ed.equals(strC) && ed2.equals(povrsina)) {
            double _strC = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            if (Math.pow(_strC, 4) < (16 * Math.pow(_povrsina, 2))) {
                st.toastShort(this, getString(R.string.nije_moguce));
                changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg,visina);
                return;
            }
            double _strA = Math.sqrt((Math.pow(_strC, 2) + Math.sqrt(Math.pow(_strC, 4) - (16 * Math.pow(_povrsina, 2))))/2);
            double _strB = Math.sqrt(Math.pow(_strC, 2) - Math.pow(_strA, 2));
            resetTextOfFields(strA,strB,strC, povrsina, opseg, visina,kutAlpha,kutBeta,visina);
            izracunaj(_strA,_strB,_strC,text);
        }
        // vrati nazad originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, kutAlpha, kutBeta, povrsina, opseg,visina);
    }

    protected void appendToText(String text,double _strA, double _strB, double _strC, double _povrsina, double _opseg, double _visina, double _kutAlpha, double _kutBeta) {
        strA.append(String.format(Locale.getDefault(), "%.4f", _strA) + text + " " + getString(R.string.stranica_A));
        strB.append(String.format(Locale.getDefault(), "%.4f", _strB) + text + " " + getString(R.string.stranica_B));
        strC.append(String.format(Locale.getDefault(), "%.4f", _strC) + text + " " + getString(R.string.stranica_C));
        kutAlpha.append(String.format(Locale.getDefault(), "%.4f", _kutAlpha) + getString(R.string.stupnjeviznak) + " " + getString(R.string.alpha) + " " + convertToDMS(_kutAlpha));
        kutBeta.append(String.format(Locale.getDefault(), "%.4f", _kutBeta) + getString(R.string.stupnjeviznak) + " " + getString(R.string.beta) + " " + convertToDMS(_kutBeta));
        opseg.append(String.format(Locale.getDefault(), "%.4f", _opseg) + text + " " + getString(R.string.opseg));
        povrsina.append(String.format(Locale.getDefault(), "%.4f", _povrsina) + text + getString(R.string.nakvadratznak) + " " + getString(R.string.povrsina));
        visina.append(String.format(Locale.getDefault(), "%.4f", _visina) + text + " " + getString(R.string.visina_C));
    }

    // https://codepal.ai/code-generator/query/gvNUnvus/java-decimal-degree-to-dms-converter
    protected static String convertToDMS(double decimalDegree) {
        int degrees = (int) decimalDegree;
        double minutesDecimal = (decimalDegree - degrees) * 60;
        int minutes = (int) minutesDecimal;
        double secondsDecimal = (minutesDecimal - minutes) * 60;
        int seconds = (int) secondsDecimal;
        return degrees + "°" + minutes + "'" + seconds + "\"";
    }

    protected void izracunaj(double _strA, double _strB, double _strC, String text)
    {
        double _opseg = _strA +_strA+_strC;
        double _povrsina = (_strA + _strB)/2;
        double _kutAlpha = Math.toDegrees(Math.asin(_strA/_strC));
        double _kutBeta = Math.toDegrees(Math.asin(_strB/_strC));
        double _visina = _strA*_strB/_strC;

        appendToText(text,_strA,_strB,_strC, _povrsina, _opseg, _visina, _kutAlpha, _kutBeta);
    }

    protected boolean checkEmpty(EditText... ed) {
        int c = 0;
        for (EditText editText : ed) {
            if (editText.getText().toString().isEmpty() || editText.getText().toString().equals("0")) {
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
