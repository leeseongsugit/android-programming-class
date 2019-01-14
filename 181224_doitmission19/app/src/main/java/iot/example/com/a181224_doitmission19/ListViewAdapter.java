package iot.example.com.a181224_doitmission19;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by student on 2018-12-24.
 */

public class ListViewAdapter extends BaseAdapter {
    ArrayList<ListViewItem> list; // 자료를 저장하고 있는 ArrayList
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
        layoutInflater = (LayoutInflater)context.getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
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

        if(view == null) {
            view = layoutInflater.inflate(item_layout, viewGroup, false);
        }

        ImageView iv_image = (ImageView) view.findViewById(R.id.iv_image);
        iv_image.setImageResource(list.get(pos).getImg_id());
        iv_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,
                        list.get(pos).getTitle()+"를(을) 선택했습니다.",
                        Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(context, InfoActivity.class);
//                intent.putExtra("imageType", pos);
//                context.startActivity(intent);
            }
        });
        return view;
    }
}