package iot.example.com.a190108_volleytest;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button button, button2;
    EditText editText1, editText2, editText3, editText4, editText5;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        editText1 = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url1 = "http://70.12.110.69:8090/smartbaby/account/android/join";
                StringRequest request1 = new StringRequest(Request.Method.POST, url1,
                        //요청 성공 시
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("result", "[" + response + "]");
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        // 에러 발생 시
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("error", "[" + error.getMessage() + "]");
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    //요청보낼 때 추가로 파라미터가 필요할 경우
                    //url?a=xxx 이런식으로 보내는 대신에 아래처럼 가능.
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("userId", String.valueOf(editText1.getText()));
                        params.put("password", String.valueOf(editText2.getText()));
                        params.put("parName", String.valueOf(editText3.getText()));
                        params.put("babyName", String.valueOf(editText4.getText()));
                        params.put("email", String.valueOf(editText5.getText()));
                        return params;
                    }
                };

                queue.add(request1);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                String url2 = "http://70.12.110.69:8090/smartbaby/account/android/join/idcheck";
                StringRequest request2 = new StringRequest(Request.Method.POST, url2,
                        //요청 성공 시
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("result", "[" + response + "]");
                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        // 에러 발생 시
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("error", "[" + error.getMessage() + "]");
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                    //요청보낼 때 추가로 파라미터가 필요할 경우
                    //url?a=xxx 이런식으로 보내는 대신에 아래처럼 가능.
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        String userId = String.valueOf(editText1.getText());
                        Map<String, String> params = new HashMap<>();
                        params.put("userId", userId);

                        return params;
                    }
                };
                queue.add(request2);
            }
        });

    }
}
