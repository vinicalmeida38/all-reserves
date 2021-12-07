package com.example.allreserves.telas.cliente.listagem.reservas;

import static android.service.controls.ControlsProviderService.TAG;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.allreserves.R;
import com.example.allreserves.classes.cliente.ListaReservaCliente;
import com.example.allreserves.classes.restaurante.ListaRestaurante;
import com.example.allreserves.telas.cliente.listagem.adapters.RecyclerAdapter;
import com.example.allreserves.telas.cliente.listagem.adapters.RecyclerAdapterReservas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MinhasReservas extends Fragment {
    private ArrayList<ListaReservaCliente> listaReservas;
    private RecyclerView recyclerView;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();;
    String horarioReserva, nomeRestaurante;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inf = inflater.inflate(R.layout.fragment_minhas_reservas, container, false);
        recyclerView = inf.findViewById(R.id.recyclerView);
        listaReservas = new ArrayList<>();
        setMinhasReservasInfo();
        return inf;
    }

    private void setAdapter(ArrayList<ListaReservaCliente> lista) {
        RecyclerAdapterReservas adapter = new RecyclerAdapterReservas(lista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setMinhasReservasInfo() {
        db.collection("reservas")
                .whereEqualTo("uid_cliente", mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot documentReservas : task.getResult()) {
                                Log.d(TAG, documentReservas.getId() + " => " + documentReservas.getData());
                                db.collection("restaurantes")
                                        .whereEqualTo("uid", documentReservas.getString("uid_restaurante"))
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot documentRestaurantes : task.getResult()) {
                                                        Log.d(TAG, documentRestaurantes.getId() + " => " + documentRestaurantes.getData());
                                                        nomeRestaurante = documentRestaurantes.getString("nome");
                                                        horarioReserva = documentReservas.getString("horario");
                                                        listaReservas.add(new ListaReservaCliente(nomeRestaurante, horarioReserva));
                                                    }
                                                    setAdapter(listaReservas);
                                                } else {
                                                    Log.w(TAG, "Error getting documents.", task.getException());
                                                }
                                            }
                                        });
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}