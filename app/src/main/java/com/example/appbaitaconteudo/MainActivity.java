package com.example.appbaitaconteudo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    VideoView videoView;
    String URL ="http://189.45.13.225/stream.php.m3u8?user=baitaconteudo&pass=123456&token=1234&s=stream50.m3u8";
    //String URL ="https://video.nbtelecom.com.br/mnt/baita-conteudo.php";

    String url2 = "http://189.45.13.225/stream.php.m3u8?user=user&pass=passnb1&token=1234&s=stream50.m3u8";
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView =(VideoView) findViewById(R.id.videoview);
        //listView = (ListView) findViewById(R.id.listView);

        //listView.setAdapter(new AdapterChannel(this));
       // listView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Uri uri = Uri.parse(url2);
        videoView.setVideoURI(uri);
        videoView.start();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String s = (String)parent.getAdapter().getItem(position);
        Toast.makeText(getApplicationContext(), "Texto Selecionado: "+s+" pocisao: "+position, Toast.LENGTH_LONG).show();
    }
}
