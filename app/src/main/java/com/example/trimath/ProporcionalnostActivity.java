package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Locale;

public class ProporcionalnostActivity extends AppCompatActivity {
    RadioButton rdb1, rdb2;
    Button btn;
    EditText ed1, ed2, ed3, ed4;
    SimplifiedToast st = new SimplifiedToast();
    int f = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proporcionalnost);
        rdb1 = findViewById(R.id.radioButton);
        rdb2 = findViewById(R.id.radioButton2);
        btn = findViewById(R.id.button8);
        ed1 = findViewById(R.id.editTextNumber24);
        ed2 = findViewById(R.id.editTextNumber25);
        ed3 = findViewById(R.id.editTextNumber26);
        ed4 = findViewById(R.id.editTextNumber27);
        // LOCK radiobuttonse

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        btn.setOnClickListener(v -> {
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(ed1, ed2, ed3, ed4);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, ed1, ed2, ed3, ed4);
                // otključaj radio gumbe
                rdb1.setEnabled(true);
                rdb2.setEnabled(true);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }
            if (checkEmpty(ed1, ed2, ed3, ed4)) {
                st.toastShort(this, getString(R.string.Unesite_samo_tri_vrijednosti));
                return;
            }
            if (checkEmpty(ed1) && !checkEmpty(ed2, ed3, ed4)) {
                IzracunajProporcionalnost(0, Double.parseDouble(ed2.getText().toString()), Double.parseDouble(ed3.getText().toString()), Double.parseDouble(ed4.getText().toString()));
            } else if (checkEmpty(ed2) && !checkEmpty(ed1, ed3, ed4)) {
                IzracunajProporcionalnost(Double.parseDouble(ed1.getText().toString()), 0, Double.parseDouble(ed3.getText().toString()), Double.parseDouble(ed4.getText().toString()));
            } else if (checkEmpty(ed3) && !checkEmpty(ed2, ed1, ed4)) {
                IzracunajProporcionalnost(Double.parseDouble(ed1.getText().toString()), Double.parseDouble(ed2.getText().toString()), 0, Double.parseDouble(ed4.getText().toString()));
            } else if (checkEmpty(ed4) && !checkEmpty(ed2, ed3, ed1)) {
                IzracunajProporcionalnost(Double.parseDouble(ed1.getText().toString()), Double.parseDouble(ed2.getText().toString()), Double.parseDouble(ed3.getText().toString()), 0);
            } else {
                st.toastShort(this, getString(R.string.Unesite_tri_vrijednosti));
            }

            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, ed1, ed2, ed3, ed4);
            // zaključaj radio gumbe
            rdb1.setEnabled(false);
            rdb2.setEnabled(false);
        });

        rdb1.setOnClickListener(v -> {
            try {
                rdb2.setChecked(false);
            } catch (Exception ex)
            {
                st.toastShort(this, "ERR RAD");
            }
        });

        rdb2.setOnClickListener(v -> {
            try {
                rdb1.setChecked(false);
            } catch (Exception ex)
            {
                st.toastShort(this, "ERR RAD");
            }
        });
    }

    // 4 unosa u funckiju ali samo 3 unosa su validna
    // A/B = C/D
    protected void IzracunajProporcionalnost(double A, double B, double C, double D) {
        double[] brojevi = new double[4];
        // 0 - A, 1 C, 2 B, 3 D
        if (A == 0) {
            // A = (B*C)/D
            if (rdb1.isChecked()) {
                // izracunaj A i formatiraj odmah sve
                brojevi[0] = (B * C)/D;
            } else {
                // 2 * 3 / 1
                brojevi[0] = (B * D)/C;
            }
            brojevi[1] = B;
            brojevi[2] = C;
            brojevi[3] = D;
        } else if (B == 0) {
            // B = A*D/C
            if (rdb1.isChecked()) {
                // izracunaj B i formatiraj odmah sve
                brojevi[1] = (A * D)/C;
            } else {
                // 0 * 1 /3
                brojevi[1] = (A * C)/D;
            }
            brojevi[0] = A;
            brojevi[2] = C;
            brojevi[3] = D;
        } else if (C == 0) {
            // C = A*D/B
            if (rdb1.isChecked()) {
                // izracunaj C i formatiraj odmah sve
                brojevi[2] = (A * D)/B;
            } else {
                // 2 * 3 / 0
                brojevi[2] = (B * D)/A;
            }
            brojevi[0] = A;
            brojevi[1] = B;
            brojevi[3] = D;
        } else if (D == 0) {
            // D = B*C/A
            if (rdb1.isChecked()) {
                // izracunaj D i formatiraj odmah sve
                brojevi[3] = (B * C)/A;
            } else {
                // 0 * 1 / 2
                brojevi[3] = (A * C)/B;
            }
            brojevi[0] = A;
            brojevi[1] = B;
            brojevi[2] = C;
        }
        formatNumbers(brojevi, ed1, ed2, ed3, ed4);
    }
    // kak koristi?
    // uneses brojeve i formatiraju se automatski na %.2f
    // eto objasnjeno pa ne trebas citat Modriću :)
    protected void formatNumbers(double[] brojevi, EditText... ed) {
        // ako slucajno zaboravis jedan sam nece radit :P
        if (ed.length != 4) return;
        for (int i = 0; i < ed.length; i++) {
            ed[i].setText(String.format(Locale.getDefault(), "%.2f", brojevi[i]));
        }
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
}