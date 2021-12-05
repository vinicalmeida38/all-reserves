package com.example.allreserves.telas.restaurante.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.allreserves.R;
import com.example.allreserves.classes.restaurante.Restaurante;

public class CadastroRestaurante extends AppCompatActivity {
    private EditText restEmail;
    private EditText restSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_restaurante);
        restEmail = findViewById(R.id.restEmail);
        restSenha = findViewById(R.id.restSenha);
    }

    public void continuarCadastro(View view){
        Restaurante restaurante = new Restaurante();
        restaurante.setEmail(restEmail.getText().toString());
        restaurante.setSenha(restSenha.getText().toString());

        Intent intent = new Intent(this, CadastroRestaurante2.class);
        intent.putExtra("email", restaurante.getEmail());
        intent.putExtra("senha", restaurante.getSenha());

        startActivity(intent);
    }
}