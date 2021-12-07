package com.example.allreserves.telas.cliente.listagem.restaurantes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.allreserves.R;
import com.example.allreserves.telas.cliente.listagem.reservas.Reserva;
import com.squareup.picasso.Picasso;

public class DetalheRestaurante extends AppCompatActivity {
    private TextView resultNomeRestaurante, resultDiasFuncionamento, resultHorarioFuncionamento, resultCapacidadeMax;
    private ImageView resultImagem;
    private String uidRestaurante, nome, imagem, horarioFuncionamento, diasFuncionamento;
    private Number capacidadeMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_restaurante);

        resultNomeRestaurante = findViewById(R.id.resultNomeRestaurante);
        resultImagem = findViewById(R.id.resultImagem);
        resultDiasFuncionamento = findViewById(R.id.resultDiasFuncionamento);
        resultHorarioFuncionamento = findViewById(R.id.resultHorarioFuncioinamento);
        resultCapacidadeMax = findViewById(R.id.resultCapacidadeMax);

        preencherDadosRestaurantes();
    }

    private void preencherDadosRestaurantes(){
        Bundle dados = getIntent().getExtras();
        uidRestaurante = dados.getString("uid_restaurante");
        nome = dados.getString("nome");
        imagem = dados.getString("imagem");
        capacidadeMax = dados.getLong("capacidade_max");
        diasFuncionamento = dados.getString("dias_funcionamento");
        horarioFuncionamento = dados.getString("horario_funcionamento");

        resultNomeRestaurante.setText(nome);
        Picasso.with(getBaseContext()).load(imagem).into(resultImagem);
        resultCapacidadeMax.setText(capacidadeMax.toString());
        resultDiasFuncionamento.setText(diasFuncionamento);
        resultHorarioFuncionamento.setText(horarioFuncionamento);
    }

    public void acessarCadastroReserva(View view){
        Intent intent = new Intent(this, Reserva.class);
        intent.putExtra("uid_restaurante", uidRestaurante);
        startActivity(intent);
    }
}