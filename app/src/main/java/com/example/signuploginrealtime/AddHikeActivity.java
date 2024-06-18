package com.example.signuploginrealtime;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AddHikeActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    EditText routeName, routeElevation, routeDistance, routeDifficulty, routeDescription;
    Button addPhotoButton, saveHikeButton;
    ImageView imagePreview;

    Uri imageUri;
    Bitmap selectedImage;

    HikeDatabase hikeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hike);

        routeName = findViewById(R.id.route_name);
        routeElevation = findViewById(R.id.route_elevation);
        routeDistance = findViewById(R.id.route_distance);
        routeDifficulty = findViewById(R.id.route_difficulty);
        routeDescription = findViewById(R.id.route_description);
        addPhotoButton = findViewById(R.id.button_add_photo);
        saveHikeButton = findViewById(R.id.button_save_hike);
        imagePreview = findViewById(R.id.image_preview);

        hikeDatabase = HikeDatabase.getInstance(this);

        addPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImageChooser();
            }
        });

        saveHikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveHikeToDatabase();
            }
        });
    }

    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                selectedImage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                imagePreview.setImageBitmap(selectedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveHikeToDatabase() {
        String name = routeName.getText().toString();
        String elevation = routeElevation.getText().toString();
        String distance = routeDistance.getText().toString();
        String difficulty = routeDifficulty.getText().toString();
        String description = routeDescription.getText().toString();
        String imageString = "";

        if (selectedImage != null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            selectedImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            imageString = Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        Hike hike = new Hike();
        hike.setName(name);
        hike.setElevation(elevation);
        hike.setDistance(distance);
        hike.setDifficulty(difficulty);
        hike.setDescription(description);
        hike.setImageUri(imageString);

        new SaveHikeTask().execute(hike);
    }

    private class SaveHikeTask extends AsyncTask<Hike, Void, Void> {
        @Override
        protected Void doInBackground(Hike... hikes) {
            hikeDatabase.hikeDao().insert(hikes[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            finish();
        }
    }
}
