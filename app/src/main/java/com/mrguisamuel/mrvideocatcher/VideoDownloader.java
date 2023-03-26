package com.mrguisamuel.mrvideocatcher;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.CountDownLatch;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;

enum SocialMedia {
    YOUTUBE,
    REDDIT
}

public class VideoDownloader {
    private SocialMedia socialMedia;
    private AppCompatActivity myContext;

    public VideoDownloader(SocialMedia socialMedia, AppCompatActivity myContext) {
        this.myContext = myContext;
        this.socialMedia = socialMedia;
    }

    public void downloadVideo(String link, String fileName) {
        switch(socialMedia) {
            case YOUTUBE:
                downloadYoutube(link, fileName);
                break;
            default:
                break;
        }
    }

    private void downloadYoutube(String link, String fileName) {
        new YouTubeExtractor(this.myContext) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {
                    int itag = 22;
                    String downloadUrl = "";
                    try {
                        downloadUrl = ytFiles.get(itag).getUrl();
                        if (downloadUrl != null) {
                            download(downloadUrl, fileName);
                            Toast.makeText(myContext, "Download started...", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(myContext, "Couldn't download the video.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }.extract(link);
    }

    private void download(String url, String fileName) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle("Download Video");
        request.setDescription("Your video is downloading");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalFilesDir(this.myContext, Environment.DIRECTORY_DOWNLOADS,  fileName + ".mp4");

        DownloadManager manager = (DownloadManager) this.myContext.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}
