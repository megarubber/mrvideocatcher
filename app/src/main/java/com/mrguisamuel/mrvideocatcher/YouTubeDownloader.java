package com.mrguisamuel.mrvideocatcher;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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
                if(ContextCompat.checkSelfPermission(
                        YouTubeDownloader.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            YouTubeDownloader.this,
                            new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE },
                            101
                    );
                }

                if(ContextCompat.checkSelfPermission(
                        YouTubeDownloader.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        == PackageManager.PERMISSION_GRANTED) {
                    FileNameDialog.createFileNameDialog(
                            YouTubeDownloader.this,
                            () -> {
                                youtubeDownloader.downloadVideo(link.getText().toString(), FileNameDialog.name);
                                link.setText("");
                                return null;
                            }
                    );
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            "Download failed because write permission is not granted.",
                            Toast. LENGTH_LONG).show();
                }
            }
        });
    }
}