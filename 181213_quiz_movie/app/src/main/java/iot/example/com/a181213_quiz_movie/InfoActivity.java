package iot.example.com.a181213_quiz_movie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    int movie_index;
    String movie_title, movie_date;
    int movie_image;

    final int MOVIE_INFO_REQUEST_CODE = 101;

    Button btn_reserve;
    TextView text_movie_title, text_movie_date;
    RatingBar rating_stars;

    LinearLayout linearLayout_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent = getIntent();

        //컴포넌트 객체 생성
        btn_reserve = (Button)findViewById(R.id.btn_reserve);
        text_movie_title = (TextView)findViewById(R.id.text_movie_title);
        text_movie_date = (TextView)findViewById(R.id.text_movie_date);
        rating_stars = (RatingBar)findViewById(R.id.rating_star);
//        movie_thumb = (ImageView)findViewById(R.id.movie_thumb);

        linearLayout_info = (LinearLayout)findViewById(R.id.linearLayout_info);

        if(intent != null && movie_index != -1){
            movie_index = intent.getIntExtra("movie_index", -1);
            movie_title = intent.getStringExtra("movie_title");
            movie_date = intent.getStringExtra("movie_date");
            movie_image = intent.getIntExtra("movie_image", -1);

            text_movie_title.setText(movie_title);
            text_movie_date.setText("개봉일 : " + movie_date);

            ImageView temp = new ImageView(InfoActivity.this);
            temp.setImageResource(movie_image);
            temp.setLayoutParams(new LinearLayout.LayoutParams(500,600));
            temp.setScaleType(ImageView.ScaleType.FIT_XY);

            linearLayout_info.addView(temp);


            // 버튼에 대한 리스너 객체 만들기
            GoToBookListener goToBookListener = new GoToBookListener();

            // 버튼 객체에 리스너 객체 등록
            btn_reserve.setOnClickListener(goToBookListener);

        }else{
            Toast.makeText(InfoActivity.this, "동작 중에 오류가 발생하였습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    class GoToBookListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(InfoActivity.this, BookActivity.class);
            intent.putExtra("movie_index", movie_index);
            intent.putExtra("movie_title", movie_title);
            intent.putExtra("movie_date", movie_date);
            intent.putExtra("movie_image", movie_image);

            startActivityForResult(intent, MOVIE_INFO_REQUEST_CODE);
        }
    }
}
