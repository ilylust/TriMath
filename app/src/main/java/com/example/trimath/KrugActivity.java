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
            if (!polumjer.getText().toString().isEmpty() && checkEmpty(promjer, povrsina, opseg)) {
                izracunaj(polumjer, "cm");
            } else if (!promjer.getText().toString().isEmpty() && checkEmpty(polumjer, povrsina, opseg)){
                izracunaj(promjer, "cm");
            } else if (!povrsina.getText().toString().isEmpty() && checkEmpty(promjer, polumjer, opseg)) {
                izracunaj(povrsina, "cm");
            } else if (!opseg.getText().toString().isEmpty() && checkEmpty(promjer, povrsina, polumjer)) {
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

    protected void izracunaj(EditText ed, String text) {
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, polumjer, promjer, povrsina, opseg);
        if (ed.equals(polumjer)) {
            // dohvati vrijednost polumjera i izračunaj ostale vrijednosti
            double _polumjer = Double.parseDouble(ed.getText().toString());

            polumjer.append(text + " (polumjer)");
            promjer.append(String.format(Locale.getDefault(), "%.2f", 2 * _polumjer) + text + " (promjer)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", Math.PI * Math.pow(_polumjer,2)) + text + getString(R.string.nakvadratznak) + " (površina)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 2 * Math.PI * _polumjer) + text + " (opseg)");
        }
        if (ed.equals(promjer)) {
            // izracunaj vrijednost polumjera i izračunaj ostale vrijednosti
            double _polumjer = Double.parseDouble(ed.getText().toString());

            polumjer.append(String.format(Locale.getDefault(), "%.2f", _polumjer) + text + " (polumjer)");
            promjer.append(text + " (promjer)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", Math.PI * Math.pow(_polumjer,2)) + text + getString(R.string.nakvadratznak) + " (površina)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 2 * Math.PI * _polumjer) + text + " (opseg)");
        }
        if (ed.equals(povrsina)) {
            // izracunaj vrijednost polumjera i izračunaj ostale vrijednosti
            double _polumjer = Double.parseDouble(ed.getText().toString());

            polumjer.append(String.format(Locale.getDefault(), "%.2f", _polumjer) + text + " (polumjer)");
            promjer.append(String.format(Locale.getDefault(), "%.2f", 2 * _polumjer) + text + " (promjer)");
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 2 * Math.PI * _polumjer) + text + " (opseg)");
        }
        if (ed.equals(opseg)) {
            // izracunaj vrijednost polumjera i izračunaj ostale vrijednosti
            double _polumjer = Double.parseDouble(ed.getText().toString());
            // pi * r = O/2
            _polumjer = _polumjer/2;
            // r
            _polumjer = _polumjer/Math.PI;

            polumjer.append(String.format(Locale.getDefault(), "%.2f", _polumjer) + text + " (polumjer)");
            promjer.append(String.format(Locale.getDefault(), "%.2f", 2 * _polumjer) + text + " (promjer)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", Math.PI * Math.pow(_polumjer,2)) + text + getString(R.string.nakvadratznak) + " (površina)");
            opseg.append(text + " (opseg)");
        }
        // vrati nazad originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, polumjer, promjer, povrsina, opseg);
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