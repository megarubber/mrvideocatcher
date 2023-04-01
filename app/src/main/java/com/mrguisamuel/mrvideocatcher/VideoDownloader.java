package com.mrguisamuel.mrvideocatcher;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.util.SparseArray;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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

    public void downloadContent(String link, String fileName, boolean isAudio) {
        switch(socialMedia) {
            case YOUTUBE:
                downloadYoutube(link, fileName, isAudio);
                break;
            default:
                break;
        }
    }

    private void downloadYoutube(String link, String fileName, boolean isAudio) {
        new YouTubeExtractor(this.myContext) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {
                    int itag = isAudio ? 140 : 22;
                    String downloadUrl = "";
                    try {
                        downloadUrl = ytFiles.get(itag).getUrl();
                        if (downloadUrl != null) {
                            download(downloadUrl, fileName, isAudio);
                            Toast.makeText(myContext, "Download started...", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(myContext, "Couldn't download the media.", Toast.LENGTH_SHORT).show();
                        System.out.println(e.toString());
                    }
                }
            }
        }.extract(link);
    }

    private void download(String url, String fileName, boolean isAudio) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setTitle(isAudio ? "Download Video" : "Download Audio");
        request.setDescription(isAudio ? "Your video is downloading" : "Your audio is downloading");
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        //request.setDestinationInExternalFilesDir(this.myContext, Environment.DIRECTORY_DOWNLOADS,  fileName + ".mp4");
        String extension = isAudio ? ".m4a" : ".mp4";

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName + extension);

        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

        DownloadManager manager = (DownloadManager) this.myContext.getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}
