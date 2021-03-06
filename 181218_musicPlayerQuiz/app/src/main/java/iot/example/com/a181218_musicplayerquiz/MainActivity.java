package iot.example.com.a181218_musicplayerquiz;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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

    boolean bReadPerm = false;
    Button button_play, button_stop;    //버튼 객채를 위한 변수
    boolean bStatePlay = false;         //재생 상태 유무를 위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //sd카드 읽기를 위한 권한을 처리하는 함수
        setPermission();

        //버튼 객체를 생성
        button_play = (Button) findViewById(R.id.button_play);
        button_stop = (Button) findViewById(R.id.button_stop);

        //버튼을 눌렀을 때 리스너 등록
        button_play.setOnClickListener(new MyButtonListener());
        button_stop.setOnClickListener(new MyButtonListener());

        registerReceiver(receiver, new IntentFilter("a181218_musicplayerquiz.myservice"));

        if (bReadPerm) {
            //sd카드 상태 확인
            String state = Environment.getExternalStorageState();

            if (state.equals(Environment.MEDIA_MOUNTED)) {
                //sd카드가 장착 되어 있다면
                try {
                    //sd 카드 안에 있는 mp3파일의 경로를 읽어온다.
                    String musicPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/music.mp3";

                    //인텐트에 mp3파일 경로를 저장한다.
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    intent.putExtra("filePath", musicPath);
                    //서비스 시작
                    startService(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MyPlayerService_log", "Main onDestroy()");

        // 엑티비티가 사라질 때 리시버도 해제
        unregisterReceiver(receiver);
    }

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //서비스로 부터 전달된 인텐트를 state라는 키 값으로 읽어온다.
            // 값이 없다면 state 변수 안에는 null, plqy, pause, stop

            String state = intent.getStringExtra("state");

            if (state != null) {
                //서비스가 재생 명령을 수행하는 결과가 있다면
                if (state.equals("play")) {
                    //재생중임을 표시하는 bStatePlay 변수에 true 저장
                    bStatePlay = true;
                    //버튼 이름은 Pause로 변경
                    button_play.setText("Pause");
                } else if (state.equals("pause") || state.equals("stop")) {
                    //서비스가 중지 또는 일시 죽잊 명령을 수행한 결과가 있다면
                    //재생중 임을 표시하는 bStatePlay 변수에 false 저장
                    bStatePlay = false;
                    //버튼 이름은 Play로 변경
                    button_play.setText("Play");
                }
            }
        }
    };


    class MyButtonListener implements View.OnClickListener {
        Intent intent;

        @Override
        public void onClick(View view) {
            // 서비스의 리시버에 전달하기 위한 인텐트 준비
            intent = new Intent("a181218_musicplayerquiz.myservice");
            switch (view.getId()) {
                // play & pause 버튼
                case R.id.button_play:
                    if (bStatePlay) { // 음악이 재생중이면
                        // play & pause 버튼이 일시 중지 명령을 인텐트에 포함
                        intent.putExtra("btn", "pause");
                    } else {
                        // play & pause 버튼이 재생 명령을 인텐트에 포함
                        intent.putExtra("btn", "play");
                    }
                    break;
                // stop 버튼
                case R.id.button_stop:
                    // stop 명령 인텐트에 포함
                    intent.putExtra("btn", "stop");
                    break;
            }
            // 엑티비티에서 서비스의 브로드케스트 리시버에게 인텐트를 전달
            sendBroadcast(intent);
        }
    }

    private void setPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            bReadPerm = true;
        }


        if (!bReadPerm) {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 200);
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                bReadPerm = true;
            }
        }
    }


}
