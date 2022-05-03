package com.example.recipeapp_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    EditText artxtFullname, artxtEmail, artxtPassword, artxtConfirmPassword;
    Button btnBack, btnSave;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        artxtFullname = findViewById(R.id.ar_txtFullname);
        artxtEmail = findViewById(R.id.ar_txtEmail);
        artxtPassword = findViewById(R.id.ar_txtPassword);
        artxtConfirmPassword = findViewById(R.id.ar_txtConfirmPassword);

        btnBack = findViewById(R.id.ar_btn_register_back);
        btnSave = findViewById(R.id.ar_btn_regiater_save);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangKy();
            }
        });
    }




    private void DangKy() {
        String email = artxtEmail.getText().toString();
        String pass = artxtPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful()) {
                           Toast.makeText(RegisterActivity.this, "dang ky thanh cong",Toast.LENGTH_LONG).show();
                           Intent intent = new Intent();
                           intent.setClass(RegisterActivity.this, LoginActivity.class);
                           startActivity(intent);

                       }
                       else {
                           Toast.makeText(RegisterActivity.this, "Lỗi", Toast.LENGTH_LONG).show();
                       }
                    }
                });
    }
}