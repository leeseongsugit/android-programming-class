package iot.example.com.a181220_jsonparserquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView tv_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_show = (TextView) findViewById(R.id.tv_show);

        String json = "{" +
                        "\"weather\" : [{" +
                    "\"id\": 721," +
                    "\"main\": \"Haze\"," +
                    "\"description\": \"haze\"," +
                    "\"icon\": \"50n\"" +
                    "}]," +
                    "\"main\": {" +
                    "\"temp\": 10.14," +
                    "\"pressure\": 1020," +
                    "\"humidity\": 37," +
                    "\"temp_min\": 6," +
                    "\"temp_max\": 13" +
                    "}," +
                    "\"id\": 18392," +
                    "\"name\": \"Seoul\"," +
                    "\"cod\": 200" +
                "}";

        try{
            JSONObject root = new JSONObject(json);

            JSONArray weather = root.getJSONArray("weather");
            JSONObject weather1 = weather.getJSONObject(0);
            int weather1_id = weather1.getInt("id");
            String weather1_main = weather1.getString("main");
            String weather1_des = weather1.getString("description");
            String weather1_icon = weather1.getString("icon");

            JSONObject main = root.getJSONObject("main");

            double main_temp = main.getDouble("temp");
            int main_press = main.getInt("pressure");
            int main_humid = main.getInt("humidity");
            int main_temp_min = main.getInt("temp_min");
            int main_temp_max = main.getInt("temp_max");

            int id = root.getInt("id");
            String name = root.getString("name");
            int cod = root.getInt("cod");

            String result =
                    "weather_id : "+ weather1_id +
                    "\nweather_main : " + weather1_main +
                    "\nweather_des : " + weather1_des +
                    "\nweather_icon : " + weather1_icon +
                    "\nmain_temp : " + main_temp +
                    "\nmain_pressure : " + main_press +
                    "\nmain_humidity : " + main_humid +
                    "\nmain_temp_min : " + main_temp_min +
                    "\nmain_temp_max : " + main_temp_max +
                    "\nid : " + id +
                    "\nname : " + name +
                    "\ncod : " + cod;
            tv_show.setText(result);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
