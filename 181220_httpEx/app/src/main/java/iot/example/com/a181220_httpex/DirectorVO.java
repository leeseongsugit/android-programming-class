package iot.example.com.a181220_httpex;

import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class DirectorVO extends RealmObject {
    String director;

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
