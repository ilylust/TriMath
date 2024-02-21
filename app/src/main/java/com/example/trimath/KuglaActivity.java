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

public class KuglaActivity extends AppCompatActivity {
    Button btn, btninf;
    EditText ed1, ed2, ed3, ed4, ed5;
    SimplifiedToast st = new SimplifiedToast();
    boolean f = false;
    String[] vrijednosti = new String[5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kugla);

        // prikaz informacija..
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(KuglaActivity.this);
        dialog1.setTitle("Informacije o aplikaciji")
                .setMessage("Autori: Marino Tadić, Matija Modrić\n\nOva aplikacija je namijenjena učenicima i profesorima za lakše provjeravanje matematičih rezultata.\n\nAplikaciju koristite klikom na gumb za željeni kalkulator te unosite vrijednosti i stisnete gumb za izračun.")
                .setCancelable(true)
                .setPositiveButton("Zatvori", (dialog, id) -> dialog.cancel());
        AlertDialog alr1 = dialog1.create();

        vrijednosti[0] = getString(R.string.polumjer);
        vrijednosti[1] = getString(R.string.promjer);
        vrijednosti[2] = getString(R.string.opseg);
        vrijednosti[3] = getString(R.string.obujam);
        vrijednosti[4] = getString(R.string.oplosje);


        btn = findViewById(R.id.button10);
        ed1 = findViewById(R.id.editTextNumber42);
        ed2 = findViewById(R.id.editTextNumber43);
        ed3 = findViewById(R.id.editTextNumber44);
        ed4 = findViewById(R.id.editTextNumber45);
        ed5 = findViewById(R.id.editTextNumber46);

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        (btninf = findViewById(R.id.button11)).setOnClickListener(v -> {
            try {
                alr1.show();
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

        btn.setOnClickListener(v -> {
            if (checkEmpty(ed1, ed2, ed3, ed4, ed5)) {
                st.toastShort(this, getString(R.string.Unesite_jednu_vrijednost));
                return;
            }
            if (f) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(ed1, ed2, ed3, ed4, ed5);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, ed1, ed2, ed3, ed4, ed5);
                // promijeni vrijednost zastavice
                f = false;
                return;
            }

            if (!checkEmpty(ed1) && checkEmpty(ed2, ed3, ed4, ed5)) {
                racun(Double.parseDouble(ed1.getText().toString()), 1);
            } else if (!checkEmpty(ed2) && checkEmpty(ed1, ed3, ed4, ed5)) {
                racun(Double.parseDouble(ed2.getText().toString()), 2);
            } else if (!checkEmpty(ed3) && checkEmpty(ed2, ed1, ed4, ed5)) {
                racun(Double.parseDouble(ed3.getText().toString()), 3);
            } else if (!checkEmpty(ed4) && checkEmpty(ed2, ed3, ed1, ed5)) {
                racun(Double.parseDouble(ed4.getText().toString()), 4);
            } else if (!checkEmpty(ed5) && checkEmpty(ed2, ed3, ed4, ed1)) {
                racun(Double.parseDouble(ed5.getText().toString()), 5);
            } else {
                st.toastShort(this, getString(R.string.Unesite_jednu_vrijednost));
            }

            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = true;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, ed1, ed2, ed3, ed4, ed5);
        });
    }

    protected void racun(double broj, int latax) {
        double[] brojevi = new double[5];
        switch (latax) {
            case 1:
                brojevi[0] = broj;
                brojevi[1] = 2 * brojevi[0];
                brojevi[2] = 2 * Math.PI * brojevi[0];
                brojevi[3] = (4.0/3) * Math.PI * Math.pow(brojevi[0], 3);
                brojevi[4] = 4 * Math.PI * Math.pow(brojevi[0], 2);
                break;
            case 2:
                brojevi[1] = broj;
                brojevi[0] = brojevi[1]/2;
                brojevi[2] = 2 * Math.PI * brojevi[0];
                brojevi[3] = (4.0/3) * Math.PI * Math.pow(brojevi[0], 3);
                brojevi[4] = 4 * Math.PI * Math.pow(brojevi[0], 2);
                break;
            case 3:
                brojevi[2] = broj;
                brojevi[0] = brojevi[2]/(2 * Math.PI);
                brojevi[1] = 2 * brojevi[0];
                brojevi[3] = (4.0/3) * Math.PI * Math.pow(brojevi[0], 3);
                brojevi[4] = 4 * Math.PI * Math.pow(brojevi[0], 2);
                break;
            case 4:
                // https://stackoverflow.com/a/21141518
                // std::pow(value, 1.0/root);
                brojevi[3] = broj;
                double _t = (3 * brojevi[3])/(4 * Math.PI);
                brojevi[0] = Math.pow(_t, 1.0/3);
                brojevi[1] = 2 * brojevi[0];
                brojevi[2] = 2 * Math.PI * brojevi[0];
                brojevi[4] = 4 * Math.PI * Math.pow(brojevi[0], 2);
                break;
            case 5:
                brojevi[4] = broj;
                brojevi[0] = Math.sqrt(brojevi[4]/(4 * Math.PI));
                brojevi[1] = 2 * brojevi[0];
                brojevi[2] = 2 * Math.PI * brojevi[0];
                brojevi[3] = (4.0/3) * Math.PI * Math.pow(brojevi[0], 3);
                break;
        }
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, ed1, ed2, ed3, ed4, ed5);
        resetTextOfFields(ed1, ed2, ed3, ed4, ed5);
        appendToText(brojevi, ed1, ed2, ed3, ed4, ed5);
        // vrati nazad na originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, ed1, ed2, ed3, ed4, ed5);
    }

    protected void appendToText(double[] brojevi, EditText... ed) {
        for (int i = 0; i < ed.length; i++) {
            if (i == 3) {
                ed[i].append(String.format(Locale.getDefault(), "%.4f", brojevi[i]) + getString(R.string.centimetar) + getString(R.string.natrecu) + " " + vrijednosti[i]);
            } else if (i == 4) {
                ed[i].append(String.format(Locale.getDefault(), "%.4f", brojevi[i]) + getString(R.string.centimetar) + getString(R.string.nakvadratznak) + " " + vrijednosti[i]);
            } else {
                ed[i].append(String.format(Locale.getDefault(), "%.4f", brojevi[i]) + getString(R.string.centimetar) + " " + vrijednosti[i]);
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