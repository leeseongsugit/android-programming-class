package iot.example.com.a181212_doitmission05;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    Button btn_customer, btn_price, btn_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        btn_customer = (Button)findViewById(R.id.btn_customer);
        btn_price = (Button)findViewById(R.id.btn_price);
        btn_product = (Button)findViewById(R.id.btn_product);

        MyButtonListener myButtonListener = new MyButtonListener();
        btn_customer.setOnClickListener(myButtonListener);
        btn_price.setOnClickListener(myButtonListener);
        btn_product.setOnClickListener(myButtonListener);

    }

    class MyButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            String msg;
            Intent intent = new Intent();
            switch (view.getId()){
                case R.id.btn_customer:
                    intent.putExtra("msg" , "고객 관리");
                    break;
                case R.id.btn_price:
                    intent.putExtra("msg" , "매출 관리");
                    break;
                default:
                    intent.putExtra("msg" , "상품 관리");
                    break;
            }
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
