package com.example.firebaseproject;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class UpdateActivity extends AppCompatActivity {
    ImageView updateImage;
    Button updateButton;
    EditText updateDesc,updateTitle,updateLang;
    String title,desc,lang;
    String imageUrl;
    String key,oldImageURL;
    Uri uri;
    DatabaseReference databaseReference;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        updateButton= findViewById(R.id.saveUpdate);
        updateDesc= findViewById(R.id.updateDesc);
        updateTitle=  findViewById(R.id.updatetopic);
        updateLang= findViewById(R.id.updateLang);
        updateImage=  findViewById(R.id.updateimage);

        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode()== Activity.RESULT_OK)
                        {
                                Intent data=result.getData();
                                uri=data.getData();
                                updateImage.setImageURI(uri);
                        }
                        else {
                            Toast.makeText(UpdateActivity.this, "No image selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            if (bundle.containsKey("Title")) {
                updateTitle.setText(bundle.getString("Title"));
            }

            if (bundle.containsKey("Description")) {
                updateDesc.setText(bundle.getString("Description"));
            }

            if (bundle.containsKey("Language")) {
                updateLang.setText(bundle.getString("Language"));
            }

            key = bundle.getString("key");

            if (bundle.containsKey("image")) {
                oldImageURL = bundle.getString("image");
                Glide.with(UpdateActivity.this).load(oldImageURL).into(updateImage);
            }


            databaseReference= FirebaseDatabase.getInstance().getReference("android Tutorial").child(key);
            updateImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent photopicker=new Intent(Intent.ACTION_PICK);
                    photopicker.setType("image/*");
                    activityResultLauncher.launch(photopicker);

                }
            });
            updateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SaveData();
                    Intent intent=new Intent(UpdateActivity.this,MainActivity.class);
                    startActivity(intent);
                }
            });

        }
        }




    public void SaveData() {
        try {
            if (uri == null) {
                // Handle the case when no image is selected
                imageUrl = oldImageURL;
                updateData();
                return;
            }

            // Existing code...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateData()
    {
        try {


            title=updateTitle.getText().toString().trim();
            desc=updateDesc.getText().toString().trim();
            lang=updateLang.getText().toString().trim();

            DataClass dataClass=new DataClass(title,desc,lang,imageUrl);

            databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful())
                    {
                        StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
                        reference.delete();
                        Toast.makeText(UpdateActivity.this, "Sultan Good", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }
}