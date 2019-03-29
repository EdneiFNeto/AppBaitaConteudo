package com.example.appbaitaconteudo;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private VideoView videoView;
    private String TAG = "MainLog";
    private ListView listView;
    private RelativeLayout relativeLayout;
    private WindowManager windowManager;
    private int screenWidth, screenHeight;
    private Toolbar myToolbar;
    private ProgressBar spinner;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToggle;
    private boolean isladscape;


    @Override
    protected void onPause() {
        super.onPause();
        videoView.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoview);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        myToolbar.inflateMenu(R.menu.menu);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_logo_2);

        spinner = (ProgressBar) findViewById(R.id.progress_bar);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //mToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        //drawerLayout.setDrawerListener(mToggle);
        //mToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        try {
            windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
            //detect size display
            Display display = windowManager.getDefaultDisplay();
            DisplayMetrics metrics = new DisplayMetrics();
            display.getMetrics(metrics);

            //setting size display
            screenWidth = metrics.widthPixels;
            screenHeight = metrics.heightPixels;

            Configuration configuration = getResources().getConfiguration();

            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                myToolbar.setVisibility(View.GONE);
                fullScreen();
            } else {
                myToolbar.setVisibility(View.VISIBLE);
            }

            playVideo(0);
        } catch (
                Exception e) {
            Log.e(TAG, e.getMessage());
        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        try {
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                myToolbar.setVisibility(View.GONE);
                fullScreen();

            } else {
                showSystemUI();
                myToolbar.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error changed orientation: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    public void fullScreen() {

        try {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                    int uiOptions =
                            View.SYSTEM_UI_FLAG_IMMERSIVE
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN;
                    View decorView = getWindow().getDecorView();
                    decorView.setSystemUiVisibility(uiOptions);
                }
            }, 1000);

        } catch (Exception e) {
            Toast.makeText(this, "Error fullscreen: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void showSystemUI() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                View decorView = getWindow().getDecorView();
                decorView.setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

            }
        }, 1000);
    }


    private void playVideo(int position) {

        String url = "http://189.45.13.225/stream.php.m3u8?user=baita&pass=2018tv&token=1553733132&resptime=109&s=stream";
        String channel = "";

        switch (position) {
            case 0:
                channel = url + "29.m3u8";
                break;
            case 1:
                channel = url + "39.m3u8";
                break;
            case 2:
                channel = url + "41.m3u8";
                break;
            case 3:
                channel = url + "44.m3u8";
                break;
            case 4:
                channel = url + "31.m3u8";
                break;
            case 5:
                channel = url + "28.m3u8";
                break;

            case 6:
                channel = url + "27.m3u8";
                break;

            case 7:
                channel = url + "36.m3u8";
                break;

            case 8:
                channel = url + "35.m3u8";
                break;

            case 9:
                channel = url + "42.m3u8";
                break;

            case 10:
                channel = url + "37.m3u8";
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
                spinner.setVisibility(View.GONE);
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                spinner.setVisibility(View.GONE);
                videoView.setBackgroundResource(R.drawable.ic_error_video);
                return true;
            }
        });

        videoView.requestFocus();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.channel_0:
                playVideo(0);
                break;
            case R.id.channel_1:
                playVideo(1);
                break;
            case R.id.channel_2:
                playVideo(2);
                break;
            case R.id.channel_3:
                playVideo(3);
                break;
            case R.id.channel_4:
                playVideo(4);
                break;
            case R.id.channel_5:
                playVideo(5);
                break;

            case R.id.channel_6:
                playVideo(6);
                break;

            case R.id.channel_7:
                playVideo(7);
                break;

            case R.id.channel_8:
                playVideo(8);
                break;

            case R.id.channel_9:
                playVideo(9);
                break;

            case R.id.channel_10:
                playVideo(10);
                break;

        }
        //close on click item
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
}
