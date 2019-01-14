package iot.example.com.a181224_doitmission19;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Scanner;

public class InfoActivity extends AppCompatActivity {
    ImageView imageView;
    VideoView videoView;
    MediaController mediaController;

    int imageType;
    Scanner

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();


        imageView = (ImageView)findViewById(R.id.imageView);
        videoView = (VideoView)findViewById(R.id.videoView);

        if(intent != null){
            imageType = intent.getIntExtra("imageType", -1);
        }

        if(imageType == 1){
            imageView.setVisibility(View.VISIBLE);
            videoView.setVisibility(View.INVISIBLE);
            String path = Environment.getExternalStorageDirectory().getAbsoluteFile()+"/myApp";
            File dir = new File(path);

            File[] list = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    Log.d("test", s);
                    return s.endsWith(".jpg||.png");
                }
            });

            if(list != null) {
                try {

                }catch(Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(InfoActivity.this, "동영상 파일이 없습니다.", Toast.LENGTH_SHORT).show();
            }
        }else{
            imageView.setVisibility(View.INVISIBLE);
            videoView.setVisibility(View.VISIBLE);
            String path = Environment.getExternalStorageDirectory().getAbsoluteFile()+"/myApp";
            File dir = new File(path);

            File[] list = dir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    Log.d("test", s);
                    return s.endsWith(".3gp");
                }
            });

            if(list != null) {
                videoView.setVideoPath(list[0].getPath());
            } else {
                Toast.makeText(InfoActivity.this, "동영상 파일이 없습니다.", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
