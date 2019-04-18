package com.example.yuan_wanandroid.service;

import android.app.DownloadManager;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.webkit.MimeTypeMap;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.app.Constant;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.utils.CommonUtils;
import com.example.yuan_wanandroid.utils.LogUtil;

import java.io.File;

public class UpdateApkService extends Service {

    private DataModel mDataModel;
    private UpdateApkReceiver mReceiver;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mDataModel = App.getAppComponent().getDataModel();
        mReceiver = new UpdateApkReceiver();
        registerReceiver(mReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String url = intent.getStringExtra(Constant.KEY_APK_URL);
        if (!TextUtils.isEmpty(url)) {
            mDataModel.setDownloadId(downloadApk(url));
            CommonUtils.toastShow(getString(R.string.download_ing));
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    /**
     * 下载apk
     */
    private long downloadApk(String url) {
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //创建目录, 外部存储--> Download文件夹
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).mkdir();
        File file = new File(Constant.PATH_APK_DOWNLOADMANAGER);
        if (file.exists())
            file.delete();
        //设置文件存放路径
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "WanAndroid.apk");
        //设置Notification的标题
        request.setTitle(this.getString(R.string.app_name));
        //设置描述
        request.setDescription(this.getString(R.string.download_ing));
        // 在下载进行的过程中，通知栏中会一直显示该下载的Notification，当下载完成时，该Notification会被移除，这是默认的参数值。
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        //下载的文件可以被系统的Downloads应用扫描到并管理
        request.setVisibleInDownloadsUi(true);
        //设置请求的Mime
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        request.setMimeType(mimeTypeMap.getMimeTypeFromExtension(url));
        //下载网络需求 - 手机数据流量、wifi
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        assert downloadManager != null;
        return downloadManager.enqueue(request);
    }
}
