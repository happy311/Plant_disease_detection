package com.example.plantcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
//import org.tensorflow.lite.DataType;
//import org.tensorflow.lite.Interpreter;
//import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Objects;

import org.tensorflow.lite.Interpreter;

public class MainActivity2 extends AppCompatActivity {

    ImageButton selectBtn,captureBtn,predictBtn;
    ImageView imageView;
    Bitmap bitmap;
    private Interpreter tflite;
    private ByteBuffer inputBuffer;
    private ByteBuffer outputBuffer;
    private static final int BATCH_SIZE = 1;
    private static final int INPUT_SIZE = 256;
    private static final int OUTPUT_SIZE = 11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        String model = getIntent().getStringExtra("name");
        String label = getIntent().getStringExtra("labels");


        String []  labels = new  String[11];
        int cnt=0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open(label)));

            do{
                String line =bufferedReader.readLine();
                if (line==null){
                    break;
                }
                labels[cnt] = line;
                cnt++;
            }while (cnt<11);
            cnt--;
        }catch (Exception e){
            Toast.makeText(MainActivity2.this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }

        selectBtn = findViewById(R.id.select);
        captureBtn = findViewById(R.id.camera);
        predictBtn = findViewById(R.id.predict);
        imageView = findViewById(R.id.scanner);

        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,10);
            }
        });
        captureBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,12);
                }else{
                    getPermission();
                }
            }
        });

        predictBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                if (bitmap!=null){
                    try {
                        tflite = new Interpreter(loadModelFile(model));
                        inputBuffer = ByteBuffer.allocateDirect(BATCH_SIZE * INPUT_SIZE * INPUT_SIZE * 3 * 4);
                        inputBuffer.order(ByteOrder.nativeOrder());
                        outputBuffer = ByteBuffer.allocateDirect(BATCH_SIZE * OUTPUT_SIZE * 4);
                        outputBuffer.order(ByteOrder.nativeOrder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, true);
                    try {
                        convertBitmapToByteBuffer(resizedBitmap);
                        tflite.run(inputBuffer, outputBuffer);
                        float[] outputValues = new float[11];
                        int j=0;
                        for (int i=0;i<44;i=i+4){
                            outputValues[j] = outputBuffer.getFloat(i);
                            j++;
                        }
                        int []data1 = new int[11];
                        int max = 0;
                        int index = 0;
                        for (int i=0;i<outputValues.length;i++){
                            data1[i] = (int)(outputValues[i]*100);
                            if(max<data1[i]){
                                max = data1[i];
                                index = i;
                            }
                        }
                        int confidence = max;
                        Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                        String lab = labels[index];
                        if(!Objects.equals(lab, "No_leaf")){
                            intent.putExtra("label",lab);
                            intent.putExtra("confidence",max);
                            startActivity(intent);
                        }else {
                            Toast.makeText(MainActivity2.this,"given above is not leaf",Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        String str = e.getMessage();
                        Toast.makeText(MainActivity2.this,str,Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(MainActivity2.this,"please select image",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    private void convertBitmapToByteBuffer(Bitmap bitmap) throws Exception {
        if (inputBuffer == null) {
            return;
        }
        int[] intValues = new int[bitmap.getHeight()*bitmap.getWidth()];
        inputBuffer.rewind();
        bitmap.getPixels(intValues, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
        for (int i = 0; i < INPUT_SIZE; ++i) {
            for (int j = 0; j < INPUT_SIZE; ++j) {
                int pixelValue = intValues[i * INPUT_SIZE + j];
                inputBuffer.putFloat((pixelValue >> 16) & 0xFF);
                inputBuffer.putFloat((pixelValue >> 8) & 0xFF);
                inputBuffer.putFloat(pixelValue & 0xFF);
            }
        }
    }
    private MappedByteBuffer loadModelFile(String model) throws IOException {
        AssetFileDescriptor fileDescriptor = this.getAssets().openFd(model);
        FileInputStream inputStream = new FileInputStream(fileDescriptor.getFileDescriptor());
        FileChannel fileChannel = inputStream.getChannel();
        long startOffset = fileDescriptor.getStartOffset();
        long declaredLength = fileDescriptor.getDeclaredLength();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }
    void getPermission(){
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity2.this, new String[]{Manifest.permission.CAMERA}, 11);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode ==11){
            if (grantResults.length>0) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity2.this,"give permission of camera to capture image",Toast.LENGTH_SHORT).show();

                }
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode ==10){
            if(data!=null){
                Uri uri = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),uri);
                    imageView.setImageBitmap(bitmap);
                }  catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        else if (requestCode==12){
            assert data != null;
            bitmap = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
            Bitmap resizedBitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, true);
            imageView.setImageBitmap(resizedBitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}