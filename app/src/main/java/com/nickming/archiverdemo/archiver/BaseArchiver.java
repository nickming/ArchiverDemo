package com.nickming.archiverdemo.archiver;

import java.io.File;

/**
 * Created by Administrator on 2016/8/25.
 */
public abstract class BaseArchiver {

    protected String TAG=this.getClass().getSimpleName();

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
    public abstract void doUnArchiver(String srcfile, String unrarPath);
}
