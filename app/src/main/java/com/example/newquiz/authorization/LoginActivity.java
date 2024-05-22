package com.example.newquiz.authorization;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.newquiz.MainActivity;
import com.example.newquiz.Network;
import com.example.newquiz.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText email, password;    private Button btnLogin;       private TextView tvForgotPass, tvSignUp;
    private FirebaseAuth mAuth;          private Dialog progressDialog;  private RelativeLayout relativeGoogle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateData()) {
                    if (Network.isNetworkAvailable(LoginActivity.this))
                        login();
                    else
                        Toast.makeText(LoginActivity.this, "У вас нет подключения интернета", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
            }
        });


    }

    void init() {
        email = findViewById(R.id.edEmail);
        password = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgotPass = findViewById(R.id.tvForgotPass);
        tvSignUp = findViewById(R.id.tvSignUp);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new Dialog(LoginActivity.this);
        progressDialog.setContentView(R.layout.progress_bar_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        relativeGoogle = findViewById(R.id.RelativeGoogle);


    }
    private boolean validateData() {

        if (email.getText().toString().isEmpty()){
            email.setError("Enter Email");
            return false;
        }
        if (password.getText().toString().isEmpty()){
            password.setError("Enter password");
            return false;
        }

        return true;
    }
    private void login() {
        progressDialog.show();

        mAuth.signInWithEmailAndPassword(email.getText().toString().trim(), password.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}
