package br.devgabriela.threatsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class AddThreat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_threat);
    }

    public void backToHomeFromAdd (View v){
        Intent it = new Intent (getBaseContext(), MainActivity.class);
        // send data as an arrayList to next context:
        // it.putStringArrayListExtra("listThreats", listThreats);
        startActivity(it);
    }
}