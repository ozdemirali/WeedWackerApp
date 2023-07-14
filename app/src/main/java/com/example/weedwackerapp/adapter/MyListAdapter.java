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

public class MyListAdapter extends BaseAdapter {

    final Context context;
    final String[] mainTitle;
    final String[] subTitle;
    final Integer[] imgId;
    LayoutInflater inflater;

    public MyListAdapter(Context context, String[] mainTitle, String[] subTitle, Integer[] imgId ){

        this.context = context;
        this.mainTitle = mainTitle;
        this.subTitle = subTitle;
        this.imgId = imgId;
        inflater = LayoutInflater.from(context);


    }

    @Override
    public int getCount() {
        return mainTitle.length;
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

        txtview.setText(mainTitle[position]);
        txtview2.setText(subTitle[position]);
        imgview.setImageResource(imgId[position]);
        return convertView;
    }
}