package br.devgabriela.appthreatsfirebase;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
//import com.firebase.ui.database.FirebaseListOptions;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity {
    public static final String THREATS_KEY = "threats";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference threats = root.child(THREATS_KEY);
    FirebaseListAdapter<Threat> listAdapter;
    ListView listThreat;

    @Override
    protected void onStart() {
        super.onStart();
        listAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        listAdapter.stopListening();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       listThreat = findViewById(R.id.listThreats);

        // Crie as opções para o adaptador FirebaseListAdapter
       FirebaseListOptions<Threat> options = new FirebaseListOptions.Builder<Threat>()
               .setLayout(R.layout.threat_list_row)
                .setQuery(threats.limitToLast(50), Threat.class)
                .build();

        // Inicialize o adaptador FirebaseListAdapter com as opções criadas
        listAdapter = new FirebaseListAdapter<Threat>(options) {
            @Override
            protected void populateView(@NonNull View v, @NonNull Threat model, int position) {
                TextView txtAddress = v.findViewById(R.id.txtAddress);
                TextView txtDate = v.findViewById(R.id.txtDate);

                txtAddress.setText(model.getAddress());
                txtDate.setText(model.getDate());

            }
        };

        listThreat.setAdapter(listAdapter);

        listThreat.setOnItemLongClickListener((parent, view, position, id) -> {
            DatabaseReference item = listAdapter.getRef(position);
            item.removeValue();
            return false;
        });

        listThreat.setOnItemClickListener(((parent, view, position, id) -> {
            DatabaseReference item = listAdapter.getRef(position);
            changeToUpdate(item.getKey(), listAdapter.getItem(position));
        }));
    }

    public void changeToAdd(View v){
        Intent it = new Intent(getBaseContext(), br.devgabriela.appthreatsfirebase.AddThreat.class);
        startActivity(it);
    }

    public void changeToUpdate(String key, br.devgabriela.appthreatsfirebase.Threat t){
        Intent it = new Intent(getBaseContext(), EditThreat.class);
        it.putExtra("KEY", key);
        it.putExtra("TRT", t);
        startActivity(it);
    }
}