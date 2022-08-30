package com.example.youtubeclone2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.FileUtils;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.youtubeclone2.ui.model.Profile;
import com.example.youtubeclone2.ui.util.DownloadFileFromURL;
import com.example.youtubeclone2.ui.util.DownloadVideoFileFromURL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity2 extends AppCompatActivity {
    Profile profile;
    TextView tappedContentName,tappedChannelName,tappedViews,tappedTime,like,dislike,subscriber,subscribeButton,subscribedButton;
    ImageView tappedImage,tappedProfileImage,likeButton,likedButton,dislikeButton,dislikedButton,shareButton,downloadButton;
    int l = 0;
    int s = 0;
    final int REQUEST_CODE = 100;
    private File pictureSaveFolderPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        pictureSaveFolderPath = getExternalMediaDirs()[0];


        Intent  intent = getIntent();
        profile = (Profile) intent.getSerializableExtra("profile");
        Log.d("++++++++++++++++++++++",profile.toString());

        tappedContentName = findViewById(R.id.tappedContentName);
        tappedChannelName = findViewById(R.id.tappedChannelName);
        tappedViews = findViewById(R.id.tappedViews);
        tappedTime = findViewById(R.id.tappedTime);
        tappedImage = findViewById(R.id.tappedImage);
        tappedProfileImage = findViewById(R.id.tappedProfileImage);
        like = findViewById(R.id.like);
        dislike = findViewById(R.id.dislike);

        likeButton = findViewById(R.id.likeButton);
        likedButton = findViewById(R.id.likedButton);
        dislikeButton = findViewById(R.id.dislikeButton);
        dislikedButton = findViewById(R.id.dislikedButton);
        shareButton = findViewById(R.id.shareButton);
        downloadButton = findViewById(R.id.downloadButton);

        subscriber = findViewById(R.id.subscriber);
        subscribeButton = findViewById(R.id.subscribeButton);
        subscribedButton = findViewById(R.id.subscribedButton);



        tappedChannelName.setText(profile.getChannelName());
        tappedContentName.setText(profile.getContentName());
        tappedViews.setText(profile.getViews());
        tappedTime.setText(profile.getTime());


        VideoView videoView = findViewById(R.id.videoView);

        if(profile.getType().equals("image")){
            videoView.setVisibility(View.GONE);
            tappedImage.setVisibility(View.VISIBLE);
            Glide.with(getApplicationContext())
                    .load(profile.getImageUrl())
                    .centerCrop()
                    .into(tappedImage);
        }else if(profile.getType().equals("video")){
            MediaController mediaController= new MediaController(this);

            videoView.setVisibility(View.VISIBLE);
            tappedImage.setVisibility(View.GONE);



            Uri uri = Uri.parse(profile.getImageUrl());
            videoView.setVideoURI(uri);
            videoView.setMediaController(mediaController);
            videoView.start();
        }

        Glide.with(this)
                .load(profile.getProfileImageUrl())
                .centerCrop()
                .into(tappedProfileImage);



        likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l++;
                profile.setLike(l);
                like.setText(profile.getLike()+" like");
                likeButton.setVisibility(View.GONE);
                likedButton.setVisibility(View.VISIBLE);
                dislikeButton.setEnabled(false);

            }
        });

        likedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                l--;
                profile.setLike(l);
                if(profile.getLike() != 0){
                like.setText(profile.getLike()+" like");
                }
                else {
                    like.setText("Like");
                }
                likeButton.setVisibility(View.VISIBLE);
                likedButton.setVisibility(View.GONE);
                dislikeButton.setEnabled(true);
            }
        });




        dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dislikeButton.setVisibility(View.GONE);
                dislikedButton.setVisibility(View.VISIBLE);
                likeButton.setEnabled(false);
            }
        });
        dislikedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dislikeButton.setVisibility(View.VISIBLE);
                dislikedButton.setVisibility(View.GONE);
                likeButton.setEnabled(true);
            }
        });


        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s++;

                profile.setSubscriber(s);
                subscriber.setText(s+" subscribers");
                subscribeButton.setVisibility(View.GONE);
                subscribedButton.setVisibility(View.VISIBLE);

            }
        });

        subscribedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s--;
                profile.setSubscriber(s);
                if (profile.getSubscriber() != 0){
                    subscriber.setText(s+"subscribers");
                }
                else{
                    subscriber.setText("");
                }
                subscribeButton.setVisibility(View.VISIBLE);
                subscribedButton.setVisibility(View.GONE);
            }
        });



        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setAction(Intent.ACTION_SEND);
                intent1.setType("text/plain");
                intent1.putExtra(Intent.EXTRA_TEXT,profile.getImageUrl());
                startActivity(Intent.createChooser(intent1, "choose one"));

//                Intent shareIntent = new Intent();
//                shareIntent.setAction(Intent.ACTION_SEND);
//                shareIntent.putExtra(Intent.EXTRA_STREAM, profile.getImageUrl());
//                shareIntent.setType("image/jpeg");
//                startActivity(Intent.createChooser(shareIntent, null));


            }
        });

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storagePermission();
                Toast.makeText(MainActivity2.this, "cliked", Toast.LENGTH_SHORT).show();


            }
        });

    }


        public void storagePermission(){
        if (ContextCompat.checkSelfPermission(
                MainActivity2.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED ) {
            // You can use the API that requires the permission.
            //performAction(...);
            Toast.makeText(MainActivity2.this, "Downloading", Toast.LENGTH_SHORT).show();
            saveImage();
        } else if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity2.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ) {
            // In an educational UI, explain to the user why your app requires this
            // permission for a specific feature to behave as expected. In this UI,
            // include a "cancel" or "no thanks" button that allows the user to
            // continue using your app without granting the permission.
            //showInContextUI(...);
            Toast.makeText(MainActivity2.this, "Media Permission not Granted ", Toast.LENGTH_SHORT).show();
        } else {
            // You can directly ask for the permission.
            ActivityCompat.requestPermissions(MainActivity2.this,
                    new String[] { Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE);

        }

    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity2.this, "Downloading", Toast.LENGTH_SHORT).show();
                    saveImage();
                } else {
                    Toast.makeText(MainActivity2.this, "Media Permission Not Granted ", Toast.LENGTH_SHORT).show();
                }
                return;
        }

    }



    public void saveImage(){
        if(profile.getType().equals("image")){

        new DownloadFileFromURL(pictureSaveFolderPath,MainActivity2.this).execute(profile.getImageUrl());
        }
        else if(profile.getType().equals("video")){
            new DownloadVideoFileFromURL(pictureSaveFolderPath,MainActivity2.this).execute(profile.getImageUrl());
        }
    }
    
}