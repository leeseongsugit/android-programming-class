package iot.example.com.a181219_threadexample;

import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tv_count;
    Button btn_start, btn_stop;
    ProgressBar pb_circle;
    int value = 0;
    Thread my_thread = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_count = (TextView)findViewById(R.id.tv_count);
        btn_stop = (Button)findViewById(R.id.btn_stop);
        btn_start = (Button)findViewById(R.id.btn_start);
        pb_circle = (ProgressBar)findViewById(R.id.pb_circle);

        btn_stop.setOnClickListener(new BtnListener());
        btn_start.setOnClickListener(new BtnListener());
    }

    Handler handler = new Handler();

    private class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_start:
                    if(my_thread == null){
                        MyThread runnable = new MyThread();
                        my_thread = new Thread(runnable);
                        my_thread.start();
                    }
                    break;
                case R.id.btn_stop:
                    if(my_thread != null){
                        my_thread.interrupt();
                        tv_count.setText("사용자에 의해 종료되었습니다.");
                        pb_circle.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    }

    private class MyThread implements Runnable {
        @Override
        public void run() {
            try{
                while(!Thread.currentThread().isInterrupted()){
                    if(value < 1000){
                        SystemClock.sleep(100);
                        value++;
                        handler.post(new UIUpdate());
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private class UIUpdate implements Runnable {

        @Override
        public void run() {
            if(value < 1000){
                tv_count.setText(Integer.toString(value));
                pb_circle.setVisibility(View.VISIBLE);
            }else{
                tv_count.setText("1000번 카운트 완료");
                pb_circle.setVisibility(View.INVISIBLE);
            }
        }
    }
}
