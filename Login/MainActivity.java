package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private static final String VALID_USERNAME="user";
    private static final String VALID_PASSWORD="pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText usernameEditText=findViewById(R.id.username);
        EditText passwordEditText=findViewById(R.id.password);
        Button loginButton=findViewById(R.id.button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=usernameEditText.getText().toString();
                String password=passwordEditText.getText().toString();

                if(VALID_USERNAME.equals(username) && VALID_PASSWORD.equals(password)){
                    Toast.makeText(MainActivity.this,"Login Successfull",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Invalid Credentials",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
