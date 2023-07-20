package com.example.vus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3 extends AppCompatActivity {
    TextView name, reg, sch, acc_name, acc_no, ifsc, bank_name, branch, mob;
    Button btLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        name = (TextView) findViewById(R.id.en1);
        reg = (TextView) findViewById(R.id.eRn1);
        sch = (TextView) findViewById(R.id.esps1);
        acc_name = (TextView) findViewById(R.id.each1);
        acc_no = (TextView) findViewById(R.id.eacn1);
        ifsc = (TextView) findViewById(R.id.eifsc1);
        bank_name = (TextView) findViewById(R.id.ename1);
        branch = (TextView) findViewById(R.id.ebranch1);
        mob = (TextView) findViewById(R.id.emobile1);

        btLogin = (Button) findViewById(R.id.button2);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=getIntent();
                String Greg=intent1.getStringExtra("Reg_no");



                String name1 = name.getText().toString();
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Students").child(Greg).child("Name");
                //Read from the database
                myRef.setValue(name1);

                String reg1 = reg.getText().toString();
                DatabaseReference regNo = database.getReference("Students").child(Greg).child("Reg_No");
                //Read from the database
                regNo.setValue(reg1);



                String sch1 = sch.getText().toString();

                DatabaseReference schRef = database.getReference("Students").child(Greg).child("Scholar_ship_sem");
                //Read from the database
                schRef.setValue(sch1);



                String acc_name1 = acc_name.getText().toString();
                DatabaseReference acc_nameRef = database.getReference("Students").child(Greg).child("acc_name");
                //Read from the database
                acc_nameRef.setValue(acc_name1);


                String acc_no1= acc_no.getText().toString();

                DatabaseReference acc_noRef = database.getReference("Students").child(Greg).child("acc_no");
                //Read from the database
                acc_noRef.setValue(acc_no1);







                String ifsc1 = ifsc.getText().toString();

                DatabaseReference ifscRef = database.getReference("Students").child(Greg).child("IFSC");
                //Read from the database
                ifscRef.setValue(ifsc1);


                String bank_name1 = bank_name.getText().toString();
                DatabaseReference bank_nameRef = database.getReference("Students").child(Greg).child("Bank_Name");
                //Read from the database
                bank_nameRef.setValue(bank_name1);


                String branch1 = branch.getText().toString();
                DatabaseReference branchRef = database.getReference("Students").child(Greg).child("Branch");
                //Read from the database
                branchRef.setValue(branch1);



                String mob1 = mob.getText().toString();

                DatabaseReference mobRef = database.getReference("Students").child(Greg).child("ph_no");
                //Read from the database
                mobRef.setValue(mob1);


                DatabaseReference status = database.getReference("Students").child(Greg).child("status");
                //Read from the database
                status.setValue("Not Verified");

                Toast.makeText(MainActivity3.this, "Submitted", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getApplicationContext(),MainActivity4.class);
                intent.putExtra("Reg_no",Greg);


                startActivity(intent);
            }
        });
    }
}
