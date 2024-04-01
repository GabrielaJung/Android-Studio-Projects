package br.devgabriela.threatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EditThreat extends AppCompatActivity {
    EditText addressEdit;
    EditText descriptionEdit;
    EditText dateEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_threat);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String description = intent.getStringExtra("description");
        String date = intent.getStringExtra("date");
        String address = intent.getStringExtra("address");

        addressEdit = findViewById(R.id.addressEdit);
        dateEdit = findViewById(R.id.dateEdit);
        descriptionEdit = findViewById(R.id.descriptionEdit);

        addressEdit.setText(address);
        dateEdit.setText(date);
        descriptionEdit.setText(description);
    }

    public void backToHomeFromEdit(View v){
        Intent it = new Intent (getBaseContext(), MainActivity.class);
        // send data as an arrayList to next context:
        // it.putStringArrayListExtra("listThreats", listThreats);
        startActivity(it);
    }
}