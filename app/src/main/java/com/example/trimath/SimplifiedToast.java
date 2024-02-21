package com.example.trimath;

import android.content.Context;
import android.widget.Toast;

public class SimplifiedToast {
    // prazan konstruktor
    public SimplifiedToast() {
    }
    // metoda za prikaz poruka unutar aplikacije
    public void toastShort(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public void toastLong(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
