package com.example.recipeapp_;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.auth.User;

public class LoginActivity extends AppCompatActivity {
    EditText txtEmail, txtPassword;
    Button btnRegister, btnLogin;

    String username = "", password = "";
    private FirebaseAuth mAuth;
    DatabaseReference mData;

// ...
// Initialize Firebase Auth

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // mapping field
        mAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.al_txtEmail);
        txtPassword = findViewById(R.id.al_txtPassword);
        btnLogin = findViewById(R.id.btn_login);
        btnRegister = findViewById(R.id.btn_register);

        //dang ky su kien khi click vào register và login

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyen activity 1 sang activity 2
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DangNhap();
            }
        });

    }
    private void DangNhap() {
        try {
            String email = txtEmail.getText().toString();
            String pass = txtPassword.getText().toString();

            mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        final FirebaseUser USER = FirebaseAuth.getInstance().getCurrentUser();
                        String userID = USER.getUid();
                        mData = FirebaseDatabase.getInstance().getReference().child("Users").child(userID);
                        startActivity(new Intent(LoginActivity.this, FoodActivity.class));
                    } else {
                        Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không hợp lệ.", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } catch (Exception ce){

        }

    }

}