package iot.example.com.a181213_quiz_movie;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import iot.example.com.a181213_quiz_movie.R;
import iot.example.com.a181213_quiz_movie.form.MovieListItem;

public class BookActivity extends AppCompatActivity {

    Button btn_res_reserve, btn_res_cancel, btn_res_datePick, btn_res_timePick, btn_res_clear;
    TextView text_res_date, text_res_time, text_res_selSeat;
    SeekBar res_seekBar;

    int year, month, day, hour, min;
    int mYear, mMonth, mDay, mHour, mMin;
    int availSeat;
    String movie_title;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        Intent intent = getIntent();

        movie_title = intent.getStringExtra("movie_title");

        //객체 참조
        btn_res_datePick = (Button)findViewById(R.id.btn_datePick);
        btn_res_timePick = (Button)findViewById(R.id.btn_timePick);
        btn_res_clear = (Button)findViewById(R.id.btn_clear);
        btn_res_reserve = (Button)findViewById(R.id.btn_reserve);
        btn_res_cancel = (Button)findViewById(R.id.btn_cancel);

        text_res_date = (TextView)findViewById(R.id.text_res_date);
        text_res_time = (TextView)findViewById(R.id.text_res_time);
        text_res_selSeat = (TextView)findViewById(R.id.text_res_selSeat);

        res_seekBar = (SeekBar)findViewById(R.id.res_seekBar);


        //날짜 캘린더 살정
        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);


        final ArrayList<MovieListItem> movieList = new ArrayList<MovieListItem>();

        movieList.add(new MovieListItem(2018, 11, 11, 13, 30, 180, "보헤미안 랩소디", 30, 28));
        movieList.add(new MovieListItem(2018, 12, 11, 13, 30, 120, "블랙팬서", 20, 20));
        movieList.add(new MovieListItem(2018, 12, 12, 13, 30, 150, "궁합", 30, 10));
        movieList.add(new MovieListItem(2018, 11, 14, 13, 30, 210, "7번방의 선물", 20, 20));

        //DatePicker 리스너
        btn_res_datePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(BookActivity.this,
                        dateSetListener, year, month, day).show();
            }
        });

        //TimePicker 리스너
        btn_res_timePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(BookActivity.this, timeSetListener, hour, min, false).show();
            }
        });

        //초기화 버튼 리스너
        btn_res_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                res_seekBar.setProgress(0);
            }
        });

        //SeekBar 리스너
        res_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                text_res_selSeat.setText("선택좌석 : "+ i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        //Cancel 리스너
        btn_res_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_res_reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int timeFlag = 0;
                int seatFlag = 0;
                int count=0, availCount = 0;
                int temp[] = new int[movieList.size()];

                //제목 비교해서 인덱스 temp 배열에 넣기
                for(int i = 0; i < movieList.size(); i++){
                    if(movie_title == movieList.get(i).getTitle()){
                        temp[count++] = i;
                    }
                }

                //temp 배열의 내용을 바탕으로 비교
                for(int i = 0 ; i < temp.length; i++){
                    mYear = movieList.get(temp[i]).getYear();
                    mMonth = movieList.get(temp[i]).getMonth();
                    mDay = movieList.get(temp[i]).getDay();
                    mHour = movieList.get(temp[i]).getHour();
                    mMin = movieList.get(temp[i]).getMin();
                    availSeat = movieList.get(temp[i]).getAllSeat() - movieList.get(temp[i]).getResSeat();

                    if(year <= mYear && month <= mMonth && day <= mDay && hour <= mHour && min <= mMin-10){
                        timeFlag = 1;
                        if(res_seekBar.getProgress() <= availSeat){
                            seatFlag = 1;
                        }
                    }else{
                        Toast.makeText(BookActivity.this, "상영일자 없음", Toast.LENGTH_SHORT).show();
                    }

                    if(seatFlag == 0){
                        Toast.makeText(BookActivity.this, "잔여좌석 부족", Toast.LENGTH_SHORT).show();
                    }
                    if(timeFlag==1 && seatFlag==1){
                        Intent intent = new Intent(BookActivity.this, BookActivity.class);
                        intent.putExtra("movie_year", mYear);
                        intent.putExtra("movie_month", mMonth);
                        intent.putExtra("movie_day", mDay);
                        intent.putExtra("movie_hour", mHour);
                        intent.putExtra("movie_min", mMin);
                        intent.putExtra("movie_title", movie_title);
                        intent.putExtra("movie_seat", res_seekBar.getProgress());
                    }
                }



            }
        });

    }

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            text_res_date.setText(i+"년 "+(i1+1)+"월 " + i2+"일");
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1) {
            text_res_time.setText(i+ "시 " + i1+"분");
        }
    };
}
