package com.example.sr2_2020_android2021_projekat.tools;

import com.example.sr2_2020_android2021_projekat.api.Routes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EnvironmentConfig {

    public static String protocol = "http";
    public static String ipAddress = "192.168.0.75";
    public static String port = "8080";
    public static String apiRootURL = "/api/";
    public static String baseURL = protocol + "://" + ipAddress + ":" + port + apiRootURL;

    public static Routes routes = new Retrofit.Builder().baseUrl(EnvironmentConfig.baseURL).
            addConverterFactory(GsonConverterFactory.create()).build().create(Routes.class);

}
