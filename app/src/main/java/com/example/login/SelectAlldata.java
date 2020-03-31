package com.example.login;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by BILLY-PC on 08/02/60.
 */

public class SelectAlldata extends AsyncTask<Void, Void, String> {
    private Context context;
    private static final String UrlGetdata = "http://192.168.1.119/Data/Selectdata.php";

    public SelectAlldata(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {

        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(UrlGetdata).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
