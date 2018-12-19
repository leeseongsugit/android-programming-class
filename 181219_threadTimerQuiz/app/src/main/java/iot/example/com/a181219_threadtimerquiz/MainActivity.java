package iot.example.com.a181219_threadtimerquiz;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button btn_30s, btn_10m, btn_30m, btn_reset, btn_start, btn_stop;
    TextView textView;
    EditText edit_minute, edit_second;
    ProgressBar progressBar;
    Thread my_thread = null;
    boolean isPlay = false;
    String minute = "00", second = "00";
    int max = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setObject();

        btn_30s.setOnClickListener(new BtnListener());
        btn_10m.setOnClickListener(new BtnListener());
        btn_30m.setOnClickListener(new BtnListener());
        btn_reset.setOnClickListener(new BtnListener());
        btn_start.setOnClickListener(new BtnListener());
        btn_stop.setOnClickListener(new BtnListener());

    }

    public void setObject() {
        btn_30s = (Button) findViewById(R.id.btn_30s);
        btn_10m = (Button) findViewById(R.id.btn_10m);
        btn_30m = (Button) findViewById(R.id.btn_30m);
        btn_reset = (Button) findViewById(R.id.btn_reset);
        btn_start = (Button) findViewById(R.id.btn_start);
        btn_stop = (Button) findViewById(R.id.btn_stop);

        textView = (TextView) findViewById(R.id.textView);

        edit_minute = (EditText) findViewById(R.id.edit_minute);
        edit_second = (EditText) findViewById(R.id.edit_second);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }

    class BtnListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_30s:
                    if (!isPlay) {
                        second = Integer.toString(Integer.parseInt(second) + 30);
                        if (Integer.parseInt(second) >= 60) {
                            minute = Integer.toString(Integer.parseInt(minute) + Integer.parseInt(second) / 60);
                            if (Integer.parseInt(minute) < 10) {
                                minute = "0" + minute;
                            }
                            second = Integer.toString(Integer.parseInt(second) % 60);
                            if (Integer.parseInt(second) < 10) {
                                second = "0" + second;
                            }
                        }
                        textView.setText(minute + ":" + second);
                        edit_minute.setText(minute);
                        edit_second.setText(second);
                    }
                    break;

                case R.id.btn_10m:
                    if (!isPlay) {
                        minute = Integer.toString(Integer.parseInt(minute) + 10);
                        if (Integer.parseInt(minute) > 100) {
                            minute = Integer.toString(Integer.parseInt(minute) % 100);
                        }
                        textView.setText(minute + ":" + second);
                        edit_minute.setText(minute);
                        edit_second.setText(second);
                    }

                    break;

                case R.id.btn_30m:
                    if (!isPlay) {
                        minute = Integer.toString(Integer.parseInt(minute) + 30);
                        if (Integer.parseInt(minute) > 100) {
                            minute = Integer.toString(Integer.parseInt(minute) % 100);
                        }
                        textView.setText(minute + ":" + second);
                        edit_minute.setText(minute);
                        edit_second.setText(second);
                    }

                    break;

                case R.id.btn_reset:
                    if (!isPlay) {
                        second = "00";
                        minute = "00";
                        textView.setText(minute + ":" + second);
                        edit_minute.setText(minute);
                        edit_second.setText(second);
                    }

                    break;

                case R.id.btn_start:
                    if (my_thread == null) {
                        MyThread thread = new MyThread();
                        my_thread = new Thread(thread);
                        isPlay = true;
                        my_thread.start();
                    }
                    break;


                case R.id.btn_stop:
                    if (my_thread != null) {
                        isPlay = false;
                        my_thread.interrupt();

                    }
                    break;
            }
        }

    }

    private class MyThread extends Thread {
        public void run() {
            try {
                int count = Integer.parseInt(minute) * 60 + Integer.parseInt(second);
                max = count;
                while (!Thread.currentThread().isInterrupted()) {
                    Thread.sleep(500);
                    count--;
                    Message msg = new Message();
                    msg.what = 1;
                    msg.arg1 = count;
                    handler.handleMessage(msg);
                    if (count == 0) {
                        msg = new Message();
                        msg.what = 2;
                        msg.obj = "finish";
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
            progressBar.setIndeterminate(false);
            progressBar.setMax(max);
            if (msg.what == 1) {
                minute = Integer.toString(msg.arg1 / 60);
                if (Integer.parseInt(minute) < 10) {
                    minute = "0" + minute;
                }
                second = Integer.toString(msg.arg1 % 60);
                if (Integer.parseInt(second) < 10) {
                    second = "0" + second;
                }
                textView.setText(minute + ":" + second);
                progressBar.setProgress(max - msg.arg1);
            } else if (msg.what == 2) {
                textView.setText("00:00");
                progressBar.setProgress(max);
            }
        }
    };
}
