package com.example.allreserves.telas.cliente.listagem.adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.allreserves.R;
import com.example.allreserves.classes.restaurante.ListaRestaurante;
import com.example.allreserves.telas.cliente.listagem.restaurantes.DetalheRestaurante;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<ListaRestaurante> restaurantesList;
    List<String> diasFuncionamentoManipuladas = new ArrayList<>();
    List<String> horariosFuncionamentoManipuladas = new ArrayList<String>();

    public RecyclerAdapter(ArrayList<ListaRestaurante> restaurantesList){
        this.restaurantesList = restaurantesList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nomeRestaurante;

        public MyViewHolder(final View view){
            super(view);
            nomeRestaurante = view.findViewById(R.id.nomeRestaurante);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_element, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {
        String uidRestaurante = restaurantesList.get(position).getUid();
        String nome = restaurantesList.get(position).getNome();
        String imagem = restaurantesList.get(position).getImagem();
        Number capacidadeMax = restaurantesList.get(position).getCapacidade_max();
        List<String> diasFuncionamento = restaurantesList.get(position).getDias_funcionamento();
        List<String> horarioFuncionamento = restaurantesList.get(position).getHorario_funcionamento();

        holder.nomeRestaurante.setText(nome);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    manipularDiasFuncionamento(diasFuncionamento);
                    manipularHorariosFuncionamento(horarioFuncionamento);

                    Intent intent = new Intent(view.getContext(), DetalheRestaurante.class);
                    intent.putExtra("uid_restaurante", uidRestaurante);
                    intent.putExtra("nome", nome);
                    intent.putExtra("imagem", imagem);
                    intent.putExtra("capacidade_max", capacidadeMax);
                    intent.putExtra("dias_funcionamento", diasFuncionamentoManipuladas.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                    intent.putExtra("horario_funcionamento", horariosFuncionamentoManipuladas.toString().replaceAll("\\[", "").replaceAll("\\]", ""));
                    view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantesList.size();
    }

    public void manipularDiasFuncionamento(List<String> lista){
        diasFuncionamentoManipuladas.clear();
            for(String dia : lista){
                switch (dia){
                    case "DOMINGO":
                        diasFuncionamentoManipuladas.add("Domingo");
                        break;
                    case "SEGUNDA":
                        diasFuncionamentoManipuladas.add("Segunda-feira");
                        break;
                    case "TERÇA":
                        diasFuncionamentoManipuladas.add("Terça-feira");
                        break;
                    case "QUARTA":
                        diasFuncionamentoManipuladas.add("Quarta-feira");
                        break;
                    case "QUINTA":
                        diasFuncionamentoManipuladas.add("Quinta-feira");
                        break;
                    case "SEXTA":
                        diasFuncionamentoManipuladas.add("Sexta-feira");
                        break;
                    case "SABADO":
                        diasFuncionamentoManipuladas.add("Sábado");
                        break;
                }
            }
    }

    private void manipularHorariosFuncionamento(List<String> lista){
        horariosFuncionamentoManipuladas.clear();
        for(String horarios : lista){
            switch (horarios){
                case "CAFE_DA_MANHA":
                    horariosFuncionamentoManipuladas.add("Café da manhã");
                    break;
                case "ALMOÇO":
                    horariosFuncionamentoManipuladas.add("Almoço");
                    break;
                case "JANTAR":
                    horariosFuncionamentoManipuladas.add("Jantar");
                    break;
            }
        }
    }
}
