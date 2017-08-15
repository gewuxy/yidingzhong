package com.yidingzhong.uikit.common.network;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2017/2/14 0014.
 */

public class DownloadManager {
    private DownloadCallback callBack;

    private static final String TAG = "DownLoadManager";

    private static String APK_CONTENTTYPE = "application/vnd.android.package-archive";

    private static String PNG_CONTENTTYPE = "image/png";

    private static String JPG_CONTENTTYPE = "image/jpg";
    private static String MP3_CONTENTTYPE = "audio/mp3";

    private static String fileSuffix="";

    private Handler handler;

    public DownloadManager(DownloadCallback callBack) {
        this.callBack = callBack;
        handler = new Handler();
    }

    private static DownloadManager sInstance;

    /**
     *DownLoadManager getInstance
     */
    public static synchronized DownloadManager getInstance(DownloadCallback callBack) {
        if (sInstance == null) {
            sInstance = new DownloadManager(callBack);
        }
        return sInstance;
    }



    public boolean  writeResponseBodyToDisk(Context context, final String savePath, ResponseBody body) {
        try {
            File futureStudioIconFile = new File(savePath);
            final String name = futureStudioIconFile.getName();

            if (futureStudioIconFile.exists()) {
                futureStudioIconFile.delete();
            }

            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                final long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                Log.d(TAG, "file length: "+ fileSize);
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    if (callBack != null) {
                        final long finalFileSizeDownloaded = fileSizeDownloaded;
                                callBack.onProgress(finalFileSizeDownloaded, fileSize);
                    }
                }

                outputStream.flush();

                if (callBack != null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onSucess(savePath, name, fileSize);
                        }
                    });

                }

                return true;
            } catch (IOException e) {
                e.printStackTrace();
                if (callBack != null) {
                    callBack.onError(e);
                }
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            if (callBack != null) {
                callBack.onError(e);
            }
            return false;
        }
    }
}
