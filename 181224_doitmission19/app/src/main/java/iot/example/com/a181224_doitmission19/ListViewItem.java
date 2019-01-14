package iot.example.com.a181224_doitmission19;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ListViewItem {
    String title;
    int img_id;
    Bitmap thumb;

    public ListViewItem(String title, int img_id, Bitmap thumb) {
        this.title = title;
        this.img_id = img_id;
        this.thumb = thumb;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public Bitmap getThumb() {
        return thumb;
    }

    public void setThumb(Bitmap thumb) {
        this.thumb = thumb;
    }
}
