package com.example.youtubeclone2.ui.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class DownloadVideoFileFromURL extends AsyncTask<String, String, String> {

    private File pictureSaveFolderPath;
    private AppCompatActivity activity;

    public DownloadVideoFileFromURL(File pictureSaveFolderPath, AppCompatActivity activity) {
        this.pictureSaveFolderPath = pictureSaveFolderPath;
        this.activity = activity;
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.connect();
            int contentLength = con.getContentLength();

            DataInputStream stream = new DataInputStream(url.openStream());

            byte[] buffer = new byte[contentLength];
            stream.readFully(buffer);
            stream.close();

            File file = new File(pictureSaveFolderPath+"/video_"+System.currentTimeMillis() +"_.mp4");
            DataOutputStream fos = new DataOutputStream(new FileOutputStream(file));
            fos.write(buffer);
            fos.flush();
            fos.close();
            Intent shareIntent = new Intent();
            shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.getAbsolutePath()));
            shareIntent.setType("video/mp4");
            activity.startActivity(Intent.createChooser(shareIntent, null));
        } catch (Exception e){
            System.out.println(e);
        }

        return null;
    }


}
