package com.example.placementpulseapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
   EditText username,password;
   Button btnLogin;
   DBHelper myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        username=(EditText) findViewById(R.id.usernameLogin);
        password=(EditText) findViewById(R.id.passwordLogin);
        btnLogin=(Button) findViewById(R.id.btnSignIn);

        myDB=new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(user.equals("")|| pass.equals(""))
                {
                    Toast.makeText(LoginActivity.this,"Please Enter the credentials",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean result=myDB.checkUsernamePassword(user,pass);
                    if(result == true && user.equals("admin")) {
                        Intent intent = new Intent(LoginActivity.this, HomeAdminActivity.class);
                        startActivity(intent);
                    } else if(result == true && !user.equals("admin")) {
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}