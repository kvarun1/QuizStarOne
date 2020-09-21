package com.example.quizstar.RetrofitForApiCalling;

import android.content.Context;

import com.example.quizstar.sharedpreferences.SharedPrefrences;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.quizstar.RetrofitForApiCalling.AppConstant.BASE_URL;
import static com.example.quizstar.RetrofitForApiCalling.AppConstant.BASE_URLP;

public class ApiP {

    static Context context;

    public static Retrofit postApiClient(Context context_) {
        Retrofit retrofit = null;
        context = context_;
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URLP)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMzE5OGU3MzJkMjZiYWI5NjY0MzNiMWZhYjRjZjg5MDJmNjhiZmNjYjQ3YjBjYjkyY2M0ODk4NmRmNWU5ODc5MGRjOWRhZDkxYTRjZDQ2MTEiLCJpYXQiOjE1OTgxMjMyMjgsIm5iZiI6MTU5ODEyMzIyOCwiZXhwIjoxNjI5NjU5MjI4LCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.hPIY0QT_JZuGMcMj6mfcYi_GxKuF30QmaorBNovmypVjNZIHFBWWw7HZhLJufiI9l-zTulpJjqBHvB3pwIRO2ISrSYdkSbVHKiBtEkviP7Wc0AB5UEDc0Y0cEAkDKUKyuJd9me09jFzgklIJsCGp4B3Zm2oUucDhHh26TDvK8TxWhFnYdjCzYYlL9ws-Vo2hJ6-zYHpx0Av80TgmyCo_UTgRWgOY8bGczM0RU5hLQKeUN0Pt8qYgFwaq2jy6mwYUo-QhScyb0u_emwwmcW-pddaymSKw-310GaG0ke9yfzGZXyt0ULhcQ6n3WlYhF_HGnVbUawv8K02hxaVGUDfRwAAx56w9U6GhRxfzfx5GGtzNAu2keaBozVZ74pjcccjYgxe_we4QCkLAsvAYx0tPllPEqHNSGaaXxHZALd_WroDm4r971LjI5NNVXUOLlgIBgE1huR25C2PRUSOdFSA63VCZp8zuUShgq02HA89N8YHImAa3Ep5tlr3EVRi4In1MiGNG_ijrXURlyeNeqpx7RriuuN0au5SsB2puaiWVxXA4P_MnGnceWhigF7tuAd3npqhI9oNy9cjVzkD4M9ljTxwPXquqGhWpPVsZ3c8fuMYQmZ_f5e5qO98TGkbNP2H_g3Fu5TuD8Qy9LfsBwITM6HJ5C7spaLgD0wfgqgGESHA")
                    .build();
            return chain.proceed(newRequest);
        }
    }).build();
}
//new SharedPrefrences(context).getUserAuth()
                  //  .addHeader("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiMzE5OGU3MzJkMjZiYWI5NjY0MzNiMWZhYjRjZjg5MDJmNjhiZmNjYjQ3YjBjYjkyY2M0ODk4NmRmNWU5ODc5MGRjOWRhZDkxYTRjZDQ2MTEiLCJpYXQiOjE1OTgxMjMyMjgsIm5iZiI6MTU5ODEyMzIyOCwiZXhwIjoxNjI5NjU5MjI4LCJzdWIiOiI0Iiwic2NvcGVzIjpbXX0.hPIY0QT_JZuGMcMj6mfcYi_GxKuF30QmaorBNovmypVjNZIHFBWWw7HZhLJufiI9l-zTulpJjqBHvB3pwIRO2ISrSYdkSbVHKiBtEkviP7Wc0AB5UEDc0Y0cEAkDKUKyuJd9me09jFzgklIJsCGp4B3Zm2oUucDhHh26TDvK8TxWhFnYdjCzYYlL9ws-Vo2hJ6-zYHpx0Av80TgmyCo_UTgRWgOY8bGczM0RU5hLQKeUN0Pt8qYgFwaq2jy6mwYUo-QhScyb0u_emwwmcW-pddaymSKw-310GaG0ke9yfzGZXyt0ULhcQ6n3WlYhF_HGnVbUawv8K02hxaVGUDfRwAAx56w9U6GhRxfzfx5GGtzNAu2keaBozVZ74pjcccjYgxe_we4QCkLAsvAYx0tPllPEqHNSGaaXxHZALd_WroDm4r971LjI5NNVXUOLlgIBgE1huR25C2PRUSOdFSA63VCZp8zuUShgq02HA89N8YHImAa3Ep5tlr3EVRi4In1MiGNG_ijrXURlyeNeqpx7RriuuN0au5SsB2puaiWVxXA4P_MnGnceWhigF7tuAd3npqhI9oNy9cjVzkD4M9ljTxwPXquqGhWpPVsZ3c8fuMYQmZ_f5e5qO98TGkbNP2H_g3Fu5TuD8Qy9LfsBwITM6HJ5C7spaLgD0wfgqgGESHA")
