package net.callback;


import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2018/5/3.
 */

public class RequestCallbacks implements Callback {

    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final IRequest REQUEST;

    public RequestCallbacks(ISuccess success, IFailure failure, IError error, IRequest request) {
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.ERROR = error;
        this.REQUEST = request;
    }


    @Override
    public void onResponse(Call call, Response response) {

        if (response.isSuccessful()){
            if (call.isExecuted()){
                if (SUCCESS!=null){

                    SUCCESS.onSuccess(response.body().toString());
                }

            }

        }else {
            if (ERROR!=null){

                ERROR.onError(response.code(),response.message());

            }
        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {

        Log.e("onFailure::",t.getStackTrace().toString());
        Log.e("onFailure::",t.getMessage().toString());

        if (FAILURE!=null){
            FAILURE.onFailure();

        }

    }
}
