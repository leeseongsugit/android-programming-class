package iot.example.com.a181219_musicplayer_quiz;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class PlayService extends Service {

    MediaPlayer player;     // mp3파일을 재생하는 MediaPlayer 객체 변수
    String filePath;        // mp3파일의 경로를 저장하는 변수

    BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String btn = intent.getStringExtra("btn");
            String time =intent.getStringExtra("time");

            Intent intent1 = new Intent("a181219_musicplayer_quiz.myplayerservice");
            if(time!=null){
                if(time.equals("running_time")){
                    if(player.isPlaying() && player != null){
                        intent1.putExtra("cur_time", Integer.parseInt(player.getCurrentPosition()));
                        intent1.putExtra("full_time", Integer.parseInt(player.getDuration()));
                    }
                    sendBroadcast(intent1);
                }
            }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 처음 실행할 때 엑티비티로 부터 받은 인텐트에서
        // 음악파일의 경로를 얻어온다.

        // null 혹은 SD카드 안의 mp3 파일의 경로가 filePath 변수에 저장
        filePath = intent.getStringExtra("filePath");

        if(filePath != null) {
            // 파일의 경로가 있다면
            try {
                // mediaplayer에서 음악 재생이 완료되면
                // 호출되는 OnCompletionListener()를 활용해서
                // 음악 파일의 재생이 완료 되었다면
                // 엑티비디에 결과를 보고해야 한다.
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        // 액티비티의 리시버에게 전달할 인텐트를 만든다.
                        Intent intent = new Intent("a181219_musicplayer_quiz.myplayerservice");
                        // 실행한 결과를 인텐트에 기록한다.
                        intent.putExtra("state", "stop");
                        // 인텐트를 패키지 안에서 브로드 캐스트를 한다.
                        sendBroadcast(intent);
                        // 서비스를 종료하는 코드
                        stopSelf();
                    }
                });
                // 경로에 있는 파일을 읽어와서 재생을 준비
                player.setDataSource(filePath);
                player.prepare();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }

        Log.d("MyPlayerService_log", "Service onStartCommend()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // mp3 재생을 위한 MediaPlayer 객체를 생성한다.
        player = new MediaPlayer();
        // 액티비티와 통신을 위한 리시버를 등록한다.
        registerReceiver(receiver, new IntentFilter("a181219_musicplayer_quiz.myplayerservice"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("MyPlayerService_log", "Service onDestroy()");
        // 서비스가 종료될때 리시버를 등록 해제한다.
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
