package br.devgabriela.threatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
   ListView listThreats;
   Button btnAddThreat;
   ThreatSQLiteDatabase db;
   ThreatAdapter threatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new ThreatSQLiteDatabase(
                getBaseContext());

        btnAddThreat = findViewById(R.id.btnAddThreat);

        // adapter personalizado
        listThreats = findViewById(R.id.listThreats);
        threatAdapter = new ThreatAdapter(this, R.layout.list_row, db);
        listThreats.setAdapter(threatAdapter);

        btnAddThreat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent (getBaseContext(), AddThreat.class);
                startActivity(it);
            }
        });

        listThreats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent (getBaseContext(), EditThreat.class);
                // envia dados em forma de array para novo contexto
                it.putExtra("id", id);
                startActivity(it);
            }
        });

        listThreats.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                db.removeThreat( threatAdapter.getItem(position));
                threatAdapter.notifyDataSetChanged();
                return true;

            }
        });
    }

}