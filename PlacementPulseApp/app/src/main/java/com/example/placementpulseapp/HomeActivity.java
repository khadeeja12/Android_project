package com.example.placementpulseapp;

import android.database.Cursor;
import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DBHelper dbHelper;
    ArrayList<String> pl_id, pl_name, pl_lastdate, pl_lpa, pl_eligibility, pl_desc, pl_type;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recycleView);

        dbHelper = new DBHelper(HomeActivity.this);
        pl_id = new ArrayList<>();
        pl_name = new ArrayList<>();
        pl_type = new ArrayList<>();
        pl_lpa = new ArrayList<>();
        pl_eligibility = new ArrayList<>();
        pl_lastdate = new ArrayList<>();
        pl_desc = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(this, pl_id, pl_name, pl_lastdate, pl_lpa, pl_eligibility, pl_desc, pl_type,false);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void storeDataInArrays() {
        Cursor cursor = dbHelper.readAllData();
        if (cursor.getCount() > 0) {
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
        }
        cursor.close(); // Ensure the cursor is closed after use
    }
}
