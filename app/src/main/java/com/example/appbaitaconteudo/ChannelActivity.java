package com.example.appbaitaconteudo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ChannelActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private RelativeLayout relative_layout_channel;
    private ImageView menu_toolbar;
    private DrawerLayout drawer_layout;
    private String channel;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new AdapterChannel(this));
        listView.setOnItemClickListener(this);

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer_layout   = (DrawerLayout) findViewById(R.id.drawer_layout_channel);
        menu_toolbar    = (ImageView) findViewById(R.id.menu_toolbar);

        menu_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });

        Intent intent = getIntent();
        channel = intent.getStringExtra("channel");
        if(channel!= null){
            Toast.makeText(this, "Channel "+ channel, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_user:
                Toast.makeText(this, "user", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case 0:
                Toast.makeText(this, "Position "+0, Toast.LENGTH_SHORT).show();
                break;
        }

        Intent intent = new Intent(ChannelActivity.this, ChannelActivity.class);
        intent.putExtra("channel", position);
        startActivity(intent);
        //drawer_layout.openDrawer(Gravity.START);
    }
}
