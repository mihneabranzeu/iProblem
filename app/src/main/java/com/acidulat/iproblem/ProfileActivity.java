package com.acidulat.iproblem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class ProfileActivity extends AppCompatActivity  {

    private TextView userText;
    private Button logOutButton;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()== null)
        {

            finish();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

        //logOutButton = (Button) findViewById(R.id.logOutBtn);



        //logOutButton.setOnClickListener(this);
    }

    /*@Override
    public void onClick(View view) {
        if(view == logOutButton) {
               firebaseAuth.signOut();
               finish();
               startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }

    }*/

}
