package com.nickming.archiverdemo.archiver;

import android.text.TextUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

import java.io.File;

/**
 * Desc:
 * Author:nickming
 * Date:16/8/25
 * Time:20:16
 * E-mail:962570483@qq.com
 */
public class ZipArchiver extends BaseArchiver {
    @Override
    public void doArchiver(File[] files, String destpath) {

    }

    @Override
    public void doUnArchiver(String srcfile, String unrarPath, String password, final IArchiverListener listener) {
        if (TextUtils.isEmpty(srcfile) || TextUtils.isEmpty(unrarPath))
            return;
        File src = new File(srcfile);
        if (!src.exists())
            return;
        try {
            ZipFile zFile = new ZipFile(srcfile);
            zFile.setFileNameCharset("GBK");
            if (!zFile.isValidZipFile())
                throw new ZipException("文件不合法!");

            File destDir = new File(unrarPath);
            if (destDir.isDirectory() && !destDir.exists()) {
                destDir.mkdir();
            }

            if (zFile.isEncrypted()) {
                zFile.setPassword(password.toCharArray());
            }
            if (listener != null)
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        listener.onStartArchiver();
                    }
                });


//            zFile.extractAll(unrarPath);
            FileHeader fh = null;
            final int total = zFile.getFileHeaders().size();
            for (int i = 0; i < zFile.getFileHeaders().size(); i++) {
                fh = (FileHeader) zFile.getFileHeaders().get(i);
//                String entrypath = "";
//                if (fh.isFileNameUTF8Encoded()) {//解決中文乱码
//                    entrypath = fh.getFileName().trim();
//                } else {
//                    entrypath = fh.getFileName().trim();
//                }
//                entrypath = entrypath.replaceAll("\\\\", "/");
//
//                File file = new File(unrarPath + entrypath);
//                Log.d(TAG, "unrar entry file :" + file.getPath());

                zFile.extractFile(fh,unrarPath);

                if (listener != null) {
                    final int finalI = i;
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.onProgressArchiver(finalI + 1, total);
                        }
                    });
                }
            }
        } catch (ZipException e1) {
            e1.printStackTrace();
        }
        if (listener != null)
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onEndArchiver();
                }
            });

    }
}
