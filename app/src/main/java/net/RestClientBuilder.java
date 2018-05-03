package net;

import android.content.Context;

import net.callback.IError;
import net.callback.IFailure;
import net.callback.IRequest;
import net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by Administrator on 2018/5/3.
 */

public class RestClientBuilder {


    private final WeakHashMap<String,Object> PARAMS= new WeakHashMap<>();

    private String mUrl = null;
    private IRequest mIRequest = null;
    private ISuccess mISuccess = null;
    private IFailure mIFailure = null;
    private IError mIError = null;

    private RequestBody mBody = null;
    private Context mContext = null;

    RestClientBuilder(){

    }

    public final RestClientBuilder url(String url){
        this.mUrl= url;
        return this;

    }

    public final RestClientBuilder params(WeakHashMap<String,Object> params){
        PARAMS.putAll(params);
        return this;

    }

    public final RestClientBuilder params(String key,Object value){
        PARAMS.put(key,value);
        return this;

    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder onRequest(IRequest request){
        this.mIRequest= request;
        return this;

    }

    public final RestClientBuilder onSuccess(ISuccess success){
        this.mISuccess= success;
        return this;
    }

    public final RestClientBuilder onFailure(IFailure failure){
        this.mIFailure= failure;
        return this;
    }

    public final RestClientBuilder onError(IError error){
        this.mIError= error;
        return this;
    }

    public final RestClient build(){
        return new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure,
                mIError, mBody,  mContext
                );
    }



}
