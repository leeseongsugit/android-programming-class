package iot.example.com.a181213_quiz_movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import iot.example.com.a181213_quiz_movie.R;
import iot.example.com.a181213_quiz_movie.form.ListViewItem;

/**
 * Created by student on 2018-12-13.
 */

public class ListViewAdapter extends BaseAdapter {
    ArrayList<ListViewItem> list;
    Context context;
    int item_layout;
    LayoutInflater layoutInflater;

    public ListViewAdapter(
                    Context context,
                    int item_layout,
                    ArrayList<ListViewItem> list) {
        this.context = context;
        this.item_layout = item_layout;
        this.list = list;
        layoutInflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        if(view == null){
            view = layoutInflater.inflate(item_layout, viewGroup, false);
        }

        ImageView iv_thumb = (ImageView) view.findViewById(R.id.iv_thumb);

        iv_thumb.setImageResource(list.get(pos).getImage());
        iv_thumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "그림 선택", Toast.LENGTH_SHORT).show();
            }
        });

        //텍스트뷰로 제목, 날짜 넣어보기
        TextView text_title = (TextView) view.findViewById(R.id.text_title);
        TextView text_date = (TextView) view.findViewById(R.id.text_date);

        text_title.setText(list.get(pos).getTitle());

        text_date.setText(list.get(pos).getDate());

        return view;
    }
}
