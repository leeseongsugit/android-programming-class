package iot.example.com.a181224_doitmission19;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv_main;

    ListViewAdapter listViewAdapter;
    boolean fileReadPermission;
    boolean fileWritePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_main = (ListView)findViewById(R.id.lv_main);

        ArrayList<ListViewItem> arrayList;

        arrayList = new ArrayList<ListViewItem>();

        //자료 불러오기




        //리스트뷰에 adapter 등록하기
        listViewAdapter = new ListViewAdapter(
                MainActivity.this,
                R.layout.list_view_image,
                arrayList);

        lv_main.setAdapter(listViewAdapter);

        lv_main.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                    }
                }
        );
    }
    private String makeImageFile(Bitmap bitmap, String fileName) {

        // 파일을 쓰기 위한 outputstream 객체
        OutputStream outputStream = null;
        String filePath = "";

        try {
            // 파일을 저장할 디렉토리 이름 & 경로를 지정
            String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath()
                    + "/MyMovieApp";
            File dir = new File(dirPath);

            // 해당 이름과 경로에 디렉토리가 있다면 새로 만들지 않는다.
            // 없다면, 새로 디렉토리를 만든다.
            if(!dir.exists()) {
                dir.mkdir();
            }

            filePath = dirPath+"/"+fileName+".png";
            // 앞서 만든 디렉토리에 파일 이름을 지정한다.
            // 만약 같은 파일이 있다면, 새로 만들지 않는다.
            // 없다면 새로 파일을 만든다.
            File file = new File(filePath);

            if(!file.exists()) {
                file.createNewFile();
            }

            // 파일 안에 데이터를 넣을 stream을 만든다. (예시 : 만두에 소를 넣음)
            outputStream = new FileOutputStream(file);

            // 비트맵을 png 파일 형태로 데이터에 넣는다.
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

            // 전송 후에 stream을 닫는다.
            outputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath;
    }
    private void setPermission() {
        // 권한 검사 - sd 카드 읽기
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            fileReadPermission = true;
        }

        // 권한 검사 - SD 카드 쓰기
        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED) {
            fileWritePermission = true;
        }

        // 권한이 허용되지 않으면, 사용자에게 요청한다.
        if(!fileReadPermission && !fileWritePermission) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[] {
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fileReadPermission = true;
            }
            if(grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                fileWritePermission = true;
            }
        }
    }
}
