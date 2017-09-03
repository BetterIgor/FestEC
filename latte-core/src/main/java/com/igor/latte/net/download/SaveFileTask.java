package com.igor.latte.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;

import com.igor.latte.app.Latte;
import com.igor.latte.net.callback.IError;
import com.igor.latte.net.callback.IFailure;
import com.igor.latte.net.callback.IRequest;
import com.igor.latte.net.callback.ISuccess;
import com.igor.latte.utils.file.FileUtil;

import java.io.File;
import java.io.InputStream;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * Created by Igor on 2017/9/3.
 */

public class SaveFileTask extends AsyncTask<Object, Void, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;


    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0];
        String downloadFileExtension = (String) params[1];
        final ResponseBody body = (ResponseBody) params[2];
        final String downloadFileName = (String) params[3];
        final InputStream is = body.byteStream();

        if (downloadDir == null || downloadDir.isEmpty()) {
            downloadDir = "down_loads";
        }

        if (downloadFileExtension == null || downloadFileExtension.isEmpty()) {

        }

        if (downloadFileName == null || downloadFileName.isEmpty()) {
            return FileUtil.writeToDisk(is, downloadDir, downloadFileExtension.toUpperCase(),
                    downloadFileExtension);
        } else {
            return FileUtil.writeToDisk(is, downloadDir, downloadFileName);
        }
    }

    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);

        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }

        if (REQUEST != null) {
            REQUEST.onEnd();
        }

        autoInstallApk(file);
    }

    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            final Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
