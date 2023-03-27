package com.mrguisamuel.mrvideocatcher;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Callable;

public class YouTubeDownloader extends AppCompatActivity {
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Download video from YouTube");

        Button videoDownload = (Button) findViewById(R.id.video);
        EditText link = (EditText) findViewById(R.id.link);

        VideoDownloader youtubeDownloader = new VideoDownloader(SocialMedia.YOUTUBE, this);
        videoDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FileNameDialog.createFileNameDialog(
                    YouTubeDownloader.this,
                    () -> { youtubeDownloader.downloadVideo(link.getText().toString(), "maisumteste"); return null; }
                );
            }
        });
    }
}