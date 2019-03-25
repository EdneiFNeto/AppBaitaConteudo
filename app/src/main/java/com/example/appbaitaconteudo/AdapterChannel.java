package com.example.appbaitaconteudo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class AdapterChannel extends BaseAdapter {

    public String[] planetas = new String[]{"Canal 1", "canal 2", "Canal 3", "Canal 4"};
    public Context context;

    public AdapterChannel(Context context){

        this.context = context;
    }

    @Override
    public int getCount() {
        return this.planetas.length;
    }

    @Override
    public Object getItem(int position) {
        return this.planetas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String planeta = planetas[position];
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        TextView t = (TextView) view.findViewById(R.id.textChannel);
        t.setText(planeta);
        return view;
    }
}
