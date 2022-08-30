package com.example.youtubeclone2.ui.util;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youtubeclone2.MainActivity2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadFileFromURL  extends AsyncTask<String, String, String> {
    private File pictureSaveFolderPath;
    private AppCompatActivity activity;

    public DownloadFileFromURL(File folder,AppCompatActivity activity){
        pictureSaveFolderPath = folder;
        this.activity = activity;
    }


    @Override
    protected String doInBackground(String... strings) {
        try {
            Bitmap bmp=null;
            int responseCode = -1;
            InputStream in;
            URL url = new URL(strings[0]);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setDoInput(true);
            con.connect();
            responseCode = con.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK)
            {
                //download
                in = con.getInputStream();
                bmp = BitmapFactory.decodeStream(in);
                System.out.println("Bitmap : "+ bmp.toString());
                FileOutputStream out = null;
                try {
                    System.out.println("Folder Path : " + pictureSaveFolderPath.getAbsolutePath());
                    File file = new File(pictureSaveFolderPath+"/img_"+System.currentTimeMillis() +"_.jpg");
//                    file.createNewFile();
                    System.out.println("File Path : " + file.getAbsolutePath());

                    out = new FileOutputStream(file);
                    bmp.compress(Bitmap.CompressFormat.JPEG, 100, out); // Compress Image
                    out.flush();
                    out.close();
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(file.getAbsolutePath()));
                    shareIntent.setType("image/jpeg");
                    activity.startActivity(Intent.createChooser(shareIntent, null));




                } catch (FileNotFoundException e) {
                    System.out.println("Error11 "+ e.getMessage());
                }
                in.close();
            }
        }catch (Exception e){
            System.out.println("Error22: " +e.getMessage());
            System.out.println("Error22 path: " +pictureSaveFolderPath.getAbsolutePath());
        }
        return null;
    }
}
