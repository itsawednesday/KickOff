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


public class RegistrationActivity extends AppCompatActivity {
    private EditText regEmail, regPass;
    private Button regBtn;
    private TextView regLogin;
    private Toolbar toolbar;

    private FirebaseAuth mAuth;

    private ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        toolbar = findViewById(R.id.regToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Registration");

         mAuth = FirebaseAuth.getInstance();
        loading = new ProgressDialog(this);


        regEmail = findViewById(R.id.registerEmail);
        regPass = findViewById(R.id.registerPassword);
        regBtn = findViewById(R.id.registerButton);
        regLogin = findViewById(R.id.loginInstead);

        regLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = regEmail.getText().toString().trim();
                String password = regPass.getText().toString().trim();


                //error if field is empty


                if (TextUtils.isEmpty(email)){
                    regEmail.setError("Must enter email");
                    return;

                }
                if (TextUtils.isEmpty(password)){
                    regPass.setError("Must enter password");
                    return;

                    //if fields are not empty
                }else {
                    loading.setMessage("In progress..");
                    loading.setCanceledOnTouchOutside(false);
                    loading.show();


                    //If registration is successful then direct to the next activity
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {


                                Intent intent = new Intent(RegistrationActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                                loading.dismiss();
                            }else{
                                String error = task.getException().toString();
                                Toast.makeText(RegistrationActivity.this, "Registration failed" + error, Toast.LENGTH_SHORT).show();
                                loading.dismiss();
                            }
                        }
                    });

                }



            }
        });








    }}

