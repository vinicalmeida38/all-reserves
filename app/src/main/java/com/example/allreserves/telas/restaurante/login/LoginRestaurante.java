package com.example.allreserves.telas.restaurante.login;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allreserves.R;
import com.example.allreserves.classes.restaurante.Restaurante;
import com.example.allreserves.telas.restaurante.cadastro.CadastroRestaurante2;
import com.example.allreserves.telas.restaurante.gerenciamento.TelaPropriedades;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginRestaurante extends AppCompatActivity {
    private EditText restEmailLogin;
    private EditText restSenhaLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_restaurante);
        restEmailLogin = findViewById(R.id.restEmailLogin);
        restSenhaLogin = findViewById(R.id.restSenhaLogin);

        mAuth = FirebaseAuth.getInstance();
    }

    private void acessarPropsRestaurantes() {
        Intent intent = new Intent(this, TelaPropriedades.class);
        startActivity(intent);
    }

    public void autenticarRestaurante(View view){
        String email = restEmailLogin.getText().toString();
        String senha = restSenhaLogin.getText().toString();
        signUser(email, senha);
    }


    private void signUser(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            acessarPropsRestaurantes();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginRestaurante.this, "Email ou senha incorretos.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}