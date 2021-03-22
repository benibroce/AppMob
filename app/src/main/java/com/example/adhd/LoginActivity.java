package com.example.adhd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    Button connexion;
    TextView new_account,forget_password;
    FirebaseAuth fAuth;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        password= findViewById(R.id.editPassword);
        email = findViewById(R.id.editEmailAddress);
        progressBar = findViewById(R.id.progressBar3);
        fAuth =FirebaseAuth.getInstance();
        connexion = findViewById(R.id.button_connexion);
        new_account =findViewById(R.id.text_connexion);
        forget_password = findViewById(R.id.mdp_oubliee);

        new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),activity_inscription.class));
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();
                String mdp = password.getText().toString().trim();

                if (TextUtils.isEmpty(mail)) {
                    email.setError("Email est demandée");
                    return;
                }
                if (TextUtils.isEmpty(mdp)) {
                    password.setError("Le mot de passe est demandé");
                    return;
                }
                if (mdp.length() < 6) {
                    password.setError(" le mot de passe doit être  => 6 caractères");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                //authentifier user
                fAuth.signInWithEmailAndPassword(mail,mdp).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Utilisateur connecté", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), bienvenue.class));
                        }else{
                            Toast.makeText(LoginActivity.this, "Erreur !"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }

                    }
                });
            }

        });
        forget_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail= new EditText(v.getContext());
                AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(v.getContext());
                passwordResetDialog.setTitle("Réinitialiser le mot de passe ? ");
                passwordResetDialog.setMessage("Entrez votre email pour réinitialiser votre mot de passe");
                passwordResetDialog.setView(resetMail);
                passwordResetDialog.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail= resetMail.getText().toString();
                        fAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this, "Entrez votre email pour réinitialiser votre mot de passe", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"Erreur! la réinitialisation n'a pas été envoyé"+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
                passwordResetDialog.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResetDialog.create().show();
            }
        });
    }
}