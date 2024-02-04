package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class ValjakActivity extends AppCompatActivity {
    boolean f = false;
    SimplifiedToast st = new SimplifiedToast();
    Button btn;
    EditText visina, polumjer, promjer, opseg, obujam, oplosje, baze, pobocja;
    String[] vrijednosti = new String[8];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valjak);
        vrijednosti[0] = getString(R.string.visina);
        vrijednosti[1] = getString(R.string.polumjer);
        vrijednosti[2] = getString(R.string.promjer);
        vrijednosti[3] = getString(R.string.opseg);
        vrijednosti[4] = getString(R.string.obujam);
        vrijednosti[5] = getString(R.string.oplosje);
        vrijednosti[6] = getString(R.string.povrsina_baze);
        vrijednosti[7] = getString(R.string.povrsina_pobocja);

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        btn = findViewById(R.id.button);

        visina = findViewById(R.id.editTextNumber47);
        polumjer = findViewById(R.id.editTextNumber48);
        promjer = findViewById(R.id.editTextNumber49);
        opseg = findViewById(R.id.editTextNumber50);
        obujam = findViewById(R.id.editTextNumber51);
        oplosje = findViewById(R.id.editTextNumber52);
        baze = findViewById(R.id.editTextNumber53);
        pobocja = findViewById(R.id.editTextNumber54);

        btn.setOnClickListener(v -> {
            if (checkEmpty(visina, polumjer, promjer, opseg, obujam, oplosje, baze, pobocja)) {
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
                return;
            }
            if (f) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(visina, polumjer, promjer, opseg, obujam, oplosje, baze, pobocja);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, visina, polumjer, promjer, opseg, obujam, oplosje, baze, pobocja);
                // promijeni vrijednost zastavice
                f = false;
                return;
            }

            if (!checkEmpty(visina, polumjer) && checkEmpty(promjer, opseg, obujam, oplosje, baze, pobocja)) {
                racun(visina, polumjer);
            } else {
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
            }

            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = true;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, visina, polumjer, promjer, opseg, obujam, oplosje, baze, pobocja);
        });
    }

    protected void racun(EditText ed1, EditText ed2) {
        double[] brojevi = new double[8];
        /*
        0 visina
        1 polumjer
        2 promjer
        3 opseg
        4 obujam
        5 oplosje
        6 baza
        7 pobocja
        */
        if (ed1.equals(visina) && ed2.equals(polumjer)) {
            brojevi[0] = Double.parseDouble(visina.getText().toString());
            brojevi[1] = Double.parseDouble(polumjer.getText().toString());
            brojevi[2] = 2 * brojevi[1];
            brojevi[3] = 2 * Math.PI * brojevi[1];
            brojevi[4] = Math.PI * Math.pow(brojevi[1], 2) * brojevi[0];
            brojevi[6] = Math.PI * Math.pow(brojevi[1], 2);
            brojevi[7] = 2 * (Math.PI * brojevi[1] * brojevi[0]);
            brojevi[5] = 2 * brojevi[6] + brojevi[7];
        } else if (ed1.equals(visina) && ed2.equals(promjer)) {
            brojevi[0] = Double.parseDouble(visina.getText().toString());
            brojevi[2] = Double.parseDouble(promjer.getText().toString());
            brojevi[1] = brojevi[2]/2;
            brojevi[3] = 2 * Math.PI * brojevi[1];
            brojevi[4] = Math.PI * Math.pow(brojevi[1], 2) * brojevi[0];
            brojevi[6] = Math.PI * Math.pow(brojevi[1], 2);
            brojevi[7] = 2 * (Math.PI * brojevi[1] * brojevi[0]);
            brojevi[5] = 2 * brojevi[6] + brojevi[7];
        } else if (ed1.equals(visina) && ed2.equals(opseg)) {
            brojevi[0] = Double.parseDouble(visina.getText().toString());
            brojevi[3] = Double.parseDouble(opseg.getText().toString());
        }
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, visina, polumjer, promjer, opseg, obujam, oplosje, baze, pobocja);
        resetTextOfFields(visina, polumjer, promjer, opseg, obujam, oplosje, baze, pobocja);
        appendToText(brojevi, visina, polumjer, promjer, opseg, obujam, oplosje, baze, pobocja);
        // vrati nazad na originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, visina, polumjer, promjer, opseg, obujam, oplosje, baze, pobocja);
    }

    protected void appendToText(double[] brojevi, EditText... ed) {
        for (int i = 0; i < ed.length; i++) {
            if (i == 4) {
                ed[i].append(String.format(Locale.getDefault(), "%.2f", brojevi[i]) + getString(R.string.centimetar) + getString(R.string.natrecu) + " " + vrijednosti[i]);
            } else if (i > 4) {
                ed[i].append(String.format(Locale.getDefault(), "%.2f", brojevi[i]) + getString(R.string.centimetar) + getString(R.string.nakvadratznak) + " " + vrijednosti[i]);
            } else {
                ed[i].append(String.format(Locale.getDefault(), "%.2f", brojevi[i]) + getString(R.string.centimetar) + " " + vrijednosti[i]);
            }
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

    protected void changeInputType(int inputType, EditText... ed) {
        for (EditText editText : ed) {
            editText.setInputType(inputType);
        }
    }
}