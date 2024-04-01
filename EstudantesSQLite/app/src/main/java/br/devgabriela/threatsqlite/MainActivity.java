package br.devgabriela.threatsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import br.devgabriela.estudantessqlite.R;


public class MainActivity extends AppCompatActivity {
    ListView listStudent;
    ThreatSQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new ThreatSQLiteDatabase(
                getBaseContext());

        Threat s = new Threat();
        s.setAddress("Luffy");
        s.setDate("Pirate's King");
        s.setDescription("monkey.d.luffy@gmail.com");
        Log.d("ADD_INFO", db.addThreat(s).toString());
        List<Threat> ss = db.getThreats();
        for(Threat as:ss){
            Log.d("LST_INFO", as.toString());
        }

        s = db.getThreat(new Long(1));
        s.setAddress("Monkey D. Luffy");
        Log.d("UPD_INFO", s.toString());
        Log.d("SIZ_INFO", db.getThreats().size() + "");

    }
}