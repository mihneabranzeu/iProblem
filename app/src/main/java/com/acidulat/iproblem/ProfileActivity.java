package com.acidulat.iproblem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    TextView userText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        userText = (TextView) findViewById(R.id.textView2);
        
    }
}
