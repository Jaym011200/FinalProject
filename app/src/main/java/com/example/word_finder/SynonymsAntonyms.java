package com.example.word_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SynonymsAntonyms extends AppCompatActivity {

    EditText editTextSearch;
    ImageButton btnSearch;
    TextView textViewResults;
    TextView textViewResults2;
    TextView textViewResults3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.synoyms_and_antonyms);

        editTextSearch = (EditText) findViewById(R.id.editTextSearch);
        btnSearch = (ImageButton) findViewById(R.id.btnSubmit);
        textViewResults = (TextView) findViewById(R.id.textViewResults);
        textViewResults2 = (TextView) findViewById(R.id.textViewResults2);
        textViewResults3 = (TextView) findViewById(R.id.textViewResults3);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextSearch.getText().toString()))
                {
                    Toast.makeText(SynonymsAntonyms.this, "Enter the word", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    DatabaseReference mRef1 = FirebaseDatabase.getInstance().getReference("synonym");
                    mRef1.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchKeyword = editTextSearch.getText().toString();
                            if (dataSnapshot.child(searchKeyword).exists())
                            {
                                textViewResults.setText(dataSnapshot.child(searchKeyword).getValue().toString());
                            }else
                            {
                                Toast.makeText(SynonymsAntonyms.this, "Word not in database.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    DatabaseReference mRef2 = FirebaseDatabase.getInstance().getReference("antonym");
                    mRef2.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchKeyword = editTextSearch.getText().toString();

                            if (dataSnapshot.child(searchKeyword).exists())
                            {
                                textViewResults2.setText(dataSnapshot.child(searchKeyword).getValue().toString());
                            }else
                            {
                                Toast.makeText(SynonymsAntonyms.this, "Word not in database.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                    DatabaseReference mRef3 = FirebaseDatabase.getInstance().getReference("examples");
                    mRef3.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String searchKeyword = editTextSearch.getText().toString();
                            if (dataSnapshot.child(searchKeyword).exists())
                            {
                                textViewResults3.setText(dataSnapshot.child(searchKeyword).getValue().toString());
                            }else
                            {
                                Toast.makeText(SynonymsAntonyms.this, "Word not in database.", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

    }

}