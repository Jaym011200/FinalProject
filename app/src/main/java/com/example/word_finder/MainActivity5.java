package com.example.word_finder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

    public class MainActivity5 extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main5);

            Button btnSynonyms = (Button) findViewById(R.id.synonyms);
            Button btnWords = (Button) findViewById(R.id.words);
            Button btnProfile = (Button) findViewById(R.id.profile);
            Button btnTrivia = (Button) findViewById(R.id.trivia);


            btnSynonyms.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent synonyms = new Intent(MainActivity5.this, SynonymsAntonyms.class);
                    startActivity(synonyms);
                }
            });
            btnWords.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent words = new Intent(MainActivity5.this, MainActivity4.class);
                    startActivity(words);
                }
            });
            btnTrivia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent trivia = new Intent(MainActivity5.this, Trivia.class);
                    startActivity(trivia);
                }
            });
            btnProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent profile = new Intent(MainActivity5.this, ActivityProfile.class);
                    startActivity(profile);
                }
            });
        }
    }
