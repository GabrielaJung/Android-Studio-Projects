package br.devgabriela.threatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditThreat extends AppCompatActivity {
    ThreatSQLiteDatabase db;
    EditText addressEdit;
    EditText descriptionEdit;
    EditText dateEdit;
    Threat currentThreat;
    ThreatAdapter threatAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_threat);

        addressEdit = findViewById(R.id.addressEdit);
        dateEdit = findViewById(R.id.dateEdit);
        descriptionEdit = findViewById(R.id.descriptionEdit);

        db = new ThreatSQLiteDatabase(
                getBaseContext());

        Intent intent = getIntent();
        Long id = intent.getLongExtra("id", 0);

        currentThreat = db.getThreat(id);

        addressEdit.setText(currentThreat.getAddress());
        dateEdit.setText(currentThreat.getDate());
        descriptionEdit.setText(currentThreat.getDescription());
    }

    public void updateThreat(View v){
        if(addressEdit.getText().length() <= 0 ||
                dateEdit.getText().length() <= 0 ||
                descriptionEdit.getText().length() <= 0) {
            Toast.makeText(this, "Oops! Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(addressEdit.getText().toString() == currentThreat.getAddress() &&
                dateEdit.getText().toString() == currentThreat.getDate() &&
                descriptionEdit.getText().toString() == currentThreat.getDescription()){
            finish();
        }

        currentThreat.setAddress(addressEdit.getText().toString());
        currentThreat.setDate(dateEdit.getText().toString());
        currentThreat.setDescription(descriptionEdit.getText().toString());
        db.updateThreat(currentThreat);
        threatAdapter.notifyDataSetChanged();
        finish();
    }
}