package com.example.studentkick;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.studentkick.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding activityLoginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLoginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(activityLoginBinding.getRoot());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        ProgressDialog loading = new ProgressDialog(this);

        activityLoginBinding.regHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);

            }
        });

        activityLoginBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = activityLoginBinding.loginEmail.getText().toString().trim();
                String password = activityLoginBinding.loginPassword.getText().toString().trim();


                //error if field is empty, then promp user to re-enter
                if (TextUtils.isEmpty(email)) {
                    activityLoginBinding.loginEmail.setError("Must enter email");
                    return;

                }
                if (TextUtils.isEmpty(password)) {
                    activityLoginBinding.loginPassword.setError("Must enter password");
                    return;

                    //if these fields are not empty then create the user

                } else {
                    loading.setMessage("In progress..");
                    loading.setCanceledOnTouchOutside(false);
                    loading.show();

                    //Sign in after Registering

                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            //Once successful then direct user to the list page
                            if (task.isSuccessful()) {


                                Intent intent = new Intent(LoginActivity.this, DashboardActivity.class);
                                startActivity(intent);
                                finish();
                                loading.dismiss();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(LoginActivity.this, "Login failed" + error, Toast.LENGTH_SHORT).show();
                                loading.dismiss();


                            }
                        }
                    });

                }


            }
        });


    }
}