package br.devgabriela.threatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddThreat extends AppCompatActivity {
    ThreatSQLiteDatabase db;
    ThreatAdapter threatAdapter;
    EditText address, date, description;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_threat);

        address = findViewById(R.id.addressAdd);
        date = findViewById(R.id.dateAdd);
        description = findViewById(R.id.descriptionAdd);

        db = new ThreatSQLiteDatabase(getBaseContext());
    }

    public void addThreat(View v){
        if(address.getText().length() <= 0 ||
                date.getText().length() <= 0 ||
                description.getText().length() <= 0) {
            Toast.makeText(this, "Oops! Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show();
            return;
        }

        Threat t = new Threat();
        t.setAddress(address.getText().toString());
        t.setDate(date.getText().toString());
        t.setDescription(description.getText().toString());

        db.addThreat(t);
        threatAdapter.notifyDataSetChanged();
        Log.println(Log.INFO, "teste", t.getId().toString());
        finish();
    }
}