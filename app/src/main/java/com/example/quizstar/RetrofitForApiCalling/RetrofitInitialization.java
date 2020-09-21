package com.example.quizstar.RetrofitForApiCalling;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitialization {

    public static QuizStarServices aa_services = null;

    public static Retrofit retrofitGoogle = null;

    public static QuizStarServices getAAService() {
        if (aa_services == null) {
            Retrofit retrofit = new Retrofit.Builder().client(okHttpClient)
                    .baseUrl(AppConstant.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            aa_services = retrofit.create(QuizStarServices.class);
        }
        return aa_services;
    }


    final static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(180, TimeUnit.SECONDS)
            .writeTimeout(180, TimeUnit.SECONDS)
            .readTimeout(180, TimeUnit.SECONDS)
            .build();
    // Google Places API Initializations
    public static synchronized QuizStarServices getGooglePlacesRetrofitInstance() {
        return initGooglePlacesRetrofit();
    }

    private static QuizStarServices initGooglePlacesRetrofit() {
        return getGooglePlacesAPIService();
    }

    public static QuizStarServices getGooglePlacesAPIService() {
        if (retrofitGoogle == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                    .create();

            retrofitGoogle = new Retrofit.Builder().baseUrl(AppConstant.GOOGLE_PLACES_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).client(okHttpClient).build();
        }
        return retrofitGoogle.create(QuizStarServices.class);
    }
}

