package com.example.allreserves.telas.cliente.cadastro;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allreserves.R;
import com.example.allreserves.classes.cliente.Cliente;
import com.example.allreserves.telas.cliente.login.LoginCliente;
import com.example.allreserves.telas.restaurante.login.LoginRestaurante;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CadastroCliente extends AppCompatActivity {
    private EditText clienteNome;
    private EditText clienteDoc;
    private EditText clienteEmail;
    private EditText clienteSenha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cliente);
        clienteNome = findViewById(R.id.restEmail);
        clienteDoc = findViewById(R.id.restSenha);
        clienteEmail = findViewById(R.id.clienteEmail);
        clienteSenha = findViewById(R.id.clienteSenha);
    }

    public void cadastrarCliente(View view) {
        Cliente cliente = new Cliente();

        cliente.setNome(clienteNome.getText().toString());
        cliente.setDocumento(clienteDoc.getText().toString());
        cliente.setEmail(clienteEmail.getText().toString());
        cliente.setSenha(clienteSenha.getText().toString());

        criarCliente(cliente);
    }

    private void acessarLoginCliente(){
        Intent intent = new Intent(this, LoginCliente.class);
        startActivity(intent);
    }

    public void criarCliente(Cliente cliente) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> hashCliente = new HashMap<>();
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(cliente.getEmail(), cliente.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            hashCliente.put("nome", cliente.getNome());
                            hashCliente.put("documento", cliente.getDocumento());
                            hashCliente.put("uid", user.getUid());

                            // Add a new document with a generated ID
                            db.collection("clientes")
                                    .add(hashCliente)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                            Toast.makeText(CadastroCliente.this, "Cadastro realizado com sucesso.",
                                                    Toast.LENGTH_SHORT).show();
                                            acessarLoginCliente();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error adding document", e);
                                        }
                                    });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastroCliente.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}