package com.example.allreserves.telas.restaurante.gerenciamento;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
import java.util.HashMap;
import java.util.Map;

public class TelaPropriedades  extends AppCompatActivity{

    private String uid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private String documentoID;

    String updateImage;
    String updateDescricao;
    ArrayList<Boolean> diasFuncionamento = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_propriedades);

        Bundle dados = getIntent().getExtras();
        uid = dados.getString("uid");


        TextView nomeRestaurante = (TextView)findViewById(R.id.textViewNomeRestaurante);
        TextView descricao = (TextView)findViewById(R.id.edtTextDesc);
        TextView imagem = (TextView)findViewById(R.id.edtTextImageLink);
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
                                documentoID = document.getId();

                                nomeRestaurante.setText(document.getString("nome"));
                                descricao.setText(document.getString("descricao"));
                                updateDescricao = document.getString("descricao");
                                imagem.setText(document.getString("imagem"));
                                updateImage = document.getString("imagem");

                                String list = document.getString("dias_funcionamento");


                                if(list.contains("DOMINGO")){
                                    cbDomingo.setChecked(true);
                                    diasFuncionamento.add(cbDomingo.isChecked());
                                }
                                if(list.contains("SEGUNDA")){
                                    cbSegunda.setChecked(true);
                                    diasFuncionamento.add(cbSegunda.isChecked());
                                }
                                if(list.contains("TERCA")){
                                    cbTerca.setChecked(true);
                                    diasFuncionamento.add(cbTerca.isChecked());
                                }
                                if(list.contains("QUARTA")){
                                    cbQuarta.setChecked(true);
                                    diasFuncionamento.add(cbQuarta.isChecked());
                                }
                                if(list.contains("QUINTA")){
                                    cbQuinta.setChecked(true);
                                    diasFuncionamento.add(cbQuinta.isChecked());
                                }
                                if(list.contains("SEXTA")){
                                    cbSexta.setChecked(true);
                                    diasFuncionamento.add(cbSexta.isChecked());
                                }
                                if(list.contains("SABADO")){
                                    cbSabado.setChecked(true);
                                    diasFuncionamento.add(cbSabado.isChecked());
                                }

                            }
                        } else {

                        }
                    }
                });


    }

    public void acessarTelaMain(){
        int x=0;
        Intent intent = new Intent(this, TelaRestaurante.class);
        // putExtra
        intent.putExtra("",x);
        startActivity(intent);
    }
    public void acessarTelaReservas(){
        int x=0;
        Intent intent = new Intent(this, TelaReservas.class);
        intent.putExtra("",x);
        startActivity(intent);
    }

    public void saveBtn(View view){

        db.collection("restaurantes").document(documentoID)
                .update(
                        "image", updateImage,
                        "descricao", updateDescricao,
                        "dias_funcionamento", diasFuncionamento
                );


    }



}
