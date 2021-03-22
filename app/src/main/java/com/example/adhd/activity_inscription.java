package com.example.adhd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adhd.data.LoginDataSource;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class activity_inscription extends AppCompatActivity {
        EditText person_Name, name, password, email, phone;
        Button register;
        FirebaseAuth fAuth;
        ProgressBar progressBar;
        TextView lien_connex;
    // Choose authentication providers


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        person_Name =findViewById(R.id.PersonName);
        name= findViewById(R.id.Name);
        password= findViewById(R.id.Password);
        email = findViewById(R.id.emailAddress);

        register= findViewById(R.id.button_validation);
        fAuth= FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar2);
        lien_connex = findViewById(R.id.lien_connexion);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail= email.getText().toString().trim();
                String mdp= password.getText().toString().trim();

                if(TextUtils.isEmpty(mail)){
                    email.setError("Email est demandée");
                    return;
                }
                if(TextUtils.isEmpty(mdp)){
                    password.setError("Le mot de passe est demandé");
                    return;
                }
                if(mdp.length()<6){
                    password.setError("plus de 6 caractères");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //Registrer the user to firebase
                fAuth.createUserWithEmailAndPassword(mail,mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(activity_inscription.this, "Utilisateur crée", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), bienvenue.class));

                        }else{
                            Toast.makeText(activity_inscription.this, "Erreur !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
        lien_connex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }


}