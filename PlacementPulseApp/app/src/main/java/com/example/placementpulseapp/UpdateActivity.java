package com.example.placementpulseapp;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class UpdateActivity extends AppCompatActivity {
   EditText name,lastdate,lpa,eligibility,description,type;
   Button update_button,delete_button;
   String id,cname,desc,lp,elig,last,typ;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);
        name=findViewById(R.id.name2);
        lastdate=findViewById(R.id.lastdate2);
        lpa=findViewById(R.id.lpa2);
        eligibility=findViewById(R.id.eligibility2);
        description=findViewById(R.id.description2);
        type=findViewById(R.id.type2);
        update_button=findViewById(R.id.update_button);
        delete_button=findViewById(R.id.delete_button);

        //First we call this
        getAndSetIntentData();

        ActionBar ab=getSupportActionBar();
        if(ab!=null)
        {
            ab.setTitle(cname);
        }


        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get updated values from EditText fields
                cname = name.getText().toString();
                last = lastdate.getText().toString();
                lp = lpa.getText().toString();
                elig = eligibility.getText().toString();
                desc = description.getText().toString();
                typ = type.getText().toString();

                Toast.makeText(UpdateActivity.this, "Attempting update...", Toast.LENGTH_SHORT).show();
                // Now pass the updated values to updateData()
                DBHelper dbHelper = new DBHelper(UpdateActivity.this);
                boolean isUpdated = dbHelper.updateData(id, cname, last, lp, elig, desc, typ);

                if (isUpdated) {
                    Toast.makeText(UpdateActivity.this, "Update successful!", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                } else {
                    Toast.makeText(UpdateActivity.this, "Update failed!", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });




    }
    void getAndSetIntentData()
    {
        if(getIntent().hasExtra("id")&& getIntent().hasExtra("name")&&getIntent().hasExtra("lastdate")&&
        getIntent().hasExtra("lpa")&& getIntent().hasExtra("eligibility")&&
                getIntent().hasExtra("description") && getIntent().hasExtra("type"))
        {
            //getting data from intent
            id=getIntent().getStringExtra("id");
            cname=getIntent().getStringExtra("name");
            last=getIntent().getStringExtra("lastdate");
            lp=getIntent().getStringExtra("lpa");
            elig=getIntent().getStringExtra("eligibility");
            desc=getIntent().getStringExtra("description");
            typ=getIntent().getStringExtra("type");
            //setting intentdata
            name.setText(cname);
            lastdate.setText(last);
            lpa.setText(lp);
            eligibility.setText(elig);
            description.setText(desc);
            type.setText(typ);

        }
        else
        {
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog()
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Delete "+cname+ " ?");
        builder.setMessage("Are you Sure you want to Delete"+ cname+ " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper dbHelper=new DBHelper(UpdateActivity.this);
                dbHelper.deleteOneRow(id);
                setResult(RESULT_OK);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();

    }


}