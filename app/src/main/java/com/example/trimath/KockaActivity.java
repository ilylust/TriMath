package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Button;

import java.util.Locale;

public class KockaActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7;
    Button btn, btninf;
    boolean f = false;
    SimplifiedToast st = new SimplifiedToast();
    String[] vrijednosti = new String[7];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kocka);

        // prikaz informacija..
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(KockaActivity.this);
        dialog1.setTitle("Informacije o aplikaciji")
                .setMessage("Autori: Marino Tadić, Matija Modrić\n\nOva aplikacija je namijenjena učenicima i profesorima za lakše provjeravanje matematičih rezultata.\n\nAplikaciju koristite klikom na gumb za željeni kalkulator te unosite vrijednosti i stisnete gumb za izračun.\nMentor: Željko Turkalj")
                .setCancelable(true)
                .setPositiveButton("Zatvori", (dialog, id) -> dialog.cancel());
        AlertDialog alr1 = dialog1.create();
        // koristi se kasnije
        vrijednosti[0] = getString(R.string.brid);
        vrijednosti[1] = getString(R.string.prostorna_dijagonala);
        vrijednosti[2] = getString(R.string.plosna_dijagonala);
        vrijednosti[3] = getString(R.string.obujam);
        vrijednosti[4] = getString(R.string.oplosje);
        vrijednosti[5] = getString(R.string.povrsina_baze);
        vrijednosti[6] = getString(R.string.povrsina_pobocja);

        btn = findViewById(R.id.button7);

        ed1 = findViewById(R.id.editTextNumber35);
        ed2 = findViewById(R.id.editTextNumber36);
        ed3 = findViewById(R.id.editTextNumber37);
        ed4 = findViewById(R.id.editTextNumber38);
        ed5 = findViewById(R.id.editTextNumber39);
        ed6 = findViewById(R.id.editTextNumber40);
        ed7 = findViewById(R.id.editTextNumber41);

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
            if (checkEmpty(ed1, ed2, ed3, ed4, ed5, ed6, ed7)) {
                st.toastShort(this, getString(R.string.Unesite_jednu_vrijednost));
                return;
            }
            if (f) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(ed1, ed2, ed3, ed4, ed5, ed6, ed7);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, ed1, ed2, ed3, ed4, ed5, ed6, ed7);
                // promijeni vrijednost zastavice
                f = false;
                return;
            }

            if (!checkEmpty(ed1) && checkEmpty(ed2, ed3, ed4, ed5, ed6, ed7)) {
                racun(Double.parseDouble(ed1.getText().toString()), 1);
            } else if (!checkEmpty(ed2) && checkEmpty(ed1, ed3, ed4, ed5, ed6, ed7)) {
                racun(Double.parseDouble(ed2.getText().toString()), 2);
            } else if (!checkEmpty(ed3) && checkEmpty(ed2, ed1, ed4, ed5, ed6, ed7)) {
                racun(Double.parseDouble(ed3.getText().toString()), 3);
            } else if (!checkEmpty(ed4) && checkEmpty(ed2, ed3, ed1, ed5, ed6, ed7)) {
                racun(Double.parseDouble(ed4.getText().toString()), 4);
            } else if (!checkEmpty(ed5) && checkEmpty(ed2, ed3, ed4, ed1, ed6, ed7)) {
                racun(Double.parseDouble(ed5.getText().toString()), 5);
            } else if (!checkEmpty(ed6) && checkEmpty(ed2, ed3, ed4, ed5, ed1, ed7)) {
                racun(Double.parseDouble(ed6.getText().toString()), 6);
            } else if (!checkEmpty(ed7) && checkEmpty(ed2, ed3, ed4, ed5, ed6, ed1)) {
                racun(Double.parseDouble(ed7.getText().toString()), 7);
            } else {
                st.toastShort(this, getString(R.string.Unesite_jednu_vrijednost));
            }

            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = true;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, ed1, ed2, ed3, ed4, ed5, ed6, ed7);
        });
    }

    protected void racun(double broj, int latax) {
        double[] brojevi = new double[7];
        switch (latax) {
            case 1:
                brojevi[0] = broj;
                brojevi[1] = brojevi[0] * Math.sqrt(3);
                brojevi[2] = brojevi[0] * Math.sqrt(2);
                brojevi[3] = Math.pow(brojevi[0], 3);
                brojevi[4] = 6 * Math.pow(brojevi[0], 2);
                brojevi[5] = Math.pow(brojevi[0], 2);
                brojevi[6] = 4 * Math.pow(brojevi[0], 2);
                break;
            case 2:
                brojevi[1] = broj;
                brojevi[0] = brojevi[1]/Math.sqrt(3);
                brojevi[2] = brojevi[0] * Math.sqrt(2);
                brojevi[3] = Math.pow(brojevi[0], 3);
                brojevi[4] = 6 * Math.pow(brojevi[0], 2);
                brojevi[5] = Math.pow(brojevi[0], 2);
                brojevi[6] = 4 * Math.pow(brojevi[0], 2);
                break;
            case 3:
                brojevi[2] = broj;
                brojevi[0] = brojevi[2]/Math.sqrt(2);
                brojevi[1] = brojevi[0] * Math.sqrt(3);
                brojevi[3] = Math.pow(brojevi[0], 3);
                brojevi[4] = 6 * Math.pow(brojevi[0], 2);
                brojevi[5] = Math.pow(brojevi[0], 2);
                brojevi[6] = 4 * Math.pow(brojevi[0], 2);
                break;
            case 4:
                // https://stackoverflow.com/a/21141518
                // std::pow(value, 1.0/root);
                brojevi[3] = broj;
                brojevi[0] = Math.pow(brojevi[3], 1.0/3);
                brojevi[1] = brojevi[0] * Math.sqrt(3);
                brojevi[2] = brojevi[0] * Math.sqrt(2);
                brojevi[4] = 6 * Math.pow(brojevi[0], 2);
                brojevi[5] = Math.pow(brojevi[0], 2);
                brojevi[6] = 4 * Math.pow(brojevi[0], 2);
                break;
            case 5:
                brojevi[4] = broj;
                brojevi[0] = Math.sqrt(brojevi[4]/6);
                brojevi[1] = brojevi[0] * Math.sqrt(3);
                brojevi[3] = Math.pow(brojevi[0], 3);
                brojevi[2] = brojevi[0] * Math.sqrt(2);
                brojevi[5] = Math.pow(brojevi[0], 2);
                brojevi[6] = 4 * Math.pow(brojevi[0], 2);
                break;
            case 6:
                brojevi[5] = broj;
                brojevi[0] = Math.sqrt(brojevi[5]);
                brojevi[1] = brojevi[0] * Math.sqrt(3);
                brojevi[2] = brojevi[0] * Math.sqrt(2);
                brojevi[3] = Math.pow(brojevi[0], 3);
                brojevi[4] = 6 * Math.pow(brojevi[0], 2);
                brojevi[6] = 4 * Math.pow(brojevi[0], 2);
                break;
            case 7:
                brojevi[6] = broj;
                brojevi[0] = Math.sqrt(brojevi[6])/2;
                brojevi[1] = brojevi[0] * Math.sqrt(3);
                brojevi[2] = brojevi[0] * Math.sqrt(2);
                brojevi[3] = Math.pow(brojevi[0], 3);
                brojevi[4] = 6 * Math.pow(brojevi[0], 2);
                brojevi[5] = Math.pow(brojevi[0], 2);
                break;
        }
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, ed1, ed2, ed3, ed4, ed5, ed6, ed7);
        resetTextOfFields(ed1, ed2, ed3, ed4, ed5, ed6, ed7);
        appendToText(brojevi, ed1, ed2, ed3, ed4, ed5, ed6, ed7);
        // vrati nazad na originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, ed1, ed2, ed3, ed4, ed5, ed6, ed7);
    }

    protected void appendToText(double[] brojevi, EditText... ed) {
        for (int i = 0; i < ed.length; i++) {
            if (i == 3) {
                ed[i].append(String.format(Locale.getDefault(), "%.4f", brojevi[i]) + getString(R.string.centimetar) + getString(R.string.natrecu) + " " + vrijednosti[i]);
            } else if (i > 3) {
                ed[i].append(String.format(Locale.getDefault(), "%.4f", brojevi[i]) + getString(R.string.centimetar) + getString(R.string.nakvadratznak) + " " + vrijednosti[i]);
            }else {
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