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
import android.widget.Toast;

import com.example.allreserves.R;
import com.example.allreserves.telas.restaurante.cadastro.CadastroRestaurante2;
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

    private String updateImage;
    private ArrayList<Boolean> diasFuncionamento = new ArrayList<>();
    private CheckBox cbCafeManha, cbAlmoco, cbJantar;
    private CheckBox cbDomingo, cbSegunda, cbTerca, cbQuarta, cbQuinta, cbSexta, cbSabado;
    private EditText restCapacidadeMax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_propriedades);

        Bundle dados = getIntent().getExtras();
        uid = dados.getString("uid");

        TextView nomeRestaurante = (TextView)findViewById(R.id.textViewNomeRestaurante);
        TextView imagem = (TextView)findViewById(R.id.edtTextImageLink);

        cbDomingo = findViewById(R.id.cbDomingo);
        cbSegunda = findViewById(R.id.cbSegunda);
        cbTerca = findViewById(R.id.cbTerca);
        cbQuarta = findViewById(R.id.cbQuarta);
        cbQuinta = findViewById(R.id.cbQuinta);
        cbSexta = findViewById(R.id.cbSabado);
        cbSabado = findViewById(R.id.cbSabado);
        cbCafeManha = findViewById(R.id.cbCafeManha);
        cbAlmoco = findViewById(R.id.cbAlmoco);
        cbJantar = findViewById(R.id.cbJantar);
        restCapacidadeMax = findViewById(R.id.restCapacidadeMax);
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


    public void saveBtn(View view){

        ArrayList<Boolean> diasSelecionados = verificarDiasSelecionados();
        ArrayList<Boolean> horariosSelecionados = verificarHorariosSelecionados();

        db.collection("restaurantes").document(documentoID)
                .update(
                        "image", updateImage,
                        "dias_funcionamento", diasDeFuncionamento(diasSelecionados),
                        "horarios_funcionamento", horariosDeFuncionamento(horariosSelecionados),
                        "capacidade_maxima", restCapacidadeMax.getText().toString()
                );


    }



}
