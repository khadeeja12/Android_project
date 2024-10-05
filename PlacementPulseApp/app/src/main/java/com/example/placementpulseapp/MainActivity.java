package com.example.placementpulseapp;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
   EditText  username,password,repassword;
   Button btnSignUp,btnSignIn;
   DBHelper myDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.username);
        password=(EditText) findViewById(R.id.password);
        repassword=(EditText) findViewById(R.id.repassword);
        btnSignUp=(Button) findViewById(R.id.btnSignUp);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);



        myDB=new DBHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();

                if(user.equals("")||pass.equals("")||repass.equals(""))
                {
                    Toast.makeText(MainActivity.this,"fill all the fields",Toast.LENGTH_SHORT).show();
                }
                else
                {
                   if(pass.equals(repass))
                   {
                       Boolean userCheckResult=myDB.checkUsername(user);
                       if(userCheckResult==false)
                       {
                           Boolean regResult=myDB.insertData(user,pass);
                           if(regResult==true)
                           {
                               Toast.makeText(MainActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                               Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                               startActivity(intent);
                           }
                           else
                           {
                               Toast.makeText(MainActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                           }
                       }
                       else
                       {
                           Toast.makeText(MainActivity.this,"User already exists \n Please Sign Up",Toast.LENGTH_SHORT).show();
                       }
                   }
                   else
                   {
                       Toast.makeText(MainActivity.this,"Password not match",Toast.LENGTH_SHORT).show();
                   }
                }
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });
    }
}