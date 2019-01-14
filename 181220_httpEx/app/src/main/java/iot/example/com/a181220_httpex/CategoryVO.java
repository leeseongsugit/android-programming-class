package iot.example.com.a181220_httpex;

import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class CategoryVO extends RealmObject {
    String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
