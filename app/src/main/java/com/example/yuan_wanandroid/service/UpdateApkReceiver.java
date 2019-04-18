package com.example.yuan_wanandroid.service;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.utils.LogUtil;

import java.io.File;

import static android.content.Context.DOWNLOAD_SERVICE;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/04/17
 *     desc   : 版本更新下载完成回调广播接收器
 * </pre>
 */

public class UpdateApkReceiver extends BroadcastReceiver {

    private DownloadManager mDownloadManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        //下载完成
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            long downloadApkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);
            long saveApkId = App.getAppComponent().getDataModel().getDownloadId();
            if (downloadApkId == saveApkId) {
                checkDownloadStatus(context,downloadApkId);
            }
            context.stopService(new Intent(context, UpdateApkService.class));
        }
    }

    /**
     * 查询下载完成文件的状态
     */
    private void checkDownloadStatus(Context context, long downloadId) {
        mDownloadManager = (DownloadManager) context.getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(downloadId);
        Cursor cursor = mDownloadManager.query(query);
        if (cursor.moveToFirst()) {
            int status = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch (status) {
                case DownloadManager.STATUS_SUCCESSFUL:
                    LogUtil.d(LogUtil.TAG_COMMON, "下载完成！");
                    installApk(context,mDownloadManager.getUriForDownloadedFile(downloadId));
                    break;
                case DownloadManager.STATUS_FAILED://下载失败
                    LogUtil.d(LogUtil.TAG_COMMON, "下载失败.....");
                    break;
                case DownloadManager.STATUS_RUNNING://正在下载
                    LogUtil.d(LogUtil.TAG_COMMON, "正在下载.....");
                    break;
                default:
                    break;
            }
        }
    }


    // 通过Intent安装APK文件
    private void installApk(Context context,Uri uri){
        LogUtil.d(LogUtil.TAG_COMMON,"安装程序"+uri);
        File file = new File(Constant.PATH_APK_DOWNLOAD_MANAGER);
        Intent intent = new Intent("android.intent.action.VIEW");
        //适配N
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            Uri contentUrl = FileProvider.getUriForFile(context,"com.example.yuan_wanandroid.fileProvider",file);
            Log.d(LogUtil.TAG_COMMON, "installApk: "+contentUrl);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setDataAndType(contentUrl,"application/vnd.android.package-archive");
        }else{
            intent.setDataAndType(Uri.fromFile(file),"application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(intent);
    }
}
