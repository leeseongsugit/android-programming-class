package iot.example.com.a181217_sdfileex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ReadImgFileActivity extends AppCompatActivity {

    ImageView iv_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_img_file);

        iv_img = (ImageView)findViewById(R.id.iv_result);

        try {
            Bitmap bitMapImage = BitmapFactory.decodeFile(
                    Environment.getExternalStorageDirectory().getAbsolutePath()
                            +"/myApp/myImage.png");
            iv_img.setImageBitmap(bitMapImage);
        }catch(Exception e) {
            e.printStackTrace();
        }

    }
}
