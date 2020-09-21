package com.example.quizstar.allactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizstar.AllAdapter.Challenge_RecyclerView;
import com.example.quizstar.AllAdapter.Challenge_RecyclerView_Pending;
import com.example.quizstar.AllAdapter.RecyclerView_Pending;
import com.example.quizstar.Model.Rank;
import com.example.quizstar.Model.Sent_Data;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Challenges extends AppCompatActivity {

    private RecyclerView recyclerView;
    RelativeLayout tv_new;
    private String auth;
    private SharedPrefrences shPrfs;
    TextView tv_rank, tv_avg_score, tv_win;
    ImageView profile, back;
    List<Sent_Data> dataR;
    TextView pending, result, tv_all;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_challenges);
        recyclerView = findViewById(R.id.recycler_View);
        tv_new = findViewById(R.id.tv_new);
        tv_rank = findViewById(R.id.rank);
        tv_avg_score = findViewById(R.id.avg_score);
        tv_win = findViewById(R.id.win);
        pending = findViewById(R.id.pending);
        result = findViewById(R.id.result);
        profile = findViewById(R.id.profile);
        tv_all = findViewById(R.id.all);
        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Challenges.this, NavigationActivity.class);
                startActivity(intent);
            }
        });

        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // hitApiSentP(auth);
               hitApiChallengePending(auth);
               // hitApiReceive(auth);
             /*   Challenge_RecyclerView_Pending adapter = new Challenge_RecyclerView_Pending(Challenges.this);
                recyclerView.setLayoutManager(new LinearLayoutManager(Challenges.this, LinearLayoutManager.VERTICAL, true));
                recyclerView.setAdapter(adapter);*/
                pending.setBackgroundResource(R.drawable.login_button);
                result.setBackgroundResource(R.drawable.white_button);
                tv_all.setBackgroundResource(R.drawable.white_button);
                pending.setTextColor(Color.WHITE);
                tv_all.setTextColor(Color.BLACK);
                result.setTextColor(Color.BLACK);

            }
        });
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pending.setBackgroundResource(R.drawable.white_button);
                result.setBackgroundResource(R.drawable.login_button);
                tv_all.setBackgroundResource(R.drawable.white_button);
                tv_all.setTextColor(Color.BLACK);
                result.setTextColor(Color.WHITE);
                pending.setTextColor(Color.BLACK);
                hitApiReceive(auth);

            }
        });
        tv_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pending.setBackgroundResource(R.drawable.white_button);
                result.setBackgroundResource(R.drawable.white_button);
                tv_all.setBackgroundResource(R.drawable.login_button);
                tv_all.setTextColor(Color.WHITE);
                result.setTextColor(Color.BLACK);
                pending.setTextColor(Color.BLACK);
                hitApiReceive(auth);

            }
        });

        try {
            shPrfs = SharedPrefrences.getsharedprefInstance(this);
            auth = shPrfs.getUserAuth().toString();
            if (shPrfs.getUserProfile() != null && !shPrfs.getUserProfile().equalsIgnoreCase("")) {
                Picasso.with(Challenges.this).load(shPrfs.getUserProfile()).error(R.drawable.dummy_profilex).into(profile);
            }

            hitApiRank(auth);
            hitApiReceive(auth);
        } catch (Exception e) {

        }
        tv_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Challenges.this, All_Rank_Screen.class);
                startActivity(intent);
            }
        });


    }


    private void hitApiRank(String auth) {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<Rank> call = apiInterface.callRank(auth);
        call.enqueue(new Callback<Rank>() {
            @Override
            public void onResponse(Call<Rank> call, Response<Rank> response) {
                try {
                    //   Toast.makeText(getApplicationContext(), "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        Rank adListModel = response.body();
                        String win = adListModel.getData().getWin_ratio();
                        double fi = Double.parseDouble(win);
                        tv_win.setText(String.format("%.3f",new BigDecimal(fi)));
                        //tv_win.setText(adListModel.getData().getWin_ratio() + "%");
                        String s = adListModel.getData().getAverage();
                        double f = Double.parseDouble(s);
                        tv_avg_score.setText(String.format("%.3f", new BigDecimal(f)));
                        System.out.println(f);
                        tv_rank.setText(adListModel.getData().getRank());
                    }

                } catch (Exception e) {
                    // Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Rank> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }

    private void hitApiReceive(String auth) {
        String authC = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiNjlmYTQzYTk1MzQxNTVlNjMxYjVhNTliYzM5ZjZlY2E2OWQ4NTNmYTA2YTVhMWI5YjczZjAyZDY1OTNhYWY3Mzg1MWM3MGM0MmI2NzUzMjgiLCJpYXQiOjE1OTU4Mzc1NzQsIm5iZiI6MTU5NTgzNzU3NCwiZXhwIjoxNjI3MzczNTc0LCJzdWIiOiIzIiwic2NvcGVzIjpbInVzZXIiXX0.wBI4moIE0Wyt75xISTerOKw-fxXiYGWMqpQk64brCARFqhU32QarLNCmzaEqEv8yBYkTWlOXaxkvgkzbZ0_5TL-ufITmGGMpon5sT9CPzEQJsoelbWgrtvuUQ5e7WNP8xh1OTttDlftfvL2r1qIqVjbj2UD5MaVUxrj04H_cADw8m0IW8R7vwnzf5BSDBFMQlc4xFsMBOuG0PyH9_EFAul_-thuM9eszGOzLXWVfwol7FNN1Adp59Y5wA9yfxT4zxdO8xPOz5RhpeKFNJxefl_8mqOyiBpQFc3_OL7MouEnYDRIoyvritzeM_DfVEQxE2QZ5OQKWM1dtUbYNebeJytoJjhVPaB3-7CySE7yaC8pJ39RleN8rbpmqMJuqC6-Uz_YYbUNlagxeo9-FgfWB53p1i4GlCS9Sz5LN3UorrKj_yJaviTHwBE6sQfvG1ExhRGQEekT59ZPapnfhIlZkW0-Qn3piC1n5QkfieC36A26Ft_w6hiSx2AT1zrpyVLdO3aTCQo8LzqYg9iTWgxodoETK9gxqePQHvS7_CmDqU-7GtDx3H5fkPjJiyciR8ri33LGqAdxisG6KfJMFAbccRyrguJQkQvnpEzL0y0p4E9cRU5Tv7naxDX-TM51YWvkYan6IDvllgpjtG--NDc3bh1GaQo8cMV2t9XiM4UF1DT8";
        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<Sent_Data> call = apiInterface.callReceived(auth);
        call.enqueue(new Callback<Sent_Data>() {
            @Override
            public void onResponse(Call<Sent_Data> call, Response<Sent_Data> response) {
                try {
                    //   Toast.makeText(getApplicationContext(), "" + response.errorBody(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        Sent_Data adListModel = response.body();
                        dataR = adListModel.getData();
                        hitApiSent(auth);
                   /*  Challenge_RecyclerView adapter = new Challenge_RecyclerView(getApplication(), adListModel.getData());
                        recyclerView.setLayoutManager(new LinearLayoutManager(Challenges.this, LinearLayoutManager.VERTICAL, true));
                        recyclerView.setAdapter(adapter);*/
                    }

                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Sent_Data> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "error" + t + call, Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }

    private void hitApiSent(String auth) {
        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<Sent_Data> call = apiInterface.callSent(auth);
        call.enqueue(new Callback<Sent_Data>() {
            @Override
            public void onResponse(Call<Sent_Data> call, Response<Sent_Data> response) {
                try {
                    if (response.isSuccessful()) {
                        Sent_Data adListModel = response.body();
                        Challenge_RecyclerView adapter = new Challenge_RecyclerView(Challenges.this, adListModel.getData(), dataR);
                        recyclerView.setLayoutManager(new LinearLayoutManager(Challenges.this, LinearLayoutManager.VERTICAL, true));
                        recyclerView.setAdapter(adapter);
                   

                    }

                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Sent_Data> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.v("error",t.getMessage());
                call.cancel();
            }
        });

    }

    private void hitApiChallengePending(String auth) {

        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        Call<Sent_Data> call = apiInterface.callSent(auth);
        call.enqueue(new Callback<Sent_Data>() {
            @Override
            public void onResponse(Call<Sent_Data> call, Response<Sent_Data> response) {
                try {
                    if (response.isSuccessful()) {
                        Sent_Data adListModel = response.body();
                        if (adListModel.getData().size() >= 1) {
                            Challenge_RecyclerView_Pending adapter = new Challenge_RecyclerView_Pending(Challenges.this, adListModel.getData());
                            recyclerView.setLayoutManager(new LinearLayoutManager(Challenges.this, LinearLayoutManager.VERTICAL, true));
                            recyclerView.setAdapter(adapter);
                        } else {
                            RecyclerView_Pending adapter = new RecyclerView_Pending(Challenges.this, adListModel.getData());
                            recyclerView.setLayoutManager(new LinearLayoutManager(Challenges.this, LinearLayoutManager.VERTICAL, true));
                            recyclerView.setAdapter(adapter);
                            Toast.makeText(Challenges.this, "Data Not found", Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (Exception e) {
                    //Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Sent_Data> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
               call.cancel();
            }
        });

    }
}