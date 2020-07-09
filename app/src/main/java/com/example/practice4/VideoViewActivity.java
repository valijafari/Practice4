package com.example.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_view);

        VideoView vv = findViewById(R.id.vv);
        String url = "https://as4.cdn.asset.aparat.com/aparat-video/93be29e4367b081325e2eb97bc40500818274262-144p.mp4";
        vv.setVideoURI(Uri.parse(url));
        vv.setMediaController(new MediaController(VideoViewActivity.this));
        vv.start();
    }
}