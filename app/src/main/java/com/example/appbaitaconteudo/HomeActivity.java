package com.example.appbaitaconteudo;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appbaitaconteudo.adapters.CanaisAdapter;
import com.example.appbaitaconteudo.dao.UserDao;
import com.example.appbaitaconteudo.model.User;

import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private GridView gridView;
    private ListView listView;
    private Toolbar toolbar;
    private ImageView menu_toolbar;
    private NavigationView navigation_view;
    private DrawerLayout drawer_layout;
    private String TAG = "HomeLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);

        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(new CanaisAdapter(this));
        gridView.setOnItemClickListener(this);

        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new AdapterChannel(this));
        listView.setOnItemClickListener(this);

        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        menu_toolbar = (ImageView) findViewById(R.id.menu_toolbar);

        menu_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });

        toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //select data bb
        UserDao dao = new UserDao(this);
        try {
            List<User> userList  = dao.listar();
            for (User users: userList){
                Log.e(TAG,"Nome: "+ users.getNome());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_user_logado:
                startActivity(new Intent(HomeActivity.this, UsuarioActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(HomeActivity.this, ChannelActivity.class);
        intent.putExtra("channel", ""+position);
        startActivity(intent);

        drawer_layout.closeDrawer(Gravity.START, true);
    }
}
