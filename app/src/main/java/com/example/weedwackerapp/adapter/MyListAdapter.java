package com.example.weedwackerapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.weedwackerapp.R;
import com.example.weedwackerapp.fragments.HomeFragment;

import java.util.List;

public class MyListAdapter extends BaseAdapter {

    final Context context;
    final List<String> mainTitle;
    final List<String> subTitle;
    final List<Integer> imgId;
    LayoutInflater inflater;

    public MyListAdapter(Context context, List<String> mainTitle, List<String> subTitle, List<Integer> imgId ){

        this.context = context;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.imgId = imgId;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return mainTitle.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.mylist, null);
        TextView txtview = (TextView) convertView.findViewById(R.id.subtitle);
        TextView txtview2 = (TextView)  convertView.findViewById(R.id.title);
        ImageView imgview = (ImageView) convertView.findViewById(R.id.icon);

        txtview.setText(subTitle.get(position));
        txtview2.setText(mainTitle.get(position));
        imgview.setImageResource(imgId.get(position));
        return convertView;
    }
}