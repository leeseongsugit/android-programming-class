package iot.example.com.a181220_httpex;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by student on 2018-12-21.
 */

public class MovieVO extends RealmObject {
    int number;
    String title;
    public RealmList<DirectorVO> directorList = new RealmList<DirectorVO>();
    public RealmList<ActorVO> actorList = new RealmList<ActorVO>();
    public RealmList<CategoryVO> categoryList = new RealmList<CategoryVO>();
    int runningTime;
    String openDate;


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

    public RealmList<DirectorVO> getDirectorList() {
        return directorList;
    }

    public void setDirectorList(RealmList<DirectorVO> directorList) {
        this.directorList = directorList;
    }

    public RealmList<ActorVO> getActorList() {
        return actorList;
    }

    public void setActorList(RealmList<ActorVO> actorList) {
        this.actorList = actorList;
    }

    public RealmList<CategoryVO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(RealmList<CategoryVO> categoryList) {
        this.categoryList = categoryList;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String getOpenDate() {
        return openDate;
    }

    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
}
