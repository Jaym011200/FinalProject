package com.example.word_finder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {

    String url;
    private TextView showDef;
    private EditText enterWord;
    ImageButton btnSearch;
    Button btnlogout;
    Button btnmore;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        showDef = findViewById(R.id.showDef);
        btnSearch = (ImageButton) findViewById(R.id.btnSubmit);
        enterWord = findViewById(R.id.enterWord);
        btnlogout = findViewById(R.id.logout);
        btnmore = findViewById(R.id.more);

        mAuth = FirebaseAuth.getInstance();

        btnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this , MainActivity5.class));
            }
        });
        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mAuth.signOut();
                startActivity(new Intent(MainActivity3.this , MainActivity.class));
                finish();
            }
        });
    }

    private String dictionaryEntries()
    {
        final String language = "en-gb";
        final String word = enterWord.getText().toString();
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }

    public void sendRequestOnClick(View v)
    {
        DictionaryRequest dR = new DictionaryRequest(this, showDef);
        url = dictionaryEntries();
        dR.execute(url);
    }
}

