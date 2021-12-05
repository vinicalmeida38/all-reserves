package com.example.allreserves.telas.cliente.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.allreserves.R;
import com.example.allreserves.classes.cliente.Cliente;
import com.example.allreserves.telas.cliente.cadastro.CadastroCliente;
import com.example.allreserves.telas.cliente.listagem.reservas.MinhasReservas;
import com.example.allreserves.telas.restaurante.login.LoginRestaurante;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginCliente extends AppCompatActivity {

    private static final String TAG = "";
    private FirebaseAuth mAuth;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);

        mAuth = FirebaseAuth.getInstance();
    }

    public void acessarCadastroCliente(View view){
        Intent intent = new Intent(this, CadastroCliente.class);
        startActivity(intent);
    }

    private void acessarListaRestaurantes(){
        Intent intent = new Intent(this, MinhasReservas.class);
        startActivity(intent);
    }

    public void loginCliente(View view){
        EditText edt1 = (EditText)findViewById(R.id.editTextTextEmailAddress);
        EditText edt2 = (EditText)findViewById(R.id.editTextTextPassword);

        String email = edt1.getText().toString();
        String pass = edt2.getText().toString();

        cliente.setEmail(email);
        cliente.setSenha(pass);

        signUser(cliente.getEmail(),cliente.getSenha());
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

                        }
                    }
                });
    }


}