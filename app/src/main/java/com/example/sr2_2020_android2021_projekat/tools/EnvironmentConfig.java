package com.example.sr2_2020_android2021_projekat.tools;

import com.example.sr2_2020_android2021_projekat.api.Routes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class EnvironmentConfig {

    public static String protocol = "http";
    public static String ipAddress = "192.168.1.6";
    public static String port = "8080";
    public static String apiRootURL = "/api/";
    public static String baseURL = protocol + "://" + ipAddress + ":" + port + apiRootURL;




}
