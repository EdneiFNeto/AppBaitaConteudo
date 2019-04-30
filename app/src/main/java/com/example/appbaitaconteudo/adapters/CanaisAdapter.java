package com.example.appbaitaconteudo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.example.appbaitaconteudo.R;

public class CanaisAdapter extends BaseAdapter {

    private int [] icons ={

            R.drawable.ic_item_bora_filmes,
            R.drawable.ic_item_clubinho,
            R.drawable.ic_item_mosaico,
            R.drawable.ic_item_mosaico,
            R.drawable.ic_item_entreter,
            R.drawable.ic_item_life_channel,
            R.drawable.ic_item_hello_tv,
            R.drawable.ic_item_full_music,
            R.drawable.ic_item_you_channel,
            R.drawable.ic_item_canal_promessas,
            R.drawable.ic__item_24h_news,
    };

    private Context context;

    public CanaisAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

    @Override
    public Object getItem(int position) {
        return icons[position];
    }

    @Override
    public long getItemId(int position) {
        return icons[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;

        if (view == null) {
            view = inflater.inflate(R.layout.list_item_channel, parent, false);
        }

        ImageView icon_channel = (ImageView) view.findViewById(R.id.icon_channel);
        icon_channel.setImageResource(icons[position]);
        return view;
    }
}
