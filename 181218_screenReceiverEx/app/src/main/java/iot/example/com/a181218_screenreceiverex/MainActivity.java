package iot.example.com.a181218_screenreceiverex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_send;
    BroadcastReceiver screenOn;
    BroadcastReceiver screenOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        screenOn = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Screen_State", "screen on");
            }
        };

        screenOff = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("Screen_State", "screen off");
            }
        };

        registerReceiver(screenOn, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(screenOff, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        //        unregisterReceiver(screenOn);
        //        unregisterReceiver(screenOff);
    }
}
