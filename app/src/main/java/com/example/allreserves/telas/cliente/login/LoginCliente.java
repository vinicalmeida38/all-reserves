package com.example.allreserves.telas.cliente.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allreserves.R;
import com.example.allreserves.telas.cliente.cadastro.CadastroCliente;
import com.example.allreserves.telas.cliente.listagem.restaurantes.ListaRestaurantes;
import com.example.allreserves.telas.restaurante.login.LoginRestaurante;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginCliente extends AppCompatActivity {
    private static final String TAG = "";
    private EditText clienteEmailLogin;
    private EditText clienteSenhaLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);
        clienteEmailLogin = findViewById(R.id.clienteEmailLogin);
        clienteSenhaLogin = findViewById(R.id.clienteSenhaLogin);

        mAuth = FirebaseAuth.getInstance();
    }

    public void acessarCadastroCliente(View view){
        Intent intent = new Intent(this, CadastroCliente.class);
        startActivity(intent);
    }

    private void acessarListaRestaurantes(){
        Intent intent = new Intent(this, ListaRestaurantes.class);
        startActivity(intent);
    }

    public void autenticarCliente(View view){
        String email = clienteEmailLogin.getText().toString();
        String senha = clienteSenhaLogin.getText().toString();

        signUser(email, senha);
    }

    private void signUser(String email,String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            acessarListaRestaurantes();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginCliente.this, "Email ou senha incorretos.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}