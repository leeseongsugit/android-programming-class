package iot.example.com.a181217_sdfileex;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {


    EditText et_content;
    Button btn_save, btn_img;

    boolean fileReadPermission;
    boolean fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_content = (EditText) findViewById(R.id.et_content);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_img = (Button) findViewById(R.id.btn_save_img);

        // 권한 검사 - sd 카드 읽기
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            fileReadPermission = true;
        }

        // 권한 검사 - SD 카드 쓰기
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            fileWritePermission = true;
        }

        // 권한이 허용되지 않으면, 사용자에게 요청한다.
        if (!fileReadPermission && !fileWritePermission) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 200);
        }
        // IMG 버튼을 누르면 /drawable/smile.png 파일을 비트맵으로 바꾸어
        // sd 카드에 저장한다.
        btn_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 파일을 쓰기 위한 outputstream 객체
                OutputStream outputStream = null;

                try {
                    // 파일을 저장할 디렉토리 이름 & 경로를 지정
                    String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/myApp";
                    File dir = new File(dirPath);

                    // 해당 이름과 경로에 디렉토리가 있다면 새로 만들지 않는다.
                    // 없다면, 새로 디렉토리를 만든다.
                    if (!dir.exists()) {
                        dir.mkdir();
                    }

                    // 앞서 만든 디렉토리에 파일 이름을 지정한다.
                    // 만약 같은 파일이 있다면, 새로 만들지 않는다.
                    // 없다면 새로 파일을 만든다.
                    File file = new File(dirPath + "/myImage.png");

                    if (!file.exists()) {
                        file.createNewFile();
                    }

                    // 파일 안에 데이터를 넣을 stream을 만든다. (예시 : 만두에 소를 넣음)
                    outputStream = new FileOutputStream(file);

                    // drawable 폴더에서 비트맵 파일을 만든다.
                    Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                            R.drawable.smile);
                    // 비트맵을 png 파일 형태로 데이터에 넣는다.
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

                    // 전송 후에 stream을 닫는다.
                    outputStream.close();

                    Intent intent = new Intent(MainActivity.this, ReadImgFileActivity.class);
                    startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // save 버튼을 누르면 edittext에 입력한 글자가 txt 파일로 저장된다.
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //editText 글자 가져오기
                String content = et_content.getText().toString();
                //권한 확인
                if (fileReadPermission && fileWritePermission) {
                    String state = Environment.getExternalStorageState();

                    if (state.equals(Environment.MEDIA_MOUNTED)) {
                        //writer 선언
                        FileWriter writer;

                        try {
                            //sdcard/myApp에 저장되도록 설정
                            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                                    + "/myApp";
                            File dir = new File(dirPath);

                            //myApp 디렉토리가 없을 경우 mkdir()이용 생성
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            //myfile.txt 참조 객체 선언
                            File file = new File(dir + "/myfile.txt");
                            //myfile.txt가 없을 경우 file 생성
                            if (!file.exists()) {
                                file.createNewFile();
                            }

                            //writer 인스턴스 생성 및 write
                            writer = new FileWriter(file, true);
                            writer.write(content);
                            //buffer 비우기
                            writer.flush();
                            //writer 끄기기
                            writer.close();

                           Intent intent = new Intent(MainActivity.this, ReadFileActivity.class);
                            startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //*/
                        //*

                        //*/
                    } else {
                        Toast.makeText(MainActivity.this, "SD 카드가 마운트 되지 않았습니다.",
                                Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(MainActivity.this, "퍼미션이 부여되지 않았습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fileReadPermission = true;
            }
            if (grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                fileWritePermission = true;
            }
        }
    }
}
