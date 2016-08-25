package com.nickming.archiverdemo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.nickming.archiverdemo.archiver.ArchiverManager;
import com.nickming.archiverdemo.archiver.IArchiverListener;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private final static String TAG=MainActivity.class.getSimpleName();

    private Button mButton;

    private String source= Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"test.rar";

    private String destpath=Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"test"+File.separator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ProgressDialog dialog=new ProgressDialog(this, AlertDialog.THEME_HOLO_LIGHT);
        dialog.setMessage("解压中，请稍候...");
        dialog.setTitle("解压文件");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        mButton= (Button) findViewById(R.id.btn_unzip);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArchiverManager.getInstance().doUnArchiver(source, destpath, new IArchiverListener() {
                    @Override
                    public void onStartArchiver() {
                        dialog.show();
                        Log.i(TAG, "onStartArchiver: ");
                    }

                    @Override
                    public void onProgressArchiver(int current, int total) {
                        dialog.setMax(total);
                        dialog.setProgress(current);
                        Log.i(TAG, "onProgressArchiver: "+current);
                    }

                    @Override
                    public void onEndArchiver() {
                        dialog.dismiss();
                        Log.i(TAG, "onEndArchiver: ");
                    }
                });
            }
        });
    }
}
