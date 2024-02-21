package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class JednadzbeMenuActivity extends AppCompatActivity {
    Button btn, btn2, btn3, btninf;
    SimplifiedToast st = new SimplifiedToast();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jednadzbe_menu);
        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        btn.setOnClickListener(v -> {
            try {
                // Linearna
                openActivity(LinearneJednadzbe.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

        btn2.setOnClickListener(v -> {
            try {
                // Kvadratna
                openActivity(KvadratneJednadzbe.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

        btn3.setOnClickListener(v -> {
            try {
                // Sustavi jedn
                openActivity(SustaviJednadzbi.class);
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