package com.example.newquiz.authorization;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class SignUpActivity extends AppCompatActivity {

    private ImageView imgBack;  private EditText userNameL, emailL, passL, confirmPassL;
    private Button btnSignUp;  private FirebaseAuth mAuth;
    private String emailStr, passStr, confirmPassStr, nameStr;  private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
                    if (Network.isNetworkAvailable(SignUpActivity.this))
                        signUpNewUser();
                    else
                        Toast.makeText(SignUpActivity.this, "У вас нет подключения интернета", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    void init(){
        userNameL = findViewById(R.id.edUserNameL);
        emailL = findViewById(R.id.edEmailL);
        passL = findViewById(R.id.edPassL);
        confirmPassL = findViewById(R.id.edConfirmPassL);

        imgBack = findViewById(R.id.imgBack);
        btnSignUp = findViewById(R.id.btnSignUp);

        mAuth = FirebaseAuth.getInstance();

        progressDialog = new Dialog(SignUpActivity.this);
        progressDialog.setContentView(R.layout.progress_bar_layout);
        progressDialog.setCancelable(false);
        progressDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }
    private boolean validate() {
        nameStr = userNameL.getText().toString().trim();
        emailStr = emailL.getText().toString().trim();
        passStr = passL.getText().toString().trim();
        confirmPassStr = confirmPassL.getText().toString().trim();

        if (nameStr.isEmpty()){
            userNameL.setError("Enter your name");
            return false;
        }if (emailStr.isEmpty()){
            emailL.setError("Enter your email");
            return false;
        }if (passStr.isEmpty()){
            passL.setError("Enter your password");
            return false;
        }if (passStr.length() < 6){
            Toast.makeText(this, "Пароль меньше 6 символа", Toast.LENGTH_SHORT).show();
            return false;
        }if (confirmPassStr.isEmpty()){
            confirmPassL.setError("Enter your password");
            return false;
        }

        if (passStr.compareTo(confirmPassStr) != 0){
            Toast.makeText(this, "Пароль не совпадают", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    private void signUpNewUser() {

        progressDialog.show();

        mAuth.createUserWithEmailAndPassword(emailStr, passStr)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();

                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(SignUpActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}

