package com.example.jivisha_awayoflife;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fetch extends AppCompatActivity {
TextView a,b,c,d;
Button btn;
DatabaseReference reff;
String diseaseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fetch);

        a=(TextView)findViewById(R.id.Disease);
        b=(TextView)findViewById(R.id.Info);

        diseaseName = getIntent().getStringExtra("DISEASE");

        updateUI();
    }

    void updateUI() {
        a.setText(diseaseName);
        reff= FirebaseDatabase.getInstance().getReference().child("Diseases");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String disease = dataSnapshot.child(diseaseName).getValue().toString();
                b.setText(disease);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}