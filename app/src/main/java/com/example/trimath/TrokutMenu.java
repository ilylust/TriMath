package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class TrokutMenu extends AppCompatActivity {

    ImageView trokut1,trokut2,trokut4;
    SimplifiedToast st = new SimplifiedToast();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trokut_menu);

        trokut1 = findViewById(R.id.jedstr);
        trokut2 = findViewById(R.id.razstr);
        //trokut3 = findViewById(R.id.jedkrac); ---> nije u funkciji
        trokut4 = findViewById(R.id.pravokut);

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        //Jednakostranicni trokut
        trokut1.setOnClickListener(v -> {
            try {
                openActivity(JednakoStranicnActivity.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });

        //Raznostranicni trokut
        trokut2.setOnClickListener(v -> {
            try {
                openActivity(RaznoStranicniActivity.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });
        //Jednakokračni trokut
        //u budućoj verziji
        /*trokut3.setOnClickListener(v -> {
            try {
                openActivity(JednakoKracniAcitvity.class);
            } catch (Exception ex) {
                st.toastShort(this, "ERR");
            }
        });*/
        //Pravokutni trokut
        trokut4.setOnClickListener(v -> {
            try {
                openActivity(PravokutniActivity.class);
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