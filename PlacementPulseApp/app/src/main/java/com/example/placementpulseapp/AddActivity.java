package com.example.placementpulseapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddActivity extends AppCompatActivity {
   EditText name,lastdate,lpa,eligibility,description,type;
   Button add_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        name=findViewById(R.id.name);
        lastdate=findViewById(R.id.lastdate);
        lpa=findViewById(R.id.lpa);
        eligibility=findViewById(R.id.eligibility);
        description=findViewById(R.id.description);
        type=findViewById(R.id.type);
        add_button=findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbHelper=new DBHelper(AddActivity.this);
                dbHelper.addPlacement(name.getText().toString().trim(),lastdate.getText().toString().trim(),
                        Integer.valueOf(lpa.getText().toString().trim()),eligibility.getText().toString().trim(),
                        description.getText().toString().trim(),type.getText().toString().trim());
                        // Set the result to indicate that a new placement has been added
                        setResult(RESULT_OK);
                finish();
            }
        });

    }
}