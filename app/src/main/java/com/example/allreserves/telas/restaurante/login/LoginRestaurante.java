package com.example.allreserves.telas.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.allreserves.R;
import com.example.allreserves.telas.cliente.login.LoginRestaurante;
import com.example.allreserves.telas.restaurante.login.LoginRestaurante;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void acessarLoginRestaurante(View view){
        Intent intent = new Intent(this, LoginRestaurante.class);
        startActivity(intent);
    }

    public void acessarLoginCliente(View view){
        Intent intent = new Intent(this, com.example.allreserves.telas.cliente.login.LoginRestaurante.class);
        startActivity(intent);
    }
}
