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
import com.example.allreserves.classes.cliente.ListaReservaCliente;
import com.example.allreserves.classes.restaurante.ListaRestaurante;
import com.example.allreserves.telas.cliente.listagem.restaurantes.DetalheRestaurante;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterReservas extends RecyclerView.Adapter<RecyclerAdapterReservas.MyViewHolder> {
    private ArrayList<ListaReservaCliente> reservasLista;

    public RecyclerAdapterReservas(ArrayList<ListaReservaCliente> reservasLista){
        this.reservasLista = reservasLista;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nomeRestaurante;
        private TextView horarioReserva;

        public MyViewHolder(final View view){
            super(view);
            nomeRestaurante = view.findViewById(R.id.nomeRestauranteReserva);
            horarioReserva = view.findViewById(R.id.horarioReserva);
        }
    }

    @NonNull
    @Override
    public RecyclerAdapterReservas.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_reservas_element, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterReservas.MyViewHolder holder, int position) {
        String nome = reservasLista.get(position).getNomeRestaurante();
        String horarioReserva = reservasLista.get(position).getHorarioReserva();

        holder.nomeRestaurante.setText(nome);
        holder.horarioReserva.setText(horarioReserva);
    }

    @Override
    public int getItemCount() {
        return reservasLista.size();
    }
}
