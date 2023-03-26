package com.mrguisamuel.mrvideocatcher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class YouTubeDownloader extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        getSupportActionBar().setTitle("Download video from YouTube");

        Button videoDownload = (Button) findViewById(R.id.video);
        EditText link = (EditText) findViewById(R.id.link);

        VideoDownloader youtubeDownloader = new VideoDownloader(SocialMedia.YOUTUBE, this);
        videoDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //youtubeDownloader.downloadVideo(link.getText().toString());
            }
        });
    }
}