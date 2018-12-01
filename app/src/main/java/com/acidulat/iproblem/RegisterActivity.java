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

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnSignUp;
    private TextView textViewSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnSignUp = (Button)findViewById(R.id.buttonSignIn);
        textViewSignIn = (TextView) findViewById(R.id.textViewSignIn);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        btnSignUp.setOnClickListener(this);
        textViewSignIn.setOnClickListener(this);

    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email) && TextUtils.isEmpty(password)) {
            Toast.makeText(this,"Please enter email and password!", Toast.LENGTH_LONG).show();
            return;

        }
        if(TextUtils.isEmpty(email)) {
             //email is empty
            Toast.makeText(this, "Please enter email!", Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)) {
               //password is empty
            Toast.makeText(this,"Please enter password!",Toast.LENGTH_LONG).show();
            return;
        }

        //if validations are ok
         progressDialog.setMessage("Registering user...");
        progressDialog.show();

         firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()) {
                       //user is succesfully registered and logged in
                       //we will start a activity here
                       Toast.makeText(RegisterActivity.this,"Registered succsesfully",Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(RegisterActivity.this,ProfileActivity.class);
                       startActivity(intent);
                   }
                   else {
                       progressDialog.dismiss();
                       Toast.makeText(RegisterActivity.this,"Could not register...",Toast.LENGTH_LONG).show();
                   }

             }
         });
    }
    @Override
    public void onClick(View view){
        if(view == btnSignUp)
        {
            registerUser();
        }
        if(view == textViewSignIn) {
            //open sign in activity
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        }


}

