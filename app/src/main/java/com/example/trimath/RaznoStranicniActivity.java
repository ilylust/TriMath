package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class RaznoStranicniActivity extends AppCompatActivity {

    EditText strA, strB, strC, opis, upis, kutAlpha, kutBeta, kutGama, povrsina, opseg,visinaa,visinab,visinac;
    Button btn;
    SimplifiedToast st = new SimplifiedToast();
    int f = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raznostr);
        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        // Inicijalizacija tekstnih objekata
        strA = findViewById(R.id.editTextNumber11);
        strB = findViewById(R.id.editTextNumber12);
        strC = findViewById(R.id.editTextNumber13);
        upis = findViewById(R.id.editTextNumber14);
        opis = findViewById(R.id.editTextNumber15);
        kutAlpha = findViewById(R.id.editTextNumber16);
        kutBeta = findViewById(R.id.editTextNumber17);
        kutGama = findViewById(R.id.editTextNumber18);
        povrsina = findViewById(R.id.editTextNumber19);
        opseg = findViewById(R.id.editTextNumber20);
        visinaa = findViewById(R.id.editTextNumber21);
        visinab = findViewById(R.id.editTextNumber22);
        visinac = findViewById(R.id.editTextNumber23);

        // Inicijalizacija gumba
        btn = findViewById(R.id.button3);
        btn.setOnClickListener(v -> {
            // provjera je li ijedna vrijednost unesena
            if (checkEmpty(strA, strB, strC, upis, opis, kutAlpha, kutBeta ,kutGama, povrsina, opseg,visinaa,visinab,visinac)) {
                // ako nije prikaži poruku
                st.toastShort(this, getString(R.string.Unesite_dvije_vrijednosti));
                return;
            }
            if (f == 1) {
                // izbriši sve vrijednosti iz polja
                resetTextOfFields(strA, strB, strC, upis, opis, kutAlpha, kutBeta ,kutGama, povrsina, opseg,visinaa,visinab,visinac);
                // promijeni tekst na gumbu
                btn.setText(getString(R.string.Izracunaj));
                // otključaj polja za unos vrijednosti
                changeStatusOfFields(true, strA, strB, strC, upis, opis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa,visinab,visinac);
                // promijeni vrijednost zastavice
                f = 0;
                return;
            }
// opseg,visinaa

            if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(strC)) && checkEmpty(upis, opis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa,visinab,visinac)) {
                izracunaj(strA,strB,strC,"cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(opis)) && checkEmpty(upis, strC, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa,visinab,visinac)) {
                izracunaj(strA,strB,opis,"cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(upis)) && checkEmpty(opis, strC, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa,visinab,visinac)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(kutAlpha)) && checkEmpty(upis, strC, opis, kutBeta ,kutGama, povrsina, opseg, visinaa,visinab,visinac)) {
                izracunaj(strA,strB,kutAlpha,"cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(kutBeta)) && checkEmpty(upis, strC, opis, kutAlpha ,kutGama, povrsina, opseg, visinaa,visinab,visinac)) {
                izracunaj(strA,strB,kutBeta,"cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(kutGama)) && checkEmpty(upis, strC, opis, kutAlpha ,kutBeta, povrsina, opseg, visinaa,visinab,visinac)) {
                izracunaj(strA,strB,kutGama,"cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(povrsina)) && checkEmpty(upis, strC, opis, kutAlpha ,kutBeta, kutGama, opseg, visinaa,visinab,visinac)) {
                izracunaj(strA,strB,povrsina,"cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(opseg)) && checkEmpty(upis, strC, opis, kutAlpha ,kutBeta, povrsina, kutGama, visinaa,visinab,visinac)) {
                izracunaj(strA,strB,opseg,"cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(visinaa)) && checkEmpty(upis, strC, opis, kutAlpha ,kutBeta, povrsina, kutGama, opseg,visinab,visinac)) {
                izracunaj(strA,strB, visinaa,"cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(visinab)) && checkEmpty(upis, strC, opis, kutAlpha ,kutBeta, povrsina, kutGama, opseg,visinaa,visinac)) {
                izracunaj(strA,strB, visinab,"cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strB)&& !checkEmpty(visinac)) && checkEmpty(upis, strC, opis, kutAlpha ,kutBeta, povrsina, kutGama, opseg,visinab,visinaa)) {
                izracunaj(strA,strB, visinac,"cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)&& !checkEmpty(opis)) && checkEmpty(strA, visinac, upis, kutAlpha ,kutBeta, povrsina, kutGama, opseg,visinab,visinaa)) {
                izracunaj(strB, strC,opis,"cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)&& !checkEmpty(upis)) && checkEmpty(strA, visinac, opis, kutAlpha ,kutBeta, povrsina, kutGama, opseg,visinab,visinaa)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)&& !checkEmpty(kutAlpha)) && checkEmpty(strA, visinac, opis, upis ,kutBeta, povrsina, kutGama, opseg,visinab,visinaa)) {
                izracunaj(strB, strC,kutAlpha,"cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)&& !checkEmpty(kutBeta)) && checkEmpty(strA, visinac, opis, upis ,kutAlpha, povrsina, kutGama, opseg,visinab,visinaa)) {
                izracunaj(strB, strC,kutBeta,"cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)&& !checkEmpty(kutGama)) && checkEmpty(strA, visinac, opis, upis ,kutAlpha, povrsina, kutBeta, opseg,visinab,visinaa)) {
                izracunaj(strB, strC,kutGama,"cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)&& !checkEmpty(visinaa)) && checkEmpty(strA, visinac, opis, upis ,kutAlpha, povrsina, kutBeta, opseg,visinab,kutAlpha)) {
                izracunaj(strB, strC,visinaa,"cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)&& !checkEmpty(visinab)) && checkEmpty(strA, visinac, opis, upis ,kutAlpha, povrsina, kutBeta, opseg,kutBeta,visinaa)) {
                izracunaj(strB, strC,visinab,"cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(strC)&& !checkEmpty(visinac)) && checkEmpty(strA, kutGama, opis, upis ,kutAlpha, povrsina, kutBeta, opseg,visinab,visinaa)) {
                izracunaj(strB, strC,visinac,"cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(upis))) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(strA)) && checkEmpty(strB, strC, kutGama, kutBeta, upis , povrsina, opseg,visinab,visinaa,visinac)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(strB)) && checkEmpty(strA, strC, kutGama, kutBeta, upis , povrsina, opseg,visinab,visinaa,visinac)) {
                izracunaj(opis, kutAlpha, strB, "cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(strC)) && checkEmpty(strB, strA, kutGama, kutBeta, upis , povrsina, opseg,visinab,visinaa,visinac)) {
                izracunaj(opis, kutAlpha, strC, "cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(kutBeta)) && checkEmpty(strA, strC, kutGama, strB, upis , povrsina, opseg,visinab,visinaa,visinac)) {
                izracunaj(opis, kutAlpha, kutBeta, "cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(kutGama)) && checkEmpty(strB, strA, strC, kutBeta, upis , povrsina, opseg,visinab,visinaa,visinac)) {
                izracunaj(opis, kutAlpha, kutGama, "cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(povrsina)) && checkEmpty(strB, strA, strC, kutBeta, upis ,kutGama, opseg,visinab,visinaa,visinac)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(opseg)) && checkEmpty(strB, strA, strC, kutBeta, upis ,kutGama,povrsina ,visinab,visinaa,visinac)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(visinaa)) && checkEmpty(strB, strA, strC, kutBeta, upis ,kutGama,povrsina ,visinab,opseg,visinac)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(visinab)) && checkEmpty(strB, strA, strC, kutBeta, upis ,kutGama,povrsina ,visinaa,opseg,visinac)) {
                izracunaj(opis, kutAlpha, visinab, "cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(kutAlpha)&& !checkEmpty(visinac)) && checkEmpty(strB, strA, strC, kutBeta, upis ,kutGama,povrsina ,visinaa,opseg,visinab)) {
                izracunaj(opis, kutAlpha, visinac, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(strA)) && checkEmpty(strB, visinac, strC, opis, upis ,kutGama,povrsina ,visinaa,opseg,visinab)) {
                izracunaj(kutAlpha, kutBeta, strA, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(strB)) && checkEmpty(strA, visinac, strC, opis, upis ,kutGama,povrsina ,visinaa,opseg,visinab)) {
                izracunaj(kutAlpha,kutBeta, strB, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(strC)) && checkEmpty(strB, visinac, strA, opis, upis ,kutGama,povrsina ,visinaa,opseg,visinab)) {
                izracunaj(kutAlpha, kutBeta, strC, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(upis)) && checkEmpty(strB, visinac, strA, opis, strC ,kutGama,povrsina ,visinaa,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(opis)) && checkEmpty(strB, visinac, strA, upis, strC ,kutGama,povrsina ,visinaa,opseg,visinab)) {
                izracunaj(kutAlpha, kutBeta, opis, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(kutGama)) && checkEmpty(strB, visinac, strA, upis, strC ,opis,povrsina ,visinaa,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(povrsina)) && checkEmpty(strB, visinac, strA, upis, strC ,kutGama, opis, upis, visinaa,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(opseg)) && checkEmpty(strB, visinac, strA, upis, strC ,kutGama, opis, upis, visinaa,povrsina,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(visinaa)) && checkEmpty(strB, visinac, strA, opis, upis ,kutGama,povrsina ,strC,opseg,visinab)) {
                izracunaj(kutAlpha, kutBeta, visinaa, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(visinab)) && checkEmpty(strB, visinac, strA, opis, upis ,kutGama,povrsina ,strC,opseg,visinaa)) {
                izracunaj(kutAlpha, kutBeta, visinab, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutBeta) && !checkEmpty(visinac)) && checkEmpty(strB, visinaa, strA, opis, upis ,kutGama,povrsina ,strC,opseg,visinab)) {
                izracunaj(kutAlpha, kutBeta, visinac, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(strA)) && checkEmpty(strB, visinac, visinaa, opis, upis ,kutAlpha,povrsina ,strC,opseg,visinab)) {
                izracunaj(kutAlpha, kutBeta, visinaa, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(strB)) && checkEmpty(visinab, visinac, strA, opis, upis , kutAlpha,povrsina ,strC,opseg,visinaa)) {
                izracunaj(kutAlpha, kutBeta, visinab, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(strC)) && checkEmpty(strB, visinaa, visinac, opis, upis ,kutAlpha,povrsina ,visinac,opseg,visinab)) {
                izracunaj(kutAlpha, kutBeta, visinac, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(upis)) && checkEmpty(strB, visinaa, visinac, opis, strC ,kutAlpha,povrsina ,visinac,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(opis)) && checkEmpty(strB, visinaa, visinac, upis, strC ,kutAlpha,povrsina ,visinac,opseg,visinab)) {
                izracunaj(kutAlpha, kutBeta, opis, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(povrsina)) && checkEmpty(strB, visinaa, visinac, opis, strC ,kutAlpha,upis ,visinac,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(opseg)) && checkEmpty(strB, visinaa, visinac, opis, strC ,kutAlpha,upis ,visinac,povrsina,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(visinaa)) && checkEmpty(strB, visinac, strA, opis, upis ,kutAlpha,povrsina ,strC,opseg,visinab)) {
                izracunaj(kutBeta, kutGama, visinaa, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(visinab)) && checkEmpty(strB, visinac, strA, opis, upis ,kutAlpha,povrsina ,strC,opseg,visinaa)) {
                izracunaj(kutBeta, kutGama, visinab, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(kutGama) && !checkEmpty(visinac)) && checkEmpty(strB, visinaa, strA, opis, upis ,kutAlpha,povrsina ,strC,opseg,visinab)) {
                izracunaj(kutBeta, kutGama, visinac, "cm");
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(strA)) && checkEmpty(strB, visinaa, visinac, opis, upis ,kutAlpha,kutBeta ,strC,opseg,visinab)) {
                izracunaj(kutGama, povrsina, strA, "cm");
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(strB)) && checkEmpty(strA, visinaa, visinac, opis, upis ,kutAlpha,kutBeta ,strC,opseg,visinab)) {
                izracunaj(kutGama, povrsina, strB, "cm");
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(strC)) && checkEmpty(strB, visinaa, visinac, opis, upis ,kutAlpha,kutBeta ,strA,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(upis)) && checkEmpty(strB, visinaa, visinac, opis, strC ,kutAlpha,kutBeta ,strA,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(opis)) && checkEmpty(strB, visinaa, visinac, strC, upis ,kutAlpha,kutBeta ,strA,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(kutAlpha)) && checkEmpty(strB, visinaa, visinac, strC, upis ,opis,kutBeta ,strA,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(opseg)) && checkEmpty(strB, visinaa, visinac, strC, upis ,opis,kutBeta ,strA,kutAlpha,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(visinaa)) && checkEmpty(strA, strB, visinac, opis, upis ,kutAlpha,kutBeta ,strC,opseg,visinab)) {
                izracunaj(kutGama, povrsina, visinaa, "cm");
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(visinab)) && checkEmpty(strA, strB, visinac, opis, upis ,kutAlpha,kutBeta ,strC,opseg,visinaa)) {
                izracunaj(kutGama, povrsina, visinab, "cm");
            } else if ((!checkEmpty(kutGama) && !checkEmpty(povrsina) && !checkEmpty(visinac)) && checkEmpty(strA, strB, visinaa, opis, upis ,kutAlpha,kutBeta ,strC,opseg,visinab)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(povrsina))) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(strA)) && checkEmpty(visinac, strB, kutGama, opis, upis ,kutAlpha,kutBeta ,strC,visinab,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(strB)) && checkEmpty(visinac, strA, kutGama, opis, upis ,kutAlpha,kutBeta ,strC,visinab,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(strC)) && checkEmpty(visinac, strA, kutGama, opis, upis ,kutAlpha,kutBeta ,strB,visinab,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(upis)) && checkEmpty(visinac, strA, kutGama, opis, strC ,kutAlpha,kutBeta ,strB,visinab,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(opis)) && checkEmpty(visinac, strA, kutGama, upis, strC ,kutAlpha,kutBeta ,strB,visinab,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(kutAlpha)) && checkEmpty(visinac, strA, kutGama, upis, strC ,opis,kutBeta ,strB,visinab,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(kutBeta)) && checkEmpty(visinac, strA, kutGama, upis, strC ,opis,kutAlpha ,strB,visinab,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(kutGama)) && checkEmpty(visinac, strA, kutBeta, upis, strC ,opis,kutAlpha ,strB,visinab,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(visinab)) && checkEmpty(visinac, strA, kutAlpha, upis, strC , opis,kutGama ,strB,povrsina,kutBeta)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opseg) && !checkEmpty(visinaa) && !checkEmpty(visinac)) && checkEmpty(visinab, strA, kutAlpha, upis, strC , opis,kutGama ,strB,povrsina,kutBeta)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(strA)) && checkEmpty(visinac, strB, kutGama, opis, upis ,kutAlpha,kutBeta ,strC,opseg,povrsina)) {
                izracunaj(visinaa, visinab, strA, "cm");
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(strB)) && checkEmpty(visinac, strA, kutGama, opis, upis ,kutAlpha,kutBeta ,strC,opseg,povrsina)) {
                izracunaj(visinaa, visinab, strB, "cm");
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(strC)) && checkEmpty(visinac, strA, kutGama, opis, upis ,kutAlpha,kutBeta ,strB,opseg,povrsina)) {
                izracunaj(visinaa, visinab, strC, "cm");
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(upis)) && checkEmpty(visinac, strA, kutGama, opis, strC ,kutAlpha,kutBeta ,strB,opseg,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(opis)) && checkEmpty(visinac, strA, kutGama, upis, strC ,kutAlpha,kutBeta ,strB,opseg,povrsina)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(kutAlpha)) && checkEmpty(visinac, strA, kutGama, upis, strC , opis,kutBeta ,strB,opseg,povrsina)) {
                izracunaj(visinaa, visinab, kutAlpha, "cm");
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(kutGama)) && checkEmpty(visinac, strA, kutAlpha, upis, strC , opis,kutBeta ,strB,opseg,povrsina)) {
                izracunaj(visinaa, visinab, kutGama, "cm");
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(kutBeta)) && checkEmpty(visinac, strA, kutAlpha, upis, strC , opis,kutGama ,strB,opseg,povrsina)) {
                izracunaj(visinaa, visinab, kutBeta, "cm");
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(povrsina)) && checkEmpty(visinac, strA, kutAlpha, upis, strC , opis,kutGama ,strB,opseg,kutBeta)) {
                izracunaj(visinaa, visinab, povrsina, "cm");
            } else if ((!checkEmpty(visinaa) && !checkEmpty(visinab) && !checkEmpty(visinac)) && checkEmpty(opseg, strA, kutAlpha, upis, strC , opis,kutGama ,strB,povrsina,kutBeta)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(visinab) && !checkEmpty(visinac) && !checkEmpty(strA)) && checkEmpty(opseg, visinaa, kutAlpha, upis, strC , opis,kutGama ,strB,povrsina,kutBeta)) {
                izracunaj(visinab, visinac, strA, "cm");
            } else if ((!checkEmpty(visinab) && !checkEmpty(visinac) && !checkEmpty(strB)) && checkEmpty(opseg, visinaa, kutAlpha, upis, strC , opis,kutGama ,strA,povrsina,kutBeta)) {
                izracunaj(visinab, visinac, strB, "cm");
            } else if ((!checkEmpty(visinab) && !checkEmpty(visinac) && !checkEmpty(strC)) && checkEmpty(opseg, visinaa, kutAlpha, upis, strA , opis,kutGama ,strB,povrsina,kutBeta)) {
                izracunaj(visinab, visinac, strC, "cm");
            } else if ((!checkEmpty(visinab) && !checkEmpty(visinac) && !checkEmpty(kutAlpha)) && checkEmpty(strC, visinaa, povrsina, upis, strA , opis,kutGama ,strB,opseg,kutBeta)) {
                izracunaj(visinab, visinac, kutAlpha, "cm");
            } else if ((!checkEmpty(visinab) && !checkEmpty(visinac) && !checkEmpty(kutBeta)) && checkEmpty(strC, visinaa, povrsina, upis, strA , opis,kutGama ,strB,opseg,kutAlpha)) {
                izracunaj(visinab, visinac, kutBeta, "cm");
            } else if ((!checkEmpty(visinab) && !checkEmpty(visinac) && !checkEmpty(kutGama)) && checkEmpty(strC, visinaa, povrsina, upis, strA , opis,kutBeta ,strB,opseg,kutAlpha)) {
                izracunaj(visinab, visinac, kutGama, "cm");
            } else if ((!checkEmpty(visinab) && !checkEmpty(visinac) && !checkEmpty(povrsina)) && checkEmpty(strC, visinaa, kutAlpha, upis, strA , opis,kutGama ,strB,opseg,kutBeta)) {
                izracunaj(visinab, visinac, povrsina, "cm");
            } else if ((!checkEmpty(visinab) && !checkEmpty(visinac) && !checkEmpty(opseg)) && checkEmpty(strC, visinaa, kutAlpha, upis, strA , opis,kutGama ,strB,povrsina,kutBeta)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(visinac) && !checkEmpty(strA) && !checkEmpty(strC)) && checkEmpty(opseg, visinaa, kutAlpha, upis, visinab , opis,kutGama ,strB,povrsina,kutBeta)) {
                izracunaj(visinac, strA, strC, "cm");
            } else if ((!checkEmpty(visinac) && !checkEmpty(strA) && !checkEmpty(opis)) && checkEmpty(opseg, visinaa, kutAlpha, upis, visinab , strC ,kutGama ,strB,povrsina,kutBeta)) {
                izracunaj(visinac, strA, opis, "cm");
            } else if ((!checkEmpty(visinac) && !checkEmpty(strA) && !checkEmpty(kutAlpha)) && checkEmpty(opseg, visinaa, opis, upis, visinab , strC ,kutGama ,strB,povrsina,kutBeta)) {
                izracunaj(visinac, strA, kutAlpha, "cm");
            } else if ((!checkEmpty(visinac) && !checkEmpty(strA) && !checkEmpty(kutBeta)) && checkEmpty(opseg, visinaa, opis, upis, visinab , strC ,kutGama ,strB,povrsina,kutAlpha)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(visinac) && !checkEmpty(strA) && !checkEmpty(kutGama)) && checkEmpty(opseg, visinaa, opis, upis, visinab , strC ,kutBeta ,strB,povrsina,kutAlpha)) {
                izracunaj(visinac, strA, kutGama, "cm");
            } else if ((!checkEmpty(visinac) && !checkEmpty(strA) && !checkEmpty(opseg)) && checkEmpty(kutGama, visinaa, opis, upis, visinab , strC ,kutBeta ,strB,povrsina,kutAlpha)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(visinac) && !checkEmpty(strA) && !checkEmpty(povrsina)) && checkEmpty(kutGama, visinaa, opis, upis, visinab , strC ,kutBeta ,strB,opseg,kutAlpha)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(upis)) && checkEmpty(kutGama, visinaa, opis, visinac, visinab , povrsina ,kutBeta ,strB,opseg,kutAlpha)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(opis)) && checkEmpty(kutGama, visinaa, upis, visinac, visinab , povrsina ,kutBeta ,strB,opseg,kutAlpha)) {
                izracunaj(strA, strC, opis, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(kutAlpha)) && checkEmpty(kutGama, visinaa, upis, visinac, visinab , povrsina ,kutBeta ,strB,opseg,opis)) {
                izracunaj(strA, strC, kutAlpha, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(kutBeta)) && checkEmpty(kutGama, visinaa, upis, visinac, visinab , povrsina ,kutAlpha ,strB,opseg,opis)) {
                izracunaj(strA, strC, kutBeta, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(kutGama)) && checkEmpty(kutAlpha, visinaa, upis, visinac, visinab , povrsina ,kutBeta ,strB,opseg,opis)) {
                izracunaj(strA, strC, kutGama, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(povrsina)) && checkEmpty(kutAlpha, visinaa, upis, visinac, visinab , kutGama ,kutBeta ,strB,opseg,opis)) {
                izracunaj(strA, strC, povrsina, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(opseg)) && checkEmpty(kutAlpha, visinaa, upis, visinac, visinab , kutGama ,kutBeta ,strB,povrsina,opis)) {
                izracunaj(strA, strC, opseg, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(visinaa)) && checkEmpty(kutAlpha, kutGama, upis, visinac, visinab , povrsina ,kutBeta ,strB,opseg,opis)) {
                izracunaj(strA, strC, visinaa, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(visinab)) && checkEmpty(kutAlpha, visinaa, upis, visinac, povrsina , kutGama ,kutBeta ,strB,opseg,opis)) {
                izracunaj(strA, strC, visinab, "cm");
            } else if ((!checkEmpty(strA) && !checkEmpty(strC) && !checkEmpty(visinac)) && checkEmpty(kutAlpha, visinaa, upis, opseg, visinab , kutGama ,kutBeta ,strB,povrsina,opis)) {
                izracunaj(strA, strC, visinac, "cm");
            } else if ((!checkEmpty(strB) && !checkEmpty(upis))) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(strC) && !checkEmpty(opis) && !checkEmpty(kutBeta)) && checkEmpty(strA,strB,upis,kutAlpha,kutGama,povrsina,opseg,visinaa,visinab,visinac)) {
                izracunaj(strC, opis, kutBeta, "cm");
            } else if ((!checkEmpty(strC) && !checkEmpty(opis) && !checkEmpty(kutGama)) && checkEmpty(strA,strB,upis,kutAlpha,kutBeta,povrsina,opseg,visinaa,visinab,visinac)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(strC) && !checkEmpty(opis) && !checkEmpty(povrsina)) && checkEmpty(strA,strB,upis,kutAlpha,opseg,kutGama,kutBeta,visinaa,visinab,visinac)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(strC) && !checkEmpty(opis) && !checkEmpty(opseg)) && checkEmpty(strA,strB,upis,kutAlpha,povrsina,kutGama,kutBeta,visinaa,visinab,visinac)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(strC) && !checkEmpty(opis) && !checkEmpty(visinaa)) && checkEmpty(strA,strB,upis,kutAlpha,povrsina,kutGama,kutBeta,opseg,visinab,visinac)) {
                izracunaj(strC, opis, visinaa, "cm");
            } else if ((!checkEmpty(strC) && !checkEmpty(opis) && !checkEmpty(visinab)) && checkEmpty(strA,strB,upis,kutAlpha,povrsina,kutGama,kutBeta,opseg,visinaa,visinac)) {
                izracunaj(strC, opis, visinab, "cm");
            } else if ((!checkEmpty(strC) && !checkEmpty(opis) && !checkEmpty(visinac)) && checkEmpty(strA,strB,upis,kutAlpha,povrsina,kutGama,kutBeta,opseg,visinab,visinaa)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            }  else if ((!checkEmpty(upis) && !checkEmpty(kutAlpha))) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opis) && !checkEmpty(kutBeta) && !checkEmpty(strA)) && checkEmpty(visinaa,strB,upis,kutAlpha,povrsina,kutGama,strC,opseg,visinab,visinac)) {
                izracunaj(opis, kutBeta, strA, "cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(kutBeta) && !checkEmpty(povrsina)) && checkEmpty(visinaa,strA,upis,kutAlpha,strB,kutGama,strC,opseg,visinab,visinac)) {
                izracunaj(opis, kutBeta, povrsina, "cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(kutBeta) && !checkEmpty(opseg)) && checkEmpty(visinaa,strA,upis,kutAlpha,strB,kutGama,strC,povrsina,visinab,visinac)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(opis) && !checkEmpty(kutBeta) && !checkEmpty(visinaa)) && checkEmpty(povrsina,strA,upis,kutAlpha,strB,kutGama,strC,opseg,visinab,visinac)) {
                izracunaj(opis, kutBeta, visinaa, "cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(kutBeta) && !checkEmpty(visinab)) && checkEmpty(povrsina,strA,upis,kutAlpha,strB,kutGama,strC,opseg,visinaa,visinac)) {
                izracunaj(opis, kutBeta, visinab, "cm");
            } else if ((!checkEmpty(opis) && !checkEmpty(kutBeta) && !checkEmpty(visinac)) && checkEmpty(povrsina,strA,upis,kutAlpha,strB,kutGama,strC,opseg,visinab,visinaa)) {
                izracunaj(opis, kutBeta, visinac, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutGama) && !checkEmpty(strA)) && checkEmpty(povrsina,opis,upis,visinac,strB,kutBeta,strC,opseg,visinab,visinaa)) {
                izracunaj(kutAlpha, kutGama, strA, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutGama) && !checkEmpty(strB)) && checkEmpty(povrsina,opis,upis,visinac,strA,kutBeta,strC,opseg,visinab,visinaa)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutGama) && !checkEmpty(strC)) && checkEmpty(povrsina,opis,upis,visinac,strB,kutBeta,strA,opseg,visinab,visinaa)) {
                izracunaj(kutAlpha, kutGama, strC, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutGama) && !checkEmpty(opseg)) && checkEmpty(povrsina,opis,upis,visinac,strB,kutBeta,strA,strC,visinab,visinaa)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutGama) && !checkEmpty(povrsina)) && checkEmpty(opseg,opis,upis,visinac,strB,kutBeta,strA,strC,visinab,visinaa)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutGama) && !checkEmpty(visinaa)) && checkEmpty(opseg,opis,upis,visinac,strB,kutBeta,strA,strC,visinab,povrsina)) {
                izracunaj(kutAlpha, kutGama, visinaa, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutGama) && !checkEmpty(visinab)) && checkEmpty(opseg,opis,upis,visinac,strB,kutBeta,strA,strC,visinaa,povrsina)) {
                izracunaj(kutAlpha, kutGama, visinab, "cm");
            } else if ((!checkEmpty(kutAlpha) && !checkEmpty(kutGama) && !checkEmpty(visinac)) && checkEmpty(opseg,opis,upis,visinaa,strB,kutBeta,strA,strC,visinab,povrsina)) {
                izracunaj(kutAlpha, kutGama, visinac, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(povrsina) && !checkEmpty(strA)) && checkEmpty(opseg,opis,upis,visinaa,strB,kutAlpha,visinac,strC,visinab,kutGama)) {
                izracunaj(kutBeta, povrsina, strA, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(povrsina) && !checkEmpty(strB)) && checkEmpty(opseg,opis,upis,visinaa,strA,kutAlpha,visinac,strC,visinab,kutGama)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(povrsina) && !checkEmpty(strC)) && checkEmpty(opseg,opis,upis,visinaa,strA,kutAlpha,visinac,strB,visinab,kutGama)) {
                izracunaj(kutBeta, povrsina, strC, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(povrsina) && !checkEmpty(upis)) && checkEmpty(opseg,opis,strB,visinaa,strA,kutAlpha,visinac,strC,visinab,kutGama)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(povrsina) && !checkEmpty(visinaa)) && checkEmpty(opseg,opis,strB,upis,strA,kutAlpha,visinac,strC,visinab,kutGama)) {
                izracunaj(kutBeta, povrsina, visinaa, "cm");
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(povrsina) && !checkEmpty(visinab)) && checkEmpty(opseg,opis,strB,upis,strA,kutAlpha,visinac,strC,visinaa,kutGama)) {
                st.toastShort(this, getString(R.string.nije_moguce));
            } else if ((!checkEmpty(kutBeta) && !checkEmpty(povrsina) && !checkEmpty(visinac)) && checkEmpty(opseg,opis,strB,upis,strA,kutAlpha,visinaa,strC,visinab,kutGama)) {
                izracunaj(kutBeta, povrsina, visinac, "cm");
            } else if ((!checkEmpty(kutGama) && !checkEmpty(opseg))) {
                st.toastShort(this, getString(R.string.nije_moguce));
            }

            // promijeni tekst na gumbu
            btn.setText(R.string.Izbrisi);
            // promijeni vrijednost zastavice
            f = 1;
            // "zaključaj" sva polja za spriječavanje unosa
            changeStatusOfFields(false, strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
        });
    }
    protected void izracunaj(EditText ed, EditText ed2, EditText ed3,  String text) {
        // promijeni vrijednost inputType tako da se mogu prikazati znakovi
        changeInputType(InputType.TYPE_CLASS_TEXT, strA, strB, strC, upis, opis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);

        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(strC)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _strC = Double.parseDouble(ed3.getText().toString());
            if (_strA+_strB<=_strC || _strB+_strC<=_strA || _strA+_strC <= _strB)
            {
                st.toastShort(this, getString(R.string.nije_moguce));
                return;
            }
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(opis)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _opis = Double.parseDouble(ed3.getText().toString());
            double _strC = 2*_opis*Math.sin(Math.PI - Math.asin(_strA/(2*_opis))-Math.asin(_strB/(2*_opis)));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(kutAlpha)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _kutBeta = Math.asin((_strB*Math.sin(_kutAlpha))/_strA);
            double _strC = (_strB*Math.sin(Math.PI-_kutBeta-_kutAlpha))/Math.sin(_kutBeta);
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(kutBeta)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _kutAlpha = Math.asin((_strA*Math.sin(_kutBeta))/_strB);
            double _strC = (_strB*Math.sin(Math.PI-_kutBeta-_kutAlpha))/Math.sin(_kutBeta);
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(kutGama)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _kutGama = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(povrsina)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _povrsina = Double.parseDouble(ed3.getText().toString());
            double _kutGama = Math.asin((2*_povrsina)/(_strA*_strB));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(opseg)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _opseg = Double.parseDouble(ed3.getText().toString());
            double _strC = _opseg -_strA-_strB;
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(visinaa)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _visinaa = Double.parseDouble(ed3.getText().toString());
            double _kutGama = Math.asin(_visinaa/_strB);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(visinab)) {
            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _visinab = Double.parseDouble(ed3.getText().toString());
            double _kutGama = Math.asin(_visinab/_strA);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strB) && ed3.equals(visinac)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strB = Double.parseDouble(ed2.getText().toString());
            double _visinac = Double.parseDouble(ed3.getText().toString());
            double _kutBeta = Math.asin(_visinac/_strA);
            double _kutAlpha = Math.asin(_visinac/_strB);
            double _strC = (_strB*Math.sin(Math.PI-_kutBeta-_kutAlpha))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(strC) && ed3.equals(opis)) {

            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _opis = Double.parseDouble(ed3.getText().toString());
            double _kutBeta = Math.asin(_strB/(2*_opis));
            double _kutGama = Math.asin(_strC/(2*_opis));
            double _strA = (_strB*Math.sin(Math.PI-_kutBeta-_kutGama))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(strC) && ed3.equals(kutAlpha)) {

            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strA = Math.sqrt(Math.pow(_strB,2)+Math.pow(_strC,2)-2*_strB*_strC*Math.cos(_kutAlpha));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(strC) && ed3.equals(kutBeta)) {

            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _kutGama = Math.asin((_strC*Math.sin(_kutBeta))/_strB);
            double _strA = (_strB*Math.sin(Math.PI-_kutBeta-_kutGama))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(strC) && ed3.equals(kutGama)) {

            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _kutGama = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _kutBeta = Math.asin((_strB*Math.sin(_kutGama))/_strC);
            double _strA = (_strB*Math.sin(Math.PI-_kutBeta-_kutGama))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(strC) && ed3.equals(visinaa)) {

            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _visinaa = Double.parseDouble(ed3.getText().toString());
            double _kutBeta = Math.asin(_visinaa/_strC);
            double _kutGama = Math.asin(_visinaa/_strB);
            double _strA = Math.sqrt(Math.pow(_strB,2)+Math.pow(_strC,2)-2*_strB*_strC*Math.cos(Math.PI-_kutBeta-_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(strC) && ed3.equals(visinab)) {

            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _visinab = Double.parseDouble(ed3.getText().toString());
            double _kutAlpha = Math.asin(_visinab/_strC);
            double _strA = Math.sqrt(Math.pow(_strB,2)+Math.pow(_strC,2)-2*_strB*_strC*Math.cos(_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strB) && ed2.equals(strC) && ed3.equals(visinac)) {

            double _strB = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _visinac = Double.parseDouble(ed3.getText().toString());
            double _kutAlpha = Math.asin(_visinac/_strB);
            double _strA = Math.sqrt(Math.pow(_strB,2)+Math.pow(_strC,2)-2*_strB*_strC*Math.cos(_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }

        if (ed.equals(opis) && ed2.equals(kutAlpha) && ed3.equals(strB)) {

            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strB = Double.parseDouble(ed3.getText().toString());
            double _strA = 2*_opis*Math.sin(_kutAlpha);
            double _kutBeta = Math.asin(_strB/(2*_opis));
            double _strC =2*_opis*Math.sin(Math.PI-_kutAlpha-_kutBeta);
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(opis) && ed2.equals(kutAlpha) && ed3.equals(strC)) {

            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strC = Double.parseDouble(ed3.getText().toString());
            double _strA = 2*_opis*Math.sin(_kutAlpha);
            double _kutGama = Math.asin(_strC/(2*_opis));
            double _strB =2*_opis*Math.sin(Math.PI-_kutAlpha-_kutGama);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(opis) && ed2.equals(kutAlpha) && ed3.equals(kutBeta)) {

            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _kutBeta = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strA = 2*_opis*Math.sin(_kutAlpha);
            double _strB = 2*_opis*Math.sin(_kutBeta);
            double _strC =2*_opis*Math.sin(Math.PI-_kutAlpha-_kutBeta);
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(opis) && ed2.equals(kutAlpha) && ed3.equals(kutGama)) {

            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strA = 2*_opis*Math.sin(_kutAlpha);
            double _strC = 2*_opis*Math.sin(_kutGama);
            double _strB =2*_opis*Math.sin(Math.PI-_kutAlpha-_kutGama);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(opis) && ed2.equals(kutAlpha) && ed3.equals(visinab)) {

            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinab = Double.parseDouble(ed3.getText().toString());
            double _strA = 2*_opis*Math.sin(_kutAlpha);
            double _kutGama = Math.asin(_visinab/_strA);
            double _strC = 2*_opis*Math.sin(_kutGama);
            double _strB =2*_opis*Math.sin(Math.PI-_kutAlpha-_kutGama);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(opis) && ed2.equals(kutAlpha) && ed3.equals(visinac)) {

            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinac = Double.parseDouble(ed3.getText().toString());
            double _strA = 2*_opis*Math.sin(_kutAlpha);
            double _kutBeta = Math.asin(_visinac/_strA);
            double _strB = 2*_opis*Math.sin(_kutAlpha);
            double _strC =2*_opis*Math.sin(Math.PI-_kutAlpha-_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutBeta) && ed3.equals(strA)) {

            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strA = Double.parseDouble(ed3.getText().toString());
            double _strB = (_strA*Math.sin(_kutBeta))/Math.sin(_kutAlpha);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(Math.PI-_kutAlpha-_kutBeta));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutBeta) && ed3.equals(strB)) {

            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strB = Double.parseDouble(ed3.getText().toString());
            double _strA = (_strB*Math.sin(_kutAlpha))/Math.sin(_kutBeta);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(Math.PI-_kutAlpha-_kutBeta));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutBeta) && ed3.equals(strC)) {

            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strC = Double.parseDouble(ed3.getText().toString());
            double _strB = (_strC*Math.sin(_kutBeta))/Math.sin(Math.PI-_kutAlpha-_kutBeta);
            double _strA = (_strB*Math.sin(_kutAlpha))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutBeta) && ed3.equals(opis)) {

            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _opis = Double.parseDouble(ed3.getText().toString());
            double _strB = 2*_opis*Math.sin(_kutBeta);
            double _strA = 2*_opis*Math.sin(_kutAlpha);
            double _strC = 2*_opis*Math.sin(Math.PI-_kutAlpha-_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        } if (ed.equals(kutAlpha) && ed2.equals(kutBeta) && ed3.equals(visinaa)) {

            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinaa = Double.parseDouble(ed3.getText().toString());
            double _strB = _visinaa/Math.sin(Math.PI-_kutAlpha-_kutBeta);
            double _strC = _visinaa/Math.sin(_kutBeta);
            double _strA = (_strB*Math.sin(_kutAlpha))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutBeta) && ed3.equals(visinab)) {

            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinab = Double.parseDouble(ed3.getText().toString());
            double _strA = _visinab/Math.sin(Math.PI-_kutAlpha-_kutBeta);
            double _strC = _visinab/Math.sin(_kutAlpha);
            double _strB = (_strC*Math.sin(_kutBeta))/Math.sin(Math.PI-_kutAlpha-_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutBeta) && ed3.equals(visinac)) {

            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinac = Double.parseDouble(ed3.getText().toString());
            double _strA = _visinac/Math.sin(_kutBeta);
            double _strB = _visinac/Math.sin(_kutAlpha);
            double _strC = (_strB*Math.sin(Math.PI-_kutAlpha-_kutBeta))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(kutGama) && ed3.equals(strA)) {

            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strA = Double.parseDouble(ed3.getText().toString());

            double _strB = (_strA*Math.sin(_kutBeta))/Math.sin(Math.PI-_kutBeta-_kutGama);
            double _strC = (_strA*Math.sin(_kutGama))/Math.sin(Math.PI-_kutBeta-_kutGama);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(kutGama) && ed3.equals(strB)) {

            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strB = Double.parseDouble(ed3.getText().toString());
            double _t=_strB*Math.sin(Math.PI-_kutBeta-_kutGama);
            double _strA = _t/Math.sin(_kutBeta);
            double _strC = _t/Math.sin(_kutGama);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(kutGama) && ed3.equals(strC)) {

            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strC = Double.parseDouble(ed3.getText().toString());

            double _strB = (_strC*Math.sin(_kutBeta))/Math.sin(_kutGama);
            double _strA = (_strB*Math.sin(Math.PI-_kutBeta-_kutGama))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(kutGama) && ed3.equals(opis)) {

            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _opis = Double.parseDouble(ed3.getText().toString());

            double _strB = 2*_opis*Math.sin(_kutBeta);
            double _strA = 2*_opis*Math.sin(Math.PI-_kutBeta-_kutGama);
            double _strC = 2*_opis*Math.sin(_kutGama);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(kutGama) && ed3.equals(visinaa)) {

            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinaa = Double.parseDouble(ed3.getText().toString());

            double _strB = _visinaa/Math.sin(_kutGama);
            double _strC = _visinaa/Math.sin(_kutBeta);
            double _strA = (_strB*Math.sin(Math.PI-_kutBeta-_kutGama))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(kutGama) && ed3.equals(visinab)) {

            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinab = Double.parseDouble(ed3.getText().toString());

            double _strA = _visinab/Math.sin(_kutGama);
            double _strC = _visinab/Math.sin(Math.PI-_kutBeta-_kutGama);
            double _strB = (_strC*Math.sin(_kutBeta))/Math.sin(_kutGama);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(kutGama) && ed3.equals(visinac)) {

            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinac = Double.parseDouble(ed3.getText().toString());

            double _strA = _visinac/Math.sin(_kutBeta);
            double _strB = _visinac/Math.sin(Math.PI-_kutBeta-_kutGama);
            double _strC = (_strB*Math.sin(_kutGama))/Math.sin(_kutBeta);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutGama) && ed2.equals(povrsina) && ed3.equals(strA)) {

            double _kutGama = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strA = Double.parseDouble(ed3.getText().toString());

            double _strB = ((2*_povrsina)/_strA)/Math.sin(_kutGama);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutGama) && ed2.equals(povrsina) && ed3.equals(strB)) {

            double _kutGama = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strB = Double.parseDouble(ed3.getText().toString());

            double _strA = ((2*_povrsina)/_strB)/Math.sin(_kutGama);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutGama) && ed2.equals(povrsina) && ed3.equals(visinaa)) {

            double _kutGama = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _visinaa = Double.parseDouble(ed3.getText().toString());

            double _strA = ((2*_povrsina)/_visinaa);
            double _strB = _visinaa/Math.sin(_kutGama);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutGama) && ed2.equals(povrsina) && ed3.equals(visinab)) {

            double _kutGama = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _visinab = Double.parseDouble(ed3.getText().toString());

            double _strB = ((2*_povrsina)/_visinab);
            double _strA = _visinab/Math.sin(_kutGama);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinaa) && ed2.equals(visinab) && ed3.equals(strA)) {

            double _visinaa = Double.parseDouble(ed.getText().toString());
            double _visinab = Double.parseDouble(ed2.getText().toString());
            double _strA = Double.parseDouble(ed3.getText().toString());
            double _strB = (_strA*_visinaa*2)/(2*_visinab);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(Math.asin(_visinab/_strB)));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinaa) && ed2.equals(visinab) && ed3.equals(strB)) {

            double _visinaa = Double.parseDouble(ed.getText().toString());
            double _visinab = Double.parseDouble(ed2.getText().toString());
            double _strB = Double.parseDouble(ed3.getText().toString());
            double _strA = (_strB*_visinab*2)/(2*_visinaa);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(Math.asin(_visinab/_strB)));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinaa) && ed2.equals(visinab) && ed3.equals(strC)) {

            double _visinaa = Double.parseDouble(ed.getText().toString());
            double _visinab = Double.parseDouble(ed2.getText().toString());
            double _strC = Double.parseDouble(ed3.getText().toString());
            double _kutBeta = Math.asin(_visinaa/_strC);
            double _kutAlpha = Math.asin(_visinab/_strC);
            double _strA = _visinab/Math.sin(Math.PI-_kutBeta-_kutAlpha);
            double _strB = _visinaa/Math.sin(Math.PI-_kutBeta-_kutAlpha);
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinaa) && ed2.equals(visinab) && ed3.equals(kutAlpha)) {

            double _visinaa = Double.parseDouble(ed.getText().toString());
            double _visinab = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strC = _visinab/Math.sin(_kutAlpha);
            double _kutBeta = Math.asin(_visinaa/_strC);
            double _strB = _visinaa/Math.sin(Math.PI-_kutBeta-_kutAlpha);
            double _strA = _visinab/Math.sin(Math.PI-_kutBeta-_kutAlpha);
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinaa) && ed2.equals(visinab) && ed3.equals(kutBeta)) {

            double _visinaa = Double.parseDouble(ed.getText().toString());
            double _visinab = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strC = _visinaa/Math.sin(_kutBeta);
            double _kutAlpha = Math.asin(_visinab/_strC);
            double _strB = _visinaa/Math.sin(Math.PI-_kutBeta-_kutAlpha);
            double _strA = _visinab/Math.sin(Math.PI-_kutBeta-_kutAlpha);
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinaa) && ed2.equals(visinab) && ed3.equals(kutGama)) {

            double _visinaa = Double.parseDouble(ed.getText().toString());
            double _visinab = Double.parseDouble(ed2.getText().toString());
            double _kutGama = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strB = _visinaa/Math.sin(_kutGama);
            double _strA = _visinab/Math.sin(_kutGama);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinaa) && ed2.equals(visinab) && ed3.equals(povrsina)) {

            double _visinaa = Double.parseDouble(ed.getText().toString());
            double _visinab = Double.parseDouble(ed2.getText().toString());
            double _povrsina = Double.parseDouble(ed3.getText().toString());
            double _strB = (2*_povrsina)/_visinab;
            double _strA = (2*_povrsina)/_visinaa;
            double _kutGama = Math.asin(_visinaa/_strB);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinab) && ed2.equals(visinac) && ed3.equals(strA)) {

            double _visinab = Double.parseDouble(ed.getText().toString());
            double _visinac = Double.parseDouble(ed2.getText().toString());
            double _strA = Double.parseDouble(ed3.getText().toString());
            double _kutBeta =Math.asin(_visinac/_strA);
            double _kutGama = Math.asin(_visinab/_strA);
            double _strC = _visinac / Math.sin(Math.PI-_kutBeta-_kutGama);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinab) && ed2.equals(visinac) && ed3.equals(strB)) {

            double _visinab = Double.parseDouble(ed.getText().toString());
            double _visinac = Double.parseDouble(ed2.getText().toString());
            double _strB = Double.parseDouble(ed3.getText().toString());
            double _strC = (_strB*_visinab*2)/(2*_visinac);
            double _kutAlpha = Math.asin(_visinab/_strC);
            double _strA = Math.sqrt(Math.pow(_strC,2)+Math.pow(_strB,2)-2*_strC*_strB*Math.cos(_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinab) && ed2.equals(visinac) && ed3.equals(strC)) {

            double _visinab = Double.parseDouble(ed.getText().toString());
            double _visinac = Double.parseDouble(ed2.getText().toString());
            double _strC = Double.parseDouble(ed3.getText().toString());
            double _strB = (_strC * _visinac * 2) / (2 * _visinab);
            double _kutAlpha = Math.asin(_visinab / _strC);
            double _strA = Math.sqrt(Math.pow(_strC, 2) + Math.pow(_strB, 2) - 2 * _strC * _strB * Math.cos(_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta, kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA, _strB, _strC, text);
        }
        if (ed.equals(visinab) && ed2.equals(visinac) && ed3.equals(kutAlpha)) {

            double _visinab = Double.parseDouble(ed.getText().toString());
            double _visinac = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strB = _visinac/Math.sin(_kutAlpha);
            double _strC = _visinab/Math.sin(_kutAlpha);
            double _strA = Math.sqrt(Math.pow(_strC,2)+Math.pow(_strB,2)-2*_strC*_strB*Math.cos(_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinab) && ed2.equals(visinac) && ed3.equals(kutBeta)) {

            double _visinab = Double.parseDouble(ed.getText().toString());
            double _visinac = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strA = _visinac/Math.sin(_kutBeta);
            double _kutGama = Math.asin(_visinab/_strA);
            double _strC = _visinab/Math.sin(Math.PI-_kutBeta-_kutGama);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinab) && ed2.equals(visinac) && ed3.equals(kutGama)) {

            double _visinab = Double.parseDouble(ed.getText().toString());
            double _visinac = Double.parseDouble(ed2.getText().toString());
            double _kutGama = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strA = _visinab/Math.sin(_kutGama);
            double _kutBeta = Math.asin(_visinac/_strA);
            double _strC = _visinab/Math.sin(Math.PI-_kutBeta-_kutGama);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinac) && ed2.equals(strA) && ed3.equals(strC)) {

            double _visinac = Double.parseDouble(ed.getText().toString());
            double _strA = Double.parseDouble(ed2.getText().toString());
            double _strC =Double.parseDouble(ed3.getText().toString());
            double _kutBeta = Math.asin(_visinac/_strA);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinac) && ed2.equals(strA) && ed3.equals(opis)) {

            double _visinac = Double.parseDouble(ed.getText().toString());
            double _strA = Double.parseDouble(ed2.getText().toString());
            double _opis = Double.parseDouble(ed3.getText().toString());
            double _kutAlpha = Math.asin(_strA/(2*_opis));
            double _strB = _visinac/Math.sin(_kutAlpha);
            double _kutBeta = Math.asin(_visinac/_strA);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(Math.PI-_kutAlpha-_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinac) && ed2.equals(strA) && ed3.equals(kutAlpha)) {

            double _visinac = Double.parseDouble(ed.getText().toString());
            double _strA = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strB = _visinac/Math.sin(_kutAlpha);
            double _kutBeta = Math.asin(_visinac/_strA);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(Math.PI-_kutAlpha-_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(visinac) && ed2.equals(strA) && ed3.equals(kutGama)) {

            double _visinac = Double.parseDouble(ed.getText().toString());
            double _strA = Double.parseDouble(ed2.getText().toString());
            double _kutGama = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _kutBeta = Math.asin(_visinac/_strA);
            double _strB = _visinac/Math.sin(Math.PI-_kutGama-_kutBeta);
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strC) && ed3.equals(opis)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _opis = Double.parseDouble(ed3.getText().toString());
            double _kutAlpha = Math.asin((2*_opis)/_strA);
            double _kutGama = Math.asin((2*_opis)/_strC);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(Math.PI-_kutGama-_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strC) && ed3.equals(kutAlpha)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _kutGama = Math.asin((_strC*Math.sin(_kutAlpha))/_strA);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(Math.PI-_kutGama-_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strC) && ed3.equals(kutBeta)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strC) && ed3.equals(kutGama)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _kutGama = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _kutAlpha = Math.asin((_strA*Math.sin(_kutGama))/_strC);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(Math.PI-_kutGama-_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strC) && ed3.equals(opseg)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _opseg = Double.parseDouble(ed3.getText().toString());
            double _strB = _opseg - _strA-_strC;
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strC) && ed3.equals(povrsina)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _povrsina = Double.parseDouble(ed3.getText().toString());
            double _visinaa = (2*_povrsina)/_strA;
            double _kutBeta = Math.asin(_visinaa/_strC);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strC) && ed3.equals(visinaa)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _visinaa = Double.parseDouble(ed3.getText().toString());
            double _kutBeta = Math.asin(_visinaa/_strC);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strC) && ed3.equals(visinab)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _visinab = Double.parseDouble(ed3.getText().toString());
            double _kutAlpha = Math.asin(_visinab/_strC);
            double _kutGama = Math.asin(_visinab/_strA);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(Math.PI-_kutGama-_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strA) && ed2.equals(strC) && ed3.equals(visinac)) {

            double _strA = Double.parseDouble(ed.getText().toString());
            double _strC = Double.parseDouble(ed2.getText().toString());
            double _visinac = Double.parseDouble(ed3.getText().toString());
            double _kutBeta = Math.asin(_visinac/_strA);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strC) && ed2.equals(opis) && ed3.equals(kutBeta)) {
            double _strC = Double.parseDouble(ed.getText().toString());
            double _opis = Double.parseDouble(ed2.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed3.getText().toString()));
            double _kutGama = Math.asin(_strC/(2*_opis));
            double _strB = 2*_opis*Math.sin(_kutBeta);
            double _strA = Math.sqrt(Math.pow(_strB,2)+Math.pow(_strC,2)-2*_strB*_strC*Math.cos(Math.PI-_kutBeta-_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }

        if (ed.equals(strC) && ed2.equals(opis) && ed3.equals(visinaa)) {
            double _strC = Double.parseDouble(ed.getText().toString());
            double _opis = Double.parseDouble(ed2.getText().toString());
            double _visinaa =Double.parseDouble(ed3.getText().toString());
            double _kutGama = Math.asin(_strC/(2*_opis));
            double _strB = _visinaa/Math.sin(_kutGama);
            double _kutBeta = Math.asin(_strB/(2*_opis));
            double _strA = Math.sqrt(Math.pow(_strB,2)+Math.pow(_strC,2)-2*_strB*_strC*Math.cos(Math.PI-_kutBeta-_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(strC) && ed2.equals(opis) && ed3.equals(visinab)) {
            double _strC = Double.parseDouble(ed.getText().toString());
            double _opis = Double.parseDouble(ed2.getText().toString());
            double _visinab =Double.parseDouble(ed3.getText().toString());
            double _kutGama = Math.asin(_strC/(2*_opis));
            double _strA = _visinab/Math.sin(_kutGama);
            double _kutAlpha = Math.asin(_strA/(2*_opis));
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(Math.PI-_kutAlpha-_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        } if (ed.equals(opis) && ed2.equals(kutBeta) && ed3.equals(strA)) {
            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strA =Double.parseDouble(ed3.getText().toString());
            double _kutAlpha = Math.asin(_strA/(2*_opis));
            double _strB = Math.sin(_kutBeta)*2*_opis;
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(Math.PI-_kutAlpha-_kutBeta));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }  if (ed.equals(opis) && ed2.equals(kutBeta) && ed3.equals(visinaa)) {
            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinaa =Double.parseDouble(ed3.getText().toString());
            double _strC = _visinaa/Math.sin(_kutBeta);
            double _strB = Math.sin(_kutBeta)*2*_opis;
            double _kutAlpha = Math.PI-_kutBeta-Math.asin(_strC/(2*_opis));
            double _strA = Math.sqrt(Math.pow(_strB,2)+Math.pow(_strC,2)-2*_strB*_strC*Math.cos(_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        } if (ed.equals(opis) && ed2.equals(kutBeta) && ed3.equals(visinac)) {
            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinac =Double.parseDouble(ed3.getText().toString());

            double _strA = _visinac/Math.sin(_kutBeta);
            double _strB = Math.sin(_kutBeta)*2*_opis;
            double _kutAlpha = Math.PI-_kutBeta-Math.asin(_strA/(2*_opis));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(opis) && ed2.equals(kutBeta) && ed3.equals(visinac)) {
            double _opis = Double.parseDouble(ed.getText().toString());
            double _kutBeta = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinac =Double.parseDouble(ed3.getText().toString());

            double _strA = _visinac/Math.sin(_kutBeta);
            double _strB = Math.sin(_kutBeta)*2*_opis;
            double _kutAlpha = Math.PI-_kutBeta-Math.asin(_strA/(2*_opis));
            double _strC = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strB,2)-2*_strA*_strB*Math.cos(_kutAlpha));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutGama) && ed3.equals(strA)) {
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strA =Double.parseDouble(ed3.getText().toString());
            double _strC=(_strA*Math.sin(_kutGama))/Math.sin(_kutAlpha);
            double _strB = Math.sqrt(Math.pow(_strA,2)+Math.pow(_strC,2)-2*_strA*_strC*Math.cos(Math.PI-_kutAlpha-_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutGama) && ed3.equals(strC)) {
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _strC =Double.parseDouble(ed3.getText().toString());
            double _strA=  (_strC*Math.sin(_kutAlpha))/Math.sin(_kutGama);
            double _strB = Math.sqrt(Math.pow(_strC,2)+Math.pow(_strA,2)-2*_strC*_strA*Math.cos(Math.PI-_kutAlpha-_kutGama));
            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutGama) && ed3.equals(visinaa)) {
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinaa =Double.parseDouble(ed3.getText().toString());
            double _strB = _visinaa/Math.sin(_kutGama);
            double _strC = _visinaa/Math.sin(Math.PI-_kutAlpha-_kutGama);
            double _strA=  (_strC*Math.sin(_kutAlpha))/Math.sin(_kutGama);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutGama) && ed3.equals(visinab)) {
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinab =Double.parseDouble(ed3.getText().toString());
            double _strA=  _visinab/Math.sin(_kutGama);
            double _strC = _visinab/Math.sin(_kutAlpha);
            double _strB = (_strC*Math.sin(_kutAlpha))/Math.sin(_kutGama);

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutAlpha) && ed2.equals(kutGama) && ed3.equals(visinac)) {
            double _kutAlpha = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _kutGama = Math.toRadians(Double.parseDouble(ed2.getText().toString()));
            double _visinac =Double.parseDouble(ed3.getText().toString());
            double _strB = _visinac/Math.sin(_kutAlpha);

            double _strA = _visinac/Math.sin(Math.PI-_kutAlpha-_kutGama);
            double _strC = Math.sqrt(Math.pow(_strB,2)+Math.pow(_strA,2)-2*_strB*_strA*Math.cos(_kutGama));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(povrsina) && ed3.equals(strA)) {
            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strA =Double.parseDouble(ed3.getText().toString());
            double _visinaa = 2*_povrsina/_strA;
            double _strC = _visinaa/Math.sin(_kutBeta);
            double _strB = Math.sqrt(Math.pow(_strC,2)+Math.pow(_strA,2)-2*_strC*_strA*Math.cos(_kutBeta));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(povrsina) && ed3.equals(strC)) {
            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _strC =Double.parseDouble(ed3.getText().toString());
            double _visinac = 2*_povrsina/_strC;
            double _strA = _visinac/Math.sin(_kutBeta);
            double _strB = Math.sqrt(Math.pow(_strC,2)+Math.pow(_strA,2)-2*_strC*_strA*Math.cos(_kutBeta));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(povrsina) && ed3.equals(visinaa)) {
            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _visinaa =Double.parseDouble(ed3.getText().toString());
            double _strA = (2*_povrsina)/_visinaa;
            double _strC = _visinaa/Math.sin(_kutBeta);
            double _strB = Math.sqrt(Math.pow(_strC,2)+Math.pow(_strA,2)-2*_strC*_strA*Math.cos(_kutBeta));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }
        if (ed.equals(kutBeta) && ed2.equals(povrsina) && ed3.equals(visinac)) {
            double _kutBeta = Math.toRadians(Double.parseDouble(ed.getText().toString()));
            double _povrsina = Double.parseDouble(ed2.getText().toString());
            double _visinac =Double.parseDouble(ed3.getText().toString());
            double _strC = (2*_povrsina)/_visinac;
            double _strA = _visinac/Math.sin(_kutBeta);
            double _strB = Math.sqrt(Math.pow(_strC,2)+Math.pow(_strA,2)-2*_strC*_strA*Math.cos(_kutBeta));

            resetTextOfFields(strA, strB, strC, opis, upis, kutAlpha, kutBeta ,kutGama, povrsina, opseg, visinaa, visinab, visinac);
            izracunaj(_strA,_strB,_strC,text);
        }












        changeInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL, strA, strB, strC, opis, upis, kutAlpha, kutBeta, kutGama, povrsina, opseg, visinaa, visinab, visinac);
    }

    protected void izracunaj(double _strA, double _strB, double _strC,  String text) {
        //polu opseg
        double s = (_strA+_strB+_strC)/2;
        double _kutAlpha= Math.acos((Math.pow(_strB,2)+Math.pow(_strC,2)-Math.pow(_strA,2))/(2*_strB*_strC));
        double _kutBeta = Math.acos((Math.pow(_strA,2)+Math.pow(_strC,2)-Math.pow(_strB,2))/(2*_strA*_strC));
        double _kutGama = Math.PI - _kutAlpha - _kutBeta;
        double _povrsina = Math.sqrt(s*(s-_strA)*(s-_strB)*(s-_strC));
        double _opseg = _strA+_strB+_strC;
        double _opis = _strA/(2*Math.sin(_kutAlpha));
        double _upis = _povrsina/s;
        double _visinaa = _strB*Math.sin(_kutGama);
        double _visinab = _strA*Math.sin(_kutGama);
        double _visinac = _strA*Math.sin(_kutBeta);

        appendToText(text,_strA,_strB,_strC,_opis,_upis,Math.toDegrees(_kutAlpha),Math.toDegrees(_kutBeta),Math.toDegrees(_kutGama),
                _opseg,_povrsina, _visinaa, _visinab, _visinac);


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

    protected void appendToText(String text, double _strA, double _strB, double _strC, double _opis, double _upis, double _kutAlpha, double _kutBeta, double _kutGama,
                                double _opseg, double _povrsina, double _visinaa, double _visinab, double _visinac) {
        strA.append(String.format(Locale.getDefault(), "%.2f", _strA) + text + " " + getString(R.string.stranica_A));
        strB.append(String.format(Locale.getDefault(), "%.2f", _strB) + text + " " + getString(R.string.stranica_B));
        strC.append(String.format(Locale.getDefault(), "%.2f", _strC) + text + " " + getString(R.string.stranica_C));
        opis.append(String.format(Locale.getDefault(), "%.2f", _opis) + text + " " + getString(R.string.opisana_kruz));
        upis.append(String.format(Locale.getDefault(), "%.2f", _upis) + text + " " + getString(R.string.upisana_kruz));
        kutAlpha.append(String.format(Locale.getDefault(), "%.2f", _kutAlpha) + getString(R.string.stupnjeviznak) + " " + getString(R.string.alpha));
        kutBeta.append(String.format(Locale.getDefault(), "%.2f", _kutBeta) + getString(R.string.stupnjeviznak) + " " + getString(R.string.beta));
        kutGama.append(String.format(Locale.getDefault(), "%.2f", _kutGama) + getString(R.string.stupnjeviznak) + " " + getString(R.string.gama));
        opseg.append(String.format(Locale.getDefault(), "%.2f", _opseg) + text + " " + getString(R.string.opseg));
        povrsina.append(String.format(Locale.getDefault(), "%.2f", _povrsina) + text + getString(R.string.nakvadratznak) + " " + getString(R.string.povrsina));
        visinaa.append(String.format(Locale.getDefault(), "%.2f", _visinaa) + text + " " + getString(R.string.visina_A));
        visinab.append(String.format(Locale.getDefault(), "%.2f", _visinab) + text + " " + getString(R.string.visina_B));
        visinac.append(String.format(Locale.getDefault(), "%.2f", _visinac) + text + " " + getString(R.string.visina_C ));

    }
}