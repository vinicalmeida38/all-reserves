package com.example.allreserves.telas.cliente.listagem.restaurantes;

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
import com.example.allreserves.classes.cliente.Cliente;
import com.example.allreserves.classes.restaurante.ListaRestaurante;
import com.example.allreserves.telas.cliente.listagem.adapters.RecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Restaurantes extends Fragment {
    private ArrayList<ListaRestaurante> listaRestaurantes;
    private RecyclerView recyclerView;
    String nomeRest, imagemRest, uidRest;
    Number capacidadeMaxRest;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inf = inflater.inflate(R.layout.fragment_restaurantes, container, false);
        recyclerView = inf.findViewById(R.id.recyclerView);
        listaRestaurantes = new ArrayList<>();
        setRestauranteInfo();
        return inf;
    }

    private void setAdapter(ArrayList<ListaRestaurante> lista) {
        RecyclerAdapter adapter = new RecyclerAdapter(lista);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setRestauranteInfo() {
        db.collection("restaurantes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                nomeRest = document.getString("nome");
                                imagemRest = document.getString("imagem");
                                capacidadeMaxRest = document.getLong("capacidade_maxima");
                                uidRest = document.getString("uid");
                                List<String> diasFuncionamentoRest = (List<String>) document.get("dias_funcionamento");
                                List<String> horarioFuncionamentoRest = (List<String>) document.get("horarios_funcionamento");

                                listaRestaurantes.add(new ListaRestaurante(nomeRest, imagemRest, capacidadeMaxRest, diasFuncionamentoRest, horarioFuncionamentoRest, uidRest));
                            }
                            setAdapter(listaRestaurantes);
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }
}