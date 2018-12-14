package iot.example.com.a181213_quiz_movie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import iot.example.com.a181213_quiz_movie.adapter.ListViewAdapter2;
import iot.example.com.a181213_quiz_movie.form.ListViewItem;

public class MovieListActivity extends AppCompatActivity {


    ListView lv_movieList;
    ArrayAdapter adapter;
//    ListViewAdapter listViewAdapter;
    final String[] list = {
            "블랙팬서", "궁합", "7번방의 선물", "보헤미안 랩소드"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        // 2. 리스트뷰 객체 만들기
        lv_movieList = (ListView) findViewById(R.id.lv_movieList);


        // Adapter 에게 전달할 데이터 구성하기
        ListViewItem item = new ListViewItem("블랙팬서", "2018.2", R.drawable.black);


        ArrayList<ListViewItem> arrayList = new ArrayList<ListViewItem>();
        arrayList.add(item);
        arrayList.add(new ListViewItem("궁합", "2018.1", R.drawable.match));
        arrayList.add(new ListViewItem("7번방의 선물", "2013.6", R.drawable.present));
        arrayList.add(new ListViewItem("보헤미안 랩소드", "2018.11", R.drawable.rhapsody));


        // 3. 리스트뷰에 Adapter 등록하기
        /**
         * 첫번째 매개변수 : 액티비티 정보 (context 객체)
         * 두번째 매개변수 : 리스트뷰 항목의 레이아웃(안드로이드 제공)
         * 세번째 매개변수 : 표시할 데이터들
         */
//        adapter = new ArrayAdapter<String>(MovieListActivity.this,
//                android.R.layout.simple_list_item_1, list);
//        lv_movieList.setAdapter(adapter);


        ListViewAdapter2 listViewAdpter = new ListViewAdapter2(
                MovieListActivity.this,
                R.layout.listview_item,
                arrayList);

        lv_movieList.setAdapter(listViewAdpter);

        // 4. 리스트 뷰에 OnItemClickListener 등록하기
        lv_movieList.setOnItemClickListener(
            new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(
                        AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(MovieListActivity.this,
                            i + " 선택함", Toast.LENGTH_LONG).show();
                }
        });

    }

}
