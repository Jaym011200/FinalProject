package com.example.word_finder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    private EditText memail, mpassword;
    private TextView mlogin;
    private Button mregister;
    private TextInputLayout til;
    private TextInputLayout til2;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        memail = findViewById(R.id.email);
        mpassword = findViewById(R.id.password);
        mlogin = findViewById(R.id.login);
        mregister = findViewById(R.id.register);

        til = (TextInputLayout) findViewById(R.id.textfield);
        til2 = (TextInputLayout) findViewById(R.id.textfield2);

        mAuth = FirebaseAuth.getInstance();

        mlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this , MainActivity.class));
            }
        });

        mregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        memail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                til.setError(null);
            }
        });

        mpassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(final CharSequence s, final int start, final int count, final int after) {

            }

            @Override
            public void onTextChanged(final CharSequence s, final int start, final int before, final int count) {

            }

            @Override
            public void afterTextChanged(final Editable s) {
                til2.setError(null);
            }
        });

    }

    private void createUser() {
        String email = memail.getText().toString();
        String password = mpassword.getText().toString();
        TextInputLayout til = (TextInputLayout) findViewById(R.id.textfield);
        TextInputLayout til2 = (TextInputLayout) findViewById(R.id.textfield2);

        if (!email.isEmpty()) {
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!password.isEmpty()) {
                if (!(password.length() < 6)) {

                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    Toast.makeText(MainActivity2.this, "Registered Successful!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(MainActivity2.this, MainActivity.class));
                                    finish();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity2.this, "Registration Error!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else
                    til2.setError("Password must be at least six (6) characters long!");
            } else
                til2.setError("This field is required!");
        } else
            til.setError("Please Enter Correct Email");
    } else
            til.setError("This field is required!");
   }
}



