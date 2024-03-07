package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Locale;

public class PostotakActivity extends AppCompatActivity {
    Button btn, btninf;
    RadioButton rdb1, rdb2;
    EditText ed1, ed2, ed3;
    SimplifiedToast st = new SimplifiedToast();
    int f = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postotak);

        // Nađi widgete
        btn = findViewById(R.id.button);
        rdb1 = findViewById(R.id.radioButton3);
        rdb2 = findViewById(R.id.radioButton4);
        ed1 = findViewById(R.id.editTextNumber28);
        ed2 = findViewById(R.id.editTextNumber29);
        ed3 = findViewById(R.id.editTextNumber30);

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        // prikaz informacija..
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(PostotakActivity.this);
        dialog1.setTitle("Informacije o aplikaciji")
                .setMessage("Autori: Marino Tadić, Matija Modrić\n\nOva aplikacija je namijenjena učenicima i profesorima za lakše provjeravanje matematičih rezultata.\n\nAplikaciju koristite klikom na gumb za željeni kalkulator te unosite vrijednosti i stisnete gumb za izračun.\nMentor: Željko Turkalj")
                .setCancelable(true)
                .setPositiveButton("Zatvori", (dialog, id) -> dialog.cancel());
        AlertDialog alr1 = dialog1.create();

        (btninf = findViewById(R.id.button11)).setOnClickListener(v -> {
            try {
                alr1.show();
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

        rdb1.setOnClickListener(v -> {
            try {
                rdb2.setChecked(false);
                ed1.setHint(getString(R.string.nepoznanica_postotak));
                ed3.setHint(getString(R.string.slovo_Z));
            } catch (Exception ex)
            {
                st.toastShort(this, "ERR RAD");
            }
        });

        rdb2.setOnClickListener(v -> {
            try {
                rdb1.setChecked(false);
                ed1.setHint(getString(R.string.broj_X));
                ed3.setHint(getString(R.string.slovo_Z_postotak));
            } catch (Exception ex)
            {
                st.toastShort(this, "ERR RAD");
            }
        });

        btn.setOnClickListener(v -> {
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(ed1, ed2, ed3);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, ed1, ed2);
                // otključaj radio gumbe
                rdb1.setEnabled(true);
                rdb2.setEnabled(true);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }
            if (checkEmpty(ed1, ed2)) {
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
                return;
            } else if (checkEmpty(ed1) || checkEmpty(ed2)) {
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
                return;
            } else {
                IzracunajPostotak(Double.parseDouble(ed1.getText().toString()), Double.parseDouble(ed2.getText().toString()));
            }

            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa (osim ed3 jer se nikad ne aktivira)
            changeStatusOfFields(false, ed1, ed2);
            // zaključaj radio gumbe
            rdb1.setEnabled(false);
            rdb2.setEnabled(false);
        });

    }

    // ZA MODRIĆA
    // Uzima 2 broja te gleda koji je radiobutton aktiviran i tako određuje kako se računa
    // ništa posebno
    protected void IzracunajPostotak(double br_X, double br_Y) {
        if (rdb1.isChecked()) {
            double _rezultat = (br_X/100) * br_Y;
            ed1.setInputType(InputType.TYPE_CLASS_TEXT);
            ed1.append(getString(R.string.posto_znak));
            ed1.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
            ed3.setText(String.format(Locale.getDefault(), "%.4f", _rezultat));
        } else {
            double _rezultat = (br_X/br_Y) * 100;
            ed3.setText(String.format(Locale.getDefault(), "%.4f", _rezultat));
            ed3.append(getString(R.string.posto_znak));
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