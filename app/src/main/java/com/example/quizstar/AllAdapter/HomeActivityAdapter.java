package com.example.quizstar.AllAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizstar.allactivity.Api_Payment;
import com.example.quizstar.allactivity.Challenges;
import com.example.quizstar.allactivity.ui.Quiz_Screen;
import com.example.quizstar.R;
import com.example.quizstar.Model.AllPojo.FeaturesPojo.DataItem;

import java.util.ArrayList;

public class HomeActivityAdapter extends RecyclerView.Adapter<HomeActivityAdapter.ViewHolder> {
    Context context;
    LayoutInflater inflater;

    ArrayList<DataItem> featured_arraylist = new ArrayList<>();

    public HomeActivityAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void add_data(ArrayList<DataItem> featured_arraylist) {
        this.featured_arraylist = new ArrayList<>();
        this.featured_arraylist.clear();
        this.featured_arraylist = featured_arraylist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_home_list
                        , parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        if(featured_arraylist.get(position).getEnabled() == 1 && !"iap".equals(featured_arraylist.get(position).getFeature())){
            holder.pro_name.setText(featured_arraylist.get(position).getDisplayName());
        }else{
            holder.pro_name.setVisibility(View.GONE);
        }



/*
        if (featured_arraylist.get(position).getDisplayName().equalsIgnoreCase("Apple In App Payments")) {

        } else {

        }*/
        holder.pro_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (featured_arraylist.get(position).getDisplayName().equalsIgnoreCase("Quiz Star")) {
                    Intent intent = new Intent(context, Quiz_Screen.class);
                    context.startActivity(intent);
                } else if (featured_arraylist.get(position).getDisplayName().equalsIgnoreCase("Quiz Star challenge")) {
                    Intent intent = new Intent(context, Challenges.class);
                    context.startActivity(intent);
                } else if (featured_arraylist.get(position).getDisplayName().equalsIgnoreCase("Apple in App Payments")) {
                    Intent intent = new Intent(context, Api_Payment.class);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, Challenges.class);
                    context.startActivity(intent);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return featured_arraylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pro_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            pro_name = itemView.findViewById(R.id.name);
        }
    }

}

