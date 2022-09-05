package com.example.sr2_2020_android2021_projekat.tools;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.sr2_2020_android2021_projekat.api.Routes;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpClient {

    Context context = null;

    public Routes routes = new Retrofit.Builder().
            baseUrl(EnvironmentConfig.baseURL).client(intercept()).
            addConverterFactory(ScalarsConverterFactory.create()).
            addConverterFactory(GsonConverterFactory.create(
                    new GsonBuilder().setLenient().create())).build().create(Routes.class);

    private String authToken;

    public OkHttpClient intercept(){

        OkHttpClient.Builder httpRequest = new OkHttpClient.Builder();

        httpRequest.addInterceptor(chain -> {

            Request request = chain.request();

            authToken = "";

            Log.d("CONTEXT", String.valueOf(context));

            if(context != null) {

                SharedPreferences preferences = PreferenceManager.
                        getDefaultSharedPreferences(context);

                authToken = "Bearer " + preferences.getString(
                        "authToken", null);
            }

            Request.Builder newRequest = request.newBuilder().header(
                    "Authorization", authToken);

            return chain.proceed(newRequest.build());
        });

        return httpRequest.build();
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
