package iot.example.com.a181213_quiz_movie.form;

import java.util.Date;

/**
 * Created by student on 2018-12-13.
 */

public class ListViewItem {
    String title;
    String date;
    int image;

    public ListViewItem(String title, String date, int image) {
        this.title = title;
        this.date = date;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
