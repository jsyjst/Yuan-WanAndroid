package com.example.yuan_wanandroid.contract;

import com.example.yuan_wanandroid.base.presenter.IPresenter;
import com.example.yuan_wanandroid.base.view.BaseView;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/03/06
 *     desc   :
 * </pre>
 */

public interface MainContract {
    interface View extends BaseView{
        void showVersionUpdateDialog(String versionDetail);//显示
        void downloadApk();//下载apk
        void setApkUrl(String apkUrl);//设置新版本的下载地址
    }

    interface Presenter extends IPresenter<View>{
        void checkVersion(String currentVersion);//检查版本更新
    }
}
