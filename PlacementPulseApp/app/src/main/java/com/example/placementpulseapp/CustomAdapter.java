package com.example.placementpulseapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private final Context context;
    Activity activity;
    private final ArrayList <String>pl_id;
    private final ArrayList <String> pl_name;
    private final ArrayList <String> pl_lastdate;
    private final ArrayList <String> pl_lpa;
    private final ArrayList <String> pl_eligibility;
    private final ArrayList <String> pl_desc;
    private final ArrayList <String> pl_type;
    private boolean isAdmin;
//    int position;
    CustomAdapter(HomeAdminActivity homeAdminActivity, Context context, ArrayList<String> pl_id, ArrayList<String> pl_name, ArrayList<String> pl_lastdate,
                  ArrayList<String> pl_lpa, ArrayList<String> pl_eligibility, ArrayList<String> pl_desc, ArrayList<String> pl_type,boolean isAdmin) {
        this.context = context;
        this.pl_id = pl_id;
        this.pl_name = pl_name;
        this.pl_lastdate = pl_lastdate;
        this.pl_lpa = pl_lpa;
        this.pl_eligibility = pl_eligibility;
        this.pl_desc = pl_desc;
        this.pl_type = pl_type;
        this.isAdmin = isAdmin;
    }

    public CustomAdapter(Context context, ArrayList<String> pl_id, ArrayList<String> pl_name,
                         ArrayList<String> pl_lastdate, ArrayList<String> pl_lpa,
                         ArrayList<String> pl_eligibility, ArrayList<String> pl_desc,
                         ArrayList<String> pl_type,boolean isAdmin) {
        this.context = context;
        this.pl_id = pl_id;
        this.pl_name = pl_name;
        this.pl_lastdate = pl_lastdate;
        this.pl_lpa = pl_lpa;
        this.pl_eligibility = pl_eligibility;
        this.pl_desc = pl_desc;
        this.pl_type = pl_type;
        this.isAdmin=isAdmin;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view);
    }

    @Override

    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
//        this.position=position;

        holder.pl_id_text.setText(String.valueOf(pl_id.get(position)));
        holder.pl_name.setText(String.valueOf(pl_name.get(position)));
        holder.pl_desc.setText(String.valueOf(pl_desc.get(position)));
        holder.pl_eligibility.setText(String.valueOf(pl_eligibility.get(position)));
        holder.pl_lpa.setText(String.valueOf(pl_lpa.get(position)));
        holder.pl_lastdate.setText(String.valueOf(pl_lastdate.get(position)));
        holder.pl_type.setText(String.valueOf(pl_type.get(position)));
        if (isAdmin) {
            holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, UpdateActivity.class);
                    intent.putExtra("id", String.valueOf(pl_id.get(position)));
                    intent.putExtra("name", String.valueOf(pl_name.get(position)));
                    intent.putExtra("description", String.valueOf(pl_desc.get(position)));
                    intent.putExtra("eligibility", String.valueOf(pl_eligibility.get(position)));
                    intent.putExtra("lpa", String.valueOf(pl_lpa.get(position)));
                    intent.putExtra("lastdate", String.valueOf(pl_lastdate.get(position)));
                    intent.putExtra("type", String.valueOf(pl_type.get(position)));


                    if (context instanceof Activity) {
                        ((Activity) context).startActivityForResult(intent, 1);
                    } else {
                        // Handle if context is not an instance of Activity
                        context.startActivity(intent);
                    }
                }
            });
        }
        else {
            holder.mainLayout.setOnClickListener(null);
        }
    }


    @Override
    public int getItemCount() {
        return pl_id.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView pl_id_text,pl_name,pl_desc,pl_eligibility,pl_lpa,pl_lastdate,pl_type;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pl_id_text=itemView.findViewById(R.id.pl_id_txt);
            pl_name=itemView.findViewById(R.id.name);
            pl_desc=itemView.findViewById(R.id.desc);
            pl_eligibility =itemView.findViewById(R.id.eligibility);
            pl_lpa=itemView.findViewById(R.id.lpa);
            pl_lastdate=itemView.findViewById(R.id.lastdate);
            pl_type=itemView.findViewById(R.id.type);
           mainLayout=itemView.findViewById(R.id.mainLayout);

        }
    }
}
