package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import java.util.Locale;

public class KvadratActivity extends AppCompatActivity {
    // Deklaracija tekstnih objekata
    EditText strA, povrsina, opseg, dijagonala, polupis, polopis;
    // Deklaracija gumba
    Button btn;
    SimplifiedToast st = new SimplifiedToast();
    // zastavica za brisanje unosa
    int f = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kvadrat);
        // Inicijalizacija tekstnih objekata
        strA = findViewById(R.id.editTextNumber);
        povrsina = findViewById(R.id.editTextNumber2);
        opseg = findViewById(R.id.editTextNumber3);
        dijagonala = findViewById(R.id.editTextNumber4);
        polupis = findViewById(R.id.editTextNumber5);
        polopis = findViewById(R.id.editTextNumber6);
        // Inicijalizacija gumba
        btn = findViewById(R.id.button);
        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        btn.setOnClickListener(v -> {
            // provjera je li jedna vrijednost unesena
            if (checkEmpty(strA, povrsina, opseg, dijagonala, polupis, polopis)) {
                // ako nije prikaži poruku
                st.toastShort(this, getString(R.string.Unesite_jednu_vrijednost));
                return;
            }
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(strA, povrsina, opseg, dijagonala, polupis, polopis);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, strA, povrsina, opseg, dijagonala, polupis, polopis);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }
            if (!checkEmpty(strA) && checkEmpty(povrsina, opseg, dijagonala, polupis, polopis)) {
                izracunaj(strA, "cm");
            } else if (!checkEmpty(povrsina) && checkEmpty(strA, opseg, dijagonala, polupis, polopis)){
                izracunaj(povrsina, "cm");
            } else if (!checkEmpty(opseg) && checkEmpty(strA, povrsina, dijagonala, polupis, polopis)) {
                izracunaj(opseg, "cm");
            } else if (!checkEmpty(dijagonala) && checkEmpty(strA, povrsina, opseg, polupis, polopis)) {
                izracunaj(dijagonala, "cm");
            } else if (!checkEmpty(polupis) && checkEmpty(strA, povrsina, opseg, dijagonala, polopis)) {
                izracunaj(polupis, "cm");
            } else if (!checkEmpty(polopis) && checkEmpty(strA, povrsina, opseg, dijagonala, polupis)) {
                izracunaj(polopis, "cm");
            } else {
                st.toastShort(this, getString(R.string.Unesite_samo_jednu_vrijednost));
            }
            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, strA, povrsina, opseg, dijagonala, polupis, polopis);
        });
    }

    protected void appendToText(String text, double _strA, double _dijagonala, double _polupis, double _polopis, double _opseg, double _povrsina) {
        strA.append(String.format(Locale.getDefault(), "%.2f", _strA) + text + " " + getString(R.string.stranica_A));
        dijagonala.append(String.format(Locale.getDefault(), "%.2f", _dijagonala) + text + " " + getString(R.string.dijagonala));
        polupis.append(String.format(Locale.getDefault(), "%.2f", _polupis) + text + " " + getString(R.string.upisana_kruz));
        polopis.append(String.format(Locale.getDefault(), "%.2f", _polopis) + text + " " + getString(R.string.opisana_kruz));
        opseg.append(String.format(Locale.getDefault(), "%.2f", _opseg) + text + " " + getString(R.string.opseg));
        povrsina.append(String.format(Locale.getDefault(), "%.2f", _povrsina) + text + getString(R.string.nakvadratznak) + " " + getString(R.string.povrsina));
    }

    protected void izracunaj(EditText ed, String text) {
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, strA, povrsina, opseg, dijagonala , polupis, polopis);
        if (ed.equals(strA)) {
            // dohvati vrijednost stranice a i izračunaj ostale vrijednosti
            double _strA = Double.parseDouble(ed.getText().toString());
            double _dijagonala = _strA * Math.sqrt(2);
            double _polupis = _strA/2;
            double _polopis = (_strA * Math.sqrt(2))/2;
            double _opseg = 4 * _strA;
            double _povrsina = Math.pow(_strA, 2);
            resetTextOfFields(strA, dijagonala, polupis, polopis, opseg, povrsina);
            appendToText(text, _strA, _dijagonala, _polupis, _polopis, _opseg, _povrsina);
        }
        else if (ed.equals(povrsina)) {
            // račun za stranicu a iz površine
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = Math.sqrt(_strA);
            double _dijagonala = _strA * Math.sqrt(2);
            double _polupis = _strA/2;
            double _polopis = (_strA * Math.sqrt(2))/2;
            double _opseg = 4 * _strA;
            double _povrsina = Math.pow(_strA, 2);
            resetTextOfFields(strA, dijagonala, polupis, polopis, opseg, povrsina);
            appendToText(text, _strA, _dijagonala, _polupis, _polopis, _opseg, _povrsina);
        }
        else if (ed.equals(opseg)) {
            // račun za stranicu a iz opsega
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = _strA/4;
            double _dijagonala = _strA * Math.sqrt(2);
            double _polupis = _strA/2;
            double _polopis = (_strA * Math.sqrt(2))/2;
            double _opseg = 4 * _strA;
            double _povrsina = Math.pow(_strA, 2);
            resetTextOfFields(strA, dijagonala, polupis, polopis, opseg, povrsina);
            appendToText(text, _strA, _dijagonala, _polupis, _polopis, _opseg, _povrsina);
        }
        else if (ed.equals(dijagonala)) {
            // račun za stranicu a iz dijagonale
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = _strA/Math.sqrt(2);
            double _dijagonala = _strA * Math.sqrt(2);
            double _polupis = _strA/2;
            double _polopis = (_strA * Math.sqrt(2))/2;
            double _opseg = 4 * _strA;
            double _povrsina = Math.pow(_strA, 2);
            resetTextOfFields(strA, dijagonala, polupis, polopis, opseg, povrsina);
            appendToText(text, _strA, _dijagonala, _polupis, _polopis, _opseg, _povrsina);
        }
        else if (ed.equals(polupis)) {
            // račun za stranicu a iz upisane kružnice
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = _strA*2;
            double _dijagonala = _strA * Math.sqrt(2);
            double _polupis = _strA/2;
            double _polopis = (_strA * Math.sqrt(2))/2;
            double _opseg = 4 * _strA;
            double _povrsina = Math.pow(_strA, 2);
            resetTextOfFields(strA, dijagonala, polupis, polopis, opseg, povrsina);
            appendToText(text, _strA, _dijagonala, _polupis, _polopis, _opseg, _povrsina);
        }
        else if (ed.equals(polopis)) {
            // račun za stranicu a iz opisane kružnice
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = _strA*Math.sqrt(2);
            double _dijagonala = _strA * Math.sqrt(2);
            double _polupis = _strA/2;
            double _polopis = (_strA * Math.sqrt(2))/2;
            double _opseg = 4 * _strA;
            double _povrsina = Math.pow(_strA, 2);
            resetTextOfFields(strA, dijagonala, polupis, polopis, opseg, povrsina);
            appendToText(text, _strA, _dijagonala, _polupis, _polopis, _opseg, _povrsina);
        }
        // vrati nazad originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, povrsina, opseg, dijagonala , polupis, polopis);
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