package br.devgabriela.appthreatsfirebase;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import br.devgabriela.appthreatsfirebase.R;

public class AddThreat extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference root = database.getReference();
    DatabaseReference threats = root.child(br.devgabriela.appthreatsfirebase.MainActivity.THREATS_KEY);
    EditText addAddress, addDate, addDescription;

//    static final int REQUEST_IMAGE_CAPTURE = 1;
//    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_threat);

//        imageView = findViewById(R.id.addImageView);

        addAddress = findViewById(R.id.addAddress);
        addDate = findViewById(R.id.addDate);
        addDescription = findViewById(R.id.addDescription);

//        Button captureButton = findViewById(R.id.addTakePhoto);
//        captureButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dispatchTakePictureIntent();
//            }
//        });
    }

//    @SuppressLint("QueryPermissionsNeeded")
//    private void dispatchTakePictureIntent() {
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        }
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            assert extras != null;
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
//        }
//    }

    public void addThreat(View v){
        br.devgabriela.appthreatsfirebase.Threat t = new br.devgabriela.appthreatsfirebase.Threat();
        t.setAddress(addAddress.getText().toString());
        t.setDate(addDate.getText().toString());
        t.setDescription(addDescription.getText().toString());
        String key = threats.push().getKey();
        threats.child(key).setValue(t);
        finish();
    }
}