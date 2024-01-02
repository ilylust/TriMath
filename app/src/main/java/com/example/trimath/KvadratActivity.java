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
                st.toastShort(this, getString(R.string.unesite_jednu_vrijednost));
                return;
            }
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(strA, povrsina, opseg, dijagonala, polupis, polopis);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, strA, povrsina, opseg, dijagonala, polupis, polopis);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }
            if (!strA.getText().toString().isEmpty() && checkEmpty(povrsina, opseg, dijagonala, polupis, polopis)) {
                izracunaj(strA, "cm");
            } else if (!povrsina.getText().toString().isEmpty() && checkEmpty(strA, opseg, dijagonala, polupis, polopis)){
                izracunaj(povrsina, "cm");
            } else if (!opseg.getText().toString().isEmpty() && checkEmpty(strA, povrsina, dijagonala, polupis, polopis)) {
                izracunaj(opseg, "cm");
            } else if (!dijagonala.getText().toString().isEmpty() && checkEmpty(strA, povrsina, opseg, polupis, polopis)) {
                izracunaj(dijagonala, "cm");
            } else if (!polupis.getText().toString().isEmpty() && checkEmpty(strA, povrsina, opseg, dijagonala, polopis)) {
                izracunaj(polupis, "cm");
            } else if (!polopis.getText().toString().isEmpty() && checkEmpty(strA, povrsina, opseg, dijagonala, polupis)) {
                izracunaj(polopis, "cm");
            } else {
                st.toastShort(this, getString(R.string.unesite_samo_jednu_vrijednost));
            }
            // promijeni tekst na gumbu
            btn.setText(R.string.izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, strA, povrsina, opseg, dijagonala, polupis, polopis);
        });
    }

    protected void izracunaj(EditText ed, String text) {
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, strA, povrsina, opseg, dijagonala , polupis, polopis);
        if (ed.equals(strA)) {
            // dohvati vrijednost stranice a i izračunaj ostale vrijednosti
            double _strA = Double.parseDouble(ed.getText().toString());

            strA.append(text + " (stranica A)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", Math.pow(_strA, 2)));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 4 * _strA));
            opseg.append(text + " (opseg)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _strA * Math.sqrt(2)));
            dijagonala.append(text + " (dijagonala)");
            polupis.append(String.format(Locale.getDefault(), "%.2f", _strA/2));
            polupis.append(text + " (upisana kružnica)");
            polopis.append(String.format(Locale.getDefault(), "%.2f", (_strA * Math.sqrt(2))/2));
            polopis.append(text + " (opisana kružnica)");
        }
        if (ed.equals(povrsina)) {
            // račun za stranicu a iz površine
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = Math.sqrt(_strA);

            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 4 * _strA));
            opseg.append(text + " (opseg)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _strA * Math.sqrt(2)));
            dijagonala.append(text + " (dijagonala)");
            polupis.append(String.format(Locale.getDefault(), "%.2f", _strA/2));
            polupis.append(text + " (upisana kružnica)");
            polopis.append(String.format(Locale.getDefault(), "%.2f", (_strA * Math.sqrt(2))/2));
            polopis.append(text + " (opisana kružnica)");
        }
        if (ed.equals(opseg)) {
            // račun za stranicu a iz opsega
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = _strA/4;

            opseg.append(text + " (opseg)");
            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", Math.pow(_strA, 2)));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _strA * Math.sqrt(2)));
            dijagonala.append(text + " (dijagonala)");
            polupis.append(String.format(Locale.getDefault(), "%.2f", _strA/2));
            polupis.append(text + " (upisana kružnica)");
            polopis.append(String.format(Locale.getDefault(), "%.2f", (_strA * Math.sqrt(2))/2));
            polopis.append(text + " (opisana kružnica)");
        }
        if (ed.equals(dijagonala)) {
            // račun za stranicu a iz dijagonale
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = _strA/Math.sqrt(2);

            dijagonala.append(text + " (dijagonala)");
            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", Math.pow(_strA, 2)));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 4 * _strA));
            opseg.append(text + " (opseg)");
            polupis.append(String.format(Locale.getDefault(), "%.2f", _strA/2));
            polupis.append(text + " (upisana kružnica)");
            polopis.append(String.format(Locale.getDefault(), "%.2f", (_strA * Math.sqrt(2))/2));
            polopis.append(text + " (opisana kružnica)");
        }
        if (ed.equals(polupis)) {
            // račun za stranicu a iz upisane kružnice
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = _strA*2;

            polupis.append(text + " (upisana kružnica)");
            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", Math.pow(_strA, 2)));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 4 * _strA));
            opseg.append(text + " (opseg)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _strA * Math.sqrt(2)));
            dijagonala.append(text + " (dijagonala)");
            polopis.append(String.format(Locale.getDefault(), "%.2f", (_strA * Math.sqrt(2))/2));
            polopis.append(text + " (opisana kružnica)");
        }
        if (ed.equals(polopis)) {
            // račun za stranicu a iz opisane kružnice
            double _strA = Double.parseDouble(ed.getText().toString());
            _strA = _strA*Math.sqrt(2);

            polopis.append(text + " (opisana kružnica)");
            strA.append(String.format(Locale.getDefault(), "%.2f", _strA));
            strA.append(text + " (stranica A)");
            povrsina.append(String.format(Locale.getDefault(), "%.2f", Math.pow(_strA, 2)));
            povrsina.append(text + getString(R.string.nakvadratznak) + " (površina)");
            opseg.append(String.format(Locale.getDefault(), "%.2f", 4 * _strA));
            opseg.append(text + " (opseg)");
            dijagonala.append(String.format(Locale.getDefault(), "%.2f", _strA * Math.sqrt(2)));
            dijagonala.append(text + " (dijagonala)");
            polupis.append(String.format(Locale.getDefault(), "%.2f", _strA/2));
            polupis.append(text + " (upisana kružnica)");
        }
        // vrati nazad originalnu vrijednost
        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, povrsina, opseg, dijagonala , polupis, polopis);
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