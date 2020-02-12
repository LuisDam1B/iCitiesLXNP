package com.example.icitieslxnp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    FirebaseDatabase database;

    Button btSingIn, btSingOUT;
    EditText userET, passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btSingIn = findViewById(R.id.signIn_Button);
        btSingOUT = findViewById(R.id.singOut_Button);

        userET = findViewById(R.id.user_EditText);
        passwordET = findViewById(R.id.password_EditText);


        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        ////Escuchador de cambios en los usuarios
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this, user.getEmail() + " LOGEADO", Toast.LENGTH_SHORT).show();
                    mAuth = firebaseAuth;
                } else
                    Toast.makeText(MainActivity.this, "Usuario NULO", Toast.LENGTH_SHORT).show();
            }
        };

        //Iniciar session con usuario y contraseña

        btSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(userET.getText().toString(),passwordET.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(MainActivity.this,"Authentificated Failed : " + task.getException(),Toast.LENGTH_SHORT).show();
                                }else {

                                    Intent internalActivityIntent = new Intent(MainActivity.this,InternalActivity.class);
                                    MainActivity.this.startActivity(internalActivityIntent);

                                    Toast.makeText(MainActivity.this,"Autentificacion Correcta!!! usuario: " +
                                            task.getResult().getUser().getEmail().split("@")[0],Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null){
            mAuth.removeAuthStateListener(mAuthListener);
            mAuth.signOut();
        }
    }
}
