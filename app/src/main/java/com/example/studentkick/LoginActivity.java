package com.example.studentkick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEmail, loginPass;
    private Button loginBtn;
    private Toolbar toolbar;
    private FirebaseAuth mAuth;
    private ProgressDialog loading;
    private TextView loginReg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar = findViewById(R.id.loginToolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Login");


        loginEmail = findViewById(R.id.loginEmail);
        loginPass= findViewById(R.id.loginPassword);
        loginBtn= findViewById(R.id.loginButton);

        loginReg= findViewById(R.id.regHere);

        mAuth =FirebaseAuth.getInstance();
        loading = new ProgressDialog(this);

        loginReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent (LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString().trim();
                String password = loginPass.getText().toString().trim();


                //error if field is empty, then promp user to re-enter
                if (TextUtils.isEmpty(email)){
                    loginEmail.setError("Must enter email");
                    return;

                }
                if (TextUtils.isEmpty(password)){
                    loginPass.setError("Must enter password");
                    return;

                    //if these fields are not empty then create the user

                }else {
                    loading.setMessage("In progress..");
                    loading.setCanceledOnTouchOutside(false);
                    loading.show();

                    //Sign in after Registering

                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //Once successful then direct to the list page
                            if (task.isSuccessful()) {


                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                                loading.dismiss();
                            }else{
                                String error = task.getException().toString();
                                Toast.makeText(LoginActivity.this, "Login failed" + error, Toast.LENGTH_SHORT).show();
                                loading.dismiss();


                            }
                        }
                    });

                }



            }
        });



    }}