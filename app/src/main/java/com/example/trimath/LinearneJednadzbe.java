package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

public class LinearneJednadzbe extends AppCompatActivity {
    TextView tv;
    Button btn;
    EditText ed1, ed2, ed3, ed4;
    boolean f = false;
    SimplifiedToast st = new SimplifiedToast();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linearne_jednadzbe);

        // pronalazimo widgete
        tv = findViewById(R.id.textView6);
        btn = findViewById(R.id.button9);
        ed1 = findViewById(R.id.editTextNumber31);
        ed2 = findViewById(R.id.editTextNumber32);
        ed3 = findViewById(R.id.editTextNumber33);
        ed4 = findViewById(R.id.editTextNumber34);

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        btn.setOnClickListener(v -> {
            // provjera je li jedna vrijednost unesena
            if (checkEmpty(ed1, ed2, ed3, ed4)) {
                // ako nije prikaži poruku izračunaj (1) * x = 0
                tv.setText(String.format(Locale.getDefault(), "%s = %.2f", "x", 0.0));
                return;
            }
            if (f) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(ed1, ed2, ed3, ed4);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, ed1, ed2, ed3, ed4);
                // promijeni vrijednost zastavice
                f = false;
                return;
            }



            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = true;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, ed1, ed2, ed3, ed4);
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
}