package iot.example.com.a181220_httpex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    TextView tv_data;
    ImageView iv_poster;
    int number = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_data = (TextView) findViewById(R.id.tv_data);
        iv_poster = (ImageView) findViewById(R.id.iv_poster);


        String url = "http://70.12.110.69:3000";
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("number", Integer.toString(number));

        MyHttpTask myHttpTask = new MyHttpTask(url, map);
        myHttpTask.execute();

        String url_img = "http://70.12.110.69:3000/files";

        MyImageHttpTask myImageHttpTask = new MyImageHttpTask(url_img, map);
        myImageHttpTask.execute();

    }

    class MyImageHttpTask extends AsyncTask<Void, Void, Bitmap> {

        String url_str;
        HashMap<String, String> map;

        public MyImageHttpTask(String url_str, HashMap<String, String> map) {
            super();

            this.url_str = url_str;
            this.map = map;
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            Bitmap result = null;
            String post_query = "";
            PrintWriter printWriter = null;

            try {
                URL text = new URL(url_str);
                HttpURLConnection http = (HttpURLConnection) text.openConnection();
                http.setRequestProperty("Content-type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                http.setConnectTimeout(10000);
                http.setReadTimeout(10000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                if (map != null && map.size() > 0) {

                    Iterator<String> keys = map.keySet().iterator();

                    boolean first_query_part = true;
                    while (keys.hasNext()) {

                        if (!first_query_part) {
                            post_query += "&";
                        }

                        String key = keys.next();
                        post_query += (key + "=" + URLEncoder.encode(map.get(key), "UTF-8"));

                        first_query_part = false;
                    }

                    // sending to server
                    printWriter = new PrintWriter(new OutputStreamWriter(
                            http.getOutputStream(), "UTF-8"));
                    printWriter.write(post_query);
                    printWriter.flush();

                    // receive from server
                    result = BitmapFactory.decodeStream(http.getInputStream());

                }
            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            } finally {
                try {
                    if (printWriter != null) printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap s) {
            // do something
            iv_poster.setImageBitmap(s);
            this.cancel(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    class MyHttpTask extends AsyncTask<Void, Void, String> {

        String url_str;
        HashMap<String, String> map;


        public MyHttpTask(String url_str, HashMap<String, String> map) {
            super();
            this.url_str = url_str;
            this.map = map;
        }

        @Override
        protected String doInBackground(Void... voids) {
            String json = null;
            String result = null;
            String post_query = "";
            PrintWriter printWriter = null;
            BufferedReader bufferedReader = null;

            try {
                URL text = new URL(url_str);
                HttpURLConnection http = (HttpURLConnection) text.openConnection();
                http.setRequestProperty("Content-type",
                        "application/x-www-form-urlencoded;charset=UTF-8");
                http.setConnectTimeout(10000);
                http.setReadTimeout(10000);
                http.setRequestMethod("POST");
                http.setDoInput(true);
                http.setDoOutput(true);

                if (map != null && map.size() > 0) {

                    Iterator<String> keys = map.keySet().iterator();

                    boolean first_query_part = true;
                    while (keys.hasNext()) {

                        if (!first_query_part) {
                            post_query += "&";
                        }

                        String key = keys.next();
                        post_query += (key + "=" + URLEncoder.encode(map.get(key), "UTF-8"));

                        first_query_part = false;
                    }

                    // sending to server
                    printWriter = new PrintWriter(new OutputStreamWriter(
                            http.getOutputStream(), "UTF-8"));
                    printWriter.write(post_query);
                    printWriter.flush();

                    // receive from server
                    bufferedReader = new BufferedReader(new InputStreamReader(
                            http.getInputStream(), "UTF-8"));
                    StringBuffer stringBuffer = new StringBuffer();
                    String line;

                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuffer.append(line);
                    }
                    json = stringBuffer.toString();
                    try {
                        JSONObject root = new JSONObject(json);

                        final String title = root.getString("title");
                        final JSONArray director = root.getJSONArray("director");
                        final String[] directors = new String[director.length()];
                        for (int i = 0; i < director.length(); i++) {
                            directors[i] = director.getString(i);
                        }

                        JSONArray actor = root.getJSONArray("actor");
                        final String[] actors = new String[actor.length()];
                        for (int i = 0; i < actor.length(); i++) {
                            actors[i] = actor.getString(i);
                        }

                        JSONArray category = root.getJSONArray("category");
                        final String[] categories = new String[category.length()];
                        for (int i = 0; i < category.length(); i++) {
                            categories[i] = category.getString(i);
                        }
                        final int runningTime = root.getInt("runningTime");
                        final String openDate = root.getString("openDate");

//                        result =
//                                        "\n제목 :          " + title +
//                                        "\n감독 :          " + director1 +
//                                        "\n배우 :          " + actors +
//                                        "\n장르 :          " + category1+
//                                        "\n상영시간 :   " + runningTime +
//                                        "\n개봉일 :       " + openDate;
                        Realm.init(getApplicationContext());
                        Realm mRealm = Realm.getDefaultInstance();
                        if (!title.equals("")) {
                            mRealm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    try {
                                        MovieVO movieVO = realm.createObject(MovieVO.class);
                                        movieVO.setNumber(number);
                                        movieVO.setTitle(title);
                                        DirectorVO dir = new DirectorVO();
                                        for (int i = 0; i < directors.length; i++) {
                                            dir.setDirector(directors[i]);
                                            movieVO.getDirectorList().add(dir);
                                        }
                                        ActorVO act = new ActorVO();
                                        for (int i = 0; i < actors.length; i++) {
                                            act.setActor(actors[i]);
                                            movieVO.getActorList().add(act);
                                        }
                                        CategoryVO cat = new CategoryVO();
                                        for (int i = 0; i < categories.length; i++) {
                                            cat.setCategory(categories[i]);
                                            movieVO.getCategoryList().add(cat);
                                        }

                                        movieVO.setRunningTime(runningTime);
                                        movieVO.setOpenDate(openDate);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                }
                            });
                        }
                        RealmResults<MovieVO> results = mRealm.where(MovieVO.class)
                                .equalTo("number", number).findAll();

                        result =
                                "\n제목 : " + results.get(number).getTitle() +
                                        "\n감독 : " + results.get(number).getDirectorList() +
                                        "\n배우 : " + results.get(number).getActorList() +
                                        "\n장르 : " + results.get(number).getCategoryList() +
                                        "\n상영시간 : " + results.get(number).getRunningTime() +
                                        "\n개봉일 : " + results.get(number).getOpenDate();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (printWriter != null) printWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    if (bufferedReader != null) bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            // do something


            tv_data.setText(s);
            this.cancel(true);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }
}