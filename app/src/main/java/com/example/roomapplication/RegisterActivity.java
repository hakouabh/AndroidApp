package com.example.roomapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName,editTextEmail,editTextPassword,editTextConfirmPassword;
    private Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextName = findViewById(R.id.name);
        editTextEmail = findViewById(R.id.email);
        editTextPassword = findViewById(R.id.password);
        editTextConfirmPassword = findViewById(R.id.confirm_password);

        buttonRegister = findViewById(R.id.register);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = editTextName.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                String confirm_password = editTextConfirmPassword.getText().toString();

                if (TextUtils.isEmpty(name)){
                    return;
                }

                if (TextUtils.isEmpty(password)|| password.length()<8){
                    return;
                }

                if (TextUtils.isEmpty(email)){
                    return;
                }

                if (TextUtils.isEmpty(confirm_password)){
                    return;
                }
                Map<String, String> parameters = new HashMap<>();
                parameters.put("name", name);
                parameters.put("email", email);
                parameters.put("password", password);
                parameters.put("password_confirmation", confirm_password);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}