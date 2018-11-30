package com.acidulat.iproblem;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSignIn;

    private EditText email;
    private EditText pass;
    private TextView textViewSignUp;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null)
        {

        }

        btnSignIn = (Button) findViewById(R.id.buttonSignIn);
        email = (EditText) findViewById(R.id.editTextEmail);
        pass = (EditText) findViewById(R.id.editTextPassword);
        textViewSignUp = (TextView) findViewById(R.id.textSignUp);

        btnSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

        progressDialog = new ProgressDialog(this);
    }

    private void usersignIn()
    {
        String Email = email.getText().toString().trim();
        String Password = pass.getText().toString().trim();

        if(TextUtils.isEmpty(Email) && TextUtils.isEmpty(Password)) {
            Toast.makeText(this,"Please enter email and password!", Toast.LENGTH_LONG).show();
            return;

        }
        if(TextUtils.isEmpty(Password)) {
            //email is empty
            Toast.makeText(this, "Please enter email!", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(Password)) {
            //password is empty
            Toast.makeText(this,"Please enter password!",Toast.LENGTH_LONG).show();
            return;
        }

        //if validations are ok
        progressDialog.setMessage("Logging user...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();
                        if(task.isSuccessful())
                            startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                        else
                        {
                            Toast.makeText(LoginActivity.this,"Authentification failed. Please try again",Toast.LENGTH_LONG).show();
                        }


                    }
                });
    }
    @Override
    public void onClick(View view) {
        if(view == btnSignIn){
            usersignIn();
        }

        if(view == textViewSignUp){
            startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        }
    }
}
