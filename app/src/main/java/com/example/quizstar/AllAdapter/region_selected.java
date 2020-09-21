package com.example.quizstar.AllAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizstar.allactivity.RegisterActivity;
import com.example.quizstar.R;

import java.util.List;

public class region_selected extends RecyclerView.Adapter<region_selected.ViewHolder> {
    Context context;
    LayoutInflater inflater;

    List<String> featured_arraylist;
    boolean select = true;

    public region_selected(Context context) {
        this.context = context;
    }

    public region_selected(RegisterActivity registerActivity, List<String> regions) {
        context = registerActivity;
        featured_arraylist = regions;
    }


    @NonNull
    @Override
    public region_selected.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.select_region
                        , parent, false);

        return new region_selected.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final region_selected.ViewHolder holder, final int position) {

        holder.pro_name.setText(featured_arraylist.get(position));
        holder.pro_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String white = "#ffffff";
                int whiteInt = Color.parseColor(white);
                if (select) {
                    holder.pro_name.setBackgroundResource(R.drawable.rect_select_region);
                    holder.pro_name.setTextColor(whiteInt);
                    select = false;
                } else {
                    String white1 = "#3F6AED";
                    int whiteInt1 = Color.parseColor(white1);
                    holder.pro_name.setBackgroundResource(R.drawable.hole_rect);
                    holder.pro_name.setTextColor(whiteInt1);
                    select = true;

                }

                /*Intent intent = new Intent(context, Quiz_Screen.class);
                context.startActivity(intent);*/
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

