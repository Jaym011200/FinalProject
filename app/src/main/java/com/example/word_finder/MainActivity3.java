package com.example.word_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3 extends AppCompatActivity {

    EditText editTextSearch;
    Button btnSearch;
    TextView textViewResults;
    private Button btnlogout;
    private FirebaseAuth mAuth;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

     editTextSearch = (EditText) findViewById(R.id.editTextSearch);
     btnSearch = (Button) findViewById(R.id.btnSubmit);
     textViewResults = (TextView) findViewById(R.id.textViewResults);

     btnlogout = findViewById(R.id.logout);
     mAuth = FirebaseAuth.getInstance();
     add = findViewById(R.id.add);


     btnlogout.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {

             mAuth.signOut();
             startActivity(new Intent(MainActivity3.this , MainActivity.class));
             finish();
         }
     });


     btnSearch.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             if (TextUtils.isEmpty(editTextSearch.getText().toString()))
             {
                 Toast.makeText(MainActivity3.this, "No empty keyboard allowed", Toast.LENGTH_SHORT).show();
             }
             else
             {
                 DatabaseReference mRef = FirebaseDatabase.getInstance().getReference("meaning");
                 mRef.addValueEventListener(new ValueEventListener() {
                     @Override
                     public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                         String searchKeyword = editTextSearch.getText().toString();
                        if (dataSnapshot.child(searchKeyword).exists())
                        {
                            textViewResults.setText(dataSnapshot.child(searchKeyword).getValue().toString());
                        }else
                        {
                            Toast.makeText(MainActivity3.this, "No search results found", Toast.LENGTH_SHORT).show();
                        }
                     }

                     @Override
                     public void onCancelled(@NonNull DatabaseError databaseError) {

                     }
                 });
             }
         }
     });
     add.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             startActivity(new Intent(MainActivity3.this , MainActivity4.class));
         }
     });

    }
}