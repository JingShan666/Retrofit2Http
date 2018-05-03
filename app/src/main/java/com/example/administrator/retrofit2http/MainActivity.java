package com.example.administrator.retrofit2http;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import net.RestClient;
import net.callback.IError;
import net.callback.IFailure;
import net.callback.IRequest;
import net.callback.ISuccess;

public class MainActivity extends AppCompatActivity {

    private Button getHttpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getHttpBtn= findViewById(R.id.startGetHttp);

        getHttpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //doNetWork
                requestToNet();

            }
        });
    }

    private void requestToNet() {
        RestClient.builder()
                .url("")
                //.params("","")
                .onSuccess(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.e("数据请求onSuccess：",response+"response");

                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();

                    }
                })
                .onError(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.e("数据请求onError：",msg+"msg");

                    }
                })
                .onFailure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.e("数据请求onFailure：","onFailure");

                    }
                })
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .build()
                .get();
    }
}
