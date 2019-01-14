package iot.example.com.a181224_cameraex;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        TextureView.SurfaceTextureListener {

    Camera camera;
    List<Camera.Size> supportedPreviewSizes;
    Camera.Size previewSize;
    Button btn_start;
    boolean bCameraPerm = false;
    TextureView textureV_show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textureV_show = (TextureView)findViewById(R.id.textureV_show);
        textureV_show.setSurfaceTextureListener(this);

        btn_start = (Button) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(camera != null) {
                    camera.takePicture(null, null, new Camera.PictureCallback() {

                        @Override
                        public void onPictureTaken(byte[] bytes, Camera camera) {
                            FileOutputStream fos;

                            try {
                                File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/myApp");
                                if(!dir.exists()) {
                                    dir.mkdir();
                                }

                                File file = File.createTempFile("IMG-", ".jpg", dir);
                                if(!file.exists()) {
                                    file.createNewFile();
                                }

                                fos = new FileOutputStream(file);
                                fos.write(bytes);
                                fos.flush();
                                fos.close();


                                camera.startPreview();
                            }catch(Exception e) {
                                e.printStackTrace();
                            }

                        }
                    });
                }
            }
        });

        setPermission();

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
        camera = Camera.open();
        Camera.Parameters parameters = camera.getParameters();
        supportedPreviewSizes = parameters.getSupportedPreviewSizes();

        for(int i2 = 0; i2 < supportedPreviewSizes.size(); i2++) {
            Log.d("Preview_Size","PrevieSize width:" + String.valueOf(supportedPreviewSizes.get(i2).width)
                    + " PrevieSize height:" + String.valueOf(supportedPreviewSizes.get(i2).height));
        }

        if(supportedPreviewSizes != null) {
            previewSize = CameraUtil.getOptimalPreviewSize(supportedPreviewSizes,i, i1);
            parameters.setPreviewSize(previewSize.width, previewSize.height);
        }

        int result = CameraUtil.setCameraDisplayOrientation(this, 0);
        parameters.setRotation(result);
        camera.setDisplayOrientation(result);

        parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_PICTURE);
        camera.setParameters(parameters);

        try {
            camera.setPreviewTexture(surfaceTexture);
        } catch(Exception e) {
            e.printStackTrace();
        }
        camera.startPreview();
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        camera.stopPreview();
        camera.release();
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {

    }

    private void setPermission() {
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            bCameraPerm = true;
        }


        if(!bCameraPerm) {
            ActivityCompat.requestPermissions(this,
                    new String[] {
                            Manifest.permission.CAMERA,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, 200);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 200 && grantResults.length > 0) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                bCameraPerm = true;
            }
        }
    }
}