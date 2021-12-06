package com.example.allreserves.telas.restaurante.gerenciamento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.allreserves.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class TelaRestaurante extends AppCompatActivity{

    private String uid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_restaurante);

        Bundle dados = getIntent().getExtras();
        uid = dados.getString("uid");

        TextView nomeRestaurante = (TextView)findViewById(R.id.textViewNomeRestaurante);
        TextView descricao = (TextView)findViewById(R.id.text_descricao);
        ImageView imagem = (ImageView)findViewById(R.id.image_restaurante);
        CheckBox cbDomingo, cbSegunda, cbTerca, cbQuarta, cbQuinta, cbSexta, cbSabado;

        cbDomingo = findViewById(R.id.cbDomingo);
        cbSegunda = findViewById(R.id.cbSegunda);
        cbTerca = findViewById(R.id.cbTerca);
        cbQuarta = findViewById(R.id.cbQuarta);
        cbQuinta = findViewById(R.id.cbQuinta);
        cbSexta = findViewById(R.id.cbSabado);
        cbSabado = findViewById(R.id.cbSabado);

        db.collection("restaurantes")
                .whereEqualTo("uid", uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                nomeRestaurante.setText(document.getString("nome"));
                                descricao.setText(document.getString("descricao"));

                                Picasso.with(TelaRestaurante.this).load(document.getString("imagem"))
                                        .into(imagem);

                                String list = document.getString("dias_funcionamento");

                                if(list.contains("SEGUNDA")){
                                    cbSegunda.setChecked(true);
                                }
                                if(list.contains("TERCA")){
                                    cbTerca.setChecked(true);
                                }
                                if(list.contains("QUARTA")){
                                    cbQuarta.setChecked(true);
                                }
                                if(list.contains("QUINTA")){
                                    cbQuinta.setChecked(true);
                                }
                                if(list.contains("SEXTA")){
                                    cbSexta.setChecked(true);
                                }
                                if(list.contains("SABADO")){
                                    cbSabado.setChecked(true);
                                }
                                if(list.contains("DOMINGO")){
                                    cbDomingo.setChecked(true);
                                }

                            }
                        } else {

                        }
                    }
                });
    }

    public void acessarTelaPropriedades(){
        Intent intent = new Intent(this, TelaPropriedades.class);
        // putExtra
        startActivity(intent);
    }
    public void acessarTelaReservas(){
        Intent intent = new Intent(this, TelaReservas.class);
        // putExtra
        startActivity(intent);
    }




}
