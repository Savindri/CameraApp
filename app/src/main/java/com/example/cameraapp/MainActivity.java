package com.example.cameraapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    //initialize variables
    ImageView imageView;
    Button btOpen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign variables
        imageView = findViewById(R.id.image_View);
        btOpen = findViewById(R.id.bt_open);

        //Request for camera permission
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                          Manifest.permission.CAMERA
                    },
                    100);
        }

        btOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Open camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,100);
            }
        });
    }

    // access the photo taken by the camera application
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            //get captured image
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            //set captured image to ImageView
            imageView.setImageBitmap(captureImage);
        }
    }
}