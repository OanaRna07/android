package com.example.signuploginrealtime;


import static android.app.PendingIntent.getActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.MountainViewHolder> {

    private static Context context;
    private List<Mountain> mountains;

    public MountainAdapter(Context context) {
        this.context = context;
    }

    public void setMountains(List<Mountain> mountains) {
        this.mountains = mountains;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MountainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mountain, parent, false);
        return new MountainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MountainViewHolder holder, int position) {
        Mountain mountain = mountains.get(position);
        holder.textName.setText(mountain.getName());
        holder.textHeight.setText(mountain.getHeight());
        holder.textLocation.setText(mountain.getLocation());
    }

    @Override
    public int getItemCount() {
        return mountains != null ? mountains.size() : 0;
    }

    public static class MountainViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textHeight, textLocation;
        Button preferences_button;

        public MountainViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_view_name);
            textHeight = itemView.findViewById(R.id.text_view_height);
            textLocation = itemView.findViewById(R.id.text_view_location);

            preferences_button = itemView.findViewById(R.id.preferences_button);
            preferences_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    SharedPreferences sharedPref = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);


                    SharedPreferences.Editor editor = sharedPref.edit();


                    editor.putString("key_mountain", textName.getText().toString());


                    editor.commit();

                }
            });
        }
    }
}
