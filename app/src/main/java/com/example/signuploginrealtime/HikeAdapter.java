package com.example.signuploginrealtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HikeAdapter extends RecyclerView.Adapter<HikeAdapter.HikeViewHolder> {

    private Context context;
    private List<Hike> hikeList;

    public HikeAdapter(Context context, List<Hike> hikeList) {
        this.context = context;
        this.hikeList = hikeList;
    }

    @NonNull
    @Override
    public HikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hike, parent, false);
        return new HikeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HikeViewHolder holder, int position) {
        Hike hike = hikeList.get(position);
        holder.name.setText(hike.getName());
        holder.elevation.setText(hike.getElevation());
        holder.distance.setText(hike.getDistance());
        holder.difficulty.setText(hike.getDifficulty());
        holder.description.setText(hike.getDescription());
        Glide.with(context).load(hike.getImageUri()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return hikeList.size();
    }

    public void setData(List<Hike> hikeList) {
        this.hikeList = hikeList;
        notifyDataSetChanged();
    }

    public static class HikeViewHolder extends RecyclerView.ViewHolder {
        TextView name, elevation, distance, difficulty, description;
        ImageView image;

        public HikeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            elevation = itemView.findViewById(R.id.elevation);
            distance = itemView.findViewById(R.id.distance);
            difficulty = itemView.findViewById(R.id.difficulty);
            description = itemView.findViewById(R.id.description);
            image = itemView.findViewById(R.id.image);
        }
    }
}
