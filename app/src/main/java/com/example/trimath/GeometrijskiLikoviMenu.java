package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class GeometrijskiLikoviMenu extends AppCompatActivity {

    ImageView kvadrat, pravokutnik, trokut, krug;
    SimplifiedToast st = new SimplifiedToast();
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