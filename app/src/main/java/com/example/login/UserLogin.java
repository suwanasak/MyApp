package com.example.login;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

/**
 * Created by BILLY-PC on 07/02/60.
 */

public class UserLogin extends AsyncTask<Void, Void, String>{
    Context context;
    private static final String  stringUrl = "http://192.168.1.119/Data/SelectUser.php";
    private String UserName, UserPass;

    public UserLogin(Context context, String userName, String userPass) {
        this.context = context;
        UserName = userName;
        UserPass = userPass;
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Username", UserName)
                    .add("Password", UserPass)
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(stringUrl).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();

        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}