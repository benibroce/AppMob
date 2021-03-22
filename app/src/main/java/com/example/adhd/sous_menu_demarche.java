package com.example.adhd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sous_menu_demarche extends AppCompatActivity {
    Button lien_fonction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sous_menu_demarche);
        lien_fonction= (Button) findViewById(R.id.fonction);
        lien_fonction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),fonctionnement_demarche.class));
            }
        });
    }
}