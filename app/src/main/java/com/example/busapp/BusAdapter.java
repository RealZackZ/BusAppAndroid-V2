package com.example.busapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BusAdapter extends BaseAdapter {

    private Context applicationContext;
    private int sample;
    private ArrayList<BusPOJO> Buslist;


    BusAdapter(Context applicationContext, int sample, ArrayList<BusPOJO> Buslist) {

        this.applicationContext =applicationContext;
        this.sample = sample;
        this.Buslist =Buslist;

    }


    @Override
    public int getCount() {
        return Buslist.size();
    }

    @Override
    public Object getItem(int i) {
        return Buslist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {



        if(view == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater) applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view =  layoutInflater.inflate(R.layout.adapter,viewGroup,false);

        }

        TextView date,from,to;


        date= view.findViewById(R.id.date);
        from=view.findViewById(R.id.from);
        to=view.findViewById(R.id.to);


        date.setText(Buslist.get(i).getBusDate());
      //  Log.d("TEST32423423", Buslist.get(1).getBusDate());
        from.setText(Buslist.get(i).getBusTo());
        to.setText(Buslist.get(i).getBusFrom());

        return view;
    }
}