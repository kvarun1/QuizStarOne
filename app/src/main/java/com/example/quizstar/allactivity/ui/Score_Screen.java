package com.example.quizstar.allactivity.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quizstar.allactivity.NavigationActivity;
import com.example.quizstar.Model.Score;
import com.example.quizstar.R;
import com.example.quizstar.RetrofitForApiCalling.Apis;
import com.example.quizstar.RetrofitForApiCalling.QuizStarServices;
import com.example.quizstar.sharedpreferences.SharedPrefrences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Score_Screen extends AppCompatActivity {

    private SharedPrefrences shPrfs;
    String auth;
    TextView tv_status, tv_scoreN, tv_blance;
    ImageView play, undo, pause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score__screen);
        tv_status = findViewById(R.id.status);
        tv_scoreN = findViewById(R.id.scoreN);
        tv_blance = findViewById(R.id.blance);
        play = findViewById(R.id.play);
        undo = findViewById(R.id.undo);
        pause = findViewById(R.id.pause);


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Score_Screen.this, NavigationActivity.class);
                startActivity(intent);
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Score_Screen.this, NavigationActivity.class);
                startActivity(intent);
            }
        });


        undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Score_Screen.this, NavigationActivity.class);
                startActivity(intent);
            }
        });


        shPrfs = SharedPrefrences.getsharedprefInstance(Score_Screen.this);
        auth = shPrfs.getUserAuth().toString();
        hitapiScore();
    }

    public void hitapiScore() {
        QuizStarServices apiInterface = Apis.postApiClient().create(QuizStarServices.class);
        //Call<Delete> call = apiInterface.callDelete("
        //  String s="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiOWI3YjY5YjdkZDhiNDE0ZWQyZDkxMmU1OTU4OWFjYWYwMmI3NWVlNWU0M2JiNjE5YmRiMTJkZGYwNTY0OTAwNWU1Y2NjNDY3MzBhMzcyZTUiLCJpYXQiOjE1OTc4MjUwOTcsIm5iZiI6MTU5NzgyNTA5NywiZXhwIjoxNjI5MzYxMDk3LCJzdWIiOiIzNiIsInNjb3BlcyI6WyJ1c2VyIl19.mnEEqSLyOkE0NdpJznfypy-XyCEOZIOTIOqcaqeJNymcmdgLpzDI5FEeSuIac0QrPOTrysrkLg6WFzMckfCQlvBQbFwPcf-tjGsK6_CfstX6BWbkCnl2gM5eyLgyZv_i-e2f9feIEmD9jvyOszTjekSrIblt4vrJ9Q_UF5T9Zt-xxVplc326KbBmuwRJB17njsbcsBRlWBNuFX-GBBI9bC-WHkKsJpEBywKNqel-p28JsKSMkdDuXzlt7fv6NP-Vm4Fg0bmT16uaGjl9Eyumklb79WpEEmWhX0mKEhEL1nLBEfM7JfgLx3z7mDUvNbP-u-JGv1wJqbWzpYu0U97lNINOQeW0BxdlnlcA-yRsXQESlpSp5k4C-Q1hH5VlVDIcNlAn3ad_Xf41mmaUPwBkF2WnNeFPzRbf1y0RMJUiGOzeEu4Ig2h8B4ZYe_JLr7Ocu0Q278ZzSSISbXt0sud-kLHc8LGCoepo7sJStvgyzfHeNT0Tx0zIGIBJ826ynUnhlSK2HRZ-BO05KkX8IlRpOSlWezFUDJyJw-W8cygfOOeQCwhs_OaXXeaRh7maMn4WJQmq0siUlHTcgOYUpvE5qBUMNzToN8oJS-Mz2CcglVewhk8jpuXB86WWNy5iQy4A5nnNdihQiohDAJyiZf19cuOytezrw7LF7Ck66Xu1Gcg";
        // String a="Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIyIiwianRpIjoiZjczODFhZDc0MzEyOWViMzNmZTcwZTIyMDIxYzk1NjFjOTMwMTI1NGI0YzI5N2Q4YzM5OWI2NDZiN2EwZmU4NWY2YmUyMTFjOTQxNDg2ZmQiLCJpYXQiOjE1OTc4MTM2OTgsIm5iZiI6MTU5NzgxMzY5OCwiZXhwIjoxNjI5MzQ5Njk4LCJzdWIiOiIzNiIsInNjb3BlcyI6WyJ1c2VyIl19.B_XhKo1DYw-gNInnmVXvunrQPwWS2POPmxdXykKpOyj1BLVVgLxxiope_56U7boqKQv5YidCqNG2CYdPxRnxCp3tOl-DPiBKe_jynNkZt40SDYsWF93BisW7107_FEIWkRUlXWPamp_8UAsMs9hSXKoGyo026IZPNfKNh-r14Ve7a0aUVDMUDRfwU2zyysLckJL-lH7U4FBSDBiQW_DXjDHVZh7CrkbBLvqy3VVOHH1M1OqHUp-PDdkCe3XXCq7d06O33UGVsVPdGqQJpVlTH8TbKb7ofVszKrXo-oyZIL-sQq_n0eYkZ4TQkfgswCgxwhtC6LbxtTKWNra4CeUcOxaCF-3vP-kdAuAOJ8n0P42Vk0fBeInF-7DoBmDeIwuyxKSVz2FOYdgO3IfrZ2flJmSPq-ivj5JA0Jp28XUxDj6gAovo-CXmfy7UNYGK-WGgs1H1sXX6yxViA-XGP3rCMA0saRFPdmG5_Xy83wpWQZkuuB3KI6eRRGG5U6mlNrszjI_mzEwDxebgaKrrArKcPtzaPXe9B8CCLlYwcyxKkuuZfio0OAemZtpi8YK-2NR0ti2oWvxoXkfi0kUOEdMdjmMaC4HFfk8FvGkeY0Y-g1bB7u6kTAll1lBS-s-XVme4fbRwWyAXhtQ5iq6Ev5JD2clBiBGwIwAZEvInCTq4wAU";
        Call<Score> call = apiInterface.callScore(auth);

        call.enqueue(new Callback<Score>() {
            @Override
            public void onResponse(Call<Score> call, Response<Score> response) {
                //progressDialog.dismiss();
                try {
                    if (response.body().getStatus().equalsIgnoreCase("success")) {
                        Score res = response.body();
                        Score data = res.getData();
                        tv_status.setText(data.getStatus());
                        tv_scoreN.setText(data.getRound() + " / " + data.getMax_rounds());


                    }


                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Score> call, Throwable t) {

                // progressDialog.dismiss();
                Toast.makeText(Score_Screen.this, "Api Fail", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Score_Screen.this, NavigationActivity.class);
        startActivity(intent);
    }
}