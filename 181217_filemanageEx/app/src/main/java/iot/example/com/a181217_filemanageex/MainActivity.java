package iot.example.com.a181217_filemanageex;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //변수 선언
    boolean bReadPerm = false;          //읽기 퍼미션
    boolean bWritePerm = false;         //쓰기 퍼미션
    Button button_play, button_stop;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //퍼미션 세팅
        setPermission();

        //객체 참조
        button_play = (Button)findViewById(R.id.button_play);
        button_stop = (Button)findViewById(R.id.button_stop);
        button_play.setOnClickListener(new MyButtonListener());
        button_stop.setOnClickListener(new MyButtonListener());
        //미디어 플레이어 객체 생성
        player = new MediaPlayer();

        //읽기 쓰기 퍼미션 모두 허용 됐을 때 music.mp3 파일을 가져와 플레이어에 준비
        if(bReadPerm && bWritePerm){
            String state = Environment.getExternalStorageState();

            if(state.equals(Environment.MEDIA_MOUNTED)){
                try{
                    String musicPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/music.mp3";

                    player.setDataSource(musicPath);
                    player.prepare();
                    Log.d("PlayMp3", "mp3 file " );
                }catch (Exception e){
                    Log.d("PlayMp3", "mp3 file error");
                }
            }

        }

    }

    //
    private void setPermission() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            bReadPerm = true;
        }

        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            bWritePerm = true;
        }
        if(!bReadPerm && !bWritePerm) {
            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 200);
        }

    }

    private class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.button_play:
                    if(player.isPlaying()){
                        player.pause();
                        button_play.setText("play");
                    }else{
                        player.start();
                        button_play.setText("pause");
                    }
                    break;
                case R.id.button_stop:
                    player.stop();
                    try{
                        if(player.isPlaying()){
                            button_play.setText("play");
                        }
                        player.prepare();

                    }catch (Exception e){
                        Log.d("PlayMp3", "mp3 file error");
                    }
                    break;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bReadPerm = true;
            }
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                bWritePerm = true;
            }
        }
    }
}
