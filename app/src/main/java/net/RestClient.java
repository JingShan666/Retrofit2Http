package net;

import android.content.Context;

import net.callback.IError;
import net.callback.IFailure;
import net.callback.IRequest;
import net.callback.ISuccess;
import net.callback.RequestCallbacks;

import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * Created by Administrator on 2018/5/3.
 */

public class RestClient {

    private final WeakHashMap<String, Object> PARAMS;
    private final String URL;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;
    private final Context CONTEXT;


    public RestClient(String mUrl,
                      WeakHashMap<String, Object> params,
                      IRequest mIRequest,
                      ISuccess mISuccess,
                      IFailure mIFailure,
                      IError mIError,
                      RequestBody mBody,
                      Context mContext) {
        this.URL = mUrl;
        this.PARAMS = params;
        this.REQUEST = mIRequest;
        this.SUCCESS = mISuccess;
        this.FAILURE = mIFailure;
        this.ERROR = mIError;
        this.BODY = mBody;
        this.CONTEXT = mContext;
    }


    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }

    private void request(HttpMethod method){

        final RestService service= RestCreator.getRestService();
        Call<String> call= null;

        if (REQUEST!=null){
            REQUEST.onRequestStart();

        }

        switch (method){
            case GET:
                call= service.get(URL,PARAMS);
                break;
            case POST:
                call= service.post(URL,PARAMS);
                break;
            case PUT:
                call= service.put(URL,PARAMS);
                break;
            case DELETE:
                call= service.delete(URL,PARAMS);
                break;
            case UPLOAD:

                //TODO
                break;
            case PUT_RAW:

                break;
            case POST_RAW:
                //TODO
                break;

            default:

                break;
        }

        if (call!=null){
            call.enqueue(getRequestCallBack());

        }
    }

    private RequestCallbacks getRequestCallBack(){

        return new RequestCallbacks(
                SUCCESS,
                FAILURE,
                ERROR,
                REQUEST);
    }

    public final void get(){
     request(HttpMethod.GET);
    }

    public final void post(){
        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            request(HttpMethod.POST_RAW);
        }
    }
}
