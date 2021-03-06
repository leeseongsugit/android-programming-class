package iot.example.com.a181219_threadex2;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

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

        tv_count = (TextView) findViewById(R.id.tv_count);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_start = (Button) findViewById(R.id.btn_start);
        pb_circle = (ProgressBar) findViewById(R.id.pb_circle);

        btn_stop.setOnClickListener(new BtnListener());
        btn_start.setOnClickListener(new BtnListener());

    }


    class MyThread extends Thread {
        public void run() {
            try {
                int value = 0;
                while (value < 100) {
                    Thread.sleep(100);

                    value++;
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = value;
                    handler.handleMessage(msg);
                    if(value == 100){
                        msg = new Message();
                        msg.what = 2;
                        msg.obj = "완료";

                        handler.handleMessage(msg);
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                tv_count.setText(String.valueOf(msg.arg1));
                pb_circle.setVisibility(View.VISIBLE);
            } else if (msg.what == 2) {
                tv_count.setText((String) msg.obj);
                pb_circle.setVisibility(View.INVISIBLE);
            }
        }
    };

    private class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_start:
                    if (my_thread == null) {
                        MyThread my_thread = new MyThread();
//                        my_thread = new Thread(thread);
                        my_thread.start();
                    }
                    break;
                case R.id.btn_stop:
                    if (my_thread != null) {
                        my_thread.interrupt();
                        tv_count.setText("사용자에 의해 종료되었습니다.");
                        pb_circle.setVisibility(View.INVISIBLE);
                    }
                    break;
            }
        }
    }
}
