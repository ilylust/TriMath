package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class GeometrijskiLikoviMenu extends AppCompatActivity {

    ImageView kvadrat, pravokutnik, trokut, krug;
    SimplifiedToast st = new SimplifiedToast();
    Button btninf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        kvadrat = findViewById(R.id.imageView);
        pravokutnik = findViewById(R.id.imageView2);
        krug = findViewById(R.id.imageView3);
        trokut = findViewById(R.id.imageView4);



        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        // prikaz informacija..
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(GeometrijskiLikoviMenu.this);
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

        kvadrat.setOnClickListener(v -> {
            try {
                openActivity(KvadratActivity.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

        pravokutnik.setOnClickListener(v -> {
            try {
                openActivity(PravokutnikActivity.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

        krug.setOnClickListener(v -> {
            try {
                openActivity(KrugActivity.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

        trokut.setOnClickListener(v -> {
            try {
                openActivity(TrokutMenu.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

    }

    private void openActivity(Class<?> _class) {
        Intent intent = new Intent(this, _class);
        startActivity(intent);
    }
}