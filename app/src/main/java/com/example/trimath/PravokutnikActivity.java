package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class PravokutnikActivity extends AppCompatActivity {
    // Deklaracija tekstnih objekata
    EditText strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg;
    // Deklaracija gumba
    Button btn;
    SimplifiedToast st = new SimplifiedToast();
    int f = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pravokutnik);
        // Inicijalizacija tekstnih objekata
        strA = findViewById(R.id.editTextNumber11);
        strB = findViewById(R.id.editTextNumber12);
        dijagonala = findViewById(R.id.editTextNumber13);
        opiskru = findViewById(R.id.editTextNumber14);
        kutAlpha = findViewById(R.id.editTextNumber15);
        kutBeta = findViewById(R.id.editTextNumber16);
        povrsina = findViewById(R.id.editTextNumber17);
        opseg = findViewById(R.id.editTextNumber18);
        // Inicijalizacija gumba
        btn = findViewById(R.id.button3);
        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        btn.setOnClickListener(v -> {
            // provjera je li ijedna vrijednost unesena
            if (checkEmpty(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg)) {
                // ako nije prikaži poruku
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
                return;
            }
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }
            // dva kuta
            if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta)) && checkEmpty(strA, strB, dijagonala, opiskru, povrsina, opseg)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(dijagonala) && !checkEmpty(opiskru)) && checkEmpty(strA, strB, povrsina, opseg, kutBeta, kutAlpha)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)) && checkEmpty(dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg)) {
                izracunaj(strA, strB, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(dijagonala)) && checkEmpty(strB, opiskru, kutAlpha, kutBeta, povrsina, opseg)) {
                izracunaj(strA, dijagonala, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(opiskru)) && checkEmpty(strB, dijagonala, povrsina, opseg, kutAlpha, kutBeta)) {
                izracunaj(strA, opiskru, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(kutAlpha)) && checkEmpty(strB, dijagonala, kutBeta, opseg, povrsina, opiskru)) {
                izracunaj(strA, kutAlpha, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(kutBeta)) && checkEmpty(strB, dijagonala, kutAlpha, opseg, povrsina, opiskru)) {
                izracunaj(strA, kutBeta, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(povrsina)) && checkEmpty(strB, dijagonala, kutAlpha, opseg, kutBeta, opiskru)) {
                izracunaj(strA, povrsina, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(opseg)) && checkEmpty(strB, dijagonala, kutAlpha, povrsina, kutBeta, opiskru)) {
                izracunaj(strA, opseg, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(dijagonala)) && checkEmpty(strA, opiskru, kutAlpha, kutBeta, povrsina, opseg)) {
                izracunaj(strB, dijagonala, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(opiskru)) && checkEmpty(strA, dijagonala, povrsina, opseg, kutAlpha, kutBeta)) {
                izracunaj(strB, opiskru, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(kutAlpha)) && checkEmpty(strA, dijagonala, kutBeta, opseg, povrsina, opiskru)) {
                izracunaj(strB, kutAlpha, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(kutBeta)) && checkEmpty(strA, dijagonala, kutAlpha, opseg, povrsina, opiskru)) {
                izracunaj(strB, kutBeta, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(povrsina)) && checkEmpty(strA, dijagonala, kutAlpha, opseg, kutBeta, opiskru)) {
                izracunaj(strB, povrsina, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(opseg)) && checkEmpty(strA, dijagonala, kutAlpha, povrsina, kutBeta, opiskru)) {
                izracunaj(strB, opseg, "cm");
            } else if ((!checkEmpty(povrsina) && !checkEmpty(opseg)) && checkEmpty(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta)) {
                izracunaj(povrsina, opseg, "cm");
            } else if ((!checkEmpty(dijagonala) && !checkEmpty(kutAlpha)) && checkEmpty(strA, strB, opiskru, kutBeta, povrsina, opseg)) {
                izracunaj(dijagonala, kutAlpha, "cm");
            } else if ((!checkEmpty(dijagonala) && !checkEmpty(kutBeta)) && checkEmpty(strA, strB, opiskru, kutAlpha, povrsina, opseg)) {
                izracunaj(dijagonala, kutBeta, "cm");
            }  else if ((!checkEmpty(dijagonala) && !checkEmpty(povrsina)) && checkEmpty(strA, strB, opiskru, kutAlpha, kutBeta, opseg)) {
                izracunaj(dijagonala, povrsina, "cm");
            } else if ((!checkEmpty(dijagonala) && !checkEmpty(opseg)) && checkEmpty(strA, strB, opiskru, kutAlpha, povrsina, kutBeta)) {
                izracunaj(dijagonala, opseg, "cm");
            } else if ((!checkEmpty(opiskru) && !checkEmpty(kutAlpha)) && checkEmpty(strA, strB, dijagonala, kutBeta, povrsina, opseg)) {
                izracunaj(opiskru, kutAlpha, "cm");
            } else if ((!checkEmpty(opiskru) && !checkEmpty(kutBeta)) && checkEmpty(strA, strB, dijagonala, kutAlpha, povrsina, opseg)) {
                izracunaj(opiskru, kutBeta, "cm");
            } else if ((!checkEmpty(opiskru) && !checkEmpty(povrsina)) && checkEmpty(strA, strB, dijagonala, kutAlpha, kutBeta, opseg)) {
                izracunaj(opiskru, povrsina, "cm");
            } else if ((!checkEmpty(opiskru) && !checkEmpty(opseg)) && checkEmpty(strA, strB, dijagonala, kutAlpha, povrsina, kutBeta)) {
                izracunaj(opiskru, opseg, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(povrsina)) && checkEmpty(strA, strB, dijagonala, opiskru, opseg, kutBeta)) {
                izracunaj(kutAlpha, povrsina, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(opseg)) && checkEmpty(strA, strB, dijagonala, opiskru, povrsina, kutBeta)) {
                izracunaj(kutAlpha, opseg, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(povrsina)) && checkEmpty(strA, strB, dijagonala, opiskru, opseg, kutAlpha)) {
                izracunaj(kutBeta, povrsina, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(opseg)) && checkEmpty(strA, strB, dijagonala, opiskru, povrsina, kutAlpha)) {
                izracunaj(kutBeta, opseg, "cm");
            }  else {
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
            }
            // A B P O
            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
        });
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

    protected void appendToText(String text, double _strA, double _strB, double _dijagonala, double _polumjer, double _kutAlpha, double _kutBeta, double _opseg, double _povrsina) {
        strA.append(String.format(Locale.getDefault(), "%.2f", _strA) + text + " " + getString(R.string.stranica_A));
        strB.append(String.format(Locale.getDefault(), "%.2f", _strB) + text + " " + getString(R.string.stranica_B));
        dijagonala.append(String.format(Locale.getDefault(), "%.2f", _dijagonala) + text + " " + getString(R.string.dijagonala));
        opiskru.append(String.format(Locale.getDefault(), "%.2f", _polumjer) + text + " " + getString(R.string.polumjer));
        kutAlpha.append(String.format(Locale.getDefault(), "%.2f", _kutAlpha) + getString(R.string.stupnjeviznak) + " " + getString(R.string.alpha));
        kutBeta.append(String.format(Locale.getDefault(), "%.2f", _kutBeta) + getString(R.string.stupnjeviznak) + " " + getString(R.string.beta));
        opseg.append(String.format(Locale.getDefault(), "%.2f", _opseg) + text + " " + getString(R.string.opseg));
        povrsina.append(String.format(Locale.getDefault(), "%.2f", _povrsina) + text + getString(R.string.nakvadratznak) + " " + getString(R.string.povrsina));
    }

    protected void izracunaj(EditText ed, EditText ed2, String text) {
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
        if (ed.equals(strA) && ed2.equals(strB)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB,2)));
            double _polumjer = _dijagonala/2;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            double _opseg = 2 * (_strA + _strB);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strA) && ed2.equals(dijagonala)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _dijagonala = Double.parseDouble(ed2.getText().toString());
            if (_strA >= _dijagonala) {
                st.toastShort(this, getString(R.string.nije_moguce));
                return;
            }
            double _strB = Math.sqrt((Math.pow(_dijagonala,2) - Math.pow(_strA,2)));
            double _polumjer = _dijagonala/2;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            double _opseg = 2 * (_strA + _strB);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strA) && ed2.equals(opiskru)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _polumjer = Double.parseDouble(ed2.getText().toString());
            double _dijagonala = _polumjer * 2;
            double _strB = Math.sqrt(Math.pow(_dijagonala,2) - Math.pow(_strA,2));
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            double _opseg = 2 * (_strA + _strB);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strA) && ed2.equals(kutAlpha)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = 180 - _kutAlpha;
            double _strB = _strA/(Math.tan(Math.toRadians(_kutAlpha/2)));
            double _dijagonala = Math.sqrt((Math.pow(_strA, 2) + Math.pow(_strB, 2)));
            double _polumjer = _dijagonala/2;
            double _opseg = 2 * (_strA + _strB);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strA) && ed2.equals(kutBeta)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = 180 - _kutBeta;
            double _strB = _strA/(Math.tan(Math.toRadians(_kutAlpha)/2));
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB, 2)));
            double _polumjer = _dijagonala/2;
            double _opseg = 2 * (_strA + _strB);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strA) && ed2.equals(povrsina)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strB = _povrsina/_strA;
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB, 2)));
            double _polumjer = _dijagonala/2;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            double _opseg = 2 * (_strA + _strB);
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strA) && ed2.equals(opseg)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            if (_opseg <= (2 * _strA)) {
                st.toastShort(this, getString(R.string.nije_moguce));
                return;
            }
            double _strB = (_opseg - (2 * _strA))/2;
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB, 2)));
            double _polumjer = _dijagonala/2;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strB) && ed2.equals(dijagonala)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _dijagonala = Double.parseDouble(ed2.getText().toString());
            if (_strB >= _dijagonala) {
                st.toastShort(this, getString(R.string.nije_moguce));
                return;
            }
            double _strA = Math.sqrt((Math.pow(_dijagonala,2) - Math.pow(_strB,2)));
            double _polumjer = _dijagonala/2;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            double _opseg = 2 * (_strB + _strA);
            double _povrsina = _strB * _strA;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strB) && ed2.equals(opiskru)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _polumjer = Double.parseDouble(ed2.getText().toString());
            double _dijagonala = _polumjer * 2;
            double _strA = Math.sqrt(Math.pow(_dijagonala,2) - Math.pow(_strB,2));
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            double _opseg = 2 * (_strA + _strB);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strB) && ed2.equals(kutAlpha)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = 180 - _kutAlpha;
            double _strA = _strB * Math.tan(Math.toRadians(_kutAlpha/2));
            double _dijagonala = Math.sqrt((Math.pow(_strA, 2) + Math.pow(_strB, 2)));
            double _polumjer = _dijagonala/2;
            double _opseg = 2 * (_strA + _strB);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strB) && ed2.equals(kutBeta)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = 180 - _kutBeta;
            double _strA = _strB * Math.tan(Math.toRadians(_kutAlpha/2));
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB, 2)));
            double _polumjer = _dijagonala/2;
            double _opseg = 2 * (_strA + _strB);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strB) && ed2.equals(povrsina)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strA = _povrsina/_strB;
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB, 2)));
            double _polumjer = _dijagonala/2;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            double _opseg = 2 * (_strA + _strB);
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(strB) && ed2.equals(opseg)) {
            double _strB = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            if (_opseg <= (2 * _strB)) {
                st.toastShort(this, getString(R.string.nije_moguce));
                return;
            }
            double _strA = (_opseg - (2 * _strB))/2;
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB, 2)));
            double _polumjer = _dijagonala/2;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            double _povrsina = _strA * _strB;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(povrsina) && ed2.equals(opseg)) {
            double _povrsina = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            if (16 * _povrsina >= Math.pow(_opseg, 2)) {
                st.toastShort(this, getString(R.string.nije_moguce));
                return;
            }
            double _strA = (_opseg + Math.sqrt(Math.pow(_opseg, 2) - (16 * _povrsina)))/4;
            double _strB = _povrsina/_strA;
            double _dijagonala = Math.sqrt(Math.pow(_strA, 2) + Math.pow(_strB, 2));
            double _polumjer = _dijagonala/2;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(dijagonala) && ed2.equals(kutAlpha)) {
            double _dijagonala = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Double.parseDouble(ed2.getText().toString());
            double _strA = _dijagonala * Math.sin(Math.toRadians(_kutAlpha/2));
            double _strB = Math.sqrt(Math.pow(_dijagonala, 2) - Math.pow(_strA, 2));
            double _povrsina = _strA * _strB;
            double _opseg = 2 * (_strA + _strB);
            double _polumjer = _dijagonala/2;
            double _kutBeta = 180 - _kutAlpha;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(dijagonala) && ed2.equals(kutBeta)) {
            double _dijagonala = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = 180 - _kutBeta;
            double _strA = _dijagonala * Math.sin(Math.toRadians(_kutAlpha/2));
            double _strB = Math.sqrt(Math.pow(_dijagonala, 2) - Math.pow(_strA, 2));
            double _povrsina = _strA * _strB;
            double _opseg = 2 * (_strA + _strB);
            double _polumjer = _dijagonala/2;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(dijagonala) && ed2.equals(povrsina)) {
            double _dijagonala = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strA = Math.sqrt((Math.pow(_dijagonala, 2) + Math.sqrt(Math.pow(_dijagonala, 4) - (4 * Math.pow(_povrsina, 2))))/2);
            double _strB = _povrsina/_strA;
            double _opseg = 2 * (_strA + _strB);
            double _polumjer = _dijagonala/2;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(dijagonala) && ed2.equals(opseg)) {
            double _dijagonala = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            if (_opseg <= (_dijagonala * 2)) {
                st.toastShort(this, getString(R.string.nije_moguce));
                return;
            }
            double _strA = (_opseg + Math.sqrt(-Math.pow(_opseg, 2) + (8 * _dijagonala)))/4;
            double _strB = (_opseg - (2 * _strA))/2;
            double _polumjer = _dijagonala/2;
            double _povrsina = _strA * _strB;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(opiskru) && ed2.equals(kutAlpha)) {
            double _polumjer = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = 180 - _kutAlpha;
            double _dijagonala = 2 * _polumjer;
            double _strA = _dijagonala * Math.sin(Math.toRadians(_kutAlpha)/2);
            double _strB = Math.sqrt(Math.pow(_dijagonala, 2) - Math.pow(_strA, 2));
            double _povrsina = _strA * _strB;
            double _opseg = 2 * (_strA + _strB);
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(opiskru) && ed2.equals(kutBeta)) {
            double _polumjer = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = 180 - _kutBeta;
            double _dijagonala = 2 * _polumjer;
            double _strA = _dijagonala * Math.sin(Math.toRadians(_kutAlpha)/2);
            double _strB = Math.sqrt(Math.pow(_dijagonala, 2) - Math.pow(_strA, 2));
            double _povrsina = _strA * _strB;
            double _opseg = 2 * (_strA + _strB);
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(opiskru) && ed2.equals(povrsina)) {
            double _polumjer = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _dijagonala = 2 * _polumjer;
            double _strA = Math.sqrt((Math.pow(_dijagonala, 2) + Math.sqrt(Math.pow(_dijagonala, 4) - (4 * Math.pow(_povrsina, 2))))/2);
            double _strB = _povrsina/_strA;
            double _opseg = 2 * (_strA + _strB);
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(opiskru) && ed2.equals(opseg)) {
            double _polumjer = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            double _dijagonala = 2 * _polumjer;
            if (_opseg <= (_dijagonala * 2)) {
                st.toastShort(this, getString(R.string.nije_moguce));
                return;
            }
            double _strA = (_opseg + Math.sqrt(-Math.pow(_opseg, 2) + (8 * Math.pow(_dijagonala, 2))))/4;
            double _strB = (_opseg - (2 * _strA))/2;
            double _povrsina = _strA * _strB;
            double _kutAlpha = Math.toDegrees(Math.asin(_strA/_dijagonala) * 2);
            double _kutBeta = Math.toDegrees(Math.asin(_strB/_dijagonala) * 2);
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(kutAlpha) && ed2.equals(povrsina)) {
            double _kutAlpha = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strA = Math.sqrt(_povrsina * Math.tan(Math.toRadians(_kutAlpha)/2));
            double _strB = _povrsina/_strA;
            double _opseg = 2 * (_strA + _strB);
            double _dijagonala = Math.sqrt(Math.pow(_strA, 2) + Math.pow(_strB, 2));
            double _polumjer = _dijagonala/2;
            double _kutBeta = 180 - _kutAlpha;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(kutAlpha) && ed2.equals(opseg)) {
            double _kutAlpha = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            double _strA = (_opseg * Math.tan(Math.toRadians(_kutAlpha)/2))/(2 * (Math.tan(Math.toRadians(_kutAlpha)/2) + 1));
            double _strB = _strA/Math.tan(Math.toRadians(_kutAlpha)/2);
            double _povrsina = _strA * _strB;
            double _dijagonala = Math.sqrt(Math.pow(_strA, 2) + Math.pow(_strB, 2));
            double _polumjer = _dijagonala/2;
            double _kutBeta = 180 - _kutAlpha;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(kutBeta) && ed2.equals(povrsina)) {
            double _kutBeta = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = 180 - _kutBeta;
            double _strA = Math.sqrt(_povrsina * Math.tan(Math.toRadians(_kutAlpha)/2));
            double _strB = _povrsina/_strA;
            double _opseg = 2 * (_strA + _strB);
            double _dijagonala = Math.sqrt(Math.pow(_strA, 2) + Math.pow(_strB, 2));
            double _polumjer = _dijagonala/2;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        if (ed.equals(kutBeta) && ed2.equals(opseg)) {
            double _kutBeta = Double.parseDouble(ed.getText().toString());
            double _opseg = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = 180 - _kutBeta;
            double _strA = (_opseg * Math.tan(Math.toRadians(_kutAlpha)/2))/(2 * (Math.tan(Math.toRadians(_kutAlpha)/2) + 1));
            double _strB = _strA/Math.tan(Math.toRadians(_kutAlpha)/2);
            double _povrsina = _strA * _strB;
            double _dijagonala = Math.sqrt(Math.pow(_strA, 2) + Math.pow(_strB, 2));
            double _polumjer = _dijagonala/2;
            resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
            appendToText(text, _strA, _strB, _dijagonala, _polumjer, _kutAlpha, _kutBeta, _opseg, _povrsina);
        }
        // vrati nazad originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
    }
}