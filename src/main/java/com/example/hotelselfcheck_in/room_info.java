package com.example.hotelselfcheck_in;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.Math;
import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class room_info extends AppCompatActivity {

    private Button exit;
    private TextView Rn,tk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_info);

        exit = findViewById(R.id.button4);
        Rn= findViewById(R.id.textView9);
        tk = findViewById(R.id.textView7);

        Random random= new Random();
        int rn=random.nextInt(201)+100;
         Rn.setText(Integer.toString(rn));
        tk.setText("Thankyou, Enjoy your stay, your room no. "+Integer.toString(rn));

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }
}