package com.example.placementpulseapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeAdminActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageView;
    TextView no_data;
    DBHelper dbHelper;
    ArrayList<String> pl_id,pl_name,pl_lastdate,pl_lpa,pl_eligibility,pl_desc,pl_type;
    CustomAdapter customAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home_admin);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        add_button = (FloatingActionButton) findViewById(R.id.add_button);
        empty_imageView=(ImageView) findViewById(R.id.empty_imageView);
        no_data=(TextView)findViewById(R.id.textView);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeAdminActivity.this, AddActivity.class);
                startActivityForResult(intent, 1);
            }
        });
        dbHelper = new DBHelper(HomeAdminActivity.this);
        pl_id = new ArrayList<>();
        pl_name = new ArrayList<>();
        pl_type = new ArrayList<>();
        pl_lpa = new ArrayList<>();
        pl_eligibility = new ArrayList<>();
        pl_lastdate = new ArrayList<>();
        pl_desc = new ArrayList<>();


        storedataInArrays();

        customAdapter = new CustomAdapter(HomeAdminActivity.this,this, pl_id, pl_name, pl_lastdate, pl_lpa, pl_eligibility, pl_desc, pl_type,true);

        if (customAdapter != null) {
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Refresh the data
            pl_id.clear();
            pl_name.clear();
            pl_type.clear();
            pl_lpa.clear();
            pl_eligibility.clear();
            pl_lastdate.clear();
            pl_desc.clear();

            storedataInArrays(); // Fetch the updated data from the database
            customAdapter.notifyDataSetChanged(); // Notify the adapter about data changes
        }
    }


    void storedataInArrays() {
        Log.d("DataCheck", "storedataInArrays called");
        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageView.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();

        } else {

            while (cursor.moveToNext()) {
                String id = cursor.getString(0) != null ? cursor.getString(0) : "N/A";
                String name = cursor.getString(1) != null ? cursor.getString(1) : "N/A";
                String type = cursor.getString(2) != null ? cursor.getString(2) : "N/A";
                String lpa = cursor.getString(3) != null ? cursor.getString(3) : "N/A";
                String eligibility = cursor.getString(4) != null ? cursor.getString(4) : "N/A";
                String lastdate = cursor.getString(5) != null ? cursor.getString(5) : "N/A";
                String desc = cursor.getString(6) != null ? cursor.getString(6) : "N/A";

                pl_id.add(id);
                pl_name.add(name);
                pl_type.add(type);
                pl_lpa.add(lpa);
                pl_eligibility.add(eligibility);
                pl_lastdate.add(lastdate);
                pl_desc.add(desc);

            }
            empty_imageView.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

}