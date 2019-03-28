package com.example.appbaitaconteudo;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.appbaitaconteudo.enums.URLEnums;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    VideoView videoView;
    String TAG = "MainLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = (VideoView) findViewById(R.id.videoview);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String url = "http://189.45.13.225/stream.php.m3u8?user=baita&pass=2018tv&token=1553733132&resptime=109&s=stream29.m3u8";

        try{

            Uri uri = Uri.parse(url);
            videoView.setVideoURI(uri);
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
                    Log.e(TAG,"Error: "+ what);
                    return false;
                }
            });

            videoView.requestFocus();

        }catch (Exception e){
            Log.e(TAG, e.getMessage());
        }

        Log.e(TAG, "URL: " + url);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = (String) parent.getAdapter().getItem(position);
        Toast.makeText(getApplicationContext(), "Texto Selecionado: " + s + " pocisao: " + position, Toast.LENGTH_LONG).show();
    }

    public void video(String videourl){
        Uri uri = Uri.parse(videourl);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setDataAndType(uri, "video/mp4");
        startActivity(intent);
    }
}
