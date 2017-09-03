package com.igor.latte.net.download;

import android.os.AsyncTask;

import com.igor.latte.net.RestCreator;
import com.igor.latte.net.RestService;
import com.igor.latte.net.callback.IError;
import com.igor.latte.net.callback.IFailure;
import com.igor.latte.net.callback.IRequest;
import com.igor.latte.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Igor on 2017/9/3.
 */

public class DownloadHandler {

    private final String URL;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;

    private final String DOWNLOAD_DIR;
    private final String DOWNLOAD_FILE_NAME;
    private final String DOWNLOAD_FILE_EXTENSION;


    public DownloadHandler(String url,
                           IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError error,
                           String downloadDir,
                           String downloadFileName,
                           String downloadFileExtension) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.DOWNLOAD_DIR = downloadDir;
        this.DOWNLOAD_FILE_NAME = downloadFileName;
        this.DOWNLOAD_FILE_EXTENSION = downloadFileExtension;
    }

    public final void handleDownload() {
        if (REQUEST != null) {
            REQUEST.onStart();
        }

        RestCreator.getRestService().download(URL, PARAMS)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            final ResponseBody responseBody = response.body();
                            final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                                    DOWNLOAD_DIR, DOWNLOAD_FILE_EXTENSION, responseBody, DOWNLOAD_FILE_NAME);

                            // 这里注意判断，否则文件下载不全
                            if (task.isCancelled() && REQUEST != null) {
                                REQUEST.onEnd();
                            }
                        } else {
                            if (ERROR != null) {
                                ERROR.onError(response.code(), response.message());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        if (FAILURE != null) {
                            FAILURE.onFailure();
                        }
                    }
                });
    }
}
