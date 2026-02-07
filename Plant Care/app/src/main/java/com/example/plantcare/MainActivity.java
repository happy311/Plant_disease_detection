package com.example.plantcare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton strawberry = findViewById(R.id.strawberry);
        ImageButton cherry = findViewById(R.id.cherry);
        ImageButton peach = findViewById(R.id.peach);
        ImageButton apple = findViewById(R.id.apple);
        ImageButton pepper = findViewById(R.id.pepper);
        ImageButton potato = findViewById(R.id.potato);
        ImageButton all = findViewById(R.id.all);

        getPermission();
//        1
        strawberry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labels = "stlabels.txt";
                String name = "straberry_tflite_model.tflite";
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name",name);
                intent.putExtra("labels",labels);
                startActivity(intent);
            }
        });
//        2
        cherry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labels = "cherrylabels.txt";
                String name = "cherry_tflite_model.tflite";
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name",name);
                intent.putExtra("labels",labels);
                startActivity(intent);
            }
        });
//        3
        peach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labels = "peachlabel.txt";
                String name = "peach_tflite_model.tflite";
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name",name);
                intent.putExtra("labels",labels);
                startActivity(intent);
            }
        });
//        4
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labels = "alabels.txt";
                String name = "apple_tflite_model.tflite";
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name",name);
                intent.putExtra("labels",labels);
                startActivity(intent);
            }
        });
//        5
        pepper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labels = "pepperlabel.txt";
                String name = "pepper_tflite_model.tflite";
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name",name);
                intent.putExtra("labels",labels);
                startActivity(intent);
            }
        });
//        6
        potato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labels = "potatolabels.txt";
                String name = "potato_tflite_model.tflite";
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name",name);
                intent.putExtra("labels",labels);
                startActivity(intent);
            }
        });
//        7
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String labels = "labels.txt";
                String name = "quantize_tflite_model.tflite";
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("name",name);
                intent.putExtra("labels",labels);
                startActivity(intent);
            }
        });
    }
    void getPermission(){
        if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, 11);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode ==11){
            if (grantResults.length>0) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this,"give permission of camera to capture image",Toast.LENGTH_SHORT).show();                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}