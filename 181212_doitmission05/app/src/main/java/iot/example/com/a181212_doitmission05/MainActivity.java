package iot.example.com.a181212_doitmission05;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_login;
    public static final int REQUERT_CONE_SECOND = 101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login = (Button)findViewById(R.id.btn_login);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivityForResult(intent, REQUERT_CONE_SECOND);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            if(resultCode == RESULT_OK) {
                String msg = data.getStringExtra("msg");
                Toast.makeText(MainActivity.this, msg, Toast.LENGTH_LONG).show();
            }

    }
}
