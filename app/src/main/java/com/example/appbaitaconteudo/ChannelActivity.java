package com.example.appbaitaconteudo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.codesgood.views.JustifiedTextView;
import com.example.appbaitaconteudo.model.Canal;


public class ChannelActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Toolbar toolbar;
    private RelativeLayout relative_layout_channel;
    private ImageView menu_toolbar;
    private DrawerLayout drawer_layout;
    private String channel;
    private ListView listView;
    private String TAG = "ChannelLog";
    private TextView text_title;
    private JustifiedTextView text_descr;
    private VideoView videoView;
    private ProgressBar spinner;
    private WindowManager windowManager;
    private int screenWidth;
    private int screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channel);

        listView = (ListView) findViewById(R.id.listView);
        toolbar = (Toolbar) findViewById(R.id.my_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout_channel);
        menu_toolbar = (ImageView) findViewById(R.id.menu_toolbar);

        menu_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer_layout.openDrawer(Gravity.LEFT);
            }
        });

        videoView = (VideoView) findViewById(R.id.vide_view_channel);
        text_title = (TextView) findViewById(R.id.title_channel);
        text_descr = (JustifiedTextView) findViewById(R.id.tv_justified_paragraph);
        spinner = (ProgressBar) findViewById(R.id.spinner_channel);
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
    }

    private boolean isLadiscapeOrientation() {

        //setting size display
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE)
            return true;

        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();

        //detect size display
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        listView.setAdapter(new AdapterChannel(this));
        listView.setOnItemClickListener(this);

        Intent intent = getIntent();
        channel = intent.getStringExtra("channel");

        if (channel != null) {
            selectChannel(Integer.parseInt(channel));
            play(Integer.parseInt(channel));
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        try {

            if (isLadiscapeOrientation()) {
                visibleAndHideComponets(View.GONE);
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            } else {
                visibleAndHideComponets(View.VISIBLE);
                drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error changed orientation: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void visibleAndHideComponets(int visible) {

        text_title.setVisibility(visible);
        text_descr.setVisibility(visible);
        toolbar.setVisibility(visible);
    }

    private void play(int position) {

        String url = "http://189.45.13.225/stream.php.m3u8?user=baita&pass=2018tv&token=1553733132&resptime=109&s=stream";
        String channel = "";

        switch (position) {
            case 0:
                channel = url + "39.m3u8";//bora films
                break;
            case 1:
                channel = url + "41.m3u8";//clubinho
                break;
            case 2:
                channel = url + "27.m3u8";//rede mosaico
                break;
            case 3:
                channel = url + "42.m3u8";//up channel
                break;
            case 4:
                channel = url + "37.m3u8";//entreter
                break;
            case 5:
                channel = url + "31.m3u8";//life
                break;

            case 6:
                channel = url + "44.m3u8";//hello tv
                break;

            case 7:
                channel = url + "28.m3u8";//full music
                break;

            case 8:
                channel = url + "35.m3u8";//you channel
                break;

            case 9:
                channel = url + "36.m3u8";//canal promessa
                break;

            case 10:
                channel = url + "29.m3u8";//24h news
                break;
        }

        videoView.setVideoURI(Uri.parse(channel));
        spinner.setVisibility(View.VISIBLE);

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e(TAG, "Vide completo....");
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
                videoView.setBackgroundResource(0);
                spinner.setVisibility(View.GONE);
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                spinner.setVisibility(View.GONE);
                videoView.setBackgroundResource(R.drawable.imagem_404_baitaplay);
                return true;
            }
        });

        videoView.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_user:
                Toast.makeText(this, "user", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        drawer_layout.closeDrawer(Gravity.START, true);//close navigation view
        selectChannel(position);
        play(Integer.parseInt(channel));
    }

    private void selectChannel(final int channel) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {

                text_title.setText(Canal.nome[channel]);
                text_descr.setText(Canal.descr[channel]);
                //videoView.setBackgroundResource(Canal.imgs[channel]);
            }
        });
    }

}
