package iot.example.com.a181211_calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0;
    Button btn_plus, btn_minus, btn_mul, btn_div, btn_dot, btn_clear, btn_equal;

    EditText editText;

    String temp1="", result="0";

    String cal ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_0 = (Button)findViewById(R.id.btn_0);
        btn_1 = (Button)findViewById(R.id.btn_1);
        btn_2 = (Button)findViewById(R.id.btn_2);
        btn_3 = (Button)findViewById(R.id.btn_3);
        btn_4 = (Button)findViewById(R.id.btn_4);
        btn_5 = (Button)findViewById(R.id.btn_5);
        btn_6 = (Button)findViewById(R.id.btn_6);
        btn_7 = (Button)findViewById(R.id.btn_7);
        btn_8 = (Button)findViewById(R.id.btn_8);
        btn_9 = (Button)findViewById(R.id.btn_9);

        btn_plus = (Button)findViewById(R.id.btn_plus);
        btn_minus = (Button)findViewById(R.id.btn_minus);
        btn_mul = (Button)findViewById(R.id.btn_mul);
        btn_div = (Button)findViewById(R.id.btn_div);
        btn_dot = (Button)findViewById(R.id.btn_dot);
        btn_clear = (Button)findViewById(R.id.btn_clear);
        btn_equal = (Button)findViewById(R.id.btn_equal);

        editText = (EditText)findViewById(R.id.editText);





        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "0";
                }else{
                    temp1 = temp1 + "0";
                }
                editText.setText(temp1);
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "1";
                }else{
                    temp1 = temp1 + "1";
                }

                editText.setText(temp1);
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "2";
                }else{
                    temp1 = temp1 + "2";
                }
                editText.setText(temp1);
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "3";
                }else{
                    temp1 = temp1 + "3";
                }
                editText.setText(temp1);
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "4";
                }else{
                    temp1 = temp1 + "4";
                }
                editText.setText(temp1);
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "5";
                }else{
                    temp1 = temp1 + "5";
                }
                editText.setText(temp1);
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "6";
                }else{
                    temp1 = temp1 + "6";
                }
                editText.setText(temp1);
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "7";
                }else{
                    temp1 = temp1 + "7";
                }
                editText.setText(temp1);
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "8";
                }else{
                    temp1 = temp1 + "8";
                }
                editText.setText(temp1);
            }
        });
        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(temp1.equals("0")){
                    temp1 = "9";
                }else{
                    temp1 = temp1 + "9";
                }
                editText.setText(temp1);
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp1 = temp1 + ".";
                editText.setText(temp1);
            }
        });

        btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                result = Double.toString(Double.parseDouble(result) + Double.parseDouble(temp1));
                result = temp1;
                temp1="0";
                editText.setText(temp1);
                cal = "+";
            }
        });

        btn_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                result = Double.toString(Double.parseDouble(result)-Double.parseDouble(temp1));\
                result = temp1;
                temp1="0";
                editText.setText(temp1);
                cal = "-";
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                result = Double.toString(Double.parseDouble(result)/Double.parseDouble(temp1));
                result = temp1;
                temp1="0";
                editText.setText(temp1);
                cal = "/";
            }
        });
        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                result = Double.toString(Double.parseDouble(result)*Double.parseDouble(temp1));
                result = temp1;
                temp1="0";

                editText.setText(temp1);
                cal = "*";
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                temp1 = "0";
                result = "0";
                cal = "";
                editText.setText(result);
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (cal){
                    case "+":
                        result = Double.toString(Double.parseDouble(temp1)+Double.parseDouble(result));
                        break;
                    case "-":
                        result = Double.toString(Double.parseDouble(temp1)-Double.parseDouble(result));
                        break;
                    case "*":
                        result = Double.toString(Double.parseDouble(temp1)*Double.parseDouble(result));
                        break;
                    default:
                        if(temp1.equals("0")){
                            Toast.makeText(MainActivity.this, "계산불가", Toast.LENGTH_SHORT).show();
                            result = "Infinity";
                        }else {
                            result = Double.toString(Double.parseDouble(result) / Double.parseDouble(temp1));
                        }
                }
                editText.setText(result);
                temp1="0";
                cal = "";
            }
        });
    }
}
