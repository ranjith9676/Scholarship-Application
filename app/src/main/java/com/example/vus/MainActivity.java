package com.example.vus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {


    EditText phone,Reg;
    EditText otp;
    Button btnlogin,otpbtn;
    FirebaseAuth mAuth;
    String verificationID;
    String reg,mob;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Reg=findViewById(R.id.text);
        phone=findViewById(R.id.phone);
        otp=findViewById(R.id.otp);
        otpbtn=findViewById(R.id.otpbtn);
        btnlogin=findViewById(R.id.btnlogin);
        mAuth=FirebaseAuth.getInstance();



        otpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("Students").child(Reg.getText().toString()).child("Profile").child("Ph_no");
                //Read from the database
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        String value = dataSnapshot.getValue(String.class);
                        valid(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        // Failed to read value
                        //Log.w(TAG, "Failed to read value.", error.toException());
                    }
                });


                }

            private void valid(String value) {
                if (value.equals(phone.getText().toString())) {
                    final String mob = phone.getText().toString();

                    sendverificationcode(mob);
                }
                else {

                    Toast.makeText(MainActivity.this, "phone_no doesn't matches with reg_no", Toast.LENGTH_LONG).show();
                }
            }


        });





        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //final String Reg=login_text.getText().toString();
                final String mob=phone.getText().toString();
                if(TextUtils.isEmpty(otp.getText().toString()))
                {
                    Toast.makeText(MainActivity.this,"Wrong Otp",Toast.LENGTH_LONG).show();
                }
                else
                {
                    verifycode(otp.getText().toString());
                }


            }
        });

    }




    private void sendverificationcode(String phoneNumber)
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }





    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {


            final String code = credential.getSmsCode();
            if(code!=null)
            {
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {

            Toast.makeText(MainActivity.this,"verification Failed",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(@NonNull String s,
                               @NonNull PhoneAuthProvider.ForceResendingToken token) {

            super.onCodeSent(s, token);
            verificationID =s;
        }
    };

    private void verifycode(String Code) {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verificationID,Code);
        signinbyCredentinals(credential);
    }

    private void signinbyCredentinals(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String reg =Reg.getText().toString();
                    String mob=phone.getText().toString();
                    Toast.makeText(MainActivity.this, "CORRECT OTP", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(MainActivity.this,MainActivity2.class);
                    i.putExtra("reg",reg);
                    i.putExtra("mob",mob);
                    startActivity(i);
                    finish();



                }
            }
        });



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser =FirebaseAuth.getInstance().getCurrentUser();
        /* if(currentUser!=null)
        {
            startActivity(new Intent(MainActivity.this,MainActivity2.class));
            finish();
        }*/
    }
}