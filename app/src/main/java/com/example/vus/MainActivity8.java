package com.example.vus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity8 extends AppCompatActivity {
Button cha,bank,adm,btn;
RadioButton verified,reupload;
String Greg1;
    static String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        cha=findViewById(R.id.chall);
        adm=findViewById(R.id.adm);
        bank=findViewById(R.id.bank);
        verified=(RadioButton)findViewById(R.id.verified);
        reupload=(RadioButton)findViewById(R.id.reupload);
        btn=(Button)findViewById(R.id.button6);
        Intent intent2=getIntent();
        Greg1=intent2.getStringExtra("reg1");

        adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Students").child(Greg1).child("img_admi");
                //Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                         String value = dataSnapshot.getValue(String.class);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(value));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        //Failed to read value
                        Log.w( "Failed to read value.", error.toException());
                    }
                });

            }
        });



        cha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Students").child(Greg1).child("img_challa");
                //Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(value));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });




        bank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Students").child(Greg1).child("img_passbook");
                //Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(value));
                        startActivity(intent);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Students").child(Greg1).child("status");
                //Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.

                        if(verified.isChecked())
                        {

                            myRef.setValue("Verified");

                        }
                        if(reupload.isChecked())
                        {

                            myRef.setValue("ReUpload");
                        }



                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });
                Toast.makeText(MainActivity8.this, "Submitted", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),adminMainActivity5.class);
                startActivity(i);
            }
        });

    }
}