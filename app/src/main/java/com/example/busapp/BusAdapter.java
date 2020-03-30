package com.example.busapp;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends ArrayAdapter<BusPOJO>  {

    public BusAdapter(@NonNull Context context, @NonNull ArrayList<BusPOJO> busList) {
        super(context, 0, busList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder = null;

        BusPOJO busPOJO = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter, parent, false);
            //holder = new ViewHolder();
            convertView.setTag(holder);

        }

        else {
            holder = (ViewHolder) convertView.getTag();
        }

        TextView tvDate = (TextView) convertView.findViewById(R.id.date);
        TextView tvFrom = (TextView) convertView.findViewById(R.id.from);
        TextView tvTo = (TextView) convertView.findViewById(R.id.to);

        tvDate.setText(busPOJO.getBusDate());
        tvFrom.setText(busPOJO.getBusFrom());
        tvTo.setText(busPOJO.getBusTo());

        return convertView;
    }







}

class ViewHolder extends RecyclerView.ViewHolder {
    public ViewHolder(View v) {
        super(v);
    }


}

