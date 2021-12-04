package com.example.allreserves.telas.restaurante.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.allreserves.R;

public class CadastroRestaurante extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_restaurante);
    }

    public void continuarCadastro(View view){
        Intent intent = new Intent(this, CadastroRestaurante2.class);
        startActivity(intent);
    }
}