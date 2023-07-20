package com.example.vus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity4 extends AppCompatActivity {


    Button uButton,upload,btn;
    ImageView im;
    Uri photoUri;

    ProgressDialog progressDialog;
    StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);


        uButton=(Button)findViewById(R.id.button3);
        im=(ImageView)findViewById(R.id.imageView);
        upload=(Button)findViewById(R.id.button4);
        btn=(Button)findViewById(R.id.button5);



        uButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 100);


            }
        });
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                uploadImage();

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=getIntent();
                String Greg1=intent.getStringExtra("Reg_no");
                Intent intent2 =new Intent(getApplicationContext(),MainActivity6.class);
                intent2.putExtra("Reg_no",Greg1);
                startActivity(intent2);
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100 && data.getData()!=null)
        {
            photoUri=data.getData();
            im.setImageURI(photoUri);
        }
    }
    private void uploadImage() {
        Intent intent=getIntent();
        String Greg=intent.getStringExtra("Reg_no");

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Uploading File....");
        progressDialog.show();

        storageReference = FirebaseStorage.getInstance().getReference(Greg+"/"+"SeatAllotmentLetter");


        storageReference.putFile(photoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                        Toast.makeText(MainActivity4.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {


                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                        Toast.makeText(MainActivity4.this,"Failed to Upload",Toast.LENGTH_SHORT).show();


                    }
                });

    }
}