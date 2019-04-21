package com.example.yuan_wanandroid.presenter;

import android.content.res.Resources;
import android.util.Log;

import com.example.yuan_wanandroid.R;
import com.example.yuan_wanandroid.app.App;
import com.example.yuan_wanandroid.base.BaseObserver;
import com.example.yuan_wanandroid.base.presenter.BasePresenter;
import com.example.yuan_wanandroid.component.RxBus;
import com.example.yuan_wanandroid.contract.MainContract;
import com.example.yuan_wanandroid.event.NightStyleEvent;
import com.example.yuan_wanandroid.event.UpdateEvent;
import com.example.yuan_wanandroid.model.DataModel;
import com.example.yuan_wanandroid.model.entity.Version;
import com.example.yuan_wanandroid.utils.FileUtil;
import com.example.yuan_wanandroid.utils.LogUtil;
import com.example.yuan_wanandroid.utils.RxUtil;

import javax.inject.Inject;

import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/03/06
 *     desc   :
 * </pre>
 */

public class MainActivityPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    @Inject
    public MainActivityPresenter(DataModel model) {
        super(model);
    }

    @Override
    public void subscribeEvent() {
        addRxSubscribe(
                RxBus.getInstance().toObservable(NightStyleEvent.class)
                        .subscribe(nightStyleEvent -> {
                            mView.showNightStyle(nightStyleEvent.isNight());
                        })
        );

        addRxSubscribe(
                RxBus.getInstance().toObservable(UpdateEvent.class)
                        .subscribe(updateEvent -> mView.downloadApk())
        );

    }

    @Override
    public void checkVersion(String currentVersion) {
        addRxSubscribe(
                mModel.getVersionDetail()
                        .compose(RxUtil.rxSchedulerHelper())
                        //设置拦截器，在github的版本大于当前版本时才进行更新操作
                        .filter(version ->
                                Float.valueOf(currentVersion) < (Float.valueOf(version.getTag_name().replace("V", "")))
                        )
                        //进行转化，提取上游的所需要的Version信息然后转化成String类型发送到下游
                        .map(version -> {
                            //设置下载apk的地址
                            mView.setApkUrl(version.getAssets().get(0).getBrowser_download_url());
                            StringBuilder content = new StringBuilder();
                            Resources resources = App.getContext().getResources();
                            content.append(resources.getString(R.string.dialog_version_name)).append("  ").append(version.getTag_name().replace("V","")).append("\n")
                                    .append(resources.getString(R.string.dialog_version_size)).append("  ").append(FileUtil.getFormatSize(version.getAssets().get(0).getSize())).append("\n")
                                    .append(resources.getString(R.string.dialog_version_content)).append("  ").append("\n").append(version.getBody());
                            return content.toString();
                        }).subscribeWith(new BaseObserver<String>(mView, true, true) {
                    @Override
                    public void onNext(String s) {
                        super.onNext(s);
                        mView.showVersionUpdateDialog(s);
                    }
                })
        );
    }

    @Override
    public void setNavCurrentItem(int position) {
        mModel.setNavCurrentItem(position);
    }

    @Override
    public int getNavCurrentItem() {
        return mModel.getNavCurrentItem();
    }
}
