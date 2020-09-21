package com.example.quizstar.RetrofitForApiCalling;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.quizstar.RetrofitForApiCalling.AppConstant.BASE_URL;

public class Apis {


    public static Retrofit postApiClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getclient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
  /*      Retrofit retrofit = null;
        retrofit = new Retrofit.Builder()
                .baseUrl(AppConsta  nt.BASE_URL)
                .client(getclient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;*/
        return retrofit;
    }

    private static OkHttpClient getclient(){
        return new OkHttpClient.Builder().addInterceptor( new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS).build();
    }
}
