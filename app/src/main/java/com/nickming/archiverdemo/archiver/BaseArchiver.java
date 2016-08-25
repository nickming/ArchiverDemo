package com.nickming.archiverdemo.archiver;

import android.os.Handler;
import android.os.Message;

import java.io.File;

/**
 * Created by Administrator on 2016/8/25.
 */
public abstract class BaseArchiver {

    protected String TAG=this.getClass().getSimpleName();


    protected Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    /**
     * 压缩文件
     * @param files
     * @param destpath
     */
    public abstract void doArchiver(File[] files,String destpath);

    /**
     * 解压文件
     * @param srcfile
     * @param unrarPath
     */
    public abstract void doUnArchiver(String srcfile, String unrarPath,IArchiverListener listener);
}
