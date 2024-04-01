package br.devgabriela.freqmax;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText inputNome, inputIdade;
    private ArrayList<String> atletasList;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando os elementos
        inputNome = findViewById(R.id.inputNome);
        inputIdade = findViewById(R.id.inputIdade);

        // Inicializando a lista de atletas
        atletasList = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, atletasList);
        ListView listaAtletas = findViewById(R.id.listaAtletas);
        listaAtletas.setAdapter(arrayAdapter);

    }
    // Função chamada quando o botão é clicado
    public void adicionarAtleta(View view) {
        // Obtendo os valores dos EditText
        String nomeAtleta = inputNome.getText().toString();
        String idadeAtletaString = inputIdade.getText().toString();

        // Verificando se os campos estão vazios
        if (nomeAtleta.isEmpty() || idadeAtletaString.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convertendo a idade para inteiro
        int idadeAtleta = Integer.parseInt(idadeAtletaString);

        // Construindo o item da lista
        String itemLista = "Atleta: " + nomeAtleta;
        String subItemLista = "FCM: " + (220 - idadeAtleta);

        // Adicionando à lista de atletas e notificar o adapter
        atletasList.add(itemLista + "\n" + subItemLista);
        arrayAdapter.notifyDataSetChanged();

        // Limpando os Inputs
        inputNome.getText().clear();
        inputIdade.getText().clear();
    }

}