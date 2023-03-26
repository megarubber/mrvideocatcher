package com.mrguisamuel.mrvideocatcher;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class YouTubeDownloader extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);
        getSupportActionBar().setTitle("Download video from YouTube");
    }
}
