package com.example.allreserves.telas.restaurante.cadastro;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.allreserves.R;
import com.example.allreserves.classes.restaurante.Restaurante;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class CadastroRestaurante2 extends AppCompatActivity {
    private EditText restNome;
    private CheckBox cbDomingo, cbSegunda, cbTerca, cbQuarta, cbQuinta, cbSexta, cbSabado;
    private CheckBox cbCafeManha, cbAlmoco, cbJantar;
    private EditText restCapacidadeMax;
    private String email, senha;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_restaurante2);
        Bundle dados = getIntent().getExtras();

        restNome = findViewById(R.id.restNome);
        restCapacidadeMax = findViewById(R.id.restCapacidadeMax);
        cbDomingo = findViewById(R.id.cbDomingo);
        cbSegunda = findViewById(R.id.cbSegunda);
        cbTerca = findViewById(R.id.cbTerca);
        cbQuarta = findViewById(R.id.cbQuarta);
        cbQuinta = findViewById(R.id.cbQuinta);
        cbSexta = findViewById(R.id.cbSexta);
        cbSabado = findViewById(R.id.cbSabado);
        cbCafeManha = findViewById(R.id.cbCafeManha);
        cbAlmoco = findViewById(R.id.cbAlmoco);
        cbJantar = findViewById(R.id.cbJantar);
        email = dados.getString("email");
        senha = dados.getString("senha");
    }

    public void cadastrarRestaurante(View view) throws ParseException {
        Restaurante restaurante = new Restaurante();

        ArrayList<Boolean> diasSelecionados = verificarDiasSelecionados();
        ArrayList<Boolean> horariosSelecionados = verificarHorariosSelecionados();

        restaurante.setNome(restNome.getText().toString());
        restaurante.setEmail(email);
        restaurante.setSenha(senha);
        restaurante.setDias_funcionamento(diasDeFuncionamento(diasSelecionados));
        restaurante.setHorario_funcionamento(horariosDeFuncionamento(horariosSelecionados));
        restaurante.setCapacidade_maxima(NumberFormat.getInstance().parse(restCapacidadeMax.getText().toString()));

        criarRestaurante(restaurante);
    }

    private void criarRestaurante (Restaurante restaurante){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> hashRestaurante = new HashMap<>();
        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(restaurante.getEmail(), restaurante.getSenha())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();


                            hashRestaurante.put("nome", restaurante.getNome());
                            hashRestaurante.put("dias_funcionamento", restaurante.getDias_funcionamento());
                            hashRestaurante.put("horarios_funcionamento", restaurante.horario_funcionamento);
                            hashRestaurante.put("capacidade_maxima", restaurante.capacidade_maxima);
                            hashRestaurante.put("uid", user.getUid());

                            // Add a new document with a generated ID
                            db.collection("restaurantes")
                                    .add(hashRestaurante)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w(TAG, "Error adding document", e);
                                        }
                                    });
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(CadastroRestaurante2.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        }

        private ArrayList verificarDiasSelecionados() {
            ArrayList<Boolean> diasFuncionamento = new ArrayList<>();
            diasFuncionamento.add(cbDomingo.isChecked());
            diasFuncionamento.add(cbSegunda.isChecked());
            diasFuncionamento.add(cbTerca.isChecked());
            diasFuncionamento.add(cbQuarta.isChecked());
            diasFuncionamento.add(cbQuinta.isChecked());
            diasFuncionamento.add(cbSexta.isChecked());
            diasFuncionamento.add(cbSabado.isChecked());

            return diasFuncionamento;
        }

        private ArrayList diasDeFuncionamento(ArrayList<Boolean> diasSelecionados) {
            ArrayList<String> diasFuncionamento = new ArrayList<>();

            for(int i = 0; i < 7; i++) {
                if(diasSelecionados.get(i) == true) {
                    switch (i){
                        case 0:
                            diasFuncionamento.add("DOMINGO");
                            break;
                        case 1:
                            diasFuncionamento.add("SEGUNDA");
                            break;
                        case 2:
                            diasFuncionamento.add("TERÇA");
                            break;
                        case 3:
                            diasFuncionamento.add("QUARTA");
                            break;
                        case 4:
                            diasFuncionamento.add("QUINTA");
                            break;
                        case 5:
                            diasFuncionamento.add("SEXTA");
                            break;
                        case 6:
                            diasFuncionamento.add("SABADO");
                            break;
                        default:
                            Toast.makeText(CadastroRestaurante2.this, "ID do dia incorreto",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
            return diasFuncionamento;
        }

    private ArrayList verificarHorariosSelecionados() {
        ArrayList<Boolean> horariosFuncionamento = new ArrayList<>();
        horariosFuncionamento.add(cbCafeManha.isChecked());
        horariosFuncionamento.add(cbAlmoco.isChecked());
        horariosFuncionamento.add(cbJantar.isChecked());

        return horariosFuncionamento;
    }

    private ArrayList horariosDeFuncionamento(ArrayList<Boolean> horariosSelecionados) {
        ArrayList<String> horariosFuncionamento = new ArrayList<>();

        for(int i = 0; i < 3; i++) {
            if(horariosSelecionados.get(i) == true) {
                switch (i){
                    case 1:
                        horariosFuncionamento.add("ALMOÇO");
                        break;
                    case 2:
                        horariosFuncionamento.add("JANTAR");
                        break;
                    default:
                        horariosFuncionamento.add("CAFE_DA_MANHA");
                        break;
                }
            }
        }
        return horariosFuncionamento;
    }
}
