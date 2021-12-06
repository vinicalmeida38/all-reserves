package com.example.allreserves.telas.restaurante.gerenciamento;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.allreserves.R;
import com.example.allreserves.telas.cliente.listagem.reservas.MinhasReservas;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TelaPropriedades  extends AppCompatActivity{

    private String uid;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_propriedades);
        Bundle dados = getIntent().getExtras();
        uid = dados.getString("uid");
    }

    public void acessarTelaMain(){
        Intent intent = new Intent(this, TelaRestaurante.class);
        // putExtra
        startActivity(intent);
    }
    public void acessarTelaReservas(){
        Intent intent = new Intent(this, TelaReservas.class);
        startActivity(intent);
    }

    public void saveBtn(View view){
        EditText imageLink = (EditText)findViewById(R.id.edtTextImageLink);
        EditText textDesc = (EditText)findViewById(R.id.edtTextDesc);
        TextView nomeRestaurante = (TextView)findViewById(R.id.textViewNomeRestaurante);
        CheckBox cbDomingo, cbSegunda, cbTerca, cbQuarta, cbQuinta, cbSexta, cbSabado;


        cbDomingo = findViewById(R.id.cbDomingo);
        cbSegunda = findViewById(R.id.cbSegunda);
        cbTerca = findViewById(R.id.cbTerca);
        cbQuarta = findViewById(R.id.cbQuarta);
        cbQuinta = findViewById(R.id.cbQuinta);
        cbSexta = findViewById(R.id.cbSexta);
        cbSabado = findViewById(R.id.cbSabado);

        db.collection("restaurantes")
                .whereEqualTo("uid", uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            System.out.println("sucesso");
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {

                        }
                    }
                });


    }


}
