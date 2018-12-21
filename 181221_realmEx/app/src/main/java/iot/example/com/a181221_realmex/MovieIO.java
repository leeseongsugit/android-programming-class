package iot.example.com.a181221_realmex;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class MovieIO extends RealmObject {
    private int number;
    private String title;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
