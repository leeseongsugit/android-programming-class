package iot.example.com.a181213_quiz_movie;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    EditText et_loginId, et_loginPw;
    Button btn_login, btn_join;
    Boolean isLogin;
    String id, pwd;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Intent loginIntent, joinIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_loginId = (EditText)findViewById(R.id.et_loginId);
        et_loginPw = (EditText)findViewById(R.id.et_loginPw);

        btn_login = (Button)findViewById(R.id.btn_login);
        btn_join = (Button)findViewById(R.id.btn_join);
        loginIntent = new Intent(LoginActivity.this, MovieListActivity.class);
        joinIntent = new Intent(LoginActivity.this, JoinActivity.class);


        sharedPreferences = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        isLogin = sharedPreferences.getBoolean("key1", false);
        id = sharedPreferences.getString("key2", "no data");
        pwd = sharedPreferences.getString("key3", "no data");

        if(isLogin == true){
            startActivity(loginIntent);
        }
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_loginId.getText().toString() == id){
                    if(et_loginPw.getText().toString() == pwd){

                        editor.putBoolean("key1", true);
                        editor.commit();

                        startActivity(loginIntent);
                    }else{
                        Toast.makeText(LoginActivity.this, "비밀번호가 다릅니다", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "아이디를 확인하시죠", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(joinIntent);
            }
        });
    }
}
