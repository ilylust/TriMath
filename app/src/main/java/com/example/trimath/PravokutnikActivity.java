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
            // provjera je li jedna vrijednost unesena
            if (checkEmpty(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg)) {
                // ako nije prikaži poruku
                st.toastShort(this, getString(R.string.unesite_dvije_vrijednosti));
                return;
            }
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }
            // dva kuta, pov i opseg, opis i dijagona
            if ((!kutAlpha.getText().toString().isEmpty() && !kutBeta.getText().toString().isEmpty()) && checkEmpty(strA, strB, dijagonala, opiskru, povrsina, opseg)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!povrsina.getText().toString().isEmpty() && !opseg.getText().toString().isEmpty()) && checkEmpty(strA, strB, dijagonala, opiskru, kutAlpha, kutBeta)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!opiskru.getText().toString().isEmpty() && !dijagonala.getText().toString().isEmpty()) && checkEmpty(strA, strB, povrsina, opseg, kutBeta, kutAlpha)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!strA.getText().toString().isEmpty() && !strB.getText().toString().isEmpty()) && checkEmpty(dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg)) {
                izracunaj(strA, strB, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !dijagonala.getText().toString().isEmpty()) && checkEmpty(strB, opiskru, kutAlpha, kutBeta, povrsina, opseg)) {
                izracunaj(strA, dijagonala, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !opiskru.getText().toString().isEmpty()) && checkEmpty(strB, dijagonala, povrsina, opseg, kutAlpha, kutBeta)) {
                izracunaj(strA, opiskru, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !kutAlpha.getText().toString().isEmpty()) && checkEmpty(strB, dijagonala, kutBeta, opseg, povrsina, opiskru)) {
                izracunaj(strA, kutAlpha, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !kutBeta.getText().toString().isEmpty()) && checkEmpty(strB, dijagonala, kutAlpha, opseg, povrsina, opiskru)) {
                izracunaj(strA, kutBeta, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !povrsina.getText().toString().isEmpty()) && checkEmpty(strB, dijagonala, kutAlpha, opseg, kutBeta, opiskru)) {
                izracunaj(strA, povrsina, "cm");
            } else if ((!strA.getText().toString().isEmpty() && !opseg.getText().toString().isEmpty()) && checkEmpty(strB, dijagonala, kutAlpha, povrsina, kutBeta, opiskru)) {
                izracunaj(strA, opseg, "cm");
            }
            // u else unesite samo dvije vrijednosti!
            // promijeni tekst na gumbu
            btn.setText(R.string.izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
        });
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

    protected void izracunaj(EditText ed, EditText ed2, String text) {
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
        if (ed.equals(strA) && ed2.equals(strB)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB,2)));

            strA.append(text + " (stranica A)");
            strB.append(text + " (stranica B)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _dijagonala));
            dijagonala.append(text + " (dijagonala)");
            opiskru.append(String.format(Locale.getDefault(), "%.2f", _dijagonala/2));
            opiskru.append(text + " (polumjer)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_dijagonala) * 2)));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_dijagonala) * 2)));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 2 * (_strA + _strB)));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
        }
        if (ed.equals(strA) && ed2.equals(dijagonala)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _dijagonala = Double.parseDouble(ed2.getText().toString());

            if (_strA >= _dijagonala) {
                st.toastShort(this, getString(R.string.nije_moguce));
                return;
            }

            double _strB = Math.sqrt((Math.pow(_dijagonala,2) - Math.pow(_strA,2)));

            strA.append(text + " (stranica A)");
            dijagonala.append(text + " (dijagonala)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 2 * (_strA + _strB)));
            opseg.append(text + " (opseg)");
            opiskru.append(String.format(Locale.getDefault(), "%.2f", _dijagonala/2));
            opiskru.append(text + " (polumjer)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_dijagonala) * 2)));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_dijagonala) * 2)));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
        }
        if (ed.equals(strA) && ed2.equals(opiskru)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _opiskru = Double.parseDouble(ed2.getText().toString());
            double _dijagonala = _opiskru * 2;
            double _strB = Math.sqrt(Math.pow(_dijagonala,2) - Math.pow(_strA,2));

            strA.append(text + " (stranica A)");
            opiskru.append(text + " (polumjer)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _dijagonala));
            dijagonala.append(text + " (dijagonala)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_dijagonala) * 2)));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_dijagonala) * 2)));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 2 * (_strA + _strB)));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
        }
        if (ed.equals(strA) && ed2.equals(kutAlpha)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = 180 - _kutAlpha;
            double _strB = _strA/(Math.tan(Math.toRadians(_kutAlpha/2)));
            double _dijagonala = Math.sqrt((Math.pow(_strA, 2) + Math.pow(_strB, 2)));

            strA.append(text + " (stranica A)");
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _dijagonala));
            dijagonala.append(text + " (dijagonala)");
            opiskru.append(String.format(Locale.getDefault(), "%.2f", _dijagonala/2));
            opiskru.append(text + " (polumjer)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", _kutBeta));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 2 * (_strA + _strB)));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
        }
        if (ed.equals(strA) && ed2.equals(kutBeta)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = 180 - _kutBeta;
            double _strB = _strA/(Math.tan(Math.toRadians(_kutAlpha)/2));
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB, 2)));

            strA.append(text + " (stranica A)");
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _dijagonala));
            dijagonala.append(text + " (dijagonala)");
            opiskru.append(String.format(Locale.getDefault(), "%.2f", _dijagonala/2));
            opiskru.append(text + " (polumjer)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", _kutAlpha));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 2 * (_strA + _strB)));
            opseg.append(text + " (opseg)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
        }
        if (ed.equals(strA) && ed2.equals(povrsina)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strB = _povrsina/_strA;
            double _dijagonala = Math.sqrt((Math.pow(_strA,2) + Math.pow(_strB, 2)));

            strA.append(text + " (stranica A)");
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _dijagonala));
            dijagonala.append(text + " (dijagonala)");
            opiskru.append(String.format(Locale.getDefault(), "%.2f", _dijagonala/2));
            opiskru.append(text + " (polumjer)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_dijagonala) * 2)));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_dijagonala) * 2)));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 2 * (_strA + _strB)));
            opseg.append(text + " (opseg)");
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
            strA.append(text + " (stranica A)");
            opseg.append(text + " (opseg)");
            strB.append(String.format(Locale.getDefault(), "%.2f", _strB));
            strB.append(text + " (stranica B)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", _strA * _strB));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _dijagonala));
            dijagonala.append(text + " (dijagonala)");
            opiskru.append(String.format(Locale.getDefault(), "%.2f", _dijagonala/2));
            opiskru.append(text + " (polumjer)");
            kutAlpha.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strA/_dijagonala) * 2)));
            kutAlpha.append(getString(R.string.stupnjeviznak) + " (alpha)");
            kutBeta.append(String.format(Locale.getDefault(), "%.2f", Math.toDegrees(Math.asin(_strB/_dijagonala) * 2)));
            kutBeta.append(getString(R.string.stupnjeviznak) + " (beta)");
        }
        // vrati nazad originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, dijagonala, opiskru, kutAlpha, kutBeta, povrsina, opseg);
    }
}