package com.example.roomapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private Button buttonlogin;
    private EditText editTextusername,editTextpassword;
    private ProgressBar progressBar;
    private TextView textViewResult, textViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextpassword = findViewById(R.id.password);
        editTextusername = findViewById(R.id.username);
        buttonlogin = findViewById(R.id.login);
        progressBar = findViewById(R.id.loading);
        textViewResult = findViewById(R.id.text_view_result);
        textViewRegister = findViewById(R.id.register);

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = editTextusername.getText().toString();
                String password = editTextpassword.getText().toString();

                if (TextUtils.isEmpty(username)){
                    return;
                }

                if (TextUtils.isEmpty(password)|| password.length()<8){
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                Toast.makeText(LoginActivity.this, "username: "+username+" password: "+password, Toast.LENGTH_SHORT).show();

                if (true ) {
                    startActivity(new Intent(LoginActivity.this, ProductActivity.class));
                    finish();
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "connected", Toast.LENGTH_SHORT).show();
                }
            }
        });

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }
}