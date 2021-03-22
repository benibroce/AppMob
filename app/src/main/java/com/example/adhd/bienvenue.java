package com.example.adhd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;

public class bienvenue extends AppCompatActivity {
    ImageView logout;
    Button lien_page_tdah, lien_mieuxVivre, lien_demarches, lien_en_parler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bienvenue);
        lien_en_parler=(Button) findViewById(R.id.btn_enparler);
        logout = (ImageView) findViewById(R.id.imageView2);
        lien_demarches =(Button) findViewById(R.id.page_demarches);
        lien_page_tdah = (Button) findViewById(R.id.page_tdah);
        lien_mieuxVivre = (Button)findViewById(R.id.pageConseils);
        lien_en_parler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),sous_menu_en_parler.class));
            }
        });
        lien_demarches.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),sous_menu_demarche.class));
            }
        });
        lien_page_tdah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),explications.class));
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                }
        });
        lien_mieuxVivre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MieuxVivreSonTDAH.class));
            }
        });

    }

}
