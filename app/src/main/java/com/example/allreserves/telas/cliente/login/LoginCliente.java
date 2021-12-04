package com.example.allreserves.telas.cliente.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.allreserves.R;
import com.example.allreserves.telas.cliente.cadastro.CadastroCliente;
import com.example.allreserves.telas.restaurante.login.LoginRestaurante;

public class LoginCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cliente);
    }

    public void acessarCadastroCliente(View view){
        Intent intent = new Intent(this, CadastroCliente.class);
        startActivity(intent);
    }
}