package com.example.yuan_wanandroid.utils;

import android.content.Context;
import android.os.Environment;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * <pre>
 *     author : 残渊
 *     time   : 2019/01/26
 *     desc   : 文件工具类
 * </pre>
 */


public class FileUtil {
    /**
     * 反序列化恢复对象
     * @param context
     * @param fileName
     * @return
     */
    public static Object restoreObject(Context context, String fileName) {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream = null;
        Object object = null;
        try {
            fileInputStream = context.openFileInput(fileName);
            objectInputStream = new ObjectInputStream(fileInputStream);
            object = objectInputStream.readObject();
            return object;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            close(fileInputStream);
            close(objectInputStream);
        }
        return object;
    }

    /**
     * 序列化对象到本地
     */
    public static  void saveObject(Context context, String fileName, Object saveObject) {
        FileOutputStream fileOutputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(saveObject);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fileOutputStream);
            close(objectOutputStream);
        }
    }

    /**
     * 关闭流对象
     */
    public static void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 得到包名所在的缓存路径
     * 当SD卡不存在或者SD卡可被移除的时候，就调用getCacheDir()方法来获取缓存路径，
     * 否则就调用getExternalCacheDir()方法来获取缓存路径。
     * 前者获取到的是 /data/data/<application package>/cache 这个路径。
     * 而后者获取到的就是 /sdcard/Android/data/<application package>/cache 这个路径，
     * @param context
     * @param name
     * @return
     */
    public static String getCachePath(Context context, String name) {
        String cachePath;
        if (!"mounted".equals(Environment.getExternalStorageState()) && Environment.isExternalStorageRemovable()) {
            cachePath = context.getCacheDir().getPath();
        } else {
            cachePath = context.getExternalCacheDir().getPath();
        }

        return cachePath + File.separator + name;
    }


}
