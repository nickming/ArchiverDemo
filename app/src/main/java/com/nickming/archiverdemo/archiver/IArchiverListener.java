package com.nickming.archiverdemo.archiver;

/**
 * Desc:解压进度接口
 * Author:nickming
 * Date:16/8/25
 * Time:20:14
 * E-mail:962570483@qq.com
 */
public interface IArchiverListener {

    void onStartArchiver();

    void onProgressArchiver(int current,int total);

    void onEndArchiver();
}
