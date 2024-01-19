package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class JednakoStranicnActivity extends AppCompatActivity {
    EditText strA, povrsina, opseg, visina,upis,opis;
    Button btn;
    int f=0;
    SimplifiedToast st = new SimplifiedToast();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jednakostr);
        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        btn = findViewById(R.id.izracunaj);

        strA = findViewById(R.id.editTextNumber7);
        povrsina = findViewById(R.id.editTextNumber8);
        opseg = findViewById(R.id.editTextNumber9);
        visina = findViewById(R.id.editTextNumber10);
        opis = findViewById(R.id.editTextNumber11);
        upis = findViewById(R.id.editTextNumber12);


        btn.setOnClickListener(v -> {
            // provjera je li jedna vrijednost unesena
            if (checkEmpty(strA, povrsina, opseg, visina, opis, upis)) {
                // ako nije prikaži poruku
                st.toastShort(this, getString(R.string.Unesite_jednu_vrijednost));
                return;
            }
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(strA, povrsina, opseg, visina, opis, upis);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, strA, povrsina, opseg, visina, opis, upis);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }

            if (!checkEmpty(strA) && checkEmpty(povrsina, opseg, visina, opis, upis)) {
                izracunaj(strA, "cm");
            } else if (!checkEmpty(povrsina) && checkEmpty(strA, opseg, visina, opis, upis)){
                izracunaj(povrsina, "cm");
            } else if (!checkEmpty(opseg) && checkEmpty(strA, povrsina, visina, opis, upis)) {
                izracunaj(opseg, "cm");
            } else if (!checkEmpty(visina) && checkEmpty(strA, povrsina, opseg, opis, upis)) {
                izracunaj(visina, "cm");
            } else if (!checkEmpty(upis) && checkEmpty(strA, opseg, povrsina, visina, opis)) {
                izracunaj(upis, "cm");
            } else if (!checkEmpty(opis) && checkEmpty(strA, opseg, povrsina, visina, upis)) {
                izracunaj(opis, "cm");
            } else {
                st.toastShort(this, getString(R.string.Unesite_samo_jednu_vrijednost));
            }

            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, strA, povrsina, opseg, visina, opis, upis);
        });
    }

    protected void appendToText(String text, double _strA, double _povrsina, double _opseg, double _visina, double _upis, double _opis) {
        strA.append(String.format(Locale.getDefault(), "%.2f", _strA) + text + " " + getString(R.string.stranica_A));
        opseg.append(String.format(Locale.getDefault(), "%.2f", _opseg) + text + " " + getString(R.string.opseg));
        povrsina.append(String.format(Locale.getDefault(), "%.2f", _povrsina) + text + getString(R.string.nakvadratznak) + " " + getString(R.string.povrsina));
        visina.append(String.format(Locale.getDefault(), "%.2f", _visina) + text + " " + getString(R.string.dijagonala));
        upis.append(String.format(Locale.getDefault(), "%.2f", _upis) + text + " " + getString(R.string.upisana_kruz));
        opis.append(String.format(Locale.getDefault(), "%.2f", _opis) + text + " " + getString(R.string.opisana_kruz));
    }

    protected void izracunaj(EditText ed, String text) {

        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, strA, povrsina, opseg, visina , upis, opis);
        if (ed.equals(strA)) {
            // dohvati vrijednost stranice a i izračunaj ostale vrijednosti
            double _strA = Double.parseDouble(ed.getText().toString());
            double _povrsina = Math.sqrt(3)/4 * Math.pow(_strA, 2);
            double _opseg = 3 * _strA;
            double _visina = Math.sqrt(3)/2 * _strA;
            double _upis = Math.sqrt(3)/6 * _strA;
            double _opis = Math.sqrt(3)/3 * _strA;
            resetTextOfFields(strA, opseg, povrsina, visina, upis, opis);
            appendToText(text, _strA, _povrsina, _opseg, _visina, _upis, _opis);
        }
        else if (ed.equals(opseg)) {
            // dohvati vrijednost stranice a i izračunaj ostale vrijednosti
            double _opseg = Double.parseDouble(ed.getText().toString());
            double _strA=_opseg/3;
            double _povrsina = Math.sqrt(3)/4 * Math.pow(_strA, 2);
            double _visina = Math.sqrt(3)/2 * _strA;
            double _upis = Math.sqrt(3)/6 * _strA;
            double _opis = Math.sqrt(3)/3 * _strA;
            resetTextOfFields(strA, opseg, povrsina, visina, upis, opis);
            appendToText(text, _strA, _povrsina, _opseg, _visina, _upis, _opis);
        }
        else if (ed.equals(povrsina)) {
            // dohvati vrijednost stranice a i izračunaj ostale vrijednosti
            double _povrsina = Double.parseDouble(ed.getText().toString());
            double _strA=Math.sqrt(4*_povrsina/Math.sqrt(3));
            double _opseg = 3 * _strA;
            double _visina = Math.sqrt(3)/2 * _strA;
            double _upis = Math.sqrt(3)/6 * _strA;
            double _opis = Math.sqrt(3)/3 * _strA;
            resetTextOfFields(strA, opseg, povrsina, visina, upis, opis);
            appendToText(text, _strA, _povrsina, _opseg, _visina, _upis, _opis);
        }
        else if (ed.equals(visina)) {
            // dohvati vrijednost stranice a i izračunaj ostale vrijednosti
            double _visina = Double.parseDouble(ed.getText().toString());
            double _strA=2*_visina/Math.sqrt(3);
            double _povrsina = Math.sqrt(3)/4 * Math.pow(_strA, 2);
            double _opseg = 3 * _strA;
            double _upis = Math.sqrt(3)/6 * _strA;
            double _opis = Math.sqrt(3)/3 * _strA;
            resetTextOfFields(strA, opseg, povrsina, visina, upis, opis);
            appendToText(text, _strA, _povrsina, _opseg, _visina, _upis, _opis);
        }
        else if (ed.equals(upis)) {
            // dohvati vrijednost stranice a i izračunaj ostale vrijednosti
            double _upis = Double.parseDouble(ed.getText().toString());
            double _strA=6*_upis/Math.sqrt(3);
            double _povrsina = Math.sqrt(3)/4 * Math.pow(_strA, 2);
            double _opseg = 3 * _strA;
            double _visina = Math.sqrt(3)/2 * _strA;
            double _opis = Math.sqrt(3)/3 * _strA;
            resetTextOfFields(strA, opseg, povrsina, visina, upis, opis);
            appendToText(text, _strA, _povrsina, _opseg, _visina, _upis, _opis);
        }
        else if (ed.equals(opis)) {
            // dohvati vrijednost stranice a i izračunaj ostale vrijednosti
            double _opis = Double.parseDouble(ed.getText().toString());
            double _strA=3*_opis/Math.sqrt(3);
            double _povrsina = Math.sqrt(3)/4 * Math.pow(_strA, 2);
            double _opseg = 3 * _strA;
            double _visina = Math.sqrt(3)/2 * _strA;
            double _upis = Math.sqrt(3)/6 * _strA;
            resetTextOfFields(strA, opseg, povrsina, visina, upis, opis);
            appendToText(text, _strA, _povrsina, _opseg, _visina, _upis, _opis);
        }
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, povrsina, opseg, visina , upis, opis);
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
