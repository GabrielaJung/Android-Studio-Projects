package br.devgabriela.firebasethreats;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditThreat extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference threats = root.child(MainActivity.THREATS_KEY);
    EditText editAddress, editDate, editDescription;
    Threat current;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_threat);
        editAddress = findViewById(R.id.editAddress);
        editDate = findViewById(R.id.editDate);
        editDescription = findViewById(R.id.editDescription);
        key = getIntent().getStringExtra("KEY");
        current = (Threat) getIntent().getSerializableExtra("TRT");
        editAddress.setText(current.getAddress());
        editDate.setText(current.getDate());
        editDescription.setText(current.getDescription());

        /*EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

    public void updateThreat(View v){
        current.setAddress(editAddress.getText().toString());
        current.setDate(editDate.getText().toString());
        current.setDescription(editDescription.getText().toString());
        threats.child(key).setValue(current);
        finish();
    }
}