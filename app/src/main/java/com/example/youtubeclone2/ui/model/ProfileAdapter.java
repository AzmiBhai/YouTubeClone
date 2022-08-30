package com.example.youtubeclone2.ui.model;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.youtubeclone2.MainActivity2;
import com.example.youtubeclone2.R;

import java.util.ArrayList;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Profile> profiles;


    public ProfileAdapter(Context context, ArrayList<Profile> profiles) {
        this.context = context;
        this.profiles = profiles;
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.homepage_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);




        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {

        Profile profile = profiles.get(position);

        holder.contentName.setText(profile.getContentName());
        holder.channelName.setText(profile.getChannelName());
        holder.views.setText(profile.getViews());
        holder.time.setText(profile.getTime());

        Glide.with(context)
                .load(profile.getProfileImageUrl())
                .centerCrop()
                .into(holder.profileImage);


            Glide.with(context)
                    .load(profile.getImageUrl())
                    .centerCrop()
                    .into(holder.imageView);



        holder.tap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity2.class);
                intent.putExtra("profile",profile);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return profiles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,profileImage;
        TextView channelName,contentName,views,time;
        LinearLayout tap;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.imageView = itemView.findViewById(R.id.tappedImage);
            this.profileImage = itemView.findViewById(R.id.tappedProfileImage);
            this.channelName = itemView.findViewById(R.id.tappedChannelName);
            this.contentName = itemView.findViewById(R.id.tappedContentName);
            this.views = itemView.findViewById(R.id.tappedViews);
            this.time = itemView.findViewById(R.id.tappedTime);
            this.tap = itemView.findViewById(R.id.tap);
        }
    }
}
