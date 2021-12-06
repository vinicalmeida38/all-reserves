package com.example.allreserves.telas.cliente.listagem.adapters;

import android.content.Intent;
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

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private ArrayList<ListaRestaurante> restaurantesList;

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
        String nome = restaurantesList.get(position).getNome();
        holder.nomeRestaurante.setText(nome);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), DetalheRestaurante.class);
                    view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return restaurantesList.size();
    }
}
