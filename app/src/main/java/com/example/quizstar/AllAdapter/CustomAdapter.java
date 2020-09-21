package com.example.quizstar.AllAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizstar.R;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends BaseAdapter {
    Context context;
    ArrayList<List<String>> regionList=new ArrayList<java.util.List<String>>();
    ArrayList<String> demolist=new ArrayList<>();
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, ArrayList<String> demolist) {
        this.context = applicationContext;
        this.demolist = demolist;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return demolist.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        TextView names = (TextView) view.findViewById(R.id.textView);

            names.setText(demolist.get(i));

        return view;
    }
}

