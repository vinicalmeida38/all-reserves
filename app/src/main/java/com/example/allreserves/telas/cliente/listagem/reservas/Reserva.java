package com.example.allreserves.telas.cliente.listagem.reservas;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allreserves.R;
import com.example.allreserves.classes.cliente.Cliente;
import com.example.allreserves.classes.cliente.ReservaCliente;
import com.example.allreserves.classes.restaurante.ListaRestaurante;
import com.example.allreserves.telas.cliente.listagem.restaurantes.ListaRestaurantes;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class Reserva extends AppCompatActivity {
    private EditText reservaNome, reservaDoc, reservaDia, reservaHorario, reservaPessoas;
    private String uidRestaurante, uidCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        reservaNome = findViewById(R.id.reservaNome);
        reservaDoc = findViewById(R.id.reservaDocumento);
        reservaDia = findViewById(R.id.reservaDia);
        reservaHorario = findViewById(R.id.reservaHorario);
        reservaPessoas = findViewById(R.id.reservaQtdPessoas);

        Bundle dados = getIntent().getExtras();
        uidRestaurante = dados.getString("uid_restaurante");
    }

    public void efetuarReserva(View view) throws ParseException{
        ReservaCliente reservaCliente = new ReservaCliente();
        reservaCliente.setNome(reservaNome.getText().toString());
        reservaCliente.setDocumento(reservaDoc.getText().toString());
        reservaCliente.setDiaReserva(reservaDia.getText().toString());
        reservaCliente.setHorarioReserva(reservaHorario.getText().toString());
        reservaCliente.setQtdPessoas(NumberFormat.getInstance().parse(reservaPessoas.getText().toString()));

        criarReserva(reservaCliente);
    }

    private void criarReserva(ReservaCliente reserva){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        Map<String, Object> hashReserva = new HashMap<>();

        hashReserva.put("nome", reserva.getNome());
        hashReserva.put("documento", reserva.getDocumento());
        hashReserva.put("dia", reserva.getDiaReserva());
        hashReserva.put("horario", reserva.getHorarioReserva());
        hashReserva.put("quantidade_pessoas", reserva.getQtdPessoas());
        hashReserva.put("uid_cliente", mAuth.getCurrentUser().getUid());
        hashReserva.put("uid_restaurante", uidRestaurante);

        db.collection("reservas")
                .add(hashReserva)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                        Toast.makeText(Reserva.this, "Reserva realizada com sucesso.",
                                Toast.LENGTH_SHORT).show();
                        acessarListaReservas();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void acessarListaReservas(){
        Intent intent = new Intent(this, ListaRestaurantes.class);
        startActivity(intent);
    }
}