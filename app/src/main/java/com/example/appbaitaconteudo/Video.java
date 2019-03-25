package com.example.appbaitaconteudo;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.VideoView;

public class Video {


    private static Context ctx;
    private static  String TAG = "VideoLog";


    public Video(Context ctx){
        this.ctx = ctx;
    }

    public static void loaderVideo(final Context ctx){

    }


    public static void prepareVideo(final String url, final VideoView videoView) {
        playVideo(url, videoView);
    }


    public static void playVideo(final String url, final VideoView videoView) {

        try {

            videoView.setVideoURI(Uri.parse(url));
            videoView.requestFocus();//starta o video
            videoView.start();

        } catch (Exception e) {
            Log.i(TAG, e.getMessage());
        }

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e(TAG, "Prepare video");

                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp, int what, int extra) {
                        if (what == MediaPlayer.MEDIA_INFO_BUFFERING_START){
                            Log.e(TAG, "MEDIA_INFO_BUFFERING_START");
                            loaderVideo(ctx);
                        }


                        if (what == MediaPlayer.MEDIA_INFO_BUFFERING_END){
                            Log.e(TAG, "MEDIA_INFO_BUFFERING_END");
                        }

                        return false;
                    }
                });

                mp.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                    @Override
                    public boolean onError(MediaPlayer mp, int what, int extra) {
                        return false;
                    }
                });
            }
        });
    }
}