package com.example.youtubeclone2.ui.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.youtubeclone2.MainActivity;
import com.example.youtubeclone2.R;
import com.google.android.material.chip.Chip;

public class FiltersAdapter extends RecyclerView.Adapter<FiltersAdapter.ViewHolder> {

    private Context context;
    private String[] filters;

    public FiltersAdapter(Context context, String[] filters) {
        this.context = context;
        this.filters = filters;
    }

    @NonNull
    @Override
    public FiltersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chip,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        Chip data = viewHolder.chip;
        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MainActivity.class);
                if(!data.getText().toString().toLowerCase().contains("all")){
                    intent.putExtra("searchterm",data.getText());
                }
                context.startActivity(intent);
                ((AppCompatActivity)context).finish();
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FiltersAdapter.ViewHolder holder, int position) {
        String filter = filters[position];
        holder.chip.setText(filter);
    }

    @Override
    public int getItemCount() {
        return filters.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Chip chip;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chip = itemView.findViewById(R.id.filter);

        }
    }
}
