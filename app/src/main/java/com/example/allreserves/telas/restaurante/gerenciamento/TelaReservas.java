package com.example.allreserves.telas.restaurante.gerenciamento;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.allreserves.R;
import com.example.allreserves.telas.restaurante.cadastro.CadastroRestaurante;

public class TelaReservas extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_reservas);
    }

    public void telaRestaurante(View view){
        int x=0;
        Intent intent = new Intent(this, TelaRestaurante.class);
        intent.putExtra("",x);
        startActivity(intent);
    }

    public void telaPropriedades(View view){
        int x=0;
        Intent intent = new Intent(this, TelaPropriedades.class);
        intent.putExtra("",x);
        startActivity(intent);
    }
}
