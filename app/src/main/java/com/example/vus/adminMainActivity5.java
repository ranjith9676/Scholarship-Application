package com.example.vus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.EventListener;
import  java.util.*;

public class adminMainActivity5 extends AppCompatActivity {
   RecyclerView recyclerView;
   ArrayList<Stud> studArrayList;
   MyAdapater myAdapter;
   DatabaseReference databaseReference;
   EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main5);


        recyclerView=(RecyclerView)findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        databaseReference = FirebaseDatabase.getInstance().getReference("Students");
        

        studArrayList = new ArrayList<Stud>();
        myAdapter= new MyAdapater(adminMainActivity5.this,studArrayList);
        
        recyclerView.setAdapter(myAdapter);

         databaseReference.addValueEventListener(new ValueEventListener()
         {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
                 for(DataSnapshot dataSnapshot:snapshot.getChildren())
                 {
                     Stud stud =dataSnapshot.getValue(Stud.class);
                     studArrayList.add(stud);

                 }
                 myAdapter.notifyDataSetChanged();

             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });
         search =findViewById(R.id.search);
         search.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

             }

             @Override
             public void afterTextChanged(Editable editable) {
                 filter(editable.toString());

             }
         });
        
    }
    private void filter(String text)
    {
        ArrayList<Stud> filteredList =new ArrayList<>();
        for(Stud item: studArrayList)
        {
            if(item.getStatus().toLowerCase().contains(text.toLowerCase()))
            {
                filteredList.add(item);
            }
        }
        myAdapter.filteredList(filteredList);
    }


}