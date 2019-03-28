package com.example.appbaitaconteudo;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnTouchListener {

    VideoView videoView;
    String TAG = "MainLog";
    ListView listView;
    RelativeLayout relativeLayout;

    String url = "http://189.45.13.225/stream.php.m3u8?user=baita&pass=2018tv&token=1553733132&resptime=109&s=stream29.m3u8";

    WindowManager windowManager;
    private int screenWidth, screenHeight;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoview);
        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(new AdapterChannel(this));
        listView.setOnItemClickListener(this);

        relativeLayout = (RelativeLayout) findViewById(R.id.relativelayout);
        relativeLayout.setOnTouchListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        //detect size display
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);

        //setting size display
        screenWidth = metrics.widthPixels;
        screenHeight = metrics.heightPixels;

        try {
            playVideo(0);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try{

        playVideo(position);
        }catch (Exception e){
            Toast.makeText(this, "Error click", Toast.LENGTH_LONG).show();
        }
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
        }

        videoView.setVideoURI(Uri.parse(channel));
        listView.setVisibility(View.GONE);

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
            }
        });

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.e(TAG, "Error: " + what);
                return false;
            }
        });

        videoView.requestFocus();
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
                        listView.setVisibility(View.VISIBLE);
                    }

                    if ((int) x > (int) width) {
                        listView.setVisibility(View.GONE);
                    }

                    break;
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error " + e.getMessage(), Toast.LENGTH_LONG).show();
        }


        return false;
    }
}
