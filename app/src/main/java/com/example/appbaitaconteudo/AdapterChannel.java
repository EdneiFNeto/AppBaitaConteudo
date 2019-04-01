package com.example.appbaitaconteudo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AdapterChannel extends BaseAdapter {

    public int[] channels = new int[]{
            R.string.h_news,
            R.string.bora_filmes,
            R.string.canal_promessas,
            R.string.clubinho,
            R.string.entreter,
            R.string.full_music,
            R.string.hello_tv,
            R.string.mosaico,
            R.string.up_channel,
            R.string.yu_channel,
    };

    public int[] icons = new int[]{
            R.mipmap.ic_24h_new,
            R.mipmap.ic_bora_filmes,
            R.mipmap.ic_canal_promessas,
            R.mipmap.ic_clubinho,
            R.mipmap.ic_entreter,
            R.mipmap.ic_full_music,
            R.mipmap.ic_hello_tv,
            R.mipmap.ic_mosaico,
            R.mipmap.ic_up_channel,
            R.mipmap.ic_yu_channel,
    };

    public Context context;

    public AdapterChannel(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.channels.length;
    }

    @Override
    public Object getItem(int position) {
        return this.channels[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int channel = channels[position];
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);

        ImageView icon = (ImageView) view.findViewById(R.id.image_icon);
        icon.setImageResource(icons[position]);

        TextView t = (TextView) view.findViewById(R.id.textChannel);
        t.setText(channel);
        return view;
    }
}
