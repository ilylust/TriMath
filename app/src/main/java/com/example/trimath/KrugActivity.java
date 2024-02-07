package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class KrugActivity extends AppCompatActivity {
    // Deklaracija tekstnih objekata
    EditText polumjer, promjer, povrsina, opseg;
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
        setContentView(R.layout.activity_krug);
        // Inicijalizacija tekstnih objekata
        polumjer = findViewById(R.id.editTextNumber7);
        promjer = findViewById(R.id.editTextNumber8);
        povrsina = findViewById(R.id.editTextNumber9);
        opseg = findViewById(R.id.editTextNumber10);
        // Inicijalizacija gumba
        btn = findViewById(R.id.button2);
        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        btn.setOnClickListener(v -> {
            if (checkEmpty(polumjer, promjer, povrsina, opseg)) {
                st.toastShort(this, getString(R.string.Unesite_jednu_vrijednost));
                return;
            }
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(polumjer, promjer, povrsina, opseg);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, polumjer, promjer, povrsina, opseg);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }
            if (!checkEmpty(polumjer) && checkEmpty(promjer, povrsina, opseg)) {
                izracunaj(polumjer, "cm");
            } else if (!checkEmpty(promjer) && checkEmpty(polumjer, povrsina, opseg)){
                izracunaj(promjer, "cm");
            } else if (!checkEmpty(povrsina) && checkEmpty(promjer, polumjer, opseg)) {
                izracunaj(povrsina, "cm");
            } else if (!checkEmpty(opseg) && checkEmpty(promjer, povrsina, polumjer)) {
                izracunaj(opseg, "cm");
            } else {
                st.toastShort(this, getString(R.string.Unesite_samo_jednu_vrijednost));
            }
            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, polumjer, promjer, povrsina, opseg);
        });
    }

    protected void appendToText(String text, double _polumjer, double _promjer, double _opseg, double _povrsina) {
        polumjer.append(String.format(Locale.getDefault(), "%.4f", _polumjer) + text + " " + getString(R.string.polumjer));
        promjer.append(String.format(Locale.getDefault(), "%.4f", _promjer) + text + " " + getString(R.string.promjer));
        opseg.append(String.format(Locale.getDefault(), "%.4f", _opseg) + text + " " + getString(R.string.opseg));
        povrsina.append(String.format(Locale.getDefault(), "%.4f", _povrsina) + text + getString(R.string.nakvadratznak) + " " + getString(R.string.povrsina));
    }

    protected void izracunaj(EditText ed, String text) {
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, polumjer, promjer, povrsina, opseg);
        if (ed.equals(polumjer)) {
            // dohvati vrijednost polumjera i izračunaj ostale vrijednosti
            double _polumjer = Double.parseDouble(ed.getText().toString());
            double _povrsina = Math.PI * Math.pow(_polumjer, 2);
            double _opseg = 2 * Math.PI * _polumjer;
            double _promjer = 2 * _polumjer;
            resetTextOfFields(polumjer, promjer, opseg, povrsina);
            appendToText(text, _polumjer, _promjer, _opseg, _povrsina);
        }
        else if (ed.equals(promjer)) {
            // izracunaj vrijednost polumjera i izračunaj ostale vrijednosti
            double _promjer = Double.parseDouble(ed.getText().toString());
            double _polumjer = _promjer/2;
            double _povrsina = Math.PI * Math.pow(_polumjer, 2);
            double _opseg = 2 * Math.PI * _polumjer;
            resetTextOfFields(polumjer, promjer, opseg, povrsina);
            appendToText(text, _polumjer, _promjer, _opseg, _povrsina);
        }
        else if (ed.equals(povrsina)) {
            // izracunaj vrijednost polumjera i izračunaj ostale vrijednosti
            double _povrsina = Double.parseDouble(ed.getText().toString());
            double _polumjer = Math.sqrt(_povrsina/Math.PI);
            double _opseg = 2 * Math.PI * _polumjer;
            double _promjer = 2 * _polumjer;
            resetTextOfFields(polumjer, promjer, opseg, povrsina);
            appendToText(text, _polumjer, _promjer, _opseg, _povrsina);
        }
        else if (ed.equals(opseg)) {
            // izracunaj vrijednost polumjera i izračunaj ostale vrijednosti
            double _opseg = Double.parseDouble(ed.getText().toString());
            double _polumjer = _opseg/(2 * Math.PI);
            double _povrsina = Math.PI * Math.pow(_polumjer, 2);
            double _promjer = 2 * _polumjer;
            resetTextOfFields(polumjer, promjer, opseg, povrsina);
            appendToText(text, _polumjer, _promjer, _opseg, _povrsina);
        }
        // vrati nazad originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, polumjer, promjer, povrsina, opseg);
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