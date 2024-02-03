package com.example.trimath;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class glavni_izbornik extends AppCompatActivity {

    SimplifiedToast st = new SimplifiedToast();
    Button btn, btn2, btn3, btn4, btn5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glavni_izbornik);
        btn = findViewById(R.id.button4);
        btn2 = findViewById(R.id.button5);
        btn3 = findViewById(R.id.button6);
        btn4 = findViewById(R.id.button7);
        btn5 = findViewById(R.id.button8);

        // koristi se za iskočni prozorčić za potvrdu izlaza iz aplikacije
        AlertDialog.Builder builder = new AlertDialog.Builder(glavni_izbornik.this);
        builder.setMessage("Želite li izaći iz aplikacije?")
                .setCancelable(true)
                .setPositiveButton("Da", (dialog, id) -> finish())
                .setNegativeButton("Ne", (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> alert.show());
        // otvori meni za geo likove
        btn.setOnClickListener(v -> {
            try {
                openActivity(GeometrijskiLikoviMenu.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });
        // meni za geo tijela
        btn2.setOnClickListener(v -> {
            try {
                openActivity(GeometrijskaTijelaMenu.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });
        // prozor za računanje postotka
        btn3.setOnClickListener(v -> {
            try {
                openActivity(PostotakActivity.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });
        // meni za račune s jednadžbama
        btn4.setOnClickListener(v -> {
            try {
                openActivity(JednadzbeMenuActivity.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });
        // prozor za računanje proporcionalnosti
        btn5.setOnClickListener(v -> {
            try {
                openActivity(ProporcionalnostActivity.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });
        // kad se klikne back button na glavnom meniju/izborniku da se
        // prikaže poruka jesu li sigurni žele li izaći il ne
        OnBackPressedCallback bCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                alert.show();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, bCallback);
    }

    private void openActivity(Class<?> _class) {
        Intent intent = new Intent(this, _class);
        startActivity(intent);
    }
}