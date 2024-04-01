package br.devgabriela.threatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import android.util.Log;
import java.util.List;
import br.devgabriela.threatsapp.R;
import br.devgabriela.threatsapp.Threat;
import br.devgabriela.threatsapp.ThreatSQLiteDatabase;

public class MainActivity extends AppCompatActivity {
   ListView listThreats;
   Button btnAddThreat;
   ThreatSQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new ThreatSQLiteDatabase(
                getBaseContext());

        Threat s = new Threat();
        s.setAddress("Teste");
        s.setDate("01/04/2024");
        s.setDescription("teste sql");
        Log.d("ADD_INFO", db.addThreat(s).toString());
        List<Threat> ss = db.getThreats();
        for(Threat as:ss){
            Log.d("LST_INFO", as.toString());
        }

        s = db.getThreat(new Long(1));
        s.setAddress("Teste abc");
        Log.d("UPD_INFO", s.toString());
        Log.d("SIZ_INFO", db.getThreats().size() + "");


        btnAddThreat = findViewById(R.id.btnAddThreat);
        btnAddThreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (getBaseContext(), AddThreat.class);
                startActivity(it);
            }
        });


        // lista buscada
        listThreats = findViewById(R.id.listThreats);

        // gerando dados estáticos
        ArrayList<Threat> arrayList = new ArrayList<Threat>();
        arrayList.add(new Threat(1L, "Amazonas", "05/03/2024", "Corte irregular de vegetação nativa"));
        arrayList.add(new Threat(2L, "Brasil", "11/03/2024", "Pesca predatória com rede de arrasto"));
        arrayList.add(new Threat(3L, "Avenida Brasil", "12/03/2024", "Derrame químico em sistema de esgotamento pluvial"));
        arrayList.add(new Threat(4L, "Hospital Santo Leonino", "14/03/2024", "Descarte irregular de lixo hospitalar"));

        // adapter personalizado
        ThreatAdapter threatAdapter = new ThreatAdapter(this, R.layout.list_row, arrayList);
        listThreats.setAdapter(threatAdapter);
        listThreats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent it = new Intent (getBaseContext(), EditThreat.class);
                    // envia dados em forma de array para novo contexto
                    it.putExtra("id", arrayList.get(position).getId());
                    it.putExtra("description", arrayList.get(position).getDescription());
                    it.putExtra("date", arrayList.get(position).getDate());
                    it.putExtra("address", arrayList.get(position).getAddress());
                    startActivity(it);
            }
        });
    }

}