package com.nickming.archiverdemo.archiver;

import com.hu.andun7z.AndUn7z;

import java.io.File;

/**
 * Created by Administrator on 2016/8/26.
 */
public class SevenZipArchiver extends BaseArchiver {
    @Override
    public void doArchiver(File[] files, String destpath) {

    }

    @Override
    public void doUnArchiver(String srcfile, String unrarPath, String password, final IArchiverListener listener) {
        if (listener != null)
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onStartArchiver();
                }
            });

        AndUn7z.extract7z(srcfile,unrarPath);

        if (listener != null)
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    listener.onEndArchiver();
                }
            });


//        try {
//            SevenZFile sevenZFile = new SevenZFile(new File(srcfile));
//            SevenZArchiveEntry entry = sevenZFile.getNextEntry();
//            while(entry!=null){
//                System.out.println(entry.getName());
//                FileOutputStream out = new FileOutputStream(unrarPath + entry.getName());
//                byte[] content = new byte[(int) entry.getSize()];
//                sevenZFile.read(content, 0, content.length);
//                out.write(content);
//                out.close();
//                entry = sevenZFile.getNextEntry();
//            }
//            sevenZFile.close();
//        }catch (Exception e)
//        {
//            e.printStackTrace();
//        }

    }
}
