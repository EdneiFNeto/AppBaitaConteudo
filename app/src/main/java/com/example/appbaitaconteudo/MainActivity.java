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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        View.OnTouchListener {

    private VideoView videoView;
    private String TAG = "MainLog";
    private ListView listView;
    private RelativeLayout relativeLayout;
    private WindowManager windowManager;
    private int screenWidth, screenHeight;
    private Toolbar myToolbar;
    private ProgressBar spinner;
    private LinearLayout menu_left, toolbar, img_nav;
    private DrawerLayout drawer_layout;
    private ImageView footer, menu_toolbar;


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
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new AdapterChannel(this));

        listView.setOnItemClickListener(this);
        listView.setSelection(0);
        listView.setItemChecked(0, true);

        spinner = (ProgressBar) findViewById(R.id.progress_bar);
        menu_left = (LinearLayout) findViewById(R.id.menu_left);
        toolbar = (LinearLayout) findViewById(R.id.toolbar);

        menu_toolbar = (ImageView) findViewById(R.id.menu_toolbar);
        menu_toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_left.setVisibility(View.VISIBLE);
            }
        });

        //img_nav = (LinearLayout) findViewById(R.id.img_nav);

        footer = (ImageView) findViewById(R.id.footer);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer_layout.setOnTouchListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        return true;
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
                visibleAndHideComponets(View.GONE);
                fullScreen();
            } else {
                visibleAndHideComponets(View.VISIBLE);
            }

            playVideo(1);
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

                visibleAndHideComponets(View.GONE);
                fullScreen();

            } else {
                showSystemUI();
                visibleAndHideComponets(View.VISIBLE);
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
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        playVideo(position);

        new Handler().post(new Runnable() {
            @Override
            public void run() {
                menu_left.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        try {

            float x = event.getX();
            float y = event.getY();
            float width = screenWidth / 2;
            float height = screenHeight;

            switch (event.getAction()) {

                case MotionEvent.ACTION_DOWN:

                    if (((int) x >= 10 && (int) x <= (int) width)) {
                        //menu_left.setVisibility(View.VISIBLE);
                    }

                    if ((int) x > (int) width) {
                        menu_left.setVisibility(View.GONE);
                    }

                    break;
            }

        } catch (Exception e) {
            Log.e(TAG, "Error " + e.getMessage());
        }

        return false;
    }

    public void visibleAndHideComponets(int visible) {
        menu_left.setVisibility(visible);
        toolbar.setVisibility(visible);
        footer.setVisibility(visible);
        //img_nav.setVisibility(visible);
    }
}
