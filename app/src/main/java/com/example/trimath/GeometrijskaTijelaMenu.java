package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class GeometrijskaTijelaMenu extends AppCompatActivity {
    Button btn, btn2, btn3, btn4;
    SimplifiedToast st = new SimplifiedToast();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geometrijska_tijela_menu);

        btn = findViewById(R.id.button);
        btn2 = findViewById(R.id.button2);
        btn3 = findViewById(R.id.button3);
        btn4 = findViewById(R.id.button4);

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        btn.setOnClickListener(v -> {
            try {
                openActivity(KockaActivity.class);
            } catch (Exception e) {
                st.toastShort(this, "ERR");
            }
        });

        btn2.setOnClickListener(v -> {
            try {
                openActivity(KuglaActivity.class);
            } catch (Exception e) {
                st.toastShort(this, "ERR");
            }
        });

        btn3.setOnClickListener(v -> {
            try {
                openActivity(ValjakActivity.class);
            } catch (Exception e) {
                st.toastShort(this, "ERR");
            }
        });

        btn4.setOnClickListener(v -> {
            try {
                openActivity(KockaActivity.class);
            } catch (Exception e) {
                st.toastShort(this, "ERR");
            }
        });
    }

    private void openActivity(Class<?> _class) {
        Intent intent = new Intent(this, _class);
        startActivity(intent);
    }
}