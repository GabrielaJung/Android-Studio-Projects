package br.devgabriela.firebasethreats;

import static br.devgabriela.firebasethreats.R.*;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.database.FirebaseListAdapter;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listThreat = findViewById(R.id.listThreats);

      /*  listAdapter = new FirebaseListAdapter<Threat>(this, Threat.class,
            R.layout.threat_list_item, threats) {
            @Override
            protected void populateView(View v, Threat model, int position) {
                TextView txtAddress= v.findViewById(id.txtAddress);
                TextView txtDate= v.findViewById(id.txtDate);
                // ImageView imageThreat= v.findViewById(id.imageThreat);

                txtAddress.setText(model.getAddress());
                txtDate.setText(model.getDate());
                //imageThreat.setImage(model.getImage());
            }
        };
        listThreat.setAdapter(listAdapter);*/

        // Crie as opções para o adaptador FirebaseListAdapter
        FirebaseListOptions<Threat> options = new FirebaseListOptions.Builder<Threat>()
                .setLayout(R.layout.threat_list_item)
                .setQuery(threats, Threat.class)
                .build();

        // Inicialize o adaptador FirebaseListAdapter com as opções criadas
        listAdapter = new FirebaseListAdapter<Threat>(options) {
            @Override
            protected void populateView(View v, Threat model, int position) {
                TextView txtAddress = v.findViewById(R.id.txtAddress);
                TextView txtDate = v.findViewById(R.id.txtDate);

                txtAddress.setText(model.getAddress());
                txtDate.setText(model.getDate());
            }
        };

        // Configure o adaptador para o RecyclerView
        listThreat.setAdapter(listAdapter);

        listThreat.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                DatabaseReference item = listAdapter.getRef(position);
                item.removeValue();
                return false;
            }
        });

        listThreat.setOnItemClickListener(((parent, view, position, id) -> {
            DatabaseReference item = listAdapter.getRef(position);
            changeToUpdate(item.getKey(), listAdapter.getItem(position));
        }));

        /*EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

    public void chandeToAdd(View v){
        Intent it = new Intent(getBaseContext(), AddThreat.class);
        startActivity(it);
    }

    public void changeToUpdate(String key, Threat t){
        Intent it = new Intent(getBaseContext(), EditThreat.class);
        it.putExtra("KEY", key);
        it.putExtra("TRT", t);
        startActivity(it);
    }
}