package com.example.quizstar.AllAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizstar.allactivity.Api_Payment;
import com.example.quizstar.Model.AllPojo.FeaturesPojo.DataItem;
import com.example.quizstar.R;

import java.util.ArrayList;

public class Top_up_Recycler_view extends RecyclerView.Adapter<Top_up_Recycler_view.ViewHolder> {
    Context context;
    LayoutInflater inflater;

    ArrayList<DataItem> featured_arraylist = new ArrayList<>();

    public Top_up_Recycler_view(Context context) {
        this.context = context;
    }


    @NonNull
    @Override
    public Top_up_Recycler_view.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.top_up_recycler_view
                        , parent, false);

        return new Top_up_Recycler_view.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final Top_up_Recycler_view.ViewHolder holder, final int position) {

       // holder.pro_name.setText(featured_arraylist.get(position).getDisplayName());
        holder.rela_blance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Api_Payment.class);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pro_name;
        RelativeLayout rela_blance;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rela_blance = itemView.findViewById(R.id.rela_blance);
            //pro_name = itemView.findViewById(R.id.name);
        }
    }

}

