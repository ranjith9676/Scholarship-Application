package com.example.vus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class MainActivity2 extends AppCompatActivity {
    TextView Reg,mob,n1,s1,b1,y1,se1;
    Button btlogin;
    ImageView imgp;
    String k;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(R.drawable.image);
        getSupportActionBar().setTitle("ScholarShip");

        Intent i=getIntent();
        String Greg=i.getStringExtra("reg");
        k=Greg;
        String Gmod=i.getStringExtra("mob");
        Reg=(TextView) findViewById(R.id.Rn1);
        mob=(TextView) findViewById(R.id.ph1);
        n1=(TextView) findViewById(R.id.n1);
        s1=(TextView) findViewById(R.id.s1);
        b1=(TextView) findViewById(R.id.b1);
        y1=(TextView) findViewById(R.id.y1);
        se1=(TextView) findViewById(R.id.se1);
        imgp=(ImageView)findViewById(R.id.imageView5);
        Reg.setText(Greg);
        mob.setText(Gmod);

        storageReference = FirebaseStorage.getInstance().getReference(Greg+"/"+"pimage.jpeg");
        try {
            File localfile =File.createTempFile("tempfile","jpeg");
            storageReference.getFile(localfile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap= BitmapFactory.decodeFile(localfile.getAbsolutePath());
                            imgp.setImageBitmap(bitmap);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Students").child(Greg).child("Profile").child("Name");
        //Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                n1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Failed to read value.", error.toException());
            }
        });
        DatabaseReference sec = database.getReference("Students").child(Greg).child("Profile").child("Section");
        sec.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                s1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




        DatabaseReference bra = database.getReference("Students").child(Greg).child("Profile").child("Branch");
        bra.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                b1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        DatabaseReference year = database.getReference("Students").child(Greg).child("Profile").child("year");
        year.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                y1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        DatabaseReference sem = database.getReference("Students").child(Greg).child("Profile").child("semester");
        sem.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                se1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        btlogin=(Button) findViewById(R.id.button);
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity2.this, "Application", Toast.LENGTH_SHORT).show();
                Intent intent1 =new Intent(getApplicationContext(),MainActivity3.class);
                intent1.putExtra("Reg_no",Greg);
                startActivity(intent1);


            }



        });
    }
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.details:
                Intent i = new Intent(getApplicationContext(), MainActivity9.class);
                i.putExtra("reg",k);
                startActivity(i);
                break;
            case R.id.hello:
                Intent in = new Intent(getApplicationContext(), homeMain.class);
                startActivity(in);
                Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}