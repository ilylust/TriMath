package com.example.trimath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class TrokutMenu extends AppCompatActivity {

    ImageView trokut1,trokut2, trokut3;
    SimplifiedToast st = new SimplifiedToast();
    Button btninf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ugasi dark mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trokut_menu);

        trokut1 = findViewById(R.id.jedstr);
        trokut2 = findViewById(R.id.razstr);
        trokut3 = findViewById(R.id.pravokut);

        // Deklaracija i inicijalizacija navigacijske trake
        Toolbar tb = findViewById(R.id.toolbar);
        // Inicijalizacija slušatelja događaja
        tb.setNavigationOnClickListener(v -> {
            // Zatvori trenutačnu aktivnost i vrati se na početnu ako postoji
            finish();
        });

        // prikaz informacija..
        AlertDialog.Builder dialog1 = new AlertDialog.Builder(TrokutMenu.this);
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
        //Pravokutni trokut
        trokut3.setOnClickListener(v -> {
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