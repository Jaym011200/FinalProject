package com.example.word_finder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity4 extends AppCompatActivity {

    private EditText mwords, mmeaning;
    private Button maddwords;
    private Button mback;

    FirebaseDatabase root;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

    mwords = findViewById(R.id.words);
    mmeaning = findViewById(R.id.meaning);
    maddwords = findViewById(R.id.addwords);
    mback = findViewById(R.id.back);


    mback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity4.this , MainActivity3.class));
        }
    });

    maddwords.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            root = FirebaseDatabase.getInstance();
            reference = root.getReference("meaning");

            String words = mwords.getText().toString();
            String meaning = mmeaning.getText().toString();

            UserHelperClass helperClass = new UserHelperClass(words, meaning);

            reference.child(words).setValue(helperClass);
            Toast.makeText(MainActivity4.this, "Words and Meaning Added !!", Toast.LENGTH_SHORT).show();

        }
    });


    }
}