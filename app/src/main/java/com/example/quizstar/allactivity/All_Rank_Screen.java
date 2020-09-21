package com.example.quizstar.allactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.quizstar.AllAdapter.RecyclerView_AllRank;
import com.example.quizstar.Model.all_rank.All;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

public class All_Rank_Screen extends AppCompatActivity {

    private RecyclerView recyclerView;
    QuizStarServices apiInterface;
    private SharedPrefrences shPrfs;
    private String auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__rank__screen);
        shPrfs = SharedPrefrences.getsharedprefInstance(this);
        auth = shPrfs.getUserAuth().toString();
        initView();

    }

    private void initView() {
        recyclerView = findViewById(R.id.recycler_View_one);
        loadData();
    }

    private void loadData() {
        apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<All> call = apiInterface.callRankData(auth);
        call.enqueue(new Callback<All>() {
            @Override
            public void onResponse(Call<All> call, Response<All> response) {
                if (response.isSuccessful()) {
                    Log.v("res:", String.valueOf(response));
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        //String data = String.valueOf(response.body().getData().getRanking());
                        RecyclerView_AllRank adapter = new RecyclerView_AllRank(All_Rank_Screen.this, response.body().getData().getRanking());
                        recyclerView.setLayoutManager(new LinearLayoutManager(All_Rank_Screen.this, LinearLayoutManager.VERTICAL, true));
                        recyclerView.setAdapter(adapter);


                    }
                }
            }

            @Override
            public void onFailure(Call<All> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}