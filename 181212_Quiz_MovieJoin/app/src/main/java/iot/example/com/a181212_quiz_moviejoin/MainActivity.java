package iot.example.com.a181212_quiz_moviejoin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_join;
    EditText edit_id, edit_pwd, edit_pwdchk;
    TextView textView;

    String id, pwd, pwdRe;
    int cond1=0, cond2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_id = (EditText)findViewById(R.id.edit_id);
        edit_pwd = (EditText)findViewById(R.id.edit_pwd);
        edit_pwdchk = (EditText)findViewById(R.id.edit_pwdchk);

        btn_join = (Button)findViewById(R.id.btn_join);
        textView = (TextView)findViewById(R.id.textView);

        edit_id.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                id = edit_id.getText().toString();
//                if(id.length() < 5 || id.length()>12){
//                    textView.setText("비정상적인 아이디입니다");
//                    cond1 = 0;
//                }else{
//                    textView.setText("정상적인 아이디입니다");
//                    cond1 = 1;
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                id = edit_id.getText().toString();
                if(id.length() < 5 || id.length()>12){
                    textView.setText("비정상적인 아이디입니다");
                    cond1 = 0;
                }else{
                    textView.setText("정상적인 아이디입니다");
                    cond1 = 1;
                }
            }
        });

        edit_pwdchk.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pwd = edit_pwd.getText().toString();
                pwdRe = edit_pwdchk.getText().toString();

                if(pwd.equals(pwdRe)){
                    textView.setText("비밀번호가 일치합니다.");
                    cond2 = 1;
                }else{
                    textView.setText("비밀번호가 일치하지 않습니다.");
                    cond2 = 0;
                }
            }
        });

        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cond1 == 1 && cond2 == 1){
                    Toast.makeText(MainActivity.this, "반갑습니다. 환영합니다", Toast.LENGTH_SHORT).show();
               }else{
                    textView.setText("정보를 확인해 주세요");
                }
            }
        });

    }

}
