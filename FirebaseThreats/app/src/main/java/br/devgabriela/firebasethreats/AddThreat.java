package br.devgabriela.firebasethreats;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddThreat extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference threats = root.child(MainActivity.THREATS_KEY);
    EditText addAddress, addDate, addDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_threat);

        addAddress = findViewById(R.id.addAddress);
        addDate = findViewById(R.id.addDate);
        addDescription = findViewById(R.id.addDescription);
        //ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        //    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        //    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        //    return insets;
        //});
    }

    public void addThreat(View v){
        Threat t = new Threat();
        t.setAddress(addAddress.getText().toString());
        t.setDate(addDate.getText().toString());
        t.setDescription(addDescription.getText().toString());
        String key = threats.push().getKey();
        threats.child(key).setValue(t);
        finish();
    }
}